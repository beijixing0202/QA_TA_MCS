package com.bill99.cps.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import shelper.common.MyDate;

import com.bill99.cps.common.dto.MockInternalItem;
import com.bill99.cps.service.MockConverInternalDataDriverService;

public class MockConverInternalDataDriverServiceImpl implements
		MockConverInternalDataDriverService {

	@Override
	public MockInternalItem ConverData(Map<String, String> datadriven) {
		MockInternalItem internalMockItem = new MockInternalItem();
		// TODO 如果txnType 不为空，并且token 为空，并且 serviceChannelTraceNo 为空?
		// 设置serviceChannelTraceNo（请求服务渠道跟踪编号 ）为14位随机数
		if (!StringUtils.hasLength(datadriven.get("serviceChannelTraceNo")))
		// StringUtils.hasLength(datadriven.get("txnType"))&&
		// !StringUtils.hasLength(datadriven.get("token"))&&
		{
			datadriven.put("serviceChannelTraceNo",
					MyDate.getUserDate("yyMMddhhmmss")+MyDate.getRandom(2));
		}
		
		

		try {
			BeanUtils.copyProperties(internalMockItem, datadriven);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return internalMockItem;
	}

}
