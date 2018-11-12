package com.bill99.fi.common.utils;

import java.util.regex.Pattern;

import org.testng.Reporter;

import shelper.db.Oracle;
import shelper.environment.Environment;
import shelper.iffixture.HttpFixture;

/**
 * 获取internal 短信验证码，使用时需判断返回值，如果是null，请重新获取，如果是6位数字，则直接使用
 * 
 * @author kaiquan.jiang
 * 
 */
public class Mobile_Verification {
	private String orderid;
	private static String validate;
	private final Oracle o1;

	public Mobile_Verification() {
		o1 = new Oracle(Environment.get("db.cps02.connect"),
				Environment.get("db.cps02.username"),
				Environment.get("db.cps02.password"));
	}

	/**
	 * @param orderid
	 *            订单号
	 * @return
	 */
	public void verification() {
		String pan = null;
		if (orderid != null && !(orderid.equals(""))) {
			int i = 0;
			do {
				try {
					// pan=o1.query("select DYN_PWD  from maspos.t_dpwd_ctrl where  ref_no='9236326617573266'  and rownum=1");
					pan = o1.query("select DYN_PWD  from maspos.t_dpwd_ctrl where ref_no='"
							+ orderid
							+ "'  And crt_time>Sysdate-5/1440  and rownum=1");
					i++;

					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}
			} while (pan == null && i < 20);
			o1.closeDBcon();
			if (pan != null) {
				System.out.println("pan:" + pan);
				HttpFixture requery = new HttpFixture();
				requery.setUrl("http://192.168.52.219:8083/mock/internal/mtp/decryptTxnInfo.htm");
				requery.addHeaderValue("Content-Type",
						"application/x-www-form-urlencoded");
				requery.addRequestBody("pan="
						+ pan
						+ "&postUrl=http://192.168.52.217:8092/mas/internal/WsMtpService");
				requery.Post();
				String temp = requery.getResponseBody();
				// System.out.println(temp);
				String str = temp.substring(temp.length() - 6, temp.length());
				// 正则表达式
				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(str).matches()) {
					validate = str;
				} else {
					validate = null;
				}
			} else {
				validate = null;
			}

		} else {
			System.out.println("orderid为空！");
		}

	}

	public void setOrderid(String Orderid) {
		this.orderid = Orderid;
	}

	public String getValidate() {
		return this.validate;
	}

	public static void main(String srg[]) throws Exception {

		Environment.set4If();
		Mobile_Verification str = new Mobile_Verification();
		str.setOrderid("9236326617573266");
		str.verification();
		str.getValidate();
		System.out.println(validate);
	}

}
