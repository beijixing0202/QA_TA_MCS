package com.bill99.ifs.common.dto;

public class PEDto {
	public String dealCode;//订单处理方式
	public String paymentServicePkgcode;//支付交易包
	public String orderCode;//订单类型
	public String drAcct;//DR科目
	public String crAcct;//CR科
	public String getDealCode() {
		return dealCode;
	}
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	public String getPaymentServicePkgcode() {
		return paymentServicePkgcode;
	}
	public void setPaymentServicePkgcode(String paymentServicePkgcode) {
		this.paymentServicePkgcode = paymentServicePkgcode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getDrAcct() {
		return drAcct;
	}
	public void setDrAcct(String drAcct) {
		this.drAcct = drAcct;
	}
	public String getCrAcct() {
		return crAcct;
	}
	public void setCrAcct(String crAcct) {
		this.crAcct = crAcct;
	}
}
