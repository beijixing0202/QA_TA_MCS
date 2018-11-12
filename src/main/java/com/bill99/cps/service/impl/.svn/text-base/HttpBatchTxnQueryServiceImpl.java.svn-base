package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

//import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.util.StringUtils;

import com.bill99.cps.access.http.MgwHttpSubmit;
import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.HttpContentService;

public class HttpBatchTxnQueryServiceImpl implements HttpContentService {
	
	private MgwHttpSubmit mgwhttpsubmit;
	private String batchtxnqueryurl;
	

	public void setMgwhttpsubmit(MgwHttpSubmit mgwhttpsubmit) {
		this.mgwhttpsubmit = mgwhttpsubmit;
	}



	public void setBatchtxnqueryurl(String batchtxnqueryurl) {
		this.batchtxnqueryurl = batchtxnqueryurl;
	}



	@Override
	public String cnpContent(MgwItem mgItem) {
		
		StringBuffer message = new StringBuffer();
		message.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">")
		.append("<version>")
		.append(mgItem.getVersion())
		.append("</version><BatchQryTxnMsgContent>");
		if(StringUtils.hasLength(mgItem.getFirstPageNo())){
			message.append("<firstPageNo>")
			.append(mgItem.getFirstPageNo())
			.append("</firstPageNo>");
		}
		if(StringUtils.hasLength(mgItem.getMaxRec())){
			message.append("<maxRec>")
			.append(mgItem.getMaxRec())
			.append("</maxRec>");
		}
		if(StringUtils.hasLength(mgItem.getTxnType()) && mgItem.getTxnType().contains(",")){
			String txntypelist[] = mgItem.getTxnType().split(",");
			message.append("<TxnTypeList><txnType>")
			.append(txntypelist[0])
			.append("</txnType><txnType>")
			.append(txntypelist[1])
			.append("</txnType></TxnTypeList>");
		}else{
			message.append("<TxnTypeList><txnType>")
			.append(mgItem.getTxnType())
			.append("</txnType></TxnTypeList>");
		}
		/*if(StringUtils.hasLength(mgItem.getTxnType())){
			message.append("<TxnTypeList><txnType>")
			.append(mgItem.getTxnType())
			.append("</txnType></TxnTypeList>");
		}*/
		if(StringUtils.hasLength(mgItem.getTerminalId())){
			message.append("<terminalId>")
			.append(mgItem.getTerminalId())
			.append("</terminalId>");
		}
		if(StringUtils.hasLength(mgItem.getMerchantId())){
			message.append("<merchantId>")
			.append(mgItem.getMerchantId())
			.append("</merchantId>");
		}
		if(StringUtils.hasLength(mgItem.getBeginEntryTime())){
			message.append("<beginEntryTime>")
			.append(mgItem.getBeginEntryTime())
			.append("</beginEntryTime>");
		}
		if(StringUtils.hasLength(mgItem.getEndEntryTime())){
			message.append("<endEntryTime>")
			.append(mgItem.getEndEntryTime())
			.append("</endEntryTime>");
		}
		if(StringUtils.hasLength(mgItem.getTxnStatus()) && mgItem.getTxnStatus().contains(",")){
			String txnstatuslist[] = mgItem.getTxnStatus().split(",");
			message.append("<TxnStatusList><txnStatus>")
			.append(txnstatuslist[0])
			.append("</txnStatus><txnStatus>")
			.append(txnstatuslist[1])
			.append("</txnStatus></TxnStatusList>");
		}else{
			message.append("<TxnStatusList><txnStatus>")
			.append(mgItem.getTxnStatus())
			.append("</txnStatus></TxnStatusList>");
		}
		message.append("</BatchQryTxnMsgContent></MasMessage>");
		String respString = "";
		try {
			respString	= mgwhttpsubmit.post(message.toString(), batchtxnqueryurl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return respString;
	}

}
