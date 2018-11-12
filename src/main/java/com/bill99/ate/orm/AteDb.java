package com.bill99.ate.orm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.testng.Reporter;

import com.alibaba.fastjson.JSON;
import com.bill99.ate.orm.dto.OrderMasterDto;
import com.bill99.ate.orm.dto.RefundApplyDto;
import com.bill99.inf.bdal.jdbc.SearchDataSource;
import com.bill99.qa.framework.jdbc.TaDbHandller;
import com.bill99.seashell.common.util.StringUtil;

import shelper.db.MySql;

public class AteDb {

	private TaDbHandller taMAMDbHandller;

	private MySql mySql;

	private String userName;

	private String passWord;

	/**
	 * ATE分表数
	 */
	private Integer tableNum;

	/**
	 * ATE分库数
	 */
	private Integer dbNum;

	private Map<Integer, String> dbMap;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setDbMap(Map<Integer, String> dbMap) {
		this.dbMap = dbMap;
	}

	public void setTaMAMDbHandller(TaDbHandller taMAMDbHandller) {
		this.taMAMDbHandller = taMAMDbHandller;
	}

	/**
	 * 
	 * @param billOrderNo
	 * @Description:计算库名，表名
	 */
	public void setMySql(String billOrderNo) {
		Integer subBillOrderNo = Integer.valueOf(billOrderNo.substring(billOrderNo.length() - 5, billOrderNo.length()));
		tableNum = subBillOrderNo % 120;
		dbNum = tableNum / 20 + 1;
		mySql = new MySql(dbMap.get(dbNum), userName, passWord);
		Reporter.log("计算出dbNum为：" + dbNum + ",tableNum为：" + tableNum);
	}

	public List<OrderMasterDto> queryOrderMasterDtoByOrderMasterDto(OrderMasterDto orderMasterDto) {
		return taMAMDbHandller.queryForList("OrderMaster.selectOrderMasterDtoByOrderMasterDto", orderMasterDto);
	}

	//更新订单主表时间为可退货
	public void updateOrderMasterTxnEndTimeReduceOneDay(OrderMasterDto orderMasterDto) {
		taMAMDbHandller.executeUpdate("OrderMaster.updateOrderMasterTxnEndTimeReduceOneDay", orderMasterDto);
	}

	//更新退货流水表为可退货
	public void updateTrasnsactionFlowingTxnEndTimeReduceOneDay(OrderMasterDto orderMasterDto) {
		taMAMDbHandller.executeUpdate("OrderMaster.updateTrasnsactionFlowingTxnEndTimeReduceOneDay", orderMasterDto);
	}

	public void updateClearTxnTimeReduceOneDay(String stl_src_ref) {
		taMAMDbHandller.executeUpdate("fscClear.updateClearTxnTimeReduceOneDay", stl_src_ref);
	}

	public void updateClearValueDateReduceOneDay(String stl_src_ref) {
		taMAMDbHandller.executeUpdate("fscClear.updateClearValueDateReduceOneDay", stl_src_ref);
	}

	public String queryClearStatus(String stl_src_ref) {
		return taMAMDbHandller.queryForObject("fscClear.selectClearStatusByStlSrcRef", stl_src_ref);
	}

	public Integer queryOrderCounts(OrderMasterDto orderMasterDto) {
		return taMAMDbHandller.queryForObject("OrderMaster.queryOrderCounts", orderMasterDto);
	}

	public Integer queryTransactionFlowingCounts(OrderMasterDto orderMasterDto) {
		return taMAMDbHandller.queryForObject("OrderMaster.queryTransactionFlowingCounts", orderMasterDto);
	}

	public RefundApplyDto queryRefundApplyDto(RefundApplyDto refundApplyDto) {
		return taMAMDbHandller.queryForObject("RefundApply.selectRefundApplyDtoByRefundApplyDto", refundApplyDto);
	}

	//查询流水表
	public Map<String, String> getFlowing(String tradeId) {
		return taMAMDbHandller.queryForObject("OrderMaster.queryFlowing", tradeId);
	}

	public String getOrderStatusByOutTradeNo(String outTradeNo) {
		// TODO Auto-generated method stub
		return taMAMDbHandller.queryForObject("ateResult.getOrderStatusByOutTradeNo", outTradeNo);
	}

	public String getPayStatusByOutTradeNo(String outTradeNo) {
		// TODO Auto-generated method stub
		return taMAMDbHandller.queryForObject("ateResult.getPayStatusByOutTradeNo", outTradeNo);
	}

	/*
	 * 获取流水表中refIdCtrl
	 */
	public String getRefIdCtrl(String billOrderNo, String tradeID) {
		setMySql(billOrderNo);
		String sql = "select ref_id_ctrl from ate_transaction_flowing_" + (new DecimalFormat("000").format(tableNum))
				+ " where trade_Id = " + tradeID;
		return mySql.query(sql);
	}

	/*
	 * 根据billOrderNo获取orderMaster表数据
	 */
	public String[] queryOrderMaster(String billOrderNo) {
		setMySql(billOrderNo);
		String sql = "select order_status,pay_Status,function_Code,order_Amount,pay_mode from t_acc_order_master_"
				+ (new DecimalFormat("000").format(tableNum)) + " where bill_order_no = " + billOrderNo;
		return mySql.query(sql).split(";");
	}

	/*
	 * 根据tradeID获取流水表数据
	 */
	public String[] queryFlowing(String billOrderNo, String tradeID) {
		setMySql(billOrderNo);
		String sql = "select ERROR_INFO,NET_AMOUNT,FUNCTION_CODE,TRADE_AMOUNT,TRADE_STATUS from ate_transaction_flowing_"
				+ (new DecimalFormat("000").format(tableNum)) + " where trade_Id = " + tradeID;
		return mySql.query(sql).split(";");
	}
	
	/*
	 * 获取一条可退货交易
	 */
	public Map<String , String> getRefund(String payMode){
		String sql = "select * from t_acc_order_master where order_status=1 and txn_type != 'PAYORDERINTER' " +"and pay_mode = '" + payMode + "' and  (TO_DAYS(NOW())  - TO_DAYS(create_time)) >=1 order by create_time desc limit 100";
		return excute(sql).get(new Random().nextInt(100));
	}

	public String buildOrderMasterSql(String sql, Map<String, String> data) {
		//String sql = "select trade_id,order_status,pay_status,order_amount,pay_amount from ate_transaction_flowing_" + (new DecimalFormat("000").format(tableNum)) + " where 1=1";
		if (!StringUtil.isEmpty(data.get("outTradeNo"))) {
			sql += " and out_trade_no = " + data.get("outTradeNo");
		}
		if (!StringUtil.isEmpty(data.get("billOrderNo"))) {
			sql += " and bill_Order_No = " + data.get("billOrderNo");
		}
		if (!StringUtil.isEmpty(data.get("masterId"))) {
			sql += " and master_Id = " + data.get("masterId");
		}
		if (!StringUtil.isEmpty(data.get("merchantCode"))) {
			sql += " and merchant_Code = " + data.get("merchantCode");
		}
		if (!StringUtil.isEmpty(data.get("channelType"))) {
			sql += " and channel_Type = " + data.get("channelType");
		}
		if (!StringUtil.isEmpty(data.get("orderType"))) {
			sql += " and order_Type = " + data.get("orderType");
		}
		if (!StringUtil.isEmpty(data.get("tradeId"))) {
			sql += " and trade_Id = " + data.get("tradeId");
		}
		if (!StringUtil.isEmpty(data.get("outOrderType"))) {
			sql += " and out_Order_Type = " + data.get("outOrderType");
		}
		if (!StringUtil.isEmpty(data.get("authCode"))) {
			sql += " and auth_Code = " + data.get("authCode");
		}
		if (!StringUtil.isEmpty(data.get("orderStatus"))) {
			sql += " and order_Status = " + data.get("orderStatus");
		}
		if (!StringUtil.isEmpty(data.get("payStatus"))) {
			sql += " and pay_Status = " + data.get("payStatus");
		}
		return sql;
	}

	/*
	 * ATE执行sql查询
	 */
	public static List<Map<String, String>> excute(String sql) {
		SearchDataSource bddlDataSource = new SearchDataSource("mysqlAteDataSourceMdp");
		Statement ps = null;
		ResultSet rs = null;
		try {
			Connection connection = bddlDataSource.getConnection();
			ps = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return getResultSet(rs);

	}

	/*
	 * 将数据库查询结果resultSet转换成List<Map<String, String>>
	 */
	public static List<Map<String, String>> getResultSet(ResultSet rs) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		if (rs == null) {
			return null;
		}
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			while (rs.next()) {
				Map<String, String> resultMap = new HashMap<String, String>();
				for (int i = 1; i <= cols; i++) {
					resultMap.put(meta.getColumnName(i), rs.getString(i));
				}
				list.add(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		/*	AteDb a = new AteDb();
			String ss= a.getRefIdCtrl("920170419112910400008909929","103000089");
			System.out.println(ss);*/
		String sql = "select * from t_acc_order_master where order_status=1  and  (TO_DAYS(NOW())  - TO_DAYS(create_time)) >=1 order by create_time desc limit 10";
		Map<String, String> resultMap = excute(sql).get(0);
		System.out.println(JSON.toJSONString(resultMap, true));
	}

}
