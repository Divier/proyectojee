package co.com.datatools.seguridad.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Recursos parametrizados en la aplicacion
 * 
 * @author Felipe Martinez <br>
 *         rodrigo.cruz (mod: 21/07/2015) - Auditoria
 */
@Entity
@Table(name = "recurso")
// @Audited
@NamedQueries({
        @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r"),
        @NamedQuery(
                name = "Recurso.findByPadre",
                query = "SELECT r FROM Recurso r WHERE r.recursoPadre.idRecurso= :idPadre"),
        @NamedQuery(
                name = "Recurso.findByNombre",
                query = "SELECT r FROM Recurso r WHERE r.nombre = :nombre AND r.aplicacion.idAplicacion= :idApp") })
public class Recurso implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_HIJOS_RECURSO = "Recurso.findByPadre";
    public static final String SQ_RECURSO_BY_NOMBRE = "Recurso.findByNombre";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Integer idRecurso;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recurso", fetch = FetchType.LAZY)
    private List<RecursoOperacion> recursoOperacionList;

    // @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_aplicacion", referencedColumnName = "id_aplicacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aplicacion aplicacion;

    // @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_tipo_recurso", referencedColumnName = "id_tipo_recurso")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private TipoRecurso tipoRecurso;

    // @AuditMappedBy(mappedBy = "recursoPadre")
    @OneToMany(mappedBy = "recursoPadre", fetch = FetchType.LAZY)
    private List<Recurso> recursoHijoList;

    @JoinColumn(name = "id_recurso_padre", referencedColumnName = "id_recurso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Recurso recursoPadre;

    public Recurso() {
    }

    public Recurso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RecursoOperacion> getRecursoOperacionList() {
        return recursoOperacionList;
    }

    public void setRecursoOperacionList(List<RecursoOperacion> recursoOperacionList) {
        this.recursoOperacionList = recursoOperacionList;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public List<Recurso> getRecursoHijoList() {
        return recursoHijoList;
    }

    public void setRecursoHijoList(List<Recurso> recursoHijoList) {
        this.recursoHijoList = recursoHijoList;
    }

    public void setRecursoPadre(Recurso recursoPadre) {
        this.recursoPadre = recursoPadre;
    }

    public Recurso getRecursoPadre() {
        return recursoPadre;
    }

    @Override
    public String toString() {
        return "Recurso {idRecurso: " + getIdRecurso() + ", nombre: " + getNombre() + "}";
    }

}
