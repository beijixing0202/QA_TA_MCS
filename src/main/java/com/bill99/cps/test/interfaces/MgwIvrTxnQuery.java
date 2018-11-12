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

/**IVR交易查询正常流程
 * @author kaiquan.jiang
 *
 */
public class MgwIvrTxnQuery extends BaseTestCase{
  
	

	@Autowired
	@Qualifier("ivrtxnQueryService")
	private HttpContentService ivrtxnQueryService;
	

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	
	@Test(dataProvider = "mgwivrtxnquery" ,description="IVR交易查询正常流程",timeOut=120000)
  public void txnquery(Map<String, String> datadriven) {
		//全要素提交
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		String ivrtxnqueryrespString = ivrtxnQueryService.cnpContent(mgwItem);
		Reporter.log(ivrtxnqueryrespString);
		
		boolean a1 = ivrtxnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a1);
  }

  @DataProvider(name = "mgwivrtxnquery")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "mgwivrtxnquery");
	}
}
