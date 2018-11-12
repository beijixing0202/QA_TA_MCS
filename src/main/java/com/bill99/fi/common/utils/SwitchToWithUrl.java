package com.bill99.fi.common.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import shelper.environment.Environment;
import shelper.webdriver.SwitchTo;

/**
 * @author KAIQUAN.JIANG
 *
 */
public class SwitchToWithUrl {

	 /**定位为URL包含指定字符串的windows
	 * @param driver
	 * @param url
	 */
	public static void windowWithUrl(WebDriver driver,String url){
			
	        for(int i =0;i<Integer.parseInt(Environment.get("Selenium.waittime"));i++){
	            for (String handle : driver.getWindowHandles()) {
	                driver.switchTo().window(handle);
	                	if(StringUtils.contains(driver.getCurrentUrl(),url)){
	                	
	                	return;
	                }
	            }
	            
	        Logger.getLogger(SwitchTo.class.getName()).log(Level.INFO, "could not find the window that url is:" + url+" -time:" + i );            
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException ex) {
	                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
	            }            
	        }
	        throw new org.openqa.selenium.NoSuchFrameException("could not find the window that url is:" + url);      
	    }    
	
	
	/**判断windows是否包含指定的url，包含返回true，不包含返回false
	 * @param driver
	 * @param url
	 * @return
	 */
	public static boolean windowNotWithUrl(WebDriver driver,String url){
		Boolean result=false;
                for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
               	if(StringUtils.contains(driver.getCurrentUrl(),url)){
               		result=true;	
               	}else{
               		result=false;
               	}
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
            }            
       return result;
    } 
	
	public static void main(String arg[]){
		WebDriver driver= new InternetExplorerDriver();
		driver.get("www.baidu.com");
		System.out.println(SwitchToWithUrl.windowNotWithUrl(driver, "www.baidu.com"));
		
	}
}
