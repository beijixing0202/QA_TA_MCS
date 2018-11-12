package com.bill99.fi.orm.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bill99.fi.orm.dao.IbatisCheckDealState;
import com.bill99.fi.orm.dao.LogDbCheck;
import com.bill99.fi.orm.mng.GatewayDbCheck;

public class LogDbCheckImpl implements LogDbCheck {

	private JdbcTemplate jdbcTemplate;
	private GatewayDbCheck gatewayDbCheck;
	private IbatisCheckDealState ibatisCheckDealState;


	public boolean notifyLogDbCkeck(Map<String, String> data) {
		String sqlString = "Select count(1)  From leqi.gatewayreturnlog r  Where  r.orderid='" + gatewayDbCheck.getSequenceidByOrderid(data).getSequenceid()
				+ "'  and  r.satatuscode='200' ";
		Integer count = (Integer) jdbcTemplate.queryForObject(sqlString, Integer.class);
		return count > 0;
	}

	/**
	 * @param orderid
	 *            leqi.gatewayreturnlog 表的orderid
	 * @return
	 */
	public boolean notifyLogDbCkeck(String orderId) {
		// 通过联通交易日志表获取到 通知需要的sequenceid
		String unionOrderId = ibatisCheckDealState.getUnionorderlog(orderId).getSequenceid();

		String sqlString = "Select count(1)  From leqi.gatewayreturnlog r  Where  r.orderid='" + unionOrderId + "'  and  r.satatuscode='200' ";
		Integer count = (Integer) jdbcTemplate.queryForObject(sqlString, Integer.class);
		return count > 0;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public GatewayDbCheck getGatewayDbCheck() {
		return gatewayDbCheck;
	}

	public void setGatewayDbCheck(GatewayDbCheck gatewayDbCheck) {
		this.gatewayDbCheck = gatewayDbCheck;
	}

	public IbatisCheckDealState getIbatisCheckDealState() {
		return ibatisCheckDealState;
	}

	public void setIbatisCheckDealState(IbatisCheckDealState ibatisCheckDealState) {
		this.ibatisCheckDealState = ibatisCheckDealState;
	}
}
