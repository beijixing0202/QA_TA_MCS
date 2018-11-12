package com.bill99.fo.service;

import org.openqa.selenium.WebDriver;

import com.bill99.fo.common.dto.DirectedPaymentData;


public interface DirectedPaymentCashService {
	/**
	 * Website手工定向付款服务
	 * 
	 * @param dr
	 * @param directedPaymentData
	 * @return
	 */
	public String processRequest(WebDriver dr,
			DirectedPaymentData directedPaymentData);
}
