package com.bill99.mcs.page;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

public class IdsManualCutOffPageHttp {

	private String url;

	private HttpFixture hf;

	public IdsManualCutOffPageHttp(HttpFixture hf, String url) {
		this.hf = hf;
		this.url = url + "/clroper/manualCutOff.action?_="
				+ System.currentTimeMillis();
	}

	public boolean executeCutOff() {
		hf.setUrl(url);
		hf.Get();
		boolean success = hf.findStringinResponse("手工日切成功");
		if (success) {
			Reporter.log(hf.getResponseBody());
		}

		// System.out.println(url);
		// System.out.println(hf.getResponseBody());
		return success;

	}
}
