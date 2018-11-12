package com.bill99.fi.service.refundHx;

public class RefundHxProxy implements com.bill99.fi.service.refundHx.RefundHx {
  private String _endpoint = null;
  private com.bill99.fi.service.refundHx.RefundHx refundHx = null;
  
  public RefundHxProxy() {
    _initRefundHxProxy();
  }
  
  public RefundHxProxy(String endpoint) {
    _endpoint = endpoint;
    _initRefundHxProxy();
  }
  
  private void _initRefundHxProxy() {
    try {
      refundHx = (new com.bill99.fi.service.refundHx.RefundHxServiceLocator()).getserviceRefund();
      if (refundHx != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)refundHx)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)refundHx)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (refundHx != null)
      ((javax.xml.rpc.Stub)refundHx)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bill99.fi.service.refundHx.RefundHx getRefundHx() {
    if (refundHx == null)
      _initRefundHxProxy();
    return refundHx;
  }
  
  public com.bill99.fi.common.dto.refundHx.RefundResponse refund(com.bill99.fi.common.dto.refundHx.RefundRequest refundRequest) throws java.rmi.RemoteException{
    if (refundHx == null)
      _initRefundHxProxy();
    return refundHx.refund(refundRequest);
  }
  
  
}