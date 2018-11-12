package com.bill99.mcs.pageobject;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * Description: 登录成功后的Mock页
 * Author: zhenfeng.liu
 * Date: 2017/10/11 20:09
 */
public class MockCenterPage {
    private WebDriver driver;

    public MockCenterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkIsLoginSuccess() {
        String title = this.driver.getTitle();
        if (title.equals("ATE_MDP方式_收单测试")) {
            Reporter.log("登录ATE 3.0 Mock成功.");
        } else {
            Reporter.FALSE("登录ATE 3.0 Mock失败.");
        }
    }
}
