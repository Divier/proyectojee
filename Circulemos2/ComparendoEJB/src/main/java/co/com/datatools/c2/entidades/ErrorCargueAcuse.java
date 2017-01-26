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
 * The persistent class for the error_cargue_acuse database table.
 * 
 */
@Entity
@Table(name = "error_cargue_acuse")
@NamedQuery(name = "ErrorCargueAcuse.findAll", query = "SELECT e FROM ErrorCargueAcuse e")
public class ErrorCargueAcuse implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_error_cargue_acuse")
    private Long id;

    // bi-directional many-to-one association to CampoArchivoAcuse
    @ManyToOne
    @JoinColumn(name = "id_campo_archivo_acuse")
    private CampoArchivoAcuse campoArchivoAcuse;

    // bi-directional many-to-one association to MensajeErrorCargueAcuse
    @ManyToOne
    @JoinColumn(name = "id_mensaje_error_cargue_acuse")
    private MensajeErrorCargueAcuse mensajeErrorCargueAcuse;

    // bi-directional many-to-one association to ComparendoArchivoAcuse
    @ManyToOne
    @JoinColumn(name = "id_comparendo_archivo_acuse")
    private ComparendoArchivoAcuse comparendoArchivoAcuse;

    public ErrorCargueAcuse() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CampoArchivoAcuse getCampoArchivoAcuse() {
        return this.campoArchivoAcuse;
    }

    public void setCampoArchivoAcuse(CampoArchivoAcuse campoArchivoAcuse) {
        this.campoArchivoAcuse = campoArchivoAcuse;
    }

    public MensajeErrorCargueAcuse getMensajeErrorCargueAcuse() {
        return this.mensajeErrorCargueAcuse;
    }

    public void setMensajeErrorCargueAcuse(MensajeErrorCargueAcuse mensajeErrorCargueAcuse) {
        this.mensajeErrorCargueAcuse = mensajeErrorCargueAcuse;
    }

    public ComparendoArchivoAcuse getComparendoArchivoAcuse() {
        return this.comparendoArchivoAcuse;
    }

    public void setComparendoArchivoAcuse(ComparendoArchivoAcuse comparendoArchivoAcuse) {
        this.comparendoArchivoAcuse = comparendoArchivoAcuse;
    }

}