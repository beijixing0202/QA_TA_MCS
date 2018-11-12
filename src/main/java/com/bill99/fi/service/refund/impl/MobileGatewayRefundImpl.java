package com.bill99.fi.service.refund.impl;

import java.util.Map;

import org.testng.Reporter;

import com.bill99.fi.access.http.DoSubmit;
import com.bill99.fi.service.refund.MobileGatewayRefund;


public class MobileGatewayRefundImpl implements MobileGatewayRefund {
	private DoSubmit doSubmit;


	public DoSubmit getDoSubmit() {
		return doSubmit;
	}

	public void setDoSubmit(DoSubmit doSubmit) {
		this.doSubmit = doSubmit;
	}

	public boolean refundRequest(Map<String, String> data) {
		return doSubmit.mobilegatewayApiRefundSubmit(data);
	}

	public void refundRequestAndRisk(Map<String, String> data) {
		if (doSubmit.mobilegatewayApiRefundSubmit(data)) {
			Reporter.log("退款申请成功！", true);
			//Reporter.log("退款过风控处理！");
			//doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("member"), 0);

		} else {
			Reporter.log("退款申请失败！", false);
		}

	}

	public boolean refundRequestAndRiskAndFinance(Map<String, String> data) {
		if (doSubmit.mobilegatewayApiRefundSubmit(data)) {
			Reporter.log("退款申请成功！", true);
			//Reporter.log("退款过风控，过财务处理！");
			//doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("member"), 1);
            return true;
		} else {
			Reporter.log("退款申请失败！", false);
			return false;
		}

	
	}

	

}
