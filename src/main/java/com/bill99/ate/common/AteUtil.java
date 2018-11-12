package com.bill99.ate.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.testng.Reporter;

import com.bill99.seashell.common.util.StringUtil;

public class AteUtil {
	
	/**
	 * 
	 * @return string
	 * @Description:生成外部订单号
	 */
	public static String getOutTradeNo(Map<String, String> data) {

		if ("1".equals(data.get("outTradeNo"))) {
			//数据驱动中outTradeNo为1，返回随机outTradeNo
			String orderTradeNo = new StringBuffer("AutoTest").append(getSimpleData(data)).toString();
			Reporter.log("生成外部订单号为:" + orderTradeNo);
			return orderTradeNo;
		} else if (StringUtil.isEmpty(data.get("outTradeNo"))) {
			//数据驱动中outTradeNo为空，返回空
			Reporter.log("生成空的外部订单号");
			return "";
		} else {
			//其他情况，返回数据驱动中outTradeNo
			Reporter.log("生成外部订单号为:" + data.get("outTradeNo"));
			return data.get("outTradeNo");
		}

	}

	/**
	 * 
	 * @return
	 * @Description:生成时间
	 */
	public static String getSimpleData(Map<String, String> data) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return format.format(date) + new Random().nextInt(10);

	}

}
