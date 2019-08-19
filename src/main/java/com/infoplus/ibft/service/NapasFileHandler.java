package com.infoplus.ibft.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infoplus.ibft.common.AppConst;
import com.infoplus.ibft.common.CheckMD5;
import com.infoplus.ibft.entity.NapasReconciliationTransTemp;
import com.infoplus.ibft.entity.NapasTransactionCompare;
import com.infoplus.ibft.model.NapasDataFormat;
import com.infoplus.ibft.model.NapasFileModel;
import com.infoplus.ibft.model.NapasFileTransactionlModel;
import com.infoplus.ibft.repository.NapasRccTransTempReps;
import com.infoplus.ibft.repository.NapasTransCompareReps;

@Service
public class NapasFileHandler extends FileHandler {

	@Value("${file.coreOutputDir}")
	private String coreOutputDir;
	
	@Autowired
	NapasFileModel napasFileModel;

	@Autowired
	NapasRccTransTempReps napasRccTransTempReps;
	
	@Autowired
	NapasTransCompareReps napasTransCompareReps;

	/**
	 * anhtn read napas reconciliation file
	 * 
	 * @param fileDir
	 * @return
	 */
	public NapasFileModel readFile(String fileDir) {
		List<String> records = new ArrayList<String>();
		NapasFileTransactionlModel napasTransaction = new NapasFileTransactionlModel();
		List<NapasFileTransactionlModel> lstNapasTransaction = new ArrayList<NapasFileTransactionlModel>();

		try {
			napasFileModel.setFileInfo(new File(fileDir));
			napasFileModel.setReceivedDate(new Date());

			BufferedReader reader = new BufferedReader(new FileReader(fileDir));
			String line;
			String recordType;
			while ((line = reader.readLine()) != null) {
				recordType = line.length() > 2 ? line.substring(0, 2) : StringUtils.EMPTY;

				switch (recordType) {
				case AppConst.HEADER:
					napasFileModel.setHeader(line);
					napasFileModel.setTrnDate(getTransactionDate(line));
					break;
				case AppConst.DETAIL:
					napasTransaction = readNapasTransactionInfo(line);

					if (napasTransaction != null) 
						lstNapasTransaction.add(napasTransaction);
					break;
				case AppConst.TRAILER:
					napasFileModel.setTrailer(line);
					break;
				default:
					break;
				}

				// add list transaction
				if (lstNapasTransaction != null && lstNapasTransaction.size() > 0)
					napasFileModel.setDetail(lstNapasTransaction);

				records.add(line);
			}
			napasFileModel.setContents(records);

			// execute Data
			executeData(lstNapasTransaction);

			reader.close();

		} catch (Exception ex) {
			System.err.format("Exception occurred trying to read '%s'.", fileDir);
			ex.printStackTrace();
			return null;
		}
		return napasFileModel;
	}

	private String getTransactionDate(String info) {
		String[] values = info.split(AppConst.TRN_DATE_REGEX);

		if (values != null && values.length > 1)
			return values[1];
		return StringUtils.EMPTY;
	}

	private NapasFileTransactionlModel readNapasTransactionInfo(String info) {
		NapasFileTransactionlModel model = new NapasFileTransactionlModel();

		NapasDataFormat dataFormat = new NapasDataFormat();
		List<NapasDataFormat> lstDataFormat = new ArrayList<NapasDataFormat>();

		// get checksum info
		getCheckSumInfo(model, info);

		// remove the DR character
		info = info.replaceFirst(AppConst.DETAIL, StringUtils.EMPTY);
		StringTokenizer multiTokenizer = new StringTokenizer(info, AppConst.TOKEN);

		// get data info
		if (multiTokenizer != null && multiTokenizer.countTokens() > 0) {
			int index = 0;
			String value = StringUtils.EMPTY;

			while (multiTokenizer.hasMoreTokens()) {
				value = multiTokenizer.nextToken();

				if (index % 2 == 1) {
					dataFormat.setValue(value.trim());
					lstDataFormat.add(dataFormat);
					dataFormat = new NapasDataFormat();
				} else {
					dataFormat.setFieldName(value);
				}

				index++;
			}
			model.setTransactions(lstDataFormat);
		}

		return model;
	}

	/**
	 * anhtn5 get generated CheckSum and isMapped
	 * 
	 * @param model
	 * @param info
	 */
	private void getCheckSumInfo(NapasFileTransactionlModel model, String info) {
		CheckMD5 checkMD5 = new CheckMD5();
		String data = StringUtils.EMPTY;
		String myCheckSum = StringUtils.EMPTY;
		String napasCheckSum = StringUtils.EMPTY;

		String strRegex = AppConst.CSR_REGEX;
		String[] lstValue = info.split(strRegex);

		if (lstValue != null && lstValue.length == 2) {
			myCheckSum = checkMD5.getCS(lstValue[0]);
			napasCheckSum = lstValue[1];

			model.setGeneratedCheckSum(myCheckSum);
			model.setMapped(myCheckSum.compareTo(napasCheckSum) == 0);
		}

		logger.info(" data: " + data);
		logger.info(" my checksum: " + myCheckSum + ", napas checksum:" + napasCheckSum);
	}

	/**
	 * anhtn5 insert napas trans into database
	 * 
	 * @param lstNapasTransModel
	 */
	private void executeData(List<NapasFileTransactionlModel> lstNapasTransModel) {
		if (StringUtils.isNotEmpty(napasFileModel.getTrnDate())) {
			List<NapasReconciliationTransTemp> lstNapasRccTransTempEntities = new ArrayList<NapasReconciliationTransTemp>();

			// parsing model to entity
			for (NapasFileTransactionlModel model : lstNapasTransModel) {
				lstNapasRccTransTempEntities.add(parsingModelToEntity(model));
			}

			// delete all data in temp table
			napasRccTransTempReps.deleteAll();

			if (lstNapasRccTransTempEntities != null && lstNapasRccTransTempEntities.size() > 0) {
				// insert into db
				napasRccTransTempReps.saveAll(lstNapasRccTransTempEntities);
			}

			// compare file
			int countMissedTrans = napasRccTransTempReps.exeCompareNapasTrans(napasFileModel.getTrnDate());
			logger.info(" number missed trans: " + countMissedTrans);
			
			if(countMissedTrans > 0) {
				sendCompareFileToCore();
			}
		}else {
			logger.info("Error! Transaction date is null in .bat file");
		}
	}
	
	private void sendCompareFileToCore() {
		List<NapasTransactionCompare> lstEntites = napasTransCompareReps.findMissedTransByTransDate(napasFileModel.getTrnDate());
		logger.info(" count missed trans: " + lstEntites.size());
		
		if(lstEntites != null && lstEntites.size() > 0){
			NapasFileModel sendModel = new NapasFileModel();
			
			sendModel.setHeader(napasFileModel.getHeader());
			sendModel.setContents(createSendData(lstEntites));
			
			String coreOutputFileName = coreOutputDir + model.getFileInfo().getName();
			writeFile(sendModel, coreOutputFileName);
		}
	}

	/**
	 * anhtn5 parsing Napas transaction model to entity
	 * 
	 * @param model
	 * @return
	 */
	private NapasReconciliationTransTemp parsingModelToEntity(NapasFileTransactionlModel model) {
		NapasReconciliationTransTemp entity = new NapasReconciliationTransTemp();

		for (NapasDataFormat dataFomart : model.getTransactions()) {
			switch (dataFomart.getFieldName()) {
			case AppConst.NAPAS_MTI:
				entity.setMTI_Msg_Type_Id(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F2:
				entity.setF2_Pr_Account_No(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F3:
				entity.setF3_Process_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_SVC:
				entity.setSVC_Service_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_TCC:
				entity.setTCC_Trans_Channel_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F4:
				entity.setF4_Trans_Amount(dataFomart.getValue());
				break;
			case AppConst.NAPAS_RTA:
				entity.setRTA_Real_Trans_Amount(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F49:
				entity.setF49_Currency_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F5:
				entity.setF5_Settement_Amount(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F50:
				entity.setF50_Settlement_Curr_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F9:
				entity.setF9_Settlement_Ex_Rate(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F6:
				entity.setF6_Billing_Amount(dataFomart.getValue());
				break;
			case AppConst.NAPAS_RCA:
				entity.setRCA_Real_Card_Amount(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F51:
				entity.setF51_Card_Billing_Curr_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F10:
				entity.setF10_Card_Billing_Conv_Rate(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F11:
				entity.setF11_Sys_Trace_Audit_No(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F12:
				entity.setF12_Local_Trans_Time(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F13:
				entity.setF13_Local_Trans_Date(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F15:
				entity.setF15_Settlement_Date(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F18:
				entity.setF18_Merchant_Cat_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F22:
				entity.setF22_Point_Ser_Entry_Mode(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F25:
				entity.setF25_Point_Ser_Condition_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F41:
				entity.setF41_Card_Acpt_Ter_Ind(dataFomart.getValue());
				break;
			case AppConst.NAPAS_ACQ:
				entity.setACQ_Acq_Ins_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_ISS:
				entity.setISS_Iss_Ind_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_MID:
				entity.setMID_Card_Acpt_Ind_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_BNB:
				entity.setBNB_Benf_Ind_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F102:
				entity.setF102_Source_Acct_No(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F103:
				entity.setF103_Dest_Card(dataFomart.getValue());
				break;
			case AppConst.NAPAS_SVFISSNP:
				entity.setSVFISSNP_Iss_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_IRFISSACQ:
				entity.setIRFISSACQ_Iss_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_IRFISSBNB:
				entity.setIRFISSBNB_Iss_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_SVFACQNP:
				entity.setSVFACQNP_ACQ_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_IRFACQISS:
				entity.setIRFACQISS_ACQ_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_IRFACQBNB:
				entity.setIRFACQBNB_ACQ_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_SVFBNBNP:
				entity.setSVFBNBNP_BNB_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_IRFBNBISS:
				entity.setIRFBNBISS_BNB_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_IRFBNBACQ:
				entity.setIRFBNBACQ_BNB_Fee(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F37:
				entity.setF37_Retriev_Ref_No(dataFomart.getValue());
				break;
			case AppConst.NAPAS_F38:
				entity.setF38_Auth_Ind_Response(dataFomart.getValue());
				break;
			case AppConst.NAPAS_TRN:
				entity.setTRN_Trans_Ref_No(dataFomart.getValue());
				break;
			case AppConst.NAPAS_RRC:
				entity.setRRC_Reconcil_Resp_Code(dataFomart.getValue());
				break;
			case AppConst.NAPAS_RSV1:
				entity.setRSV1_Reserve_Info_1(dataFomart.getValue());
				break;
			case AppConst.NAPAS_RSV2:
				entity.setRSV2_Reserve_Info_2(dataFomart.getValue());
				break;
			case AppConst.NAPAS_RSV3:
				entity.setRSV3_Reseve_Info_3(dataFomart.getValue());
				break;
			case AppConst.NAPAS_CSR:
				entity.setCSR_CheckSum(dataFomart.getValue());
				break;
			default:
				break;
			}
		}

		entity.setCSR_Generated_CheckSum(model.getGeneratedCheckSum());
		entity.setCSR_Mapped(model.isMapped());

		return entity;
	}

	private List<String> createSendData(List<NapasTransactionCompare> lstEntities){
		List<String> rs = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		for(NapasTransactionCompare entity: lstEntities) {
			sb.append("DR");
			sb.append("[MTI]" + entity.getMti_msg_type_id());
			sb.append("[F2]" + entity.getF2_pr_account_no());
			sb.append("[F3]" + entity.getF3_process_code());
			sb.append("[F4]" + entity.getF4_trans_amount());
			sb.append("[F5]" + entity.getF5_settement_amount());
			sb.append("[F6]" + entity.getF6_billing_amount());
			sb.append("[F49]" + entity.getF49_currency_code());
			sb.append("[F50]" + entity.getF50_settlement_curr_code());
			sb.append("[F51]" + entity.getF51_card_billing_curr_code());
			sb.append("[F12]" + entity.getF12_local_trans_time());
			sb.append("[F13]" + entity.getF13_local_trans_date());
			sb.append("[F15]" + entity.getF15_settlement_date());
			sb.append("[F18]" + entity.getF18_merchant_cat_code());
			sb.append("[F32]" + entity.getF32_acq_ins_code());
			sb.append("[F41]" + entity.getF41_card_acpt_ter_ind());
			sb.append("[F38]" + entity.getF38_auth_ind_response());
			sb.append("[RRC]" + entity.getRrc_reconcil_resp_code());
			sb.append("[F63]" + entity.getF63_trans_ref_no());
			sb.append("[STS]" + entity.getStatus());
			
			rs.add(sb.toString());
		}
		return rs;
	}
}
