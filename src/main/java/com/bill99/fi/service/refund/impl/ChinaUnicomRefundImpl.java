package com.bill99.fi.service.refund.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import shelper.iffixture.HttpFixture;

import com.bill99.fi.service.refund.ChinaUnicomRefund;

public class ChinaUnicomRefundImpl implements ChinaUnicomRefund{



	public void refundrequest(HttpFixture query,Map<String, String> data) {
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl("http://192.168.64.51:8997/ChinaUnicomClicent/refund.jsp");
		query.addRequestBody("&Currency="+data.get("Currency")+"&key="+data.get("key")+"&MerchantsID="+data.get("MerchantsID")
				+"&PartnerID="+data.get("PartnerID")+"&PayOrderID="+data.get("PayOrderID")+"&PayProviderOrder="+data.get("PayOrderID")
				+"&RefundFee="+data.get("orderAmount")+"&RefundTime="+data.get("RefundTime")+"&ServiceType=Refund&subAddress="
				+data.get("subAddress")+"&TimeStamp="+data.get("RefundTime"));
		query.Post();
		
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/unionproxy/recvUnicomRfdAction.htm");
		try {
			query.addRequestBody("PartnerID="+data.get("PartnerID")+"&PayReqObj="+URLEncoder.encode(data.get("PayReqObj") , "ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.Post();
		System.out.println(query.getResponseBody());
		
	}

}
