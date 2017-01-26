package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "procesa_comparendo")
@NamedQueries({ @NamedQuery(name = "ProcesaComparendo.findAll", query = "SELECT p FROM ProcesaComparendo p") })
public class ProcesaComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procesamiento")
    private Long id;

    @JoinColumn(name = "cicomparendo", referencedColumnName = "cicomparendo")
    @OneToOne(fetch = FetchType.LAZY)
    private Comparendo comparendo;

    @Column(name = "numero_comparendo")
    private String numeroComparendo;

    @JoinColumn(name = "codigo_organismo_transito", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @Column(name = "id_tipo_comparendo")
    private Integer idTipoComparendo;

    @Basic(optional = false)
    @Column(name = "fecha_operacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOperacion;

    @Column(name = "codigo_origen")
    private Integer codigoOrigen;

    @Column(name = "id_medio_imposicion")
    private Integer codigoMedioImposicion;

    @Column(name = "codigo_tipo_infractor")
    private Integer codigoTipoInfractor;

    @Column(name = "id_tipo_notificacion_compa")
    private Integer idTipoNotificacionComparendo;

    @Column(name = "fecha_infraccion")
    @Temporal(TemporalType.DATE)
    private Date fechaInfraccion;

    @Column(name = "hora_infraccion")
    @Temporal(TemporalType.TIME)
    private Date horaInfraccion;

    @JoinColumn(name = "id_procesa_direccion_compa", referencedColumnName = "id_procesa_direccion")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProcesaDireccion procesaDireccionComparendo;

    @Column(name = "codigo_infraccion")
    private String codigoInfraccion;

    @Column(name = "retiene_licencia")
    private Boolean retieneLicencia;

    @Column(name = "existe_fuga_infractor")
    private Boolean existeFugaInfractor;

    @Column(name = "niega_prueba_alcoholemia")
    private Boolean niegaPruebaAlcoholemia;

    @Column(name = "numero_prueba_alcoholemia")
    private String numeroPruebaAlcoholemia;

    @Column(name = "grado_alcoholemia")
    private Integer gradoAlcoholemia;

    @Column(name = "id_actividad")
    private Integer idActividad;

    @Column(name = "valor_grado_alcoholemia")
    private Integer valorGradoAlcoholemia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_prueba_alcoholemia")
    private Date fechaPruebaAlcoholemia;

    @Column(name = "numero_reincidencia")
    private Integer numeroReincidencia;

    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaNotificacion;

    @Column(name = "observaciones_infractor")
    private String observacionesInfractor;

    @Column(name = "inmovilizado")
    private Boolean inmovilizado;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioPersona usuarioPersona;

    @Column(name = "id_ruta")
    private Integer idRuta;

    @Column(name = "id_tipo_servicio")
    private Integer idTipoServicio;

    @Column(name = "id_radio_accion")
    private Integer idRadioAccion;

    @Column(name = "id_modalidad")
    private Integer idModalidad;

    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    @Column(name = "identificacion_vehiculo")
    private String identificacionVehiculo;

    @Column(name = "numero_tarjeta_operacion")
    private String numeroTarjetaOperacion;

    @Column(name = "id_clase_vehiculo")
    private Integer idClaseVehiculo;

    @Column(name = "id_nivel_servicio")
    private Integer idNivelServicio;

    @Column(name = "codigo_organismo_matri_vehic")
    private Integer codigoOrganismoMatriculaVehiculo;

    @Column(name = "codigo_organimo_licen_trans")
    private Integer codigoOrganismoLicenciaTransito;

    @Column(name = "numero_licencia_transito")
    private String numeroLicenciaTransito;

    @Column(name = "id_marca_vehiculo")
    private Integer idMarcaVehiculo;

    @Column(name = "id_linea_vehiculo")
    private Integer idLineaVehiculo;

    @Column(name = "id_color")
    private Integer idColor;

    @Column(name = "numero_motor")
    private String numeroMotor;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "placa_agente")
    private String placaAgente;

    @Column(name = "peso_neto")
    private Integer pesoNeto;

    @Column(name = "peso_seco")
    private Integer pesoSeco;

    @Column(name = "codigo_tipo_transporte_pasaj")
    private Integer codigoTipoTransPasajero;

    @Column(name = "id_agente_transito")
    private Integer idAgenteTransito;

    @Column(name = "observaciones_agente")
    private String observacionesAgente;

    @Column(name = "id_tipo_identificacion_agente")
    private Integer idTipoIdentificacionAgente;

    @Column(name = "numero_identificacion_agente")
    private String numeroIdentificacionAgente;

    @Column(name = "id_unificacion_responsable")
    private Long idUnificacionResponsable;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    @Column(name = "apellido1_agente")
    private String apellido1Agente;

    @Column(name = "apellido2_agente")
    private String apellido2Agente;

    @Column(name = "nombre1_agente")
    private String nombre1Agente;

    @Column(name = "nombre2_agente")
    private String nombre2Agente;

    @Column(name = "id_patio")
    private Integer idPatio;

    @Column(name = "numero_grua")
    private Integer numeroGrua;

    @Column(name = "placa_grua")
    private String placaGrua;

    @Column(name = "consecutivo_inmovilizacion")
    private String consecutivoInmovilizacion;

    @Column(name = "numero_patio")
    private Integer numeroPatio;

    @Column(name = "nombre_patio")
    private String nombrePatio;

    @Column(name = "numero_informe")
    private String numeroInforme;

    @JoinColumn(name = "id_procesa_direccion_patio", referencedColumnName = "id_procesa_direccion")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProcesaDireccion procesaDireccionPatio;

    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_correccion")
    private Date fechaCorreccion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_reporte")
    private Date fechaReporte;

    @Basic
    @Column(name = "numero_citacion")
    private String numeroCitacion;

    @Basic
    @Column(name = "velocidad_vehiculo")
    private BigDecimal velocidadVehiculo;

    @Basic
    @Column(name = "id_estado_comparendo")
    private Integer idEstadoComparendo;

    @Basic
    @Column(name = "id_tipo_agente_impositor")
    private Integer idTipoAgenteImpositor;

    @Basic
    @Column(name = "id_factura_axis")
    private Long idFacturaAxis;

    // ////////////////////////////////////////////////////////////////

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "procesaComparendo")
    private List<ProcesaEvidencia> procesaEvidencias;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "procesaComparendo")
    private List<DetalleProcesamiento> detalleProcesamientos;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "procesaComparendo", fetch = FetchType.LAZY)
    private List<ProcesaComparendoPersona> procesaComparendoPersonas;

    public ProcesaComparendo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getCodigoOrigen() {
        return codigoOrigen;
    }

    public void setCodigoOrigen(Integer codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public Integer getCodigoMedioImposicion() {
        return codigoMedioImposicion;
    }

    public void setCodigoMedioImposicion(Integer codigoMedioImposicion) {
        this.codigoMedioImposicion = codigoMedioImposicion;
    }

    public Integer getCodigoTipoInfractor() {
        return codigoTipoInfractor;
    }

    public void setCodigoTipoInfractor(Integer codigoTipoInfractor) {
        this.codigoTipoInfractor = codigoTipoInfractor;
    }

    public Integer getIdTipoNotificacionComparendo() {
        return idTipoNotificacionComparendo;
    }

    public void setIdTipoNotificacionComparendo(Integer idTipoNotificacionComparendo) {
        this.idTipoNotificacionComparendo = idTipoNotificacionComparendo;
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

    public ProcesaDireccion getProcesaDireccionComparendo() {
        return procesaDireccionComparendo;
    }

    public void setProcesaDireccionComparendo(ProcesaDireccion procesaDireccion) {
        this.procesaDireccionComparendo = procesaDireccion;
    }

    public Boolean getRetieneLicencia() {
        return retieneLicencia;
    }

    public void setRetieneLicencia(Boolean retieneLicencia) {
        this.retieneLicencia = retieneLicencia;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Integer getIdRadioAccion() {
        return idRadioAccion;
    }

    public void setIdRadioAccion(Integer idRadioAccion) {
        this.idRadioAccion = idRadioAccion;
    }

    public Integer getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Integer idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getIdentificacionVehiculo() {
        return identificacionVehiculo;
    }

    public void setIdentificacionVehiculo(String identificacionVehiculo) {
        this.identificacionVehiculo = identificacionVehiculo;
    }

    public Integer getIdNivelServicio() {
        return idNivelServicio;
    }

    public void setIdNivelServicio(Integer idNivelServicio) {
        this.idNivelServicio = idNivelServicio;
    }

    public Integer getCodigoOrganismoMatriculaVehiculo() {
        return codigoOrganismoMatriculaVehiculo;
    }

    public void setCodigoOrganismoMatriculaVehiculo(Integer codigoOrganismoMatriculaVehiculo) {
        this.codigoOrganismoMatriculaVehiculo = codigoOrganismoMatriculaVehiculo;
    }

    public Boolean getExisteFugaInfractor() {
        return existeFugaInfractor;
    }

    public void setExisteFugaInfractor(Boolean existeFugaInfractor) {
        this.existeFugaInfractor = existeFugaInfractor;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public String getNumeroTarjetaOperacion() {
        return numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(String numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public Integer getGradoAlcoholemia() {
        return gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(Integer gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public Integer getValorGradoAlcoholemia() {
        return valorGradoAlcoholemia;
    }

    public void setValorGradoAlcoholemia(Integer valorGradoAlcoholemia) {
        this.valorGradoAlcoholemia = valorGradoAlcoholemia;
    }

    public String getPlacaAgente() {
        return placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public Integer getIdTipoIdentificacionAgente() {
        return idTipoIdentificacionAgente;
    }

    public void setIdTipoIdentificacionAgente(Integer idTipoIdentificacionAgente) {
        this.idTipoIdentificacionAgente = idTipoIdentificacionAgente;
    }

    public String getNumeroIdentificacionAgente() {
        return numeroIdentificacionAgente;
    }

    public void setNumeroIdentificacionAgente(String numeroIdentificacionAgente) {
        this.numeroIdentificacionAgente = numeroIdentificacionAgente;
    }

    public Integer getIdPatio() {
        return idPatio;
    }

    public void setIdPatio(Integer idPatio) {
        this.idPatio = idPatio;
    }

    public Integer getIdClaseVehiculo() {
        return idClaseVehiculo;
    }

    public void setIdClaseVehiculo(Integer idClaseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
    }

    public Integer getNumeroGrua() {
        return numeroGrua;
    }

    public void setNumeroGrua(Integer numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getNumeroLicenciaTransito() {
        return numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public String getPlacaGrua() {
        return placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getObservacionesAgente() {
        return observacionesAgente;
    }

    public void setObservacionesAgente(String observacionesAgente) {
        this.observacionesAgente = observacionesAgente;
    }

    public String getObservacionesInfractor() {
        return observacionesInfractor;
    }

    public void setObservacionesInfractor(String observacionesInfractor) {
        this.observacionesInfractor = observacionesInfractor;
    }

    public Boolean getInmovilizado() {
        return inmovilizado;
    }

    public void setInmovilizado(Boolean inmovilizado) {
        this.inmovilizado = inmovilizado;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public UsuarioPersona getUsuarioPersona() {
        return usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersona usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public List<ProcesaEvidencia> getProcesaEvidencias() {
        return procesaEvidencias;
    }

    public void setProcesaEvidencias(List<ProcesaEvidencia> procesaEvidencias) {
        this.procesaEvidencias = procesaEvidencias;
    }

    public List<DetalleProcesamiento> getDetalleProcesamientos() {
        return detalleProcesamientos;
    }

    public void setDetalleProcesamientos(List<DetalleProcesamiento> detalleProcesamientos) {
        this.detalleProcesamientos = detalleProcesamientos;
    }

    public List<ProcesaComparendoPersona> getProcesaComparendoPersonas() {
        return procesaComparendoPersonas;
    }

    public void setProcesaComparendoPersonas(List<ProcesaComparendoPersona> procesaComparendoPersonas) {
        this.procesaComparendoPersonas = procesaComparendoPersonas;
    }

    public String getConsecutivoInmovilizacion() {
        return consecutivoInmovilizacion;
    }

    public void setConsecutivoInmovilizacion(String consecutivoInmovilizacion) {
        this.consecutivoInmovilizacion = consecutivoInmovilizacion;
    }

    public Long getIdUnificacionResponsable() {
        return idUnificacionResponsable;
    }

    public void setIdUnificacionResponsable(Long idUnificacionResponsable) {
        this.idUnificacionResponsable = idUnificacionResponsable;
    }

    public Date getFechaCorreccion() {
        return fechaCorreccion;
    }

    public void setFechaCorreccion(Date fechaCorreccion) {
        this.fechaCorreccion = fechaCorreccion;
    }

    public int getIdTipoComparendo() {
        return idTipoComparendo;
    }

    public void setIdTipoComparendo(int idTipoComparendo) {
        this.idTipoComparendo = idTipoComparendo;
    }

    public Integer getIdLineaVehiculo() {
        return idLineaVehiculo;
    }

    public void setIdLineaVehiculo(Integer idLineaVehiculo) {
        this.idLineaVehiculo = idLineaVehiculo;
    }

    public Integer getIdMarcaVehiculo() {
        return idMarcaVehiculo;
    }

    public void setIdMarcaVehiculo(Integer idMarcaVehiculo) {
        this.idMarcaVehiculo = idMarcaVehiculo;
    }

    public String getNumeroInforme() {
        return numeroInforme;
    }

    public void setNumeroInforme(String numeroInforme) {
        this.numeroInforme = numeroInforme;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroPruebaAlcoholemia() {
        return numeroPruebaAlcoholemia;
    }

    public void setNumeroPruebaAlcoholemia(String numeroPruebaAlcoholemia) {
        this.numeroPruebaAlcoholemia = numeroPruebaAlcoholemia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNombrePatio() {
        return nombrePatio;
    }

    public void setNombrePatio(String nombrePatio) {
        this.nombrePatio = nombrePatio;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getNombre1Agente() {
        return nombre1Agente;
    }

    public void setNombre1Agente(String nombre1Agente) {
        this.nombre1Agente = nombre1Agente;
    }

    public String getNombre2Agente() {
        return nombre2Agente;
    }

    public void setNombre2Agente(String nombre2Agente) {
        this.nombre2Agente = nombre2Agente;
    }

    public Boolean getNiegaPruebaAlcoholemia() {
        return niegaPruebaAlcoholemia;
    }

    public void setNiegaPruebaAlcoholemia(Boolean niegaPruebaAlcoholemia) {
        this.niegaPruebaAlcoholemia = niegaPruebaAlcoholemia;
    }

    public Date getFechaPruebaAlcoholemia() {
        return fechaPruebaAlcoholemia;
    }

    public void setFechaPruebaAlcoholemia(Date fechaPruebaAlcoholemia) {
        this.fechaPruebaAlcoholemia = fechaPruebaAlcoholemia;
    }

    public String getApellido1Agente() {
        return apellido1Agente;
    }

    public void setApellido1Agente(String apellido1Agente) {
        this.apellido1Agente = apellido1Agente;
    }

    public String getApellido2Agente() {
        return apellido2Agente;
    }

    public void setApellido2Agente(String apellido2Agente) {
        this.apellido2Agente = apellido2Agente;
    }

    public Integer getCodigoTipoTransPasajero() {
        return codigoTipoTransPasajero;
    }

    public void setCodigoTipoTransPasajero(Integer codigoTipoTransPasajero) {
        this.codigoTipoTransPasajero = codigoTipoTransPasajero;
    }

    public void setIdTipoComparendo(Integer idTipoComparendo) {
        this.idTipoComparendo = idTipoComparendo;
    }

    public void setPesoNeto(Integer pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public void setPesoSeco(Integer pesoSeco) {
        this.pesoSeco = pesoSeco;
    }

    public Integer getNumeroReincidencia() {
        return numeroReincidencia;
    }

    public void setNumeroReincidencia(Integer numeroReincidencia) {
        this.numeroReincidencia = numeroReincidencia;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public ProcesaDireccion getProcesaDireccionPatio() {
        return this.procesaDireccionPatio;
    }

    public void setProcesaDireccionPatio(ProcesaDireccion procesaDireccionPatio) {
        this.procesaDireccionPatio = procesaDireccionPatio;
    }

    public Integer getIdAgenteTransito() {
        return this.idAgenteTransito;
    }

    public void setIdAgenteTransito(Integer idAgenteTransito) {
        this.idAgenteTransito = idAgenteTransito;
    }

    public Integer getCodigoOrganismoLicenciaTransito() {
        return this.codigoOrganismoLicenciaTransito;
    }

    public void setCodigoOrganismoLicenciaTransito(Integer codigoOrganismoLicenciaTransito) {
        this.codigoOrganismoLicenciaTransito = codigoOrganismoLicenciaTransito;
    }

    public Integer getPesoNeto() {
        return pesoNeto;
    }

    public Integer getPesoSeco() {
        return pesoSeco;
    }

    public Integer getNumeroPatio() {
        return numeroPatio;
    }

    public void setNumeroPatio(Integer numeroPatio) {
        this.numeroPatio = numeroPatio;
    }

    public BigDecimal getVelocidadVehiculo() {
        return velocidadVehiculo;
    }

    public void setVelocidadVehiculo(BigDecimal velocidadVehiculo) {
        this.velocidadVehiculo = velocidadVehiculo;
    }

    public Integer getIdEstadoComparendo() {
        return idEstadoComparendo;
    }

    public void setIdEstadoComparendo(Integer idEstadoComparendo) {
        this.idEstadoComparendo = idEstadoComparendo;
    }

    public Integer getIdTipoAgenteImpositor() {
        return idTipoAgenteImpositor;
    }

    public void setIdTipoAgenteImpositor(Integer idTipoAgenteImpositor) {
        this.idTipoAgenteImpositor = idTipoAgenteImpositor;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
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

}