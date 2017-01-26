package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 31 15:36:21 COT 2016
 */
public class NovedadImpugnacionSacDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Long idProceso;
    private Long idObligacionSac;
    private Long idCartera;
    private Date fechaReplicaApertura;
    private Date fechaReplicaFallo;

    // --- Constructor
    public NovedadImpugnacionSacDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public Long getIdObligacionSac() {
        return idObligacionSac;
    }

    public void setIdObligacionSac(Long idObligacionSac) {
        this.idObligacionSac = idObligacionSac;
    }

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public Date getFechaReplicaApertura() {
        return fechaReplicaApertura;
    }

    public void setFechaReplicaApertura(Date fechaReplicaApertura) {
        this.fechaReplicaApertura = fechaReplicaApertura;
    }

    public Date getFechaReplicaFallo() {
        return fechaReplicaFallo;
    }

    public void setFechaReplicaFallo(Date fechaReplicaFallo) {
        this.fechaReplicaFallo = fechaReplicaFallo;
    }

}
