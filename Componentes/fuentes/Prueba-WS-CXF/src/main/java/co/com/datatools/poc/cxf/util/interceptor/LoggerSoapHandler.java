package co.com.datatools.poc.cxf.util.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.jboss.logging.Logger;
import org.w3c.dom.Node;

/**
 * @author luis.forero
 */
public class LoggerSoapHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger logger = Logger.getLogger(LoggerSoapHandler.class.getName());

    @Override
    public void close(MessageContext arg0) {
    }

    @Override
    public boolean handleFault(SOAPMessageContext sMessageContext) {
        final SOAPMessage message = sMessageContext.getMessage();

        try {
            SOAPBody soapBody = message.getSOAPBody();
            Node firstChild = soapBody.getFirstChild();
        } catch (SOAPException e) {
            logger.error(e.getMessage());
            return false;
        }
        HttpServletRequest request = (HttpServletRequest) sMessageContext.get(SOAPMessageContext.SERVLET_REQUEST);
        String ip = request.getRemoteAddr();

        generateSOAPErrMessage(message, ip + "Reason Internationalized");

        return true;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
            SOAPBody soapBody = envelope.getBody();
            if (soapBody == null) {
                soapBody = envelope.addBody();
            }
            SOAPFault soapFault = soapBody.getFault();
            Iterator faultReasonLocales = soapFault.getFaultReasonLocales();
      

            soapFault.setFaultString(reason);

            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        logger.info(LoggerSoapHandler.class.getName() + "::handleMessage(SOAPMessageContext messageContext)");

        final StringBuffer buffer = new StringBuffer(0);
        final SOAPMessage message = messageContext.getMessage();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            message.writeTo(baos);

            final HttpServletRequest request = (HttpServletRequest) messageContext
                    .get(SOAPMessageContext.SERVLET_REQUEST);
            final String ip = request.getRemoteAddr();
            buffer.append("\nIP Input Request: " + ip + "\n");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.sss");
            buffer.append("Fecha Hora: " + format.format(new Date()) + "\n");
            String operacion = ((QName) messageContext.get(SOAPMessageContext.WSDL_OPERATION)).toString();
            buffer.append("Nombre metodo: " + operacion + "\n");

            buffer.append("Mensaje: " + message.toString() + "\n");

            buffer.append(baos.toString() + "\n");

            logger.info(buffer);

            return true;
        } catch (SOAPException e) {
            logger.error(e.getMessage());
            return false;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

}
