package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

public class RespuestaCoactivoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long idCoactivo;
    private String CodigoTipoDocumento;
    private String numeroDocumento;
    private String nombreDeudor;
    private String numeroProceso;
    private Date fechaProceso;
    private String numeroOficio;
    private Boolean tieneBien;

    public Long getIdCoactivo() {
        return idCoactivo;
    }

    public void setIdCoactivo(Long idCoactivo) {
        this.idCoactivo = idCoactivo;
    }

    public String getCodigoTipoDocumento() {
        return CodigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
        CodigoTipoDocumento = codigoTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreDeudor() {
        return nombreDeudor;
    }

    public void setNombreDeudor(String nombreDeudor) {
        this.nombreDeudor = nombreDeudor;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public Boolean getTieneBien() {
        return tieneBien;
    }

    public void setTieneBien(Boolean tieneBien) {
        this.tieneBien = tieneBien;
    }

}
