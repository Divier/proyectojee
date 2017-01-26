package co.com.datatools.c2.ws.comparendo.servicebroker;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ws.RespuestaSolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.dto.ws.SolicitudNumeroComparendoWSDTO;

/**
 * Interfaz que proporciona los servicios para procesar la peticion del WS de solicitar OCN
 * 
 * @author diego.fonseca
 */
@Local
public interface ILSolicitarNumeroComparendoWS {

    /**
     * Recibe la información necesaria para realizar la solicitud de un OCN
     * 
     * @param solicitudNumeroComparendoWSDTO
     * @return
     */
    public RespuestaSolicitudNumeroComparendoWSDTO solicitarNumeroComparendo(
            SolicitudNumeroComparendoWSDTO solicitudNumeroComparendoWSDTO);

}
