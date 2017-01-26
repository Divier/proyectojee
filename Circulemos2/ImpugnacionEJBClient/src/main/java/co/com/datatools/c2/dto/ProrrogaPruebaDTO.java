package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 17:52:39 COT 2016
 */
public class ProrrogaPruebaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer diasProrroga;
    private Integer numeroProrroga;
    private Date fechaProrroga;
    private SolicitudPruebasBackDTO solicitudPruebasBack;

    // --- Constructor
    public ProrrogaPruebaDTO() {
    }

    public ProrrogaPruebaDTO(Long id) {
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiasProrroga() {
        return diasProrroga;
    }

    public void setDiasProrroga(Integer diasProrroga) {
        this.diasProrroga = diasProrroga;
    }

    public Integer getNumeroProrroga() {
        return numeroProrroga;
    }

    public void setNumeroProrroga(Integer numeroProrroga) {
        this.numeroProrroga = numeroProrroga;
    }

    public Date getFechaProrroga() {
        return fechaProrroga;
    }

    public void setFechaProrroga(Date fechaProrroga) {
        this.fechaProrroga = fechaProrroga;
    }

    public SolicitudPruebasBackDTO getSolicitudPruebasBack() {
        return solicitudPruebasBack;
    }

    public void setSolicitudPruebasBack(SolicitudPruebasBackDTO solicitudPruebasBack) {
        this.solicitudPruebasBack = solicitudPruebasBack;
    }
}
