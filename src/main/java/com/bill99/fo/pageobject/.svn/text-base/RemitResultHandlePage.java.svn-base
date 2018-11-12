package com.bill99.fo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * 导入出款结果页面
 * @author lulu.yang
 *
 */
public class RemitResultHandlePage {
	private WebDriver dr;
	private String url = "http://fscposs.99bill.com/fscposs/remitresulthandle/remitResultQuery.htm";
	
	public RemitResultHandlePage(WebDriver dr){
		this.dr = dr;
		dr.get(url);
		PageFactory.initElements(dr, this);
	}
	
	/**
	 * 
	 * @param batchId批次号
	 * @param dealAmount实际交易金额
	 */
	public void bankRemit(String batchId,String dealAmount){
		
		//批次号
		dr.findElement(By.name("batchCode")).sendKeys(batchId);
		
		//批次查询按钮
		dr.findElement(By.id("batchbutton")).click();
		
		//点击笔数
		dr.findElement(By.xpath("//*[@id='paginationResult']/div/table/tbody/tr[4]/td[2]/a/u")).click();
		
		//点击多选复选框
		dr.findElement(By.xpath("//table[@class='tabWhole']/thead/tr/th/input[@id='btCheckbox']")).click();
		
		//点击批量完成按钮
		dr.findElement(By.id("massHandCheck1")).click();
		
		//输入手工操作原因
		Select handOperateReason = new Select(dr.findElement(By.id("handOperateReason")));
		handOperateReason.selectByIndex(1);
		
		//输入交易金额
		dr.findElement(By.id("totalAmount")).sendKeys(dealAmount);
		
		//点击确定按钮
		dr.findElement(By.id("matchCheck")).click();
		//确认操作完成
		dr.switchTo().alert().accept();
		
	}

}
