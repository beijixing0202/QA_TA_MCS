package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpPciDelServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String pcidelurl;
	
	

	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}



	public void setPcidelurl(String pcidelurl) {
		this.pcidelurl = pcidelurl;
	}



	@Override
	public String cnpContent(MgwItem mgItem) {
		StringBuffer message = new StringBuffer();
		message.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
		.append("<version>")
		.append(mgItem.getVersion())
		.append("</version><PciDeleteContent>");
		if(StringUtils.hasLength(mgItem.getCustomerId())){
			message.append("<customerId>")
			.append(mgItem.getCustomerId())
			.append("</customerId>");
		}
		
		if(StringUtils.hasLength(mgItem.getMerchantId())){
			message.append("<merchantId>")
			.append(mgItem.getMerchantId())
			.append("</merchantId>");
		}
		if(StringUtils.hasLength(mgItem.getPan())){
			message.append("<pan>")
			.append(mgItem.getPan())
			.append("</pan>");
		}
		if(StringUtils.hasLength(mgItem.getBankId())){
			message.append("<bankId>")
			.append(mgItem.getBankId())
			.append("</bankId>");
		}
		if(StringUtils.hasLength(mgItem.getStorablePan())){
			message.append("<storablePan>")
			.append(mgItem.getStorablePan())
			.append("</storablePan>");
		}
		message.append("</PciDeleteContent></MasMessage>");
		String respstring = "";
		try {
			respstring = mgwhttpsubmit.post(message.toString(), pcidelurl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return respstring;
	}

}
