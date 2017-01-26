package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author giovanni.velandia Se encarga de trasportar los datos de una solicitud de oficio de un coactivo
 */
public class SolicitudOficioCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idCoactivoOficioBien;
    private String numeroProceso;
    private Date fechaProceso;
    private String tipoDocIdentificacion;
    private String numeroDocIdentificacion;
    private String nombres;
    private String entidad;
    private String numeroOficio;
    private Long idCoactivo;

    // --- Constructor
    public SolicitudOficioCoactivoDTO() {
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

    public String getTipoDocIdentificacion() {
        return tipoDocIdentificacion;
    }

    public void setTipoDocIdentificacion(String tipoDocIdentificacion) {
        this.tipoDocIdentificacion = tipoDocIdentificacion;
    }

    public String getNumeroDocIdentificacion() {
        return numeroDocIdentificacion;
    }

    public void setNumeroDocIdentificacion(String numeroDocIdentificacion) {
        this.numeroDocIdentificacion = numeroDocIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public Long getIdCoactivoOficioBien() {
        return idCoactivoOficioBien;
    }

    public void setIdCoactivoOficioBien(Long idCoactivoOficioBien) {
        this.idCoactivoOficioBien = idCoactivoOficioBien;
    }

    public Long getIdCoactivo() {
        return idCoactivo;
    }

    public void setIdCoactivo(Long idCoactivo) {
        this.idCoactivo = idCoactivo;
    }

}
