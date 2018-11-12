package com.bill99.ate.service.ate.mockHttp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bill99.ate.common.httpclient.HttpClientUtil;

public class LoginHttp {
	
	/**
	 * 
	 * 
	 * @Description:httpClient方式登录ATE mock
	 */
	public static void login(){
		Map<String , String> map = new HashMap<String, String>();
		map.put("username", "admin");
		map.put("password", "ateMock");
		try {
			String result= HttpClientUtil.doPost("http://192.168.14.88:8088/cap-mock/loginServlet", map, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
