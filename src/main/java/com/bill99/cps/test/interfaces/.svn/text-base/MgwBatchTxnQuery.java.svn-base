package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.common.tools.ExcelProvider;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**批交易查询正常流程
 * @author kaiquan.jiang
 *
 */
public class MgwBatchTxnQuery extends BaseTestCase{
  
	

	@Autowired
	@Qualifier("batchtxnQueryService")
	private HttpContentService batchtxnQueryService;
	

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	
	@Test(dataProvider = "mgwbatchtxnquery")
  public void batchtxnquery(Map<String, String> datadriven) {
		//全要素提交
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		String batchtxnqueryrespString = batchtxnQueryService.cnpContent(mgwItem);
		Reporter.log(batchtxnqueryrespString);
		
		boolean a1 = batchtxnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a1);
		
		boolean a2 = batchtxnqueryrespString.contains(datadriven.get("exceptedmessage1"));
		Reporter.log(datadriven.get("comment"), a2);
		
		
		
  }

  @DataProvider(name = "mgwbatchtxnquery")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "mgwbatchtxnquery");
	}
}
