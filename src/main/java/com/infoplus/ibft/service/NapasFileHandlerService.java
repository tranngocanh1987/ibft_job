package com.infoplus.ibft.service;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infoplus.ibft.common.AppConst;
import com.infoplus.ibft.common.CheckMD5;
import com.infoplus.ibft.common.CommonFunctions;
import com.infoplus.ibft.entity.NapasRccTransCompare;
import com.infoplus.ibft.entity.NapasReconciliationTransTemp;
import com.infoplus.ibft.model.FileModel;
import com.infoplus.ibft.model.NapasFileNameModel;
import com.infoplus.ibft.model.NapasFileTransModel;
import com.infoplus.ibft.repository.NapasRccTransCompareReps;
import com.infoplus.ibft.repository.NapasRccTransTempReps;

@Service("NapasFileHandlerService")
public class NapasFileHandlerService extends FileHandler {

	@Value("${file.napasInputDir}")
	private String napasInputDir;

	@Value("${file.napasBackupDir}")
	private String napasBackupDir;

	@Value("${file.coreOutputDir}")
	private String coreOutputDir;

	@Autowired
	NapasRccTransTempReps napasRccTransTempReps;

	@Autowired
	NapasRccTransCompareReps napaRccsTransCompareReps;

	private String strHeader;
	private String strTrailer;
	private String fromDate;
	private String toDate;

	@Override
	public void send() {
		try {
			historyMessage = "Execute sendFileToCore job";
			// get napas input files
			List<File> inputFiles = getFilesFromDirectory(napasInputDir);

			if (inputFiles != null && inputFiles.size() > 0) {
				// delete all data in TEMP table before reading file
				napasRccTransTempReps.deleteAll();

				for (File inputFile : inputFiles) {
					// read napas input file
					FileModel fileModel = readFile(inputFile);
					// move file to backup folder
					moveFile(inputFile.getPath(), napasBackupDir + inputFile.getName());

					if (fileModel != null) {
						List<String> lstCoreData = fileModel.getContents();
						List<String> lstOutputData = new ArrayList<String>();
						String outputFileName = StringUtils.EMPTY;
						String fileDir = StringUtils.EMPTY;

						if (lstCoreData != null && lstCoreData.size() > 0) {
							// convert file data to output data
							for (String data : lstCoreData) {
								if (StringUtils.isNotEmpty(data)) {
									executeData(fileModel.getNapasFileName(), data);
								}
							}

							// write to CORE_OUT folder
							outputFileName = fileModel.getNapasFileName()
									.createOutputFileName(fileModel.getNapasFileName().getReportCode(), ".dat");
							fileDir = coreOutputDir + outputFileName;

							lstOutputData = getOutputData(fromDate, toDate);
							if (lstOutputData != null && lstOutputData.size() > 0) {
								boolean writeStatus = writeFile(lstOutputData, fileDir);
								// insert history
								insertHistory(historyMessage + " file: " + fileModel.getNapasFileName().getFullName()
										+ " " + (writeStatus ? "success" : "fail"));
								continue;
							}

						} else
							logger.info("Read file from napas. No data found!");
					} else
						logger.info("Read file from napas. No file found!");

					// insert history
					insertHistory(historyMessage + " read file error");
				}
			} else
				logger.info("No files found!");

		} catch (Exception ex) {
			// insert history
			insertHistory(historyMessage + " sendFileToCore error");
			logger.error("Error!" + ex.toString());
		}
	}

	private String executeData(NapasFileNameModel oFileName, String data) {

		if (data.length() > 2) {
			switch (data.substring(0, 2)) {
			case AppConst.HEADER:
				strHeader = data;
				return strHeader;
			case AppConst.DETAIL:
				return getDetailInfo(oFileName, data);
			case AppConst.TRAILER:
				strTrailer = data;
				return strTrailer;
			default:
				return StringUtils.EMPTY;
			}
		} else {
			logger.error("Wrong data format! " + data);
			return StringUtils.EMPTY;
		}
	}

	private String getDetailInfo(NapasFileNameModel oFileName, String data) {
		boolean checkSumStatus = false;
		String detailData = getNoneMd5Value(data, checkSumStatus);

		String fileName = oFileName.getFullName();
		if (fileName != null && fileName.length() > 0) {

			NapasFileTransModel napasTransModel = new NapasFileTransModel(detailData);
			// String status = "0"; // 0: both, NAPAS: napas only, CORE: core only

			switch (oFileName.getReportCode()) {
			case AppConst.RPC_XL:
				return detailData;
			case AppConst.RPC_TC:
				insertToTemp(napasTransModel, detailData, checkSumStatus);
				return detailData;
			default:
				break;
			}

			return data;
		}
		return StringUtils.EMPTY;
	}

	private String getNoneMd5Value(String data, boolean checkSumStatus) {
		String[] splitValue = data.split(AppConst.CSR_REGEX);

		if (splitValue != null) {
			if (splitValue.length >= 1) {
				CheckMD5 checkMD5 = new CheckMD5();

				String rootData = splitValue[0];
				String md5Data = splitValue[1];

				if (checkMD5.checkCheckSum(rootData, md5Data)) {
					checkSumStatus = true;
					return rootData;
				}
			}
		}
		checkSumStatus = false;
		return data;
	}

	private void insertToTemp(NapasFileTransModel napasTransModel, String data, boolean checkSumStatus) {
		if (napasTransModel != null) {
			// get rangeDate to compare betwen online transaction and reconcile
			getRangeCompareDates(StringUtils.isEmpty(napasTransModel.getF15_Settlement_Date()) ? StringUtils.EMPTY
					: napasTransModel.getF15_Settlement_Date());

			NapasReconciliationTransTemp entity = new NapasReconciliationTransTemp(napasTransModel);

			if (!checkSumStatus) {
				entity.setCSR_Generated_CheckSum(new CheckMD5().getCS(data));
				entity.setCSR_Mapped(false);
			} else {
				entity.setCSR_Generated_CheckSum(napasTransModel.getCSR_CheckSum());
				entity.setCSR_Mapped(true);
			}

			napasRccTransTempReps.save(entity);
		}
	}

	/**
	 * anhtn5 Get compared transaction result between online and reconcile
	 * 
	 * @param fromDate ddMMyy
	 * @param toDate   ddMMyy
	 * @return
	 */
	private List<String> getOutputData(String sfromDate, String sToDate) {
		List<String> lstOutputData = null;
		String fromDate = Year.now().toString() + CommonFunctions.getFirstChars(sfromDate, 4);
		String toDate = Year.now().toString() + CommonFunctions.getFirstChars(sToDate, 4);

		logger.info(" fromDate: " + fromDate + ", toDate:" + toDate);

		int countMissedTrans = napasRccTransTempReps.exeCompareNapasTrans(fromDate, toDate);
		logger.info(" number missed trans: " + countMissedTrans);

		if (countMissedTrans > 0) {
			lstOutputData = new ArrayList<String>();
			lstOutputData.add(strHeader);

			List<NapasRccTransCompare> lstEntites = napaRccsTransCompareReps.findMissedTrans();
			logger.info(" count missed trans: " + lstEntites.size());

			for (NapasRccTransCompare entity : lstEntites) {
				NapasFileTransModel model = new NapasFileTransModel(entity);
				lstOutputData.add(model.toString());
				logger.info(" data: " + model.toString());
			}
			lstOutputData.add(strTrailer);
		}

		return lstOutputData;
	}

	private void getRangeCompareDates(String settlementDate) {

		if (StringUtils.isEmpty(fromDate))
			fromDate = settlementDate;

		if (StringUtils.isEmpty(toDate))
			toDate = settlementDate;

		if (CommonFunctions.compare2SettlementDate(fromDate, settlementDate) < 0)
			fromDate = settlementDate;

		if (CommonFunctions.compare2SettlementDate(toDate, settlementDate) > 0)
			toDate = settlementDate;
	}
}
