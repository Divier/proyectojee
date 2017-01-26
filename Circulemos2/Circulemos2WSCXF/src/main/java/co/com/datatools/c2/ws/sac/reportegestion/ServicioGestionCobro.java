package co.com.datatools.c2.ws.sac.reportegestion;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.interceptor.InInterceptors;
import org.jboss.ws.api.annotation.EndpointConfig;

import co.com.datatools.c2.dto.ws.GestionCobroWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaGestionCobroWSDTO;
import co.com.datatools.c2.ws.interceptor.SubjectCreatingPolicyIPInterceptor;
import co.com.datatools.c2.ws.interceptor.ValidationLoggerInterceptor;
import co.com.datatools.c2.ws.interceptor.XSSValidationInterceptor;
import co.com.datatools.c2.ws.sac.servicebroker.ILGestionCobroWS;

/**
 * EJB encargado de procesar la peticion del WS de gestion comparendo comparendo
 * 
 * @author javier.fajardo(2016-04-22)
 * 
 */
@WebService(
        targetNamespace = "http://reportegestion.sac.ws.c2.datatools.com.co/",
        endpointInterface = "co.com.datatools.c2.ws.sac.reportegestion.IServicioGestionCobro",
        portName = "ServicioGestionCobroPort",
        serviceName = "ServicioGestionCobroService",
        wsdlLocation = "wsdl/serviciogestioncobro.wsdl")
@SchemaValidation
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config-jaas.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(classes = { SubjectCreatingPolicyIPInterceptor.class, XSSValidationInterceptor.class,
        ValidationLoggerInterceptor.class })
public class ServicioGestionCobro implements IServicioGestionCobro {

	@EJB
	private ILGestionCobroWS ilGestionCobroWS;
	
    public RespuestaGestionCobroWSDTO procesarGestionCobro(GestionCobroWSDTO gestionCobroWSDTO) {
        RespuestaGestionCobroWSDTO respuestaGestionCobroWSDTO = new RespuestaGestionCobroWSDTO();
        
        ilGestionCobroWS.procesarGestionCobro(gestionCobroWSDTO); 
        
        return respuestaGestionCobroWSDTO;
    }
}
