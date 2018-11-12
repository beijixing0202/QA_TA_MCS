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

import com.bill99.cps.orm.AccessDB;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**鉴权CNP
 * @author kaiquan.jiang
 *
 */
public class MgwAuthAndCnp extends BaseTestCase {
	
	@Autowired
	@Qualifier("httpContentService")
	private HttpContentService httpContentService;
	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	@Autowired
	private DBOracleCheck dbOracleCheck;
	@Autowired
	private AccessDB accessDB;
	
  @Test(dataProvider = "AuthAndCnp" ,description="鉴权CNP",timeOut=120000)
  public void authandcnp(Map<String, String> datadriven)  {
	  
	  Reporter.start(datadriven.get("comment"));
	  
	  String respose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));
	  
	  Reporter.log("respose=" + respose);
	  
	  String resprefnumberResult ="";
		
	  Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");  
		
	  	Matcher mat= pattern.matcher(respose); 
		
	  	boolean result = mat.find(); 
		
	  	if(result){
	  		
	  		resprefnumberResult = mat.group();
			
	  		Reporter.log("获取交易返回码=" + resprefnumberResult );
		
			boolean a1 = respose.contains(datadriven.get("exceptedmessage"));
			
			Reporter.log(datadriven.get("comment"), a1);
		
		// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，数据库检查
			if (a1 && datadriven.get("comment").equalsIgnoreCase("鉴权CNP正常流程")) {
		
		// 访问数据库，并得出查询结果
				String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));
				
				 String[] splitresult = b3.split(";");
				 
				 String txn_flg=splitresult[0];
				 
				 String txn_type=splitresult[1];
				
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
  
  @DataProvider(name = "AuthAndCnp")
  public Iterator<Object[]> data4test() throws IOException {
  	return ExcelProviderByEnv(this, "AuthAndCnp");
  }
	  
}


