package com.bill99.mcs.service;

import com.bill99.mcs.service.impl.PayImpl;
import com.bill99.mcs.service.impl.RfdImpl;

import java.util.Map;

/**
 * Created by wentao.jia on 2018/9/20.
 */
public class PayFactory extends AbstractDeal{
    @Override
    public PayImpl Pay(Map<String, String> data){
        return new PayImpl();
    }
    @Override
    public RfdImpl Rfd (Map<String, String> data){
        return null;
    }
}

