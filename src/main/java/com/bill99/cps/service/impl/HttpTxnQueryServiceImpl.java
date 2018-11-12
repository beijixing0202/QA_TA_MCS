package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpTxnQueryServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String txnqueryurl;
	
	

	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}



	public void setTxnqueryurl(String txnqueryurl) {
		this.txnqueryurl = txnqueryurl;
	}



	@Override
	public String cnpContent(MgwItem mgItem) {
		
		StringBuffer message = new StringBuffer();
		message.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
		.append("<version>")
		.append(mgItem.getVersion())
		.append("</version><QryTxnMsgContent>");
		if(StringUtils.hasLength(mgItem.getExternalRefNumber())){
			message.append("<externalRefNumber>")
			.append(mgItem.getExternalRefNumber())
			.append("</externalRefNumber>");
		}
		if(StringUtils.hasLength(mgItem.getRefNumber())){
			message.append("<refNumber>")
			.append(mgItem.getRefNumber())
			.append("</refNumber>");
		}
		if(StringUtils.hasLength(mgItem.getTxnType())){
			message.append("<txnType>")
			.append(mgItem.getTxnType())
			.append("</txnType>");
		}
		if(StringUtils.hasLength(mgItem.getMerchantId())){
			message.append("<merchantId>")
			.append(mgItem.getMerchantId())
			.append("</merchantId>");
		}
		if(StringUtils.hasLength(mgItem.getSettleMerchantId())){
			message.append("<settleMerchantId>")
			.append(mgItem.getMerchantId())
			.append("</getSettleMerchantId>");
		}
		if(StringUtils.hasLength(mgItem.getTerminalId())){
			message.append("<terminalId>")
			.append(mgItem.getTerminalId())
			.append("</terminalId>");
		}
		if(StringUtils.hasLength(mgItem.getTxnStatus())){
			message.append("<txnStatus>")
			.append(mgItem.getTxnStatus())
			.append("</txnStatus>");
		}
		message.append("</QryTxnMsgContent></MasMessage>");
		String respString = "";
		try {
			respString	= mgwhttpsubmit.post(message.toString(), txnqueryurl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return respString;
	}

}
