package com.bill99.ifs.common.dto;

public class QuartzDto {
	public String  triggerName;//任务名称
	public String  triggerGroup;//应用名称
	
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}


}
