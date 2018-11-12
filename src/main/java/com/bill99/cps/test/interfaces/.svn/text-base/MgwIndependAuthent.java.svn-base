package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.common.tools.XmlParse;
import com.bill99.cps.orm.AccessDB;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;

public class MgwIndependAuthent extends BaseTestCase {
	@Autowired
	@Qualifier("independAuthentService")
	private HttpContentService independAuthentService;

	@Autowired
	@Qualifier("independAuthentVerifyServiceImpl")
	private HttpContentService independAuthentVerifyServiceImpl;

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;

	@Autowired
	private AccessDB accessDB;

	@Autowired
	private DBOracleCheck dbOracleCheck;

	private String token = "";
	private String validCode = "";
	private String externalRefNumber = "";
	private String extNo="";

	@Test(dataProvider = "independAuth", description = "独立鉴权申请")
	public void independAuthApplyTest(Map<String, String> datadriven) throws Exception {
		Reporter.start(datadriven.get("comment"));

		MgwItem mgwItem=converMgwDataDriverService.ConverData(datadriven);
		externalRefNumber = mgwItem.getExternalRefNumber();
		// http 发送报文
		String respose = independAuthentService.cnpContent(mgwItem);
		// 实际结果与预期结果比较
		Reporter.log(datadriven.get("comment"), respose.contains(datadriven.get("exceptedmessage")));

		String resprefnumberResult = "";

		resprefnumberResult = XmlParse.getResponse(respose, "responseCode");
		Reporter.log("获取交易返回码=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取token 值
			if ("00".equals(resprefnumberResult)) {
					token = XmlParse.getResponse(respose, "token");
					//验证落地数据
				    extNo = XmlParse.getResponse(respose, "externalRefNumber");
				    Reporter.log("t_txn_ctrl.ext_trace_no : "+extNo);
				    String typeString= accessDB.getTxnDBInfo(extNo).getTxnType();
				    String flagString=accessDB.getTxnDBInfo(extNo).getTxnFlg();

					Reporter.log("TXN_TYPE: "+typeString,typeString.equals(datadriven.get("DB_txn_type")));
					Reporter.log("TXN_Flag: "+flagString,flagString.equals(datadriven.get("DB_txn_flg")));

					//数据库捞出短信验证码validCode
					Thread.sleep(5000);
//					validCode=accessDB.getvalidCode(mgwItem.getPhoneNO());
					validCode=dbOracleCheck.getValidCode(extNo, mgwItem.getPhoneNO());
					Reporter.log("validCode: "+validCode);
		}
		Reporter.end(datadriven.get("comment") +"validCode: "+validCode+"; token: "+token);
	}


	 @Test(dataProvider = "independAuthVerify", description = "独立鉴权短信验证", dependsOnMethods="independAuthApplyTest")
	public void independAuthVerifyTest(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));
		// http 发送报文
		MgwItem itemData =converMgwDataDriverService.ConverData(datadriven);

		itemData.setExternalRefNumber(externalRefNumber);
		itemData.setValidCode(validCode);
		itemData.setToken(token);

		String respose = independAuthentVerifyServiceImpl.cnpContent(itemData);

		Reporter.log("respose=" + respose);

		// 实际结果与预期结果比较
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);


		Reporter.log("TXN_Flag",accessDB.getTxnDBInfo(extNo).getTxnFlg().equals("S"));

	 }


	@DataProvider(name = "independAuth")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "independAuth");
	}


	@DataProvider(name = "independAuthVerify")
	public Iterator<Object[]> data2test() throws IOException {
		return ExcelProviderByEnv(this, "independAuthVerify");
	}
}
