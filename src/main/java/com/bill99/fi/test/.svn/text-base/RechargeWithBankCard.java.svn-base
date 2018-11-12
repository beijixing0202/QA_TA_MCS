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
import com.bill99.fi.orm.mng.GatewayDbCheck;
import com.bill99.fi.service.payments.PaymentController;
import com.bill99.fi.service.recharge.impl.ChargePageSubmitImpl;

public class RechargeWithBankCard extends BaseTestCase {

	@Autowired
	private DoSubmit bill99WebSiteLoginSubmit;
	@Autowired
	private PaymentController paymentController;
	@Autowired
	private GatewayDbCheck gatewayDbCheck;

	/*
	 * @Autowired private ChargePageSubmitImpl chargePageSubmitImpl;
	 */

	@Test(description = "银行卡充值", dataProvider = "rechargeWithBankCard", timeOut = 6000000, enabled = true)
	public void rechargeWithBankCard(Map<String, String> data) throws Exception {
		// 添加一些字段的默认值
		// ParameterDispose.addDefaultValue(data);
		HttpFixture loginHf = bill99WebSiteLoginSubmit.bill99WebSiteLoginSubmit(data);
		Reporter.log("website 登陆成功", loginHf.getResponseBody().contains(data.get("pname") + "，欢迎您！"));

		String orderNO = new ChargePageSubmitImpl().bankOnLineCharge(loginHf, data);
		data.put("orgorderID", orderNO);
		System.out.println("充值金额：" + data.get("depositAmount_format"));
		System.out.println("data:"+data);
		boolean payResult = paymentController.reChargeDemoBank(loginHf, data);
		// 如果支付成功，进行数据库检查
		if (payResult) {
			data.put("seqId", gatewayDbCheck.getDealidByOrgorderid(data).getSequenceid());
			System.out.println("data"+data);
			Reporter.log(data.get("name") + "支付成功，数据库校验成功", gatewayDbCheck.checkGatewayDealByOrgOrderId(data, 2) && gatewayDbCheck.checkAcctItermsRecharge(data));
		} else {
			Reporter.log(data.get("name") + "支付失败", payResult);
		}
		
		
	}

	@DataProvider(name = "rechargeWithBankCard")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "rechargeWithBankCard");
	}

}
