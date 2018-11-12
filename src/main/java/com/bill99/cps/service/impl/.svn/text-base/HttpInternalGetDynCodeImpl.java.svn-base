package com.bill99.cps.service.impl;

import java.net.MalformedURLException;

import org.springframework.util.StringUtils;

import shelper.common.MyDate;

import com.bill99.cps.access.http.HttpInternalPost;
import com.bill99.cps.common.dto.MockInternalItem;
import com.bill99.cps.service.HttpInternalContentService;

/**
 * Internal mock quickpay页面，获取手机动态码鉴权
 * @author chongpeng.yan
 *
 */
public class HttpInternalGetDynCodeImpl implements HttpInternalContentService {
	
	private HttpInternalPost httpInternalPost;
	private String getDynCodeUrl;
	private String posturl;
	
	public void setGetDynCodeUrl(String getDynCodeUrl) {
		this.getDynCodeUrl = getDynCodeUrl;
	}

	public void setPosturl(String postUrl) {
		this.posturl = postUrl;
	}

	public void setHttpInternalPost(HttpInternalPost httpInternalPost) {
		this.httpInternalPost = httpInternalPost;
	}


	@Override
	//TODO 如何区分首次二次鉴权消费，来决定是否改变customerid的值
	public String cnpContent(MockInternalItem mockInternalItem ) {
		
		//若customerid为空则生成一个时间戳的id
		if (!StringUtils.hasLength(mockInternalItem.getCustomerId())){
			mockInternalItem.setCustomerId("sk"+MyDate.getUserDate("yyMMddhhmmss")+"@qa.99bill.com");
		}
		// 组装获取手机动态码鉴权报文
		StringBuffer arraymsgcontent = new StringBuffer();
		
		arraymsgcontent
		.append("postUrl=http://internal.99bill.com/mas/internal/WsMtpService")//+posturl)
		.append("&merchantId="+mockInternalItem.getMerchantId())
		.append("&merchantMemberCode="+mockInternalItem.getMerchantMemberCode())
		.append("&customerId="+mockInternalItem.getCustomerId())
		//此处externalRefNumber与cnp中serviceChannelNo生成逻辑一致，故直接套用
		.append("&externalRefNumber="+mockInternalItem.getServiceChannelTraceNo())
		.append("&PAN="+mockInternalItem.getPAN())
		.append("&storablePAN="+mockInternalItem.getStorablePAN())//TODO，二次
		.append("&phoneNO="+mockInternalItem.getPhoneNO())
		.append("&amt="+mockInternalItem.getAmt())
		.append("&cardholderName="+mockInternalItem.getCatdHolderName())
		.append("&idType="+mockInternalItem.getIdType())
		.append("&cardholderId="+mockInternalItem.getCardHolderId())
		.append("&bankId="+mockInternalItem.getBankId())
		.append("&cvv2="+mockInternalItem.getCvv2())
		.append("&expiredDate="+mockInternalItem.getExpiredDate())
		.append("&bindType="+"");//mockInternalItem.getBindType());
		
		String resString = "";
		try {
			// 打印请求报文
			//String ssst="postUrl=http://internal.99bill.com/mas/internal/WsMtpService&merchantId=812310045110066&merchantMemberCode=10020185410&customerId=2111ddp210@qa.99bill.com&externalRefNumber=16481281926755&PAN=6229180005703303&storablePAN=&phoneNO=17092582594&amt=0.35&cardholderName=晏崇芃&idType=101&cardholderId=320322199101308638&bankId=CITIC&cvv2=158&expiredDate=0120&bindType=";
			//System.out.println("reqString=" + ssst);
			System.out.println("reqString=" + arraymsgcontent.toString());
			// 获取返回报文
			System.out.println("获取动态码地址" + getDynCodeUrl);
			resString = httpInternalPost.post(arraymsgcontent.toString(),
					getDynCodeUrl);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return resString;
	}

}
