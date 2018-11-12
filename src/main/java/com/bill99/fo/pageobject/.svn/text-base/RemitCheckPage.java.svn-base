package com.bill99.fo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * 复核出款申请页面
 * @author lulu.yang
 *
 */
public class RemitCheckPage {
	
	private WebDriver dr;
	private String url = "http://fscposs.99bill.com/fscposs/remitcheck/checkedBatchInforQuery.htm";
	
	public RemitCheckPage(WebDriver dr){
		this.dr = dr;
		dr.get(url);
		PageFactory.initElements(dr, this);
	}
	
	/**
	 * 
	 * @param batchId批次号
	 * @param bankNameChange出款银行名称
	 * @param checkTotalNum出款条数
	 * @param checkTotalAmount交易金额
	 */
	public void checkedBatchInforQuery(String batchId,String bankNameChange,String checkTotalNum,String checkTotalAmount){
		//批次号
		dr.findElement(By.id("batchId")).sendKeys(batchId);
		//选择交易单选项
		dr.findElement(By.xpath("//input[@id='searchType'][@value='deal']")).click();
		//查询按钮
		dr.findElement(By.id("query")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//选择多选按钮
		dr.findElement(By.xpath("//table[@class='tabWhole']/thead/tr/th/input[@id='btCheckbox']")).click();
		
		//点击批量复核按钮
		dr.findElement(By.id("massHandCheck")).click();
		
		//确定弹出框
		dr.switchTo().alert().accept();
		
		//手工复核页面--出款银行选择
		Select bankName = new Select(dr.findElement(By.id("orgCode")));
		bankName.selectByVisibleText(bankNameChange);
		
		//出款渠道
		Select remitChannelId = new Select(dr.findElement(By.id("remitChannelId")));
		remitChannelId.selectByIndex(1);
		
		//手工复核原因
		Select handCheckReason = new Select(dr.findElement(By.id("handCheckReason")));
		handCheckReason.selectByIndex(1);
		
		//复核总条数
		dr.findElement(By.id("checkTotalNum")).sendKeys(checkTotalNum);
		//复核金额
		dr.findElement(By.id("checkTotalAmount")).sendKeys(checkTotalAmount);
		
		//确定按钮
		dr.findElement(By.name("massPay")).click();
		
		//确定批量复核
		dr.switchTo().alert().accept();
	}

}
