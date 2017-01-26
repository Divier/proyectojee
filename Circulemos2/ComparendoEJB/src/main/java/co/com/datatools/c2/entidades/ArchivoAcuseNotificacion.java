package co.com.datatools.c2.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the archivo_acuse_notificacion database table.
 * 
 */
@Entity
@Table(name = "archivo_acuse_notificacion")
@NamedQuery(name = "ArchivoAcuseNotificacion.findAll", query = "SELECT a FROM ArchivoAcuseNotificacion a")
public class ArchivoAcuseNotificacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo_acuse_correo")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cargue")
    private Date fechaCargue;

    // bi-directional many-to-one association to ComparendoArchivoAcuse
    @OneToMany(mappedBy = "archivoAcuseNotificacion", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    private List<ComparendoArchivoAcuse> comparendoArchivoAcuses;

    public ArchivoAcuseNotificacion() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCargue() {
        return this.fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public List<ComparendoArchivoAcuse> getComparendoArchivoAcuses() {
        if (this.comparendoArchivoAcuses == null)
            this.comparendoArchivoAcuses = new ArrayList<>();
        return this.comparendoArchivoAcuses;
    }

    public void setComparendoArchivoAcuses(List<ComparendoArchivoAcuse> comparendoArchivoAcuses) {
        this.comparendoArchivoAcuses = comparendoArchivoAcuses;
    }

}