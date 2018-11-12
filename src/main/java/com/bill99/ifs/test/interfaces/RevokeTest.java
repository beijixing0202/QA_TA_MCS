package com.bill99.ifs.test.interfaces;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.bill99.ate.common.httpclient.HttpClientUtil;
import com.bill99.ate.service.ate.check.AteDbCheck;
import com.bill99.ate.service.ate.check.AteResultCheck;
import com.bill99.ate.service.ate.mockHttp.ChooseFunctionPage;
import com.bill99.ate.service.ate.mockHttp.PayInterfaceHttpRequest;
import com.bill99.ate.service.ate.mockHttp.RevokeHttpRequest;
import com.bill99.ifs.service.CheckIfsDbService;
import com.bill99.qa.framework.testcase.BaseTestCase;

public class RevokeTest extends BaseTestCase{


	@Resource
	CheckIfsDbService checkIfsDbService;
	@Resource
	private PayInterfaceHttpRequest payInterfaceHttpRequest;
	@Resource
	private RevokeHttpRequest revokeHttpRequest;
	@Resource
	private AteDbCheck ateDbCheck;

	@BeforeClass
	public void beforeTest() {
		ChooseFunctionPage.enterMdpCenter();
	}

	@Test(description = "撤销",dataProvider="txnRevoke")
	public void txnRevoke(Map<String, String> data) throws Exception {
		Reporter.start(data.get("comment"));
		String result = payInterfaceHttpRequest.submitPay(data);
		Map<String, Object> response = AteResultCheck.uiPayCheck(result, data);
		Reporter.log("responseJSON="+JSONObject.toJSONString(response));

		String dataSting=JSONObject.toJSONString(response.get("dataMap"));
		ObjectMapper objectMapper=new ObjectMapper();
		Map<String, String>dataMap=objectMapper.readValue(dataSting, Map.class);
		dataMap.put("orderType", data.get("orderType"));
		Reporter.log("dataMap="+JSONObject.toJSONString(dataMap));

		//判断是否是现金分期，如果不是则做撤销
		if(!"110009".equals(data.get("orderType"))){
			String revokeResult = revokeHttpRequest.submitRevoke(data);
			Map<String, Object> revokeResponse=AteResultCheck.uiRevokeCheck(revokeResult, data);
			Reporter.log("revokeResponseJSON="+JSONObject.toJSONString(revokeResponse));
			String revoke=JSONObject.toJSONString(response.get("dataMap"));
			Map<String, String>revokeMap=objectMapper.readValue(revoke, Map.class);
			Reporter.log(data.get("comment")+"数据库验证",checkIfsDbService.checkRevokeService(revokeMap));

		}
		Reporter.end(data.get("comment"));
	}

	@DataProvider(name = "txnRevoke")
	private Iterator<Object[]> getData() {
		return ExcelProviderByEnv(this, "txnRevoke");
	}
	@AfterClass
	public void afterClass() {
		if (HttpClientUtil.getHttpClient() != null) {
			HttpClientUtil.closeHttpClient();
		}
	}

}
