package co.com.datatools.c2.ws.comparendo.solicitudocn;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.interceptor.InInterceptors;
import org.jboss.ws.api.annotation.EndpointConfig;

import co.com.datatools.c2.dto.ws.RespuestaSolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.dto.ws.SolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.ws.comparendo.servicebroker.ILSolicitarNumeroComparendoWS;
import co.com.datatools.c2.ws.interceptor.SubjectCreatingPolicyIPInterceptor;
import co.com.datatools.c2.ws.interceptor.ValidationLoggerInterceptor;
import co.com.datatools.c2.ws.interceptor.XSSValidationInterceptor;

@WebService(
        targetNamespace = "http://solicitudocn.comparendo.ws.c2.datatools.com.co/",
        endpointInterface = "co.com.datatools.c2.ws.comparendo.solicitudocn.ISolicitarNumeroComparendo",
        portName = "SolicitarNumeroComparendoPort",
        serviceName = "SolicitarNumeroComparendoService",
        wsdlLocation = "wsdl/solicitarnumerocomparendo.wsdl")
@SchemaValidation
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config-jaas.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(classes = { SubjectCreatingPolicyIPInterceptor.class, XSSValidationInterceptor.class,
        ValidationLoggerInterceptor.class })
public class SolicitarNumeroComparendo implements ISolicitarNumeroComparendo {

    @EJB
    private ILSolicitarNumeroComparendoWS ilSolicitarNumeroComparendo;

    public RespuestaSolicitudNumeroComparendoWSDTO solicitarNumeroComparendo(
            SolicitudNumeroComparendoWSDTO solicitudNumeroComparendo) {
        RespuestaSolicitudNumeroComparendoWSDTO respuesta = null;

        respuesta = ilSolicitarNumeroComparendo.solicitarNumeroComparendo(solicitudNumeroComparendo);
        return respuesta;
    }

}