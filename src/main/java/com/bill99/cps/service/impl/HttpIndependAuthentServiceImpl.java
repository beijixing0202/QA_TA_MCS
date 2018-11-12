package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;
import org.testng.Reporter;

//import shelper.iffixture.HttpFixture;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpIndependAuthentServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String indepentAuthUrl;

	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}


	public void setIndepentAuthUrl(String indepentAuthUrl) {
		this.indepentAuthUrl = indepentAuthUrl;
	}


	@Override
	public String cnpContent(MgwItem mgItem) {
		
		StringBuffer message = new StringBuffer();
		message.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
		.append("<version>")
		.append(mgItem.getVersion())
		.append("</version><indAuthContent>");
		
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
			.append("</pan><storablePan></storablePan><cardHolderName></cardHolderName>");
		}
		if(StringUtils.hasLength(mgItem.getCardHolderName())){
			message.append("<cardHolderName>")
			.append(mgItem.getCardHolderName())
			.append("</cardHolderName>");
		}
		if(StringUtils.hasLength(mgItem.getIdType())){
			message.append("<idType>")
			.append(mgItem.getIdType())
			.append("</idType>");
		}
		if(StringUtils.hasLength(mgItem.getCardHolderId())){
			message.append("<cardHolderId>")
			.append(mgItem.getCardHolderId())
			.append("</cardHolderId>");
		}  
		if(StringUtils.hasLength(mgItem.getExpiredDate())){
			message.append("<expiredDate>")
			.append(mgItem.getExpiredDate())
			.append("</expiredDate>");
		}
		if(StringUtils.hasLength(mgItem.getCvv2())){
			message.append("<cvv2>")
			.append(mgItem.getCvv2())
			.append("</cvv2>");
		}
		if(StringUtils.hasLength(mgItem.getPhoneNO())){
			message.append("<phoneNO>")
			.append(mgItem.getPhoneNO())
			.append("</phoneNO>");
		} 
		message.append("</indAuthContent></MasMessage>");
		String respString = "";
		try { 
			Reporter.log("=====>request:  "+message);
			respString= mgwhttpsubmit.post(message.toString(), indepentAuthUrl);
			Reporter.log("=====>response:  "+respString);
			 
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return respString;
	}

}
