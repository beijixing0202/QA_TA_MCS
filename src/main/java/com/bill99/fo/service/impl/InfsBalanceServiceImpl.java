package com.bill99.fo.service.impl;

import com.bill99.fo.common.httpclient.HttpInfsBalance;
import com.bill99.fo.service.InfsBalanceService;

public class InfsBalanceServiceImpl implements InfsBalanceService {

	@Override
	public void chargeMoneyRmb(String memberCode, String newBalance) {
		// TODO Auto-generated method stub
		HttpInfsBalance infsBalance = new HttpInfsBalance();
		infsBalance.chargeMoneyRmb(memberCode, newBalance);
	}

}
