package com.bill99.ate.test;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.ate.common.httpclient.HttpClientUtil;
import com.bill99.ate.orm.AteDb;
import com.bill99.ate.service.ate.check.AteDbCheck;
import com.bill99.ate.service.ate.check.AteResultCheck;
import com.bill99.ate.service.ate.mockHttp.ChooseFunctionPage;
import com.bill99.ate.service.ate.mockHttp.RefundHttpRequest;
import com.bill99.cps.service.check.CpsDbCheck;
import com.bill99.mcs.service.McsService;
import com.bill99.qa.framework.testcase.BaseTestCase;

public class BDCA_AteRefundTest extends BaseTestCase {
	
	/**
	 * 
	 * @Title:Ate退货测试类
	 * @Description:ATE1.9-CPS2.6-MCS2.4
	 * @Author:mingjun.li
	 * @Since:2016年11月1日
	 * @Version:1.1.0
	 */

	@Resource
	private RefundHttpRequest refundHttpRequest;

	@Resource
	private AteDbCheck ateDbCheck;

	@Resource
	private AteDb ateDb;
	
	@Resource
	private McsService mcsService;
	
	@Resource
	private CpsDbCheck cpsDbCheck;

	@BeforeClass
	public void beforeTest() {
		ChooseFunctionPage.enterMdpCenter();
	}

	@Test(dataProvider = "testBDCAAteRefund", description = "BDCA退货(http 集成)")
	public void testBDCAAteRefund(Map<String, String> data) throws InterruptedException {
		Reporter.start("第" + data.get("seq") + "测试:" + data.get("comment") + "开始");
		//查找一笔可退货交易
		data = setRefundInfo(data);
		//退货
		String result = refundHttpRequest.submitRefund(data);
		//页面检查
		AteResultCheck.uiPayCheck(result, data);
		// 退货数据库检查
		if (AteDbCheck.isCheckDb) {
			ateDbCheck.refundDbCheck(data);
		}
		
		//获取流水表中REF_ID_CTRL,关联CPS表
		String idTxnCtrl = ateDbCheck.getRefIdCtrl();
		System.out.println("+++++ATE落CPS的交易控制编号+++++++"+idTxnCtrl);
		
		String merchantCode = data.get("merchantCode");

		// 2.CPS
		// CPS交易落表检查
		if(idTxnCtrl!=null && idTxnCtrl.length()>5){
		String idTxn = cpsDbCheck.IdTxnCtrl2IdTxn(idTxnCtrl);

		// 3.MCS
		// 获取ref_id_txn，执行清结算
		Reporter.log("结算商户号：" + merchantCode + ",交易编号：" + idTxn);
		boolean mcs = mcsService.clearAndSettle(merchantCode, idTxn);
		// 结算明细表状态为2，结算指令执行成功，mcs清结算通过
		Reporter.log("MCS清结算验证通过,商户号：" + merchantCode + ",交易编号：" + idTxn,mcs);
		}
		else{
			Reporter.log("idTxnCtrl为空，不校验Cps和mcs");
		}
		

		Reporter.end("第" + data.get("seq") + "测试:" + data.get("comment") + "结束");
	}

	@DataProvider(name = "testBDCAAteRefund")
	private Iterator<Object[]> getData() {
		return ExcelProviderByEnv(this, "testBDCAAteRefund");
	}

	@AfterClass
	public void afterClass() {
		if (HttpClientUtil.getHttpClient() != null) {
			HttpClientUtil.closeHttpClient();
		}
	}

	/*
	 * 将可退货交易字段放入data
	 */
	private Map<String, String> setRefundInfo(Map<String, String> data) {
		//查找一笔可退货交易
		Map<String, String> refundOrder = ateDb.getRefund(data.get("payMode"));
		//退货所需字段
		data.put("outTradeNo", refundOrder.get("OUT_TRADE_NO"));
		data.put("merchantCode", refundOrder.get("MERCHANT_CODE"));
		data.put("refundAmount", refundOrder.get("PAY_AMOUNT"));
		data.put("channelType", refundOrder.get("CHANNEL_TYPE"));
		return data;
	}

}
