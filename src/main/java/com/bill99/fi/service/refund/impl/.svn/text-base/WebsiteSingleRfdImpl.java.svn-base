package com.bill99.fi.service.refund.impl;

import java.util.Map;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

import com.bill99.fi.service.refund.WebsiteSingleRfd;

public class WebsiteSingleRfdImpl implements WebsiteSingleRfd {
	
	public void singleRfd(HttpFixture loginHf, Map<String, String> data) {
		
		//进入退款页面
		loginHf.nextRequest();
		loginHf.setUrl("https://www.99bill.com/firefund/gatewayrfd/singleQueryRfd.htm");
		loginHf.Get(); 
		System.err.println("data+"+data);
		//查询订单		
		boolean queryResult;
		int i = 0;
		do {
			try {
				Thread.sleep(i * 500);
				// TODO Auto-generated catch block
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("============================"+data);
			loginHf.nextRequest();
			loginHf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
			loginHf.setUrl("https://www.99bill.com/firefund/gatewayrfd/singleQueryRfd.htm?method=query&uniqueKey=");
			loginHf.addParamValue("orderId",data.get("orderId"));
			loginHf.Post();
			
			System.err.println("postRes"+loginHf.getResponseBody());
			queryResult=loginHf.findStringinResponse("<td id=\"rorderId\">"+data.get("orderId")+"</td>");
			
			System.err.println("result~~~~~~~~~~~~~~~~~~"+queryResult);
			i++;
		} while ((!queryResult) && i < 100);
		
		Reporter.log("website单笔退款查询",queryResult);
		
		//退款申请
		loginHf.nextRequest();
		loginHf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		loginHf.setUrl("https://www.99bill.com/firefund/gatewayrfd/singleQueryRfd.htm?method=refund");
		loginHf.addParamValue("orderId",data.get("orderId"));
		//金额为分，需转成元
		data.put("orderAmount",String.valueOf(Integer.valueOf(data.get("orderAmount"))/100));
		loginHf.addParamValue("rfdAmount",data.get("orderAmount"));
		loginHf.Post();
		System.out.println(loginHf.getResponseBody());
		boolean refundResult=loginHf.findStringinResponse("success");
		Reporter.log("website单笔退款申请",refundResult);
		

		
	}


}
