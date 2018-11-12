package com.bill99.fi.service.gatewayOrderQueryV21;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

public class V21GatewayOrderQueryServiceLocator extends Service
  implements V21GatewayOrderQueryService
{
  private String gatewayOrderQueryV21_address = "http://www.99bill.com/gatewayapi/services/gatewayOrderQueryV21";

  private String gatewayOrderQueryV21WSDDServiceName = "gatewayOrderQueryV21";

  private HashSet ports = null;

  public V21GatewayOrderQueryServiceLocator()
  {
  }

  public V21GatewayOrderQueryServiceLocator(EngineConfiguration config)
  {
    super(config);
  }

  public V21GatewayOrderQueryServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
    super(wsdlLoc, sName);
  }

  public String getgatewayOrderQueryV21Address()
  {
    return this.gatewayOrderQueryV21_address;
  }

  public String getgatewayOrderQueryV21WSDDServiceName()
  {
    return this.gatewayOrderQueryV21WSDDServiceName;
  }

  public void setgatewayOrderQueryV21WSDDServiceName(String name) {
    this.gatewayOrderQueryV21WSDDServiceName = name;
  }

  public V21GatewayOrderQuery getgatewayOrderQueryV21() throws ServiceException {
    URL endpoint;
    try {
      endpoint = new URL(this.gatewayOrderQueryV21_address);
    }
    catch (MalformedURLException e) {
      throw new ServiceException(e);
    }
    return getgatewayOrderQueryV21(endpoint);
  }

  public V21GatewayOrderQuery getgatewayOrderQueryV21(URL portAddress) throws ServiceException {
    try {
      GatewayOrderQueryV21SoapBindingStub _stub = new GatewayOrderQueryV21SoapBindingStub(portAddress, this);
      _stub.setPortName(getgatewayOrderQueryV21WSDDServiceName());
      return _stub;
    } catch (AxisFault e) {
    }
    return null;
  }

  public void setgatewayOrderQueryV21EndpointAddress(String address)
  {
    this.gatewayOrderQueryV21_address = address;
  }

  public Remote getPort(Class serviceEndpointInterface)
    throws ServiceException
  {
    try
    {
      if (V21GatewayOrderQuery.class.isAssignableFrom(serviceEndpointInterface)) {
        GatewayOrderQueryV21SoapBindingStub _stub = new GatewayOrderQueryV21SoapBindingStub(new URL(this.gatewayOrderQueryV21_address), this);
        _stub.setPortName(getgatewayOrderQueryV21WSDDServiceName());
        return _stub;
      }
    }
    catch (Throwable t) {
      throw new ServiceException(t);
    }
    throw new ServiceException("There is no stub implementation for the interface:  " + ((serviceEndpointInterface == null) ? "null" : serviceEndpointInterface.getName()));
  }

  public Remote getPort(QName portName, Class serviceEndpointInterface)
    throws ServiceException
  {
    if (portName == null) {
      return getPort(serviceEndpointInterface);
    }
    String inputPortName = portName.getLocalPart();
    if ("gatewayOrderQueryV21".equals(inputPortName)) {
      return getgatewayOrderQueryV21();
    }

    Remote _stub = getPort(serviceEndpointInterface);
    ((Stub)_stub).setPortName(portName);
    return _stub;
  }

  public QName getServiceName()
  {
    return new QName("https://www.99bill.com/gatewayapi/services/gatewayOrderQueryV21", "V21GatewayOrderQueryService");
  }

  public Iterator getPorts()
  {
    if (this.ports == null) {
      this.ports = new HashSet();
      this.ports.add(new QName("https://www.99bill.com/gatewayapi/services/gatewayOrderQueryV21", "gatewayOrderQueryV21"));
    }
    return this.ports.iterator();
  }

  public void setEndpointAddress(String portName, String address)
    throws ServiceException
  {
    if ("gatewayOrderQueryV21".equals(portName)) {
      setgatewayOrderQueryV21EndpointAddress(address);
    }
    else
    {
      throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
    }
  }

  public void setEndpointAddress(QName portName, String address)
    throws ServiceException
  {
    setEndpointAddress(portName.getLocalPart(), address);
  }
}