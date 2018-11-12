package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpAcceptCapacityQueryServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String acceptcapacityqueryurl;
	
	

	public void setAcceptcapacityqueryurl(String acceptcapacityqueryurl) {
		this.acceptcapacityqueryurl = acceptcapacityqueryurl;
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
		.append("</version><AcceptCapacityContent>");
		if(StringUtils.hasLength(mgItem.getMerchantId())){
			message.append("<merchantId>")
			.append(mgItem.getMerchantId())
			.append("</merchantId>");
		}
		if(StringUtils.hasLength(mgItem.getTxnType())){
			message.append("<txnType>")
			.append(mgItem.getTxnType())
			.append("</txnType>");
		}
		if(StringUtils.hasLength(mgItem.getProdCode())){
			message.append("<prodCode>")
			.append(mgItem.getProdCode())
			.append("</prodCode>");
		}
//		if(StringUtils.hasLength(mgItem.getCardType())){
			message.append("<cardType>")
			.append(mgItem.getCardType())
			.append("</cardType>");
//		}
		message.append("</AcceptCapacityContent></MasMessage>");
		String respString = "";
		try {
			respString	= mgwhttpsubmit.post(message.toString(), acceptcapacityqueryurl);
			System.out.println(respString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return respString;
	}

}
