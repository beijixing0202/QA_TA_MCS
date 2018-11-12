package com.bill99.ate.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @Description: 对象属性操作
 * @author  xiangnan.qi
 * @date 2015-11-5 上午10:00:51 
 *  
 */
public class FieldUtil {

	/**
	 * @Description: 获取对象所有属性
	 * @param obj
	 * @return
	 */
	public static List<String> getObjFields(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> list = new ArrayList<String>();
		for (Field field : fields) {
			list.add(field.getName());
		}
		return list;
	}

	/**
	 * @Description: 获取对象值和属性的list
	 * @param obj
	 * @return
	 */
	public static List<Map<String, Object>> getObjFieldValues(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Field field : fields) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", field.getName());
			map.put("value", getFieldValueByName(field.getName(), obj));
			list.add(map);
		}
		return list;
	}

	/**
	 * @Description: 获取对象属性值
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object getFieldValueByName(String fieldName, Object obj) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = obj.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(obj, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Description: 输出对象属性和值
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		List<Map<String, Object>> list = getObjFieldValues(obj);
		StringBuilder sb = new StringBuilder();
		for (Map<String, Object> map : list) {
			if (map.get("value") != null) {
				sb.append(map.get("name")).append(":").append(map.get("value")).append(",");
			}
		}
		return "".equals(sb.toString()) ? "" : sb.substring(0, sb.length() - 1);
	}
}
