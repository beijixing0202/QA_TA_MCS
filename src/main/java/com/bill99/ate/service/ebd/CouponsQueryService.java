package com.bill99.ate.service.ebd;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bill99.ate.common.httpclient.HttpUtil;

public class CouponsQueryService {
	
	private EdbLoginService edbLoginService ;

	private static final String COUPONURL = "https://ebd.99bill.com/coc-bill-api/1.0/crt/coupons";

	public Map<String, Object> query(Map<String, String> data) throws IOException, URISyntaxException {
		
		Map<String, String> loginResult = edbLoginService.login(data);
		String token = loginResult.get("loginToken");
		Map<String , String > map = new HashMap<String, String>();
		map.put("couponStatus", data.get("edbStatus"));//查可用券和历史券 ， 1为历史 ，0为可用
		map.put("couponSort", "couponDate-");
		map.put("num", "1000");
		map.put("start", "0");
		Map<String , String > header = new HashMap<String, String>();
		header.put("Authorization", token);
		String result =HttpUtil.getInstance().getJson(map, header, COUPONURL);
		return JSON.parseObject(result, Map.class);
	}

	public void setEdbLoginService(EdbLoginService edbLoginService) {
		this.edbLoginService = edbLoginService;
	}
	
	

}
