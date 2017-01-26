package co.com.datatools.poc.cxf.util.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class LoggerInterceptorOut extends AbstractSoapInterceptor {

    public LoggerInterceptorOut() {
        super(Phase.SEND);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        System.out
                .println("**********************************************************************************************************");
        System.out
                .println("********************************************* Handle message *********************************************");
        System.out
                .println("**********************************************************************************************************");

    }

    @Override
    public void handleFault(SoapMessage message) {
        System.out
                .println("**********************************************************************************************************");
        System.out
                .println("********************************************* Handle message *********************************************");
        System.out
                .println("**********************************************************************************************************");
    }

}
