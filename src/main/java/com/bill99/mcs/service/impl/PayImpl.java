package com.bill99.mcs.service.impl;

import com.bill99.mcs.common.helper.DealType;
import com.bill99.mcs.common.helper.ParameterSource;
import com.bill99.mcs.common.helper.SubmitUrl;
import com.bill99.mcs.common.helper.UnionpayCode;
import com.bill99.mcs.service.MockLoginService;
import com.bill99.mcs.service.Pay;
import com.bill99.mcs.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.json.JSONObject;
import shelper.iffixture.HttpFixture;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wentao.jia on 2018/9/20.
 */
public class PayImpl  implements Pay {

    private DateUtils dateUtils = new DateUtils();
    private Calendar cal = Calendar.getInstance();
    private String submitUrl;
    @Autowired
    private MockLoginService mockLoginService;
    @Autowired
    private UnionpayCode unionpayCode;

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    @Override
    public Map<String, String> getDealData(Map<String, String> data) {
        System.err.println("交易类型为："+data.get("mockType"));
        HttpFixture request = mockLoginService.mockLoginRequest("admin", "ateMock");
        if(DealType.CPS_CNP.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            System.err.println("in it~~~~~~~~~~~~~~~~~~~~~~~");
            submitUrl = SubmitUrl.CPS_URL;
            return post(request, data, submitUrl, ParameterSource.CPS_CNP);
        }
        else if (DealType.ATE_PAY.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            data.put("functionCode","10");
            submitUrl = SubmitUrl.ATE_URL;
            return get(request, data, submitUrl, ParameterSource.ATE_PAY);
        }
        else if (DealType.ACCT_DEPOSIT.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            data.put("functionCode","14");
            submitUrl = SubmitUrl.ATE_URL;
            return get(request, data, submitUrl, ParameterSource.ACCT_DEPOSIT);
        }
        else if(DealType.PIX_CUP.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            submitUrl = SubmitUrl.PIX_URL;
            return get(request, data, submitUrl, ParameterSource.PIX_CUP);
        }
        else {
            return null;
        }
    }


    private Map<String, String> get(HttpFixture request, Map<String, String> data, String requestUrl, String[] requestParm) {
        StringBuilder srcRequest = new StringBuilder();

        if(DealType.PIX_CUP.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            HashMap jsonMap =new  HashMap();
            for (String key : requestParm) {
                 if (null != data.get(key) && !data.get(key).equals("")) {
                    jsonMap.put(key,data.get(key));
                }
            }
            jsonMap.put("termTraceNo",dateUtils.getTimeStamp());
            jsonMap.put("externalTraceNo",dateUtils.getTimeStamp());
            jsonMap.put("dimensionCode",unionpayCode.getUnionpayCode());
            //jsonMap.put("dimensionCode","6221337132261688128");
            String json = new JSONObject(jsonMap).toString();
            System.err.print("进入PIX交易");
            request.nextRequest();
            request.addHeaderValue("Content-Type", "application/json;charset=UTF-8");
            request.setUrl(SubmitUrl.PIX_URL_Post);
            request.addRequestBody(json);
            request.Post();
            String response = request.getResponseBody();
            Reporter.log("交易返回报文：" + sliceResponse(response));
            return sliceResponse(response);

        }else{
            for (String key : requestParm) {
                if (null != data.get(key) && !data.get(key).equals("")) {
                    String temp = key + "=" + data.get(key) + "&";
                    srcRequest.append(temp);
                }
            }
            requestUrl = requestUrl + "?" + srcRequest.substring(0, srcRequest.length() - 1);
            Reporter.log("交易请求Url为:" + requestUrl);
            request.nextRequest();
            request.setUrl(requestUrl);
            request.Get();
            String response = request.getResponseBody();
            Reporter.log("交易返回报文：" + response);
            return sliceResponse(response);
        }

    }
    private Map<String, String> post(HttpFixture request, Map<String, String> data, String submitUrl, String[] requestParm) {
        request.nextRequest();
        System.err.println("参数列表为："+requestParm);
        for (String key : requestParm) {
            if (null != data.get(key) && !data.get(key).equals("")) {
                System.err.println("这个参数是："+key+"值是："+data.get(key));
                request.addParamValue(key,data.get(key));
            }
        }
        request.addParamValue("serviceChannelTraceNo",dateUtils.getTimeStamp());
        SimpleDateFormat df = new SimpleDateFormat("");
        String date = df.format(cal.getTime());
        request.addParamValue("entryTime",dateUtils.format(cal.getTime(),"yyyy-MM-dd HH:mm:ss"));
        Reporter.log("交易请求Url为:" + submitUrl);
        request.setUrl(submitUrl);
        request.Post();
        String response = request.getResponseBody();
        Reporter.log("交易返回报文：" + response);
        return sliceResponse(response);
    }

    private Map<String, String> sliceResponse(String response) {
        Map<String, String> responseData = new HashMap<String, String>();
        String regEx_IsATE = "errorInfo\":\"(.*?)\"";
        Pattern patternIsATE = Pattern.compile(regEx_IsATE);
        Matcher matcherIsATE = patternIsATE.matcher(response);
        String regEx_BillOrderNo = "billOrderNo\":\"(.*?)\"";
        Pattern patternBillOrderNo = Pattern.compile(regEx_BillOrderNo);
        Matcher matcherBillOrderNo = patternBillOrderNo.matcher(response);
        
        String regEx_TradeId= "tradeId\":\"(.*?)\".*?\"refIdTxn\":\"(.*?)\"";
        Pattern patternTradeId = Pattern.compile(regEx_TradeId);
        Matcher matcherTradeId = patternTradeId.matcher(response);
        
        String regEx_RRN = "RRN=\\[(.*?)\\]";
        Pattern patternRRN = Pattern.compile(regEx_RRN);
        Matcher matcherRRN = patternRRN.matcher(response);
        String regEx_IsCPS = "responseTextMessage=\\[(.*?)\\]";
        Pattern patternIsCPS = Pattern.compile(regEx_IsCPS);
        Matcher matcherIsCPS = patternIsCPS.matcher(response);

        String regEx_idBiz = "responseMessage\":\"(.*?)\".*?\"idBiz\":\"(.*?)\"";
        Pattern patternidBiz = Pattern.compile(regEx_idBiz);
        Matcher matcheridBiz = patternidBiz.matcher(response);


        if (matcherIsATE.find()) {
            String errorInfo = matcherIsATE.group(1);
            if (errorInfo.equals("成功")&&matcherBillOrderNo.find()) {
                responseData.put("billOrderNo", matcherBillOrderNo.group(1) == null?null:matcherBillOrderNo.group(1));
                if(matcherTradeId.find()){
                	responseData.put("tradeId", matcherTradeId.group(1) == null?null:matcherTradeId.group(1));
                    responseData.put("refIdTxn", matcherTradeId.group(2) == null?null:matcherTradeId.group(2));
                }
                
            }
            else {
                Reporter.FALSE("交易请求失败,请检查.");
            }
        }
        else if (matcherIsCPS.find()){
            String errorInfo = matcherIsCPS.group(1);
            if (errorInfo.equals("交易成功")&&matcherRRN.find()) {
                responseData.put("idTxn", matcherRRN.group(1) == null?null:matcherRRN.group(1));
            }
            else {
                Reporter.FALSE("交易请求失败,请检查.");
            }
        }
        else if(matcheridBiz.find()){
            if(matcheridBiz.group(1).equals("正在处理中")){
                responseData.put("idTxn",matcheridBiz.group(2));
            }else{
                Reporter.FALSE("交易请求失败,请检查.");
            }
        }
        Reporter.log(responseData.toString());
        System.err.println(responseData);
        return responseData;
    }
}