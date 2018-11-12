package com.bill99.ate.service.ebd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bill99.ate.common.httpclient.HttpUtil;

public class EdbLoginService {

	private static final String LOGINURL = "https://ebd.99bill.com/coc-bill-api/1.0/members/password/login";

	public Map<String, String> login(Map<String, String> data) throws IOException {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("idContent", data.get("idContent"));
		map.put("password", data.get("password"));
		String jsonStr = JSON.toJSONString(map);
		String result = HttpUtil.getInstance().postJson(jsonStr, LOGINURL);
		return JSON.parseObject(result, Map.class);

	}

}
