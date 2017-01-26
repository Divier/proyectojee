package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:40:45 COT 2016
 */
public class CargueCourierDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String consecutivo;
    private Date fechaCargue;
    private Long idDocumentoCargue;
    private UsuarioPersonaDTO usuarioCargue;
    private List<DetalleCargueCourierDTO> detalleCargueCouriers;

    // --- Constructor
    public CargueCourierDTO() {
    }

    public CargueCourierDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFechaCargue() {
        return this.fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public Long getIdDocumentoCargue() {
        return this.idDocumentoCargue;
    }

    public void setIdDocumentoCargue(Long idDocumentoCargue) {
        this.idDocumentoCargue = idDocumentoCargue;
    }

    public UsuarioPersonaDTO getUsuarioCargue() {
        return this.usuarioCargue;
    }

    public void setUsuarioCargue(UsuarioPersonaDTO usuarioCargue) {
        this.usuarioCargue = usuarioCargue;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleCargueCourierDTO> getDetalleCargueCouriers() {
        if (this.detalleCargueCouriers == null) {
            this.detalleCargueCouriers = new java.util.ArrayList<>(1);
        }
        return this.detalleCargueCouriers;
    }

    public void setDetalleCargueCouriers(List<DetalleCargueCourierDTO> detalleCargueCouriers) {
        this.detalleCargueCouriers = detalleCargueCouriers;
    }

}
