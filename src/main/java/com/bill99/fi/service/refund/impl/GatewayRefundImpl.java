package com.bill99.fi.service.refund.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

import com.bill99.fi.access.http.DoSubmit;
import com.bill99.fi.common.utils.MD5Util;
import com.bill99.fi.service.refund.GatewayRefund;

public class GatewayRefundImpl implements GatewayRefund {

	private DoSubmit doSubmit;


	public DoSubmit getDoSubmit() {
		return doSubmit;
	}

	public void setDoSubmit(DoSubmit doSubmit) {
		this.doSubmit = doSubmit;
	}

	public boolean refundRequest(Map<String, String> data) {
		return doSubmit.gatewayApiRefundSubmit(data);
	}

	public void refundRequestAndRisk(Map<String, String> data) {
		if (doSubmit.gatewayApiRefundSubmit(data)) {
			Reporter.log("退款申请成功！", true);
			Reporter.log("退款过风控处理！");
			doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("member"), 0);

		} else {
			Reporter.log("退款申请失败！", false);
		}

	}

	public void refundRequestAndRiskAndFinance(Map<String, String> data) {
		if (doSubmit.gatewayApiRefundSubmit(data)) {
			Reporter.log("退款申请成功！", true);
			Reporter.log("退款过风控，过财务处理！");
			doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("member"), 1);

		} else {
			Reporter.log("退款申请失败！", false);
		}

	}


	public void entrustRefund(HttpFixture query, Map<String, String> data) {
		data.put("postdate",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		data.put("txOrder", String.valueOf(new Random().nextInt()));
		data.put("orderid", data.get("orderId"));
		
		query.setUrl("http://192.168.64.51/qatest/ReturnApi_V3/ReturnApi.php");
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");//https://www.99bill.com/webapp/receiveDrawbackAction.do
		query.addRequestBody("actionMthd=post&actionUrl=https://www.99bill.com/webapp/receiveDrawbackAction.do&amount="
				+data.get("ref_amount")+"&command_type="+data.get("command_type")+"&merchant_id="+data.get("ref_merchant_id")
				+"&merchant_key="+data.get("ref_merchant_key")+"&orderid="+data.get("orderId")+"&payeeidsrc="
				+data.get("payeeidsrc")+"&payby99bill=&postdate="+data.get("postdate")+"&txOrder="+
				data.get("txOrder")+"&version="+data.get("ref_version"));
		query.Post();	
		//mac
		String mac = MD5Util.MD5("merchant_id=" + data.get("ref_merchant_id") + "version=bill_drawback_api_3command_type=001orderid="
		+ data.get("orderId") + "amount="+data.get("ref_amount")+"postdate=" + data.get("postdate")+ "txOrder=" + data.get("txOrder")+
		"merchant_key=" + data.get("ref_merchant_key") +"payeeidsrc="+data.get("payeeidsrc"), null);
		
		query.nextRequest();
		//query.setUrl("https://www.99bill.com/webapp/receiveDrawbackAction.do");
		query.setUrl("https://www.99bill.com/gatewayapi/receiveDrawbackAction.do");
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.addRequestBody("amount="+data.get("ref_amount")+"&command_type="+data.get("command_type")+"&mac="
				+mac+"&merchant_id="+data.get("ref_merchant_id")+"&orderid="+
				data.get("orderId")+"&payeeidsrc="+data.get("payeeidsrc")+"&postdate="+data.get("postdate")+"&txOrder="+
						data.get("txOrder")+"&version="+data.get("ref_version"));
		query.Post();
		
		boolean refundResult=query.findStringinResponse("<RESULT>Y</RESULT>");
		//System.err.println(refundResult);
	    Reporter.log("退款失败原因：" + query.getResponseBody().split("<CODE>|</CODE>")[1],refundResult);
	
	}
	public void apiRefund(HttpFixture query, Map<String, String> data) {
		data.put("postdate",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		data.put("txOrder", String.valueOf(new Random().nextInt()));
		data.put("orderid", data.get("orderId"));
		
		query.setUrl("http://192.168.64.51/qatest/ReturnApi/ReturnApi.php");
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");//https://www.99bill.com/webapp/receiveDrawbackAction.do
		query.addRequestBody("actionMthd=post&actionUrl=https://www.99bill.com/webapp/receiveDrawbackAction.do&amount="
				+data.get("ref_amount")+"&command_type="+data.get("command_type")+"&merchant_id="+data.get("ref_merchant_id")
				+"&merchant_key="+data.get("ref_merchant_key")+"&orderid="+data.get("orderId")
				+"&payby99bill=&postdate="+data.get("postdate")+"&txOrder="+data.get("txOrder")+"&version="+data.get("ref_version"));
		query.Post();
		
		//mac
		String mac = MD5Util.MD5("merchant_id=" + data.get("ref_merchant_id") + "version=bill_drawback_api_1command_type=001orderid="
		+ data.get("orderId") + "amount="+data.get("ref_amount")+"postdate=" + data.get("postdate")+ "txOrder=" + data.get("txOrder")+
		"merchant_key=" + data.get("ref_merchant_key"), null);
		
		
		query.nextRequest();
		//query.setUrl("https://www.99bill.com/webapp/receiveDrawbackAction.do");
		query.setUrl("https://www.99bill.com/gatewayapi/receiveDrawbackAction.do");
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		
		query.addRequestBody("amount="+data.get("ref_amount")+"&command_type="+data.get("command_type")+"&mac="
				+mac+"&merchant_id="+data.get("ref_merchant_id")+"&orderid="+
				data.get("orderId")+"&postdate="+data.get("postdate")+"&txOrder="+
						data.get("txOrder")+"&version="+data.get("ref_version"));
		
		query.Post();
		
		boolean refundResult=query.findStringinResponse("<RESULT>Y</RESULT>");
		//System.err.println(refundResult);
	    Reporter.log("退款失败原因：" + query.getResponseBody().split("<CODE>|</CODE>")[1],refundResult);
	
	}

}
