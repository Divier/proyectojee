package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 10
 * 
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Resolucion.findAll", query = "SELECT r FROM Resolucion r"),
        @NamedQuery(
                name = "Resolucion.findByIdResolucion",
                query = "SELECT r FROM Resolucion r LEFT JOIN FETCH r.tipoResolucion LEFT JOIN FETCH r.estadoResolucion LEFT JOIN FETCH r.resolucion WHERE r.id = :pIdRes") })
public class Resolucion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta la resolucion por su id retornando sus relaciones de primer nivel oviando las listas.<br>
     * 
     * SELECT r FROM Resolucion r LEFT JOIN FETCH r.tipoResolucion LEFT JOIN FETCH r.estadoResolucion LEFT JOIN FETCH r.resolucion WHERE id = :pIdRes
     * 
     * @author luis.forero(2016-02-02)
     */
    public static final String SQ_FIND_BY_ID_FETCH = "Resolucion.findByIdResolucion";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private Long id;

    @Column(name = "ano_resolucion")
    private Integer anoResolucion;

    @Column(name = "codigo_organismo")
    private Integer codigoOrganismo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_resolucion")
    private Date fechaResolucion;

    @Column(name = "id_documento")
    private Long idDocumento;

    @Column(name = "numero_resolucion")
    private String numeroResolucion;

    @Column(name = "resolucion_exitosa")
    private Boolean resolucionExitosa;

    // bi-directional many-to-one association to TipoResolucion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_resolucion")
    private TipoResolucion tipoResolucion;

    // bi-directional many-to-one association to EstadoResolucion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_resolucion")
    private EstadoResolucion estadoResolucion;

    // bi-directional many-to-one association to Resolucion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resolucion_anterior")
    private Resolucion resolucion;

    // bi-directional many-to-one association to Resolucion
    @OneToMany(mappedBy = "resolucion")
    private List<Resolucion> resoluciones;

    public Resolucion() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnoResolucion() {
        return this.anoResolucion;
    }

    public void setAnoResolucion(Integer anoResolucion) {
        this.anoResolucion = anoResolucion;
    }

    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaResolucion() {
        return this.fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Long getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNumeroResolucion() {
        return this.numeroResolucion;
    }

    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    public Boolean getResolucionExitosa() {
        return this.resolucionExitosa;
    }

    public void setResolucionExitosa(Boolean resolucionExitosa) {
        this.resolucionExitosa = resolucionExitosa;
    }

    public TipoResolucion getTipoResolucion() {
        return this.tipoResolucion;
    }

    public void setTipoResolucion(TipoResolucion tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }

    public EstadoResolucion getEstadoResolucion() {
        return this.estadoResolucion;
    }

    public void setEstadoResolucion(EstadoResolucion estadoResolucion) {
        this.estadoResolucion = estadoResolucion;
    }

    public Resolucion getResolucion() {
        return this.resolucion;
    }

    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }

    public List<Resolucion> getResoluciones() {
        return this.resoluciones;
    }

    public void setResoluciones(List<Resolucion> resoluciones) {
        this.resoluciones = resoluciones;
    }

}