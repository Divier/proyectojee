package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Contiene la respuesta a la invocacion de la puublicacion del servicio ILGestionComparendoWS.
 * 
 * @author javier.fajardo (2016-04-22)
 * 
 */
public class RespuestaGestionCobroWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private String detalle;
    private Integer codigo;

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
