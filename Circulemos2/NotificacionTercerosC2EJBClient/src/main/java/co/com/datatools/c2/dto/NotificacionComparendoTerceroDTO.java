package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.enumeracion.EnumOrigenNotificacionTercero;

public class NotificacionComparendoTerceroDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int codigoOrganismo;
    private Long cicomparendo;
    private EnumOrigenNotificacionTercero origenNotificacionTercero;

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public EnumOrigenNotificacionTercero getOrigenNotificacionTercero() {
        return origenNotificacionTercero;
    }

    public void setOrigenNotificacionTercero(EnumOrigenNotificacionTercero origenNotificacionTercero) {
        this.origenNotificacionTercero = origenNotificacionTercero;
    }

}
