package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.common.tools.ExcelProvider;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**cnp 交易查询
 * @author kaiquan.jiang
 *
 */
public class MgwTxnQuery extends BaseTestCase{
  
	

	@Autowired
	@Qualifier("txnQueryService")
	private HttpContentService txnQueryService;
	

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	
	
	@Test(dataProvider = "mgwtxnquery",description="cnp 交易查询",timeOut=120000)
  public void txnquery(Map<String, String> datadriven) {
		//全要素提交
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		String txnqueryrespString = txnQueryService.cnpContent(mgwItem);
		Reporter.log(txnqueryrespString);
		
		boolean a1 = txnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a1);
		
		//externalRefNumber+txnType+merchantId
		mgwItem.setRefNumber("");
		mgwItem.setTerminalId("");
		mgwItem.setSettleMerchantId("");
		mgwItem.setTxnStatus("");
		
		MgwItem mgwItem1 = converMgwDataDriverService.ConverData(datadriven);
		String txnqueryrespString1 = txnQueryService.cnpContent(mgwItem1);
		Reporter.log(txnqueryrespString1);
		
		boolean a2 = txnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a2);
		
		//refNumber+txnType+merchantId
		mgwItem.setExternalRefNumber("");
		mgwItem.setTerminalId("");
		mgwItem.setSettleMerchantId("");
		mgwItem.setTxnStatus("");
		
		MgwItem mgwItem2 = converMgwDataDriverService.ConverData(datadriven);
		String txnqueryrespString2= txnQueryService.cnpContent(mgwItem2);
		Reporter.log(txnqueryrespString2);
		
		boolean a3 = txnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a3);
		
		//externalRefNumber+txnType+merchantId+txnStatus
		mgwItem.setRefNumber("");
		mgwItem.setTerminalId("");
		mgwItem.setSettleMerchantId("");
		
		MgwItem mgwItem3 = converMgwDataDriverService.ConverData(datadriven);
		String txnqueryrespString3= txnQueryService.cnpContent(mgwItem3);
		Reporter.log(txnqueryrespString3);
		
		boolean a4 = txnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a4);
		
		//externalRefNumber+txnType+merchantId+terminalId
		mgwItem.setRefNumber("");
		mgwItem.setTxnStatus("");
		mgwItem.setSettleMerchantId("");
		
		MgwItem mgwItem4 = converMgwDataDriverService.ConverData(datadriven);
		String txnqueryrespString4 = txnQueryService.cnpContent(mgwItem4);
		Reporter.log(txnqueryrespString4);
		
		boolean a5 = txnqueryrespString.contains(datadriven.get("exceptedmessage"));
		Reporter.log(datadriven.get("comment"), a5);
		
  }

  @DataProvider(name = "mgwtxnquery")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "mgwtxnquery");
	}
}
