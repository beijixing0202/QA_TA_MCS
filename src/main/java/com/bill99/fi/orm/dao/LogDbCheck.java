package com.bill99.fi.orm.dao;

import java.util.Map;

public interface LogDbCheck {

	public boolean notifyLogDbCkeck(Map<String, String> data);

	public boolean notifyLogDbCkeck(String orderid);

}
