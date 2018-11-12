package com.bill99.fi.access.webservice.gatewayOrderQuery;

import java.net.URL;

import javax.xml.rpc.ServiceException;

public interface GatewayOrderQueryService extends javax.xml.rpc.Service {
    public java.lang.String getgatewayOrderQueryAddress();

    public GatewayOrderQuery getgatewayOrderQuery() throws ServiceException;

    public GatewayOrderQuery getgatewayOrderQuery(URL portAddress) throws ServiceException;
}
