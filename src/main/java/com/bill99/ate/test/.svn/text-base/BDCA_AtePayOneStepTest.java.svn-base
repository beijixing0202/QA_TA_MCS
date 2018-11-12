package com.bill99.ate.test;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import antlr.StringUtils;

import com.bill99.ate.common.httpclient.HttpClientUtil;
import com.bill99.ate.pageObject.HessianUrl;
import com.bill99.ate.service.ate.check.AteDbCheck;
import com.bill99.ate.service.ate.check.AteResultCheck;
import com.bill99.ate.service.ate.mockHttp.ChooseFunctionPage;
import com.bill99.ate.service.ate.mockHttp.PayInterfaceHttpRequest;
import com.bill99.ate.service.ate.mockHttp.RevokeHttpRequest;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.check.CpsDbCheck;
import com.bill99.mcs.service.McsService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**
 * 
 *@Title:Ate 支付接口一步测试类
 *@Description:ATE1.1-CPS2.5-MCS2.2
 *@Author:mingjun.li
 *@Since:2016年11月1日
 *@Version:1.1.0
 */
public class BDCA_AtePayOneStepTest extends BaseTestCase {

	@Resource
	private PayInterfaceHttpRequest payInterfaceHttpRequest;

	@Resource
	private RevokeHttpRequest revokeHttpRequest;

	@Resource
	private AteDbCheck ateDbCheck;

	@Resource
	private McsService mcsService;

	@Resource
	private CpsDbCheck cpsDbCheck;

	@BeforeClass
	public void beforeTest() {
		ChooseFunctionPage.enterMdpCenter();
	}

	@Test(dataProvider = "testBDCAPayOneStep", description = "BDCA ATE支付接口一步(http 集成)")
	public void testBDCAPayOneStep(Map<String, String> data) throws InterruptedException {
		Reporter.start("Ate第" + data.get("seq") + "测试:" + data.get("comment") + "开始");
		//1.Ate 
		//支付接口一步
		String result = payInterfaceHttpRequest.submitPay(data);
		Map<String, Object> response = AteResultCheck.uiPayCheck(result, data);
		boolean isSuccess = AteResultCheck.compareString(String.valueOf(response.get("resultCode")), "1");
		//支付接口一步数据库检查
		if (AteDbCheck.isCheckDb) {
			ateDbCheck.payOneStepDbCheck(data, isSuccess);
		}
		//获取流水表中REF_ID_CTRL,关联CPS表
		String idTxnCtrl = ateDbCheck.getRefIdCtrl();
		System.out.println("+++++ATE落CPS的交易控制编号+++++++"+idTxnCtrl);
		//获取流水表中REF_ID_TXN关联CPS
		/*data.put("refIdTxn", ateDbCheck.getRefIdTxn(data.get("outTradeNo")));*/
		

		//String refIdTxn = data.get("refIdTxn");
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
		
		//ATE撤销
		if (isSuccess) {
			Thread.sleep(1000l);
			//撤销
			String revokeResult = revokeHttpRequest.submitRevoke(data);
			AteResultCheck.uiRevokeCheck(revokeResult, data);
			//撤销数据库检查
			if (AteDbCheck.isCheckDb) {
				ateDbCheck.revokeDbCheck(data);
			}
			String revokeIdTxnCtrl = ateDbCheck.getRefIdCtrl();
		}

	}

	@DataProvider(name = "testBDCAPayOneStep")
	private Iterator<Object[]> getData() {
		return ExcelProviderByEnv(this, "testBDCAPayOneStep");
	}

	@AfterClass
	public void afterClass() {
		if (HttpClientUtil.getHttpClient() != null) {
			HttpClientUtil.closeHttpClient();
		}
	}

}
