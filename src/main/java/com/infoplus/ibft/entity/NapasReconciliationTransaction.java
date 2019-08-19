package com.infoplus.ibft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "napas_reconciliation_transaction")
public class NapasReconciliationTransaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="MTI_Msg_Type_Id")
	private String mTI_Msg_Type_Id;
	
	@Column(name="F2_Pr_Account_No", nullable=false)
	private String f2_Pr_Account_No;
	
	@Column(name="F3_Process_Code")
	private String f3_Process_Code;
	
	@Column(name="SVC_Service_Code")
	private String sVC_Service_Code;
	
	@Column(name="TCC_Trans_Channel_Code")
	private String tCC_Trans_Channel_Code;
	
	@Column(name="F4_Trans_Amount")
	private String f4_Trans_Amount;
	
	@Column(name="RTA_Real_Trans_Amount")
	private String rTA_Real_Trans_Amount;
	
	@Column(name="F49_Currency_Code")
	private String f49_Currency_Code;
	
	@Column(name="F5_Settement_Amount")
	private String f5_Settement_Amount;
	
	@Column(name="F50_Settlement_Curr_Code")
	private String f50_Settlement_Curr_Code;
	
	@Column(name="F9_Settlement_Ex_Rate")
	private String f9_Settlement_Ex_Rate;
	
	@Column(name="F6_Billing_Amount")
	private String f6_Billing_Amount;
	
	@Column(name="RCA_Real_Card_Amount")
	private String rCA_Real_Card_Amount;
	
	@Column(name="F51_Card_Billing_Curr_Code")
	private String f51_Card_Billing_Curr_Code;
	
	@Column(name="F10_Card_Billing_Conv_Rate")
	private String f10_Card_Billing_Conv_Rate;
	
	@Column(name="F11_Sys_Trace_Audit_No")
	private String f11_Sys_Trace_Audit_No;
	
	@Column(name="F12_Local_Trans_Time")
	private String f12_Local_Trans_Time;
	
	@Column(name="F13_Local_Trans_Date")
	private String f13_Local_Trans_Date;
	
	@Column(name="F15_Settlement_Date")
	private String f15_Settlement_Date;
	
	@Column(name="F18_Merchant_Cat_Code")
	private String f18_Merchant_Cat_Code;
	
	@Column(name="F22_Point_Ser_Entry_Mode")
	private String f22_Point_Ser_Entry_Mode;
	
	@Column(name="F25_Point_Ser_Condition_Code")
	private String f25_Point_Ser_Condition_Code;
	
	@Column(name="F41_Card_Acpt_Ter_Ind")
	private String f41_Card_Acpt_Ter_Ind;
	
	@Column(name="ACQ_Acq_Ins_Code")
	private String aCQ_Acq_Ins_Code;
	
	@Column(name="ISS_Iss_Ind_Code")
	private String iSS_Iss_Ind_Code;
	
	@Column(name="MID_Card_Acpt_Ind_Code")
	private String mID_Card_Acpt_Ind_Code;
	
	@Column(name="BNB_Benf_Ind_Code")
	private String bNB_Benf_Ind_Code;
	
	@Column(name="F102_Source_Acct_No")
	private String f102_Source_Acct_No;
	
	@Column(name="F103_Dest_Card")
	private String f103_Dest_Card;
	
	@Column(name="SVFISSNP_Iss_Fee")
	private String sVFISSNP_Iss_Fee;
	
	@Column(name="IRFISSACQ_Iss_Fee")
	private String iRFISSACQ_Iss_Fee;
	
	@Column(name="IRFISSBNB_Iss_Fee")
	private String iRFISSBNB_Iss_Fee;
	
	@Column(name="SVFACQNP_ACQ_Fee")
	private String sVFACQNP_ACQ_Fee;
	
	@Column(name="IRFACQISS_ACQ_Fee")
	private String iRFACQISS_ACQ_Fee;
	
	@Column(name="IRFACQBNB_ACQ_Fee")
	private String iRFACQBNB_ACQ_Fee;
	
	@Column(name="SVFBNBNP_BNB_Fee")
	private String sVFBNBNP_BNB_Fee;
	
	@Column(name="IRFBNBISS_BNB_Fee")
	private String iRFBNBISS_BNB_Fee;
	
	@Column(name="IRFBNBACQ_BNB_Fee")
	private String iRFBNBACQ_BNB_Fee;
	
	@Column(name="F37_Retriev_Ref_No")
	private String f37_Retriev_Ref_No;
	
	@Column(name="F38_Auth_Ind_Response")
	private String f38_Auth_Ind_Response;
	
	@Column(name="TRN_Trans_Ref_No")
	private String tRN_Trans_Ref_No;
	
	@Column(name="RRC_Reconcil_Resp_Code")
	private String rRC_Reconcil_Resp_Code;
	
	@Column(name="RSV1_Reserve_Info_1")
	private String rSV1_Reserve_Info_1;
	
	@Column(name="RSV2_Reserve_Info_2")
	private String rSV2_Reserve_Info_2;
	
	@Column(name="RSV3_Reseve_Info_3")
	private String rSV3_Reseve_Info_3;
	
	@Column(name="CSR_CheckSum")
	private String cSR_CheckSum;
	
	@Column(name="CSR_Generated_CheckSum")
	private String cSR_Generated_CheckSum;
	
	@Column(name="CSR_Mapped")
	private boolean cSR_Mapped;

}
