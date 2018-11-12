package com.bill99.fo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebsiteMainPage {
	private WebDriver dr;
	private String url = "www.99bill.com";
	// 用户名输入框
	@FindBy(name = "idContent")
	private WebElement edit_loginName;
	// 更换随机验证码的图片链接
	@FindBy(linkText = "换一张")
	private WebElement link_changeimg;
	// 随机验证码输入框
	@FindBy(id = "inputRand")
	private WebElement edit_inputRand;
	// 登录按钮
	@FindBy(name = "submit")
	private WebElement button_submit;

	public WebsiteMainPage(WebDriver dr) {
		this.dr = dr;
		dr.get(url);
		dr.findElement(By.xpath("//a[text()='登录']")).click();

		PageFactory.initElements(dr, this);
	}

	public WebsiteLogonPage login(String username) {
		edit_loginName.clear();
		edit_loginName.sendKeys(username);
		link_changeimg.click();
		edit_inputRand.sendKeys("8888");
		button_submit.click();
		return new WebsiteLogonPage(dr);
	}

}
