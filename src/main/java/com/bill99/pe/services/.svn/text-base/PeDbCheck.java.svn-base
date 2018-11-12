package com.bill99.pe.services;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.testng.Reporter;

import com.bill99.pe.dto.DbConn;

import com.bill99.pe.dto.PeDbItem;
import com.bill99.seashell.common.util.StringUtil;

import shelper.data.DataTable;
import shelper.db.Oracle;

public class PeDbCheck {
	
	
	
	private DbConn dbConn;
	
	
	public PeDbItem peAll(String sequenceid,String dealid,Map<String,String> data){
		PeDbItem peAllDto = new PeDbItem();
		String payment="";
		String deal="";
		try {
			Oracle db =dbConn.getDBConn();
			payment = db
					.query("Select orderid,submitacctcode,ordercode,productname,orderamount,payeracctcode,payeeacctcode,orderstatus From Seashell.paymentorder d Where d.Orderseqid = " + sequenceid + "");
			deal = db
					.query("Select dealamount,dealstatus,dealcode,paymentservicepkgcode,payerorgcode From Seashell.Deal d Where d.dealid = " + dealid + "");
			
		//	String dealid=peAllDto.get
		//	String entry = db.query("Select d.* From Seashell.Entry d Where d.Orderseqid = "
		//			+ sequenceid + "");
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 分割取结果字段
				String[] splitresult = payment.split(";");
		//		String orderid = splitresult[0];
				String submitacctcode = splitresult[1];
				String ordercode = splitresult[2];
				String productname = splitresult[3];
				String orderamount = splitresult[4];
				String payeracctcode = splitresult[5];
				String payeeacctcode = splitresult[6];
				String orderstatus = splitresult[7];
				Reporter.log("+++++paymentorder表 期望submitacctcode+++++"
						+ data.get("excepted_submitacctcode"));
				if (data.get("excepted_submitacctcode") == null
						|| data.get("excepted_submitacctcode") == "") {
					System.out.println("数据驱动未配置excepted_submitacctcode，请检查");
				}
				Reporter.log("+++++paymentorder表 实际submitacctcode+++++" + submitacctcode);
				Reporter.log("检查submitacctcode是否匹配",
						compareString(data.get("excepted_submitacctcode"), submitacctcode));

				Reporter.log("+++++paymentorder表 期望ordercode+++++"
						+ data.get("excepted_ordercode"));
				if (data.get("excepted_ordercode") == null
						|| data.get("excepted_ordercode") == "") {
					System.out.println("数据驱动未配置excepted_ordercode，请检查");
				}
				Reporter.log("+++++paymentorder表 实际ordercode+++++" + ordercode);
				Reporter.log("检查ordercode是否匹配",
						compareString(data.get("excepted_ordercode"), ordercode));

				Reporter.log("+++++paymentorder表 期望orderamount+++++"
						+ data.get("excepted_orderamount"));
				if (data.get("excepted_orderamount") == null
						|| data.get("excepted_orderamount") == "") {
					System.out.println("数据驱动未配置excepted_orderamount，请检查");
				}
				Reporter.log("+++++paymentorder表 实际orderamount+++++" + orderamount);
				Reporter.log("检查orderamount是否匹配",
						compareString(data.get("excepted_orderamount"), orderamount));
		
		return peAllDto;
	}
	
	
	
	/**
	 * @param sequenceid
	 * @return 收款方手续费、付款方手续费、是否计费
	 */
	public PeDbItem calcPrice(Long sequenceid) {
		PeDbItem calcPriceDto = new PeDbItem();
		try {
			Oracle db =dbConn.getDBConn();
			String payeeFee = db
					.query("Select d.Payeefee From Seashell.Deal d Where d.Orderseqid = " + sequenceid + "");
			String payerFee = db
					.query("Select d.Payerfee From Seashell.Deal d Where d.Orderseqid = " + sequenceid + "");
			String hascaCulatedPrice = db.query("Select d.Hascaculatedprice From Seashell.Deal d Where d.Orderseqid = "
					+ sequenceid + "");
			calcPriceDto.setPayerFee(payerFee);
			calcPriceDto.setPayeeFee(payeeFee);
			calcPriceDto.setHasCaculatedPrice(hascaCulatedPrice);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calcPriceDto;
	}

	/**
	 * @param dealid
	 * @return 交易状态
	 */
	public PeDbItem dealStatus(String dealid) {
		PeDbItem dealStatusDto = new PeDbItem();
		try {
			Oracle db =dbConn.getDBConn();
			Integer dealStatus = Integer.parseInt(db.query("Select Dealstatus From Seashell.Deal Where Dealid = '"
					+ dealid + "'"));
			dealStatusDto.setDealStatus(dealStatus);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dealStatusDto;
	}

	/**
	 * @param sequenceid
	 * @return 订单状态
	 */
	public PeDbItem orderStatus(String sequenceid) {
		PeDbItem orderStatusDto = new PeDbItem();
		try {
			Oracle db = dbConn.getDBConn();
			//			Oracle db = DbConn.getDBConnDev();
			Integer orderStatus = Integer.parseInt(db
					.query("Select Orderstatus From Seashell.Paymentorder Where Sequenceid = " + sequenceid + ""));
			orderStatusDto.setOrderStatus(orderStatus);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderStatusDto;
	}

	/**
	 * @param dealid
	 * @return 收款方手续费、付款方手续费
	 */
	public PeDbItem dealFee(String dealid) {
		PeDbItem dealFeeDto = new PeDbItem();
		try {
			Oracle db = dbConn.getDBConn();
			String payeeFee = db.query("Select d.Payeefee From Seashell.Deal d Where d.Dealid = '" + dealid + "'");
			String payerFee = db.query("Select d.Payerfee From Seashell.Deal d Where d.Dealid = '" + dealid + "'");
			dealFeeDto.setPayerFee(payerFee);
			dealFeeDto.setPayeeFee(payeeFee);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dealFeeDto;
	}

	/**
	 * @param sequenceid
	 * @return 订单摘要
	 */
	public PeDbItem orderDigest(String sequenceid) {
		PeDbItem orderDigestDto = new PeDbItem();
		try {
			Oracle db = dbConn.getDBConn();
			String orderDigest = (db.query("Select Orderdigest From Seashell.Paymentorder Where Sequenceid = "
					+ sequenceid + ""));
			orderDigestDto.setOrderDigest(orderDigest);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDigestDto;
	}

	/**
	 * @param sequenceid
	 * @return 机构订单号
	 */
	public PeDbItem orderOrgOrderId(String sequenceid) {
		PeDbItem orderOrgOrderIdDto = new PeDbItem();
		try {
			Oracle db =dbConn.getDBConn();
			String orderOrgOrderId = (db.query("Select Orgorderid From Seashell.Paymentorder Where Sequenceid = "
					+ sequenceid + ""));
			orderOrgOrderIdDto.setOrderOrgOrderId(orderOrgOrderId);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderOrgOrderIdDto;
	}

	/**
	 * @param dealStatus
	 * @param orderCode
	 * @param memberAcctCode
	 * @param fromDate
	 * @param endDate
	 * @return 交易金额汇总
	 */
	public PeDbItem dealAmount(Integer dealStatus, Integer orderCode, String memberAcctCode, String fromDate,
			String endDate) {
		PeDbItem dealAmountDto = new PeDbItem();
		try {
			Oracle db = dbConn.getDBConn();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT sum(d.dealAmount) FROM seashell.deal d, seashell.paymentorder p WHERE d.orderseqid = p.sequenceid");
			if (null != dealStatus) {
				sql.append(" AND d.dealStatus = " + dealStatus + " ");
			}
			if (null != orderCode) {
				sql.append(" AND p.ordercode = " + orderCode + "");
			}
			if (null != memberAcctCode) {
				sql.append(" AND d.payeeAcctCode = " + memberAcctCode + " ");
			}
			if (null != fromDate) {
				sql.append(" AND d.dealbeginDate > to_date('" + fromDate + "','yyyy-MM-dd')");
			}
			sql.append(" AND d.dealbeginDate < to_date('" + endDate + "','yyyy-MM-dd')");
			Reporter.log("sql：" + sql.toString(), true);

			long dealAmount = Long.parseLong(db.query(sql.toString()));
			dealAmountDto.setDealAmount(dealAmount);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dealAmountDto;
	}

	public PeDbItem OrderCount(Long sequenceid, Integer orderStatus, Long orderAmount, Integer orderCode,
			Integer payMethod, Integer isReversed, String currencyCode) {
		PeDbItem orderCountDto = new PeDbItem();
		try {
			Oracle db = dbConn.getDBConn();
			//			Oracle db = DbConn.getDBConnDev();
			String sql = "Select Count(*) From Seashell.Paymentorder p Where p.Sequenceid = " + sequenceid
					+ " And p.Orderstatus = " + orderStatus + " And p.Orderamount = " + orderAmount
					+ " And p.Ordercode = " + orderCode + " And p.Paymethod = " + payMethod + " And p.Isreversed = "
					+ isReversed + " And p.Currencycode = '" + currencyCode + "'";
			Reporter.log("sql：" + sql, true);
			Integer orderCount = Integer.parseInt(db.query(sql));
			orderCountDto.setOrderCount(orderCount);
			Reporter.log("orderCount：" + orderCount, true);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderCountDto;
	}

	/**
	 * @param
	 * @return orderId
	 */
	public PeDbItem orderId() {
		PeDbItem orderIdDto = new PeDbItem();
		try {
			Oracle db =dbConn.getDBConn();
			String orderId = (db.query("Select Accounting.Seq_Bpslog_Id.Nextval From Dual"));
			orderIdDto.setOrderId(orderId);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderIdDto;
	}

	/**
	 * @param
	 * @return transactionDate
	 */
	public PeDbItem transactionDate() {
		PeDbItem transactionDateDto = new PeDbItem();
		try {
			Oracle db =dbConn.getDBConn();
			String transactionDate = (db.query("Select Max(Transaction_Date) From Accounting.t_Transaction_Date"));
			Date transactiondate = new SimpleDateFormat("yyyyMMdd").parse(transactionDate);
			transactionDateDto.setTransactionDate(transactiondate);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionDateDto;
	}

	/**
	 * @param
	 * @return sequenceId
	 */
	public PeDbItem sequenceId() {
		PeDbItem sequenceIdDto = new PeDbItem();
		try {
			Oracle db =dbConn.getDBConn();
			String sequenceId = (db.query("Select Seashell.Seq_Paymentorder_Id.Nextval From Dual"));
			sequenceIdDto.setSequenceId(sequenceId);
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequenceIdDto;
	}
	
	public void updateTTransactionDateStatus(String dealTransactiondate) {
		try {
			String dealTransactiondateNew = dealTransactiondate.replace("-", "");
			Oracle db = dbConn.getDBConn();
			db.Update("Update ACCOUNTING.T_Transaction_Date t Set t.status = 2 Where t.transaction_date = '"+ dealTransactiondateNew +"'");
			db.closeDBcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DbConn getDbConn() {
		return dbConn;
	}

	public void setDbConn(DbConn dbConn) {
		this.dbConn = dbConn;
	}
	
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @Description:比较两个字符串 ， 考虑为null的情况
	 */
	public static boolean compareString(String a , String b){
		if (a==null || a==""){
			return true;
		}else{
			return StringUtil.null2String(a).equals(StringUtil.null2String(b));
		}
		
	}
}