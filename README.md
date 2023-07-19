# JaasSoapTomEEIssue

Set up TomEE apache-tomee-plus-9.1.0 (/opt/tomee/apache-tomee-plus-9.1.0)

Configure TomEE with JAAS configuration from src/tomee

Build war: gradle war

copy war
cp build/libs/JaasSoapTomEEIssue-1.0-SNAPSHOT.war /opt/tomee/apache-tomee-plus-9.1.0/webapps/example.war

Start TomEE server
http://localhost:8080/example/webservices/WSSession?wsdl

Send Soap request with Soap Client
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:wss="http://example.com/wssession/">
   <soapenv:Header/>
   <soapenv:Body>
      <wss:login>
         <!--Optional:-->
         <wss:username>?</wss:username>
         <!--Optional:-->
         <wss:password>?</wss:password>
      </wss:login>
   </soapenv:Body>
</soapenv:Envelope>

19-Jul-2023 10:30:09.321 WARNING [http-nio-8080-exec-9] org.apache.cxf.phase.PhaseInterceptorChain.doDefaultLogging Interceptor for {http://example.com/wssession/}WSSession has thrown 
exception, unwinding now
        org.apache.cxf.binding.soap.SoapFault: Error reading XMLStreamReader.
                at org.apache.cxf.binding.soap.saaj.SAAJInInterceptor$SAAJPreInInterceptor.handleMessage(SAAJInInterceptor.java:145)
                at org.apache.cxf.binding.soap.saaj.SAAJInInterceptor$SAAJPreInInterceptor.handleMessage(SAAJInInterceptor.java:107)
                at org.apache.cxf.phase.PhaseInterceptorChain.doIntercept(PhaseInterceptorChain.java:307)
                at org.apache.cxf.transport.ChainInitiationObserver.onMessage(ChainInitiationObserver.java:121)
                at org.apache.cxf.transport.http.AbstractHTTPDestination.invoke(AbstractHTTPDestination.java:265)
                at org.apache.openejb.server.cxf.CxfWsContainer.onMessage(CxfWsContainer.java:85)
                at org.apache.openejb.server.webservices.WsServlet.service(WsServlet.java:72)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:223)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)
                at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:185)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)
                at org.apache.openejb.server.httpd.EEFilter.doFilter(EEFilter.java:67)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:185)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)
                at io.smallrye.metrics.jaxrs.JaxRsMetricsServletFilter.doFilter(JaxRsMetricsServletFilter.java:53)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:185)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)
                at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)
                at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)
                at org.apache.tomee.catalina.OpenEJBValve.invoke(OpenEJBValve.java:45)
                at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)
                at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:119)
                at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
                at org.apache.tomee.catalina.OpenEJBSecurityListener$RequestCapturer.invoke(OpenEJBSecurityListener.java:97)
                at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:690)
                at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)
                at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:356)
                at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)
                at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
                at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:870)
                at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1762)
                at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
                at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
                at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
                at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
                at java.base/java.lang.Thread.run(Thread.java:833)
        Caused by: jakarta.xml.soap.SOAPException: Unable to create message factory for SOAP: Error while searching for service [jakarta.xml.soap.MessageFactory]
                at jakarta.xml.soap.MessageFactory.newInstance(MessageFactory.java:90)
                at org.apache.cxf.binding.soap.saaj.SAAJFactoryResolver.createMessageFactory(SAAJFactoryResolver.java:56)
                at org.apache.cxf.binding.soap.saaj.SAAJInInterceptor$SAAJPreInInterceptor.getFactory(SAAJInInterceptor.java:151)
                at org.apache.cxf.binding.soap.saaj.SAAJInInterceptor$SAAJPreInInterceptor.handleMessage(SAAJInInterceptor.java:133)
                ... 36 more

less localhost_access_log.2023-07-19.txt 
192.168.181.1 - - [19/Jul/2023:10:28:33 +0200] "GET /example/webservices/WSSession?wsdl HTTP/1.1" 200 5954
0:0:0:0:0:0:0:1 - - [19/Jul/2023:10:30:09 +0200] "POST /example/webservices/WSSession HTTP/1.1" 500 225


