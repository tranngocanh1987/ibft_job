package com.infoplus.ibft.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infoplus.ibft.common.AppConst;
import com.infoplus.ibft.common.CommonFunctions;
import com.infoplus.ibft.model.FileModel;
import com.infoplus.ibft.model.NapasFileNameModel;
import com.infoplus.ibft.model.NapasFileTransModel;

@Service("CoreFileHandlerService")
public class CoreFileHandlerService extends FileHandler {

	@Value("${file.coreInputDir}")
	private String coreInputDir;

	@Value("${file.napasOutputDir}")
	private String napasOutputDir;

	@Value("${file.coreBackupDir}")
	private String coreBackupDir;

	@Override
	public void send() {
		try {
			historyMessage = "Execute sendFileToNapas job";
			// get core input files
			List<File> inputFiles = getFilesFromDirectory(coreInputDir);

			if (inputFiles != null && inputFiles.size() > 0) {
				for (File inputFile : inputFiles) {
					// read core input file
					FileModel fileModel = readFile(inputFile);
					// move file to backup folder
					moveFile(inputFile.getPath(), coreBackupDir + inputFile.getName());

					if (fileModel != null) {
						List<String> lstCoreData = fileModel.getContents();
						List<String> lstOutputData = new ArrayList<String>();
						String outputData = StringUtils.EMPTY;
						String outputFileName = StringUtils.EMPTY;
						String fileDir = StringUtils.EMPTY;

						if (lstCoreData != null && lstCoreData.size() > 0) {
							// convert file data to output data
							for (String data : lstCoreData) {
								if (StringUtils.isNotEmpty(data)) {
									outputData = executeData(fileModel.getNapasFileName(), data);
									lstOutputData.add(outputData);
								}
							}

							// write to NAPAS_OUT folder
							if (lstOutputData != null && lstOutputData.size() > 0) {
								outputFileName = fileModel.getNapasFileName().createOutputFileName("SL", ".dat");
								fileDir = napasOutputDir + outputFileName;
								boolean writeStatus = writeFile(lstOutputData, fileDir);
								// insert history
								insertHistory(historyMessage + " file: " + fileModel.getNapasFileName().getFullName()
										+ " " + (writeStatus ? "success" : "fail"));
								continue;
							} else
								logger.info("Read file from core. Convert data failed!");
						} else
							logger.info("Read file from core. No data found!");
					} else
						logger.info("Read file from core. No file found!");

					// insert history
					insertHistory(historyMessage + " read file error");
				}
			} else
				logger.info("No files found!");

		} catch (Exception ex) {
			// insert history
			insertHistory(historyMessage + " sendFileToNapas error");
			logger.error("Error!" + ex.toString());
		}
	}
	
	private String executeData(NapasFileNameModel oFileName, String data) {

		if (data.length() > 2) {
			switch (data.substring(0, 2)) {
			case AppConst.HEADER:
				return data;
			case AppConst.DETAIL:
				return CommonFunctions.getMd5Value(getDetailInfo(oFileName, data));
			case AppConst.TRAILER:
				return CommonFunctions.getMd5Value(data);
			default:
				return StringUtils.EMPTY;
			}
		} else {
			logger.error("Wrong data format! " + data);
			return StringUtils.EMPTY;
		}
	}

	private String getDetailInfo(NapasFileNameModel oFileName, String data) {
		String fileName = oFileName.getFullName();

		if (fileName != null && fileName.length() > 0) {
			// update reconcile code
			NapasFileTransModel napasTransModel = new NapasFileTransModel(data);

			switch (oFileName.getTransCode()) {
			case AppConst.NAPAS_ISS:
				switch (napasTransModel.getStatus()) {
				case AppConst.STS_NAPAS:
					napasTransModel.setRRC_Reconcil_Resp_Code(AppConst.CODE_117);
					break;
				case AppConst.STS_CORE:
					napasTransModel.setRRC_Reconcil_Resp_Code(AppConst.CODE_116);
					break;
				default:
					napasTransModel.setRRC_Reconcil_Resp_Code(AppConst.CODE_000);
					break;
				}
				break;
			case AppConst.NAPAS_ACQ:
				napasTransModel.setRRC_Reconcil_Resp_Code(AppConst.CODE_117);
				break;
			case AppConst.NAPAS_BNB:
				napasTransModel.setRRC_Reconcil_Resp_Code(AppConst.CODE_117);
				break;
			default:
				napasTransModel.setRRC_Reconcil_Resp_Code(AppConst.CODE_000);
				break;
			}

			// create output data
			return napasTransModel.toString();
		}
		return StringUtils.EMPTY;
	}
}
