package co.com.datatools.c2.ws.comparendo.recepcion;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.interceptor.InInterceptors;
import org.jboss.ws.api.annotation.EndpointConfig;

import co.com.datatools.c2.dto.ws.ComparendoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaRecibirComparendoWSDTO;
import co.com.datatools.c2.ws.comparendo.servicebroker.ILRecibirComparendoWS;
import co.com.datatools.c2.ws.interceptor.SubjectCreatingPolicyIPInterceptor;
import co.com.datatools.c2.ws.interceptor.ValidationLoggerInterceptor;
import co.com.datatools.c2.ws.interceptor.XSSValidationInterceptor;

@WebService(
        targetNamespace = "http://recepcion.comparendo.ws.c2.datatools.com.co/",
        endpointInterface = "co.com.datatools.c2.ws.comparendo.recepcion.IServicioRecibirComparendo",
        portName = "ServicioRecibirComparendoPort",
        serviceName = "ServicioRecibirComparendoService",
        wsdlLocation = "wsdl/serviciorecibircomparendo.wsdl")
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config-jaas.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(
        classes = { SubjectCreatingPolicyIPInterceptor.class, ValidationLoggerInterceptor.class,
                XSSValidationInterceptor.class })
@SchemaValidation
public class ServicioRecibirComparendo implements IServicioRecibirComparendo {

    @EJB
    private ILRecibirComparendoWS ilRecibirComparendo;

    @Override
    public RespuestaRecibirComparendoWSDTO recibirComparendo(ComparendoWSDTO comparendo) {
        if (comparendo != null) {
            return ilRecibirComparendo.procesarComparendoWs(comparendo);
        } else {
            return null;
        }
    }

}
