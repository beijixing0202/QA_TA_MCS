package com.bill99.ate.service.ate.mockHttp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bill99.ate.common.AteUtil;
import com.bill99.ate.common.httpclient.HttpClientUtil;
import com.bill99.ate.pageObject.HessianUrl;
import com.bill99.golden.inf.core.mdp.util.StringUtil;

public class RechargeHttpRequest {

	private String functionCode;

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * 
	 * @param data，数据驱动
	 * @return
	 * @Description:httpClient方式充值
	 */
	public String submitRecharge(Map<String, String> data) {
		//加入functionCode
		data.put("functionCode", functionCode);
		//随机生成外部订单号
		data.put("outTradeNo", AteUtil.getOutTradeNo(data));
		//机组地址
		if (StringUtil.isBlank(data.get("hessianUrl"))) {
			data.put("hessianUrl", HessianUrl.getHessianUrl());
		}
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.putAll(data);
		dataMap.remove("errorInfo");
		String result = null;
		try {
			result = HttpClientUtil.doPost("http://192.168.14.88:8088/cap-mock/orderMdpBankProcess.jsp", dataMap, null,
					null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
