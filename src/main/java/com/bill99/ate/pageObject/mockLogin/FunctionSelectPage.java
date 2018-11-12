package com.bill99.ate.pageObject.mockLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FunctionSelectPage {
	
	private WebDriver dr;
	
	public FunctionSelectPage(WebDriver dr){
		this.dr=dr;
		PageFactory.initElements(dr, this);
	}
	
//	/**
//	 *
//	 * @return
//	 * @Description:进入ATE 3.0 mock页面
//	 */
//	public WebDriver enterMdpCenter(){
//		MockLoginPage loginPage = new MockLoginPage(dr);
//		dr = loginPage.login();
//		dr.get("https://mock.99bill.net/cap-mock/mdpCenter.jsp");
//		return dr;
//	}

}
