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

/**卡信息查询流程
 * @author kaiquan.jiang
 *
 */
public class MgwQueryCardInfo extends BaseTestCase{
	@Autowired
	@Qualifier("cardinfoQueryService")
	private HttpContentService cardinfoQueryService;
	

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	
	@Test(dataProvider = "mgwcardinfoquery",description="卡信息查询流程",timeOut=120000)
  public void cardinfoquery(Map<String, String> datadriven) {
		//全要素提交
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		String cardinfoqueryrespString = cardinfoQueryService.cnpContent(mgwItem);
		Reporter.log(cardinfoqueryrespString);
		
		boolean a1 = cardinfoqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a1);
		
		//externalRefNumber+txnType+merchantId
		
	}		
		 @DataProvider(name = "mgwcardinfoquery")
			public Iterator<Object[]> data1test() throws IOException {
				return ExcelProviderByEnv(this, "mgwcardinfoquery");
			}
}
