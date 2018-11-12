package com.bill99.fi.access.webservice.gatewayRefundQuery;

public class GatewayRefundQueryProxy implements com.bill99.fi.access.webservice.gatewayRefundQuery.GatewayRefundQuery {
  private String _endpoint = null;
  private com.bill99.fi.access.webservice.gatewayRefundQuery.GatewayRefundQuery gatewayRefundQuery = null;
  
  public GatewayRefundQueryProxy() {
    _initGatewayRefundQueryProxy();
  }
  
  public GatewayRefundQueryProxy(String endpoint) {
    _endpoint = endpoint;
    _initGatewayRefundQueryProxy();
  }
  
  private void _initGatewayRefundQueryProxy() {
    try {
      gatewayRefundQuery = (new com.bill99.fi.access.webservice.gatewayRefundQuery.GatewayRefundQueryServiceLocator()).getgatewayRefundQuery();
      if (gatewayRefundQuery != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)gatewayRefundQuery)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)gatewayRefundQuery)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (gatewayRefundQuery != null)
      ((javax.xml.rpc.Stub)gatewayRefundQuery)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bill99.fi.access.webservice.gatewayRefundQuery.GatewayRefundQuery getGatewayRefundQuery() {
    if (gatewayRefundQuery == null)
      _initGatewayRefundQueryProxy();
    return gatewayRefundQuery;
  }
  
  public com.bill99.fi.common.dto.gatewayRefundQuery.GatewayRefundQueryResponse query(com.bill99.fi.common.dto.gatewayRefundQuery.GatewayRefundQueryRequest request) throws java.rmi.RemoteException{
    if (gatewayRefundQuery == null)
      _initGatewayRefundQueryProxy();
    return gatewayRefundQuery.query(request);
  }
  
  
}