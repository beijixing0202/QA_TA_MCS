package com.bill99.ifs.common.dto;

public class DealDto {
	public String getPaymentServicePkgcode() {
		return paymentServicePkgcode;
	}
	public void setPaymentServicePkgcode(String paymentServicePkgcode) {
		this.paymentServicePkgcode = paymentServicePkgcode;
	}
	public String dealCode;//订单处理方式
	public String paymentServicePkgcode;//支付交易包
	public String dealAmount;//金额
	public String getDealCode() {
		return dealCode;
	}
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
}
