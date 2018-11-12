package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import mas.channel.internal.client.WsMtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shelper.iffixture.HttpFixture;

import com.bill99.cps.orm.AccessDB;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.cps.service.InternalService;
import com.bill99.qa.framework.testcase.BaseTestCase;
import com.caucho.hessian.client.HessianProxyFactory;

/**
 * cnp短信支付
 * 
 * @author kaiquan.jiang
 * 
 */
public class MgwSmsPay extends BaseTestCase {

	@Resource
	private HttpContentService httpContentService;
	@Resource
	private InternalService internalService;
	@Resource
	private ConverMgwDataDriverService converMgwDataDriverService;
	@Resource
	private DBOracleCheck dbOracleCheck;
	@Autowired
	private AccessDB accessDB;

	// @Resource
	// private SysParaManager sysParaManager;

	// CNP短信支付
	@SuppressWarnings("unused")
	@Test(dataProvider = "mgwCnpSmsPay", description = "cnp短信支付", timeOut = 200000)
	public void smsPay(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));
		// 更新系统参数（订单有效时间，短信有效时间，短信重发次数）
		// SysParaManager sysParaManager = new SysParaManagerImpl();
		// sysParaManager.setString(SysParaNames.SMS_RESEND_COUNT,
		// datadriven.get("smsResendCount"));
		// dbOracleCheck.updatesyspara(datadriven.get("sysorderValidTime"),
		// datadriven.get("syssmsValidTime"),datadriven.get("smsResendCount"));

		// http 发送报文
		String respose = httpContentService
				.cnpContent(converMgwDataDriverService.ConverData(datadriven));

		Reporter.log("respose=" + respose);

		// 实际结果与预期结果比较
		// boolean a1 = respose.contains(datadriven.get("exceptedmessage")) &&
		// respose.contains("<responseCode>C0</responseCode>");
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);

		// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，数据库检查
		if (a1 && datadriven.get("comment").contains("cnp短信支付正常流程")
				&& respose.contains("<responseCode>C0</responseCode>")) {

			String smspayvalidcode = dbOracleCheck.SmsPayValidCode(datadriven
					.get("externalRefNumber"));

			String internalmockurl = internalService.getInternalmockurl();

			// String internalmockurl = httpContentService.getInternalmockurl();
			String url = internalmockurl + "/mas/internal/WsMtpService";

			HessianProxyFactory factory = new HessianProxyFactory();
			factory.setOverloadEnabled(true);
			WsMtpService service = null;

			try {
				service = (WsMtpService) factory
						.create(WsMtpService.class, url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			String smsvalidcode = service.decryptTxnInfo(smspayvalidcode);

			// System.out.println(smsvalidcode);

			String smspaycommit = internalmockurl
					+ "/mas/internal/notifySMS?mobile="
					// String smspaycommit =
					// "http://192.168.63.217:8092/mas/internal/notifySMS?mobile="
					+ datadriven.get("phone") + "&content=" + smsvalidcode
					+ "&moTime=" + datadriven.get("entryTime");
			System.out.println("smspaycommit" + smspaycommit);
			// String url_code =
			// "http://192.168.63.217:8092/mas/internal/notifySMS?mobile=18221593341&content=863665&moTime=20130521130101";
			String id = null;
			while ("".equals(id) || null == id) {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);
						System.out.println(new Date());
						id = dbOracleCheck.smsCheck(datadriven.get("phone"),
								smsvalidcode);
						/**
						 * 查到id后跳出循环 
						 * @author chongpeng.yan
						 */
						if (id!=null &&!"".equals(id)) {
							Reporter.log("sms控制表已经落地" + id);
							break;
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				/*if (id!=null &&!"".equals(id)) {
					Reporter.log("sms控制表已经落地" + id);
					break;
				}*/
			}
			HttpFixture hf = new HttpFixture();
			hf.setUrl(smspaycommit);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			hf.Post();
			boolean aaa = (datadriven.get("orderValidTime").isEmpty() && datadriven
					.get("smsValidTime").isEmpty());
			if (aaa == false) {
				String b4 = dbOracleCheck.MoasDBcheck(datadriven
						.get("externalRefNumber"));
				String[] splitresult3 = b4.split(";");
				String tunnel_data = splitresult3[2];
				if (tunnel_data.contains("orderValidTime")
						|| tunnel_data.contains("smsValidTime")) {
					// if
					// (tunnel_data.equalsIgnoreCase(datadriven.get("excepted_app_type")))
					// {
					Reporter.TRUE("moas数据库交易表tunnel_data检查正常");
				} else {
					Reporter.FALSE("moas数据库交易表tunnel_data检查异常");
				}
			}

			String b3 = accessDB.purchaseDBcheck(datadriven
					.get("externalRefNumber"));

			String[] splitresult2 = b3.split(";");
			String txn_flg = splitresult2[0];
			String txn_type = splitresult2[1];
			String app_type = splitresult2[2];

			int i = 0;
			while (txn_flg.equalsIgnoreCase("p") || txn_type.equals("00100")) {
				if (i == 20) {
					Reporter.log("30秒后落库仍失败");
					break;
				}
				try {
					Thread.sleep(3000);
					i++;

					b3 = accessDB.purchaseDBcheck(datadriven
							.get("externalRefNumber"));
					splitresult2 = b3.split(";");
					txn_flg = splitresult2[0];
					txn_type = splitresult2[1];
					app_type = splitresult2[2];

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
				Reporter.TRUE("mas数据库交易表txn_flg检查正常");
			} else {
				Reporter.log("txn_flg 预期 : "
						+ datadriven.get("excepted_txn_flg"));
				Reporter.FALSE("mas数据库交易表txn_flg检查异常" + b3);
			}
			if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
				Reporter.TRUE("mas数据库交易表txn_type检查正常");
			} else {
				Reporter.log("txn_type 预期 : "
						+ datadriven.get("excepted_txn_type"));
				Reporter.FALSE("mas数据库交易表txn_type检查异常" + b3);
			}
			if (app_type.equalsIgnoreCase(datadriven.get("excepted_app_type"))) {
				Reporter.TRUE("mas数据库交易表app_type检查正常");
			} else {
				Reporter.log("app_type 预期 : "
						+ datadriven.get("excepted_app_type"));
				Reporter.FALSE("mas数据库交易表app_type检查异常" + b3);
			}
			if (datadriven.get("comment").contains("cnp短信支付正常流程")
					&& datadriven.get("excepted_phase").contains(
							dbOracleCheck.getsmspaytbizlog(
									datadriven.get("externalRefNumber"),
									datadriven.get("tais_route_id")))) {
				Reporter.TRUE("biz日志表检查正常");
			} else {
				Reporter.FALSE("biz日志表检查异常");
			}
		}
		Reporter.end(datadriven.get("comment"));
	}

	@DataProvider(name = "mgwCnpSmsPay")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "mgwCnpSmsPay");
	}

	@AfterClass
	public void afterClass() {
	}

}