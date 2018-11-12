package com.bill99.cps.common.dto;

import java.io.Serializable;

public class TxnDBInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String id_txn;
	protected String id_txn_ctrl;
	protected String txn_flg;
	protected String resp_code;
	protected String txn_type;
	protected String ext_trace_no;


	public String getIdTxn() {
		return id_txn;
	}
	public void setIdTxn(String id_txn) {
		this.id_txn = id_txn;
	}
	public String getIdTxnCtrl() {
		return id_txn_ctrl;
	}
	public void setIdTxnCtrl(String id_txn_ctrl) {
		this.id_txn_ctrl = id_txn_ctrl;
	}
	public String getTxnFlg() {
		return txn_flg;
	}
	public void setTxnFlag(String txn_flg) {
		this.txn_flg = txn_flg;
	}
	public String getRespCode() {
		return resp_code;
	}
	public void setRespCode(String resp_code) {
		this.resp_code = resp_code;
	}
	public String getTxnType() {
		return txn_type;
	}
	public void setTxnType(String txn_type) {
		this.txn_type = txn_type;
	}
	public String getExtTraceNo() {
		return ext_trace_no;
	}
	public void setExtTraceNo(String ext_trace_no) {
		this.ext_trace_no = ext_trace_no;
	}



}
