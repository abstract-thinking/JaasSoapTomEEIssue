package com.example;

import com.example.wssession.Message;
import com.example.wssession.WSSessionPortType;
import jakarta.ejb.Stateless;
import jakarta.jws.WebService;
import jakarta.xml.ws.Holder;

@Stateless
@WebService(
        serviceName = "WSSession",
        portName = "WSSession",
        wsdlLocation = "classpath:wsdl/WSSession.wsdl",
        targetNamespace = "http://example.com/wssession/",
        endpointInterface = "com.example.wssession.WSSessionPortType")
public class WSSession implements WSSessionPortType {

    @Override
    public void login(String username, String password, Holder<String> sessionId, Holder<Message> message) {
    }

    @Override
    public Message logout(String sessionId) {
        return null;
    }

}
