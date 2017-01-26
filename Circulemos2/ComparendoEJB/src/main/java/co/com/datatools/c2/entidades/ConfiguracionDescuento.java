package co.com.datatools.c2.entidades;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the configuracion_descuento database table.
 * 
 */
@Entity
@Table(name = "configuracion_descuento")
@NamedQuery(name = "ConfiguracionDescuento.findAll", query = "SELECT c FROM ConfiguracionDescuento c")
public class ConfiguracionDescuento implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento")
    private Integer id;

    @Column(name = "cantidad_dias_calendario")
    private Integer cantidadDiasCalendario;

    @Column(name = "cantidad_dias_habiles")
    private Integer cantidadDiasHabiles;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "dias_desde_fecha_imposicion")
    private Integer diasDesdeFechaImposicion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_vigencia_fin")
    private Date fechaVigenciaFin;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_vigencia_inicio")
    private Date fechaVigenciaInicio;

    @Basic(optional = false)
    @Column(name = "interes_comparendo")
    private Boolean interesComparendo;

    @Column(name = "limite")
    private String limite;

    @Basic(optional = false)
    @Column(name = "resolucion_especial")
    private Boolean resolucionEspecial;

    // bi-directional many-to-one association to OrganismoTransito
    @Basic(optional = false)
    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    // bi-directional many-to-one association to TipoDescuento
    @ManyToOne(fetch = FetchType.LAZY)
    @Basic(optional = false)
    @JoinColumn(name = "codigo_tipo_descuento")
    private TipoDescuento tipoDescuento;

    // uni-directional many-to-one association to TipoFechaOrigen
    @ManyToOne(fetch = FetchType.LAZY)
    @Basic(optional = false)
    @JoinColumn(name = "codigo_fecha_origen")
    private TipoFechaOrigen tipoFechaOrigen;

    // bi-directional many-to-one association to DetalleDescuento
    @OneToMany(mappedBy = "configuracionDescuento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleDescuento> detalleDescuentoList;

    // bi-directional many-to-many association to MedioImposicionComparendo
    @JoinTable(
            name = "descuento_medio_imposicion",
            joinColumns = @JoinColumn(name = "id_descuento"),
            inverseJoinColumns = @JoinColumn(name = "codigo_medio_imposicion"))
    @OneToMany
    private List<MedioImposicionComparendo> medioImposicionList;

    // bi-directional many-to-many association to TipoNotificacionComparendo
    @JoinTable(
            name = "descuento_notificacion",
            joinColumns = @JoinColumn(name = "id_descuento"),
            inverseJoinColumns = @JoinColumn(name = "id_tipo_notificacion"))
    @OneToMany
    private List<TipoNotificacionComparendo> tipoNotificacionList;

    public ConfiguracionDescuento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadDiasCalendario() {
        return this.cantidadDiasCalendario;
    }

    public void setCantidadDiasCalendario(Integer cantidadDiasCalendario) {
        this.cantidadDiasCalendario = cantidadDiasCalendario;
    }

    public Integer getCantidadDiasHabiles() {
        return this.cantidadDiasHabiles;
    }

    public void setCantidadDiasHabiles(Integer cantidadDiasHabiles) {
        this.cantidadDiasHabiles = cantidadDiasHabiles;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiasDesdeFechaImposicion() {
        return this.diasDesdeFechaImposicion;
    }

    public void setDiasDesdeFechaImposicion(Integer diasDesdeFechaImposicion) {
        this.diasDesdeFechaImposicion = diasDesdeFechaImposicion;
    }

    public Date getFechaVigenciaFin() {
        return this.fechaVigenciaFin;
    }

    public void setFechaVigenciaFin(Date fechaVigenciaFin) {
        this.fechaVigenciaFin = fechaVigenciaFin;
    }

    public Date getFechaVigenciaInicio() {
        return this.fechaVigenciaInicio;
    }

    public void setFechaVigenciaInicio(Date fechaVigenciaInicio) {
        this.fechaVigenciaInicio = fechaVigenciaInicio;
    }

    public Boolean getInteresComparendo() {
        return this.interesComparendo;
    }

    public void setInteresComparendo(Boolean interesComparendo) {
        this.interesComparendo = interesComparendo;
    }

    public String getLimite() {
        return this.limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public Boolean getResolucionEspecial() {
        return this.resolucionEspecial;
    }

    public void setResolucionEspecial(Boolean resolucionEspecial) {
        this.resolucionEspecial = resolucionEspecial;
    }

    public OrganismoTransito getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public TipoDescuento getTipoDescuento() {
        return this.tipoDescuento;
    }

    public void setTipoDescuento(TipoDescuento tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public TipoFechaOrigen getTipoFechaOrigen() {
        return this.tipoFechaOrigen;
    }

    public void setTipoFechaOrigen(TipoFechaOrigen tipoFechaOrigen) {
        this.tipoFechaOrigen = tipoFechaOrigen;
    }

    public List<DetalleDescuento> getDetalleDescuentoList() {
        return this.detalleDescuentoList;
    }

    public void setDetalleDescuentoList(List<DetalleDescuento> detalleDescuentos) {
        this.detalleDescuentoList = detalleDescuentos;
    }

    public DetalleDescuento addDetalleDescuento(DetalleDescuento detalleDescuento) {
        getDetalleDescuentoList().add(detalleDescuento);
        return detalleDescuento;
    }

    public DetalleDescuento removeDetalleDescuento(DetalleDescuento detalleDescuento) {
        getDetalleDescuentoList().remove(detalleDescuento);
        return detalleDescuento;
    }

    public List<MedioImposicionComparendo> getMedioImposicionList() {
        return medioImposicionList;
    }

    public void setMedioImposicionList(List<MedioImposicionComparendo> medioImposicionList) {
        this.medioImposicionList = medioImposicionList;
    }

    public List<TipoNotificacionComparendo> getTipoNotificacionList() {
        return tipoNotificacionList;
    }

    public void setTipoNotificacionList(List<TipoNotificacionComparendo> tipoNotificacionList) {
        this.tipoNotificacionList = tipoNotificacionList;
    }

}