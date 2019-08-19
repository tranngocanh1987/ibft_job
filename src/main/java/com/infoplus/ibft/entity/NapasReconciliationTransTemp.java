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

import lombok.Data;

@Entity
@Data
@Table(name = "napas_reconciliation_trans_temp")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "procCompareNapasTrans", procedureName = "proc_compare_napas_trans", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_transDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_countMissedTrans", type = Integer.class)
		})
})
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
}
