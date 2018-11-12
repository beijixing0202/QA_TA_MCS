package com.bill99.fi.access.http.impl;

import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.bill99.fi.common.helper.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

import com.bill99.fi.access.http.DoSubmit;
import com.bill99.fi.common.helper.ParameterSignMsg;
import com.bill99.fi.common.helper.ParameterSource;
import com.bill99.fi.common.helper.ParametersInitialization;
import com.bill99.fi.common.utils.CheckResponse;
import com.bill99.fi.common.utils.ChinaUnicomUtil;
import com.bill99.fi.common.utils.MD5Util;

public class DoSubmitImpl implements DoSubmit {

    @Autowired
    private ParametersInitialization parametersInitialization;
    private ParameterSignMsg parameterSignMsg;
    public int i = 0;


    public ParameterSignMsg getParameterSignMsg() {
        return parameterSignMsg;
    }

    public void setParameterSignMsg(ParameterSignMsg parameterSignMsg) {
        this.parameterSignMsg = parameterSignMsg;
    }

    /*
     * ataGateway post submit
     *
     * @see com.bill99.fi.access.http.DoSubmit#gatewaySubmit(java.util.Map)
     */
    public HttpFixture ataGatewaySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getGatewayUrl(), ParameterSource.ataGatewayParameter);

    }

    /*
     * gateway post submit
     *
     * @see com.bill99.fi.access.http.DoSubmit#gatewaySubmit(java.util.Map)
     */
    public HttpFixture gatewaySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getGatewayUrl(), ParameterSource.gatewayParameter);
    }

    /*
     * mobileGateway post submit
     *
     * @see com.bill99.fi.access.http.DoSubmit#gatewaySubmit(java.util.Map)
     */
    public HttpFixture mobileGatewaySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getMobileGatewayUrl(), ParameterSource.mobileGatewaySubmitParameter);
    }

    /*
     * oldGateway post submit
     *
     * @see com.bill99.fi.access.http.DoSubmit#gatewaySubmit(java.util.Map)
     */
    public HttpFixture oldGatewaySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getOldGatewayUrl(), ParameterSource.oldGatewayParameter);
    }


    /*public HttpFixture platfromaccountAutoPaySubmit(Map<String, String> data) {

        StringBuffer restrtemp = new StringBuffer();
        for (String key : ParameterSource.gatewayParameter) {
            // System.out.println("key========"+key);
            if (null != data.get(key) && !data.get(key).equals("")) {

                restrtemp.append(key + "=" + data.get(key) + "&");
            }
        }

        //拼接key
        String str[]=restrtemp.toString().split("&signMsg");
        data.put("orderMsg", str[0]+"&key="+data.get("key")+"&signMsg"+str[1].substring(0, str[1].length()-1));
        //改变加签类型,因平台使用的是PKI
        data.put("signType", "4");
        //data.put("platformSignMsg", parameterSignMsg.SignMsg(ParameterSource.platfromAccountAutopayParameter,data));

        return post(data, parametersInitialization.getPlatAutoPayUrl(), ParameterSource
        .platfromAccountAutopayParameter);
    }*/
    /*
     * msGateway post Submit
	 * 
	 * @see com.bill99.fi.access.http.DoSubmit#msGatewaySubmit(java.util.Map)
	 */
    public HttpFixture msGatewaySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getMsGatewayUrl(), ParameterSource.msgatewayParameter);
    }

    /*
     * hxGateway post Submit
     *
     * @see com.bill99.fi.access.http.DoSubmit#hxGatewaySubmit(java.util.Map)
     */
    public HttpFixture hxGatewaySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getHxGatewayUrl(), ParameterSource.hxgatewayParameter);
    }

    public HttpFixture hxGatewayRefundSubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getHxGatewayRefundUrl(), ParameterSource.hxgatewayRefundParameter);
    }

    private HttpFixture post(Map<String, String> data, String url, String[] strmac) {
        StringBuffer restrtemp = new StringBuffer();
        for (String key : strmac) {
            System.out.println("key========" + key);
            if (null != data.get(key) && !data.get(key).equals("")) {

                restrtemp.append(key + "=" + data.get(key) + "&");
            }
        }
        HttpFixture query = new HttpFixture();
        query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        query.setUrl(url);
        System.out.println("URL+++++++++++++++++++++++++++++++" + url);
        query.addRequestBody(restrtemp.substring(0, restrtemp.length() - 1));
        System.out.println("requestBody====================" + restrtemp.substring(0, restrtemp.length() - 1));
        query.Post();
        System.out.println("resBody====================" + query.getResponseBody());
        // 参数校验及判断失败原因
        if (null == data.get("responseTextError") && query.findStringinResponse("unsuccess")) {
            Reporter.log("订单提交失败原因：" + query.saveParamLeftstrRightstr("<div class=\"unsuccess\">", "</div>").replace
                    ("\r\n", "").replace("	", ""), false);
        }
        return query;

    }

	
/*
 *平台支付*/

	/*public HttpFixture platAutoPay(Map<String, String> data) {
        return post(data, parametersInitialization.getPlatAutoPayUrl(), ParameterSource
		.platfromAccountAutopayParameterMsg);
	}*/

    /*public boolean platAutoPaySubmit(HttpFixture refund, Map<String, String> data,String[] strmac) throws Exception
    {

        StringBuffer restrtemp = new StringBuffer();
        for (String key : strmac) {
            if (null != data.get(key) && !data.get(key).equals("")) {

                restrtemp.append(key + "=" + data.get(key) + "&");
            }
        }

        String orderMsg = restrtemp.substring(0, restrtemp.length() - 1);
        String platformSignMsg = parameterSignMsg.platSignMsg(data, orderMsg);

        //HttpFixture refund = new HttpFixture();
        refund.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        refund.setUrl("http://192.168.64.51:8997/acctAutopay/platformMsg.jsp");
        refund.addRequestBody(restrtemp.substring(0, restrtemp.length() - 1));
        refund.Post();

        refund.setUrl("http://192.168.64.51:8997/acctAutopay/platformSign.jsp");
        refund.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        refund.addRequestBody("orderMsg="+orderMsg+"platformInputCharset=" + data.get("platformInputCharset")
        +"&platformPayType="+ data.get("platformPayType")+
                "&acct=" + data.get("acct")+
                "&platformVersion="+data.get("platformVersion")+"&platformId=" + data.get("platformId")+
                "&platformSignType=" +data.get("platformSignType") + "&platformSignMsg=" + platformSignMsg +
                "&serverSubmitAddress=http://www.99bill.com");
        refund.Post();

        refund.nextRequest();
        refund.setUrl("http://www.99bill.com/gateway/recvMerchantInfoAction.htm");
        refund.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");

        refund.addRequestBody("orderMsg="+orderMsg+"platformInputCharset=" + data.get("platformInputCharset")
        +"&platformPayType="+ data.get("platformPayType")+
                "&platformPayMsg=" + data.get("platformPayMsg")+
                "&platformVersion="+data.get("platformVersion")+"&platformId=" + data.get("platformId")+
                "&platformSignType=" +data.get("platformSignType") + "&platformSignMsg=" + platformSignMsg +
                "&platformSignMsg=orderMsg="+ orderMsg);
        refund.Post();

        boolean refundResult = refund.findStringinResponse("<RESULT>Y</RESULT>")||refund.findStringinResponse
        ("<payResult>10</payResult>");
        return refundResult;

    }*/
	/*
	 * gateway Api refund
	 * 
	 * @see
	 * com.bill99.fi.access.http.DoSubmit#gatewayApiRefundSubmit(java.util.Map)
	 */
    public boolean gatewayApiRefundSubmit(Map<String, String> data) {
        String txOrder = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.err.println("data.refund_merchant_id:" + data.get("refund_merchant_id"));
        String refund_merchant_id = data.get("refund_merchant_id").length() > 12 ? StringUtils.substring(data.get
                ("refund_merchant_id"), 0, 11) : data
                .get("refund_merchant_id");

        String amount = String.valueOf((Float.valueOf(data.get("orderAmount")) / 100));
        String mac = MD5Util.MD5("merchant_id=" + refund_merchant_id +
                "version=bill_drawback_api_1command_type=001orderid=" + data.get("orderId") + "amount="
                + amount + "postdate=" + txOrder + "txOrder=" + txOrder + "signType=1merchant_key=" + data.get
                ("refund_merchant_key") + "", null);
        HttpFixture refund = new HttpFixture();
        refund.setUrl(parametersInitialization.getGateApiRefundwayUrl());
        refund.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        refund.addRequestBody("merchant_id=" + refund_merchant_id +
                "&version=bill_drawback_api_1&command_type=001&txOrder=" + txOrder + "&amount=" + amount
                + "&postdate=" + txOrder + "&signType=1" + "&orderid=" + data.get("orderId") + "&mac=" + mac + "");
        System.err.println("hahahahhahahahahahahhaha" + "merchant_id=" + refund_merchant_id +
                "&version=bill_drawback_api_1&command_type=001&txOrder=" + txOrder + "&amount=" + amount
                + "&postdate=" + txOrder + "&signType=1" + "&orderid=" + data.get("orderId") + "&mac=" + mac + "");
        refund.Post();
        System.out.println("res+" + refund.getResponseBody());
        boolean refundResult = refund.findStringinResponse("<RESULT>Y</RESULT>");
        System.err.println("refundResult" + refundResult);

        if (!refundResult) {
            refundResult = refund.findStringinResponse("退款申请正在进行中");
        }
        if (!refundResult) {
            Reporter.log("退款失败原因：" + refund.getResponseBody().split("<CODE>|</CODE>")[1]);
        } else {
            String seqid = refund.getResponseBody().split("<TXORDER>|</TXORDER>")[1];
            Reporter.log("退款申请号：" + seqid);
            data.put("refundOrderId", seqid);
        }
        return refundResult;

    }

    /*
     * mobilegateway Api refund
     *
     * @see
     * com.bill99.fi.access.http.DoSubmit#mobilegatewayApiRefundSubmit(java.util.Map)
     */
    public boolean mobilegatewayApiRefundSubmit(Map<String, String> data) {
        String txOrder = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //String refund_merchant_id = data.get("ref_merchant_id").length() > 12 ? StringUtils.substring(data.get
        // ("ref_merchant_id"), 0, 11) : data
        //.get("ref_merchant_id");
        String amount = String.valueOf((Float.valueOf(data.get("orderAmount")) / 100));
        String mac = MD5Util.MD5("merchant_id=" + data.get("ref_merchant_id") +
                "version=bill_drawback_api_1command_type=001orderid=" + data.get("orderId") + "amount="
                + amount + "postdate=" + txOrder + "txOrder=" + txOrder + "signType=1merchant_key=" + data.get
                ("ref_merchant_key") + "", null);
        HttpFixture refund = new HttpFixture();
        refund.nextRequest();
        refund.setUrl(parametersInitialization.getMobileDebitRefundUrl());
        refund.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        refund.addRequestBody("merchant_id=" + data.get("ref_merchant_id") +
                "&version=bill_drawback_api_1&command_type=001&orderid=" + data.get("orderId") + "&amount=" + amount
                + "&postdate=" + txOrder + "&signType=1" + "&txOrder=" + txOrder + "&mac=" + mac + "");

        refund.Post();
        boolean refundResult = refund.findStringinResponse("<RESULT>Y</RESULT>");
        String seqid = refund.getResponseBody().split("<TXORDER>|</TXORDER>")[1];
        Reporter.log("退款申请号：" + seqid);
        data.put("refundOrderId", seqid);
        if (!refundResult) {
            refundResult = refund.findStringinResponse("退款申请正在进行中");
        }
        if (!refundResult) {
            Reporter.log("退款失败原因：" + refund.getResponseBody().split("<CODE>|</CODE>")[1]);
        }
        return refundResult;
    }

    /*
     * msgateway api refund
     *
     * @see
     * com.bill99.fi.access.http.DoSubmit#msGatewayApiRefundSubmit(java.util
     * .Map)
     */
    public boolean msGatewayApiRefundSubmit(Map<String, String> data) {

        HttpFixture refund = new HttpFixture();

        refund.setUrl(parametersInitialization.getMsGateApiRefundwayUrl());
        refund.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        refund.addRequestBody("inputCharset=1&isOpenNewWindow=on&version=" + data.get("ref_version") +
                "&signType=1" + "&merchant_key=" + data.get("ref_key") + "&orderId=" + data.get("orderId") +
                "&advanceFlag=" + data.get("ref_advanceFlag") + "&pid=" + data.get("ref_pid") + "&seqId=" +
                data.get("ref_seqId") + "&returnAllAmount=" + data.get("ref_returnAllAmount") + "&getTolerance=" +
                data.get("ref_getTolerance") +
                "&returnTime=" + data.get("ref_returnTime") + "&returnDetail=" + data.get("ref_returnDetail") +
                "&returnSharingDetail=" + data.get("ref_returnSharingDetail") + "&assignAcct=" +
                data.get("ref_assignAcct") + "&refundFlag=" + data.get("ref_refundFlag") + "&shareRefundFeeFlag=" +
                data.get("ref_shareRefundFeeFlag") + "&signMsg=" + data.get("ref_signMsg"));
        refund.Post();
        System.out.println(refund.getResponseBody());
        boolean refundResult = refund.findStringinResponse("<RESULT>Y</RESULT>") || refund.findStringinResponse
                ("<result>10</result>");
        String seqid = refund.getResponseBody().split("<seqId>|</seqId>")[1];
        Reporter.log("退款申请号：" + seqid);
        data.put("refundOrderId", seqid);
        if (!refundResult) {
            refundResult = refund.findStringinResponse("退款申请正在进行中");
        }
        if (!refundResult) {
            Reporter.log("退款失败原因：" + refund.getResponseBody().split("<CODE>|</CODE>")[1]);
        }
        return refundResult;
    }

/*	public boolean hxGatewayApiRefundSubmit(Map<String, String> data) {

		HttpFixture refund = new HttpFixture();
		refund.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		refund.setUrl(parametersInitialization.getHxGatewayRefundUrl());
		refund.addRequestBody("inputCharset=1&version=" + data.get("ref_version")+
				"&language=" + data.get("ref_language")+ "&signType=" + data.get("ref_signType")+
				"&orderId=" + data.get("orderId")+ "&pid=" + data.get("ref_pid")+"&seqId=" + data.get("ref_seqId")+
				"&returnAllAmount=" + data.get("ref_returnAllAmount") +	"&returnTime="+data.get("ref_returnTime")+
				"&returnDetail=" + data.get("ref_returnDetail")+ "&merchant_key=" + data.get("ref_key")+
				"&signMsg=" + data.get("ref_signMsg"));
		*/

    /**
     * ('signMsg','inputCharset=[inputCharset]&version=[version]&language=[language]&signType=[signType]&
     * orderId=[orderId]&pid=[pid]&seqId=[seqId]&returnAllAmount=[returnAllAmount]&returnTime=[returnTime]&
     * ext1=[ext1]&ext2=[ext2]&returnDetail=[returnDetail]&key=[merchant_key]')" value='刷新MD5'>
     *//*
		refund.Post();
		System.out.println(refund.getResponseBody());

		boolean refundResult = refund.findStringinResponse("<td>result</td><td>10</td>");
		//String seqid = refund.getResponseBody().split("<seqId>|</seqId>")[1];
		Reporter.log("退款申请号：" + data.get("ref_seqId"));
		//data.put("refundOrderId", seqid);
		if (!refundResult) {
			Reporter.log("退款失败");
		}
		return refundResult;

	}*/
    public HttpFixture intraLogin() {
        HttpFixture query = new HttpFixture();
        query.setRequesttimeout(20000);
        query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        // 登录intra
        query.setUrl(parametersInitialization.getIntraUrl() + "?method=login&userName=" + parametersInitialization
                .getIntraUserName() + "&password="
                + parametersInitialization.getIntraPassWord());
        query.Get();
        return query;
    }

	/*
	 * 风控查询

	 */

    public HttpFixture RiskQuery(HttpFixture query, String orderId, String member, String startdate, String enddate) {

        // 风控查询https://intra.99bill.net/fiintra/gateway/refundRiskQuery.htm?method=query
        query.nextRequest();
        query.setUrl("https://intra.99bill.net/fiintra/gateway/refundRiskQuery.htm?method=query&merchantIdContent=" +
                member
                + "&merchantName=&orderStatus=0&rfdTerminalType=all&isTrustRefund=all&startDate=" + startdate +
                "&endDate=" + enddate
                + "&alertCount=&alertAmount=&optBatchId=");
        do {
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            query.Get();
            i++;
            Reporter.log("风控循环查询第" + i + "次");
        } while (i < 5 && query.getResponseBody().contains("共 0 条记录") && !query.getResponseBody().contains(orderId));

        i = 0;
        return query;

    }

    /*
     * financeSign==1 过财务，member 账户登录邮箱，orderId 支付订单号
     *
     * @see
     * com.bill99.fi.access.http.DoSubmit#refundRiskAndFinance(java.lang.
     * String, java.lang.String, int)
     */
    public void refundRiskAndFinance(String orderId, String member, int financeSign) {
        HttpFixture query = intraLogin();
        // HttpFixture query = new HttpFixture();
        // query.setRequesttimeout(20000);
        // query.addHeaderValue("Content-Type",
        // "application/x-www-form-urlencoded");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long date = new java.util.Date().getTime();
        String startdate = format.format(date);
        String enddate = format.format(date + 86400000);

        // // 登录intra
        // query.setUrl(parametersInitialization.getIntraUrl() +
        // "?method=login&userName=" +
        // parametersInitialization.getIntraUserName() + "&password="
        // + parametersInitialization.getIntraPassWord());
        // query.Get();
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Reporter.log("intra 网关退款风控处理查询--开始");
        query = RiskQuery(query, orderId, member, startdate, enddate);
        if (!query.getResponseBody().contains("共 0 条记录")) {

            String key = query.saveParamLeftstrRightstr("REFRESH_CODE\" value=\"", "\" />");
            // String woId =
            // query.saveParamLeftstrRightstr("checkbox\" value=\"", "\">");
            // 取查询商户的最后一条记录， seashell.refundmentworkorder2 的woid 可以从数据库取
            String splitstr[] = query.getResponseBody().split("<TR bgColor=#ccddee>");
            String woId = splitstr[splitstr.length - 1].split("checkbox\" value=\"")[1].substring(0,
                    splitstr[splitstr.length - 1].split("checkbox\" value=\"")[1].indexOf("\">"));

            int j = 1;
            while (!query.getResponseBody().contains("共 0 条记录")) {

                if (j == 10) {
                    System.out.println("查询超过十次！");
                    break;
                }

                key = query.saveParamLeftstrRightstr("REFRESH_CODE\" value=\"", "\" />");
                // String woId =
                // query.saveParamLeftstrRightstr("checkbox\" value=\"", "\">");
                // 取查询商户的最后一条记录， seashell.refundmentworkorder2 的woid 可以从数据库取
                String splitstr1[] = query.getResponseBody().split("<TR bgColor=#ccddee>");
                woId = splitstr1[splitstr1.length - 1].split("checkbox\" value=\"")[1].substring(0,
                        splitstr1[splitstr1.length - 1].split("checkbox\" value=\"")[1].indexOf("\">"));

                // // 风控通过
                query.nextRequest();
                query.setUrl("https://intra.99bill.net/fiintra/gateway/refundRiskHandle.htm");
                String bodystr = "method=pass&memo=&batchId=" + String.valueOf(date) + "&REFRESH_CODE=" + key +
                        "&woId=" + woId + "&size=10";
                System.out.println("风控审核===post 第" + j + "次=>" + bodystr);
                query.addRequestBody(bodystr);
                query.Post();
                j++;
                query = RiskQuery(query, orderId, member, startdate, enddate);
            }


            if (financeSign == 1) {
                // 获取REFRESH_CODE
                query.nextRequest();
                query.setUrl("https://intra.99bill.net/fiintra/gateway/refundFinanceQuery" +
                        ".htm?ssoFlag=defaultUrl&ssoMenuCode=M6_18");
                query.Get();
                String query_REFRESH_CODE = query.saveParamLeftstrRightstr("REFRESH_CODE\" value=\"", "\" />");

                // 过财务查询
                query.nextRequest();
                query.setUrl("https://intra.99bill.net/fiintra/gateway/refundFinanceQuery" +
                        ".htm?method=query&rfdOrderSeqId=&bankOrder=&orderId=&bankId=&channelCode=&orderStatus=10" +
                        "&rfdTerminalType=all&isTrustRefund=all&rfdType=&startDate="
                        + startdate
                        + "%2000:00:00&endDate="
                        + startdate
                        + "%2023:59:59&tempStartDate1="
                        + startdate
                        + "&tempStartDate2=00:00:00&tempEndDate1="
                        + startdate
                        + "&tempEndDate2=23:59:59&woStartDate=&woEndDate=&optBatchId=&download_left=&download_right" +
                        "=&download_memo=&download_batchId=&download_statusChange=false&REFRESH_CODE="
                        + query_REFRESH_CODE + "");
                i = 0;
                do {
                    query.Get();
                    i++;

                } while (i < 5 && query.getResponseBody().contains("共 0 条记录"));
                if (!query.getResponseBody().contains("共 0 条记录")) {
                    String response_REFRESH_CODE = query.saveParamLeftstrRightstr("REFRESH_CODE\" value=\"", "\" />");
                    // String response_woId =
                    // query.saveParamLeftstrRightstr("checkbox\" value=\"",
                    // "\">"); //seashell.refundmentworkorder2 的woid

                    // 财务通过
                    query.nextRequest();
                    query.setUrl("https://intra.99bill.net/fiintra/gateway/refundFinanceHandle.htm");
                    query.addRequestBody("autoRefund_memo=&autoRefund_batchId=&method=pass&memo=&batchId=" + String
                            .valueOf(date) + "1&REFRESH_CODE="
                            + response_REFRESH_CODE + "&woId=" + woId + "&size=10");
                    query.Post();
                }
            }
        } else {
            Reporter.log("intra 网关退款风控处理查询 --无记录");
        }
    }

    public ParametersInitialization getParametersInitialization() {
        return parametersInitialization;
    }

    public void setParametersInitialization(ParametersInitialization parametersInitialization) {
        this.parametersInitialization = parametersInitialization;
    }

    /*
     * static gateway post submit
     *
     * @see
     * com.bill99.fi.access.http.DoSubmit#staticGatewaySubmit(java.util.Map)
     */
    @SuppressWarnings("deprecation")
    public HttpFixture staticGatewaySubmit(Map<String, String> data) {

        StringBuffer restrtemp = new StringBuffer();
        for (String key : ParameterSource.staticGatewaySubmitParameter) {
            // System.out.println("key========"+key);
            if (null != data.get(key) && !data.get(key).equals("")) {

                restrtemp.append(key + "=" + URLEncoder.encode(data.get(key)) + "&");
                // restrtemp.append(key + "=" + data.get(key) + "&");
            }
        }
        HttpFixture query = new HttpFixture();
        query.setUrl(parametersInitialization.getStaticGatewayUrl() + restrtemp.substring(0, restrtemp.length() - 1));
        query.Get();
        Reporter.log("静态网关参数提交结果！", query.getResponseBody().contains(data.get("merchantIdcontent")) && query
                .getResponseBody().contains(data.get("submitOk")));
        query.nextRequest();
        StringBuffer temp = new StringBuffer();
        for (String key : ParameterSource.gatewayParameter) {
            // System.out.println("key========"+key);
            if (null != data.get(key) && !data.get(key).equals("")) {

                temp.append(key + "=" + URLEncoder.encode(data.get(key)) + "&");
                // restrtemp.append(key + "=" + data.get(key) + "&");
            }
        }

        query.setUrl(parametersInitialization.getGatewayUrl() + temp.substring(0, temp.length() - 1));
        query.Get();
        return query;

    }

    @SuppressWarnings("deprecation")
    public HttpFixture staticInstallmentSubmit(Map<String, String> data) {

        StringBuffer restrtemp = new StringBuffer();
        for (String key : ParameterSource.staticInstallmentSubmitParameter) {
            // System.out.println("key========"+key);
            if (null != data.get(key) && !data.get(key).equals("")) {

                restrtemp.append(key + "=" + URLEncoder.encode(data.get(key)) + "&");
                // restrtemp.append(key + "=" + data.get(key) + "&");
            }
        }
        HttpFixture query = new HttpFixture();
        query.setUrl(parametersInitialization.getStaticGatewayUrl() + restrtemp.substring(0, restrtemp.length() - 1));
        query.Get();
        //System.out.println(query.getResponseBody());
        Reporter.log("静态分期网关参数提交结果！", query.getResponseBody().contains(data.get("merchantIdcontent")) && query
                .getResponseBody().contains(data.get("submitOk")));
        query.nextRequest();
        StringBuffer temp = new StringBuffer();
        for (String key : ParameterSource.InstallmentSignParameter) {
            // System.out.println("key========"+key);
            if (null != data.get(key) && !data.get(key).equals("")) {

                temp.append(key + "=" + URLEncoder.encode(data.get(key)) + "&");
                // restrtemp.append(key + "=" + data.get(key) + "&");
            }
        }

        query.setUrl(parametersInitialization.getGatewayUrl() + temp.substring(0, temp.length() - 1));
        query.Get();
        //System.out.println(query.getResponseBody());
        return query;

    }

    public HttpFixture accountAutoPaySubmit(Map<String, String> data) {

        return post(data, parametersInitialization.getAccountAutoPayUrl(), ParameterSource.autoPaySubmitParameter);

    }

    /*
     * RMB 车险网关　 post submit
     *
     */
    public HttpFixture rmbCarInsuranceSubmit(Map<String, String> data) {
        data.put("version", "v2.1");
        data.put("key", data.get("key"));
        return post(data, parametersInitialization.getGatewayUrl(), ParameterSource.rmbCarInsuranceSumbitParameter);
    }

    /*
     * 海外网关　 post submit
     *
     */
    public HttpFixture hwGateWaySubmit(Map<String, String> data) {
        data.put("merchantKey", data.get("key"));
        return post(data, parametersInitialization.getGatewayUrl(), ParameterSource.hwGateWaySubmitParameter);
    }


    //分账修改参数提交
    public HttpFixture msGatewayModifySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getMsGatewayModifyUrl(), ParameterSource.msgatewayParameter);

    }

    //分账确认参数提交
    public HttpFixture msGatewayConfirmSubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getMsGatewayConfirmUrl(), ParameterSource.msgatewayParameter);

    }

    //分账退款修改参数提交
    public HttpFixture msGatewayRefundModifySubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getMsGatewayRefundModifyUrl(), ParameterSource
                .msgatewayRefundModifySubmit);
    }

    //分账退款确认参数提交
    public HttpFixture msGatewayRefundActionSubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getMsGatewayRefundActionUrl(), ParameterSource
                .msgatewayRefundAction);
    }

    //分期付款
    public HttpFixture installmentSubmit(Map<String, String> data) {
        return post(data, parametersInitialization.getGatewayUrl(), ParameterSource.InstallmentSubmitParameter);
    }

    //合并支付
    public HttpFixture mergeGatewaySubmit(Map<String, String> data) {

        return post(data, parametersInitialization.getGatewayUrl(), ParameterSource.mergeNetBankMD5SubmitParameter);

    }
	
	/*//平台支付
	public HttpFixture platAutoPaySubmit(Map<String, String> data) {
		return post(data, parametersInitialization.getPlatAutoPayUrl(),ParameterSource
		.platfromAccountAutopayParameterMsg);
			
	}*/


    public HttpFixture chinaUnicomSubmit(Map<String, String> data) {
        HttpFixture query = new HttpFixture();

        query.setUrl("http://www.99bill.com/unionproxy/recvUnicomPayAction.htm");
        query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
//		query.addRequestBody("PartnerID="+data.get("PartnerID")+"&PayReqObj="+data.get("PayReqObj"));
//		因字符串中存在特殊字符，需encode

        try {
            query.addRequestBody("PartnerID=" + data.get("PartnerID") + "&PayReqObj=" + URLEncoder.encode(data.get
                    ("PayReqObj"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        query.Post();
        //System.out.println(query.getResponseBody());
        String responseBody = ChinaUnicomUtil.unResponseBodyDispose(query.getResponseBody());

        query.nextRequest();
        query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        query.setUrl(parametersInitialization.getGatewayUrl());
        query.addRequestBody(responseBody);
        query.Post();
        //System.out.println(query.getResponseBody());
        return query;

    }

    public HttpFixture bill99WebSiteLoginSubmit(Map<String, String> data) {
        HttpFixture loginHf = null;
        for (int i = 0; i < 100; i++) {
            loginHf = bill99WebSiteLoginSubmitOld(data);
            //System.out.println("Body______________"+loginHf.getResponseBody());
            String ret = loginHf.getResponseBody();
            //System.out.println("Pname_________"+data.get("pname"));
            if (ret.contains(data.get("pname"))) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        return loginHf;
    }

    public HttpFixture bill99WebSiteLoginSubmitOld(Map<String, String> data) {
        HttpFixture loginHf = new HttpFixture();
        String site2pstoretoken = null;

        loginHf.nextRequest();
        loginHf.setUrl("https://www.99bill.com/website/login/loginout.htm");
        loginHf.Get();
//https://ssoqa.99bill.com/sso/login/authentication1.htm  https://www.99bill.com/website/login/authentication.htm
        loginHf.nextRequest();
        loginHf.setUrl("https://ssoqa.99bill.com/sso/login/authentication1.htm");
        loginHf.Get();

        //获取真实 site2pstoretoken 值
//		site2pstoretoken = CheckResponse
//				.parseString(
//						"(?<=<input type=\"hidden\" name=\"site2pstoretoken\" value=\")(.*?)(?=\" />)",
//						loginHf.getResponseBody());
//		System.out.println("site2pstoretoken1：：：："+site2pstoretoken);

        loginHf.nextRequest();
        loginHf.setUrl("https://www.99bill.com/website/myaccount/myacctinfo.htm");
        loginHf.Get();

        //获取真实 site2pstoretoken 值
        site2pstoretoken = CheckResponse
                .parseString(
                        "(?<=<input type=\"hidden\" name=\"site2pstoretoken\" value=\")(.*?)(?=\" />)",
                        loginHf.getResponseBody());
//		System.out.println("site2pstoretoken2：：：："+site2pstoretoken);

        loginHf.nextRequest();
        loginHf.setUrl(parametersInitialization.getAuthenticationUrl() + "?v=v1.4&site2pstoretoken=" +
                site2pstoretoken + "&p_error_code=&ssousername=");
        loginHf.Get();
		 
		 /*String REFRESH_CODE = CheckResponse
				.parseString(
						"(?<=value=\")(.*?)(?=\" /> <input type=\"hidden\" name=\"url\")",
						loginHf.getResponseBody());*/

        String REFRESH_CODE = CheckResponse
                .parseString(
                        "(?<=<input type=\"hidden\" name=\"REFRESH_CODE\" value=\")(.*?)(?=\" />)",
                        loginHf.getResponseBody());

        loginHf.nextRequest();
        loginHf.setUrl("https://ssoqa.99bill.com/sso/validatecode/validatecode.htm");
        loginHf.Get();

        loginHf.nextRequest();
        loginHf.setUrl("https://ssoqa.99bill.com/sso/login/authentication1.htm");
        //附加码输入有误!
        loginHf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        //loginHf.addHeaderValue("Host", "ssoqa.99bill.com");
        loginHf.setUrl(parametersInitialization.getAuthenticationUrl());
//		System.out.println(parametersInitialization.getAuthenticationUrl());

        loginHf.addParamValue("idContent", data.get("pemail"));
        loginHf.addParamValue("method", "checkIdcontent");
        loginHf.addParamValue("pageNo", "0");
        loginHf.addParamValue("randomValidateCode", data.get("randomValidateCode"));
        loginHf.addParamValue("REFRESH_CODE", REFRESH_CODE);
        System.err.println("REFRESH_CODE1" + REFRESH_CODE);
        loginHf.addParamValue("site2pstoretoken", site2pstoretoken);
        loginHf.addParamValue("submit", "登录");
        loginHf.addParamValue("v", "v1.4");
        loginHf.addParamValue("locale", "");
        loginHf.addParamValue("url", "");
        loginHf.Post();
//		System.out.println(loginHf.getResponseBody());
        //System.out.println("newRefreshCode"+loginHf.getResponseBody());
        if (CheckResponse
                .parseString(
                        "(?<=<input type=\"hidden\" name=\"REFRESH_CODE\"	value=\")(.*?)(?=\" />)",
                        loginHf.getResponseBody()) != "") {
            REFRESH_CODE = CheckResponse
                    .parseString(
                            "(?<=<input type=\"hidden\" name=\"REFRESH_CODE\"	value=\")(.*?)(?=\" />)",
                            loginHf.getResponseBody());
        } else {
            REFRESH_CODE = CheckResponse
                    .parseString(
                            "(?<=<input type=\"hidden\" name=\"REFRESH_CODE\" value=\")(.*?)(?=\" />)",
                            loginHf.getResponseBody());
        }


        loginHf.nextRequest();
        String encryptPassord = data.get("webLoginWord");
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String jsfile = "D:/TA/resource/FI/rsa-all-min.js";
            engine.eval(new FileReader(jsfile));

            // Invocable 接口: 允许java平台调用脚本程序中的函数或方法
            Invocable inv = (Invocable) engine;
            // invokeFunction()中的第一个参数就是被调用的脚本程序中的函数，第二个参数是传递给被调用函数的参数；
            encryptPassord = (String) inv.invokeFunction("getRsaPassword", encryptPassord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("encryptPassord:::"+encryptPassord);
        loginHf.setUrl(parametersInitialization.getAuthenticationUrl());
        loginHf.addParamValue("hasMatrixCard", "0");
        loginHf.addParamValue("hasToken", "0");
        loginHf.addParamValue("idContent", data.get("pemail"));
        loginHf.addParamValue("local_cpu", "");
        loginHf.addParamValue("local_disk", "");
        loginHf.addParamValue("local_network", "");
        loginHf.addParamValue("locale", "");
        loginHf.addParamValue("loginTypeTab", "0");
        loginHf.addParamValue("memberLoginType", "0");
        loginHf.addParamValue("pkiPassword", "");
        loginHf.addParamValue("pkiSerialNo", "");
        loginHf.addParamValue("pkiSignedData", "");
        loginHf.addParamValue("pkiVersion", "");
        loginHf.addParamValue("productCode", "");
        loginHf.addParamValue("url", "");
        loginHf.addParamValue("pageNo", "1");
        loginHf.addParamValue("password", encryptPassord);//data.get("webLoginWord"));
        loginHf.addParamValue("pkiMemberIsCertMember", "true");
        loginHf.addParamValue("REFRESH_CODE", REFRESH_CODE);
        System.err.println("REFRESH_CODE2" + REFRESH_CODE);
        loginHf.addParamValue("site2pstoretoken", site2pstoretoken);
        loginHf.addParamValue("userID", "admin");
        loginHf.addParamValue("v", "v1.4");
        loginHf.Post();
        System.out.println(loginHf.getResponseBody());

        loginHf.nextRequest();
        loginHf.setUrl("https://www.99bill.com/website/myaccount/myacctinfo.htm");
        loginHf.Get();
//		System.out.println(loginHf.getResponseBody());

        loginHf.nextRequest();
        loginHf.setUrl("https://www.99bill.com/website/common/manageSimpleInfo.htm?dojo.preventCache=1414670269802");
        loginHf.Get();

        return loginHf;


    }

    @Override
    public boolean refundByMock(Map<String, String> data) {
        String orderId = data.get("orderId");
        HttpFixture refundHttp = new HttpFixture();
        int txOrder = new Random().nextInt(100000000);
        String postdate = DateUtil.getDateTimeFormat(new Date());
        String refundUrl = "http://192.168.64.51/qatest/ReturnApi/ReturnApi.php";
        String refundBody1 = "actionUrl=https://www.99bill.com/gatewayapi/receiveDrawbackAction" +
                ".do&actionMthd=post&merchant_id=10011478543&merchant_key=SQUAMIWMWMAT6ZYL&version" +
                "=bill_drawback_api_1&command_type=001&txOrder=" + txOrder + "&amount=100&postdate=" + postdate +
                "&orderid=" + orderId + "&payby99bill=%E7%94%9F%E6%88%90%E6%95%B0%E6%8D%AE";
        refundHttp.setUrl(refundUrl);
        refundHttp.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        refundHttp.addRequestBody(refundBody1);
        // 执行生成数据
        refundHttp.Post();
        String responseBody = refundHttp.getResponseBody();
        Document document = Jsoup.parse(responseBody);
        String mac = document.select("input[name=mac]").attr("value");

        refundHttp.nextRequest();
        refundHttp.setUrl("https://www.99bill.com/gatewayapi/receiveDrawbackAction.do");
        refundHttp.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        String refundBody2 = "merchant_id=10011478543&version=bill_drawback_api_1&command_type=001&txOrder=" +
                txOrder + "&amount=100&postdate=" + postdate + "&orderid=" + orderId +
                "&mac=" + mac + "&payby99bill=%E6%89%A7%E8%A1%8C%E9%80%80%E6%AC%BE";
        refundHttp.addRequestBody(refundBody2);
        // 执行退款
        refundHttp.Post();
        return refundHttp.findStringinResponse("<RESULT>Y</RESULT>") || refundHttp.findStringinResponse
                ("<result>10</result>");
    }

    public void test() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("pemail", "jiang@qa.99bill.com");
        data.put("randomValidateCode", "8888");
        data.put("webLoginWord", "`1111111");
        parametersInitialization = new ParametersInitialization();
        parametersInitialization.setAuthenticationUrl("https://ssoqa.99bill.com/sso/login/authentication1.htm");
        for (int i = 0; i < 100; i++) {
            String ret = bill99WebSiteLoginSubmit(data).getResponseBody();
            Thread.sleep(1000);
            if (ret.contains("qq")) {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        DoSubmitImpl doSumit = new DoSubmitImpl();

        doSumit.test();
    }

//	public HttpFixture doWebsiteRefund(Map<String, String> data){
//		HttpFixture x = new HttpFixture();
//		return x;
//	};

}
