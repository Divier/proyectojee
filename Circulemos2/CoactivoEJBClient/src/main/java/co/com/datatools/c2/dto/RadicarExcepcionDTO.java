package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author giovanni.velandia
 */
public class RadicarExcepcionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long idRadicarExcepcion;
    private CoactivoDTO coactivoDTO;
    private String observaciones;
    private Date fechaExcepcion;
    private Date fechaFalloExcepcion;
    private String observacionesFallo;
    private Boolean falloAFavor;
    private List<ArchivoExcepcionDTO> archivoExcepcionDTOs;

    public RadicarExcepcionDTO() {
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CoactivoDTO getCoactivoDTO() {
        return coactivoDTO;
    }

    public void setCoactivoDTO(CoactivoDTO coactivoDTO) {
        this.coactivoDTO = coactivoDTO;
    }

    public Long getIdRadicarExcepcion() {
        return idRadicarExcepcion;
    }

    public void setIdRadicarExcepcion(Long idRadicarExcepcion) {
        this.idRadicarExcepcion = idRadicarExcepcion;
    }

    public Date getFechaExcepcion() {
        return fechaExcepcion;
    }

    public void setFechaExcepcion(Date fechaExcepcion) {
        this.fechaExcepcion = fechaExcepcion;
    }

    public Date getFechaFalloExcepcion() {
        return fechaFalloExcepcion;
    }

    public void setFechaFalloExcepcion(Date fechaFalloExcepcion) {
        this.fechaFalloExcepcion = fechaFalloExcepcion;
    }

    public String getObservacionesFallo() {
        return observacionesFallo;
    }

    public void setObservacionesFallo(String observacionesFallo) {
        this.observacionesFallo = observacionesFallo;
    }

    public Boolean getFalloAFavor() {
        return falloAFavor;
    }

    public void setFalloAFavor(Boolean falloAFavor) {
        this.falloAFavor = falloAFavor;
    }

    public List<ArchivoExcepcionDTO> getArchivoExcepcionDTOs() {
        return archivoExcepcionDTOs;
    }

    public void setArchivoExcepcionDTOs(List<ArchivoExcepcionDTO> archivoExcepcionDTOs) {
        this.archivoExcepcionDTOs = archivoExcepcionDTOs;
    }

}