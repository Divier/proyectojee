package co.com.datatools.sogit.ws.ecuador.recepcion;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.interceptor.InInterceptors;
import org.jboss.ws.api.annotation.EndpointConfig;

import co.com.datatools.c2.dto.ws.ComparendoWSDTO;
import co.com.datatools.c2.dto.ws.PagoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaRecibirComparendoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaWSDTO;
import co.com.datatools.c2.ws.comparendo.servicebroker.ILRecibirComparendoWS;
import co.com.datatools.c2.ws.interceptor.SubjectCreatingPolicyIPInterceptor;
import co.com.datatools.c2.ws.interceptor.ValidationLoggerInterceptor;
import co.com.datatools.c2.ws.interceptor.XSSValidationInterceptor;
import co.com.datatools.c2.ws.recaudo.servicebroker.ILReplicarPagoWS;

@WebService(
        targetNamespace = "http://recepcion.ecuador.ws.sogit.datatools.com.co/",
        endpointInterface = "co.com.datatools.sogit.ws.ecuador.recepcion.IServiciosSogit",
        portName = "ServiciosSogitPort",
        serviceName = "ServiciosSogitService",
        wsdlLocation = "wsdl/serviciossogit.wsdl")
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config-jaas.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(
        classes = { SubjectCreatingPolicyIPInterceptor.class, ValidationLoggerInterceptor.class,
                XSSValidationInterceptor.class })
@SchemaValidation
public class ServiciosSogit implements IServiciosSogit {

    @EJB
    private ILRecibirComparendoWS ilRecibirComparendo;
    @EJB
    private ILReplicarPagoWS ilReplicarPago;

    @Override
    public RespuestaRecibirComparendoWSDTO recibirComparendo(ComparendoWSDTO comparendo) {
        if (comparendo != null) {
            return ilRecibirComparendo.procesarComparendoWs(comparendo);
        } else {
            return null;
        }
    }

    @Override
    public RespuestaWSDTO replicarPago(PagoWSDTO pago) {
        if (pago != null) {
            return ilReplicarPago.replicarPagoWs(pago);
        } else {
            return null;
        }
    }

}
