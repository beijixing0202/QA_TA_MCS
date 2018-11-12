package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpGetDynNumServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String getDynNumUrl;
	

	public void setGetDynNumUrl(String getDynNumUrl) {
		this.getDynNumUrl = getDynNumUrl;
	}
	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}

	// 获取手机动态码http 报文
	@Override
	public String cnpContent(MgwItem mgItem) {
		StringBuffer message = new StringBuffer();
		message
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
		.append("<version>")
		.append(mgItem.getVersion())
		.append("</version><GetDynNumContent>");
		if(StringUtils.hasLength(mgItem.getCustomerId())){
			message.append("<customerId>")
			.append(mgItem.getCustomerId())
			.append("</customerId>");
		}
		
		if(StringUtils.hasLength(mgItem.getMerchantId())){
			message.append("<merchantId>" )
			.append(mgItem.getMerchantId())
			.append("</merchantId>");
		}
		if(StringUtils.hasLength(mgItem.getExternalRefNumber())){
			message.append("<externalRefNumber>")
			.append(mgItem.getExternalRefNumber())
			.append("</externalRefNumber>");
		}
		if(StringUtils.hasLength(mgItem.getCardHolderName())){
			message.append("<cardHolderName>" )
			.append(mgItem.getCardHolderName())
			.append("</cardHolderName>");
		}
		if(StringUtils.hasLength(mgItem.getIdType())){
			message.append("<idType>" )
			.append(mgItem.getIdType())
			.append("</idType>");
		}
		if(StringUtils.hasLength(mgItem.getCardHolderId())){
			message.append("<cardHolderId>" )
			.append(mgItem.getCardHolderId())
			.append("</cardHolderId>");
		}
		if(StringUtils.hasLength(mgItem.getPan())){
			message.append("<pan>" )
			.append(mgItem.getPan())
			.append("</pan>");
		}
		if(StringUtils.hasLength(mgItem.getStorablePan())){
			message.append("<storablePan>" )
			.append(mgItem.getStorablePan())
			.append("</storablePan>");
		}
		if(StringUtils.hasLength(mgItem.getBankId())){
			message.append("<bankId>" )
			.append(mgItem.getBankId())
			.append("</bankId>");
		}
		if(StringUtils.hasLength(mgItem.getExpiredDate())){
			message.append("<expiredDate>")
			.append(mgItem.getExpiredDate())
			.append("</expiredDate>");
		}
		if(StringUtils.hasLength(mgItem.getPhoneNO())){
			message.append("<phoneNO>")
			.append(mgItem.getPhoneNO())
			.append("</phoneNO>");
		}
		if(StringUtils.hasLength(mgItem.getCvv2())){
			message.append("<cvv2>")
			.append(mgItem.getCvv2())
			.append("</cvv2>");
		}
		if(StringUtils.hasLength(mgItem.getAmount())){
			message.append("<amount>")
			.append(mgItem.getAmount())
			.append("</amount>");
		}
		message.append("</GetDynNumContent></MasMessage>");
		 String reposeString ="";
		try {
			reposeString =	mgwhttpsubmit.post(message.toString(), getDynNumUrl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return reposeString;
	}

}
