package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.cps.orm.AccessDB;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**平安积分支付首次快捷
 * @author 杨彧
 *
 */

public class MgwAmtPointQpay1 extends BaseTestCase{

	@Autowired
	@Qualifier("httpContentService")
	private HttpContentService httpContentService;
	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	@Autowired
	private DBOracleCheck dbOracleCheck;
	@Autowired
	private AccessDB accessDB;

	// 平安积分支付
	@Test(dataProvider = "MgwAmtPointQpay1",description="平安积分支付首次快捷",timeOut=100000)
	
	public void amtPointQpay1(Map<String, String> datadriven){
		Reporter.start(datadriven.get("comment"));
		// http 发送报文
		String respose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));
		
		Reporter.log("respose=" + respose);
		
	// 实际结果与Reporter预期结果比较
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));
		
		Reporter.log(datadriven.get("comment"), a1);
		// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，数据库检查
				if (a1 && datadriven.get("comment").equalsIgnoreCase("平安积分支付首次快捷正常流程")) {
					
					String b2 = dbOracleCheck.MoasDBcheck(datadriven.get("externalRefNumber"));
					
					String[] splitresult = b2.split(";");
					String cmd_code=splitresult[0];
					String point_amt=splitresult[1];
					String moas_txn_type = splitresult[3];
					String moas_amt = splitresult[4];
					String moas_status = splitresult[5];
					 
					 if (cmd_code.equalsIgnoreCase(datadriven.get("excepted_cmd_code"))) {
							Reporter.TRUE("mas数据库moas表cmd_code检查正常");
						} else {
							Reporter.FALSE("mas数据库moas表cmd_code检查异常");
						}
						if (point_amt.equalsIgnoreCase(datadriven.get("excepted_point_amt"))) {
							Reporter.TRUE("mas数据库moas表point_amt检查正常");
						} else {
							Reporter.FALSE("mas数据库moas表point_amt检查异常");
						}
						if (moas_txn_type.equalsIgnoreCase(datadriven.get("moas_txn_type"))) {
							Reporter.TRUE("mas数据库moas表txn_type检查正常");
						} else {
							Reporter.FALSE("mas数据库moas表txn_type检查异常");
						}
						if (moas_amt.equalsIgnoreCase(datadriven.get("moas_amt"))) {
							Reporter.TRUE("mas数据库moas表amt检查正常");
						} else {
							Reporter.FALSE("mas数据库moas表pamt检查异常");
						}
						if (moas_status.equalsIgnoreCase(datadriven.get("moas_status"))) {
							Reporter.TRUE("mas数据库moas表status检查正常");
						} else {
							Reporter.FALSE("mas数据库moas表status检查异常");
						}
					
					String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));
					
					 String[] splitresult2 = b3.split(";");
					 String txn_flg=splitresult2[0];
					 String txn_type=splitresult2[1];
					 String app_type=splitresult2[2];
					
					if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
						Reporter.TRUE("mas数据库交易表txn_flg检查正常");
					} else {
						Reporter.FALSE("mas数据库交易表txn_flg检查异常");
					}
					if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
						Reporter.TRUE("mas数据库交易表txn_type检查正常");
					} else {
						Reporter.FALSE("mas数据库交易表txn_type检查异常");
					}
					if (app_type.equalsIgnoreCase(datadriven.get("excepted_app_type"))) {
						Reporter.TRUE("mas数据库交易表app_type检查正常");
					} else {
						Reporter.FALSE("mas数据库交易表app_type检查异常");
					}
				}
				Reporter.end(datadriven.get("comment"));
			}
					



		@DataProvider(name = "MgwAmtPointQpay1")
		public Iterator<Object[]> data1test() throws IOException {
			return ExcelProviderByEnv(this, "MgwAmtPointQpay1");
		}

		@AfterClass
		public void afterClass() {
		}

	}
