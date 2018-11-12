package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.common.tools.ExcelProvider;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**日确认交易查询
 * @author kaiquan.jiang
 *
 */
public class MgwQueryConfirmTxn extends BaseTestCase{
	@Autowired
	@Qualifier("confirmtxnQueryService")
	private HttpContentService confirmtxnQueryService;
	

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	
	@Test(dataProvider = "mgwconfirmtxnquery",description="日确认交易查询",timeOut=120000)
  public void confirmtxnquery(Map<String, String> datadriven) {
		//全要素提交
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		String confirmtxnqueryrespString = confirmtxnQueryService.cnpContent(mgwItem);
		Reporter.log(confirmtxnqueryrespString);
		
		boolean a1 = confirmtxnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a1);
		
		//externalRefNumber+txnType+merchantId
		
	}		
		 @DataProvider(name = "mgwconfirmtxnquery")
			public Iterator<Object[]> data1test() throws IOException {
				return ExcelProviderByEnv(this, "mgwconfirmtxnquery");
			}
}
