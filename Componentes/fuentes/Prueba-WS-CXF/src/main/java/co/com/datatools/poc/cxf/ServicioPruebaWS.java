package co.com.datatools.poc.cxf;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@org.apache.cxf.interceptor.OutInterceptors(
        interceptors = { "co.com.datatools.poc.cxf.util.interceptor.LoggerInterceptorOut" })
@org.apache.cxf.interceptor.OutFaultInterceptors(
        interceptors = { "co.com.datatools.poc.cxf.util.interceptor.LoggerInterceptorOut" })
@org.apache.cxf.interceptor.InFaultInterceptors(
        interceptors = { "co.com.datatools.poc.cxf.util.interceptor.LoggerInterceptor" })
@org.apache.cxf.interceptor.InInterceptors(
        interceptors = { "co.com.datatools.poc.cxf.util.interceptor.LoggerInterceptor" })
@WebService(
        targetNamespace = "http://suma.ws.poc.c2.datatools.com.co/",
        endpointInterface = "co.com.datatools.poc.cxf.IServicioPruebaWS",
        portName = "ServicioPruebaWSPort",
        serviceName = "ServicioPruebaWSService",
        wsdlLocation = "wsdl/serviciopruebaws.wsdl")
@HandlerChain(file = "util/interceptor/handler.xml")
public class ServicioPruebaWS implements IServicioPruebaWS {

    public int suma(Integer x, Integer y) {
        int result = x + y;
        return result;
    }

}
