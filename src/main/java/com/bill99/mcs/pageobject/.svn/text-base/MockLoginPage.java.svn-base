package com.bill99.mcs.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MockLoginPage {
    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//table//tr[3]//input")
    private WebElement submitBtn;

    public MockLoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://192.168.14.88:8088/cap-mock/login.jsp");
        PageFactory.initElements(driver, this);
    }

    private void setUserName(String strUserName) {
        this.username.clear();
        this.username.sendKeys(strUserName);
    }

    private void setPassWord(String strPassWord) {
        this.password.clear();
        this.password.sendKeys(strPassWord);
    }

    private void clickBtn() {
        this.submitBtn.click();
    }

    public MockCenterPage login(String strUserName, String strPassWord) {
        this.setUserName(strUserName);
        this.setPassWord(strPassWord);
        this.clickBtn();
        return new MockCenterPage(this.driver);
    }
}
