package com.bill99.fi.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shelper.iffixture.HttpFixture;

import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.fi.access.http.DoSubmit;
import com.bill99.fi.common.helper.ParameterDispose;
import com.bill99.fi.common.helper.ParameterSignMsg;
import com.bill99.fi.common.helper.ParameterSource;
import com.bill99.fi.orm.dao.LogDbCheck;
import com.bill99.fi.orm.mng.GatewayDbCheck;
import com.bill99.fi.service.gateway.GatewayPageSubmit;
import com.bill99.fi.service.payments.PaymentController;
import com.bill99.fi.service.refund.impl.WebsiteSingleRfdImpl;

public class SingleRfdPrepaidCardPay extends BaseTestCase {
    @Autowired
    private DoSubmit bill99WebSiteLoginSubmit;
    @Autowired
    private DoSubmit doSubmit;
    @Autowired
    private GatewayPageSubmit gatewayPageSubmit;
    @Autowired
    private PaymentController paymentController;
    @Autowired
    private GatewayDbCheck gatewayDbCheck;
    @Autowired
    private ParameterSignMsg parameterSignMsg;
    @Autowired
    private LogDbCheck logDbCheck;


    @Test(description = "网关单笔退款-预付卡支付", dataProvider = "singleRfdPrepaidCardPay", timeOut = 180000, enabled = true)
    public void singleRfdPrepaidCardPay(Map<String, String> data) throws Exception {
        Reporter.start("当前测试--------：" + data.get("name") + "开始！");
        // 添加一些字段的默认值
        ParameterDispose.addDefaultValue(data);
        if (data.get("signMsg").equals("")) {

            data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.gatewayParameter, data));
        }

        // 网关提交
        HttpFixture query = doSubmit.gatewaySubmit(data);
        //选择支付方式
        gatewayPageSubmit.selectPayType(query, data);

        // 输入卡信息支付，及检查商户通知页面是否返回成功
        boolean payResult = paymentController.payPrepaidCard(query, data);

        // 如果支付成功，进行数据库检查 数据库和商户通知
        if (payResult) {
            Reporter.log("网关预付卡支付数据库检查结果",
                    gatewayDbCheck.checkGatewayCNPDeal(data, 1) && logDbCheck.notifyLogDbCkeck(data));
        } else {
            Reporter.log("网关预付卡支付", payResult);
        }
//		// website登录
//		HttpFixture loginHf = bill99WebSiteLoginSubmit.bill99WebSiteLoginSubmit(data);
//		Reporter.log("website 登陆成功",loginHf.getResponseBody().contains(data.get("pname")+"，欢迎您！"));
//
//		//单笔退款申请
//		new WebsiteSingleRfdImpl().singleRfd(loginHf, data);

        boolean refundResult = doSubmit.refundByMock(data);
        String result = refundResult ? "成功" : "失败";
        Reporter.log("Mock退款结果：" + result, refundResult);
        //退款数据库检查
        String orderId;
        orderId = gatewayDbCheck.getRefundOrderIdBySeqId(gatewayDbCheck.getSequenceidByOrderid(data).getSequenceid());
        data.put("orderId", orderId);
        Reporter.log("退款数据库检查结果", gatewayDbCheck.checkGatewayCNPDeal(data, 1));

        Reporter.end("当前测试--------：" + data.get("name") + "结束！");
    }

    @DataProvider(name = "singleRfdPrepaidCardPay")
    public Iterator<Object[]> data4test() throws IOException {
        return ExcelProviderByEnv(this, "singleRfdPrepaidCardPay");
    }
}