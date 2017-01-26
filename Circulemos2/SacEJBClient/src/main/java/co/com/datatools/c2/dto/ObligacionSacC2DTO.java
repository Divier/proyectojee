package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 31 15:36:21 COT 2016
 */
public class ObligacionSacC2DTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Long idCartera;
    private Long idObligacionSac;
    private Date fechaHoraReplicaSac;

    // --- Constructor
    public ObligacionSacC2DTO() {
    }

    public ObligacionSacC2DTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCartera() {
        return this.idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public Long getIdObligacionSac() {
        return this.idObligacionSac;
    }

    public void setIdObligacionSac(Long idObligacionSac) {
        this.idObligacionSac = idObligacionSac;
    }

    public Date getFechaHoraReplicaSac() {
        return this.fechaHoraReplicaSac;
    }

    public void setFechaHoraReplicaSac(Date fechaHoraReplicaSac) {
        this.fechaHoraReplicaSac = fechaHoraReplicaSac;
    }

}
