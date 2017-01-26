package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class SolicitudOficioBienDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date fechaSolicitud;
    private CoactivoDTO coactivoDTO;
    private Boolean generaOficio;
    private List<SolicitudBienEntidadDTO> solicitudBienEntidadDTOs;

    public SolicitudOficioBienDTO() {
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoactivoDTO getCoactivoDTO() {
        return coactivoDTO;
    }

    public void setCoactivoDTO(CoactivoDTO coactivoDTO) {
        this.coactivoDTO = coactivoDTO;
    }

    public Boolean getGeneraOficio() {
        return generaOficio;
    }

    public void setGeneraOficio(Boolean generaOficio) {
        this.generaOficio = generaOficio;
    }

    public List<SolicitudBienEntidadDTO> getSolicitudBienEntidadDTOs() {
        return solicitudBienEntidadDTOs;
    }

    public void setSolicitudBienEntidadDTOs(List<SolicitudBienEntidadDTO> solicitudBienEntidadDTOs) {
        this.solicitudBienEntidadDTOs = solicitudBienEntidadDTOs;
    }

}