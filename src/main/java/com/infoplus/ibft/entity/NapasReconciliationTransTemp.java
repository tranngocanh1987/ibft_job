package com.infoplus.ibft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.infoplus.ibft.model.NapasFileTransModel;

import lombok.Data;

@Entity
@Data
@Table(name = "napas_rcc_trans_temp")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "procNapasTransCompare", procedureName = "proc_napas_trans_compare", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fromDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_toDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_countMissedTrans", type = Integer.class) }) })
public class NapasReconciliationTransTemp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "mti_msg_type_id")
	private String mTI_Msg_Type_Id;

	@Column(name = "f2_pr_account_no", nullable = false)
	private String f2_Pr_Account_No;

	@Column(name = "f3_process_code")
	private String f3_Process_Code;

	@Column(name = "svc_service_code")
	private String sVC_Service_Code;

	@Column(name = "tcc_trans_channel_code")
	private String tCC_Trans_Channel_Code;

	@Column(name = "f4_trans_amount")
	private String f4_Trans_Amount;

	@Column(name = "rta_real_trans_amount")
	private String rTA_Real_Trans_Amount;

	@Column(name = "f49_currency_code")
	private String f49_Currency_Code;

	@Column(name = "f5_settement_amount")
	private String f5_Settement_Amount;

	@Column(name = "f50_settlement_curr_code")
	private String f50_Settlement_Curr_Code;

	@Column(name = "f9_settlement_ex_rate")
	private String f9_Settlement_Ex_Rate;

	@Column(name = "f6_billing_amount")
	private String f6_Billing_Amount;

	@Column(name = "rca_real_card_amount")
	private String rCA_Real_Card_Amount;

	@Column(name = "f51_card_billing_curr_code")
	private String f51_Card_Billing_Curr_Code;

	@Column(name = "f10_card_billing_conv_rate")
	private String f10_Card_Billing_Conv_Rate;

	@Column(name = "f11_sys_trace_audit_no")
	private String f11_Sys_Trace_Audit_No;

	@Column(name = "f12_local_trans_time")
	private String f12_Local_Trans_Time;

	@Column(name = "f13_local_trans_date")
	private String f13_Local_Trans_Date;

	@Column(name = "f15_settlement_date")
	private String f15_Settlement_Date;

	@Column(name = "f18_merchant_cat_code")
	private String f18_Merchant_Cat_Code;

	@Column(name = "f22_point_ser_entry_mode")
	private String f22_Point_Ser_Entry_Mode;

	@Column(name = "f25_point_ser_condition_code")
	private String f25_Point_Ser_Condition_Code;

	@Column(name = "f41_card_acpt_ter_ind")
	private String f41_Card_Acpt_Ter_Ind;

	@Column(name = "acq_acq_ins_code")
	private String aCQ_Acq_Ins_Code;

	@Column(name = "iss_iss_ind_code")
	private String iSS_Iss_Ind_Code;

	@Column(name = "mid_card_acpt_ind_code")
	private String mID_Card_Acpt_Ind_Code;

	@Column(name = "bnb_benf_ind_code")
	private String bNB_Benf_Ind_Code;

	@Column(name = "f102_source_acct_no")
	private String f102_Source_Acct_No;

	@Column(name = "f103_dest_card")
	private String f103_Dest_Card;

	@Column(name = "svfissnp_iss_fee")
	private String sVFISSNP_Iss_Fee;

	@Column(name = "irfissacq_iss_fee")
	private String iRFISSACQ_Iss_Fee;

	@Column(name = "irfissbnb_iss_fee")
	private String iRFISSBNB_Iss_Fee;

	@Column(name = "svfacqnp_acq_fee")
	private String sVFACQNP_ACQ_Fee;

	@Column(name = "irfacqiss_acq_fee")
	private String iRFACQISS_ACQ_Fee;

	@Column(name = "irfacqbnb_acq_fee")
	private String iRFACQBNB_ACQ_Fee;

	@Column(name = "svfbnbnp_bnb_fee")
	private String sVFBNBNP_BNB_Fee;

	@Column(name = "irfbnbiss_bnb_fee")
	private String iRFBNBISS_BNB_Fee;

	@Column(name = "irfbnbacq_bnb_fee")
	private String iRFBNBACQ_BNB_Fee;

	@Column(name = "f37_retriev_ref_no")
	private String f37_Retriev_Ref_No;

	@Column(name = "f38_auth_ind_response")
	private String f38_Auth_Ind_Response;

	@Column(name = "trn_trans_ref_no")
	private String tRN_Trans_Ref_No;

	@Column(name = "rrc_reconcil_resp_code")
	private String rRC_Reconcil_Resp_Code;

	@Column(name = "rsv1_reserve_info_1")
	private String rSV1_Reserve_Info_1;

	@Column(name = "rsv2_reserve_info_2")
	private String rSV2_Reserve_Info_2;

	@Column(name = "rsv3_reseve_info_3")
	private String rSV3_Reseve_Info_3;

	@Column(name = "csr_check_sum")
	private String cSR_CheckSum;

	@Column(name = "csr_generated_check_sum")
	private String cSR_Generated_CheckSum;

	@Column(name = "csr_mapped")
	private boolean cSR_Mapped;

	public NapasReconciliationTransTemp() {
		super();
	}

	public NapasReconciliationTransTemp(NapasFileTransModel model) {
		this.mTI_Msg_Type_Id = StringUtils.isEmpty(model.getMTI_Msg_Type_Id()) ? StringUtils.EMPTY
				: model.getMTI_Msg_Type_Id().trim();

		this.aCQ_Acq_Ins_Code = StringUtils.isEmpty(model.getACQ_Acq_Ins_Code()) ? StringUtils.EMPTY
				: model.getACQ_Acq_Ins_Code().trim();
		this.bNB_Benf_Ind_Code = StringUtils.isEmpty(model.getBNB_Benf_Ind_Code()) ? StringUtils.EMPTY
				: model.getBNB_Benf_Ind_Code().trim();
		this.f102_Source_Acct_No = StringUtils.isEmpty(model.getF102_Source_Acct_No()) ? StringUtils.EMPTY
				: model.getF102_Source_Acct_No().trim();
		this.f10_Card_Billing_Conv_Rate = StringUtils.isEmpty(model.getF10_Card_Billing_Conv_Rate()) ? StringUtils.EMPTY
				: model.getF10_Card_Billing_Conv_Rate().trim();
		this.f11_Sys_Trace_Audit_No = StringUtils.isEmpty(model.getF11_Sys_Trace_Audit_No()) ? StringUtils.EMPTY
				: model.getF11_Sys_Trace_Audit_No().trim();
		this.f12_Local_Trans_Time = StringUtils.isEmpty(model.getF12_Local_Trans_Time()) ? StringUtils.EMPTY
				: model.getF12_Local_Trans_Time().trim();
		this.f13_Local_Trans_Date = StringUtils.isEmpty(model.getF13_Local_Trans_Date()) ? StringUtils.EMPTY
				: model.getF13_Local_Trans_Date().trim();
		this.f15_Settlement_Date = StringUtils.isEmpty(model.getF15_Settlement_Date()) ? StringUtils.EMPTY
				: model.getF15_Settlement_Date().trim();
		this.f18_Merchant_Cat_Code = StringUtils.isEmpty(model.getF18_Merchant_Cat_Code()) ? StringUtils.EMPTY
				: model.getF18_Merchant_Cat_Code().trim();

		this.f22_Point_Ser_Entry_Mode = StringUtils.isEmpty(model.getF22_Point_Ser_Entry_Mode()) ? StringUtils.EMPTY
				: model.getF22_Point_Ser_Entry_Mode().trim();
		this.f25_Point_Ser_Condition_Code = StringUtils.isEmpty(model.getF25_Point_Ser_Condition_Code())
				? StringUtils.EMPTY
				: model.getF25_Point_Ser_Condition_Code().trim();
		this.f2_Pr_Account_No = StringUtils.isEmpty(model.getF2_Pr_Account_No()) ? StringUtils.EMPTY
				: model.getF2_Pr_Account_No().trim();
		this.f37_Retriev_Ref_No = StringUtils.isEmpty(model.getF37_Retriev_Ref_No()) ? StringUtils.EMPTY
				: model.getF37_Retriev_Ref_No().trim();
		this.f38_Auth_Ind_Response = StringUtils.isEmpty(model.getF38_Auth_Ind_Response()) ? StringUtils.EMPTY
				: model.getF38_Auth_Ind_Response().trim();
		this.f3_Process_Code = StringUtils.isEmpty(model.getF3_Process_Code()) ? StringUtils.EMPTY
				: model.getF3_Process_Code().trim();
		this.f41_Card_Acpt_Ter_Ind = StringUtils.isEmpty(model.getF41_Card_Acpt_Ter_Ind()) ? StringUtils.EMPTY
				: model.getF41_Card_Acpt_Ter_Ind().trim();
		this.f49_Currency_Code = StringUtils.isEmpty(model.getF49_Currency_Code()) ? StringUtils.EMPTY
				: model.getF49_Currency_Code().trim();
		this.f4_Trans_Amount = StringUtils.isEmpty(model.getF4_Trans_Amount()) ? StringUtils.EMPTY
				: model.getF4_Trans_Amount().trim();
		this.f50_Settlement_Curr_Code = StringUtils.isEmpty(model.getF50_Settlement_Curr_Code()) ? StringUtils.EMPTY
				: model.getF50_Settlement_Curr_Code().trim();
		this.f51_Card_Billing_Curr_Code = StringUtils.isEmpty(model.getF51_Card_Billing_Curr_Code()) ? StringUtils.EMPTY
				: model.getF51_Card_Billing_Curr_Code().trim();
		this.f5_Settement_Amount = StringUtils.isEmpty(model.getF5_Settement_Amount()) ? StringUtils.EMPTY
				: model.getF5_Settement_Amount().trim();
		this.f6_Billing_Amount = StringUtils.isEmpty(model.getF6_Billing_Amount()) ? StringUtils.EMPTY
				: model.getF6_Billing_Amount().trim();
		this.f9_Settlement_Ex_Rate = StringUtils.isEmpty(model.getF9_Settlement_Ex_Rate()) ? StringUtils.EMPTY
				: model.getF9_Settlement_Ex_Rate().trim();

		this.iRFACQBNB_ACQ_Fee = StringUtils.isEmpty(model.getIRFACQBNB_ACQ_Fee()) ? StringUtils.EMPTY
				: model.getIRFACQBNB_ACQ_Fee().trim();
		this.iRFACQISS_ACQ_Fee = StringUtils.isEmpty(model.getIRFACQISS_ACQ_Fee()) ? StringUtils.EMPTY
				: model.getIRFACQISS_ACQ_Fee().trim();
		this.iRFBNBACQ_BNB_Fee = StringUtils.isEmpty(model.getIRFBNBACQ_BNB_Fee()) ? StringUtils.EMPTY
				: model.getIRFBNBACQ_BNB_Fee().trim();
		this.iRFBNBISS_BNB_Fee = StringUtils.isEmpty(model.getIRFBNBISS_BNB_Fee()) ? StringUtils.EMPTY
				: model.getIRFBNBISS_BNB_Fee().trim();
		this.iRFISSACQ_Iss_Fee = StringUtils.isEmpty(model.getIRFISSACQ_Iss_Fee()) ? StringUtils.EMPTY
				: model.getIRFISSACQ_Iss_Fee().trim();
		this.iRFISSBNB_Iss_Fee = StringUtils.isEmpty(model.getIRFISSBNB_Iss_Fee()) ? StringUtils.EMPTY
				: model.getIRFISSBNB_Iss_Fee().trim();
		this.iSS_Iss_Ind_Code = StringUtils.isEmpty(model.getISS_Iss_Ind_Code()) ? StringUtils.EMPTY
				: model.getISS_Iss_Ind_Code().trim();
		this.mID_Card_Acpt_Ind_Code = StringUtils.isEmpty(model.getMID_Card_Acpt_Ind_Code()) ? StringUtils.EMPTY
				: model.getMID_Card_Acpt_Ind_Code().trim();
		this.mTI_Msg_Type_Id = StringUtils.isEmpty(model.getMTI_Msg_Type_Id()) ? StringUtils.EMPTY
				: model.getMTI_Msg_Type_Id().trim();
		this.rCA_Real_Card_Amount = StringUtils.isEmpty(model.getRCA_Real_Card_Amount()) ? StringUtils.EMPTY
				: model.getRCA_Real_Card_Amount().trim();
		this.rRC_Reconcil_Resp_Code = StringUtils.isEmpty(model.getRRC_Reconcil_Resp_Code()) ? StringUtils.EMPTY
				: model.getRRC_Reconcil_Resp_Code().trim();
		this.rSV1_Reserve_Info_1 = StringUtils.isEmpty(model.getRSV1_Reserve_Info_1()) ? StringUtils.EMPTY
				: model.getRSV1_Reserve_Info_1().trim();
		this.rSV2_Reserve_Info_2 = StringUtils.isEmpty(model.getRSV2_Reserve_Info_2()) ? StringUtils.EMPTY
				: model.getRSV2_Reserve_Info_2().trim();
		this.rSV3_Reseve_Info_3 = StringUtils.isEmpty(model.getRSV3_Reseve_Info_3()) ? StringUtils.EMPTY
				: model.getRSV3_Reseve_Info_3().trim();
		this.rTA_Real_Trans_Amount = StringUtils.isEmpty(model.getRTA_Real_Trans_Amount()) ? StringUtils.EMPTY
				: model.getRTA_Real_Trans_Amount().trim();
		this.sVC_Service_Code = StringUtils.isEmpty(model.getSVC_Service_Code()) ? StringUtils.EMPTY
				: model.getSVC_Service_Code().trim();
		this.sVFACQNP_ACQ_Fee = StringUtils.isEmpty(model.getSVFACQNP_ACQ_Fee()) ? StringUtils.EMPTY
				: model.getSVFACQNP_ACQ_Fee().trim();
		this.sVFBNBNP_BNB_Fee = StringUtils.isEmpty(model.getSVFBNBNP_BNB_Fee()) ? StringUtils.EMPTY
				: model.getSVFBNBNP_BNB_Fee().trim();
		this.sVFISSNP_Iss_Fee = StringUtils.isEmpty(model.getSVFISSNP_Iss_Fee()) ? StringUtils.EMPTY
				: model.getSVFISSNP_Iss_Fee().trim();
		this.tCC_Trans_Channel_Code = StringUtils.isEmpty(model.getTCC_Trans_Channel_Code()) ? StringUtils.EMPTY
				: model.getTCC_Trans_Channel_Code().trim();
		this.tRN_Trans_Ref_No = StringUtils.isEmpty(model.getTRN_Trans_Ref_No()) ? StringUtils.EMPTY
				: model.getTRN_Trans_Ref_No().trim();
		this.cSR_CheckSum = StringUtils.isEmpty(model.getCSR_CheckSum()) ? StringUtils.EMPTY
				: model.getCSR_CheckSum().trim();
	}
}
