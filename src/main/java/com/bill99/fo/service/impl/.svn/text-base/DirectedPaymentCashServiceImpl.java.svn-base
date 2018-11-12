package com.bill99.fo.service.impl;

import org.openqa.selenium.WebDriver;

import com.bill99.fo.common.dto.DirectedPaymentData;
import com.bill99.fo.pageobject.DirectedPaymentCashPage;
import com.bill99.fo.service.DirectedPaymentCashService;


public class DirectedPaymentCashServiceImpl implements
		DirectedPaymentCashService {

	@Override
	public String processRequest(WebDriver dr,
			DirectedPaymentData directedPaymentData) {
		DirectedPaymentCashPage directedPaymentCashPage = new DirectedPaymentCashPage(
				dr);

		return directedPaymentCashPage.submitRequest(directedPaymentData);
	}

}
