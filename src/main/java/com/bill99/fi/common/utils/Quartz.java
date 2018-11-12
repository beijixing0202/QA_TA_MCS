package com.bill99.fi.common.utils;


import shelper.iffixture.HttpFixture;

public class Quartz {
	private String  triggerName;
	private int  time;

  /**
 * 执行quartz
 */
public boolean executeQuartz() {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	HttpFixture query = new HttpFixture();
	  	query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("http://192.168.63.227:8081/app-monitor-website/userLoginSub.do?");
		query.addRequestBody("userName=fi_user&userPwd=99bill");
		query.Post();
		query.nextRequest();
		query.setUrl("http://192.168.63.227:8081/app-monitor-website/quartz-framework/runJob.do");
		query.addRequestBody("triggerGroup=fi.appquartz&triggerName="+triggerName+"&triggerState=&jobInsId=&remark=test");
		query.Post();	
		System.out.println(query.getResponseBody());
		return query.findStringinResponse("Quartz执行成功");
  }
  
  
  /**quartz 名称
 * @param TriggerName
 */
public void setTriggerName(String TriggerName){
	  this.triggerName=TriggerName;
  }
public void setDelayTime (int time){
	this.time=time;
}
}
