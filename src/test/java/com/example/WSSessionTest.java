package com.example;

import com.example.wssession.WSSessionPortType;
import jakarta.ejb.embeddable.EJBContainer;
import jakarta.xml.ws.Service;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.openejb.OpenEjbContainer;
import org.apache.openejb.loader.SystemInstance;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Properties;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// @Disabled("works when beans.xml will be deleted")
class WSSessionTest {

//    //Random port to avoid test conflicts
//    private static final int port = Integer.parseInt(System.getProperty("httpejbd.port", "" + org.apache.openejb.util.NetworkUtil.getNextAvailablePort()));
//
//    @BeforeAll
//    public static void setUp() throws Exception {
//        var properties = new Properties();
//        properties.setProperty("openejb.embedded.remotable", "true");
//
//        //Just for this test we change the default port from 4204 to avoid conflicts
//        properties.setProperty("httpejbd.port", "" + port);
//
//        properties.setProperty("httpejbd.print", "true");
//        properties.setProperty("httpejbd.indent.xml", "true");
//
//        properties.setProperty("logging.level.OpenEJB.server.http", "FINE");
//
//        // properties.put("openejb.deployments.classpath.filter.descriptors", "true");
//        // properties.put("openejb.deployments.classpath.exclude", ".*/..xml");
//        // properties.put("openejb.deployments.classpath.include", ".*openejb-jar.xml");
//        // properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.LocalInitialContextFactory");
//
//        EJBContainer.createEJBContainer(properties);
//    }
//
//    @Test
//    void test() throws Exception {
//        var calculatorService = Service.create(
//                new URL("http://localhost:"  + port + "/main/WSSession?wsdl"),
//                new QName("http://example.com/wssession/", "WSSession"));
//
//        assertNotNull(calculatorService);
//
//        WSSessionPortType session = calculatorService.getPort(WSSessionPortType.class);
//        session.login("bla", "bal", null, null);
//    }

    @Test
    void call() throws MalformedURLException {
        final EJBContainer container = EJBContainer.createEJBContainer(new Properties() {{
            setProperty(OpenEjbContainer.OPENEJB_EMBEDDED_REMOTABLE, "true");
            setProperty("httpejbd.port", "0"); // random port to avoid issue on CI, default is 4204
            // setProperty("openejb.deployments.classpath.exclude", ".*build.*");
        }});
        final int port = Integer.parseInt(SystemInstance.get().getProperty("httpejbd.port"));

        final Service service = Service.create(
                new URL("http://127.0.0.1:" + port + "/main/WSSession?wsdl"),
                new QName("http://example.com/wssession/", "WSSession"));

        final WSSessionPortType session = service.getPort(WSSessionPortType.class);
        ClientProxy.getClient(session).getOutInterceptors().add(
                new WSS4JOutInterceptor(new HashMap<>() {
                    {
                        put("action", "UsernameToken");
                        put("user", "openejb");
                        put("passwordType", "PasswordText");
                        put("passwordCallbackRef", (CallbackHandler) callbacks -> {
                            final WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
                            pc.setPassword("tomee");
                        });
                    }
                })
        );

        session.login("bla", "bal", null, null);

        container.close();
    }
}
