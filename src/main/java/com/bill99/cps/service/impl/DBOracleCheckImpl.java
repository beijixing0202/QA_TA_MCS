package com.bill99.cps.service.impl;

import java.util.Date;

import javax.annotation.Resource;

//import org.apache.commons.io.filefilter.AndFileFilter;
import org.testng.Reporter;

import shelper.db.Oracle;

import com.bill99.cps.common.tools.SercurityUtil;
import com.bill99.cps.common.utils.MgwFinal;
import com.bill99.cps.orm.OracleDao;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.qa.ta.security.ssm.service.SecuritySsmService;

public class DBOracleCheckImpl implements DBOracleCheck {
	
	private OracleDao oracleDao;
	
	private String keyString;
	
	@Resource
	SecuritySsmService securitySsmService;
	

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public void setOracleDao(OracleDao oracleDao) {
		this.oracleDao = oracleDao;
	}

	// CNP交易数据库查询交易状态   轮询时间15秒
	@Override
	/*public boolean  PurchaseDBcheck(String externalRefNumber) {
		boolean b2 = o1.queryCheckInTime("select txn_flg from maspos.t_txn_ctrl where ext_trace_no = '"
						+ externalRefNumber + "'", "S", 15);
		boolean b2 = o1.queryCheckInTime("select txn_flg,txn_type from maspos.t_txn_ctrl where ext_trace_no = '"
				+ externalRefNumber + "'", "S;00200", 15);
		return b2;
		
	}*/

	// CNP交易数据库查询交易
	public String  PurchaseDBcheck(String externalRefNumber) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String queryStr = "select txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where ext_trace_no = '"
				+ externalRefNumber + "'" +"order by id_txn_ctrl desc";
		System.out.println(new Date());
		String b3 = o1.query(queryStr);
		o1.closeDBcon();
		Reporter.log(queryStr);
//		if(b3 != null && b3.length() > 0);
		Reporter.log("select txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where ext_trace_no = '"
				+ externalRefNumber + "'" +"order by id_txn_ctrl desc");
			return b3;
	}
	// 合并支付权益数据库校验
	public String  WithPayEquityDBcheck(String externalRefNumber) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String queryStr = "select main_status,swipe_amt,point_amt from maspos.t_withpay_order where insurance_order_no = '"
				+ externalRefNumber + "'" +"order by id_txn_ctrl desc";
		System.out.println(new Date());
		String b3 = o1.query(queryStr);
		o1.closeDBcon();
		Reporter.log(queryStr);
//		if(b3 != null && b3.length() > 0);
		Reporter.log("select main_status,swipe_amt,point_amt from maspos.t_withpay_order where insurance_order_no = '"
				+ externalRefNumber + "'" +"order by id_txn_ctrl desc");
			return b3;
	}
	
	//查询短信控制表
	public String smsCheck(String phone_no,String dyn_pwd)
	{
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		
		String sql = "select id from maspos.t_sms_ctrl where phone_no='"+securitySsmService.encryptMessage(phone_no)+ "' and dyn_pwd=" + dyn_pwd;
		String id = o1.query(sql);
		o1.closeDBcon();
		Reporter.log(sql);
		return id;
	}
	//获取moas状态
	public String  MoasDBcheck(String externalRefNumber) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);

		String queryString = "select cmd_code,point_amt,tunnel_data,txn_type,amt,status from maspos.t_order_acquired where order_no = '"
				+ externalRefNumber + "'";
		
		String b3 = o1.query(queryString);
		o1.closeDBcon();		
		Reporter.log(queryString);

//		if(b3 != null && b3.length() > 0);
			return b3;
	}
	
	//获取短信支付验证码
	public String  SmsPayValidCode(String externalRefNumber) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String b3 = o1.query("select dyn_pwd from  maspos.t_dpwd_ctrl where ref_no= '"
				+ externalRefNumber + "'");
		o1.closeDBcon();
			return b3;
	}
	
	
	
	// CCS交易数据库查询交易
		public String  CCSPurchaseDBcheck(String externalRefNumber) {
			Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CCS);
			String b3 = o1.query("select order_txn_info,txn_flag,rep_code from t_order_txn where order_txn_id = '"
					+ externalRefNumber + "'");
			o1.closeDBcon();
				return b3;
		}
	
	// 获取手机校证码
	@Override
	public String getValidCode(String ref_no,String phone_no) {
		 String	validCode="";
		 Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
//		validCode = o1.query("select dyn_pwd from (select dyn_pwd from maspos.t_dpwd_ctrl where crt_time>=sysdate - interval '1' day order by crt_time desc) where rownum=1");
		 validCode = o1.query("select dyn_pwd from (select dyn_pwd,ref_no,phone_no from maspos.t_dpwd_ctrl where crt_time>=sysdate - interval '1' day) t  where t.ref_no='"+ ref_no + "' and t.phone_no='"+ securitySsmService.encryptMessage(phone_no) + "'");
		 String validCode22 = SercurityUtil.decryptTxnInfo(validCode,keyString);
		o1.closeDBcon();
		return validCode22;
	}

	// 更改消费结帐状态
	@Override
	public boolean updateTerm_Recon_flg(String refNumber) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		boolean b3 = o1.Update("update maspos.t_txn_ctrl set term_recon_flg = 'C'  where id_txn = ' "+ refNumber + "'");
//		boolean b3 = o1.Update("update maspos.test set COL1 = 'C'  where ID = ' "+ refNumber + "'");
//		String b3 = o1.query("select * from test where ID = ' "+ refNumber + "'");
//		System.out.println(refNumber);
		o1.closeDBcon();
		return b3;
	}

	//获取系统参数（订单有效时间，短信有效时间）
	@Override
	public String getsysorderandsmsValidTime() {
		 Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String b1 = o1.query("select name,value from  maspos.t_sys_para where name in ('orderValidTime','smsValidTime')");
		o1.closeDBcon();
		return b1;
	}

	@Override
	public String getsmspaytbizlog(String externalRefNumber,String biz_type) {
		 Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		 String b1 = o1.query("select phase from  maspos.t_biz_log where ext_trace_no = '"+ externalRefNumber + "'and biz_type='"+biz_type+"' order by id_biz_log" );
		 o1.closeDBcon();
		return b1;
	}

//	@Override
//	public boolean updatesyspara(String orderValidTime,String smsValidTime,String smsResendCount) {
//		 Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
//		 boolean b=false;
//		boolean b1 = o1.Update("update  maspos.t_sys_para  set value = '"+ orderValidTime +"' where name = 'orderValidTime'");
//		boolean b2 = o1.Update("update  maspos.t_sys_para  set value = '"+ smsValidTime +"' where name = 'smsValidTime'");
//		boolean b3 = o1.Update("update  maspos.t_sys_para  set value = '"+ smsResendCount +"' where name = 'smsResendCount'");
//		o1.closeDBcon();
//		if(b1&&b2&&b3){
//			b=true;
//		}
//		return b;
//	}
	
	// CNP交易数据库查询 by 交易控制编号
	public String IdTxnCtrlDBcheck(String idTxnCtrl) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String queryStr = "select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where id_txn_ctrl = '"
				+ idTxnCtrl + "'" + "order by id_txn_ctrl desc";
		System.out.println(new Date());
		String b3 = o1.query(queryStr);
		o1.closeDBcon();
		Reporter.log(queryStr);
		// if(b3 != null && b3.length() > 0);
		Reporter.log("select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where id_txn_ctrl = '"
				+ idTxnCtrl + "'" + "order by id_txn_ctrl desc");
		return b3;
	}
	
}
