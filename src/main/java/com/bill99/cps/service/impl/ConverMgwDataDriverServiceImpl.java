package com.bill99.cps.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.StringUtils;

import shelper.common.MyDate;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.service.ConverMgwDataDriverService;

public class ConverMgwDataDriverServiceImpl implements ConverMgwDataDriverService {

	@Override
	public MgwItem ConverData(Map<String, String> datadriven) {
		MgwItem mgwItem = new MgwItem();
		//数据驱动 entryTime 为auto 转成当前时间
		
		if(StringUtils.hasLength(datadriven.get("entryTime")) && datadriven.get("entryTime").equalsIgnoreCase("auto")){
			datadriven.put("entryTime", MyDate.getUserDate("yyyyMMddhhmmss"));

		}
		//数据驱动 entryTime 为auto 转成当前时间
		if (StringUtils.hasLength(datadriven.get("externalRefNumber")) && datadriven.get("externalRefNumber").equalsIgnoreCase("auto")) {
			datadriven.put("externalRefNumber",
					"1" + RandomStringUtils.random(13, false, true));
		}
		
		if (StringUtils.hasLength(datadriven.get("customerId")) && datadriven.get("customerId").equalsIgnoreCase("auto")) {
			datadriven.put("customerId",
					"1" + RandomStringUtils.random(7, false, true));
		}
		if (StringUtils.hasLength(datadriven.get("cardType")) && datadriven.get("cardType").equalsIgnoreCase("null")) {
			datadriven.put("cardType","");
		}
		try {
			BeanUtils.copyProperties(mgwItem, datadriven);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return  mgwItem;
	}

}
