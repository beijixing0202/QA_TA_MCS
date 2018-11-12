package com.bill99.pe.dto;

import shelper.db.Oracle;
import com.bill99.pe.dto.UrlDto;

public class DbConn {
//	private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {
//	"classpath:context/context-pe-hessian.xml"});
	
//	private static UrlDto urlDto = (UrlDto) ctx.getBean("urlDto");
	private  UrlDto urlDto;
	public  UrlDto getUrlDto() {
		return urlDto;
	}
	public  void setUrlDto(UrlDto urlDto) {
		this.urlDto = urlDto;
	}
	public  Oracle getDBConn() throws Exception {
		return new Oracle(urlDto.getDbpeconnect(), urlDto.getDbpeusername(),
				urlDto.getDbpepassword());
	}

}