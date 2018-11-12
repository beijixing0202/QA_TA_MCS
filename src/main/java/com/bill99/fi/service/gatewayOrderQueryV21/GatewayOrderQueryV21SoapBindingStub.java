package com.bill99.fi.service.gatewayOrderQueryV21;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.xml.namespace.QName;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.EnumDeserializerFactory;
import org.apache.axis.encoding.ser.EnumSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListSerializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.utils.JavaUtils;

import com.bill99.fi.common.dto.gatewayqueryV21.GatewayOrderQueryRequest;
import com.bill99.fi.common.dto.gatewayqueryV21.V21GatewayOrderDetail;
import com.bill99.fi.common.dto.gatewayqueryV21.V21GatewayOrderQueryResponse;

public class GatewayOrderQueryV21SoapBindingStub extends Stub
  implements V21GatewayOrderQuery
{
  private Vector cachedSerClasses;
  private Vector cachedSerQNames;
  private Vector cachedSerFactories;
  private Vector cachedDeserFactories;
  static OperationDesc[] _operations = new OperationDesc[1];

  static { _initOperationDesc1();
  }

  private static void _initOperationDesc1()
  {
    OperationDesc oper = new OperationDesc();
    oper.setName("gatewayOrderQueryV21");
    ParameterDesc param = new ParameterDesc(new QName("", "request"), (byte) 1, new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "GatewayOrderQueryRequest"), GatewayOrderQueryRequest.class, false, false);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "V21GatewayOrderQueryResponse"));
    oper.setReturnClass(V21GatewayOrderQueryResponse.class);
    oper.setReturnQName(new QName("", "gatewayOrderQueryV21Return"));
    oper.setStyle(Style.RPC);
    oper.setUse(Use.ENCODED);
    _operations[0] = oper;
  }

  public GatewayOrderQueryV21SoapBindingStub() throws AxisFault
  {
    this(null);
  }

  public GatewayOrderQueryV21SoapBindingStub(URL endpointURL, javax.xml.rpc.Service service) throws AxisFault {
    this(service);
    this.cachedEndpoint = endpointURL;
  }

  public GatewayOrderQueryV21SoapBindingStub(javax.xml.rpc.Service service)
    throws AxisFault
  {
    this.cachedSerClasses = new Vector();
    this.cachedSerQNames = new Vector();
    this.cachedSerFactories = new Vector();
    this.cachedDeserFactories = new Vector();

    if (service == null)
      this.service = new org.apache.axis.client.Service();
    else {
      this.service = service;
    }
    ((org.apache.axis.client.Service)this.service).setTypeMappingVersion("1.2");

    Class beansf = BeanSerializerFactory.class;
    Class beandf = BeanDeserializerFactory.class;
    Class enumsf = EnumSerializerFactory.class;
    Class enumdf = EnumDeserializerFactory.class;
    Class arraysf = ArraySerializerFactory.class;
    Class arraydf = ArrayDeserializerFactory.class;
    Class simplesf = SimpleSerializerFactory.class;
    Class simpledf = SimpleDeserializerFactory.class;
    Class simplelistsf = SimpleListSerializerFactory.class;
    Class simplelistdf = SimpleListDeserializerFactory.class;
    QName qName = new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "GatewayOrderQueryRequest");
    this.cachedSerQNames.add(qName);
    Class cls = GatewayOrderQueryRequest.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);

    qName = new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "V21GatewayOrderDetail");
    this.cachedSerQNames.add(qName);
    cls = V21GatewayOrderDetail.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);

    qName = new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "V21GatewayOrderQueryResponse");
    this.cachedSerQNames.add(qName);
    cls = V21GatewayOrderQueryResponse.class;
    this.cachedSerClasses.add(cls);
    this.cachedSerFactories.add(beansf);
    this.cachedDeserFactories.add(beandf);

    qName = new QName("https://www.99bill.com/gatewayapi/services/gatewayOrderQueryV21", "ArrayOf_tns1_V21GatewayOrderDetail");
    this.cachedSerQNames.add(qName);
    cls = V21GatewayOrderDetail.class;
    this.cachedSerClasses.add(cls);
    qName = new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "V21GatewayOrderDetail");
    QName qName2 = null;
    this.cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
    this.cachedDeserFactories.add(new ArrayDeserializerFactory());
  }

  protected Call createCall() throws RemoteException
  {
    try {
      Call _call = super._createCall();
      if (this.maintainSessionSet) {
        _call.setMaintainSession(this.maintainSession);
      }
      if (this.cachedUsername != null) {
        _call.setUsername(this.cachedUsername);
      }
      if (this.cachedPassword != null) {
        _call.setPassword(this.cachedPassword);
      }
      if (this.cachedEndpoint != null) {
        _call.setTargetEndpointAddress(this.cachedEndpoint);
      }
      if (this.cachedTimeout != null) {
        _call.setTimeout(this.cachedTimeout);
      }
      if (this.cachedPortName != null) {
        _call.setPortName(this.cachedPortName);
      }
      Enumeration keys = this.cachedProperties.keys();
      while (keys.hasMoreElements()) {
        String key = (String)keys.nextElement();
        _call.setProperty(key, this.cachedProperties.get(key));
      }

      synchronized (this) {
        if (firstCall())
        {
          _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
          _call.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");
          for (int i = 0; i < this.cachedSerFactories.size(); ++i) {
            Class cls = (Class)this.cachedSerClasses.get(i);
            QName qName = 
              (QName)this.cachedSerQNames.get(i);
            Object x = this.cachedSerFactories.get(i);
            if (x instanceof Class) {
              Class sf = 
                (Class)this.cachedSerFactories.get(i);
              Class df = 
                (Class)this.cachedDeserFactories.get(i);
              _call.registerTypeMapping(cls, qName, sf, df, false);
            }
            else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
              org.apache.axis.encoding.SerializerFactory sf = 
                (org.apache.axis.encoding.SerializerFactory)this.cachedSerFactories.get(i);
              DeserializerFactory df = 
                (DeserializerFactory)this.cachedDeserFactories.get(i);
              _call.registerTypeMapping(cls, qName, sf, df, false);
            }
          }
        }
      }
      return _call;
    }
    catch (Throwable _t) {
      throw new AxisFault("Failure trying to get the Call object", _t);
    }
  }

  public V21GatewayOrderQueryResponse gatewayOrderQueryV21(GatewayOrderQueryRequest request) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[0]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("http://api.seashell.bill99.com", "gatewayOrderQueryV21"));

    setRequestHeaders(_call);
    setAttachments(_call);
    try { Object _resp = _call.invoke(new Object[] { request });

      if (_resp instanceof RemoteException) {
        throw ((RemoteException)_resp);
      }

      extractAttachments(_call);
      try {
        return ((V21GatewayOrderQueryResponse)_resp);
      } catch (Exception _exception) {
        return ((V21GatewayOrderQueryResponse)JavaUtils.convert(_resp, V21GatewayOrderQueryResponse.class));
      }
    }
    catch (AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }
}