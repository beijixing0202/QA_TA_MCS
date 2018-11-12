package com.bill99.fi.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class ErrorcodeCheck {
	
	public static void main(String args[]) {
		String c = new  ErrorcodeCheck().ErrorCheck01("00000");
		System.out.println(c);
	}
	/**
	 * 分账异步确认接口errorcode
	 */
	public static Map<String, String> errorCode01 = new HashMap<String, String>();
	static {
		errorCode01.put("00000", "邮件地址不正确或其他错误");
		errorCode01.put("10001", "字符集类型不正确");
		errorCode01.put("10002", "网关版本号不正确或不存在");
		errorCode01.put("10003", "签名类型不正确或不存");
		errorCode01.put("10004", "商户订单号格式不正确");
		errorCode01.put("10005", "异步分账提交时间格式不正确");
		errorCode01.put("10006", "合作伙伴用户编号格式不正确");
		errorCode01.put("10007", "分账数据格式不正确");
		errorCode01.put("11001", "提交时间不能是还没有到的日期");
		errorCode01.put("11002", "合作伙伴用户编号不存在");
		errorCode01.put("20001", "分帐支付订单不存在");
		errorCode01.put("20002", "分帐支付订单不能异步分帐");
		errorCode01.put("20003", "无此分帐收款人");
		errorCode01.put("20004", "email对应的快钱帐户不存在");
		errorCode01.put("20005", "收款人帐户止入/冻结/注销");
		errorCode01.put("20006", "异步分帐pid与原分帐支付pid不相同");
		errorCode01.put("20007", "收款人已做过分帐");
		errorCode01.put("20011", "该账号无权进行分账操作");
		errorCode01.put("20012", "签名字符串不匹配，您无权进行该操作 ");
		errorCode01.put("20013", "签名字符串不存在");
		errorCode01.put("30001", "系统繁忙，请稍后再查询");
		errorCode01.put("30002", "查询过程异常，请稍后再试");
		errorCode01.put("31001", "该订单号已经存在");
		errorCode01.put("31002", "分账操作失败，请稍后再试");
		errorCode01.put("31003", "分账部分成功，请重新发起异步分账确");
			
	}
	
	/**
	 * 分账同步退款errorcode
	 */
	public static Map<String, String>errorCode02 = new HashMap<String, String>();
	static{
		errorCode02.put("00000", "邮件地址不正确或其他错误");
		errorCode02.put("10001","不支持的字符编码格式,系统支持的字符编码格式为1.UTF-8, 2.GBK,3.GB2312");
		errorCode02.put("10002","不支持的返回类型,系统支持的返回类型为1.页面返回,2.后台返回,3.同时支持页面和后");
		errorCode02.put("10003","不合法的页面返回地址,请使用一个未带参数的http 或者https 的合法URL 地址");
		errorCode02.put("10004","不合法的后台返回地址,请使用一个未带参数的http 或者https 的合法URL 地址");
		errorCode02.put("10005","不支持的网关接口版本号,目前系统支持的版本号为v2.0");
		errorCode02.put("10006","用户编号不存在");
		errorCode02.put("10007","付款方用户名不正确");
		errorCode02.put("10008","不支持的付款方联系方式,系统支持的联系方式为1.电子邮件,2.电话");
		errorCode02.put("10009","付款方的联系内容不正确,请输入合法的联系地址");
		errorCode02.put("10010","商家订单号不正确,系统只支持以字母,数字组合的订单号");
		errorCode02.put("10011","商家订单额不正确,请输入以分为单位的金额");
		errorCode02.put("10012","订单提交时间不正确,请输入以yyyymmddHIMMSS 格式的时间字符串");
		errorCode02.put("10013","商品名称不正确");
		errorCode02.put("10014","商品数量不正确");
		errorCode02.put("10015","商品ID不正确");
		errorCode02.put("10016","商品的描述不正确");
		errorCode02.put("10017","扩展参数一不正确");
		errorCode02.put("10018","扩展参数二不正确");
		errorCode02.put("10019","指定的支付方式不正确");
		errorCode02.put("10020","指定的支付服务代码不正确");
		errorCode02.put("10021","指定的银行代码不正确");
		errorCode02.put("10022","不支持的语言类型,系统支持的语言为1.中文,2.英文");
		errorCode02.put("10023","不支持的签名类型,系统支持的签名类型为1.MD5");
		errorCode02.put("10024","商户未开通人民币网关");
		errorCode02.put("10025","商户未开通分帐网关");
		errorCode02.put("20001","订单信息的签名内容不正确");
		errorCode02.put("20002","账户号已被冻结");
		errorCode02.put("20003","交易金额已超过限制");
		errorCode02.put("20004","商户的银行直联参数不正确");
		errorCode02.put("20005","不能使用优惠券");
		errorCode02.put("20006","商家账户不允许收款");
		errorCode02.put("20007","商家账户不支持网关收款");
		errorCode02.put("20008","原支付订单不存在");
		errorCode02.put("20009","余额不足，无法退款");
		errorCode02.put("20010","原始交易无法退款");
		errorCode02.put("20011","退款金额无效");
		errorCode02.put("20012","退款人与退款订单商家不一致");
		errorCode02.put("20013","已过退款有效期，无法退款");
		errorCode02.put("20014","待退款帐户冻结，无法退款");
		errorCode02.put("20015","退款帐户冻结，无法退款");
		errorCode02.put("20016","退款明细不正确");
		errorCode02.put("20017","退款总金额与退款明细总金额不相等");
		errorCode02.put("20018","待退款帐户止入，无法退款");
		errorCode02.put("20019","退款帐户止出，无法退款");
		errorCode02.put("20020","快钱退手续费错误");
		errorCode02.put("20021","退款pid与原支付订单pid不一致");
		errorCode02.put("20022","分帐操作未完成");
		errorCode02.put("20023","原支付订单状态为未完成");
		errorCode02.put("20024","分帐子订单状态为未完成");
		errorCode02.put("20025","退款明细重复");
		errorCode02.put("20026","已有相同的退款流水号");
		errorCode02.put("20027","退款明细必须有原主收款人退款信息");
		errorCode02.put("20033","账户校验失败");
		errorCode02.put("30001","子订单退款错误");
		errorCode02.put("100051","网关接口版本号不存在");
		errorCode02.put("100061","用户编号只能为数字");
		errorCode02.put("100231","查询类型不能为空");
		errorCode02.put("100101","商家订单号不存在");
		errorCode02.put("100121","订单提交时间必须在30天内");
		errorCode02.put("200011","订单信息的签名内容不存在");
		errorCode02.put("20036","交易被锁定");
		
	}
	
  public String ErrorCheck01(String errorId) {
	  return errorCode01.get(errorId);
  }
  public String ErrorCheck02(String errorId) {
	  return errorCode02.get(errorId);
  }
  
}
	