package com.bill99.fo.common.dto;

public class DirectedPaymentData {
	/**
	 * 定向付款页面数据
	 * 
	 * @param memberAcctCode
	 *            付款账户(人民币账户)
	 * @param bankAcctId
	 *            银行账号
	 * @param cash
	 *            支付的现金
	 * @param remark
	 *            附言
	 * @param tel
	 *            手机号码
	 */
	private String memberAcctCode;
	private String bankAcctId;
	private String bankAcctIdSec;
	private String cash;
	private String memo;
	private String verifyCode;
	private String tel;

	public String getBankAcctIdSec() {
		return bankAcctIdSec;
	}

	public void setBankAcctIdSec(String bankAcctIdSec) {
		this.bankAcctIdSec = bankAcctIdSec;
	}

	public String getMemberAcctCode() {
		return memberAcctCode;
	}

	public void setMemberAcctCode(String memberAcctCode) {
		this.memberAcctCode = memberAcctCode;
	}

	public String getBankAcctId() {
		return bankAcctId;
	}

	public void setBankAcctId(String bankAcctId) {
		this.bankAcctId = bankAcctId;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
