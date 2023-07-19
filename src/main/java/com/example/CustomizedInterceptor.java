package com.example;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class CustomizedInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    public CustomizedInterceptor() {
        super(Phase.POST_LOGICAL);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
    }

}
