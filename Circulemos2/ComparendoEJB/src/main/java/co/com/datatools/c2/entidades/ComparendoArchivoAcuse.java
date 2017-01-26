package co.com.datatools.c2.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the comparendo_archivo_acuse database table.
 * 
 */
@Entity
@Table(name = "comparendo_archivo_acuse")
@NamedQuery(name = "ComparendoArchivoAcuse.findAll", query = "SELECT c FROM ComparendoArchivoAcuse c")
public class ComparendoArchivoAcuse implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comparendo_archivo_acuse")
    private Long id;

    @Basic(optional = false)
    @Column(name = "numero_comparendo")
    private String numeroComparendo;

    @Basic(optional = false)
    @Column(name = "procesado")
    private Boolean procesado;

    // bi-directional many-to-one association to ArchivoAcuseNotificacion
    @ManyToOne
    @JoinColumn(name = "id_archivo_acuse_correo")
    private ArchivoAcuseNotificacion archivoAcuseNotificacion;

    // bi-directional many-to-one association to Comparendo
    @ManyToOne
    @JoinColumn(name = "cicomparendo")
    private Comparendo comparendo;

    // bi-directional many-to-one association to ErrorCargueAcuse
    @OneToMany(mappedBy = "comparendoArchivoAcuse", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    private List<ErrorCargueAcuse> errorCargueAcuses;

    public ComparendoArchivoAcuse() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroComparendo() {
        return this.numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Boolean getProcesado() {
        return this.procesado;
    }

    public void setProcesado(Boolean procesado) {
        this.procesado = procesado;
    }

    public ArchivoAcuseNotificacion getArchivoAcuseNotificacion() {
        return this.archivoAcuseNotificacion;
    }

    public void setArchivoAcuseNotificacion(ArchivoAcuseNotificacion archivoAcuseNotificacion) {
        this.archivoAcuseNotificacion = archivoAcuseNotificacion;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public List<ErrorCargueAcuse> getErrorCargueAcuses() {
        if (this.errorCargueAcuses == null)
            this.errorCargueAcuses = new ArrayList<>();
        return this.errorCargueAcuses;
    }

    public void setErrorCargueAcuses(List<ErrorCargueAcuse> errorCargueAcuses) {
        this.errorCargueAcuses = errorCargueAcuses;
    }

}