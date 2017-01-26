package co.com.datatools.c2.ws.comparendo.solicitudocn;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import co.com.datatools.c2.dto.ws.RespuestaSolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.dto.ws.SolicitudNumeroComparendoWSDTO;

/**
 * Interface que expone el servicio para solicitar un OCN
 * 
 * @author diego.fonseca
 * 
 */
@WebService(
        name = "ISolicitarNumeroComparendo",
        targetNamespace = "http://solicitudocn.comparendo.ws.c2.datatools.com.co/")
public interface ISolicitarNumeroComparendo {

    /**
     * servicio que valida los campos recibidos segun obligatoriedad o catalogos de sistema
     * 
     * @param solicitudNumeroComparendo
     * @return un numero de formulario, datos del vehículo si existe y del propietario
     */
    @WebMethod(operationName = "solicitarNumeroComparendo", action = "urn:SolicitarNumeroComparendo")
    public RespuestaSolicitudNumeroComparendoWSDTO solicitarNumeroComparendo(@WebParam(
            name = "solicitudNumeroComparendo") SolicitudNumeroComparendoWSDTO solicitudNumeroComparendo);

}