package com.bill99.mcs.page;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

public class McsWebLoginPageHttp {

	private String mcsLoginUrl;

	private HttpFixture httpClient;

	public McsWebLoginPageHttp(HttpFixture httpClient, String mcsUrl) {
		this.httpClient = httpClient;
		this.mcsLoginUrl = mcsUrl + "LoginPoint.ws";
	}

	// 登录mcs
	public String login(String username, String password, String verifyCode) {

		Reporter.log("MCS登陆，登陆信息：" + username + ";" + password + ";"
				+ verifyCode);
		String loginRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
				+ "	<SOAP-ENV:Body>"
				+ "		<m:login xmlns:m=\"http://www.51edc.com/mas/2007/console/LoginPoint\">"
				+ "					<m:in0>" + username + "</m:in0>" + "				    <m:in1>"
				+ password + "</m:in1>" + "                 <m:in2>"
				+ verifyCode + "</m:in2>" + "</m:login>" + "	</SOAP-ENV:Body>"
				+ "</SOAP-ENV:Envelope>";
		httpClient.nextRequest();
		httpClient.setUrl(mcsLoginUrl);
		httpClient.addHeaderValue("Content-Type", "text/xml");
		httpClient.addHeaderValue("charset", "utf-8");
		httpClient.addHeaderValue("Accept", "application/soap+xml");
		httpClient.addRequestBody(loginRequest);
		httpClient.Post();
		httpClient.getStatus();

		Reporter.log("页面返回状态：" + httpClient.getStatus());
		String Cookie = httpClient.getResponseheader("Set-Cookie");
		Reporter.log("保存cookie：" + Cookie);
		return Cookie;
	}

	public void main(String[] args) {
		login("9999", "123456", "1111");
	}
}
