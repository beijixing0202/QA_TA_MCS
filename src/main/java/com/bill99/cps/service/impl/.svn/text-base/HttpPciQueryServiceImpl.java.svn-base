package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpPciQueryServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String pciqueryurl;
	
	

	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}



	public void setPciqueryurl(String pciqueryurl) {
		this.pciqueryurl = pciqueryurl;
	}



	@Override
	public String cnpContent(MgwItem mgItem) {
		
		StringBuffer message = new StringBuffer();
		message.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
		.append("<version>")
		.append(mgItem.getVersion())
		.append("</version><PciQueryContent>");
		if(StringUtils.hasLength(mgItem.getCardHolderId())){
			message.append("<customerId>")
			.append(mgItem.getCustomerId())
			.append("</customerId>");
		}
		if(StringUtils.hasLength(mgItem.getMerchantId())){
			message.append("<merchantId>")
			.append(mgItem.getMerchantId())
			.append("</merchantId>");
		}
		if(StringUtils.hasLength(mgItem.getStorablePan())){
			message.append("<storablePAN>")
			.append(mgItem.getStorablePan())
			.append("</storablePAN>");
		}
		if(StringUtils.hasLength(mgItem.getBankId())){
			message.append("<bankId>")
			.append(mgItem.getBankId())
			.append("</bankId>");
		}
		message.append("</PciQueryContent></MasMessage>");
		String respString = "";
		try {
			respString	= mgwhttpsubmit.post(message.toString(), pciqueryurl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return respString;
	}

}
