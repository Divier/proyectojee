package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;

/**
 * Contiene la informacion basica de un comparendo para ser notificado a SIMIT.
 * 
 * @author luis.forero(2016-03-30)
 * 
 */
public class NotificacionComparendoSIMITDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identificador comparendo
     */
    private Long cicomparendo;
    /**
     * Codigo del organismo al cual pertenece el comparendo
     */
    private int codigoOrganismo;
    /**
     * Accion que se ejecuta en la aplicacion
     */
    private EnumAccionComparendo origenSIMIT;

    public NotificacionComparendoSIMITDTO() {
    }

    public NotificacionComparendoSIMITDTO(Long cicomparendo, int codigoOrganismo, EnumAccionComparendo origenSIMIT) {
        super();
        this.cicomparendo = cicomparendo;
        this.codigoOrganismo = codigoOrganismo;
        this.origenSIMIT = origenSIMIT;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public EnumAccionComparendo getOrigenSIMIT() {
        return origenSIMIT;
    }

    public void setOrigenSIMIT(EnumAccionComparendo origenSIMIT) {
        this.origenSIMIT = origenSIMIT;
    }

}
