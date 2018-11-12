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

/**商户受理能力查询
 * @author kaiquan.jiang
 *
 */
public class MgwQueryAcceptCapacity extends BaseTestCase{
	@Autowired
	@Qualifier("acceptcapacityQueryService")
	private HttpContentService acceptcapacityQueryService;
	

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	
	@Test(dataProvider = "mgwacceptcapacityquery" ,description="商户受理能力查询流程",timeOut=120000)
  public void settletxnquery(Map<String, String> datadriven) {
		//全要素提交
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		String acceptcapacityqueryrespString = acceptcapacityQueryService.cnpContent(mgwItem);
		Reporter.log(acceptcapacityqueryrespString);
		
		boolean a1 = acceptcapacityqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a1);

		//externalRefNumber+txnType+merchantId
		
	}		
		 @DataProvider(name = "mgwacceptcapacityquery")
			public Iterator<Object[]> data1test() throws IOException {
				return ExcelProviderByEnv(this, "mgwacceptcapacityquery");
			}
}
