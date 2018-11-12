package com.bill99.ate.common.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PostHttpClient {
	
	private Log log = LogFactory.getLog(PostHttpClient.class);
	
	HttpClient httpClient = new HttpClient();
	private String location;
	private int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int post(String url, NameValuePair[] data) {
		//	public static void main(String[] args) {
		//		try {
		//	创建post方法
		//		PostMethod postMethod = new PostMethod("http://srv-monitor-st2.99bill.net/app-monitor-website/userLoginSub.do");
		PostMethod postMethod = new PostMethod(url);
		//		填入表单域的值
		//		NameValuePair[] data = { new NameValuePair("userName", "ifs_user"), new NameValuePair("userPwd", "000000"), new NameValuePair("Submit", "登录") };
		//		表单值放入postMethod中
		postMethod.setRequestBody(data);
		//		执行postMethod
		try {
			statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == 200) {
				byte[] responseBodyLog = postMethod.getResponseBody();
				//System.out.println(new String(responseBodyLog));
				log.info("请求成功");
			}
			if ((statusCode == HttpStatus.SC_MOVED_PERMANENTLY) || (statusCode == HttpStatus.SC_MOVED_TEMPORARILY) || (statusCode == HttpStatus.SC_SEE_OTHER)
					|| (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
				//				System.out.println(statusCode);
				Header locationHeader = postMethod.getResponseHeader("location");
				location = null;
				byte[] responseBodyLog = postMethod.getResponseBody();
				log.info(new String(responseBodyLog));
				log.info("重定向请求成功");
				if (locationHeader != null) {
					location = locationHeader.getValue();
					log.info("网页重定向到: " + location);
				} else {
					log.info("Location 字段为空，无重定向");
				}

			}

		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There may be problems with the network!");
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return statusCode;

	}
	//	创建httpClient实例

}
