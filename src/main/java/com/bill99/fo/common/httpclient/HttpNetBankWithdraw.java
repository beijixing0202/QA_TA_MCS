package com.bill99.fo.common.httpclient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.bill99.fo.orm.SeashellDB;

import shelper.iffixture.HttpFixture;

public class HttpNetBankWithdraw {
	
	@Autowired
	private SeashellDB seashellDB;
		
	public HttpFixture netBankWithdraw(HttpFixture hfFixture){
		
		//HttpFixture hfFixture = new HttpFixture();
		
//		try{
			//开始登陆系统
			hfFixture.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
			hfFixture.addParamValue("method", "login");
			hfFixture.addParamValue("userName", "qatest_nj");
			hfFixture.addParamValue("password", "99bill99");
			hfFixture.addParamValue("tokenPWD", "");
			hfFixture.setUrl("http://fscposs.99bill.com/fscposs/loginProcess.htm");
			hfFixture.Post();

//			String  JSESSIONID ="";
//			Pattern p = Pattern.compile("(?<=JSESSIONID=).+?(?=;)"); 
//			Matcher m = p.matcher(hfFixture.getResponseheaders());
//			if (m.find()) {		
//				JSESSIONID = m.group();
//			}
//			else{
//				return false;
//			}
//
//			String T99BILLCOM="";
//			p = Pattern.compile("(?<=T99BILLCOM=).+?(?=;)");; 
//			m = p.matcher(hfFixture.getResponseheaders());
//			if (m.find()) {		
//				T99BILLCOM=  m.group();						
//			}
//			else{
//				return false;
//			}
			
			String orderSeqId = "71188653";
			String dealCode = "108";
			
			String dealId = seashellDB.qryDealIdFrmDeal(orderSeqId, dealCode);
			
			//登陆成功后
			hfFixture.nextRequest();
			hfFixture.setUrl("http://fscposs.99bill.com/fscposs/fscposs/main.htm");
			hfFixture.Get();
			return hfFixture;
			

//			int response = hfFixture.getStatus();
//			System.out.println("response:"+response);
			
//		}catch(Exception ex){
//			ex.printStackTrace();
//			 return false;
//		}
		
		
		
	}
	
	//手工出批次
	public HttpFixture handBatch(HttpFixture htf,String dealid,String orderSeqId,String withdrawWorkOrderId,String amount,String membercode,String bankNameId){
		
		//手工出批次--点击链接
//		htf.nextRequest();
//		htf.setUrl("http://fscposs.99bill.com/fscposs/handbatch/handBatchQuery.htm");
//		htf.Get();
		
		//确认生成批次
		htf.nextRequest();
		htf.setUrl("http://fscposs.99bill.com/fscposs/handbatch/handBatch.htm?method=massRmPassedPaymentConfirm");
		htf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		htf.addParamValue("autoSend", "0");
		htf.addParamValue("autoTransform", "0");
		htf.addParamValue("batchidType", "0");
		htf.addParamValue("batchNameInput", "新华上海");
		htf.addParamValue("batchNameSelect", "新华上海");
		// select dealid from seashell.deal Where orderseqid = 71188653 and dealcode=108
		//Select WITHDRAWWORKORDERID,AMOUNT,MEMBERCODE,BANKNAMEID,BATCHID From seashell.withdrawworkorder Where orderseqid = 71188653
		htf.addParamValue("code", dealid+"@!@"+orderSeqId+"@!@"+withdrawWorkOrderId+"@!@"+amount+"@!@"+membercode+"@!@"+bankNameId);
		htf.addParamValue("massFlag", "0");
		htf.addParamValue("massPayChecked", "0");
		htf.addParamValue("rmPassPayment", "1");
		htf.addParamValue("timeOffSet", "-1");
		htf.Post();
		
		return htf;

		
	}
	
	public static void main(String[] args) {
		//String orderSeqId = "71180760";
		HttpFixture hfFixture = new HttpFixture();
		HttpNetBankWithdraw netBankWithdraw = new HttpNetBankWithdraw();
		//netBankWithdraw.netBankWithdraw(orderSeqId);
		netBankWithdraw.netBankWithdraw(hfFixture);
	}
	
	@Test
	public void test(){
		HttpFixture hfFixture = new HttpFixture();
		HttpNetBankWithdraw netBankWithdraw = new HttpNetBankWithdraw();
		netBankWithdraw.netBankWithdraw(hfFixture);		
	}

}
