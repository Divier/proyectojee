package co.com.datatools.c2.ws.interceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.security.policy.SPConstants;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.util.WSSecurityUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import co.com.datatools.c2.ws.comparendo.utilidades.HelperProcesarMensaje;

/**
 * Interceptor que permite recibir los errores generados en la validacion del WSDL.
 * 
 * @author luis.forero(2015-11-20)
 */
public class ValidationLoggerInterceptor extends AbstractSoapInterceptor {

    public ValidationLoggerInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        HelperProcesarMensaje.obtenerInformacionLogMensaje(message, this.getPhase());
    }

    @Override
    public void handleFault(SoapMessage message) {
        super.handleFault(message);

        // LOGICA DE OBTENCION DE USUARIO
        String nombreUsuario = null;
        Header h = findSecurityHeader(message);
        if (h != null) {

            Element el = (Element) h.getObject();
            Element child = DOMUtils.getFirstElement(el);
            while (child != null) {
                if (SPConstants.USERNAME_TOKEN.equals(child.getLocalName())
                        && WSConstants.WSSE_NS.equals(child.getNamespaceURI())) {
                    nombreUsuario = WSSecurityUtil.getDirectChildElement(child, WSConstants.USERNAME_LN,
                            WSConstants.WSSE_NS).getTextContent();
                    break;
                }
                child = DOMUtils.getNextElement(child);
            }
        }

        HelperProcesarMensaje.escribirLogServidor(nombreUsuario, message);
    }

    /**
     * Permite encontrar el header de seguridad del mensaje entrante
     * 
     * @param message
     *            mensaje soap con el contenido
     * @return header de seguridad del mensaje entrante
     * @author luis.forero(2015-11-25)
     */
    private Header findSecurityHeader(SoapMessage message) {
        for (Header h : message.getHeaders()) {
            QName n = h.getName();
            if (n.getLocalPart().equals("Security")
                    && (n.getNamespaceURI().equals(WSConstants.WSSE_NS) || n.getNamespaceURI().equals(
                            WSConstants.WSSE11_NS))) {
                return h;
            }
        }
        Document doc = DOMUtils.createDocument();
        Element el = doc.createElementNS(WSConstants.WSSE_NS, "wsse:Security");
        el.setAttributeNS(WSConstants.XMLNS_NS, "xmlns:wsse", WSConstants.WSSE_NS);
        SoapHeader sh = new SoapHeader(new QName(WSConstants.WSSE_NS, "Security"), el);
        sh.setMustUnderstand(true);
        message.getHeaders().add(sh);
        return sh;
    }

}
