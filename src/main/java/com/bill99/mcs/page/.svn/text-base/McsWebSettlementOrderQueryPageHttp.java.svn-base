package com.bill99.mcs.page;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

public class McsWebSettlementOrderQueryPageHttp {
	private String webserviceWsPaymentUrl;
	private HttpFixture httpClient;

	public McsWebSettlementOrderQueryPageHttp(HttpFixture httpClient, String url) {
		this.httpClient = httpClient;
		this.webserviceWsPaymentUrl = url + "/WsPaymentService.ws";
	}

	// 执行结算指令
	public void excuteSettleOrder(String settleOrderId, String operId,
			String password, String sessionID) {

		Reporter.log("结算-执行结算指令，结算编号：" + settleOrderId);
		String excuteSettle = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:m0=\"http://office.mas\">"
				+ "	<SOAP-ENV:Body>"
				+ "		<m:batchExecute xmlns:m=\"http://www.51edc.com/mas/2007/console/WsPaymentService\">"
				+ "              <m:in0>" + "					<m:long>" + settleOrderId
				+ "</m:long>" + "              </m:in0>" + "					<m:in1>"
				+ "                   <m0:operId>" + operId + "</m0:operId>"
				+ "                   <m0:password>" + password
				+ "</m0:password>" + "                 </m:in1>"
				+ "      </m:batchExecute>" + "	</SOAP-ENV:Body>"
				+ "</SOAP-ENV:Envelope>";
		httpClient.nextRequest();
		httpClient.setUrl(webserviceWsPaymentUrl);
		httpClient.addHeaderValue("Content-Type", "text/xml");
		httpClient.addHeaderValue("charset", "utf-8");
		httpClient.addHeaderValue("Accept", "application/soap+xml");
		httpClient.addHeaderValue("Cookie", sessionID);
		httpClient.addRequestBody(excuteSettle);
		httpClient.Post();
		Reporter.log("页面返回状态：" + httpClient.getStatus());
	}
}
