package co.com.datatools.c2.negocio.ejb.utilidades;

import co.com.datatools.c2.log.ILogueable;

/**
 * Log Generacion de Notificacion por Aviso de comparendos.
 * 
 * @author luis.forero (2016-02-12)
 * 
 */
public class LoggerGenerarNotificacionAviso implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismoTransito;
    private Integer numeroRegistrosObtenidos;
    private String mensaje;

    public LoggerGenerarNotificacionAviso() {
    }

    public LoggerGenerarNotificacionAviso(Integer codigoOrganismoTransito, Integer numeroRegistrosObtenidos,
            String mensaje) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
        this.numeroRegistrosObtenidos = numeroRegistrosObtenidos;
        this.mensaje = mensaje;
    }

    public Integer getNumeroRegistrosObtenidos() {
        return numeroRegistrosObtenidos;
    }

    public void setNumeroRegistrosObtenidos(Integer numeroRegistrosObtenidos) {
        this.numeroRegistrosObtenidos = numeroRegistrosObtenidos;
    }

    public Integer getCodigoOrganismoTransito() {
        return codigoOrganismoTransito;
    }

    public void setCodigoOrganismoTransito(Integer codigoOrganismoTransito) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
