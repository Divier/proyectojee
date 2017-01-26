package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

public class RespuestaAccidentalidadDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idAccidentalidad;
    private String usuario;
    private Date fechaActulizacion;
    private boolean documentos;
    private String consecutivo;

    public Long getIdAccidentalidad() {
        return idAccidentalidad;
    }

    public void setIdAccidentalidad(Long idAccidentalidad) {
        this.idAccidentalidad = idAccidentalidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaActulizacion() {
        return fechaActulizacion;
    }

    public void setFechaActulizacion(Date fechaActulizacion) {
        this.fechaActulizacion = fechaActulizacion;
    }

    public boolean isDocumentos() {
        return documentos;
    }

    public void setDocumentos(boolean documentos) {
        this.documentos = documentos;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

}
