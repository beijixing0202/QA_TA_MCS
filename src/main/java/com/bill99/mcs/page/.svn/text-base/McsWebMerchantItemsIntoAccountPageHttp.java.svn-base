package com.bill99.mcs.page;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

public class McsWebMerchantItemsIntoAccountPageHttp {
	private String webserviceWsSettlementUrl;
	private HttpFixture httpClient;

	public void setWebserviceWsSettlementUrl(String webserviceWsSettlementUrl) {
		this.webserviceWsSettlementUrl = webserviceWsSettlementUrl;
	}

	public McsWebMerchantItemsIntoAccountPageHttp(HttpFixture httpClient,
			String url) {
		this.httpClient = httpClient;
		this.webserviceWsSettlementUrl = url + "WsSettlementService.ws";
	}

	public void settleMerchant(String merchantId, int qsjb, int jsdj,
			String sessionID) {

		Reporter.log("结算-商户入账，MerchantId：" + merchantId);
		String settleMerchantRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
				+ "	<SOAP-ENV:Body>"
				+ "		<m:settleMerchant xmlns:m=\"http://www.51edc.com/mas/2007/console/SettlementService\">"
				+ "					<m:in0>"
				+ merchantId
				+ "</m:in0>"
				+ "					<m:in1>"
				+ qsjb
				+ "</m:in1>"
				+ "					<m:in2>undefined</m:in2>"
				+ "      </m:settleMerchant>"
				+ "	</SOAP-ENV:Body>"
				+ "</SOAP-ENV:Envelope>";
		httpClient.nextRequest();
		httpClient.setUrl(webserviceWsSettlementUrl);
		httpClient.addHeaderValue("Content-Type", "text/xml");
		httpClient.addHeaderValue("charset", "utf-8");
		httpClient.addHeaderValue("Accept", "application/soap+xml");
		httpClient.addHeaderValue("Cookie", sessionID);
		httpClient.addRequestBody(settleMerchantRequest);
		httpClient.Post();
		Reporter.log("页面返回状态：" + httpClient.getStatus());
		// System.out.println(httpClient.getResponseBody());
	}

}
