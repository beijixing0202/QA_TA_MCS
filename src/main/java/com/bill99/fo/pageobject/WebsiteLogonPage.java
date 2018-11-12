package com.bill99.fo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import shelper.webdriver.Wait;

public class WebsiteLogonPage {

	private WebDriver dr;

	// 操作员选择框
	@FindBy(id = "userID")
	private WebElement select_userID;

	// 普通模式登录选择框
	@FindBy(name = "loginTypeTab")
	private WebElement checkbox_loginTypeTab;

	// 密码输入框
	@FindBy(id = "password")
	private WebElement input_password;

	// 提交按钮
	@FindBy(xpath = "//input[@value='登录']")
	private WebElement button_submit;

	public WebsiteLogonPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}

	public WelcomePage loginWithPassword(String operator, String passwd) {

		new Select(select_userID).selectByVisibleText(operator);

		checkbox_loginTypeTab.click();
		Wait.waitDisplay(input_password);
		input_password.sendKeys(passwd);
		button_submit.click();

		return new WelcomePage(dr);
	}
}
