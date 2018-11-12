package com.bill99.fi.common.utils;

import java.util.Map;

public class ParameterCheck {
	private StringBuffer restrtemp = new StringBuffer();
	public String SignMsg(String[] strmac,Map<String, String> data) {

		for (String key:strmac) {

			if(key.equals("signMsg")){
				key="key";
			}
			if (data.get(key).equals("")) {

			} else {
				restrtemp.append(key + "=" + data.get(key) + "&");
			}
		}
		System.out.println("加签前字符串：" + restrtemp.substring(0, restrtemp.length() - 1));
		
		return MD5Util.MD5(restrtemp.substring(0, restrtemp.length() - 1),data.get("inputCharset"));
	}
}
