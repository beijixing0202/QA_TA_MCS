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

import com.bill99.cps.common.tools.ExcelProvider;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;


/**CCS导入正常流程
 * @author kaiquan.jiang
 *
 */
public class MgwCcsImport extends BaseTestCase {	
	
	@Autowired
	@Qualifier("httpContentService")
	private HttpContentService httpContentService;
	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	@Autowired
	private DBOracleCheck dbOracleCheck;
	
	
	// CCS导入
	@Test(dataProvider = "mgwCcsImport")
	public void ccsImport(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));
	// http 发送报文
		String respose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));
		
		Reporter.log("respose=" + respose);
		
	// 实际结果与预期结果比较
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));
		
		Reporter.log(datadriven.get("comment"), a1);
	
	// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，数据库检查
		if (a1 && datadriven.get("comment").equalsIgnoreCase("CCS导入正常流程")) {
			
			String b3 = dbOracleCheck.CCSPurchaseDBcheck(datadriven.get("externalRefNumber"));
			
			System.out.println(b3);
			
			if (b3.contains(datadriven.get("excepted_order_txn_info"))
					&& b3.contains(datadriven.get("excepted_txn_flg"))
					&& b3.contains(datadriven.get("excepted_rep_code"))) {
				Reporter.TRUE("RRRRR数据库检查正常");
			} else {
				Reporter.FALSE("NNNNN数据库检查异常");
			}
		}
		Reporter.end(datadriven.get("comment"));
	}
			



@DataProvider(name = "mgwCcsImport")
public Iterator<Object[]> data1test() throws IOException {
	return ExcelProviderByEnv(this, "mgwCcsImport");
}

@AfterClass
public void afterClass() {
}

}