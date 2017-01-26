package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "comparendo")
@NamedQueries({ @NamedQuery(name = "Comparendo.findAll", query = "SELECT c FROM Comparendo c"), @NamedQuery(
        name = "Comparendo.findByCiCompOrgTrans",
        query = "SELECT c FROM Comparendo c WHERE c.cicomparendo = :pCiComp AND c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :pCodOrg") })
public class Comparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Nombre consulta de comparendo por:
     * <p>
     * <li>pCiComp identificador del comparendo CICOMPARENDO</li>
     * <li>pCodOrg codigo del organismo de transito</li>
     * </p>
     * <br>
     * JPQL: SELECT c FROM Comparendo c WHERE c.cicomparendo = :pCiComp AND c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :pCodOrg
     * 
     * @author luis.forero(2016-03-30)
     */
    public static final String SQ_COMPARENDO_BY_CICOM_ORG = "Comparendo.findByCiCompOrgTrans";

    @Id
    @Column(name = "cicomparendo")
    private Long cicomparendo;

    @JoinColumn(name = "cicomparendo", referencedColumnName = "cicomparendo")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private OrdenComparendoNacional ordenComparendoNacional;

    @JoinColumn(name = "codigo_origen", referencedColumnName = "codigo_origen")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoOrigenComparendo tipoOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioPersona usuarioPersona;

    @JoinColumn(name = "codigo_medio_imposicion", referencedColumnName = "codigo_medio_imposicion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MedioImposicionComparendo medioImposicion;

    @JoinColumn(name = "codigo_tipo_infractor", referencedColumnName = "codigo_tipo_infractor")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoInfractor tipoInfractor;

    @JoinColumn(name = "id_tipo_notificacion_compa", referencedColumnName = "id_tipo_notificacion")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoNotificacionComparendo tipoNotificacion;

    @Basic(optional = false)
    @Column(name = "fecha_infraccion")
    @Temporal(TemporalType.DATE)
    private Date fechaInfraccion;

    @Basic(optional = false)
    @Column(name = "hora_infraccion")
    @Temporal(TemporalType.TIME)
    private Date horaInfraccion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @JoinColumn(name = "id_direccion_comparendo", referencedColumnName = "id_direccion")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Direccion direccion;

    @Column(name = "es_polca")
    private Boolean esPolca;

    @Column(name = "retiene_licencia")
    private Boolean retieneLicencia;

    @Column(name = "existe_fuga_infractor")
    private Boolean existeFugaInfractor;

    @JoinColumn(name = "id_infraccion", referencedColumnName = "id_infraccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Infraccion infraccion;

    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ruta ruta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_comparendo")
    private TipoComparendo tipoComparendo;

    @JoinColumn(name = "id_grado_alcoholemia", referencedColumnName = "id_grado_alcoholemia")
    @ManyToOne(fetch = FetchType.LAZY)
    private GradoAlcoholemia gradoAlcoholemia;

    @Column(name = "valor_grado_alcoholemia")
    private Integer valorGradoAlcoholemia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_comparendo")
    private EstadoComparendo estadoComparendo;

    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaNotificacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_prueba_alcoholemia")
    private Date fechaPruebaAlcoholemia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "observaciones_infractor")
    private String observacionesInfractor;

    @Column(name = "niega_prueba_alcoholemia")
    private Boolean niegaPruebaAlcoholemia;

    @Column(name = "numero_prueba_alcoholemia")
    private String numeroPruebaAlcoholemia;

    @Column(name = "numero_reincidencia")
    private Integer numeroReincidencia;

    @Column(name = "numero_citacion")
    private String numeroCitacion;

    @Column(name = "velocidad_vehiculo")
    private BigDecimal velocidadVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_agente_impositor")
    private TipoAgenteImpositor tipoAgenteImpositor;

    @OneToOne(mappedBy = "comparendo", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private ComparendoAgente comparendoAgente;

    @OneToOne(mappedBy = "comparendo", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private ComparendoPatio comparendoPatio;

    @OneToOne(mappedBy = "comparendo", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private ComparendoVehiculo comparendoVehiculo;

    @OneToMany(mappedBy = "comparendo", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<ComparendoPersona> personaList;

    @OneToMany(mappedBy = "comparendo", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<Evidencia> evidenciaList;

    @Column(name = "fecha_genera_cartera")
    @Temporal(TemporalType.DATE)
    private Date fechaGeneraCartera;

    @Basic(optional = false)
    @Column(name = "cartera_generada")
    private Boolean carteraGenerada;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comparendo", fetch = FetchType.LAZY)
    private List<ComparendoCartera> comparendoCarteraList;

    @OneToMany(mappedBy = "comparendo", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<TrazabilidadComparendo> trazabilidadComparendoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_comparendo_simit")
    private EstadoComparendoSimit estadoComparendoSimit;

    @Column(name = "id_documento_notificacion")
    private Long idDocumentoNotificacion;

    @Column(name = "id_factura_axis")
    private Long idFacturaAxis;

    @Column(name = "secuencia_unica")
    private String secuenciaUnica;

    @OneToMany(mappedBy = "comparendo", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<RectificaComparendo> rectificaComparendoList;

    @OneToMany(mappedBy = "comparendo")
    private List<NotificacionCorreoComparendo> notificacionCorreoComparendoList;

    @OneToMany(mappedBy = "comparendo", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<PagoComparendo> pagoComparendos;

    // bi-directional many-to-one association to ComparendoArchivoAcuse
    @OneToMany(mappedBy = "comparendo")
    private List<ComparendoArchivoAcuse> comparendoArchivoAcuses;

    // bi-directional many-to-one association to CursoPedagogico
    @OneToMany(mappedBy = "comparendo")
    private List<CursoPedagogico> cursoPedagogicoList;

    // bi-directional many-to-many association to Resolucion
    @OneToMany(mappedBy = "comparendo", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ComparendoResolucion> comparendoResolucions;

    // bi-directional many-to-one association to ComparendoProceso
    @OneToMany(mappedBy = "comparendo")
    private List<ComparendoProceso> comparendoProcesos;

    @Column(name = "placa_agente_transito")
    private String placaAgenteTransito;

    public Comparendo() {
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public OrdenComparendoNacional getOrdenComparendoNacional() {
        return ordenComparendoNacional;
    }

    public void setOrdenComparendoNacional(OrdenComparendoNacional ordenComparendoNacional) {
        this.ordenComparendoNacional = ordenComparendoNacional;
    }

    public TipoOrigenComparendo getTipoOrigen() {
        return tipoOrigen;
    }

    public void setTipoOrigen(TipoOrigenComparendo tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public MedioImposicionComparendo getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(MedioImposicionComparendo medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public TipoInfractor getTipoInfractor() {
        return tipoInfractor;
    }

    public void setTipoInfractor(TipoInfractor tipoInfractor) {
        this.tipoInfractor = tipoInfractor;
    }

    public TipoNotificacionComparendo getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacionComparendo tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public Date getFechaInfraccion() {
        return fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public Date getHoraInfraccion() {
        return horaInfraccion;
    }

    public void setHoraInfraccion(Date horaInfraccion) {
        this.horaInfraccion = horaInfraccion;
    }

    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Boolean getRetieneLicencia() {
        return retieneLicencia;
    }

    public void setRetieneLicencia(Boolean retieneLicencia) {
        this.retieneLicencia = retieneLicencia;
    }

    public Boolean getExisteFugaInfractor() {
        return existeFugaInfractor;
    }

    public void setExisteFugaInfractor(Boolean existeFugaInfractor) {
        this.existeFugaInfractor = existeFugaInfractor;
    }

    public Infraccion getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public GradoAlcoholemia getGradoAlcoholemia() {
        return gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(GradoAlcoholemia gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public Integer getValorGradoAlcoholemia() {
        return valorGradoAlcoholemia;
    }

    public void setValorGradoAlcoholemia(Integer valorGradoAlcoholemia) {
        this.valorGradoAlcoholemia = valorGradoAlcoholemia;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaPruebaAlcoholemia() {
        return this.fechaPruebaAlcoholemia;
    }

    public void setFechaPruebaAlcoholemia(Date fechaPruebaAlcoholemia) {
        this.fechaPruebaAlcoholemia = fechaPruebaAlcoholemia;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservacionesInfractor() {
        return observacionesInfractor;
    }

    public void setObservacionesInfractor(String observacionesInfractor) {
        this.observacionesInfractor = observacionesInfractor;
    }

    public Boolean getNiegaPruebaAlcoholemia() {
        return this.niegaPruebaAlcoholemia;
    }

    public void setNiegaPruebaAlcoholemia(Boolean niegaPruebaAlcoholemia) {
        this.niegaPruebaAlcoholemia = niegaPruebaAlcoholemia;
    }

    public String getNumeroPruebaAlcoholemia() {
        return this.numeroPruebaAlcoholemia;
    }

    public void setNumeroPruebaAlcoholemia(String numeroPruebaAlcoholemia) {
        this.numeroPruebaAlcoholemia = numeroPruebaAlcoholemia;
    }

    public Integer getNumeroReincidencia() {
        return this.numeroReincidencia;
    }

    public void setNumeroReincidencia(Integer numeroReincidencia) {
        this.numeroReincidencia = numeroReincidencia;
    }

    public List<ComparendoPersona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<ComparendoPersona> personaList) {
        this.personaList = personaList;
    }

    public List<Evidencia> getEvidenciaList() {
        return evidenciaList;
    }

    public void setEvidenciaList(List<Evidencia> evidenciaList) {
        this.evidenciaList = evidenciaList;
    }

    public UsuarioPersona getUsuarioPersona() {
        return usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersona usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public TipoComparendo getTipoComparendo() {
        return tipoComparendo;
    }

    public void setTipoComparendo(TipoComparendo tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public EstadoComparendo getEstadoComparendo() {
        return estadoComparendo;
    }

    public void setEstadoComparendo(EstadoComparendo estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

    public ComparendoAgente getComparendoAgente() {
        return comparendoAgente;
    }

    public void setComparendoAgente(ComparendoAgente comparendoAgente) {
        this.comparendoAgente = comparendoAgente;
    }

    public ComparendoPatio getComparendoPatio() {
        return comparendoPatio;
    }

    public void setComparendoPatio(ComparendoPatio comparendoPatio) {
        this.comparendoPatio = comparendoPatio;
    }

    public ComparendoVehiculo getComparendoVehiculo() {
        return comparendoVehiculo;
    }

    public void setComparendoVehiculo(ComparendoVehiculo comparendoVehiculo) {
        this.comparendoVehiculo = comparendoVehiculo;
    }

    public Date getFechaGeneraCartera() {
        return fechaGeneraCartera;
    }

    public void setFechaGeneraCartera(Date fechaGeneraCartera) {
        this.fechaGeneraCartera = fechaGeneraCartera;
    }

    public Boolean getCarteraGenerada() {
        return carteraGenerada;
    }

    public void setCarteraGenerada(Boolean carteraGenerada) {
        this.carteraGenerada = carteraGenerada;
    }

    public Boolean getEsPolca() {
        return esPolca;
    }

    public void setEsPolca(Boolean esPolca) {
        this.esPolca = esPolca;
    }

    public List<TrazabilidadComparendo> getTrazabilidadComparendoList() {
        return trazabilidadComparendoList;
    }

    public void setTrazabilidadComparendoList(List<TrazabilidadComparendo> trazabilidadComparendoList) {
        this.trazabilidadComparendoList = trazabilidadComparendoList;
    }

    public EstadoComparendoSimit getEstadoComparendoSimit() {
        return this.estadoComparendoSimit;
    }

    public void setEstadoComparendoSimit(EstadoComparendoSimit estadoComparendoSimit) {
        this.estadoComparendoSimit = estadoComparendoSimit;
    }

    public List<ComparendoCartera> getComparendoCarteraList() {
        return comparendoCarteraList;
    }

    public void setComparendoCarteraList(List<ComparendoCartera> comparendoCarteraList) {
        this.comparendoCarteraList = comparendoCarteraList;
    }

    public Long getIdDocumentoNotificacion() {
        return this.idDocumentoNotificacion;
    }

    public void setIdDocumentoNotificacion(Long idDocumentoNotificacion) {
        this.idDocumentoNotificacion = idDocumentoNotificacion;
    }

    public List<RectificaComparendo> getRectificaComparendoList() {
        return this.rectificaComparendoList;
    }

    public void setRectificaComparendoList(List<RectificaComparendo> rectificaComparendoList) {
        this.rectificaComparendoList = rectificaComparendoList;
    }

    public List<NotificacionCorreoComparendo> getNotificacionCorreoComparendoList() {
        return this.notificacionCorreoComparendoList;
    }

    public void setNotificacionCorreoComparendoList(
            List<NotificacionCorreoComparendo> notificacionCorreoComparendoList) {
        this.notificacionCorreoComparendoList = notificacionCorreoComparendoList;
    }

    public List<PagoComparendo> getPagoComparendos() {
        return this.pagoComparendos;
    }

    public void setPagoComparendos(List<PagoComparendo> pagoComparendos) {
        this.pagoComparendos = pagoComparendos;
    }

    public List<ComparendoArchivoAcuse> getComparendoArchivoAcuses() {
        return comparendoArchivoAcuses;
    }

    public void setComparendoArchivoAcuses(List<ComparendoArchivoAcuse> comparendoArchivoAcuses) {
        this.comparendoArchivoAcuses = comparendoArchivoAcuses;
    }

    public List<CursoPedagogico> getCursoPedagogicoList() {
        return cursoPedagogicoList;
    }

    public void setCursoPedagogicoList(List<CursoPedagogico> cursoPedagogicoList) {
        this.cursoPedagogicoList = cursoPedagogicoList;
    }

    public List<ComparendoResolucion> getComparendoResolucions() {
        return this.comparendoResolucions;
    }

    public void setComparendoResolucions(List<ComparendoResolucion> comparendoResolucions) {
        this.comparendoResolucions = comparendoResolucions;
    }

    public List<ComparendoProceso> getComparendoProcesos() {
        return this.comparendoProcesos;
    }

    public void setComparendoProcesos(List<ComparendoProceso> comparendoProcesos) {
        this.comparendoProcesos = comparendoProcesos;
    }

    public BigDecimal getVelocidadVehiculo() {
        return velocidadVehiculo;
    }

    public void setVelocidadVehiculo(BigDecimal velocidadVehiculo) {
        this.velocidadVehiculo = velocidadVehiculo;
    }

    public TipoAgenteImpositor getTipoAgenteImpositor() {
        return tipoAgenteImpositor;
    }

    public void setTipoAgenteImpositor(TipoAgenteImpositor tipoAgenteImpositor) {
        this.tipoAgenteImpositor = tipoAgenteImpositor;
    }

    public Long getIdFacturaAxis() {
        return idFacturaAxis;
    }

    public void setIdFacturaAxis(Long idFacturaAxis) {
        this.idFacturaAxis = idFacturaAxis;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public String getPlacaAgenteTransito() {
        return placaAgenteTransito;
    }

    public void setPlacaAgenteTransito(String placaAgenteTransito) {
        this.placaAgenteTransito = placaAgenteTransito;
    }

    public String getSecuenciaUnica() {
        return secuenciaUnica;
    }

    public void setSecuenciaUnica(String secuenciaUnica) {
        this.secuenciaUnica = secuenciaUnica;
    }
}