package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpQueryOrderService;

public class HttpQueryOrderServiceImpl implements HttpQueryOrderService {

	private MgwHttpSubmit mgwhttpsubmit;
	private String url;

	public void seturl(String url) {
		this.url = url;
	}
	
	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}


	@Override
	public String queryOrderContent(MgwItem mgwItem) {
		StringBuffer arraymsgcontent = new StringBuffer();
		arraymsgcontent.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
		.append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version>")
		.append("<QryInsuranceOrderContent><externalRefNumber>")
		.append(mgwItem.getExternalRefNumber())
		.append("</externalRefNumber><orderType>")
		.append(mgwItem.getOrderType())
		.append("</orderType><merchantId>")
		.append(mgwItem.getMerchantId())
		.append("</merchantId><txnType>")
		.append(mgwItem.getWithPayTxnType())
		.append("</txnType><swipeAmt>")
		.append(mgwItem.getSwipeAmt())
		.append("</swipeAmt><pointAmt>")
		.append(mgwItem.getWithPayPointAmt())
		.append("</pointAmt>");
		
		if(StringUtils.hasLength(mgwItem.getMembercode())){
			arraymsgcontent
			.append("<memberCode>")
			.append(mgwItem.getMembercode())
			.append("</memberCode>");
		}
		if(StringUtils.hasLength(mgwItem.getCardHolderName())){
			arraymsgcontent
			.append("<name>")
			.append(mgwItem.getCardHolderName())
			.append("</name>");
		}
		if(StringUtils.hasLength(mgwItem.getCardHolderId())){
			arraymsgcontent
			.append("<cHid>")
			.append(mgwItem.getCardHolderId())
			.append("</cHid>");
		}
		if(StringUtils.hasLength(mgwItem.getPhone())){
			arraymsgcontent
			.append("<phone>")
			.append(mgwItem.getPhone())
			.append("</phone>");
		}
		if(StringUtils.hasLength(mgwItem.getStatus())){
			arraymsgcontent
			.append("<status>")
			.append(mgwItem.getStatus())
			.append("</status>");
		}
		arraymsgcontent.append("<ext1></ext1><ext2></ext2><ext3></ext3><ext4></ext4><ext5></ext5>");
		arraymsgcontent.append("</QryInsuranceOrderContent></MasMessage>");


		String reposeString ="";
		try {
		reposeString = mgwhttpsubmit.post(arraymsgcontent.toString(), url);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}


		return reposeString;
	}

}
