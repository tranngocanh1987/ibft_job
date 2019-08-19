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
@Table(name = "napas_trans_compare")
public class NapasTransactionCompare implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "mti_msg_type_id")
	private String mti_msg_type_id;
	@Column(name = "f2_pr_account_no")
	private String f2_pr_account_no;
	@Column(name = "f3_process_code")
	private String f3_process_code;
	@Column(name = "f4_trans_amount")
	private String f4_trans_amount;
	@Column(name = "f5_settement_amount")
	private String f5_settement_amount;
	@Column(name = "f6_billing_amount")
	private String f6_billing_amount;
	@Column(name = "f49_currency_code")
	private String f49_currency_code;
	@Column(name = "f50_settlement_curr_code")
	private String f50_settlement_curr_code;
	@Column(name = "f51_card_billing_curr_code")
	private String f51_card_billing_curr_code;
	@Column(name = "f12_local_trans_time")
	private String f12_local_trans_time;
	@Column(name = "f13_local_trans_date")
	private String f13_local_trans_date;
	@Column(name = "f15_settlement_date")
	private String f15_settlement_date;
	@Column(name = "f18_merchant_cat_code")
	private String f18_merchant_cat_code;
	@Column(name = "f32_acq_ins_code")
	private String f32_acq_ins_code;
	@Column(name = "f41_card_acpt_ter_ind")
	private String f41_card_acpt_ter_ind;
	@Column(name = "f38_auth_ind_response")
	private String f38_auth_ind_response;
	@Column(name = "rrc_reconcil_resp_code")
	private String rrc_reconcil_resp_code;
	@Column(name = "f63_trans_ref_no")
	private String f63_trans_ref_no;
	@Column(name = "status")
	private String status;
	@Column(name = "created_dt")
	private String created_dt;
	@Column(name = "created_by")
	private String created_by;
	@Column(name = "updated_dt")
	private String updated_dt;
	@Column(name = "updated_by")
	private String updated_by;
	@Column(name = "record_status")
	private String record_status;
	@Column(name = "compare_trn_date")
	private String compare_trn_date;

}
