package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;
import org.testng.Reporter;

//import shelper.iffixture.HttpFixture;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpIndependAuthentVerifyServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String indAuthVerificationUrl;

	public void setIndAuthVerificationUrl(String indAuthVerificationUrl) {
		this.indAuthVerificationUrl = indAuthVerificationUrl;
	}

	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}

	@Override
	public String cnpContent(MgwItem mgItem) {
		
		StringBuffer message = new StringBuffer();
		message.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
		.append("<version>")
		.append(mgItem.getVersion())
		.append("</version><indAuthDynVerifyContent>");
		
		if(StringUtils.hasLength(mgItem.getMerchantId())){
			message.append("<merchantId>")
			.append(mgItem.getMerchantId())
			.append("</merchantId>");
		}
		if(StringUtils.hasLength(mgItem.getTerminalId())){
			message.append("<terminalId>")
			.append(mgItem.getTerminalId())
			.append("</terminalId>");
		}
		if(StringUtils.hasLength(mgItem.getCustomerId())){
			message.append("<customerId>")
			.append(mgItem.getCustomerId())
			.append("</customerId>");
		} 
		if(StringUtils.hasLength(mgItem.getExternalRefNumber())){
			message.append("<externalRefNumber>")
			.append(mgItem.getExternalRefNumber())
			.append("</externalRefNumber>");
		}
		if(StringUtils.hasLength(mgItem.getPan())){
			message.append("<pan>")
			.append(mgItem.getPan())
			.append("</pan><storablePan></storablePan>");
		}
		if(StringUtils.hasLength(mgItem.getPhoneNO())){
			message.append("<phoneNO>")
			.append(mgItem.getPhoneNO())
			.append("</phoneNO>");
		} 
		if(StringUtils.hasLength(mgItem.getValidCode())){
			message.append("<validCode>")
			.append(mgItem.getValidCode())
			.append("</validCode>");
		}
		if(StringUtils.hasLength(mgItem.getToken())){
			message.append("<token>")
			.append(mgItem.getToken())
			.append("</token>");
		}
		
		message.append("</indAuthDynVerifyContent></MasMessage>");
		String respString = "";
		try {
			 
			Reporter.log("===request:  ==>"+message);
			respString = mgwhttpsubmit.post(message.toString(), indAuthVerificationUrl);
		    Reporter.log("===response:  ==>"+message); 
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return respString;
	}

}
