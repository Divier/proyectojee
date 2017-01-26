package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_bloqueo database table.
 * 
 */
@Entity
@Table(name = "detalle_bloqueo")
@NamedQuery(name = "DetalleBloqueo.findAll", query = "SELECT d FROM DetalleBloqueo d")
public class DetalleBloqueo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_bloqueo")
    private String id;

    // bi-directional many-to-one association to BloqueoComparendo
    @ManyToOne
    @JoinColumn(name = "id_bloqueo_comparendo")
    private BloqueoComparendo bloqueoComparendo;

    // bi-directional many-to-one association to ErrorProcesamiento
    @ManyToOne
    @JoinColumn(name = "id_error_procesamiento")
    private ErrorProcesamiento errorProcesamiento;

    // bi-directional many-to-one association to CampoEntidad
    @ManyToOne
    @JoinColumn(name = "id_campo_entidad")
    private CampoEntidad campoEntidad;

    public DetalleBloqueo() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BloqueoComparendo getBloqueoComparendo() {
        return this.bloqueoComparendo;
    }

    public void setBloqueoComparendo(BloqueoComparendo bloqueoComparendo) {
        this.bloqueoComparendo = bloqueoComparendo;
    }

    public ErrorProcesamiento getErrorProcesamiento() {
        return this.errorProcesamiento;
    }

    public void setErrorProcesamiento(ErrorProcesamiento errorProcesamiento) {
        this.errorProcesamiento = errorProcesamiento;
    }

    public CampoEntidad getCampoEntidad() {
        return this.campoEntidad;
    }

    public void setCampoEntidad(CampoEntidad campoEntidad) {
        this.campoEntidad = campoEntidad;
    }

}