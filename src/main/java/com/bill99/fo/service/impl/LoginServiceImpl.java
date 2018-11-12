package com.bill99.fo.service.impl;

import org.openqa.selenium.WebDriver;

import com.bill99.fo.pageobject.WebsiteLogonPage;
import com.bill99.fo.pageobject.WebsiteMainPage;
import com.bill99.fo.pageobject.WelcomePage;
import com.bill99.fo.service.LoginService;


public class LoginServiceImpl implements LoginService {

	@Override
	public Boolean logon(WebDriver dr, String username, String operator,
			String passwd) {

		WebsiteMainPage websiteMainPage = new WebsiteMainPage(dr);
		WebsiteLogonPage websiteLogonPage = websiteMainPage.login(username);
		WelcomePage welcomePage = websiteLogonPage.loginWithPassword(operator,
				passwd);
		return welcomePage.isLonon();
	}

	@Override
	public Boolean logon(WebDriver dr, String username, String passwd) {

		return logon(dr, username, "admin", passwd);
	}

	@Override
	public Boolean logout(WebDriver dr) {
		WelcomePage welcomePage = new WelcomePage(dr);
		return welcomePage.clickLogout();
	}

}
