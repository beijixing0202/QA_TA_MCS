package com.bill99.mcs.service.impl;

import com.bill99.cps.orm.impl.OracleDaoImpl;
import com.bill99.mcs.common.helper.SubmitUrl;
import com.bill99.mcs.orm.OracleDaoService;
import com.bill99.mcs.service.ManualClearing;
import com.bill99.mcs.tools.DbTypeConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import shelper.db.Oracle;
import shelper.iffixture.HttpFixture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 手动过清分实现类
 * Author: Henry.jia
 */
public class ManualClearingImp implements ManualClearing{

    @Autowired
    private OracleDaoService oracleDaoService;

    private HttpFixture doManualClearing(String idTxn) throws InterruptedException {
        HttpFixture request = new HttpFixture();
        ArrayList idTxnCtrls = new ArrayList();
        Oracle oracle = oracleDaoService.getInstance(DbTypeConstants.DB_TYPE_ORACLE_VPOS);
        String strQuery = "SELECT ID_TXN_CTRL FROM MASPOS.T_TXN_CTRL WHERE ID_TXN = '" + idTxn + "'";
        Reporter.log("表T_TXN_CTRL查询SQL：" + strQuery);
        Thread.sleep(5000);
        Long idTxnCtrl = Long.parseLong(oracle.query(strQuery));
        System.err.println("idTxnCtrl:"+idTxnCtrl);
        idTxnCtrls.add(0,idTxnCtrl);
        request.nextRequest();
        request.setUrl(SubmitUrl.Manual_Clearing_URL);
        request.addHeaderValue("Content-Type","Application/json");
        HashMap jsonMap = new HashMap();
        jsonMap.put("idTxnCtrls",idTxnCtrls);
        String json = new JSONObject(jsonMap).toString();
        System.err.println("json:"+json);
        request.addRequestBody(json);
        request.Post();
        return request;
    }
    @Override
    public String postingSuccess(String idTxn) throws InterruptedException {
        HttpFixture request = doManualClearing(idTxn);
        String regEx_IsFail = "Posting success";
        Pattern patternIsFail = Pattern.compile(regEx_IsFail);
        Matcher matcherIsFail = patternIsFail.matcher(request.getResponseBody());
        System.err.println(request.getResponseBody());
        //System.err.println(matcherIsFail.find());
        if (matcherIsFail.find()){
            System.err.println("posting success");
            return request.getResponseBody();
        }else{
            Thread.sleep(3000);
            postingSuccess(idTxn);
        }
            return null;
    }

}
