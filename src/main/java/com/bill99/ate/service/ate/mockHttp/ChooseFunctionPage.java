package com.bill99.ate.service.ate.mockHttp;

import org.springframework.stereotype.Service;

import com.bill99.ate.common.httpclient.HttpClientUtil;

public class ChooseFunctionPage {
	
	/**
	 * 
	 * 
	 * @Description:httpClient方式进入ATE 3.0 mock页面
	 */
	public static void enterMdpCenter(){
		LoginHttp.login();
		try {
			String result = HttpClientUtil.doGet("http://192.168.14.88:8088/cap-mock/mdpCenter.jsp", null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
