package com.bill99.fo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shelper.webdriver.Check;
import shelper.webdriver.Wait;

public class WelcomePage {

	private WebDriver dr;

	// 用户名输入框
	@FindBy(linkText = "退出")
	private WebElement link_logout;

	public WelcomePage(WebDriver dr) {
		this.dr = dr;
		dr.get("https://www.99bill.com/website/myaccount/myacctinfo.htm");
		PageFactory.initElements(dr, this);
	}

	/**
	 * 是否登录成功
	 * 
	 * @return
	 */
	public boolean isLonon() {

		return Wait.waitDisplay(link_logout);

	}

	/**
	 * 登出系统
	 * 
	 * @return
	 */
	public boolean clickLogout() {
		link_logout.click();
		// return Check.checkElementExist(dr, By.xpath("//li[text()='用户登录']"));
		return Check.checkElementExist(dr, By.xpath("//a[text()='登录']"));

	}
}
