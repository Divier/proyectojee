package co.com.datatools.c2.ws.sac.reportegestion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import co.com.datatools.c2.dto.ws.GestionCobroWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaGestionCobroWSDTO;

@WebService(name = "IServicioGestionCobro", targetNamespace = "http://reportegestion.sac.ws.c2.datatools.com.co/")
public interface IServicioGestionCobro {

    @WebMethod(operationName = "procesarGestionCobro", action = "urn:ProcesarGestionCobro")
    public RespuestaGestionCobroWSDTO procesarGestionCobro(@WebParam(name = "arg0") GestionCobroWSDTO gestionCobroWSDTO);

}