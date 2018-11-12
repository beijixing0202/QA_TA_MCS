package com.bill99.ate.service.rm;

import org.springframework.data.redis.core.RedisTemplate;

import com.bill99.seashell.common.util.StringUtil;

public class QuatoService {

	private RedisTemplate strHashRedisTemplate;

	private RedisTemplate stringRedisTemplate;

	/**
	 * 
	 * @param memberCode
	 * @param bankAcctId
	 * @Description: 删除充值提现限额
	 */
	public void deleteCardAcctBalance(String memberCode, String bankAcctId) {
		stringRedisTemplate.delete("AcctBalance" + memberCode + "CardNo" + bankAcctId);
	}

	/**
	 * 
	 * @param bankAcctId
	 * @return
	 * @Description: 根据银行卡号生成rtmsJson
	 */
	public String getRtmsJsonByBankId(String bankAcctId) {
		return new StringBuffer("{\"appIP\":\"10.1.1.1\",\"cardNo\":\"" + bankAcctId + "\"}").toString();
	}

	/**
	 * 
	 * @param memberCodeGroup
	 * @param identityCardId
	 * @param bankAcctIdGroup
	 * @Description: 删除所有缓存
	 */
	public void deleteAllCache(String memberCode) {
		deleteDayAmt(memberCode);
		deleteYearAmt(memberCode);
		deleteAccuAmt(memberCode);
	}
	
	
	public void deleteDayAmt(String memberCode) {
		stringRedisTemplate.delete("LimitValueDay." + memberCode);
	}
	
	public void deleteYearAmt(String memberCode) {
		stringRedisTemplate.delete("rechargeYearAmt" + memberCode);
	}

	public void deleteAccuAmt(String memberCode) {
		stringRedisTemplate.delete("rechargeDayTimes" + memberCode);
	}
	
	public void setStrHashRedisTemplate(RedisTemplate strHashRedisTemplate) {
		this.strHashRedisTemplate = strHashRedisTemplate;
	}

	public void setStringRedisTemplate(RedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

}
