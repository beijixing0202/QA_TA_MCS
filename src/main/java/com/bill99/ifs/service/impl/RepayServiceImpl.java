package com.bill99.ifs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.testng.Reporter;

import com.bill99.ifs.common.dto.IfsUrlDto;
import com.bill99.ifs.fcs.api.message.RepayCheckItem;
import com.bill99.ifs.fcs.api.message.Repayment;
import com.bill99.ifs.fcs.api.message.RepaymentConfrimInfo;
import com.bill99.ifs.fcs.api.message.RepaymentConfrimInfoResult;
import com.bill99.ifs.fcs.api.message.Result;
import com.bill99.ifs.fcs.api.message.TxnInfo;
import com.bill99.ifs.fcs.api.message.ValidCodeResponse;
import com.bill99.ifs.fcs.api.message.WaitRepayInfoRequest;
import com.bill99.ifs.fcs.api.message.WaitRepayInfoResponse;
import com.bill99.ifs.fcs.api.service.CreditRepayService;
import com.bill99.ifs.fcs.api.service.RepaymentService;
import com.bill99.ifs.orm.IfsDao;
import com.bill99.ifs.service.RepayService;
import com.caucho.hessian.client.HessianProxyFactory;

public class RepayServiceImpl implements RepayService {

	public IfsUrlDto ifsUrlDto;
	public IfsDao ifsDao;

	@Override
	public List<TxnInfo> repayOne(Map<String, String> data) throws Exception {
		ifsDao.updateCashDrawByMemberCode(data.get("req_membercode"));
		ArrayList<TxnInfo> txnList=new ArrayList<TxnInfo>();
		//调用待还款信息列表查询接口
		WaitRepayInfoRequest req = new WaitRepayInfoRequest();
		req.setMemberCode(data.get("req_membercode"));
		req.setLastRowNum(Integer.parseInt(data.get("req_lastRowNum")));
		req.setPageSize(Integer.valueOf(data.get("req_pageSize")));
		req.setAppId(data.get("req_appId"));
		WaitRepayInfoResponse res=new WaitRepayInfoResponse();
		//判断是否全部还款
		if("all".equals(data.get("req_RepayType"))){
			res = queryAllWaitRepayInfo(req);
		}else {
			res = queryWaitRepayInfo(req);
		}

		Reporter.log("还款信息列表查询接口getResultCode=" + res.getResultCode()+ "getMsgInfo=" + res.getMsgInfo());
		Reporter.log("还款查询","00".equals(res.getResultCode()));

		if(res.getTotalRowNum()>0||res.getTotalAmt()>0){
			//调用还款确认接口
			RepaymentConfrimInfo info=new RepaymentConfrimInfo();
			for (int i = 0; i < res.getList().size(); i++) {
				TxnInfo txnInfo=new TxnInfo();
				//判断还款信息列表中的交易类型是现金分期还是普通消费，并取一条记录
				String txnAcctNo=res.getList().get(i).getTxnAcctNo();
				String useLImitType=ifsDao.getfcsTxnByTxnAcctNo(txnAcctNo).getUse_limit_type();
				if(data.get("req_useLImitType").equals(useLImitType)){
					BeanUtils.copyProperties(txnInfo, res.getList().get(i));
					txnInfo.setRepayAmt(res.getList().get(i).getTxnAmt());
					txnList.add(txnInfo);
					break;
				}
			}
			if (txnList.size() > 0) {
				info.setAppId(data.get("req_appId"));
				info.setMemberCode(data.get("req_membercode"));
				info.setList(txnList);
				RepaymentConfrimInfoResult confrimInfoResult=new RepaymentConfrimInfoResult();
				if("all".equals(data.get("req_RepayType"))){
					confrimInfoResult = getAllRepaymentInfo(info);
				}else{
					confrimInfoResult=getRepaymentInfo(info);
				}

				Reporter.log("还款确认接口getResultCode："+ confrimInfoResult.getResultCode() + "getMsgInfo："+ confrimInfoResult.getMsgInfo());
				Reporter.log("还款确认","00".equals(confrimInfoResult.getResultCode()));

				// 调用是否需要短信校验码接口
				RepayCheckItem checkItem = new RepayCheckItem();
				checkItem.setDeviceId(data.get("req_deviceId"));
				checkItem.setAppType(data.get("req_appType"));
				checkItem.setAppVersion(data.get("req_appVersion"));
				checkItem.setMemberCode(data.get("req_membercode"));
				checkItem.setPayType(confrimInfoResult.getPayTypeList().get(0).getPayType());
				Result checkResult = repaymentCheck(checkItem);
				Reporter.log("还款是否需要短信校验码接口getResultCode="+ checkResult.getResultCode() + "getMsgInfo="+ checkResult.getMsgInfo());
				Reporter.log("短信校验码接口","00".equals(checkResult.getResultCode()));

				// 调用还款接口
				Repayment repayment = new Repayment();
				repayment.setAppId(data.get("req_appId"));
				repayment.setMemberCode(data.get("req_membercode"));
				repayment.setPayPWD(data.get("req_payPWD"));
				repayment.setPayType(confrimInfoResult.getPayTypeList().get(0).getPayType());
				repayment.setRepayCode(checkResult.getSupplementInfo());
				repayment.setToken(checkResult.getExtInfo());
				repayment.setTxnList(txnList);
				Result repayResult=new Result();
				if("all".equals(data.get("req_RepayType"))){
					repayResult=repaymentAll(repayment);
				}else{
					repayResult=repayment(repayment);
				}

				Reporter.log("还款接口getResultCode="+repayResult.getResultCode()+"getMsgInfo="+repayResult.getMsgInfo());
				Reporter.log("还款接口！","00".equals(repayResult.getResultCode()));
				// 等待10秒待还款完成
				Thread.sleep(10000);
			}
		}
		return txnList;
	}
	/**查询全部待还款信息列表接口**/
	public WaitRepayInfoResponse queryAllWaitRepayInfo(WaitRepayInfoRequest req)
			throws Exception {
		CreditRepayService creditRepayService=setRepayService();
		WaitRepayInfoResponse response=creditRepayService.queryAllWaitRepayInfo(req);
		return response;
	}
	/**查询近七日待还款信息列表接口**/
	public WaitRepayInfoResponse queryWaitRepayInfo(WaitRepayInfoRequest req)
			throws Exception {
		CreditRepayService creditRepayService=setRepayService();
		WaitRepayInfoResponse response=creditRepayService.queryWaitRepayInfo(req);
		return response;
	}

	public CreditRepayService setRepayService() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		CreditRepayService creditRepayService = (CreditRepayService) factory
				.create(CreditRepayService.class, ifsUrlDto.getQueryRepayUrl());
		return creditRepayService;
	}
	/**还款接口（分期）**/
	public  Result repayment(Repayment repayment) throws Exception {
		RepaymentService repaymentService =initService();
		Result rs=repaymentService.repayment(repayment);
		return rs;
	}

	/**还款确认接口（分期）**/
	public RepaymentConfrimInfoResult getRepaymentInfo(RepaymentConfrimInfo info)throws Exception {
		RepaymentService repaymentService =initService();
		RepaymentConfrimInfoResult rs=repaymentService.getRepaymentInfo(info);
		return rs;
	}

	/**还款接口（整笔）**/
	public Result repaymentAll(Repayment repayment) throws Exception {
		RepaymentService repaymentService =initService();
		Result rs=repaymentService.repaymentAll(repayment);
		return rs;
	}

	/**还款接口（整笔）**/
	public RepaymentConfrimInfoResult getAllRepaymentInfo(RepaymentConfrimInfo info) throws Exception {
		RepaymentService repaymentService =initService();
		RepaymentConfrimInfoResult rs=repaymentService.getAllRepaymentInfo(info);
		return rs;
	}

	/**查询还款结果接口**/
	public Result queryRepayResult(String memberCode, long repayId)throws Exception {
		RepaymentService repaymentService =initService();
		Result rs=repaymentService.queryRepayResult(memberCode, repayId);
		return rs;
	}


	public RepaymentService initService() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		RepaymentService repaymentService = (RepaymentService) factory
				.create(RepaymentService.class, ifsUrlDto.getRepaymentUrl());
		return repaymentService;
	}

	public void setIfsUrlDto(IfsUrlDto ifsUrlDto) {
		this.ifsUrlDto = ifsUrlDto;
	}

	public Result repaymentCheck(RepayCheckItem repayCheckItem)
			throws Exception {
		RepaymentService repaymentService =initService();
		Result checkResult=repaymentService.repaymentCheck(repayCheckItem);
		return checkResult;
	}

	public ValidCodeResponse getMobileCheckCode(String memberCode,
			String payType, long repayTotalAmt) throws Exception {
		RepaymentService repaymentService =initService();
		ValidCodeResponse response=repaymentService.getMobileCheckCode(memberCode, payType, repayTotalAmt);
		return response;
	}
	public void setIfsDao(IfsDao ifsDao) {
		this.ifsDao = ifsDao;
	}




}
