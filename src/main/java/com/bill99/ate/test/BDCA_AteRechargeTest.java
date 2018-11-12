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
import com.bill99.ate.pageObject.HessianUrl;
import com.bill99.ate.service.ate.check.AteDbCheck;
import com.bill99.ate.service.ate.check.AteResultCheck;
import com.bill99.ate.service.ate.mockHttp.ChooseFunctionPage;
import com.bill99.ate.service.ate.mockHttp.RechargeHttpRequest;
import com.bill99.ate.service.ate.mockHttp.WithDrawHttpRequest;
import com.bill99.ate.service.rm.QuatoService;
import com.bill99.cps.service.check.CpsDbCheck;
import com.bill99.mcs.service.McsService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**
 * 
 * @Title:充值测试类
 * @Description:ATE3.2-CPS2.4-MCS2.20
 * @Author:mingjun.li
 * @Since:2016年11月1日
 * @Version:1.1.0
 */
public class BDCA_AteRechargeTest extends BaseTestCase {

	@Resource
	private RechargeHttpRequest rechargeHttpRequest;
	
	@Resource
	private WithDrawHttpRequest withDrawHttpRequest;

	@Resource
	private AteDbCheck ateDbCheck;
	
	@Resource
	private QuatoService quatoService;
	
	@Resource
	private McsService mcsService;

	@Resource
	private CpsDbCheck cpsDbCheck;

	@BeforeClass
	public void beforeTest() {
		ChooseFunctionPage.enterMdpCenter();
	}

	@Test(dataProvider = "testBDCAAteRecharge", description = "支付账户充值(http 集成)")
	public void testBDCAAteRecharge(Map<String, String> data)
			throws InterruptedException {
			//删除充值  转账限额
			quatoService.deleteAllCache(data.get("memberCode"));
			Reporter.start("第" + data.get("seq") + "测试:" + data.get("comment") + "开始");
			// 充值
			String result = rechargeHttpRequest.submitRecharge(data);
			Map<String, Object> response = AteResultCheck.uiPayCheck(result, data);
			boolean isSuccess = AteResultCheck.compareString(String.valueOf(response.get("resultCode")), "1");
			// 充值数据库检查
			if (AteDbCheck.isCheckDb) {
				ateDbCheck.rechargeDbCheck(data, isSuccess);
			}
			if (isSuccess) {
				//重置outTradeNo
				data.put("outTradeNo","1");
				data.put("orderType", "250002");
				//提现
				String withDrawResult = withDrawHttpRequest.withDraw(data);
				AteResultCheck.uiWithDrawCheck(withDrawResult, data);
				if (AteDbCheck.isCheckDb) {
					ateDbCheck.withDrawDbCheck(data);
				}
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
			
			
			//恢复outTradeNo
			data.put("outTradeNo", "1");
			Reporter.end("第" + data.get("seq") + "测试:" + data.get("comment") + "结束");
	}

	@DataProvider(name = "testBDCAAteRecharge")
	private Iterator<Object[]> getData() {
		return ExcelProviderByEnv(this, "testBDCAAteRecharge");
	}

	@AfterClass
	public void afterClass() {
		if (HttpClientUtil.getHttpClient() != null) {
			HttpClientUtil.closeHttpClient();
	}
}

}
