package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpCardInfoQueryServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String cardinfoqueryurl;
	
	

	public void setCardinfoqueryurl(String cardinfoqueryurl) {
		this.cardinfoqueryurl = cardinfoqueryurl;
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
		.append("</version><QryCardContent>");
		if(StringUtils.hasLength(mgItem.getCardNo())){
			message.append("<cardNo>")
			.append(mgItem.getCardNo())
			.append("</cardNo>");
		}
		if(StringUtils.hasLength(mgItem.getTxnType())){
			message.append("<txnType>")
			.append(mgItem.getTxnType())
			.append("</txnType>");
		}
		message.append("</QryCardContent></MasMessage>");
		String respString = "";
		try {
			respString	= mgwhttpsubmit.post(message.toString(), cardinfoqueryurl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return respString;
	}

}
