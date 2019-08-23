package com.infoplus.ibft.model;

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.infoplus.ibft.common.AppConst;
import com.infoplus.ibft.common.CommonFunctions;
import com.infoplus.ibft.entity.NapasRccTransCompare;

import lombok.Data;

@Data
public class NapasFileTransModel {
	private String mTI_Msg_Type_Id;
	private String f2_Pr_Account_No;
	private String f3_Process_Code;
	private String sVC_Service_Code;
	private String tCC_Trans_Channel_Code;
	private String f4_Trans_Amount;
	private String rTA_Real_Trans_Amount;
	private String f49_Currency_Code;
	private String f5_Settement_Amount;
	private String f50_Settlement_Curr_Code;
	private String f9_Settlement_Ex_Rate;
	private String f6_Billing_Amount;
	private String rCA_Real_Card_Amount;
	private String f51_Card_Billing_Curr_Code;
	private String f10_Card_Billing_Conv_Rate;
	private String f11_Sys_Trace_Audit_No;
	private String f12_Local_Trans_Time;
	private String f13_Local_Trans_Date;
	private String f15_Settlement_Date;
	private String f18_Merchant_Cat_Code;
	private String f22_Point_Ser_Entry_Mode;
	private String f25_Point_Ser_Condition_Code;
	private String f41_Card_Acpt_Ter_Ind;
	private String aCQ_Acq_Ins_Code;
	private String iSS_Iss_Ind_Code;
	private String mID_Card_Acpt_Ind_Code;
	private String bNB_Benf_Ind_Code;
	private String f102_Source_Acct_No;
	private String f103_Dest_Card;
	private String sVFISSNP_Iss_Fee;
	private String iRFISSACQ_Iss_Fee;
	private String iRFISSBNB_Iss_Fee;
	private String sVFACQNP_ACQ_Fee;
	private String iRFACQISS_ACQ_Fee;
	private String iRFACQBNB_ACQ_Fee;
	private String sVFBNBNP_BNB_Fee;
	private String iRFBNBISS_BNB_Fee;
	private String iRFBNBACQ_BNB_Fee;
	private String f37_Retriev_Ref_No;
	private String f38_Auth_Ind_Response;
	private String tRN_Trans_Ref_No;
	private String rRC_Reconcil_Resp_Code;
	private String rSV1_Reserve_Info_1;
	private String rSV2_Reserve_Info_2;
	private String rSV3_Reseve_Info_3;
	private String cSR_CheckSum;
	private String cSR_Generated_CheckSum;
	private boolean cSR_Mapped;
	
	private String status;
	
	public NapasFileTransModel() {
		super();
	}
	
	public NapasFileTransModel(NapasRccTransCompare entity) {
		this.aCQ_Acq_Ins_Code = entity.getACQ_Acq_Ins_Code();
		this.bNB_Benf_Ind_Code = entity.getBNB_Benf_Ind_Code();
		this.f102_Source_Acct_No = entity.getF102_Source_Acct_No();
		this.f103_Dest_Card = entity.getF103_Dest_Card();
		this.f10_Card_Billing_Conv_Rate = entity.getF10_Card_Billing_Conv_Rate();
		this.f11_Sys_Trace_Audit_No = entity.getF11_Sys_Trace_Audit_No();
		this.f12_Local_Trans_Time = entity.getF12_Local_Trans_Time();
		this.f13_Local_Trans_Date = entity.getF13_Local_Trans_Date();
		this.f15_Settlement_Date = entity.getF15_Settlement_Date();
		this.f18_Merchant_Cat_Code = entity.getF18_Merchant_Cat_Code();
		this.f22_Point_Ser_Entry_Mode = entity.getF22_Point_Ser_Entry_Mode();
		this.f25_Point_Ser_Condition_Code = entity.getF25_Point_Ser_Condition_Code();
		this.f2_Pr_Account_No= entity.getF2_Pr_Account_No();
		this.f37_Retriev_Ref_No = entity.getF37_Retriev_Ref_No();
		this.f38_Auth_Ind_Response = entity.getF38_Auth_Ind_Response();
		this.f3_Process_Code = entity.getF3_Process_Code();
		this.f41_Card_Acpt_Ter_Ind = entity.getF41_Card_Acpt_Ter_Ind();
		this.f49_Currency_Code = entity.getF49_Currency_Code();
		this.f4_Trans_Amount = entity.getF4_Trans_Amount();
		this.f50_Settlement_Curr_Code = entity.getF50_Settlement_Curr_Code();
		this.f51_Card_Billing_Curr_Code = entity.getF51_Card_Billing_Curr_Code();
		this.f5_Settement_Amount = entity.getF5_Settement_Amount();
		this.f6_Billing_Amount = entity.getF6_Billing_Amount();
		this.f9_Settlement_Ex_Rate = entity.getF9_Settlement_Ex_Rate();
		this.iRFACQBNB_ACQ_Fee = entity.getIRFACQBNB_ACQ_Fee();
		this.iRFACQISS_ACQ_Fee = entity.getIRFACQISS_ACQ_Fee();
		this.iRFBNBACQ_BNB_Fee = entity.getIRFBNBACQ_BNB_Fee();
		this.iRFBNBISS_BNB_Fee = entity.getIRFBNBISS_BNB_Fee();
		this.iRFISSACQ_Iss_Fee = entity.getIRFISSACQ_Iss_Fee();
		this.iRFISSBNB_Iss_Fee = entity.getIRFISSBNB_Iss_Fee();
		this.iSS_Iss_Ind_Code = entity.getISS_Iss_Ind_Code();
		this.mID_Card_Acpt_Ind_Code = entity.getMID_Card_Acpt_Ind_Code();
		this.mTI_Msg_Type_Id = entity.getMTI_Msg_Type_Id();
		this.rCA_Real_Card_Amount = entity.getRCA_Real_Card_Amount();
		this.rRC_Reconcil_Resp_Code = entity.getRRC_Reconcil_Resp_Code();
		this.rSV1_Reserve_Info_1 = entity.getRSV1_Reserve_Info_1();
		this.rSV2_Reserve_Info_2 = entity.getRSV2_Reserve_Info_2();
		this.rSV3_Reseve_Info_3 = entity.getRSV3_Reseve_Info_3();
		this.rTA_Real_Trans_Amount = entity.getRTA_Real_Trans_Amount();
		this.sVC_Service_Code = entity.getSVC_Service_Code();
		this.sVFACQNP_ACQ_Fee = entity.getSVFACQNP_ACQ_Fee();
		this.sVFBNBNP_BNB_Fee = entity.getSVFBNBNP_BNB_Fee();
		this.sVFISSNP_Iss_Fee = entity.getSVFISSNP_Iss_Fee();
		this.tCC_Trans_Channel_Code = entity.getTCC_Trans_Channel_Code();
		this.tRN_Trans_Ref_No = entity.getTRN_Trans_Ref_No();
		this.cSR_CheckSum = entity.getCSR_CheckSum();
		
		this.status = entity.getStatus();
	}
	
	public NapasFileTransModel(String strData) {
		// remove the DR character
		strData = strData.replaceFirst(AppConst.DETAIL, StringUtils.EMPTY);
		StringTokenizer multiTokenizer = new StringTokenizer(strData, AppConst.TOKEN);

		// get data info
		if (multiTokenizer != null && multiTokenizer.countTokens() > 0) {
			int index = 0;
			String value = StringUtils.EMPTY;
			String key = StringUtils.EMPTY;
			
			while (multiTokenizer.hasMoreTokens()) {
				value = multiTokenizer.nextToken();

				if (index % 2 == 1) {
					parsingValue(key, value);
				} else {
					key = value;
				}

				index++;
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DR");
		 CommonFunctions.setFieldName(AppConst.NAPAS_MTI, AppConst.FIELD_NUM, 5,  this.getMTI_Msg_Type_Id());
		 
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_MTI, AppConst.FIELD_NUM, 5,  this.getMTI_Msg_Type_Id()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F2, AppConst.FIELD_CHAR, 19, this.getF2_Pr_Account_No()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F3, AppConst.FIELD_CHAR, 6, this.getF3_Process_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_SVC, AppConst.FIELD_CHAR, 10, this.getSVC_Service_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_TCC, AppConst.FIELD_CHAR, 2, this.getTCC_Trans_Channel_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F4, AppConst.FIELD_NUM, 12, this.getF4_Trans_Amount()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_RTA, AppConst.FIELD_NUM, 12, this.getRTA_Real_Trans_Amount()));
		
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F49, AppConst.FIELD_NUM, 3, this.getF49_Currency_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F5, AppConst.FIELD_NUM, 12, this.getF5_Settement_Amount()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F50, AppConst.FIELD_NUM, 8, this.getF50_Settlement_Curr_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F9, AppConst.FIELD_NUM, 3, this.getF9_Settlement_Ex_Rate()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F6, AppConst.FIELD_NUM, 12, this.getF6_Billing_Amount()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_RCA, AppConst.FIELD_NUM, 12, this.getRCA_Real_Card_Amount()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F51, AppConst.FIELD_NUM, 3, this.getF51_Card_Billing_Curr_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F10, AppConst.FIELD_NUM, 8, this.getF10_Card_Billing_Conv_Rate()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F11, AppConst.FIELD_NUM, 8, this.getF11_Sys_Trace_Audit_No()));
		
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F12, this.getF12_Local_Trans_Time()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F13, this.getF13_Local_Trans_Date()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F15, this.getF15_Settlement_Date()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F18, AppConst.FIELD_NUM, 4, this.getF18_Merchant_Cat_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F22, AppConst.FIELD_NUM, 3, this.getF22_Point_Ser_Entry_Mode()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F25, AppConst.FIELD_NUM, 2, this.getF25_Point_Ser_Condition_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F41, AppConst.FIELD_CHAR, 8, this.getF41_Card_Acpt_Ter_Ind()));
		
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_ACQ, AppConst.FIELD_CHAR, 8, this.getACQ_Acq_Ins_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_ISS, AppConst.FIELD_CHAR, 8, this.getISS_Iss_Ind_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_MID, AppConst.FIELD_CHAR, 15, this.getMID_Card_Acpt_Ind_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_BNB, AppConst.FIELD_CHAR, 8, this.getBNB_Benf_Ind_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F102, AppConst.FIELD_CHAR, 28, this.getF102_Source_Acct_No()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F103, AppConst.FIELD_CHAR, 28, this.getF103_Dest_Card()));
		
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_SVFISSNP, AppConst.FIELD_NUM, 12, this.getSVFISSNP_Iss_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_IRFISSACQ, AppConst.FIELD_NUM, 12, this.getIRFISSACQ_Iss_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_IRFISSBNB, AppConst.FIELD_NUM, 12, this.getIRFISSBNB_Iss_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_SVFACQNP, AppConst.FIELD_NUM, 12, this.getSVFACQNP_ACQ_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_IRFACQISS, AppConst.FIELD_NUM, 12, this.getIRFACQISS_ACQ_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_IRFACQBNB, AppConst.FIELD_NUM, 12, this.getIRFACQBNB_ACQ_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_SVFBNBNP, AppConst.FIELD_NUM, 12, this.getSVFBNBNP_BNB_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_IRFBNBISS, AppConst.FIELD_NUM, 12, this.getIRFBNBISS_BNB_Fee()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_IRFBNBACQ, AppConst.FIELD_NUM, 12, this.getIRFBNBACQ_BNB_Fee()));
		

		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F37, AppConst.FIELD_CHAR, 12, this.getF37_Retriev_Ref_No()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_F38, AppConst.FIELD_CHAR, 6, this.getF38_Auth_Ind_Response()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_TRN, AppConst.FIELD_CHAR, 16, this.getTRN_Trans_Ref_No()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_RRC, AppConst.FIELD_NUM, 4, this.getRRC_Reconcil_Resp_Code()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_RSV1, AppConst.FIELD_CHAR, 100, this.getRSV1_Reserve_Info_1()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_RSV2, AppConst.FIELD_CHAR, 100, this.getRSV2_Reserve_Info_2()));
		sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_RSV3, AppConst.FIELD_CHAR,100, this.getRSV3_Reseve_Info_3()));
		
		if(StringUtils.isEmpty(this.status))
			sb.append(CommonFunctions.setFieldName(AppConst.NAPAS_STATUS, status));
		
		return sb.toString();
	}
	
	public String toString(String status) {
		String sb =	toString();
		sb += CommonFunctions.setFieldName(AppConst.NAPAS_STATUS, status);
		return sb;
	}
	
 	private void parsingValue(String key, String value) {
		switch(key) {
		case AppConst.NAPAS_MTI:
			this.setMTI_Msg_Type_Id(value);
			break;
		case AppConst.NAPAS_F2:
			this.setF2_Pr_Account_No(value);
			break;
		case AppConst.NAPAS_F3:
			this.setF3_Process_Code(value);
			break;
		case AppConst.NAPAS_SVC:
			this.setSVC_Service_Code(value);
			break;
		case AppConst.NAPAS_TCC:
			this.setTCC_Trans_Channel_Code(value);
			break;
		case AppConst.NAPAS_F4:
			this.setF4_Trans_Amount(value);
			break;
		case AppConst.NAPAS_RTA:
			this.setRTA_Real_Trans_Amount(value);
			break;
		case AppConst.NAPAS_F49:
			this.setF49_Currency_Code(value);
			break;
		case AppConst.NAPAS_F5:
			this.setF5_Settement_Amount(value);
			break;
		case AppConst.NAPAS_F50:
			this.setF50_Settlement_Curr_Code(value);
			break;
		case AppConst.NAPAS_F9:
			this.setF9_Settlement_Ex_Rate(value);
			break;
		case AppConst.NAPAS_F6:
			this.setF6_Billing_Amount(value);
			break;
		case AppConst.NAPAS_RCA:
			this.setRCA_Real_Card_Amount(value);
			break;
		case AppConst.NAPAS_F51:
			this.setF51_Card_Billing_Curr_Code(value);
			break;
		case AppConst.NAPAS_F10:
			this.setF10_Card_Billing_Conv_Rate(value);
			break;
		case AppConst.NAPAS_F11:
			this.setF11_Sys_Trace_Audit_No(value);
			break;
		case AppConst.NAPAS_F12:
			this.setF12_Local_Trans_Time(value);
			break;
		case AppConst.NAPAS_F13:
			this.setF13_Local_Trans_Date(value);
			break;
		case AppConst.NAPAS_F15:
			this.setF15_Settlement_Date(value);
			break;
		case AppConst.NAPAS_F18:
			this.setF18_Merchant_Cat_Code(value);
			break;
		case AppConst.NAPAS_F22:
			this.setF22_Point_Ser_Entry_Mode(value);
			break;
		case AppConst.NAPAS_F25:
			this.setF25_Point_Ser_Condition_Code(value);
			break;
		case AppConst.NAPAS_F41:
			this.setF41_Card_Acpt_Ter_Ind(value);
			break;
		case AppConst.NAPAS_ACQ:
			this.setACQ_Acq_Ins_Code(value);
			break;
		case AppConst.NAPAS_ISS:
			this.setISS_Iss_Ind_Code(value);
			break;
		case AppConst.NAPAS_MID:
			this.setMID_Card_Acpt_Ind_Code(value);
			break;
		case AppConst.NAPAS_BNB:
			this.setBNB_Benf_Ind_Code(value);
			break;
		case AppConst.NAPAS_F102:
			this.setF102_Source_Acct_No(value);
			break;
		case AppConst.NAPAS_F103:
			this.setF103_Dest_Card(value);
			break;
		case AppConst.NAPAS_SVFISSNP:
			this.setSVFISSNP_Iss_Fee(value);
			break;
		case AppConst.NAPAS_IRFISSACQ:
			this.setIRFISSACQ_Iss_Fee(value);
			break;
		case AppConst.NAPAS_IRFISSBNB:
			this.setIRFISSBNB_Iss_Fee(value);
			break;
		case AppConst.NAPAS_SVFACQNP:
			this.setSVFACQNP_ACQ_Fee(value);
			break;
		case AppConst.NAPAS_IRFACQISS:
			this.setIRFACQISS_ACQ_Fee(value);
			break;
		case AppConst.NAPAS_IRFACQBNB:
			this.setIRFACQBNB_ACQ_Fee(value);
			break;
		case AppConst.NAPAS_SVFBNBNP:
			this.setSVFBNBNP_BNB_Fee(value);
			break;
		case AppConst.NAPAS_IRFBNBISS:
			this.setIRFBNBISS_BNB_Fee(value);
			break;
		case AppConst.NAPAS_IRFBNBACQ:
			this.setIRFBNBACQ_BNB_Fee(value);
			break;
		case AppConst.NAPAS_F37:
			this.setF37_Retriev_Ref_No(value);
			break;
		case AppConst.NAPAS_F38:
			this.setF38_Auth_Ind_Response(value);
			break;
		case AppConst.NAPAS_TRN:
			this.setTRN_Trans_Ref_No(value);
			break;
		case AppConst.NAPAS_RRC:
			this.setRRC_Reconcil_Resp_Code(value);
			break;
		case AppConst.NAPAS_RSV1:
			this.setRSV1_Reserve_Info_1(value);
			break;
		case AppConst.NAPAS_RSV2:
			this.setRSV2_Reserve_Info_2(value);
			break;
		case AppConst.NAPAS_RSV3:
			this.setRSV3_Reseve_Info_3(value);
			break;
		case AppConst.NAPAS_CSR:
			this.setCSR_CheckSum(value);
		case AppConst.NAPAS_STATUS:
			this.setStatus(value);
			break;
		default:
			break;
		}
	}

}
