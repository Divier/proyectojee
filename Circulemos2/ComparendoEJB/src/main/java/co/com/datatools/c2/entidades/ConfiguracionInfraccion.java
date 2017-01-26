package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "configuracion_infraccion")
@NamedQueries({
        @NamedQuery(
                name = "ConfiguracionInfraccion.countConfByInfraccion",
                query = "SELECT count(c) FROM ConfiguracionInfraccion c WHERE c.infraccion.id= :pIdInf"),
        @NamedQuery(
                name = "ConfiguracionInfraccion.findUltimaConfByInfraccion",
                query = "SELECT ci FROM ConfiguracionInfraccion AS ci WHERE ci.infraccion.id = :pIdInf ORDER BY ci.fechaInicio DESC") })
public class ConfiguracionInfraccion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * SELECT ci FROM ConfiguracionInfraccion AS ci WHERE ci.infraccion.id = :pIdInf ORDER BY ci.fechaInicio DESC
     */
    public static final String SQ_ULTIMA_CONFIGURACION_INFRACCION = "ConfiguracionInfraccion.findUltimaConfByInfraccion";

    /**
     * SELECT count(c) FROM ConfiguracionInfraccion c WHERE c.infraccion.id= :pIdInf
     */
    public static final String SQ_COUNT_CONF_BY_INFRACCION = "ConfiguracionInfraccion.countConfByInfraccion";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_configuracion_infraccion")
    private Long id;

    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Lob
    @Column(name = "articulo")
    private String articulo;

    @Basic(optional = false)
    @Column(name = "fecha_articulo")
    @Temporal(TemporalType.DATE)
    private Date fechaArticulo;

    @Basic(optional = false)
    @Column(name = "requiere_placa")
    private Boolean requierePlaca;

    @Basic(optional = false)
    @Column(name = "infractor_obligatorio")
    private Boolean infractorObligatorio;

    @Basic(optional = false)
    @Column(name = "dias_genera_cartera")
    private Short diasGeneraCartera;

    @Column(name = "genera_cartera")
    private Boolean generaCartera;

    @Basic(optional = false)
    @Column(name = "dia_habil_genera_cartera")
    private Boolean diaHabilGeneraCartera;

    @Basic(optional = false)
    @Column(name = "fecha_inicio_vigencia")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin_vigencia")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Column(name = "aplica_descuento")
    private Boolean aplicaDescuento;

    @Column(name = "aplica_embriaguez")
    private Boolean aplicaEmbriaguez;

    @Basic(optional = false)
    @Column(name = "genera_resolucion_automatica")
    private Boolean generaResolucionAutomatica;

    @Basic(optional = false)
    @Column(name = "condiciones_especiales")
    private Boolean condicionesEspeciales;

    @Basic(optional = false)
    @Column(name = "aplica_impugnacion")
    private Boolean aplicaImpugnacion;

    @JoinColumn(name = "id_infraccion", referencedColumnName = "id_infraccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Infraccion infraccion;

    @JoinColumn(name = "id_normatividad", referencedColumnName = "id_normatividad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Normatividad normatividad;

    @JoinColumn(name = "id_entidad_agente", referencedColumnName = "id_entidad_agente")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoEntidadAgenteTransito entidadAgenteTransito;

    @JoinColumn(name = "id_causal_infraccion", referencedColumnName = "id_causal_infraccion")
    @ManyToOne(fetch = FetchType.LAZY)
    private CausalInfraccion causalInfraccion;

    @JoinColumn(name = "id_ordenamiento_pais", referencedColumnName = "id_ordenamiento_pais")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrdenamientoPais ordenamientoPais;

    @JoinTable(
            name = "infraccion_tipo_respo_solid",
            joinColumns = @JoinColumn(name = "id_configuracion_infraccion"),
            inverseJoinColumns = @JoinColumn(name = "codigo_responsable"))
    @OneToMany
    private List<TipoResponsableSolidario> tipoResponsableSolidarioList;

    @JoinTable(
            name = "tipo_sancion_confi_infra",
            joinColumns = { @JoinColumn(
                    name = "id_configuracion_infraccion",
                    referencedColumnName = "id_configuracion_infraccion") },
            inverseJoinColumns = { @JoinColumn(name = "id_tipo_sancion", referencedColumnName = "id_tipo_sancion") })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TipoSancion> tipoSancionList;

    @JoinTable(
            name = "tipo_infractor_confi_infra",
            joinColumns = { @JoinColumn(
                    name = "id_configuracion_infraccion",
                    referencedColumnName = "id_configuracion_infraccion") },
            inverseJoinColumns = {
                    @JoinColumn(name = "codigo_tipo_infractor", referencedColumnName = "codigo_tipo_infractor") })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TipoInfractor> tipoInfractorList;

    public ConfiguracionInfraccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Date getFechaArticulo() {
        return fechaArticulo;
    }

    public void setFechaArticulo(Date fechaArticulo) {
        this.fechaArticulo = fechaArticulo;
    }

    public Boolean getRequierePlaca() {
        return requierePlaca;
    }

    public void setRequierePlaca(Boolean requierePlaca) {
        this.requierePlaca = requierePlaca;
    }

    public Boolean getInfractorObligatorio() {
        return infractorObligatorio;
    }

    public void setInfractorObligatorio(Boolean infractorObligatorio) {
        this.infractorObligatorio = infractorObligatorio;
    }

    public Short getDiasGeneraCartera() {
        return diasGeneraCartera;
    }

    public void setDiasGeneraCartera(Short diasGeneraCartera) {
        this.diasGeneraCartera = diasGeneraCartera;
    }

    public Boolean getDiaHabilGeneraCartera() {
        return diaHabilGeneraCartera;
    }

    public void setDiaHabilGeneraCartera(Boolean diaHabilGeneraCartera) {
        this.diaHabilGeneraCartera = diaHabilGeneraCartera;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Infraccion getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }

    public Normatividad getNormatividad() {
        return normatividad;
    }

    public void setNormatividad(Normatividad normatividad) {
        this.normatividad = normatividad;
    }

    public TipoEntidadAgenteTransito getEntidadAgenteTransito() {
        return entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(TipoEntidadAgenteTransito entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }

    public CausalInfraccion getCausalInfraccion() {
        return causalInfraccion;
    }

    public void setCausalInfraccion(CausalInfraccion causalInfraccion) {
        this.causalInfraccion = causalInfraccion;
    }

    public OrdenamientoPais getOrdenamientoPais() {
        return ordenamientoPais;
    }

    public void setOrdenamientoPais(OrdenamientoPais ordenamientoPais) {
        this.ordenamientoPais = ordenamientoPais;
    }

    public List<TipoResponsableSolidario> getTipoResponsableSolidarioList() {
        return tipoResponsableSolidarioList;
    }

    public void setTipoResponsableSolidarioList(List<TipoResponsableSolidario> tipoResponsableSolidarioList) {
        this.tipoResponsableSolidarioList = tipoResponsableSolidarioList;
    }

    public List<TipoSancion> getTipoSancionList() {
        return tipoSancionList;
    }

    public void setTipoSancionList(List<TipoSancion> tipoSancionList) {
        this.tipoSancionList = tipoSancionList;
    }

    public List<TipoInfractor> getTipoInfractorList() {
        return tipoInfractorList;
    }

    public void setTipoInfractorList(List<TipoInfractor> tipoInfractorList) {
        this.tipoInfractorList = tipoInfractorList;
    }

    public Boolean getGeneraCartera() {
        return generaCartera;
    }

    public void setGeneraCartera(Boolean generaCartera) {
        this.generaCartera = generaCartera;
    }

    public Boolean getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(Boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public Boolean getAplicaEmbriaguez() {
        return aplicaEmbriaguez;
    }

    public void setAplicaEmbriaguez(Boolean aplicaEmbriaguez) {
        this.aplicaEmbriaguez = aplicaEmbriaguez;
    }

    public Boolean getAplicaImpugnacion() {
        return this.aplicaImpugnacion;
    }

    public void setAplicaImpugnacion(Boolean aplicaImpugnacion) {
        this.aplicaImpugnacion = aplicaImpugnacion;
    }

    public Boolean getGeneraResolucionAutomatica() {
        return this.generaResolucionAutomatica;
    }

    public void setGeneraResolucionAutomatica(Boolean generaResolucionAutomatica) {
        this.generaResolucionAutomatica = generaResolucionAutomatica;
    }

    public Boolean getCondicionesEspeciales() {
        return this.condicionesEspeciales;
    }

    public void setCondicionesEspeciales(Boolean condicionesEspeciales) {
        this.condicionesEspeciales = condicionesEspeciales;
    }

}