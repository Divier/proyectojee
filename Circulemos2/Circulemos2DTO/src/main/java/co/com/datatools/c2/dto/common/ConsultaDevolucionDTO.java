package co.com.datatools.c2.dto.common;

import java.io.Serializable;

/**
 * DTO que se encarga de encapsular los filtros de consulta de las solicitudes de devolucion de saldo a favor de un ciudadano
 * 
 * @author luis.forero
 * @version iteracion 3 Wed Sep 24 16:26:00 2014
 */
public class ConsultaDevolucionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int codigoOrganismo;
    private Integer codigoTipoSolicitud;
    private Integer consecutivo;
    private int idTipoIdentificacion;
    private String numeroIdentificacion;

    public ConsultaDevolucionDTO() {
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getCodigoTipoSolicitud() {
        return codigoTipoSolicitud;
    }

    public void setCodigoTipoSolicitud(Integer codigoTipoSolicitud) {
        this.codigoTipoSolicitud = codigoTipoSolicitud;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

}
