package co.com.datatools.c2.ws.recaudo.recepcion;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.apache.cxf.annotations.EndpointProperties;
import org.apache.cxf.annotations.EndpointProperty;
import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.interceptor.InInterceptors;
import org.jboss.ws.api.annotation.EndpointConfig;

import co.com.datatools.c2.dto.ws.PagoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaWSDTO;
import co.com.datatools.c2.ws.interceptor.SubjectCreatingPolicyIPInterceptor;
import co.com.datatools.c2.ws.interceptor.ValidationLoggerInterceptor;
import co.com.datatools.c2.ws.interceptor.XSSValidationInterceptor;
import co.com.datatools.c2.ws.recaudo.servicebroker.ILReplicarPagoWS;

@WebService(
        targetNamespace = "http://recepcion.recaudo.ws.c2.datatools.com.co/",
        endpointInterface = "co.com.datatools.c2.ws.recaudo.recepcion.IServicioReplicarPago",
        portName = "ServicioReplicarPagoPort",
        serviceName = "ServicioReplicarPagoService",
        wsdlLocation = "wsdl/servicioreplicarpago.wsdl")
@SchemaValidation
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config-jaas.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(
        classes = { SubjectCreatingPolicyIPInterceptor.class, XSSValidationInterceptor.class,
                ValidationLoggerInterceptor.class })
@EndpointProperties({ @EndpointProperty(key = "set-jaxb-validation-event-handler", value = "false") })
public class ServicioReplicarPago implements IServicioReplicarPago {

    @EJB
    private ILReplicarPagoWS ilReplicarPago;

    @Override
    public RespuestaWSDTO replicarPago(PagoWSDTO pago) {
        if (pago != null) {
            return ilReplicarPago.replicarPagoWs(pago);
        } else {
            return null;
        }
    }
}
