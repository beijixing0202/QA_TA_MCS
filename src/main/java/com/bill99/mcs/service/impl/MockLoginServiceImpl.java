package com.bill99.mcs.service.impl;

import com.bill99.mcs.service.MockLoginService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Reporter;
import shelper.iffixture.HttpFixture;


/**
 * Description: Mock登录服务实现
 * Author: zhenfeng.liu
 * Date: 2017/10/12 10:07
 */
public class MockLoginServiceImpl implements MockLoginService {
    private String loginUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public HttpFixture mockLoginRequest(String username, String password) {
        HttpFixture request = new HttpFixture();
        request.setUrl(loginUrl);
        request.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        request.addRequestBody("username=" + username + "&password=" + password);
        request.Post();
        
        String response = request.getResponseBody();
        System.err.println(request.getResponseheaders());
        Document document = Jsoup.parse(response);
        String title = document.title();
        if (title.equals("ATE test page")) {
            Reporter.log("Mock登录成功.");
        } else {
            Reporter.FALSE("Mock登录失败,请检查.");
        }
        
        return request;
        
        
    }


}
