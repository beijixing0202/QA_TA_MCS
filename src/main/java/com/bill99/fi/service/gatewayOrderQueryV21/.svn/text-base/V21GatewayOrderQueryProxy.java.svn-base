package com.bill99.fi.service.gatewayOrderQueryV21;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

import com.bill99.fi.common.dto.gatewayqueryV21.GatewayOrderQueryRequest;
import com.bill99.fi.common.dto.gatewayqueryV21.V21GatewayOrderQueryResponse;

public class V21GatewayOrderQueryProxy
  implements V21GatewayOrderQuery
{
  private String _endpoint = null;
  private V21GatewayOrderQuery v21GatewayOrderQuery = null;

  public V21GatewayOrderQueryProxy() {
    _initV21GatewayOrderQueryProxy();
  }

  public V21GatewayOrderQueryProxy(String endpoint) {
    this._endpoint = endpoint;
    _initV21GatewayOrderQueryProxy();
  }

  private void _initV21GatewayOrderQueryProxy() {
    try {
      this.v21GatewayOrderQuery = new V21GatewayOrderQueryServiceLocator().getgatewayOrderQueryV21();
      if (this.v21GatewayOrderQuery != null)
        if (this._endpoint != null)
          ((Stub)this.v21GatewayOrderQuery)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
        else
          this._endpoint = ((String)((Stub)this.v21GatewayOrderQuery)._getProperty("javax.xml.rpc.service.endpoint.address"));
    }
    catch (ServiceException localServiceException)
    {
    }
  }

  public String getEndpoint() {
    return this._endpoint;
  }

  public void setEndpoint(String endpoint) {
    this._endpoint = endpoint;
    if (this.v21GatewayOrderQuery != null)
      ((Stub)this.v21GatewayOrderQuery)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
  }

  public V21GatewayOrderQuery getV21GatewayOrderQuery()
  {
    if (this.v21GatewayOrderQuery == null)
      _initV21GatewayOrderQueryProxy();
    return this.v21GatewayOrderQuery;
  }

  public V21GatewayOrderQueryResponse gatewayOrderQueryV21(GatewayOrderQueryRequest request) throws RemoteException {
    if (this.v21GatewayOrderQuery == null)
      _initV21GatewayOrderQueryProxy();
    return this.v21GatewayOrderQuery.gatewayOrderQueryV21(request);
  }
}