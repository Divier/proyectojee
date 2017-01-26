package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

public class FiltroCoactivoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer IdtipoIdentificacion;
    private String numeroIdentificacion;
    private String numeroProcesoInicio;
    private String numeroProcesoFin;
    private Date fechaIncialProceso;
    private Date fechaFinProceso;
    private Boolean tieneBien;

    public Integer getIdtipoIdentificacion() {
        return IdtipoIdentificacion;
    }

    public void setIdtipoIdentificacion(Integer idtipoIdentificacion) {
        IdtipoIdentificacion = idtipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNumeroProcesoInicio() {
        return numeroProcesoInicio;
    }

    public void setNumeroProcesoInicio(String numeroProcesoInicio) {
        this.numeroProcesoInicio = numeroProcesoInicio;
    }

    public String getNumeroProcesoFin() {
        return numeroProcesoFin;
    }

    public void setNumeroProcesoFin(String numeroProcesoFin) {
        this.numeroProcesoFin = numeroProcesoFin;
    }

    public Date getFechaIncialProceso() {
        return fechaIncialProceso;
    }

    public void setFechaIncialProceso(Date fechaIncialProceso) {
        this.fechaIncialProceso = fechaIncialProceso;
    }

    public Date getFechaFinProceso() {
        return fechaFinProceso;
    }

    public void setFechaFinProceso(Date fechaFinProceso) {
        this.fechaFinProceso = fechaFinProceso;
    }

    public Boolean getTieneBien() {
        return tieneBien;
    }

    public void setTieneBien(Boolean tieneBien) {
        this.tieneBien = tieneBien;
    }

}
