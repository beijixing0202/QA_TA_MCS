package com.bill99.ifs.test.interfaces;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sun.security.action.PutAllAction;

import com.alibaba.fastjson.JSONObject;
import com.bill99.ate.common.httpclient.HttpClientUtil;
import com.bill99.ate.common.httpclient.StringUtil;
import com.bill99.ate.service.ate.check.AteDbCheck;
import com.bill99.ate.service.ate.check.AteResultCheck;
import com.bill99.ate.service.ate.mockHttp.ChooseFunctionPage;
import com.bill99.ate.service.ate.mockHttp.PayInterfaceHttpRequest;
import com.bill99.ate.service.ate.mockHttp.RefundHttpRequest;
import com.bill99.ate.service.ate.mockHttp.RevokeHttpRequest;
import com.bill99.ifs.common.dto.FcsTxnDto;
import com.bill99.ifs.common.dto.QuartzDto;
import com.bill99.ifs.common.util.TriggerQuartz;
import com.bill99.ifs.orm.IfsDao;
import com.bill99.ifs.service.CheckIfsDbService;
import com.bill99.qa.framework.testcase.BaseTestCase;

public class RefundTest extends BaseTestCase{

	@Resource
	private RefundHttpRequest refundHttpRequest;
	@Resource
	CheckIfsDbService checkIfsDbService;
	@Resource
	private PayInterfaceHttpRequest payInterfaceHttpRequest;
	@Resource
	private IfsDao ifsdao;

	@BeforeClass
	public void beforeTest()  {
		ChooseFunctionPage.enterMdpCenter();
	}

	@Test(description = "退货",dataProvider="txnRefund")
	public void txnRefund(Map<String, String> data) throws Exception {
		Reporter.start(data.get("comment"));
//		Map<String, String>map=new HashMap<String, String>();
//		map.put("billOrderNo", "920170816135220900108627326");
//		map.put("payAmount", "9900");
//		map.put("orgRefundStatus", "32");
//		checkIfsDbService.checkRefundService(map);

		//查找一笔可退货交易
		FcsTxnDto fcsTxnDto = setRefundInfo(data);
		if(null==fcsTxnDto){
			Reporter.log("没有可退货的数据");
		}else{
			if ("3".equals(data.get("type"))) {
				ifsdao.updateCashDrawByRef(fcsTxnDto.getId());
			}
			// 退货
			String result = refundHttpRequest.submitRefund(data);
			// 页面检查
			Map<String, Object> refundResponse=AteResultCheck.uiPayCheck(result, data);

			//等待10s,待数据落地后，再执行quartz
			Thread.sleep(10000);
			//执行quartz：ifs.credit.ClearAcctLegTaskTrigger
			QuartzDto quartzDto=new QuartzDto();
			quartzDto.setTriggerGroup("ifs.creidt.dpm");
			quartzDto.setTriggerName("ifs.credit.ClearAcctLegTaskTrigger");
			if(!TriggerQuartz.trigOneQuartz(quartzDto)){
				Reporter.log("Quartz执行失败",false);
			}
			//等待10s，等待quartz执行完成
			Thread.sleep(10000);

			Reporter.log("refundResponse="+JSONObject.toJSONString(refundResponse));
			String refund=JSONObject.toJSONString(refundResponse.get("dataMap"));
			ObjectMapper objectMapper=new ObjectMapper();
			Map<String, String>refundMap=objectMapper.readValue(refund, Map.class);
			//把原始交易退货后的预期状态传入refundMap
			refundMap.put("orgRefundStatus", data.get("orgRefundStatus"));
			Reporter.log(data.get("comment")+"数据库验证",checkIfsDbService.checkRefundService(refundMap));
		}

		Reporter.end(data.get("comment"));
	}
	private FcsTxnDto setRefundInfo(Map<String, String> data) {
		//查找一笔可退货交易
		FcsTxnDto fcsTxnDto= ifsdao.getRefundTxnByMemberAndType(data);
		//退货所需字段
		if(null==fcsTxnDto){
			return null;
		}else{
			BigDecimal amt=new BigDecimal(fcsTxnDto.getPay_amount()).multiply(new BigDecimal("100"));
			data.put("billOrderNo", fcsTxnDto.getOut_trade_no());
			data.put("merchantCode", fcsTxnDto.getMerchant_code());
			data.put("refundAmount", amt.toString());
			data.put("channelType", fcsTxnDto.getChannel_type());
			return fcsTxnDto;
		}

	}
	@DataProvider(name = "txnRefund")
	private Iterator<Object[]> getData() {
		return ExcelProviderByEnv(this, "txnRefund");
	}
	@AfterClass
	public void afterClass() {
		if (HttpClientUtil.getHttpClient() != null) {
			HttpClientUtil.closeHttpClient();
		}
	}



}
