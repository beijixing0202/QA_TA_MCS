package com.bill99.ate.service.ate.check;

import java.util.Map;

import org.testng.Reporter;

import com.bill99.ate.common.util.BasicStringTools;
import com.bill99.seashell.common.util.StringUtil;
import com.bill99.seashell.ma.common.util.HttpUtil;

public class AteResultCheck {
	
	public static Map<String, Object> responseMap ;
	
	public static String billOrderNo ;
	
	public static String tradeId ;

	/**
	 * 
	 * @param resultCode 错误码
	 * @param errorMessage 错误信息
	 * @param data 数据驱动
	 * @Description: 检查resultCode和errorMessage是否与期望一致
	 */
	public static void checkResultCodeAndMessage(Integer resultCode , String errorMessage , Map<String, String> data){
		Reporter.log("响应resultCode:" + resultCode + ", 响应错误信息:" + errorMessage);
		Reporter.log("期望resultCode:" + data.get("resultCode")+"，期望错误信息:" + data.get("errorMessage"));
		Reporter.log("检查resultCode是否匹配", resultCode==(Integer.valueOf(data.get("resultCode"))));
		Reporter.log("检查errorMessage是否匹配", StringUtil.null2String(errorMessage).equals(StringUtil.null2String(data.get("errorMessage"))));
	}
	
	/**
	 * 
	 * @param errorInfo dataMap中错误信息
	 * @param data 数据驱动
	 * @Description:检查dataMap中错误信息是否与期望一致
	 */
	public static void checkErrorInfo(String errorInfo , Map<String, String> data){
		Reporter.log("响应dataMap中errorInfo:" + errorInfo);
		Reporter.log("期望errorInfo:" + data.get("errorInfo"));
		Reporter.log("检查errorInfo是否匹配", StringUtil.null2String(errorInfo).equals(StringUtil.null2String(data.get("errorInfo"))));
	}
	
	/**
	 * 
	 * @param pageResource , 页面源码
	 * @param data , 校验resultCode、orderStatus、payStatus、errorInfo四个字段
	 * @Description:支付接口结果验证
	 */
	public static Map<String , Object> uiPayCheck(String pageResource , Map<String , String> data){
		Map<String , Object> response = getResponseMap(pageResource);
		Map<String , String> dataMap =(Map<String, String>) response.get("dataMap");
		String resultCode = String.valueOf(response.get("resultCode"));
		String orderStatus = dataMap.get("orderStatus");
		String payStatus = dataMap.get("payStatus");
		String errorInfo = dataMap.get("errorInfo");
		Reporter.log("响应dataMap中resultCode:" + resultCode);
		Reporter.log("期望resultCode:" + data.get("resultCode"));
		Reporter.log("检查resultCode是否匹配", compareString(data.get("resultCode"), resultCode));
		
		Reporter.log("响应dataMap中orderStatus:" + orderStatus);
		Reporter.log("期望orderStatus:" + data.get("orderStatus"));
		Reporter.log("检查orderStatus是否匹配", compareString(data.get("orderStatus"), orderStatus));
		
		Reporter.log("响应dataMap中payStatus:" + payStatus);
		Reporter.log("期望payStatus:" + data.get("payStatus"));
		Reporter.log("检查payStatus是否匹配", compareString(data.get("payStatus"), payStatus));
		
		Reporter.log("响应dataMap中errorInfo:" + errorInfo);
		Reporter.log("期望errorInfo:" + data.get("errorInfo"));
		Reporter.log("检查errorInfo是否匹配", compareString(data.get("errorInfo"), errorInfo));
		return response;
	}
	
	/**
	 * 
	 * @param pageResource , 页面源码
	 * @param data , 校验resultCode、orderStatus、payStatus、errorInfo四个字段
	 * @Description:支付确认结果校验
	 */
	public static Map<String , Object> uiPayConfirmCheck(String pageResource , Map<String , String> data){
		Map<String , Object> response = getResponseMap(pageResource);
		Map<String , String> dataMap =(Map<String, String>) response.get("dataMap");
		String confirmResultCode = String.valueOf(response.get("resultCode"));
		String confirmOrderStatus = dataMap.get("orderStatus");
		String confirmPayStatus = dataMap.get("payStatus");
		String confirmErrorInfo = dataMap.get("errorInfo");
		Reporter.log("响应dataMap中confirmResultCode:" + confirmResultCode);
		Reporter.log("期望confirmResultCode:" + data.get("confirmResultCode"));
		Reporter.log("检查confirmResultCode是否匹配", compareString(data.get("confirmResultCode"), confirmResultCode));
		
		Reporter.log("响应dataMap中confirmOrderStatus:" + confirmOrderStatus);
		Reporter.log("期望confirmOrderStatus:" + data.get("confirmOrderStatus"));
		Reporter.log("检查confirmOrderStatus是否匹配", compareString(data.get("confirmOrderStatus"), confirmOrderStatus));
		
		Reporter.log("响应dataMap中confirmPayStatus:" + confirmPayStatus);
		Reporter.log("期望confirmPayStatus:" + data.get("confirmPayStatus"));
		Reporter.log("检查confirmPayStatus是否匹配", compareString(data.get("confirmPayStatus"), confirmPayStatus));
		
		Reporter.log("响应dataMap中confirmErrorInfo:" + confirmErrorInfo);
		Reporter.log("期望confirmErrorInfo:" + data.get("confirmErrorInfo"));
		Reporter.log("检查confirmErrorInfo是否匹配", compareString(data.get("confirmErrorInfo"), confirmErrorInfo));
		return response;
}
	
	/**
	 * 
	 * @param pageResource , 页面源码
	 * @param data , 校验resultCode、orderStatus、payStatus、errorInfo四个字段
	 * @Description:撤销结果校验
	 */
	public static Map<String , Object> uiRevokeCheck(String pageResource , Map<String , String> data){
		Map<String , Object> response = getResponseMap(pageResource);
		Map<String , String> dataMap =(Map<String, String>) response.get("dataMap");
		String revokeResultCode = String.valueOf(response.get("resultCode"));
		String revokeOrderStatus = dataMap.get("orderStatus");
		String revokePayStatus = dataMap.get("payStatus");
		String revokeErrorInfo = dataMap.get("errorInfo");
		Reporter.log("响应dataMap中revokeResultCode:" + revokeResultCode);
		Reporter.log("期望revokeResultCode:" + data.get("revokeResultCode"));
		Reporter.log("检查revokeResultCode是否匹配", compareString(data.get("revokeResultCode"), revokeResultCode));
		
		Reporter.log("响应dataMap中revokeOrderStatus:" + revokeOrderStatus);
		Reporter.log("期望revokeOrderStatus:" + data.get("revokeOrderStatus"));
		Reporter.log("检查revokeOrderStatus是否匹配", compareString(data.get("revokeOrderStatus"), revokeOrderStatus));
		
		Reporter.log("响应dataMap中revokePayStatus:" + revokePayStatus);
		Reporter.log("期望revokePayStatus:" + data.get("revokePayStatus"));
		Reporter.log("检查revokePayStatus是否匹配", compareString(data.get("revokePayStatus"), revokePayStatus));
		
		Reporter.log("响应dataMap中revokeErrorInfo:" + revokeErrorInfo);
		Reporter.log("期望revokeErrorInfo:" + data.get("revokeErrorInfo"));
		Reporter.log("检查revokeErrorInfo是否匹配", compareString(data.get("revokeErrorInfo"), revokeErrorInfo));
		return response;
}
	
	/**
	 * 
	 * @param pageResource , 页面源码
	 * @param data , 校验resultCode、orderStatus、payStatus、errorInfo四个字段
	 * @Description:提现结果校验
	 */
	public static Map<String , Object> uiWithDrawCheck(String pageResource , Map<String , String> data){
		Map<String , Object> response = getResponseMap(pageResource);
		Map<String , String> dataMap =(Map<String, String>) response.get("dataMap");
		String withDrawResultCode = String.valueOf(response.get("resultCode"));
		String withDrawOrderStatus = dataMap.get("orderStatus");
		String withDrawPayStatus = dataMap.get("payStatus");
		String withDrawErrorInfo = dataMap.get("errorInfo");
		Reporter.log("响应dataMap中withDrawResultCode:" + withDrawResultCode);
		Reporter.log("期望withDrawResultCode:" + data.get("withDrawResultCode"));
		Reporter.log("检查withDrawResultCode是否匹配", compareString(data.get("withDrawResultCode"), withDrawResultCode));
		
		Reporter.log("响应dataMap中withDrawOrderStatus:" + withDrawOrderStatus);
		Reporter.log("期望withDrawOrderStatus:" + data.get("withDrawOrderStatus"));
		Reporter.log("检查withDrawOrderStatus是否匹配", compareString(data.get("withDrawOrderStatus"), withDrawOrderStatus));
		
		Reporter.log("响应dataMap中withDrawPayStatus:" + withDrawPayStatus);
		Reporter.log("期望withDrawPayStatus:" + data.get("withDrawPayStatus"));
		Reporter.log("检查withDrawPayStatus是否匹配", compareString(data.get("withDrawPayStatus"), withDrawPayStatus));
		
		Reporter.log("响应dataMap中withDrawErrorInfo:" + withDrawErrorInfo);
		Reporter.log("期望withDrawErrorInfo:" + data.get("withDrawErrorInfo"));
		Reporter.log("检查withDrawErrorInfo是否匹配", compareString(data.get("withDrawErrorInfo"), withDrawErrorInfo));
		return response;
}
	
	/**
	 * 
	 * @param pageResource , 页面源码
	 * @return , 校验resultCode、orderStatus、payStatus、errorInfo四个字段
	 * @Description:从页面中获取map形式的响应
	 */
	public static Map<String , Object> getResponseMap(String pageResource){
		String requestString = BasicStringTools.saveParamLeftstrRightstr("Request: ",
				"Response", pageResource.getBytes());
		String responseString = BasicStringTools.saveParamLeftstrRightstr("Response: ",
				"{1}", pageResource.getBytes());
		Reporter.log("请求:" + requestString);
		Reporter.log("响应:" + responseString);
		try {
			responseMap = HttpUtil.jsonToMap(responseString);
		} catch (Exception e) {
			Reporter.log("response转换map失败,response:" + responseString , false);
		}
		Map<String , String> dataMap = (Map<String, String>) responseMap.get("dataMap");
		billOrderNo = dataMap.get("billOrderNo");
		tradeId = dataMap.get("tradeId");
		return responseMap;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @Description:比较两个字符串 ， 考虑为null的情况
	 */
	public static boolean compareString(String a , String b){
		return StringUtil.null2String(a).equals(StringUtil.null2String(b));
	}
	
}
