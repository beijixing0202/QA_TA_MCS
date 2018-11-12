package com.bill99.cps.service.check;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

//import org.apache.commons.io.filefilter.AndFileFilter;
import org.testng.Reporter;

import shelper.db.Oracle;

import com.bill99.cps.common.tools.SercurityUtil;
import com.bill99.cps.common.utils.MgwFinal;
import com.bill99.cps.orm.OracleDao;
//import com.bill99.cps.service.DBOracleCheck;
import com.bill99.qa.ta.security.ssm.service.SecuritySsmService;
import com.bill99.seashell.common.util.StringUtil;

public class CpsDbCheck {
	
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
	

	// CNP交易数据库查询交易 by 外部跟踪编号
	public String exRefDBcheck(String externalRefNumber,
			Map<String, String> data) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String queryStr = "select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where ext_trace_no = '"
				+ externalRefNumber + "'" + "order by id_txn_ctrl desc";
		System.out.println(new Date());
		String b3 = o1.query(queryStr);
		o1.closeDBcon();
		Reporter.log(queryStr);
		// if(b3 != null && b3.length() > 0);
		Reporter.log("select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where ext_trace_no = '"
				+ externalRefNumber + "'" + "order by id_txn_ctrl desc");

		// 分割取结果字段
		String[] splitresult = b3.split(";");
		String txn_flg = splitresult[1];
		String txn_type = splitresult[2];
		String app_type = splitresult[3];
		// String tunnel_data=splitresult[4];
		// String auth_net_id=splitresult[5];
		Reporter.log("+++++maspos表 期望txn_flg+++++"
				+ data.get("excepted_txn_flg"));
		if (data.get("excepted_txn_flg") == null
				|| data.get("excepted_txn_flg") == "") {
			System.out.println("数据驱动未配置excepted_txn_flg，请检查");
		}
		Reporter.log("+++++maspos表 实际txn_flg+++++" + txn_flg);
		Reporter.log("检查txn_flg是否匹配",
				compareString(data.get("excepted_txn_flg"), txn_flg));

		Reporter.log("+++++maspos表 期望txn_type+++++"
				+ data.get("excepted_txn_type"));
		if (data.get("excepted_txn_type") == null
				|| data.get("excepted_txn_type") == "") {
			System.out.println("数据驱动未配置excepted_txn_type，请检查");
		}
		Reporter.log("+++++maspos表 实际txn_type+++++" + txn_type);
		Reporter.log("检查txn_type是否匹配",
				compareString(data.get("excepted_txn_type"), txn_type));

		Reporter.log("+++++maspos表 期望app_type+++++"
				+ data.get("excepted_app_type"));
		if (data.get("excepted_app_type") == null
				|| data.get("excepted_app_type") == "") {
			System.out.println("数据驱动未配置excepted_app_type，请检查");
		}
		Reporter.log("+++++maspos表 实际app_type+++++" + app_type);
		Reporter.log("检查app_type是否匹配",
				compareString(data.get("excepted_app_type"), app_type));

		return b3;
	}

	// 合并支付权益数据库校验
	public String WithPayEquityDBcheck(String externalRefNumber,
			Map<String, String> datadriven) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String queryStr = "select main_status,swipe_amt,point_amt from maspos.t_withpay_order where insurance_order_no = '"
				+ externalRefNumber + "'" + "order by id_txn_ctrl desc";
		System.out.println(new Date());
		String b3 = o1.query(queryStr);
		o1.closeDBcon();
		Reporter.log(queryStr);
		String[] splitresult = b3.split(";");
		String main_status = splitresult[0];
		if (main_status
				.equalsIgnoreCase(datadriven.get("excepted_main_status"))) {
			Reporter.TRUE("RRRRR数据库main_status检查正常");
		} else {
			Reporter.FALSE("NNNNN数据库main_status检查异常");
		}

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
	public String getsysorderandsmsValidTime() {
		 Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String b1 = o1.query("select name,value from  maspos.t_sys_para where name in ('orderValidTime','smsValidTime')");
		o1.closeDBcon();
		return b1;
	}
	//短信支付bizlog
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
	
	
	/**
	 * 
	 * @param externalRefNumber
	 * @Description:CNP交易查询by外部跟踪编号,返回交易编号，供mcs接口使用
	 */
	public String extTraceNo2IdTxn(String externalRefNumber) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String queryStr = "select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where ext_trace_no = '"
				+ externalRefNumber + "'" + "order by id_txn_ctrl desc";
		System.out.println(new Date());
		String b3 = o1.query(queryStr);
		o1.closeDBcon();	
		Reporter.log(queryStr);
		// if(b3 != null && b3.length() > 0);
		Reporter.log("select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where ext_trace_no = '"
				+ externalRefNumber + "'" + "order by id_txn_ctrl desc");	
		//分割取结果字段
		String[] splitresult = b3.split(";");
		String id_txn = splitresult[0];
//		String txn_flg = splitresult[1];
//		String txn_type=splitresult[2];
//		String app_type=splitresult[3];
//		String tunnel_data=splitresult[4];
//		String auth_net_id=splitresult[5];
		
		return id_txn;
	}
	
	
	/**
	 * 
	 * @param idTxnCtrl
	 * @Description:CNP交易查询by交易控制编号,返回交易编号，供mcs接口使用
	 */
	public String IdTxnCtrl2IdTxn(String idTxnCtrl) {
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
		//分割取结果字段
		String[] splitresult = b3.split(";");
		String id_txn = splitresult[0];
//		String txn_flg = splitresult[1];
//		String txn_type=splitresult[2];
//		String app_type=splitresult[3];
//		String tunnel_data=splitresult[4];
//		String auth_net_id=splitresult[5];
		
		return id_txn;
	}
	
	
	/**
	 * 
	 * @param data
	 * @Description: CNP交易数据库检查
	 */

	public String cnpDbcheck( Map<String, String> data) {
		Oracle o1 = oracleDao.getInstance(MgwFinal.DB_TYPE_ORCLE_CPS);
		String id_txn=data.get("id_txn");
		String queryStr = "select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where id_txn = '"
				+ id_txn + "'" + "order by id_txn_ctrl desc";
		System.out.println(new Date());
		String b3 = o1.query(queryStr);
		o1.closeDBcon();	
		Reporter.log(queryStr);
		// if(b3 != null && b3.length() > 0);
		Reporter.log("select id_txn,txn_flg,txn_type,app_type,tunnel_data,auth_net_id from maspos.t_txn_ctrl where id_txn = '"
				+ id_txn + "'" + "order by id_txn_ctrl desc");
		//分割取结果字段
		String[] splitresult = b3.split(";");
		String txn_flg = splitresult[1];
		String txn_type=splitresult[2];
		String app_type=splitresult[3];
//		String tunnel_data=splitresult[4];
//		String auth_net_id=splitresult[5];
		Reporter.log("+++++maspos表 期望txn_flg+++++"+data.get("excepted_txn_flg"));
		if (data.get("excepted_txn_flg")==null||data.get("excepted_txn_flg")==""){
			System.out.println("数据驱动未配置excepted_txn_flg，请检查");
		}
		Reporter.log("+++++maspos表 实际txn_flg+++++"+txn_flg);
		Reporter.log("检查txn_flg是否匹配", compareString(data.get("excepted_txn_flg"), txn_flg));
		
		Reporter.log("+++++maspos表 期望txn_type+++++"+data.get("excepted_txn_type"));
		if (data.get("excepted_txn_type")==null||data.get("excepted_txn_type")==""){
			System.out.println("数据驱动未配置excepted_txn_type，请检查");
		}
		Reporter.log("+++++maspos表 实际txn_type+++++"+txn_type);
		Reporter.log("检查txn_type是否匹配", compareString(data.get("excepted_txn_type"), txn_type));
		
		Reporter.log("+++++maspos表 期望app_type+++++"+data.get("excepted_app_type"));
		if (data.get("excepted_app_type")==null||data.get("excepted_app_type")==""){
			System.out.println("数据驱动未配置excepted_app_type，请检查");
		}
		Reporter.log("+++++maspos表 实际app_type+++++"+app_type);
		Reporter.log("检查app_type是否匹配", compareString(data.get("excepted_app_type"), app_type));
		
		return b3;
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
