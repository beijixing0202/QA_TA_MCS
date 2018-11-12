package com.bill99.mcs.orm.impl;

import com.bill99.mcs.common.dto.*;
import com.bill99.mcs.common.helper.FinanceSubjectConstants;
import com.bill99.mcs.common.helper.ParameterSource;
import com.bill99.mcs.orm.DbCheckService;
import com.bill99.mcs.orm.MySqlDaoServer;
import com.bill99.mcs.orm.OracleDaoService;
import com.bill99.mcs.tools.DbTypeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import shelper.db.MySql;
import shelper.db.Oracle;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 数据库校验接口实现
 * Author: zhenfeng.liu
 * Date: 2017/10/13 10:02
 */
public class DbCheckServiceImpl implements DbCheckService {
    @Autowired
    private OracleDaoService oracleDaoService;
    @Autowired
    private MySqlDaoServer mySqlDaoServer;


    
    @Override
    public Map<String, String> checkDataFromTTxnCtrlAtCPS(String extTraceNo, Map<String, String> data) {
        Reporter.log("----------CPS表数据校验==》对表T_TXN_CTRL开始校验----------");
        Oracle oracle = oracleDaoService.getInstance(DbTypeConstants.DB_TYPE_ORACLE_VPOS);
        
        String strQuery = "SELECT ID_TXN, TXN_FLG, APP_TYPE, AMT, STL_FLG, TERM_RECON_FLG, POSTING_FLG, TUNNEL_DATA " +
                "FROM MASPOS.T_TXN_CTRL WHERE EXT_TRACE_NO = '" + extTraceNo + "'";
        Reporter.log("表T_TXN_CTRL查询SQL：" + strQuery);
        String result = oracle.query(strQuery);
        Map<String, String> responseData = splitResult(result);
        Reporter.log("查询结果为：" + responseData);

        String expectedTxnFlg = data.get("expectedTxnFlg");
        String actualTxnFlg = responseData.get("TXN_FLG");
        Reporter.log("校验表T_TXN_CTRL--TXN_FLG字段,期望值为：" + expectedTxnFlg + ";实际值为：" + actualTxnFlg, expectedTxnFlg.equals
                (actualTxnFlg));

        String expectedAppType = data.get("expectedAppType");
        String actualAppType = responseData.get("APP_TYPE");
        Reporter.log("校验表T_TXN_CTRL--APP_TYPE字段,期望值为：" + expectedAppType + ";实际值为：" + actualAppType, expectedAppType
                .equals
                        (actualAppType));

        String expectedAMT = data.get("expectedAMT_1");
        String actualAMT = responseData.get("AMT");
        Reporter.log("校验表T_TXN_CTRL--AMT字段,期望值为：" + expectedAMT + ";实际值为：" + actualAMT, expectedAMT.equals(actualAMT));

        String expectedStlFlg = data.get("expectedStlFlg");
        String actualStlFlg = responseData.get("STL_FLG");
        Reporter.log("校验表T_TXN_CTRL--STL_FLG字段,期望值为：" + expectedStlFlg + ";实际值为：" + actualStlFlg, expectedStlFlg.equals
                (actualStlFlg));

        String expectedTermReconFlg = data.get("expectedTermReconFlg");
        String actualTermReconFlg = responseData.get("TERM_RECON_FLG");
        Reporter.log("校验表T_TXN_CTRL--TXN_Flg字段,期望值为：" + expectedTermReconFlg + ";实际值为：" + actualTermReconFlg,
                expectedTermReconFlg.equals(actualTermReconFlg));

        String expectedPostingFlg = data.get("expectedPostingFlg");
        String actualPostingFlg = responseData.get("POSTING_FLG");
        Reporter.log("校验表T_TXN_CTRL--TXN_Flg字段,期望值为：" + expectedPostingFlg + ";实际值为：" + actualPostingFlg,
                expectedPostingFlg.equals(actualPostingFlg));
        Reporter.log("**********************************表T_TXN_CTRL校验结束**********************************");
        return responseData;
    }
    
    @Override
  	public void updateTTxnCtrl(String idTxn) {
    	 Oracle oracle = oracleDaoService.getInstance(DbTypeConstants.DB_TYPE_ORACLE_VPOS);
    	 String updatesql="update  maspos.t_txn_ctrl set stl_flg='1',term_recon_flg='C' where id_txn in ('"+
    			 idTxn + "')";
    	// System.err.println(updatesql);
    	 oracle.Update(updatesql);	
    	 oracle.closeDBcon();
  	}

  	@Override
    public void updateTTxnCtrlPix(String sql){
        MySql mySql=mySqlDaoServer.getInstance(DbTypeConstants.DB_TYPE_MYSQL_VPOS);
        mySql.Update(sql);
        mySql.closeDBcon();
    }

  	@Override
    public void checkTStlListNum(String idTxn,Map<String, String> data){
        Oracle oracle = oracleDaoService.getInstance(DbTypeConstants.DB_TYPE_ORACLE_VPOS);
        
        String sql="select count(*) from  maspos.t_stl_list  where stl_src_ref in ('"+
                idTxn + "') and crt_time>sysdate-1 ";
        String actualNum=oracle.query(sql);
        oracle.closeDBcon();
        String expectedNum=data.get("expectedNum");
        
        Reporter.log("校验表T_STL_LIST记录数，期望值为：" + expectedNum + ";实际值为：" + actualNum, expectedNum.equals
                (actualNum));

    }


    @Override
    public void checkDataFromTClrTxnListAtMCSClear(TClrTxnList tClrTxnList, Map<String, String> data) {
        Reporter.log("MCS清分表数据校验==》T_CLR_TXN_LIST表查询结果为：" + tClrTxnList.toString());
        String expectedAmt = data.get("expectedAmt_2");
        String actualAmt = tClrTxnList.getAmt();
        Reporter.log("校验表T_CLR_TXN_LIST--AMT字段,期望值为：" + expectedAmt + ";实际值为：" + actualAmt, expectedAmt.equals
                (actualAmt));

        String actualAuthRef = tClrTxnList.getAuthRef();
        Reporter.log("校验表T_CLR_TXN_LIST--AUTH_REF字段,期望值为有值;实际值为：" + actualAuthRef, null != actualAuthRef);

        String expectedFee = data.get("expectedFee");
        double actualFee = tClrTxnList.getFee();
        Reporter.log("校验表T_CLR_TXN_LIST--FEE字段,期望值为：" + expectedFee + ";实际值为：" + actualFee, actualFee == Double
                .valueOf(expectedFee));

//        String expectedFeeRate = data.get("expectedFeeRate");
//        String actualFeeRate = tClrTxnList.getFeeRate();
//        Reporter.log("校验表T_CLR_TXN_LIST--FEE_RATE字段,期望值为："+expectedFeeRate+";实际值为：" + actualFeeRate, actualFeeRate.equals(expectedFeeRate));

        String expectedDiscAmt = data.get("expectedDiscAmt");
        String actualDiscAmt = tClrTxnList.getDiscAmt();
        Reporter.log("校验表T_CLR_TXN_LIST--DISC_AMT字段,期望值为：" + expectedDiscAmt + ";实际值为：" + actualDiscAmt,
                expectedDiscAmt.equals(actualDiscAmt));

        String expectedDiscRate = data.get("expectedDiscRate");
        double actualDiscRate = tClrTxnList.getDiscRate();
        Reporter.log("校验表T_CLR_TXN_LIST--DISC_RATE字段,期望值为：" + expectedDiscRate + ";实际值为：" + actualDiscRate,
                expectedDiscRate.equals(Double.toString(actualDiscRate)));
        Reporter.log("**********************************表T_CLR_TXN_LIST校验结束**********************************");
    }


    @Override
    public void checkDataFromTStlListAtMCSClear(TStlList tStlList, Map<String, String> data) {
        Reporter.log("MCS清分表数据校验==》T_STL_LIST表查询结果为：" + tStlList.toString());
        String expectedStatus = data.get("expectedStatus_1");
        String actualStatus = tStlList.getStatus();
        Reporter.log("校验表T_STL_LIST--STATUS字段,期望值为：" + expectedStatus + ";实际值为：" + actualStatus, expectedStatus
                .equals(actualStatus));

        String expectedAmt = data.get("expectedAmt1");
        double actualAmt1 = tStlList.getAmt1();
        Reporter.log("校验表T_STL_LIST--AMT1字段,期望值为：" + expectedAmt + ";实际值为：" + actualAmt1, actualAmt1 == Double
                .valueOf(expectedAmt));

        String expectedNetTotal = data.get("expectedNetTotal");
        double actualNetTotal = tStlList.getNetTotal();
        Reporter.log("校验表T_STL_LIST--NET_TOTAL字段,期望值为：" + expectedNetTotal + ";实际值为：" + actualNetTotal,
                actualNetTotal == Double.valueOf(expectedNetTotal));
        Reporter.log("**********************************表T_STL_LIST校验结束**********************************");
    }

    @Override
    public void checkDataFromTBkApply(TBkApply tBkApply, Map<String, String> data) {
        Reporter.log("CSPPE清分/结算表数据校验==》T_BK_APPLY表查询结果为：" + tBkApply.toString());
        String expectedBkStatus = data.get("expectedBkStatus");
        String actualBkStatus = tBkApply.getBkStatus();
        Reporter.log("校验表T_BK_APPLY--BK_STATUS字段,期望值为：" + expectedBkStatus + ";实际值为：" + actualBkStatus,
                actualBkStatus.equals(expectedBkStatus));
        Reporter.log("**********************************表T_BK_APPLY校验结束**********************************");
    }

    @Override
    public void checkDataFromTBkRecord(TBkRecord tBkRecord, Map<String, String> data) {
        Reporter.log("CSPPE清分/结算表数据校验==》T_BK_RECORD表查询结果为：" + tBkRecord.toString());
        String expectedStatus = data.get("expectedStatus_2");
        String actualStatus = tBkRecord.getStatus();
        Reporter.log("校验表T_BK_RECORD--STATUS字段,期望值为：" + expectedStatus + ";实际值为：" + actualStatus,
                actualStatus.equals(expectedStatus));
        Reporter.log("**********************************表T_BK_RECORD校验结束**********************************");
    }

    @Override
    public void checkDataFromTBkPePaymentOrderAtCSPPEClear(TBkPePaymentOrder tBkPePaymentOrder, Map<String, String>
            data) {
        Reporter.log("CSPPE清分表数据校验==》T_BK_PE_PAYMENT_ORDER表查询结果为：" + tBkPePaymentOrder.toString());
        String expectedOrderAmt = data.get("expectedOrderAmt");
        double actualOrderAmt = tBkPePaymentOrder.getOrderAmt();
        Reporter.log("校验表T_BK_PE_PAYMENT_ORDER--ORDER_AMT字段,期望值为：" + expectedOrderAmt + ";实际值为：" + actualOrderAmt,
                actualOrderAmt == Double.valueOf(expectedOrderAmt));
        Reporter.log("**********************************表T_BK_PE_PAYMENT_ORDER校验结束**********************************");
    }

    @Override
    public void checkDataFromTBkPePaymentOrderAtCSPPESettlement(TBkPePaymentOrder tBkPePaymentOrder, TStlOrder
            tStlOrder) {
        Reporter.log("CSPPE结算表数据校验==》T_BK_PE_PAYMENT_ORDER表查询结果为：" + tBkPePaymentOrder.toString());
        Reporter.log("CSPPE结算表数据校验==》T_STL_ORDER表查询结果为：" + tStlOrder.toString());
        double expectedOrderAmt = tStlOrder.getAmt();
        double actualOrderAmt = tBkPePaymentOrder.getOrderAmt();
        Reporter.log("校验表T_BK_PE_PAYMENT_ORDER--ORDER_AMT字段,期望值为：" + expectedOrderAmt + ";实际值为：" + actualOrderAmt,
                actualOrderAmt == expectedOrderAmt);
        Reporter.log("**********************************表T_BK_PE_PAYMENT_ORDER校验结束**********************************");
    }

    @Override
    public void checkDataFromTBkPeDealAtCSPPEClear(List<TBkPeDeal> tBkPeDeals, Map<String, String> data) {
        Reporter.log("CSPPE清分表数据校验==》T_BK_DEAL表查询结果为：" + tBkPeDeals.toString());
        String expectedAmt1 = data.get("expectedDealAmt_1");
        String expectedAmt2 = data.get("expectedDealAmt_2");
        double actualAmt1 = 0;
        double actualAmt2 = 0;

        for (TBkPeDeal tBkPeDeal : tBkPeDeals) {
            String priority = tBkPeDeal.getPriority();
            if ("1".equals(priority)) {
                actualAmt1 = tBkPeDeal.getDealAmt();
            } else {
                actualAmt2 = tBkPeDeal.getDealAmt();
            }
        }
        boolean b1 = (actualAmt1 == Double.valueOf(expectedAmt1));
        boolean b2 = (actualAmt2 == Double.valueOf(expectedAmt2));
        Reporter.log("校验表T_BK_PE_DEAL--DEAL_AMT字段,期望值为：" + expectedAmt1 + "/" + expectedAmt2 + ";实际值为：" + actualAmt1
                + "/" + actualAmt2, b1 && b2);
        Reporter.log("**********************************表T_BK_DEAL校验结束**********************************");
    }
    @Override
    public void checkDataFromTBkPeDealAtCSPPEClearOne(TBkPeDeal tBkPeDeals, Map<String, String> data){
        Reporter.log("CSPPE清分表数据校验==》T_BK_DEAL表查询结果为：" + tBkPeDeals.toString());
        String expectedAmt=data.get("expectedDealAmt");
        double actualAmt=tBkPeDeals.getDealAmt();
        Reporter.log("校验表T_BK_PE_DEAL--DEAL_AMT字段,期望值为：" + expectedAmt + ";实际值为：" + actualAmt,actualAmt == Double.valueOf(expectedAmt));
        String expectedStatus=data.get("expectedDealStatus");
        BigDecimal actualStatus = tBkPeDeals.getDealStatus();
       // Reporter.log("校验表T_BK_PE_DEAL--DEAL_STATUS字段,期望值为："+expectedStatus+";实际值:"+actualStatus, actualStatus == BigDecimal.valueOf(Double.valueOf(expectedStatus)));
    
        Reporter.log("校验表T_BK_PE_DEAL--DEAL_STATUS字段,期望值为：" + expectedStatus + ";实际值为：" + actualStatus,actualStatus.doubleValue() == Double.valueOf(expectedStatus));
        Reporter.log("**********************************表T_BK_DEAL校验结束**********************************");

    }
    @Override
    public void checkDataFromTBkPeDealAtCSPPESettlement(List<TBkPeDeal> tBkPeDeals, TStlOrder tStlOrder) {
        Reporter.log("CSPPE结算表数据校验==》T_BK_DEAL表查询结果为：" + tBkPeDeals.toString());
        double expectedAmt = tStlOrder.getAmt();
        // 结算job后DEAL表只会有一条数据
        double actualDealAmt = tBkPeDeals.get(0).getDealAmt();
        Reporter.log("校验表T_BK_PE_DEAL--DEAL_AMT字段,期望值为：" + expectedAmt + ";实际值为：" + actualDealAmt, expectedAmt ==
                actualDealAmt);
        Reporter.log("**********************************表T_BK_DEAL校验结束**********************************");
    }

    @Override
    public void checkDataFromTStlListAtMCSSettlement(TStlList tStlList) {
        Reporter.log("MCS结算表数据校验==》T_STL_LIST表查询结果为：" + tStlList.toString());
        String actualStatus = tStlList.getStatus();
        Reporter.log("校验表T_STL_LIST--STATUS字段,期望值为：2;实际值为：" + actualStatus, "2".equals(actualStatus));
        Reporter.log("**********************************表T_STL_LIST校验结束**********************************");
    }

    @Override
    public void checkDataFromTStlOrderAtMCSSettlement(TStlOrder tStlOrder, List<TStlList> tStlLists) {
        Reporter.log("MCS结算表数据校验==》T_STL_ORDER表查询结果为：" + tStlOrder.toString());
        Reporter.log("MCS结算表数据校验==》T_STL_LIST表查询结果列表为：" + tStlLists.toString());
        String actualStatus = tStlOrder.getStatus();
        double expectedAmt = tStlOrder.getAmt();
        double sumAmt = 0;
        for (TStlList tStlList : tStlLists) {
            sumAmt += tStlList.getNetTotal();
        }
        Reporter.log("校验表T_STL_ORDER--STATUS字段,期望值为：1;实际值为：" + actualStatus, "1".equals(actualStatus));
        Reporter.log("校验表T_STL_ORDER表AMT及T_STL_LIST表NET_TOTAL值总和,期望值为：" + expectedAmt + ";实际值为：" + sumAmt,
                expectedAmt == sumAmt);
        Reporter.log("**********************************表T_STL_ORDER校验结束**********************************");
    }

    @Override
    public void checkDataFromPaymentOrderAtPEClear(PaymentOrder paymentOrder, Map<String, String> data) {
        Reporter.log("PE清分记账表数据校验==》PAYMENTORDER表查询结果为：" + paymentOrder.toString());
        String expectedOrderAmount = data.get("expectedOrderAmount");
        String actualOrderAmount = paymentOrder.getOrderamount();
        int actualOrderStatus = paymentOrder.getOrderstatus();
        Reporter.log("校验表PAYMENTORDER--ORDERAMOUNT字段,期望值为：" + expectedOrderAmount + ";实际值为：" + actualOrderAmount,
                expectedOrderAmount.equals(actualOrderAmount));
        Reporter.log("校验表PAYMENTORDER--ORDERSTATUS字段,期望值为：111;实际值为：" + actualOrderStatus, 111 == actualOrderStatus);
        Reporter.log("**********************************表PAYMENTORDER校验结束**********************************");
    }

    @Override
    public void checkDataFromPaymentOrderACSPEClear(PaymentOrder paymentOrder, Map<String, String> data){
    	 Reporter.log("PE清分记账表数据校验==》PAYMENTORDER表查询结果为：" + paymentOrder.toString());
         String expectedACSOrderAmount = data.get("expectedACSOrderAmount");
         String actualOrderAmount = paymentOrder.getOrderamount();
         int actualOrderStatus = paymentOrder.getOrderstatus();
         Reporter.log("校验表PAYMENTORDER--ORDERAMOUNT字段,期望值为：" + expectedACSOrderAmount + ";实际值为：" + actualOrderAmount,
        		 expectedACSOrderAmount.equals(actualOrderAmount));
         Reporter.log("校验表PAYMENTORDER--ORDERSTATUS字段,期望值为：111;实际值为：" + actualOrderStatus, 111 == actualOrderStatus);
         Reporter.log("**********************************表PAYMENTORDER校验结束**********************************");
    	
    }
    @Override
    public void checkDataFromPaymentOrderAtPESettlement(PaymentOrder paymentOrder, TStlOrder tStlOrder) {
        Reporter.log("PE结算记账表数据校验==》PAYMENTORDER表查询结果为：" + paymentOrder.toString());
        double expectedOrderAmount = tStlOrder.getAmt();
        // 单位为厘
        expectedOrderAmount *= 1000;
        String actualOrderAmount = paymentOrder.getOrderamount();
        int actualOrderStatus = paymentOrder.getOrderstatus();
        int orderCode = paymentOrder.getOrdercode();
        Reporter.log("校验表PAYMENTORDER--ORDERAMOUNT字段,期望值为：" + expectedOrderAmount + ";实际值为：" + actualOrderAmount,
                expectedOrderAmount == Double.valueOf(actualOrderAmount));
        Reporter.log("校验表PAYMENTORDER--ORDERSTATUS字段,期望值为：111;实际值为：" + actualOrderStatus, 111 == actualOrderStatus);
//        Reporter.log("校验表PAYMENTORDER--ORDERCODE字段,期望值为：114;实际值为：" + orderCode, 114 == orderCode);
        Reporter.log("**********************************表PAYMENTORDER校验结束**********************************");
    }

    @Override
    public void checkDataFromDealAtPEClear(List<Deal> deals, Map<String, String> data) {
        Reporter.log("PE清分记账表数据校验==》DEAL表查询结果为：" + deals.toString());
        if(deals.size()==1){
        	String expectedDealAmount=data.get("expectedDealAmount");
        	double actualDealAmount=deals.get(0).getDealamount();
        	Reporter.log("校验表DEAL--DEALAmount字段,期望值为："+expectedDealAmount+";实际值为："+actualDealAmount, actualDealAmount==Double.valueOf(expectedDealAmount));
        	int expectedDealStatus = 1; 
        	int actualDealStatus=deals.get(0).getDealstatus();
        	Reporter.log("校验表DEAL--DEALStatus字段,期望值为："+expectedDealStatus+";实际值为："+actualDealStatus, actualDealStatus==expectedDealStatus);
        	Reporter.log("**********************************表DEAL校验结束**********************************");
        	
        }else{
        	 String expectedDealAmount1 = data.get("expectedOrderAmount");
             String expectedDealAmount2 = data.get("expectedDealAmount");
             double actualDealAmount1 = 0;
             double actualDealAmount2 = 0;
             int expectedDealStatus = 1;
             int actualDealStatus1 = 0;
             int actualDealStatus2 = 0;
             boolean b3;

             for (Deal deal : deals) {
                 if (null != deal.getFeetype()) {
                     actualDealAmount1 = deal.getDealamount();
                     actualDealStatus1 = deal.getDealstatus();
                 } else {
                     actualDealAmount2 = deal.getDealamount();
                     actualDealStatus2 = deal.getDealstatus();
                 }
             }
             boolean b1 = actualDealAmount1 == Double.valueOf(expectedDealAmount1);
             boolean b2 = actualDealAmount2 == Double.valueOf(expectedDealAmount2);
             b3 = expectedDealStatus == actualDealStatus1 && expectedDealStatus == actualDealStatus2;
             Reporter.log("校验表DEAL--DEALSTATUS字段,期望值为：1/1;实际值为：" + actualDealStatus1 + "/" + actualDealStatus2, b3);
             Reporter.log("校验表DEAL--DEALAMOUNT字段,期望值为：" + expectedDealAmount1 + "/" + expectedDealAmount2 + ";实际值为：" +
                     actualDealAmount1 + "/" + actualDealAmount2, b1 && b2);
             Reporter.log("**********************************表DEAL校验结束**********************************");
        }
    }
    
    @Override
    public void checkDataFromDealACSPEClear(List<Deal> deals, Map<String, String> data){
    	Reporter.log("PE清分记账表数据校验==》DEAL表查询结果为：" + deals.toString());
    	String expectedACSDealAmount=data.get("expectedACSDealAmount");
    	double actualDealAmount=deals.get(0).getDealamount();
    	Reporter.log("校验表DEAL--DEALAmount字段,期望值为："+expectedACSDealAmount+";实际值为："+actualDealAmount, actualDealAmount==Double.valueOf(expectedACSDealAmount));
    	int expectedDealStatus = 1; 
    	int actualDealStatus=deals.get(0).getDealstatus();
    	Reporter.log("校验表DEAL--DEALStatus字段,期望值为："+expectedDealStatus+";实际值为："+actualDealStatus, actualDealStatus==expectedDealStatus);
    	Reporter.log("**********************************表DEAL校验结束**********************************");
    }

    @Override
    public void checkDataFromDealAtPESettlement(List<Deal> deals, TStlOrder tStlOrder) {
        Reporter.log("PE结算记账表数据校验==》DEAL表查询结果为：" + deals.toString());
        double expectedDealAmount = tStlOrder.getAmt();
        // 单位为厘
        expectedDealAmount *= 1000;
        double actualDealAmount = deals.get(0).getDealamount();
        int actualDealStatus = deals.get(0).getDealstatus();
        Reporter.log("校验表DEAL--DEALAMOUNT字段,期望值为：" + expectedDealAmount + ";实际值为：" + actualDealAmount,
                actualDealAmount == expectedDealAmount);
        Reporter.log("校验表DEAL--DEALSTATUS字段,期望值为：1;实际值为：" + actualDealStatus, 1 == actualDealStatus);
        Reporter.log("**********************************表DEAL校验结束**********************************");
    }

    @Override
    public void checkDataFromEntryAtPEClear(List<Entry> entries, Map<String, String> data) {
        Reporter.log("PE清分记账表数据校验==》ENTRY表查询结果为：" + entries.toString());
        String expectedEntryAmt1 = data.get("expectedEntryAmt1");  // 100000
        String expectedEntryAmt2 = data.get("expectedEntryAmt2");  // 99800
        String expectedEntryAmt3 = data.get("expectedEntryAmt3");  // 200
        String expectedEntryAmt4 = data.get("expectedEntryAmt4");  // 4500
        for (Entry entry : entries) {
            String acctCode = entry.getAcctCode();
            if (acctCode.equals(FinanceSubjectConstants.AUTHORIZE_NETWORK_SUBJECT)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                if (crdr == 1) {
                    Reporter.log("校验表DEAL--授权网络科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                            value == Double.valueOf(expectedEntryAmt1));
                } else {
                    Reporter.log("校验表DEAL--授权网络科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                            value == Double.valueOf(expectedEntryAmt4));
                }
            } else if (acctCode.equals(FinanceSubjectConstants.BANK_COST_FEE_SUBJECT)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                Reporter.log("校验表DEAL--银行成本手续费科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr, value
                        == Double.valueOf(expectedEntryAmt4));
            } else if (acctCode.equals(FinanceSubjectConstants.BILL_COST_SUBJECCT)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                Reporter.log("校验表DEAL--快钱手续费科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr, value
                        == Double.valueOf(expectedEntryAmt3));
            } else if (acctCode.equals(FinanceSubjectConstants.TO_BE_SETTLED_SUBJECCT)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                Reporter.log("校验表DEAL--待结算科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr, value
                        == Double.valueOf(expectedEntryAmt2));
            }
            else if (acctCode.equals(FinanceSubjectConstants.BANK_COST_FEE_SUBJECT_PIX)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                Reporter.log("校验表DEAL--银行成本手续费科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr, value
                        == Double.valueOf(expectedEntryAmt3));
            }
            else if (acctCode.equals(FinanceSubjectConstants.BILL_COST_SUBJECCT_PIX)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                Reporter.log("校验表DEAL--快钱手续费科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr, value
                        == Double.valueOf(expectedEntryAmt2));
            }

            else if(acctCode.equals(FinanceSubjectConstants.TO_BE_SETTLED_SUBJECCT_PIX)){
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                if (crdr == 1) {
                    Reporter.log("校验表DEAL--待结算科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                            value == Double.valueOf(expectedEntryAmt2));
                } else {
                    Reporter.log("校验表DEAL--待结算科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                            value == Double.valueOf(expectedEntryAmt1));
                }

            }

        }
        Reporter.log("**********************************表ENTRY校验结束**********************************");
    }

    @Override
    public void checkDataFromEntryAtPESettlement(List<Entry> entries, TStlOrder tStlOrder) {
        Reporter.log("PE结算记账表数据校验==》ENTRY表查询结果为：" + entries.toString());
        double expectedEntryAmt = tStlOrder.getAmt();
        expectedEntryAmt *= 1000;
        for (Entry entry : entries) {
            String acctCode = entry.getAcctCode();
            if (acctCode.equals(FinanceSubjectConstants.TO_BE_SETTLED_ACCOUNT)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                Reporter.log("校验表DEAL--待结算科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                            value == expectedEntryAmt);
            } else if (acctCode.equals(FinanceSubjectConstants.RMB_SUBJECT)) {
                double value = entry.getValue();
                int crdr = entry.getCrdr();
                Reporter.log("校验表DEAL--人民币账户,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                        value == expectedEntryAmt);
            }else{
    			Reporter.log("校验表ENTRY--记账数据正确", entries.size()==2);
    		}
        }
        Reporter.log("**********************************表ENTRY校验结束**********************************");
    }
    @Override
    public void checkDataFromEntryPEClear(List<Entry> entries,Map<String, String> data ){
    	Reporter.log("PE记账表数据校验==》ENTRY表查询结果为：" + entries.toString());
    	for(Entry entry:entries){
    		String acctCode=entry.getAcctCode();
    		if(acctCode.equals(FinanceSubjectConstants.BANK_COST_FEE_SUBJECT)){
    			double value=entry.getValue();
    			String expectedEntryAmt1=data.get("expectedEntryAmt1");
    			int crdr=entry.getCrdr();
    			Reporter.log("校验表DEAL--银行成本手续费科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                        value ==Double.valueOf(expectedEntryAmt1) );
    		}else if(acctCode.equals(FinanceSubjectConstants.AUTHORIZE_NETWORK_SUBJECT)){
    			double value=entry.getValue();
    			String expectedEntryAmt2=data.get("expectedEntryAmt2");
    			int crdr=entry.getCrdr();
    			Reporter.log("校验表DEAL--授权网络科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                        value ==Double.valueOf(expectedEntryAmt2) );
    		}else{
    			Reporter.log("校验表DEAL--记账科目号不正确", false);
    		}
    	}
    	
    }
  
    @Override
    public void checkDataFromEntryACSPEClear(List<Entry> entries,Map<String, String> data ){
    	Reporter.log("PE记账表数据校验==》ENTRY表查询结果为：" + entries.toString());
    	for(Entry entry:entries){
    		String acctCode=entry.getAcctCode();
    		if(acctCode.equals(FinanceSubjectConstants.AUTHORIZE_NETWORK_SUBJECT)){
    			double value=entry.getValue();
    			String expectedEntryAmt3=data.get("expectedEntryAmt3");
    			int crdr=entry.getCrdr();
    			Reporter.log("校验表DEAL--授权网络科目,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                        value ==Double.valueOf(expectedEntryAmt3) );
    		}else if(acctCode.equals("20100201"+data.get("memberCode")+"01")){
    			double value=entry.getValue();
    			String expectedEntryAmt4=data.get("expectedEntryAmt4");
    			int crdr=entry.getCrdr();
    			Reporter.log("校验表DEAL--人民币账户,科目号：" + acctCode + ";额度为：" + value + ";借贷标志(1:DR,2:CR)为：" + crdr,
                        value ==Double.valueOf(expectedEntryAmt4) );
    		}else{
    			Reporter.log("校验表DEAL--记账科目号不正确", false);
    		}
    	}
    	
    }
    
    @Override
	public void checkDataFromTAcctTxn(TAcctTxn tAcctTxn,Map<String, String> data) {
    	Reporter.log("T_ACCT_TXN表查询结果为："+tAcctTxn.toString());
    	String expectedTxnFlg=data.get("expectedTxnFlg");
    	String actualStatus=tAcctTxn.getTxnStatus();
    	Reporter.log("校验表T_ACCT_TXN--TXN_STATUS 字段,期望值为：" + expectedTxnFlg + ";实际值为：" + actualStatus,
    			 expectedTxnFlg.equals(actualStatus));
    	String expectedAppType=data.get("expectedAppType");
    	String actualAppType=tAcctTxn.getAppType();
    	Reporter.log("校验表T_ACCT_TXN--APP_TYPE 字段,期望值为：" + expectedAppType + ";实际值为：" + actualAppType,
    			expectedAppType.equals(actualAppType));
    	String expectedAMT=data.get("expectedAMT_1");
    	String actualAmt = tAcctTxn.getAmt();
    	Reporter.log("校验表T_ACCT_TXN--AMT 字段,期望值为：" + expectedAMT + ";实际值为：" + actualAmt,
    			expectedAMT.equals(actualAmt));
    	Reporter.log("**********************************表T_ACCT_TXN校验结束**********************************");
    
	}

    private Map<String, String> splitResult(String result) {
        Map<String, String> responseData = new HashMap<String, String>();
        String[] strSplit = result.split(";");
        for (int i = 0; i < ParameterSource.tTxnCtrlParameter.length; i++) {
            responseData.put(ParameterSource.tTxnCtrlParameter[i], strSplit[i]);
        }
        return responseData;
    }
    @Override
    public Map<String,String> checkDataFromTTxnCtrlPIX(String idTxn,Map<String,String> data){

        Reporter.log("----------PIX表数据校验==》对表T_TXN_CTRL开始校验----------");
        MySql mysql=mySqlDaoServer.getInstance(DbTypeConstants.DB_TYPE_MYSQL_VPOS);

        String strQuery = "SELECT ID_TXN, TXN_FLG, APP_TYPE, AMT, STL_FLG, TERM_RECON_FLG, POSTING_FLG, TUNNEL_DATA " +
                "FROM T_TXN_CTRL WHERE EXT_TRACE_NO = '" + idTxn + "'";
        Reporter.log("表T_TXN_CTRL查询SQL：" + strQuery);
        String result = mysql.query(strQuery);
        Map<String, String> responseData = splitResult(result);
        Reporter.log("查询结果为：" + responseData);

        String expectedTxnFlg = data.get("expectedTxnFlg");
        String actualTxnFlg = responseData.get("TXN_FLG");
        Reporter.log("校验表T_TXN_CTRL--TXN_FLG字段,期望值为：" + expectedTxnFlg + ";实际值为：" + actualTxnFlg, expectedTxnFlg.equals
                (actualTxnFlg));

        String expectedAppType = data.get("expectedAppType");
        String actualAppType = responseData.get("APP_TYPE");
        Reporter.log("校验表T_TXN_CTRL--APP_TYPE字段,期望值为：" + expectedAppType + ";实际值为：" + actualAppType, expectedAppType
                .equals
                        (actualAppType));

        String expectedAMT = data.get("expectedAMT_1");
        String actualAMT = responseData.get("AMT");
        Reporter.log("校验表T_TXN_CTRL--AMT字段,期望值为：" + expectedAMT + ";实际值为：" + actualAMT, expectedAMT.equals(actualAMT));

        String expectedStlFlg = data.get("expectedStlFlg");
        String actualStlFlg = responseData.get("STL_FLG");
        Reporter.log("校验表T_TXN_CTRL--STL_FLG字段,期望值为：" + expectedStlFlg + ";实际值为：" + actualStlFlg, expectedStlFlg.equals
                (actualStlFlg));

        String expectedTermReconFlg = data.get("expectedTermReconFlg");
        String actualTermReconFlg = responseData.get("TERM_RECON_FLG");
        Reporter.log("校验表T_TXN_CTRL--TXN_Flg字段,期望值为：" + expectedTermReconFlg + ";实际值为：" + actualTermReconFlg,
                expectedTermReconFlg.equals(actualTermReconFlg));

        String expectedPostingFlg = data.get("expectedPostingFlg");
        String actualPostingFlg = responseData.get("POSTING_FLG");
        Reporter.log("校验表T_TXN_CTRL--TXN_Flg字段,期望值为：" + expectedPostingFlg + ";实际值为：" + actualPostingFlg,
                expectedPostingFlg.equals(actualPostingFlg));
        Reporter.log("**********************************表T_TXN_CTRL校验结束**********************************");
        return responseData;
    }






}
