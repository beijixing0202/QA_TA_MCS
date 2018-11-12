package com.bill99.fi.common.utils;

import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import org.apache.commons.codec.binary.Base64;

public class PKIUtil
{
  /**Pki 加签
 * @param memberCode eg:10011637788
 * @param featureCode eg:F21 网关 , F25 分账 , F42 网关退款 , F26 分账退款
 * @param originalData 加密前字符串
 * @param inputcharset	字符集  eg:utf-8
 * @return
 */
public static String pki(String memberCode, String featureCode, String originalData, String inputCharset)
  {

    String outSignedData = "";
    try {
      Mpf mpf = new Mpf();
      mpf.setMemberCode(memberCode);
      mpf.setFeatureCode(featureCode);
      ICryptoService service = CryptoServiceFactory.createCryptoService();

      SealedData sealedData = service.seal(mpf, originalData.getBytes(inputCharset==null?"utf-8":inputCharset.equals("1")? "utf-8":inputCharset.equals("2")? "GBK":inputCharset.equals("3")? "GB2312":"utf-8"));
      outSignedData = new String(Base64.encodeBase64(sealedData.getSignedData()));
    } catch (Exception e) {
      System.out.println("error!!!" + e);
    }
    return outSignedData;
  }

  /**
 * @param memberCode eg:10011637788
 * @param featureCode eg: 网关-F21 退款-F42
 * @param signedData 加密后字符串
 * @param originalData 加密前字符串
 * @param Charset  字符集  eg:utf-8
 * @throws Exception
 */
public static void checkMsg(String memberCode, String featureCode, String signedData, String originalData, String Charset) throws Exception
  {

    Mpf mpf = new Mpf();
    mpf.setMemberCode(memberCode);
    mpf.setFeatureCode(featureCode);
    ICryptoService service = CryptoServiceFactory.createCryptoService();
    SealedData sealedData = new SealedData();

    sealedData.setOriginalData(originalData.getBytes());
    sealedData.setSignedData(Base64.decodeBase64(signedData.getBytes()));
    UnsealedData us = service.unseal(mpf, sealedData);
    System.out.println(us.getVerifySignResult());
  }

}