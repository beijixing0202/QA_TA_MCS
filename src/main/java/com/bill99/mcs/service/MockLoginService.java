package com.bill99.mcs.service;

import shelper.iffixture.HttpFixture;

/**
 * Description: ATE Mock登录请求服务
 * Author: zhenfeng.liu
 * Date: 2017/10/12 10:07
 */
public interface MockLoginService {
    /**
     * Mock登录请求
     *
     * @param username 用户名
     * @param password 密码
     */
    HttpFixture mockLoginRequest(String username, String password);
}
