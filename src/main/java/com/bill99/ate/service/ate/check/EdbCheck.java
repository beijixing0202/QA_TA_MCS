package com.bill99.ate.service.ate.check;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;

import com.bill99.ate.service.ebd.CouponsQueryService;
import com.bill99.seashell.common.util.StringUtil;

public class EdbCheck {

	private CouponsQueryService couponsQueryService;

	private static Integer count = 0;

	private static String voucherNo = "";

	public boolean check(Map<String, String> data) {
		boolean edbResult = true;
		try {
			if (!StringUtil.isEmpty(data.get("checkEdb"))) {
				String checkEdb = data.get("checkEdb");
				if (checkEdb.equals("back")) {
					edbResult = checkBack(data);
				} else if (checkEdb.equals("reactive")) {
					edbResult = checkReactive(data);
				} else if (checkEdb.equals("active")) {

				}
			}
		} catch (Exception e) {
			edbResult = false;
			e.printStackTrace();
		}
		return edbResult;
	}

	public boolean checkCancel(Map<String, String> data) {
		boolean edbResult = true;
		try {
			if (!StringUtil.isEmpty(data.get("checkEdb"))) {
				String checkEdb = data.get("checkEdb");
				if (checkEdb.equals("back")) {
					edbResult = checkBackCancel(data);
				} else if (checkEdb.equals("reactive")) {

				} else if (checkEdb.equals("active")) {

				}
			}
		} catch (Exception e) {
			edbResult = false;
			e.printStackTrace();
		}
		return edbResult;
	}

	/**
	 * 
	 * @param data
	 * @throws IOException
	 * @throws URISyntaxException
	 * @Description: 检查被动券 ， 需检查历史券
	 */
	public boolean checkReactive(Map<String, String> data) throws IOException, URISyntaxException {
		data.put("edbStatus", "1");
		Map<String, Object> result = couponsQueryService.query(data);
		List<Map<String, String>> list = (List<Map<String, String>>) result.get("couponsList");
		if (list.size() > 0) {
			Map<String, String> coupons = list.get(0);
			String amount = coupons.get("amount");
			String couponStatus = coupons.get("couponStatus");
			boolean newCoupon = checkTime(coupons.get("couponDate"));
			if (!newCoupon) {
				Reporter.log("券时间不正确");
				return false;
			}
			boolean amountRight = AteResultCheck.compareString(amount, data.get("couponAmount"));
			boolean couponStatusRight = AteResultCheck.compareString(couponStatus, data.get("couponStatus"));
			Reporter.log("期望券金额 ： " + data.get("couponAmount") + " , 实际券金额 : " + amount);
			Reporter.log("期望券状态 ： " + data.get("couponStatus") + " , 实际券状态 : " + couponStatus);
			return amountRight && couponStatusRight;
		} else {
			Reporter.log("无满足条件的券");
			return false;
		}
	}

	/**
	 * 
	 * @param data
	 * @throws IOException
	 * @throws URISyntaxException
	 * @Description:检查返券 ， 需检查可用券
	 */
	public boolean checkBack(Map<String, String> data) throws IOException, URISyntaxException {
		data.put("edbStatus", "0");
		Map<String, Object> result = couponsQueryService.query(data);
		List<Map<String, String>> list = (List<Map<String, String>>) result.get("couponsList");
		if (list !=null &&list.size() > 0) {
			count = list.size();
			Map<String, String> coupons = list.get(0);
			voucherNo = coupons.get("voucherNo");
			String amount = coupons.get("amount");
			String couponStatus = coupons.get("couponStatus");
			boolean newCoupon = checkTime(coupons.get("couponDate"));
			if (!newCoupon) {
				Reporter.log("券时间不正确");
				return false;
			}
			boolean amountRight = AteResultCheck.compareString(amount, data.get("couponBackAmount"));
			boolean couponStatusRight = AteResultCheck.compareString(couponStatus, data.get("couponBackStatus"));
			Reporter.log("返券期望券金额 ： " + data.get("couponBackAmount") + " , 返券实际券金额 : " + amount);
			Reporter.log("返券期望券状态 ： " + data.get("couponBackStatus") + " , 返券实际券状态 : " + couponStatus);
			return amountRight && couponStatusRight;
		} else {
			Reporter.log("无满足条件的券");
			return false;
		}
	}

	/**
	 * 
	 * @param data
	 * @throws IOException
	 * @throws URISyntaxException
	 * @Description:检查返券撤销 ， 检查可用券中是否减少
	 */
	public boolean checkBackCancel(Map<String, String> data) throws IOException, URISyntaxException {
		data.put("edbStatus", "0");
		Map<String, Object> result = couponsQueryService.query(data);
		List<Map<String, String>> list = (List<Map<String, String>>) result.get("couponsList");
		//检查可用券数量是否减一
		if (1 != (count - list.size())) {
			return false;
		}
		if (list !=null && list.size() > 0) {
			Map<String, String> coupons = list.get(0);
			//检查返券券号是否不存在
			if(coupons!=null && voucherNo.equals(coupons.get("coupons"))){
				return false;
			}
			return true;
		} else {
			return true;
		}
	}

	private boolean checkTime(String couponDate) {
		boolean result = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date coupon = sdf.parse(couponDate);
			long couponTime = coupon.getTime();
			if ((System.currentTimeMillis() - couponTime) > 10000) {
				result = false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void setCouponsQueryService(CouponsQueryService couponsQueryService) {
		this.couponsQueryService = couponsQueryService;
	}

}
