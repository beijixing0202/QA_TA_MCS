package com.bill99.fi.common.dto.gatewayqueryV21;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class V21GatewayOrderQueryResponse
  implements Serializable
{
  private String currentPage;
  private String errCode;
  private String merchantAcctId;
  private V21GatewayOrderDetail[] orders;
  private String pageCount;
  private String pageSize;
  private String recordCount;
  private String signMsg;
  private int signType;
  private String version;
  private Object __equalsCalc = null;

  private boolean __hashCodeCalc = false;

  private static TypeDesc typeDesc = new TypeDesc(V21GatewayOrderQueryResponse.class, true);

  static {
    typeDesc.setXmlType(new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "V21GatewayOrderQueryResponse"));
    ElementDesc elemField = new ElementDesc();
    elemField.setFieldName("currentPage");
    elemField.setXmlName(new QName("", "currentPage"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("errCode");
    elemField.setXmlName(new QName("", "errCode"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("merchantAcctId");
    elemField.setXmlName(new QName("", "merchantAcctId"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("orders");
    elemField.setXmlName(new QName("", "orders"));
    elemField.setXmlType(new QName("http://gatewayquery21.dto.domain.seashell.bill99.com", "V21GatewayOrderDetail"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("pageCount");
    elemField.setXmlName(new QName("", "pageCount"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("pageSize");
    elemField.setXmlName(new QName("", "pageSize"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("recordCount");
    elemField.setXmlName(new QName("", "recordCount"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("signMsg");
    elemField.setXmlName(new QName("", "signMsg"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("signType");
    elemField.setXmlName(new QName("", "signType"));
    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
    elemField.setNillable(false);
    typeDesc.addFieldDesc(elemField);
    elemField = new ElementDesc();
    elemField.setFieldName("version");
    elemField.setXmlName(new QName("", "version"));
    elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
    elemField.setNillable(true);
    typeDesc.addFieldDesc(elemField);
  }

  public V21GatewayOrderQueryResponse()
  {
  }

  public V21GatewayOrderQueryResponse(String currentPage, String errCode, String merchantAcctId, V21GatewayOrderDetail[] orders, String pageCount, String pageSize, String recordCount, String signMsg, int signType, String version)
  {
    this.currentPage = currentPage;
    this.errCode = errCode;
    this.merchantAcctId = merchantAcctId;
    this.orders = orders;
    this.pageCount = pageCount;
    this.pageSize = pageSize;
    this.recordCount = recordCount;
    this.signMsg = signMsg;
    this.signType = signType;
    this.version = version;
  }

  public String getCurrentPage()
  {
    return this.currentPage;
  }

  public void setCurrentPage(String currentPage)
  {
    this.currentPage = currentPage;
  }

  public String getErrCode()
  {
    return this.errCode;
  }

  public void setErrCode(String errCode)
  {
    this.errCode = errCode;
  }

  public String getMerchantAcctId()
  {
    return this.merchantAcctId;
  }

  public void setMerchantAcctId(String merchantAcctId)
  {
    this.merchantAcctId = merchantAcctId;
  }

  public V21GatewayOrderDetail[] getOrders()
  {
    return this.orders;
  }

  public void setOrders(V21GatewayOrderDetail[] orders)
  {
    this.orders = orders;
  }

  public String getPageCount()
  {
    return this.pageCount;
  }

  public void setPageCount(String pageCount)
  {
    this.pageCount = pageCount;
  }

  public String getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(String pageSize)
  {
    this.pageSize = pageSize;
  }

  public String getRecordCount()
  {
    return this.recordCount;
  }

  public void setRecordCount(String recordCount)
  {
    this.recordCount = recordCount;
  }

  public String getSignMsg()
  {
    return this.signMsg;
  }

  public void setSignMsg(String signMsg)
  {
    this.signMsg = signMsg;
  }

  public int getSignType()
  {
    return this.signType;
  }

  public void setSignType(int signType)
  {
    this.signType = signType;
  }

  public String getVersion()
  {
    return this.version;
  }

  public void setVersion(String version)
  {
    this.version = version;
  }

  public synchronized boolean equals(Object obj)
  {
    if (!(obj instanceof V21GatewayOrderQueryResponse)) return false;
    V21GatewayOrderQueryResponse other = (V21GatewayOrderQueryResponse)obj;
    if (obj == null) return false;
    if (this == obj) return true;
    if (this.__equalsCalc != null) {
      return (this.__equalsCalc == obj);
    }
    this.__equalsCalc = obj;

    boolean _equals = 
      ((this.currentPage == null) && (other.getCurrentPage() == null)) || (
      (this.currentPage != null) && 
      (this.currentPage.equals(other.getCurrentPage())) && ((
      ((this.errCode == null) && (other.getErrCode() == null)) || (
      (this.errCode != null) && 
      (this.errCode.equals(other.getErrCode())) && ((
      ((this.merchantAcctId == null) && (other.getMerchantAcctId() == null)) || (
      (this.merchantAcctId != null) && 
      (this.merchantAcctId.equals(other.getMerchantAcctId())) && ((
      ((this.orders == null) && (other.getOrders() == null)) || (
      (this.orders != null) && 
      (Arrays.equals(this.orders, other.getOrders())) && ((
      ((this.pageCount == null) && (other.getPageCount() == null)) || (
      (this.pageCount != null) && 
      (this.pageCount.equals(other.getPageCount())) && ((
      ((this.pageSize == null) && (other.getPageSize() == null)) || (
      (this.pageSize != null) && 
      (this.pageSize.equals(other.getPageSize())) && ((
      ((this.recordCount == null) && (other.getRecordCount() == null)) || (
      (this.recordCount != null) && 
      (this.recordCount.equals(other.getRecordCount())) && ((
      ((this.signMsg == null) && (other.getSignMsg() == null)) || (
      (this.signMsg != null) && 
      (this.signMsg.equals(other.getSignMsg())) && 
      (this.signType == other.getSignType()) && ((
      ((this.version == null) && (other.getVersion() == null)) || (
      (this.version != null) && 
      (this.version.equals(other.getVersion())))))))))))))))))))))))))));
    this.__equalsCalc = null;
    return _equals;
  }

  public synchronized int hashCode()
  {
    if (this.__hashCodeCalc) {
      return 0;
    }
    this.__hashCodeCalc = true;
    int _hashCode = 1;
    if (getCurrentPage() != null) {
      _hashCode += getCurrentPage().hashCode();
    }
    if (getErrCode() != null) {
      _hashCode += getErrCode().hashCode();
    }
    if (getMerchantAcctId() != null) {
      _hashCode += getMerchantAcctId().hashCode();
    }
    if (getOrders() != null) {
      int i = 0;
      while (i < Array.getLength(getOrders()))
      {
        Object obj = Array.get(getOrders(), i);
        if ((obj != null) && 
          (!(obj.getClass().isArray())))
          _hashCode += obj.hashCode();
        ++i;
      }

    }

    if (getPageCount() != null) {
      _hashCode += getPageCount().hashCode();
    }
    if (getPageSize() != null) {
      _hashCode += getPageSize().hashCode();
    }
    if (getRecordCount() != null) {
      _hashCode += getRecordCount().hashCode();
    }
    if (getSignMsg() != null) {
      _hashCode += getSignMsg().hashCode();
    }
    _hashCode += getSignType();
    if (getVersion() != null) {
      _hashCode += getVersion().hashCode();
    }
    this.__hashCodeCalc = false;
    return _hashCode;
  }

  public static TypeDesc getTypeDesc()
  {
    return typeDesc;
  }

  public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType)
  {
    return 
      new BeanSerializer(
      _javaType, _xmlType, typeDesc);
  }

  public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType)
  {
    return 
      new BeanDeserializer(
      _javaType, _xmlType, typeDesc);
  }
}