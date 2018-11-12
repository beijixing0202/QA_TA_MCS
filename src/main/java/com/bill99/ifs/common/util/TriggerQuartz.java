package com.bill99.ifs.common.util;

import org.testng.Reporter;

import com.bill99.ifs.common.dto.QuartzDto;

import shelper.iffixture.HttpFixture;

public class TriggerQuartz {

	public static boolean trigOneQuartz(QuartzDto quartzDto) throws InterruptedException{
		 HttpFixture hfFixture = new HttpFixture();
		 hfFixture.setUrl("http://192.168.63.227:8081/app-monitor-website/userLoginSub.do?userName=ifs_user&userPwd=000000");	
		 hfFixture.Post();
		 hfFixture.nextRequest();
		 hfFixture.setUrl("http://192.168.63.227:8081/app-monitor-website/quartz-framework/runJob.do?triggerName="+quartzDto.getTriggerName()+"&triggerGroup="+quartzDto.getTriggerGroup()+"&triggerState=&jobInsId=&remark=33333333");
		 hfFixture.Post();
		 boolean flag= hfFixture.findStringinResponse("Quartz执行成功");
		return flag;
	}
}
