package com.bill99.ifs.test.interfaces;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.ifs.fcs.api.message.TxnInfo;
import com.bill99.ifs.service.CheckIfsDbService;
import com.bill99.ifs.service.RepayService;
import com.bill99.qa.framework.testcase.BaseTestCase;


public class RepayTest extends BaseTestCase{

	@Resource
	RepayService repayService;

	@Resource
	CheckIfsDbService checkIfsDbService;

	@Test(description = "还款",dataProvider="repayAll")
	public void repayAll(Map<String, String> data) throws Exception {
		Reporter.start(data.get("comment"));
		List<TxnInfo> txnlist=repayService.repayOne(data);
		if(!(txnlist.isEmpty()||txnlist==null)){
			if("all".equals(data.get("req_RepayType"))){
				Reporter.log(data.get("comment")+"数据库验证",checkIfsDbService.checkRepayAllService(txnlist.get(0)));
			}else {
				Reporter.log(data.get("comment")+"数据库验证",checkIfsDbService.checkRepayTermService(txnlist.get(0)));
			}

		}else {
			Reporter.log("没有需要部分还款的数据");
		}
//		TxnInfo txnInfo=new TxnInfo();
//		txnInfo.setCurrentStage(-1);
//		txnInfo.setDivide(true);
//		txnInfo.setRepayAmt(149000L);
//		txnInfo.setRepayInfo("");
//		txnInfo.setTradeId("D!@!1575224");
//		txnInfo.setTxnAcctNo("2000000110516321");
//		Reporter.log(data.get("comment")+"数据库验证",checkIfsDbService.checkRepayAllService(txnInfo));
		Reporter.end(data.get("comment"));
	}
	@DataProvider(name = "repayAll", parallel = false)
	public Iterator<Object[]> getData() {
		return ExcelProviderByEnv(this, "repayAll");
	}

	@BeforeClass
	public void beforeTest()  {

	}
	@AfterClass
	public void afterClass() {

	}
}
