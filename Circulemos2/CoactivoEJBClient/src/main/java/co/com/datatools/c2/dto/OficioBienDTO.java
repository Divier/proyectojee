package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class OficioBienDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String numeroOficio;
    private Date fechaGeneracion;
    private SolicitudBienEntidadDTO solicitudBienEntidadDTO;
    private List<CoactivoOficioBienDTO> coactivoOficioBiens;

    public OficioBienDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public List<CoactivoOficioBienDTO> getCoactivoOficioBiens() {
        return coactivoOficioBiens;
    }

    public void setCoactivoOficioBiens(List<CoactivoOficioBienDTO> coactivoOficioBiens) {
        this.coactivoOficioBiens = coactivoOficioBiens;
    }

    public SolicitudBienEntidadDTO getSolicitudBienEntidadDTO() {
        return solicitudBienEntidadDTO;
    }

    public void setSolicitudBienEntidadDTO(SolicitudBienEntidadDTO solicitudBienEntidadDTO) {
        this.solicitudBienEntidadDTO = solicitudBienEntidadDTO;
    }

}