package com.bill99.mcs.page;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

public class IdsLoginPageHttp {
	/**
	 * IDS登录
	 */
	public String url;

	private HttpFixture hf;

	public IdsLoginPageHttp(HttpFixture hf, String url) {
		this.hf = hf;
		this.url = url + "/login.action";
	}

	public boolean login(String username, String passwd) {
		// 设置访问url
		hf.setUrl(url);
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		// 添加参数
		hf.addParamValue("Submit", "登录");
		hf.addParamValue("user.userName", username);
		hf.addParamValue("user.password", passwd);
		// post方法完成请求
		hf.Post();

		if (200 == hf.getStatus()) {
			Reporter.log("IDS登录成功");
		}
		return 200 == hf.getStatus();
	}
}
