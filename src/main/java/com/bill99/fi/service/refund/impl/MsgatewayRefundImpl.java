package com.bill99.fi.service.refund.impl;

import java.rmi.RemoteException;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.testng.Reporter;

import com.bill99.fi.access.http.DoSubmit;
import com.bill99.fi.service.refund.MsgatewayRefund;
import com.bill99.fi.common.dto.refundHx.*;
import com.bill99.fi.service.refundHx.*;

public class MsgatewayRefundImpl implements MsgatewayRefund{
	
	private DoSubmit doSubmit; 

	public DoSubmit getDoSubmit() {
		return doSubmit;
	}

	public void setDoSubmit(DoSubmit doSubmit) {
		this.doSubmit = doSubmit;
	}

	public boolean refundRequest(Map<String, String> data){
		return doSubmit.msGatewayApiRefundSubmit(data);
	}

	public void refundRequestAndRisk(Map<String, String> data){
		if (doSubmit.msGatewayApiRefundSubmit(data)) {
			Reporter.log("退款申请成功！", true);
			Reporter.log("退款过风控处理！");
			doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("member"), 0);

		} else {
			Reporter.log("退款申请失败！", false);
		}
	}

	public void refundRequestAndRiskAndFinance(Map<String, String> data){
		if (doSubmit.msGatewayApiRefundSubmit(data)) {
			Reporter.log("退款申请成功！", true);
			Reporter.log("退款过风控，过财务处理！");
			//doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("member"), 1);

		} else {
			Reporter.log("退款申请失败！", false);
		}	
	}

	public void hxrefundRequestAndRiskAndFinance(Map<String, String> data){

    RefundResponse r = null;
    String inputCharset = data.get("inputCharset");
	String version = data.get("ref_version");
	String language = data.get("ref_language");
	String signType = data.get("ref_signType");
	String orderId = data.get("orderId");
	String pid = data.get("ref_pid");
	String seqId = data.get("ref_seqId");
	String returnAllAmount = data.get("ref_returnAllAmount");
	String returnTime = data.get("ref_returnTime");
	String ext1 = data.get("ext1");
	String ext2 = data.get("ext2");
	String returnDetail = data.get("ref_returnDetail");
	String signMsg = data.get("ref_signMsg");
	
	  RefundHxServiceLocator a = new RefundHxServiceLocator();
		RefundRequest reques = new RefundRequest();
		reques.setInputCharset(inputCharset);
		reques.setVersion(version);
		reques.setLanguage(language);
		reques.setSignType(signType);
		reques.setOrderId(orderId);
		reques.setPid(pid);
		reques.setSeqId(seqId);
		reques.setReturnAllAmount(returnAllAmount);
		reques.setReturnTime(returnTime);
		reques.setExt1(ext1);
		reques.setExt2(ext2);
		reques.setReturnDetail(returnDetail);
		reques.setSignMsg(signMsg);
		
		try {
			r = a.getserviceRefund().refund(reques);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("errcode等于空",(r.getErrCode()).equals(""));
		System.out.println("errcode="+r.getErrCode());
	    Reporter.log("===退款申请成功===", r.getResult().equals("10"));	
	    doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("member"), 1);
	    Reporter.log("退款过风控，过财务处理！");
	}
}
