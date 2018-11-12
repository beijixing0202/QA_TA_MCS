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

public class MgwPci extends BaseTestCase{
  
	
	@Autowired
	@Qualifier("pciStoreService")
	private HttpContentService pciStoreService;
	
	@Autowired
	@Qualifier("pciQueryService")
	private HttpContentService pciQueryService;
	
	@Autowired
	@Qualifier("pciDelService")
	private HttpContentService pciDelService;
	
	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
	
	@Test(dataProvider = "mgwpci")
  public void pci(Map<String, String> datadriven) {
		
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		
		String pcistorerespString = pciStoreService.cnpContent(mgwItem);
		
		Reporter.log(pcistorerespString);
		
		String pciqueryrespString = pciQueryService.cnpContent(mgwItem);
		
		Reporter.log(pciqueryrespString);
		
		String pcidelrespString = pciDelService.cnpContent(mgwItem);
		
		Reporter.log(pcidelrespString);


		
		
  }

  @DataProvider(name = "mgwpci")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "mgwpci");
	}
}
