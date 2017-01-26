package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class CoactivoOficioBienDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long id;
    private OficioBienDTO oficioBienDTO;
    private CoactivoDTO coactivoDTO;
    private Date fechaRespuesta;
    private String numeroDocRespuesta;
    private BienDTO bienDTO;
    private Boolean tieneBien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OficioBienDTO getOficioBienDTO() {
        return oficioBienDTO;
    }

    public void setOficioBienDTO(OficioBienDTO oficioBienDTO) {
        this.oficioBienDTO = oficioBienDTO;
    }

    public CoactivoDTO getCoactivoDTO() {
        return coactivoDTO;
    }

    public void setCoactivoDTO(CoactivoDTO coactivoDTO) {
        this.coactivoDTO = coactivoDTO;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getNumeroDocRespuesta() {
        return numeroDocRespuesta;
    }

    public void setNumeroDocRespuesta(String numeroDocRespuesta) {
        this.numeroDocRespuesta = numeroDocRespuesta;
    }

    public BienDTO getBienDTO() {
        return bienDTO;
    }

    public void setBienDTO(BienDTO bienDTO) {
        this.bienDTO = bienDTO;
    }

    public Boolean getTieneBien() {
        return tieneBien;
    }

    public void setTieneBien(Boolean tieneBien) {
        this.tieneBien = tieneBien;
    }

}