package com.bill99.mcs.common.helper;

import com.bill99.mcs.tools.DateUtils;
import org.json.HTTP;
import org.json.JSONObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import shelper.iffixture.HttpFixture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class UnionpayCode {

    public String getUnionpayCode1(String url){
        // HttpFixture request = new HttpFixture("open.unionpay.com", 2443, "TLSv1.2");
        String sendData="[{\"fid\":523,\"keyword\":\"issCode\",\"value\":\"90880019\"},{\"fid\":525,\"keyword\":\"backUrl\",\"value\":\"http://101.231.204.84:8091/sim/notify_url2.jsp\"},{\"fid\":526,\"keyword\":\"qrType\",\"value\":\"\"},{\"fid\":527,\"keyword\":\"reqAddnData\",\"value\":\"\"},{\"fid\":646,\"keyword\":\"emvCodeIn\",\"value\":\"\"},{\"fid\":528,\"keyword\":\"accNo\",\"value\":\"6216261000000002485\"},{\"fid\":529,\"keyword\":\"name\",\"value\":\"宋小\"}]";
        HttpFixture request=new HttpFixture();
        request.addHeaderValue("Content-Type","application/json;charset=UTF-8");
       /* request.addParamValue("puid","34");
        request.addParamValue("requestType","coverSweepReceiverApp");
        request.addParamValue("sendtype","C2B码申请");
        request.addParamValue("sendData",sendData);*/
        HashMap jsonMap=new HashMap();
        jsonMap.put("puid","9");
        jsonMap.put("requestType","coverSweepReceiverApp");
        jsonMap.put("sendtype","C2B码申请");
        jsonMap.put("sendData",sendData);
        String json = new JSONObject(jsonMap).toString();
        request.nextRequest();
        request.addRequestBody(json);
        request.setUrl(url);
        request.Post();
        String response=request.getResponseBody();
        return response;

    }
    public  String getUnionpayCode() {
        System.setProperty("webdriver.chrome.driver", "E:\\TA\\selenium_grid\\chromedriver.exe");
        WebDriver dr=new ChromeDriver();
        dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //WebDriverWait wait = new WebDriverWait(dr, 10);
        dr.get("https://open.unionpay.com/ajweb/help/qrcodeFormPage/coverSweepReceiverApp");
        dr.findElement(By.xpath(".//*[@id='main-container']/div[2]/div[1]/div/div[2]/ul/li/a")).click();
        dr.findElement(By.xpath(".//*[@id='sendButOK']/div/div/button[1]")).click();
        dr.findElement(By.xpath(".//*[@id='sendButOK']/div/div/button[2]")).click();
        String qrNo=dr.findElement(By.xpath(".//*[@id='sendResult']/table/tbody/tr[5]/td[2]")).getText();
        dr.quit();
        if(qrNo!=null){
            return qrNo;
        }else{
            Reporter.FALSE("请码失败");
            return null;
        }

    }

    @Test
    public void test1(){

        System.out.print(getUnionpayCode1("https://open.unionpay.com//ajweb/help/qrcodeFormPage/sendOk"));
    }

    @Test
    public void test2(){
        HttpFixture hf=new HttpFixture("open.unionpay.com", 2443, "TLS 1.2");
        String url="https://open.unionpay.com//ajweb/help/qrcodeFormPage/sendOk?puid=34&requestType=coverSweepReceiverApp&sendtype=C2B码申请&sendData=[{\"fid\":523,\"keyword\":\"issCode\",\"value\":\"90880019\"},{\"fid\":525,\"keyword\":\"backUrl\",\"value\":\"http://101.231.204.84:8091/sim/notify_url2.jsp\"},{\"fid\":526,\"keyword\":\"qrType\",\"value\":\"\"},{\"fid\":527,\"keyword\":\"reqAddnData\",\"value\":\"\"},{\"fid\":646,\"keyword\":\"emvCodeIn\",\"value\":\"\"},{\"fid\":528,\"keyword\":\"accNo\",\"value\":\"6216261000000002485\"},{\"fid\":529,\"keyword\":\"name\",\"value\":\"宋小\"}]";
        hf.nextRequest();
        hf.addHeaderValue("Content-Type","application/json;charset:utf-8");
        hf.addHeaderValue("Cookie","JSESSIONID=Wsm6nShSxk9q+TuzY+UTNyUT; UM_distinctid=166e6948a95e4-0942fa6af3e131-69101b7d-13c680-166e6948a9645; org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE=en; F5_cookie_open=!6g2oB57tQHouJKkLtEk0e0tLEl2NkRdsIq6dfG8vvZcNosI75/CV7omvZgmjm4VrIQ9InvwfbBxZpw==; Hm_lvt_663f4dff9a63809fb87e471b923a85d3=1541466786,1541661336; CNZZDATA1255931429=1747754009-1541463231-%7C1541656932; Hm_lpvt_663f4dff9a63809fb87e471b923a85d3=1541661358");
        hf.setUrl(url);
        hf.Get();
        System.out.print( hf.getResponseBody());
    }



}
