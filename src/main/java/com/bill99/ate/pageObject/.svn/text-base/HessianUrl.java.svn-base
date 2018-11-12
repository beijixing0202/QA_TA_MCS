package com.bill99.ate.pageObject;

import java.util.Arrays;
import java.util.List;

import steel.util.StringUtil;

public class HessianUrl {

	//机组名
	private static String groupName ;
	
	private static String groupList = "";
	
	private static final String payMentSuffix = "orderPaymentServiceRemoting";
	
	private static final String orderSuffix = "orderServiceRemoting";

	/**
	 * 
	 * @return
	 * @Description:支付相关url
	 */
	public static String getHessianUrl() {
		if (StringUtil.isBlank(groupName)) {
			return "";
		} else {
			return (getPreUrl() + payMentSuffix);
		}

	}

	/**
	 * 
	 * @return
	 * @Description:下单和取消下单机组地址
	 */
	public static String getOrderHessianUrl() {
		if (StringUtil.isBlank(groupName)) {
			return "";
		} else {
			return (getPreUrl() + orderSuffix);
		}

	}
	
	/**
	 * 
	 * @return
	 * @Description:根据url获取公共部分url
	 * 
	 */
	private static String getPreUrl(){
		//若机组名为大写 ， 则改为小写
		char[] g = groupName.toCharArray();
		if(g[0] >= 65 && g[0] <= 90){
			g[0] += 32;
		}
		return ("http://ate-inner-" + String.valueOf(g) + ".99bill.com/cap-ate-center-mdp/hessian/");
	}
	
	//多机组数组，转换为list
	public static List<String> getGroupList(){
		return Arrays.asList(groupList.split(","));
	}

	public static void setGroupName(String groupName) {
		HessianUrl.groupName = groupName;
	}
	
	

}
