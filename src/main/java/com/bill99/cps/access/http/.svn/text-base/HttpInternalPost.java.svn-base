package com.bill99.cps.access.http;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import shelper.iffixture.HttpFixture;

public class HttpInternalPost {
	public String post(String content, String url)
			throws NumberFormatException, MalformedURLException {

		HttpFixture Hf = new HttpFixture();
		Hf.setUrl(url);
		Hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		Hf.addRequestBody(content);
		Hf.Post();
		return Hf.getResponseBody();
	}

	public String get(String url) {
		HttpFixture Hf = new HttpFixture();
		Hf.setUrl(url);
		Hf.Get();
		return Hf.getResponseBody();
	}

	// 将返回字符串转换为map便于取值
	public static Map<String, String> tranStringtoMap(String resString) {

		resString = resString.replace("[", "").replace("]<br", "")
				.replace("<a href=''></a>", "").replace("{", "")
				.replace("}", "").replace("attachInfos=", "").trim();
		String st[] = new String[100];
		st = resString.split("[>,]");

		int num = st.length;
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < num; i++) {
			//System.out.println(st[i].trim());
			String stt[] = new String[1];
			stt = st[i].trim().split("=");
			
			if(stt.length==1){//空值判断
				map.put(stt[0],"");	
				//System.out.println(stt[0]+"="+stt[1]);
			}
			else{
			map.put(stt[0],stt[1]);
			//System.out.println(stt[0]+"="+stt[1]);
			}
		}
		return map;
	}
	
	public static Map<String, String> getDynCodetoMap(String resString) {

		String st[] = new String[10];
		st = resString.split("<br>");

		int num = st.length;
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < num; i++) {
			//System.out.println(st[i].trim());
			String stt[] = new String[1];
			stt = st[i].trim().split("=");
			
			if(stt.length==1){//空值判断
				map.put(stt[0],"");	
				//System.out.println(stt[0]+"="+stt[1]);
			}
			else{
			map.put(stt[0],stt[1]);
			//System.out.println(stt[0]+"="+stt[1]);
			}
		}
		return map;
	}
}
