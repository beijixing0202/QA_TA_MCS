package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import com.bill99.cps.access.http.HttpInternalPost;
import com.bill99.cps.common.dto.MockInternalItem;
import com.bill99.cps.service.HttpInternalContentService;

/**
 * internal mock cnp交易发起
 * @author chongpeng.yan
 */
public class HttpInternalCnpServiceImpl implements HttpInternalContentService {
	private HttpInternalPost httpInternalPost;
	private String cnpUrl;
	private String posturl;

	public void setCnpUrl(String cnpUrl) {
		this.cnpUrl = cnpUrl;
	}

	public void setPosturl(String postUrl) {
		this.posturl = postUrl;
	}

	public void setHttpInternalPost(HttpInternalPost httpInternalPost) {
		this.httpInternalPost = httpInternalPost;
	}

	@Override
	public String cnpContent(MockInternalItem mockInternalItem) {
		// 获取请求发往的url,原来从数据驱动获取，现在修改为从httpurl.properties中获取
		//String url=mockInternalItem.getUrl();
		// 组装http请求
		StringBuffer arraymsgcontent = new StringBuffer();
		arraymsgcontent
		//.append("postUrl="+mockInternalItem.getPostUrl())，改为直接从配置中获取的postUrl
		.append("postUrl="+posturl)
		.append("&merchantId="+mockInternalItem.getMerchantId())
		.append("&merchantMemberCode="+mockInternalItem.getMerchantMemberCode())
		.append("&terminalId="+mockInternalItem.getTerminalId())
		.append("&txnType="+mockInternalItem.getTxnType())
		.append("&PAN="+mockInternalItem.getPAN())
		.append("&authResponseCode="+mockInternalItem.getAuthResponseCode())
		.append("&expiredDate="+mockInternalItem.getExpiredDate())
		.append("&cvv2="+mockInternalItem.getCvv2())
		.append("&cur="+mockInternalItem.getCur())
		.append("&amt="+mockInternalItem.getAmt())
		.append("&cardHolderId="+mockInternalItem.getCardHolderId())
		.append("&catdHolderName="+mockInternalItem.getCatdHolderName())
		.append("&rewardPoints="+mockInternalItem.getRewardPoints())
		.append("&termInMonths="+mockInternalItem.getTermInMonths())
		.append("&productId="+mockInternalItem.getProductId())
		.append("&convCur="+mockInternalItem.getConvCur())
		.append("&authCode="+mockInternalItem.getAuthCode())
		.append("&originalRRN="+mockInternalItem.getOriginalRRN())
		.append("&termTraceNo="+mockInternalItem.getTermTraceNo())
		.append("&termBatchNo="+mockInternalItem.getTermBatchNo())
		.append("&termInvoiceNo="+mockInternalItem.getTermInvoiceNo())
		.append("&externalRefNumber="+mockInternalItem.getExternalRefNumber())
		.append("&customerId="+mockInternalItem.getCustomerId())
		.append("&serviceChannelType="+mockInternalItem.getServiceChannelType())
		.append("&serviceChannelTraceNo="+mockInternalItem.getServiceChannelTraceNo())
		.append("&entryTime="+mockInternalItem.getEntryTime())
		.append("&siteType="+mockInternalItem.getSiteType())
		.append("&siteId="+mockInternalItem.getSiteId())
		.append("&appType="+mockInternalItem.getAppType())
		.append("&referenceDataId="+mockInternalItem.getReferenceDataId())
		.append("&extTraceId="+mockInternalItem.getExtTraceId())
		.append("&storablePAN="+mockInternalItem.getStorablePAN())
		.append("&phoneNO="+mockInternalItem.getPhoneNO())
		.append("&validCode="+mockInternalItem.getValidCode())
		.append("&savePciFlag="+mockInternalItem.getSavePciFlag())
		.append("&token="+mockInternalItem.getToken())
		.append("&payBatch="+mockInternalItem.getPayBatch())
		.append("&attachInfos="+mockInternalItem.getAttachInfos())
		.append("&supCardFlag="+mockInternalItem.getSupCardFlag())
		.append("&butPost="+mockInternalItem.getButPost());
		String resString = "";
		try {
			// 打印请求报文
			System.out.println("reqString=" + arraymsgcontent.toString());
			// 获取返回报文
			System.out.println("cnp交易发送地址：" + cnpUrl);
			resString = httpInternalPost.post(arraymsgcontent.toString(),
					cnpUrl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return resString;
	}
	

}
