package com.bill99.fo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author lulu.yang 清算业务系统登陆页面
 * 
 */
public class FscLoginPage {

	private WebDriver dr;
	private String url = "http://fscposs.99bill.com/fscposs/login/login.htm";

	@FindBy(id = "userName")
	private WebElement loginNameField;

	@FindBy(id = "password")
	private WebElement loginPasswdField;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginSubmitButton;

	public FscLoginPage(WebDriver dr) {
		this.dr = dr;
		dr.get(url);
		PageFactory.initElements(dr, this);
	}

	public boolean login(String username, String passwd) {
		loginNameField.clear();
		loginNameField.sendKeys(username);
		loginPasswdField.clear();
		loginPasswdField.sendKeys(passwd);
		loginSubmitButton.submit();
		return dr.getTitle().equals("清算业务系统");
	}

}
