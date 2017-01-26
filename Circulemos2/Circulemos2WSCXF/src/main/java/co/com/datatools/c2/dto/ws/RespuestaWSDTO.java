package co.com.datatools.c2.dto.ws;

import java.util.List;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Contiene la respuesta a la invocacion de un servicio Web
 * 
 * @author julio.pinzon (2016-04-21)
 * 
 */
public class RespuestaWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    /**
     * Codigo general de la respuesta del servicio.
     */
    private String codigoGeneral;
    /**
     * Contiene el listado de detalles de la respuesta.
     */
    private List<DetalleRespuestaWSDTO> detalle;

    public String getCodigoGeneral() {
        return codigoGeneral;
    }

    public void setCodigoGeneral(String codigoGeneral) {
        this.codigoGeneral = codigoGeneral;
    }

    public List<DetalleRespuestaWSDTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleRespuestaWSDTO> detalle) {
        this.detalle = detalle;
    }
}
