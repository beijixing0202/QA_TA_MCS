package com.bill99.ate.common.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class GetHttpClient {

	//	构造HttpClient实例
	HttpClient httpClient = new HttpClient();
	public void get(String url) {

		//		创建Get方法

		GetMethod getMethod = new GetMethod(url);
		//		使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			//			执行get方法
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed:" + getMethod.getStatusLine());
			}
			//			读取内容
			byte[] responseBody = getMethod.getResponseBody();
			System.out.println(new String(responseBody));
			System.out.println("请求成功");
		} catch (HttpException e) {
			// TODO: handle exception
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There may be problems with the network!");
			e.printStackTrace();
		}
		//		finally {
		//			getMethod.releaseConnection();
		//		}

	}
}
