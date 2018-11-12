package com.bill99.fi.access.webservice.gatewayOrderQuery;

import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;

public class GatewayOrderQueryServiceLocator extends Service implements GatewayOrderQueryService {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GatewayOrderQueryServiceLocator() {
    }


    public GatewayOrderQueryServiceLocator(EngineConfiguration config) {
        super(config);
    }

    public GatewayOrderQueryServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for gatewayOrderQuery
    private String gatewayOrderQuery_address = "http://www.99bill.com/gatewayapi/services/gatewayOrderQuery";

    public String getgatewayOrderQueryAddress() {
        return gatewayOrderQuery_address;
    }

    // The WSDD service name defaults to the port name.
    private String gatewayOrderQueryWSDDServiceName = "gatewayOrderQuery";

    public String getgatewayOrderQueryWSDDServiceName() {
        return gatewayOrderQueryWSDDServiceName;
    }

    public void setgatewayOrderQueryWSDDServiceName(String name) {
        gatewayOrderQueryWSDDServiceName = name;
    }

    public com.bill99.fi.access.webservice.gatewayOrderQuery.GatewayOrderQuery getgatewayOrderQuery() throws ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(gatewayOrderQuery_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getgatewayOrderQuery(endpoint);
    }

    public com.bill99.fi.access.webservice.gatewayOrderQuery.GatewayOrderQuery getgatewayOrderQuery(java.net.URL portAddress) throws ServiceException {
        try {
            com.bill99.fi.access.webservice.gatewayOrderQuery.GatewayOrderQuerySoapBindingStub _stub = new com.bill99.fi.access.webservice.gatewayOrderQuery.GatewayOrderQuerySoapBindingStub(portAddress, this);
            _stub.setPortName(getgatewayOrderQueryWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setgatewayOrderQueryEndpointAddress(String address) {
        gatewayOrderQuery_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
        try {
            if (com.bill99.fi.access.webservice.gatewayOrderQuery.GatewayOrderQuery.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bill99.fi.access.webservice.gatewayOrderQuery.GatewayOrderQuerySoapBindingStub _stub = new com.bill99.fi.access.webservice.gatewayOrderQuery.GatewayOrderQuerySoapBindingStub(new java.net.URL(gatewayOrderQuery_address), this);
                _stub.setPortName(getgatewayOrderQueryWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new ServiceException(t);
        }
        throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("gatewayOrderQuery".equals(inputPortName)) {
            return getgatewayOrderQuery();
        }
        else  {
            Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public QName getServiceName() {
        return new QName("http://www.99bill.com/gatewayapi/services/gatewayOrderQuery", "GatewayOrderQueryService");
    }

    private HashSet ports = null;

    public Iterator getPorts() {
        if (ports == null) {
            ports = new HashSet();
            ports.add(new QName("http://www.99bill.com/gatewayapi/services/gatewayOrderQuery", "gatewayOrderQuery"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws ServiceException {
        
if ("gatewayOrderQuery".equals(portName)) {
            setgatewayOrderQueryEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(QName portName, String address) throws ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
