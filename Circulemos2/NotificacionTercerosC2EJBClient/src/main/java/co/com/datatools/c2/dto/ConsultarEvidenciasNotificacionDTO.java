package co.com.datatools.c2.dto;

import java.io.Serializable;

public class ConsultarEvidenciasNotificacionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idDetalleNotificacion;
    private Long codigoEvidencia;
    private Integer tipoEvidencia;
    private String idEvidenciaExterno;

    public Long getIdDetalleNotificacion() {
        return idDetalleNotificacion;
    }

    public void setIdDetalleNotificacion(Long idDetalleNotificacion) {
        this.idDetalleNotificacion = idDetalleNotificacion;
    }

    public Long getCodigoEvidencia() {
        return codigoEvidencia;
    }

    public void setCodigoEvidencia(Long codigoEvidencia) {
        this.codigoEvidencia = codigoEvidencia;
    }

    public Integer getTipoEvidencia() {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(Integer tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }

    public String getIdEvidenciaExterno() {
        return idEvidenciaExterno;
    }

    public void setIdEvidenciaExterno(String idEvidenciaExterno) {
        this.idEvidenciaExterno = idEvidenciaExterno;
    }
}