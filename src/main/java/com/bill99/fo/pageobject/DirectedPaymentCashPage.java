package com.bill99.fo.pageobject;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.bill99.fo.common.dto.DirectedPaymentData;

import shelper.webdriver.Wait;

/**
 * 定向付款页面
 * @author lulu.yang
 *
 */
public class DirectedPaymentCashPage {

	private WebDriver dr = null;

	public DirectedPaymentCashPage(WebDriver dr) {
		this.dr = dr;
		dr.get("https://www.99bill.com/directedpayment/directedpayment/withdrawbankcardview.htm");
		PageFactory.initElements(dr, this);
	}

	@FindBy(xpath = "//select[@name='memberAcctCode']")
	private WebElement memberAcctCode;

	@FindBy(xpath = "//a[@class='cardMore' and contains(.,'查看更多银行')]")
	private WebElement checkBank;

	@FindBy(partialLinkText = "...查看更多银行")
	private WebElement checkMoreBank;

	@FindBy(xpath = "//input[@name='cash_format']")
	private WebElement cash_format;

	@FindBy(id = "tel")
	private WebElement tel;

	@FindBy(id = "submitOk")
	private WebElement submitOk;

	@FindBy(xpath = "//textarea[@name='remark']")
	private WebElement remark;

	@FindBy(id = "randomCode")
	private WebElement randomCode;

	@FindBy(xpath = "//input[@name='submit0']")
	private WebElement submit0;


	public String submitRequest(DirectedPaymentData directedPaymentData) {

		// 从下拉框中选择人民币账户
		Select selectMemberAcctCode = new Select(memberAcctCode);
		selectMemberAcctCode.selectByVisibleText(directedPaymentData
				.getMemberAcctCode());

		// 点击查看更多银行
		if (Wait.waitDisplay(dr, checkBank, 30)) {
			checkMoreBank.click();
		}

		// 选择提现银行账户
		dr.findElement(
				By.xpath("//input[@value='"
						+ directedPaymentData.getBankAcctIdSec()
						+ "' and @name='bankAcctId']")).click();

		cash_format.sendKeys(directedPaymentData.getCash()); // 付款金额
		tel.sendKeys(directedPaymentData.getTel()); // 收款人手机号码
		remark.sendKeys(directedPaymentData.getMemo()); // 附言

		submitOk.click();
		Wait.waitDisplay(dr, randomCode, 30);
		randomCode.sendKeys("8888");
		submit0.click();

		return dr
				.findElement(
						By.xpath("//table//tbody//tr//td[contains(text(),'交易号')]//following-sibling::td[position()=1]"))
				.getText();

	}


	public BigDecimal getBalance() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 获取余额
		return BigDecimal
				.valueOf(Double.valueOf(dr
						.findElement(
								By.xpath("//li[concat(.,'当前账户余额 ')]/../li[2]/div"))
						.getText().trim()));
	}
}
