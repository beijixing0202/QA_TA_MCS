package com.bill99.fo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * 清算业务系统---手工出批次（）
 * 
 * @author lulu.yang
 * 
 */

public class HandBatchPage {

	private WebDriver dr;
	private String url = "http://fscposs.99bill.com/fscposs/handbatch/handBatchQuery.htm";

	// private JavascriptExecutor js;

	// private String url =
	// "http://fscposs.99bill.com/fscposs/ftl/fscposs/main.jsp";

	public HandBatchPage(WebDriver dr) {
		this.dr = dr;
		dr.get(url);
		PageFactory.initElements(dr, this);

		// this.js = (JavascriptExecutor) dr;
	}

	// 首页汇款按钮
	@FindBy(xpath = "//a[text()='汇款']")
	private WebElement remitBtn;

	// 手工出批次链接
	@FindBy(xpath = "//a[text()='手工出批次']")
	private WebElement handBatchBtn;

	// 交易输入框
	@FindBy(id = "orderSeqId")
	private WebElement orderSeqIdTxt;

	// 渠道方式
	@FindBy(id = "channelType")
	private WebElement channelTypeSel;

	// 查询按钮
	@FindBy(id = "batchbutton")
	private WebElement batchQueryBtn;

	// iframe的定位 批量付款页面
	@FindBy(id = "content")
	private WebElement IframeElement;

	// 批量付款全部选中的复选框
	@FindBy(id = "btCheckbox")
	private WebElement btChk;

	// 批量付款复选框
	@FindBy(name = "btRecordsChbox")
	private WebElement btRecordsChk;

	// 批量付款按钮
	// @FindBy(id = "massPay")
	@FindBy(xpath = "//input[@value='批量付款']")
	private WebElement massPayBtn;

	// 手工出批次名称下来框(选中白名单)
	@FindBy(name = "batchNameSelect")
	private WebElement batchNameSel;

	// 确定按钮
	@FindBy(name = "massPay")
	private WebElement confirmBtn;

	// 批次号txt
	@FindBy(id = "batchId")
	private WebElement batchIdTxt;

	/**
	 * 单笔出批次
	 * 
	 * @param orderSeqId
	 * @param channelIndex:0网银出款，1直连出款
	 * @return
	 * @throws Exception
	 */

	public String massPayRequest(String orderSeqId,int channelIndex) throws Exception {

		// remitBtn.click();
		// handBatchBtn.click();
		Select channelType = new Select(channelTypeSel);
		// channelType.selectByValue("直连");
		channelType.selectByIndex(channelIndex);
		//channelType.selectByIndex(1);
		orderSeqIdTxt.clear();
		orderSeqIdTxt.sendKeys(orderSeqId);
		batchQueryBtn.click();
		// Driver.switch().frame(IframeElement);
		btChk.click();
		// btRecordsChk.click();
		massPayBtn.click();
		dr.switchTo().alert().accept();
		// dr.switchTo().frame(IframeElement);
		// SwitchTo.frame(dr, 0);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Select batchNameChange = new Select(batchNameSel);
		batchNameChange.selectByValue("白名单");
		confirmBtn.click();
		dr.switchTo().alert().accept();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return batchIdTxt.getText();
		return batchIdTxt.getAttribute("value");

	}
}
