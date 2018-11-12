package com.bill99.fi.service.gatewayOrderQueryV21;


import java.rmi.Remote;
import java.rmi.RemoteException;

import com.bill99.fi.common.dto.gatewayqueryV21.GatewayOrderQueryRequest;
import com.bill99.fi.common.dto.gatewayqueryV21.V21GatewayOrderQueryResponse;

public abstract interface V21GatewayOrderQuery extends Remote
{
  public abstract V21GatewayOrderQueryResponse gatewayOrderQueryV21(GatewayOrderQueryRequest paramGatewayOrderQueryRequest)
    throws RemoteException;
}