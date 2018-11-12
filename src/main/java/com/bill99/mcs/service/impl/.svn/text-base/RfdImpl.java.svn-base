package com.bill99.mcs.service.impl;

import com.bill99.mcs.common.helper.DealType;
import com.bill99.mcs.common.helper.ParameterSource;
import com.bill99.mcs.common.helper.SubmitUrl;
import com.bill99.mcs.service.MockLoginService;
import com.bill99.mcs.service.Rfd;
import com.bill99.mcs.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
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
public class RfdImpl implements Rfd {
    private DateUtils dateUtils = new DateUtils();
    private Calendar cal = Calendar.getInstance();
    private String submitUrl;
    @Autowired
    private MockLoginService mockLoginService;

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    @Override
    public Map<String, String> getRfdData(Map<String, String> data) {
        HttpFixture request = mockLoginService.mockLoginRequest("admin", "ateMock");
        if(DealType.CPS_CNP.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            submitUrl = SubmitUrl.CPS_URL;
            return post(request, data, submitUrl, ParameterSource.CPS_CNP);
        }
        else if (DealType.ATE_RFD.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            submitUrl = SubmitUrl.ATE_URL;
            return get(request, data, submitUrl, ParameterSource.ATE_RFD);
        }
        else if(DealType.PIX_CUP_RFD.getEntryType().equalsIgnoreCase(data.get("mockType"))){
            submitUrl = SubmitUrl.PIX_URL;
            return get(request, data, submitUrl, ParameterSource.PIX_CUP_RFD);
        }
        else {
            return null;
        }
    }


    private Map<String, String> get(HttpFixture request, Map<String, String> data, String requestUrl, String[] requestParm) {
        StringBuilder srcRequest = new StringBuilder();
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
    private Map<String, String> post(HttpFixture request, Map<String, String> data, String submitUrl, String[] requestParm) {
        request.nextRequest();
        for (String key : requestParm) {
            if (null != data.get(key) && !data.get(key).equals("")) {
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
        String regEx_RRN = "RRN=\\[(.*?)\\]";
        Pattern patternRRN = Pattern.compile(regEx_RRN);
        Matcher matcherRRN = patternRRN.matcher(response);
        String regEx_IsCPS = "responseTextMessage=\\[(.*?)\\]";
        Pattern patternIsCPS = Pattern.compile(regEx_IsCPS);
        Matcher matcherIsCPS = patternIsCPS.matcher(response);

        if (matcherIsATE.find()) {
            String errorInfo = matcherIsATE.group(1);
            if (errorInfo.equals("成功")) {
                responseData.put("billOrderNo", matcherBillOrderNo.group(1) == null?null:matcherBillOrderNo.group(1));
            }
            else {
                Reporter.FALSE("交易请求失败,请检查.");
            }
        }
        else if (matcherIsCPS.find()){
            String errorInfo = matcherIsCPS.group(1);
            if (errorInfo.equals("交易成功")) {
                responseData.put("idTxn", matcherRRN.group(1) == null?null:matcherRRN.group(1));
            }
            else {
                Reporter.FALSE("交易请求失败,请检查.");
            }
        }
        Reporter.log(responseData.toString());
        System.err.println(responseData);
        return responseData;
    }
}
