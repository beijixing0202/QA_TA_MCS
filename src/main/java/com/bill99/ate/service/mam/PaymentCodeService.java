package com.bill99.ate.service.mam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bill99.seashell.ma.common.util.HttpUtil;

public class PaymentCodeService {
	
	private String APP_ID ;
	
	private String BIZ_CODE;
	
	private String serviceId;
		
	private String url;
	
	/**
	 * 生成支付二维码
	 * @param memberCode
	 * @return
	 */
	public String getAuthCode(String memberCode) {
		Object requestObject = buildRequest(memberCode);
		System.out.println(("request:" + requestObject));
		String responseString;
		try {
			responseString = HttpUtil.postHttpRequest(requestObject, serviceId, url);
			Map<String, Object> resultMap = HttpUtil.jsonToMap(responseString);
			System.out.println(("response:" + resultMap));
			return resultMap.get("payCode").toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成支付二维码
	 * @param memberCode
	 * @return 
	 */
	private Object buildRequest(String memberCode) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("appId", APP_ID);
		reqMap.put("bizCode", BIZ_CODE);
		//		reqMap.put("media", "10012768613");
		reqMap.put("media", memberCode);
		reqMap.put("deviceId", "ffffffff-e3df-665a-ffff-ffffc4d418a1");
		reqMap.put("requestTime", new Date());
		reqMap.put("expiredDuration", 5);
		return reqMap;
	}
	
	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}

	public void setBIZ_CODE(String bIZ_CODE) {
		BIZ_CODE = bIZ_CODE;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
