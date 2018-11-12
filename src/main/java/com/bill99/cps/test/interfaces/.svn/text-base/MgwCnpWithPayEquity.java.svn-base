package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.orm.AccessDB;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**
 * 
 * @author tingting.xu
 * @description mgwcnp,合并支付正常流程
 */

public class MgwCnpWithPayEquity extends BaseTestCase {

	@Autowired
	@Qualifier("httpContentService")
	private HttpContentService httpContentService;
	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	@Autowired
	private DBOracleCheck dbOracleCheck;
	@Autowired
	private AccessDB accessDB;

	private String refNumber = "";
	private String cfmrefNumber = "";

	// pur 消费
	@Test(dataProvider = "CnpPurchasePUR", description = "CNP 消费", timeOut = 100000)
	public void cnpPur(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));
		// http 发送报文
		String respose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));

		Reporter.log("respose=" + respose);
		
		// 实际结果与预期结果比较
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);

		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(respose);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取交易返回码=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resprefnumberResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat1 = pattern1.matcher(respose);
				boolean result1 = mat1.find();
				if (result1) {
					refNumber = mat1.group();
				}
			}

			

			// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，数据库检查
			if (a1 && datadriven.get("comment").contains("正常流程")) {

				// 访问数据库，并得出查询结果
				// boolean b2 = dbOracleCheck.PurchaseDBcheck(datadriven.get("externalRefNumber"));

				String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));

				String[] splitresult = b3.split(";");
				String txn_flg = splitresult[0];
				String txn_type = splitresult[1];
				String auth_net_id = splitresult[4];
				
				if(datadriven.get("comment").contains("PUR简单SVC正常流程")){ 
					if(datadriven.get("excepted_auth_net_id").equals(auth_net_id)){
					Reporter.TRUE("数据库svc授权网络检查正常");
				}else {
					Reporter.FALSE("数据库svc授权网络检查异常,请检查授权网络配置");
				}
				}
				if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
					Reporter.TRUE("RRRRR数据库txn_flg检查正常");
				} else {
					Reporter.FALSE("NNNNN数据库txn_flg检查异常");
				}
				if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
					Reporter.TRUE("RRRRR数据库txn_type检查正常");
				} else {
					Reporter.FALSE("NNNNN数据库txn_type检查异常");
				}
			}
			Reporter.end(datadriven.get("comment"));
		}
	}

	

	@DataProvider(name = "CnpPurchasePUR")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchasePUR");
	}

	@AfterClass
	public void afterClass() {
	}

}
