package com.bill99.fi.access.http;

import java.util.Map;

import shelper.iffixture.HttpFixture;

public interface DoSubmit {

    public HttpFixture gatewaySubmit(Map<String, String> data);

    public HttpFixture rmbCarInsuranceSubmit(Map<String, String> data);

    public HttpFixture hwGateWaySubmit(Map<String, String> data);

    public HttpFixture msGatewaySubmit(Map<String, String> data);

    public HttpFixture oldGatewaySubmit(Map<String, String> data);

    public HttpFixture staticGatewaySubmit(Map<String, String> data);

    public HttpFixture staticInstallmentSubmit(Map<String, String> data);

    public HttpFixture ataGatewaySubmit(Map<String, String> data);

    public boolean gatewayApiRefundSubmit(Map<String, String> data);

    public boolean mobilegatewayApiRefundSubmit(Map<String, String> data);

    public boolean msGatewayApiRefundSubmit(Map<String, String> data);

    //public boolean hxGatewayApiRefundSubmit(Map<String, String> data);

    public HttpFixture msGatewayRefundActionSubmit(Map<String, String> data);

    public void refundRiskAndFinance(String orderId, String member, int financeSign);

    public HttpFixture intraLogin();

    public HttpFixture accountAutoPaySubmit(Map<String, String> data);

//	public HttpFixture platfromaccountAutoPaySubmit(Map<String, String> data);

    public HttpFixture hxGatewaySubmit(Map<String, String> data);

    public HttpFixture hxGatewayRefundSubmit(Map<String, String> data);

    public HttpFixture msGatewayModifySubmit(Map<String, String> data);

    public HttpFixture msGatewayRefundModifySubmit(Map<String, String> data);

    public HttpFixture msGatewayConfirmSubmit(Map<String, String> data);

    public HttpFixture mobileGatewaySubmit(Map<String, String> data);

    public HttpFixture chinaUnicomSubmit(Map<String, String> data);

    public HttpFixture installmentSubmit(Map<String, String> data);

    public HttpFixture mergeGatewaySubmit(Map<String, String> data);

    public HttpFixture bill99WebSiteLoginSubmit(Map<String, String> data);

    /**
     * 通过mock发起退款操作
     *
     * @param data 请求数据
     * @return 是否退款成功
     */
    boolean refundByMock(Map<String, String> data);

    //public HttpFixture doWebsiteRefund(Map<String, String> data);

	
	/*public boolean platAutoPaySubmit(HttpFixture query, Map<String, String> data,String[] strmac) throws Exception;

	public HttpFixture platAutoPay(Map<String, String> data);*/

}
