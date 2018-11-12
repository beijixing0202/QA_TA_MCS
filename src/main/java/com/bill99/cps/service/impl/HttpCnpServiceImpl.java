package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;
//import com.bill99.cps.service.InternalService;

public class HttpCnpServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String url = "";
	private String pururl;
	private String inpurl;
	private String preurl;
	private String cfmurl;
	private String vtxurl;
	private String rfdurl;




	public void setPururl(String pururl) {
		this.pururl = pururl;
	}


	public void setInpurl(String inpurl) {
		this.inpurl = inpurl;
	}


	public void setPreurl(String preurl) {
		this.preurl = preurl;
	}


	public void setCfmurl(String cfmurl) {
		this.cfmurl = cfmurl;
	}


	public void setVtxurl(String vtxurl) {
		this.vtxurl = vtxurl;
	}


	public void setRfdurl(String rfdurl) {
		this.rfdurl = rfdurl;
	}


	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}

   // CNP http 报文
	@Override
	public String cnpContent(MgwItem mgItem) {
		StringBuffer arraymsgcontent = new StringBuffer();
		arraymsgcontent.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
		.append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version>")
		.append("<TxnMsgContent><txnType>")
		.append(mgItem.getTxnType())
		.append("</txnType><interactiveStatus>")
		.append(mgItem.getInteractiveStatus())
		.append("</interactiveStatus>")
		.append("<amount>")
		.append(mgItem.getAmount())
		.append("</amount>")
		.append("<merchantId>")
		.append(mgItem.getMerchantId())
		.append("</merchantId>")
		.append("<terminalId>")
		.append(mgItem.getTerminalId())
		.append("</terminalId><entryTime>")
		.append(mgItem.getEntryTime())
		.append("</entryTime><externalRefNumber>")
		.append(mgItem.getExternalRefNumber())
		.append("</externalRefNumber>");
		if(StringUtils.hasLength(mgItem.getCardNo())){
					arraymsgcontent
					.append("<cardNo>")
					.append(mgItem.getCardNo())
					.append("</cardNo>");
				}
		if(StringUtils.hasLength(mgItem.getExpiredDate())){
			arraymsgcontent
			.append("<expiredDate>")
			.append(mgItem.getExpiredDate())
			.append("</expiredDate>");
		}
		if(StringUtils.hasLength(mgItem.getCvv2())){
			arraymsgcontent
			.append("<cvv2>")
			.append(mgItem.getCvv2())
			.append("</cvv2>");
		}
		if(StringUtils.hasLength(mgItem.getTermInMonths())){
			arraymsgcontent
			.append("<termInMonths>")
			.append(mgItem.getTermInMonths())
			.append("</termInMonths>");
		}
		if(StringUtils.hasLength(mgItem.getOrignalTxnType())){
			arraymsgcontent
			.append("<orignalTxnType>")
			.append(mgItem.getOrignalTxnType())
			.append("</orignalTxnType>");
		}
		if(StringUtils.hasLength(mgItem.getSettleMerchantId())){
			arraymsgcontent
			.append("<settleMerchantId>")
			.append(mgItem.getSettleMerchantId())
			.append("</settleMerchantId>");
		}
		if(StringUtils.hasLength(mgItem.getCustomerId())){
			arraymsgcontent
			.append("<customerId>")
			.append(mgItem.getCustomerId())
			.append("</customerId>");
		}
		if(StringUtils.hasLength(mgItem.getRefNumber())){
			arraymsgcontent
			.append("<refNumber>")
			.append(mgItem.getRefNumber())
			.append("</refNumber>");
		}
		if(StringUtils.hasLength(mgItem.getOrigRefNumber())){
			arraymsgcontent
			.append("<origRefNumber>")
			.append(mgItem.getOrigRefNumber())
			.append("</origRefNumber>");
		}
		if(StringUtils.hasLength(mgItem.getTr3Url())){
			arraymsgcontent
			.append("<tr3Url>")
			.append(mgItem.getTr3Url())
			.append("</tr3Url>");
		}
		if(StringUtils.hasLength(mgItem.getPin())){
			arraymsgcontent
			.append("<pin>")
			.append(mgItem.getPin())
			.append("</pin>");
		}
		if(StringUtils.hasLength(mgItem.getSiteType())){
			arraymsgcontent
			.append("<siteType>")
			.append(mgItem.getSiteType())
			.append("</siteType>");
		}
		if(StringUtils.hasLength(mgItem.getSiteID())){
			arraymsgcontent
			.append("<siteID>")
			.append(mgItem.getSiteID())
			.append("</siteID>");
		}
		if(StringUtils.hasLength(mgItem.getIssuerCountry())){
			arraymsgcontent
			.append("<issuerCountry>")
			.append(mgItem.getIssuerCountry())
			.append("</issuerCountry>");
		}
		if(StringUtils.hasLength(mgItem.getSpFlag())){
			arraymsgcontent
			.append("<spFlag>")
			.append(mgItem.getSpFlag())
			.append("</spFlag>");
		}
		if(StringUtils.hasLength(mgItem.getDynPin())){
			arraymsgcontent
			.append("<dynPin>")
			.append(mgItem.getDynPin())
			.append("</dynPin>");
		}
		if(StringUtils.hasLength(mgItem.getSerialNo())){
			arraymsgcontent
			.append("<serialNo>")
			.append(mgItem.getSerialNo())
			.append("</serialNo>");
		}
		if(StringUtils.hasLength(mgItem.getExt())){
			arraymsgcontent
			.append("<ext>")
			.append(mgItem.getExt())
			.append("</ext>");
		}
		if(StringUtils.hasLength(mgItem.getExt1())){
			arraymsgcontent
			.append("<ext1>")
			.append(mgItem.getExt1())
			.append("</ext1>");
		}
		arraymsgcontent.append("<extMap>");
		
		if(StringUtils.hasLength(mgItem.getProdInfo()) && StringUtils.hasLength(mgItem.getPhone())){
			arraymsgcontent
			.append("<extDate><key>phone</key><value>")
			.append(mgItem.getProdInfo())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getProdInfo()) && !StringUtils.hasLength(mgItem.getPhone())){
			arraymsgcontent
			.append("<extDate><key>prodInfo</key><value>")
			.append(mgItem.getProdInfo())
			.append("</value></extDate>");
		}
		if(!StringUtils.hasLength(mgItem.getProdInfo()) && StringUtils.hasLength(mgItem.getPhone())){
			arraymsgcontent
			.append("<extDate><key>phone</key><value>")
			.append(mgItem.getPhone())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getToken())){
			arraymsgcontent
			.append("<extDate><key>token</key><value>")
			.append(mgItem.getToken())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getValidCode())){
			arraymsgcontent
			.append("<extDate><key>validCode</key><value>")
			.append(mgItem.getValidCode())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getSmsValidTime())){
			arraymsgcontent
			.append("<extDate><key>smsValidTime</key><value>")
			.append(mgItem.getSmsValidTime())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getOrderValidTime())){
			arraymsgcontent
			.append("<extDate><key>orderValidTime</key><value>")
			.append(mgItem.getOrderValidTime())
			.append("</value></extDate>");
		}
		
		if(StringUtils.hasLength(mgItem.getSavePciFlag())){
			arraymsgcontent
			.append("<extDate><key>savePciFlag</key><value>")
			.append(mgItem.getSavePciFlag())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getCmdCode())){
			arraymsgcontent
			.append("<extDate><key>cmdCode</key><value>")
			.append(mgItem.getCmdCode())
			.append("</value></extDate>");
		}
		
		if(StringUtils.hasLength(mgItem.getPointAmt())){
			arraymsgcontent
			.append("<extDate><key>pointAmt</key><value>")
			.append(mgItem.getPointAmt())
			.append("</value></extDate>");
		}
		
		if(StringUtils.hasLength(mgItem.getCustomerPointAccount())){
			arraymsgcontent
			.append("<extDate><key>customerPointAccount</key><value>")
			.append(mgItem.getCustomerPointAccount())
			.append("</value></extDate>");
		}
		
		if(StringUtils.hasLength(mgItem.getPayBatch())){
			arraymsgcontent
			.append("<extDate><key>payBatch</key><value>")
			.append(mgItem.getPayBatch())
			.append("</value></extDate>");
		}
		
		if(StringUtils.hasLength(mgItem.getPayBatch())){
			arraymsgcontent
			.append("<extDate><key>payBatch</key><value>")
			.append(mgItem.getPayBatch())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getWithpay())){
			arraymsgcontent
			.append("<extDate><key>withpay</key><value>")
			.append(mgItem.getWithpay())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getWithpayamt())){
			arraymsgcontent
			.append("<extDate><key>withpayamt</key><value>")
			.append(mgItem.getWithpayamt())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getMembercode())){
			arraymsgcontent
			.append("<extDate><key>membercode</key><value>")
			.append(mgItem.getMembercode())
			.append("</value></extDate>");
		}
		if(StringUtils.hasLength(mgItem.getInterestsid())){
			arraymsgcontent
			.append("<extDate><key>interestsid</key><value>")
			.append(mgItem.getInterestsid())
			.append("</value></extDate>");
		}
		if(mgItem.getSpFlag().equalsIgnoreCase("CCT")){
			arraymsgcontent
			.append("<extDate>")
			.append("<key>")
			.append("cct.ext1")
			.append("</key></extDate>")
			.append("<extDate><key>")
			.append("cct.ext2")
			.append("</key></extDate>");
		}
		arraymsgcontent.append("</extMap>");
		if(StringUtils.hasLength(mgItem.getOrgPartyId())){
			arraymsgcontent
			.append("<orgPartyId>")
			.append(mgItem.getOrgPartyId())
			.append("</orgPartyId>");
		}
		
		if(StringUtils.hasLength(mgItem.getAuthorizationCode())){
			arraymsgcontent
			.append("<authorizationCode>")
			.append(mgItem.getAuthorizationCode())
			.append("</authorizationCode>");
		}
		if(StringUtils.hasLength(mgItem.getStorableCardNo())){
			arraymsgcontent
			.append("<storableCardNo>")
			.append(mgItem.getStorableCardNo())
			.append("</storableCardNo>");
		}
		if(StringUtils.hasLength(mgItem.getStorablePan())){
			arraymsgcontent
			.append("<storablePan>")
			.append(mgItem.getStorablePan())
			.append("</storablePan>");
		}
		if(StringUtils.hasLength(mgItem.getCardHolderName())){
			arraymsgcontent
			.append("<cardHolderName>")
			.append(mgItem.getCardHolderName())
			.append("</cardHolderName>");
		}
		if(StringUtils.hasLength(mgItem.getCustomerId())){
			arraymsgcontent
			.append("<customerId>")
			.append(mgItem.getCustomerId())
			.append("</customerId>");
		}
		
		if(StringUtils.hasLength(mgItem.getCardHolderId())){
			arraymsgcontent
			.append("<cardHolderId>")
			.append(mgItem.getCardHolderId())
			.append("</cardHolderId>");
		}
		if(StringUtils.hasLength(mgItem.getIdType())){
			arraymsgcontent
			.append("<idType>")
			.append(mgItem.getIdType())
			.append("</idType>");
		}
		arraymsgcontent.append("<rifleMap/>");	
		arraymsgcontent.append("</TxnMsgContent></MasMessage>");
		
		// 根据 txntype 选择对应的URL
		if ("PUR".equalsIgnoreCase(mgItem.getTxnType())) {
			url = pururl;
		} 
		if ("INP".equalsIgnoreCase(mgItem.getTxnType())) {
			url = inpurl;
		} 
		if ("PRE".equals(mgItem.getTxnType())) {
			url = preurl;
		} 
         
		if ("CFM".equals(mgItem.getTxnType())) {
			url = cfmurl;
		} 
        if ("VTX".equals(mgItem.getTxnType())) {
        	  url = vtxurl;
		}
          
         if ("RFD".equals(mgItem.getTxnType())) {
        	 url = rfdurl;
		}
         
         String reposeString ="";
		try {

			System.out.println("reposeString=" + arraymsgcontent.toString());
			
//			System.out.println(internalmockurl);

//			System.out.println("reposeString=" + arraymsgcontent.toString());

			reposeString = mgwhttpsubmit.post(arraymsgcontent.toString(), url);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return reposeString;
	}

}
