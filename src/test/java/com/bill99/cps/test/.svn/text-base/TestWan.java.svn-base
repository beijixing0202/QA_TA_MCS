package com.bill99.cps.test;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;
//import org.testng.Reporter;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;
import com.bill99.cps.service.HttpQueryOrderService;
//import com.bill99.cps.service.HttpQueryOrderService;
import com.bill99.qa.framework.testcase.BaseTestCase;

public class TestWan extends BaseTestCase{
	
//	@Autowired
//	@Qualifier("httpQueryOrderService")
	@Resource
	public HttpQueryOrderService httpQueryOrderService;
	private  String ts;
	
	@Test
	public void testWan() {
	
//	MgwItem mgwItem = new MgwItem(); 
//	mgwItem.setExternalRefNumber("1498918660021");
//	mgwItem.setOrderType("01");
//	mgwItem.setMerchantId("812025045110007");
//	mgwItem.setWithPayTxnType("ADD");
//	mgwItem.setSwipeAmt("100");
//	mgwItem.setWithPayPointAmt("0.01");
//	mgwItem.setStatus("N");
//	
//	System.out.println(mgwItem.getExternalRefNumber()+mgwItem.getOrderType()+mgwItem.getMerchantId()
//			+mgwItem.getWithPayTxnType()+mgwItem.getSwipeAmt()+mgwItem.getWithPayPointAmt()+mgwItem.getStatus());
//	
//	String respose = httpQueryOrderService.queryOrderContent(mgwItem);
//
//	Reporter.log("respose=" + respose);
	
	ts = sssss();
	System.out.println(ts);
	
	}
	
	public String sssss(){
		MgwItem mgwItem = new MgwItem(); 
		mgwItem.setExternalRefNumber("1498918660021");
		mgwItem.setOrderType("01");
		mgwItem.setMerchantId("812025045110007");
		mgwItem.setWithPayTxnType("ADD");
		mgwItem.setSwipeAmt("100");
		mgwItem.setWithPayPointAmt("0.01");
		mgwItem.setStatus("N");
		
		System.out.println(mgwItem.getExternalRefNumber()+mgwItem.getOrderType()+mgwItem.getMerchantId()
				+mgwItem.getWithPayTxnType()+mgwItem.getSwipeAmt()+mgwItem.getWithPayPointAmt()+mgwItem.getStatus());
		
		String respose = httpQueryOrderService.queryOrderContent(mgwItem);
		return respose;
	}
	
}
