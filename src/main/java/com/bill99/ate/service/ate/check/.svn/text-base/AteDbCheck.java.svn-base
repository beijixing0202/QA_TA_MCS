package com.bill99.ate.service.ate.check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;

import com.alibaba.fastjson.JSON;
import com.bill99.ate.orm.AteDb;
import com.bill99.ate.orm.dto.OrderMasterDto;

public class AteDbCheck {

	private AteDb ateDb;

	public static final boolean isCheckDb = true;

	public void setAteDb(AteDb ateDb) {
		this.ateDb = ateDb;
	}

	/**
	 * 
	 * @param data
	 * @param outTradeNo
	 * @Description 检查交易结束后DB中的交易状态
	 */
	public void checkPayResultOnDb(Map<String, String> data, String outTradeNo) {
		String actualOrderStatus = ateDb.getOrderStatusByOutTradeNo(outTradeNo);
		String actualPayStatus = ateDb.getPayStatusByOutTradeNo(outTradeNo);

		String expectedOrderStatus = data.get("orderStatus");
		String expectedPayStatus = data.get("payStatus");

		Reporter.log("数据库验证order status：" + "[expected]" + expectedOrderStatus + " <=> [actual]" + actualOrderStatus,
				expectedOrderStatus.equals(actualOrderStatus));
		Reporter.log("数据库验证pay status：" + "[expected]" + expectedPayStatus + " <=> [actual]" + actualPayStatus,
				expectedPayStatus.equals(actualPayStatus));
	}

	public void checkOrderMaster(String outTradeNo, String orderStatus, String payStatus, String functionCode,
			String orderAmount, String payMode) {
		//查询数据库OrderMaster表
		String [] orderMasterCheck = ateDb.queryOrderMaster(AteResultCheck.billOrderNo);		
		Reporter.log("OrderMaster表信息:" + JSON.toJSONString(orderMasterCheck, true));
		
		Reporter.log("OrderMaster表期望order_status:" + orderStatus);
		Reporter.log("OrderMaster表实际order_status:" + orderMasterCheck[0]);
		Reporter.log("检查order_status是否匹配",
				AteResultCheck.compareString(orderMasterCheck[0], orderStatus));
		
		Reporter.log("OrderMaster表期望payStatus:" + payStatus);
		Reporter.log("OrderMaster表实际payStatus:" + orderMasterCheck[1]);
		Reporter.log("检查pay_status是否匹配",
				AteResultCheck.compareString(orderMasterCheck[1], payStatus));
		
		Reporter.log("OrderMaster表期望functionCode:" + functionCode);
		Reporter.log("OrderMaster表实际functionCode:" + orderMasterCheck[2]);
		Reporter.log("检查functionCode是否匹配",
				AteResultCheck.compareString(orderMasterCheck[2], functionCode));
		
		Reporter.log("OrderMaster表期望orderAmount:" + orderAmount);
		Reporter.log("OrderMaster表实际orderAmount:" + orderMasterCheck[3]);
		Reporter.log("检查orderAmount是否匹配",
				AteResultCheck.compareString(orderMasterCheck[3], orderAmount));
		
		Reporter.log("OrderMaster表期望payMode:" + payMode);
		Reporter.log("OrderMaster表实际payMode:" + orderMasterCheck[4]);
		Reporter.log("检查payMode是否匹配", AteResultCheck.compareString(orderMasterCheck[4], payMode));

	}

	public void checkFlowing(String outTradeNo, String errorInfo, String netAmount, String functionCode,
			String tradeAmount, String tradeStatus) {
		String[] flowingCheck = ateDb.queryFlowing(AteResultCheck.billOrderNo, AteResultCheck.tradeId);
		Reporter.log("transaction_flowing表信息:" + JSON.toJSONString(flowingCheck, true));
		
		Reporter.log("transaction_flowing表期望errorInfo:" + errorInfo);
		Reporter.log("transaction_flowing表实际errorInfo:" + flowingCheck[0]);
		Reporter.log("transaction_flowing表检查errorInfo是否匹配",
				AteResultCheck.compareString(flowingCheck[0], errorInfo));
		
		Reporter.log("transaction_flowing表期望netAmount:" + netAmount);
		Reporter.log("transaction_flowing表实际netAmount:" + flowingCheck[1]);
		Reporter.log("transaction_flowing表检查netAmount是否匹配",
				AteResultCheck.compareString(flowingCheck[1], netAmount));
		
		Reporter.log("transaction_flowing表期望functionCode:" + functionCode);
		Reporter.log("transaction_flowing表实际functionCode:" + flowingCheck[2]);
		Reporter.log("transaction_flowing表检查functionCode是否匹配",
				AteResultCheck.compareString(flowingCheck[2], functionCode));
		
		Reporter.log("transaction_flowing表期望tradeAmount:" + tradeAmount);
		Reporter.log("transaction_flowing表实际tradeAmount:" + flowingCheck[3]);
		Reporter.log("transaction_flowing表检查tradeAmount是否匹配",
				AteResultCheck.compareString(flowingCheck[3], tradeAmount));
		
		Reporter.log("transaction_flowing表期望tradeStatus:" + tradeStatus);
		Reporter.log("transaction_flowing表实际tradeStatus:" + flowingCheck[4]);
		Reporter.log("transaction_flowing表检查tradeStatus是否匹配",
				AteResultCheck.compareString(flowingCheck[4], tradeStatus));

	}



	/**
	 * 
	 * @param data
	 * @Description:支付一步，数据库检查
	 */
	public void payOneStepDbCheck(Map<String, String> data, boolean checkFlowing) {
		checkOrderMaster(data.get("outTradeNo"), data.get("orderStatus"), data.get("payStatus"),
				data.get("functionCode"), data.get("orderAmount"), data.get("payMode"));
		if (checkFlowing) {
			checkFlowing(data.get("outTradeNo"), data.get("dbErrorInfo"), data.get("netAmount"),
					data.get("functionCode"), data.get("tradeAmount"), data.get("tradeStatus"));
		}
	}

	/**
	 * 
	 * @param data
	 * @Description:撤销，数据库检查
	 */
	public void revokeDbCheck(Map<String, String> data) {
		checkOrderMaster(data.get("outTradeNo"), data.get("revokeOrderStatus"), data.get("revokePayStatus"),
				data.get("revokeFunctionCode"), data.get("revokeOrderAmount"), data.get("revokePayMode"));
		checkFlowing(data.get("outTradeNo"), data.get("dbErrorInfo"), data.get("revokeNetAmount"),
				data.get("revokeFunctionCode"), data.get("revokeTradeAmount"), data.get("revokeTradeStatus"));
	}
	
	/**
	 * 
	 * @param data
	 * @Description:退货，数据库检查
	 */
	public void refundDbCheck(Map<String, String> data) {
		checkOrderMaster(data.get("outTradeNo"), data.get("refundOrderStatus"), data.get("refundPayStatus"),
				data.get("refundFunctionCode"), data.get("refundAmount"), data.get("payMode"));
		checkFlowing(data.get("outTradeNo"), data.get("dbErrorInfo"), data.get("refundAmount"),
				data.get("refundFunctionCode"), data.get("refundAmount"), data.get("refundTradeStatus"));
	}
	
	
	/**
	 * 
	 * @param data
	 * @Description:充值，数据库检查
	 */
	public void rechargeDbCheck(Map<String, String> data, boolean checkFlowing) {
		checkOrderMaster(data.get("outTradeNo"), data.get("orderStatus"), data.get("payStatus"),
				data.get("functionCode"), data.get("orderAmount"), data.get("payMode"));
		if (checkFlowing) {
			checkFlowing(data.get("outTradeNo"), data.get("dbErrorInfo"), data.get("netAmount"),
					data.get("functionCode"), data.get("tradeAmount"), data.get("tradeStatus"));
		}
	}

	/**
	 * 
	 * @param data
	 * @Description:提现，数据库检查
	 */
	public void withDrawDbCheck(Map<String, String> data) {
		checkOrderMaster(data.get("outTradeNo"), data.get("withDrawOrderStatus"), data.get("withDrawPayStatus"),
				data.get("functionCode"), data.get("orderAmount"), "10");

		checkFlowing(data.get("outTradeNo"), data.get("dbErrorInfo"), data.get("netAmount"), data.get("functionCode"),
				data.get("tradeAmount"), data.get("withDrawTradeStatus"));

	}

	/**
	 * 
	 * @param orderMasterDto
	 * @return
	 * @Description:获取退货tradeId
	 */
	public static String getRevokeTradeId(OrderMasterDto orderMasterDto) {
		String tradeId = orderMasterDto.getTradeId();
		String origTradeId = orderMasterDto.getOrigTradeId();
		//origTradeId此字段中会包含退货、撤销和正向交易的tradeId , 所以做如下处理 。 若size大于1 ，肯定是已撤销或退货 。 需要检查反向交易的流水表信息
		List<String> list = new ArrayList<String>(Arrays.asList(origTradeId.split(",")));
		if (list.size() > 1) {
			list.remove(tradeId);
		}
		return list.get(0);
	}

	/**
	 * 
	 * @param outTradeNo
	 * @return
	 * @Description:获取流水表ID_TXN_TXN字段
	 */
	public String getRefIdTxn(String outTradeNo) {
		OrderMasterDto orderMasterDto = new OrderMasterDto();
		orderMasterDto.setOutTradeNo(outTradeNo);
		OrderMasterDto dbResult = ateDb.queryOrderMasterDtoByOrderMasterDto(orderMasterDto).get(0);
		Map<String, String> flowingDb = ateDb.getFlowing(getRevokeTradeId(dbResult));
		return flowingDb.get("REF_ID_TXN");
	}

	/**
	 * 
	 * @param pageResource
	 * @return
	 * @Description:mysql获取流水表ID_TXN_CTRL字段
	 */
	public String getRefIdCtrl() {
		Reporter.log("billOrderNo为:" + AteResultCheck.billOrderNo + ",tradeId为:" + AteResultCheck.tradeId);
		return ateDb.getRefIdCtrl(AteResultCheck.billOrderNo, AteResultCheck.tradeId);
	}

}
