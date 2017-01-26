package co.com.datatools.poc.cxf.util.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class LoggerInterceptor extends AbstractSoapInterceptor {

    public LoggerInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        @SuppressWarnings("unchecked")
        List<Object> myList = message.getContent(List.class);

        for (Object item : myList) {
            if (item instanceof Integer) {
                Integer req = (Integer) item;
                if (req == 30) {
                    throw new Fault(new Exception("Numero ingresado no puede ser 30"));
                }
            }
        }
        System.out
                .println("**********************************************************************************************************");
        System.out
                .println("********************************************* Handle message *********************************************");
        System.out
                .println("**********************************************************************************************************");

    }

    @Override
    public void handleFault(SoapMessage message) {
        Fault f = (Fault) message.getContent(Exception.class);
        // You should inspect its g.getCause() to maybe identify what went wrong
        // A CXF Fault also much ressembles a SOAPFault element
        f.setMessage("Your SOAP Fault message");
        System.out
                .println("**********************************************************************************************************");
        System.out
                .println("********************************************* Handle Fault *********************************************");
        System.out
                .println("**********************************************************************************************************");
    }
}
