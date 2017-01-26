package co.com.datatools.c2.dto.comparendo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Tiene la logica para el registro de comparendos no regenerar
 * 
 * @author Generated
 * @author julio.pinzon (2015-10-21)
 * @author luis.forero (2016-04-25)
 * @version 3.0 - Tue Oct 20 08:14:46 COT 2015
 * @author divier.casas (2016-04-26)
 * @version 3.0 - Tue Apr 26 13:37:20 COT 2016
 */
public class ProcesaComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ComparendoDTO comparendo;
    private String numeroComparendo;
    private String numeroCitacion;
    private OrganismoTransitoDTO organismoTransito;
    private Integer idTipoComparendo;
    private Date fechaOperacion;
    private Integer codigoOrigen;
    private Integer codigoMedioImposicion;
    private Integer codigoTipoInfractor;
    private Integer idTipoNotificacionComparendo;
    private Date fechaInfraccion;
    private Date horaInfraccion;
    private ProcesaDireccionDTO procesaDireccionComparendo;
    private String codigoInfraccion;
    private BigDecimal velocidadVehiculo;
    private Integer idEstadoComparendo;
    private Integer idTipoAgenteImpositor;
    private Boolean retieneLicencia;
    private Boolean existeFugaInfractor;
    private Boolean niegaPruebaAlcoholemia;
    private String numeroPruebaAlcoholemia;
    private Integer gradoAlcoholemia;
    private Integer valorGradoAlcoholemia;
    private Date fechaPruebaAlcoholemia;
    private Integer numeroReincidencia;
    private Date fechaNotificacion;
    private String observacionesInfractor;
    private Boolean inmovilizado;
    private UsuarioPersonaDTO usuarioPersona;
    private Integer idRuta;
    private Integer idTipoServicio;
    private Integer idRadioAccion;
    private Integer idModalidad;
    private String placaVehiculo;
    private String identificacionVehiculo;
    private String numeroTarjetaOperacion;
    private Integer idClaseVehiculo;
    private Integer idNivelServicio;
    private Integer codigoOrganismoMatriculaVehiculo;
    private Integer codigoOrganismoLicenciaTransito;
    private String numeroLicenciaTransito;
    private Integer idMarcaVehiculo;
    private Integer idLineaVehiculo;
    private Integer idColor;
    private String numeroMotor;
    private String modelo;
    private String placaAgente;
    private Integer pesoNeto;
    private Integer pesoSeco;
    private Integer codigoTipoTransPasajero;
    private Integer idAgenteTransito;
    private String observacionesAgente;
    private Integer idTipoIdentificacionAgente;
    private String numeroIdentificacionAgente;
    private Long idUnificacionResponsable;
    private String nombreResponsable;
    private String apellido1Agente;
    private String apellido2Agente;
    private String nombre1Agente;
    private String nombre2Agente;
    private Integer idPatio;
    private Integer numeroGrua;
    private String placaGrua;
    private String consecutivoInmovilizacion;
    private Integer numeroPatio;
    private String nombrePatio;
    private String numeroInforme;
    private ProcesaDireccionDTO procesaDireccionPatio;
    private Date fechaRecepcion;
    private Date fechaCorreccion;
    private Date fechaReporte;
    private Long idFacturaAxis;
    private String secuenciaUnica;
    private List<ProcesaEvidenciaDTO> procesaEvidencias;
    private List<DetalleProcesamientoDTO> detalleProcesamientos;
    private List<ProcesaComparendoPersonaDTO> procesaComparendoPersonas;
    private String nombreOrigen;

    // Campo adicional para guardar la infraccion en el comparendo
    private Integer idInfraccion;
    private Integer idGradoAlcoholemia;

    // Campo adicional para identificar el valor del comparendo procesado
    private BigDecimal valorConcepto;
    private Integer idActividad;

    // --- Constructor
    public ProcesaComparendoDTO() {
    }

    public ProcesaComparendoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public String getNumeroComparendo() {
        return this.numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getIdTipoComparendo() {
        return this.idTipoComparendo;
    }

    public void setIdTipoComparendo(Integer idTipoComparendo) {
        this.idTipoComparendo = idTipoComparendo;
    }

    public Date getFechaOperacion() {
        return this.fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Integer getCodigoOrigen() {
        return this.codigoOrigen;
    }

    public void setCodigoOrigen(Integer codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public Integer getCodigoMedioImposicion() {
        return this.codigoMedioImposicion;
    }

    public void setCodigoMedioImposicion(Integer codigoMedioImposicion) {
        this.codigoMedioImposicion = codigoMedioImposicion;
    }

    public Integer getCodigoTipoInfractor() {
        return this.codigoTipoInfractor;
    }

    public void setCodigoTipoInfractor(Integer codigoTipoInfractor) {
        this.codigoTipoInfractor = codigoTipoInfractor;
    }

    public Integer getIdTipoNotificacionComparendo() {
        return this.idTipoNotificacionComparendo;
    }

    public void setIdTipoNotificacionComparendo(Integer idTipoNotificacionComparendo) {
        this.idTipoNotificacionComparendo = idTipoNotificacionComparendo;
    }

    public Date getFechaInfraccion() {
        return this.fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public Date getHoraInfraccion() {
        return this.horaInfraccion;
    }

    public void setHoraInfraccion(Date horaInfraccion) {
        this.horaInfraccion = horaInfraccion;
    }

    public ProcesaDireccionDTO getProcesaDireccionComparendo() {
        return this.procesaDireccionComparendo;
    }

    public void setProcesaDireccionComparendo(ProcesaDireccionDTO procesaDireccionComparendo) {
        this.procesaDireccionComparendo = procesaDireccionComparendo;
    }

    public String getCodigoInfraccion() {
        return this.codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Boolean getRetieneLicencia() {
        return this.retieneLicencia;
    }

    public void setRetieneLicencia(Boolean retieneLicencia) {
        this.retieneLicencia = retieneLicencia;
    }

    public Boolean getExisteFugaInfractor() {
        return this.existeFugaInfractor;
    }

    public void setExisteFugaInfractor(Boolean existeFugaInfractor) {
        this.existeFugaInfractor = existeFugaInfractor;
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

    public Integer getGradoAlcoholemia() {
        return this.gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(Integer gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public Integer getValorGradoAlcoholemia() {
        return this.valorGradoAlcoholemia;
    }

    public void setValorGradoAlcoholemia(Integer valorGradoAlcoholemia) {
        this.valorGradoAlcoholemia = valorGradoAlcoholemia;
    }

    public Date getFechaPruebaAlcoholemia() {
        return this.fechaPruebaAlcoholemia;
    }

    public void setFechaPruebaAlcoholemia(Date fechaPruebaAlcoholemia) {
        this.fechaPruebaAlcoholemia = fechaPruebaAlcoholemia;
    }

    public Integer getNumeroReincidencia() {
        return this.numeroReincidencia;
    }

    public void setNumeroReincidencia(Integer numeroReincidencia) {
        this.numeroReincidencia = numeroReincidencia;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getObservacionesInfractor() {
        return this.observacionesInfractor;
    }

    public void setObservacionesInfractor(String observacionesInfractor) {
        this.observacionesInfractor = observacionesInfractor;
    }

    public Boolean getInmovilizado() {
        return this.inmovilizado;
    }

    public void setInmovilizado(Boolean inmovilizado) {
        this.inmovilizado = inmovilizado;
    }

    public UsuarioPersonaDTO getUsuarioPersona() {
        return this.usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersonaDTO usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public Integer getIdRuta() {
        return this.idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Integer getIdTipoServicio() {
        return this.idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Integer getIdRadioAccion() {
        return this.idRadioAccion;
    }

    public void setIdRadioAccion(Integer idRadioAccion) {
        this.idRadioAccion = idRadioAccion;
    }

    public Integer getIdModalidad() {
        return this.idModalidad;
    }

    public void setIdModalidad(Integer idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getIdentificacionVehiculo() {
        return this.identificacionVehiculo;
    }

    public void setIdentificacionVehiculo(String identificacionVehiculo) {
        this.identificacionVehiculo = identificacionVehiculo;
    }

    public String getNumeroTarjetaOperacion() {
        return this.numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(String numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public Integer getIdClaseVehiculo() {
        return this.idClaseVehiculo;
    }

    public void setIdClaseVehiculo(Integer idClaseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
    }

    public Integer getIdNivelServicio() {
        return this.idNivelServicio;
    }

    public void setIdNivelServicio(Integer idNivelServicio) {
        this.idNivelServicio = idNivelServicio;
    }

    public Integer getCodigoOrganismoMatriculaVehiculo() {
        return this.codigoOrganismoMatriculaVehiculo;
    }

    public void setCodigoOrganismoMatriculaVehiculo(Integer codigoOrganismoMatriculaVehiculo) {
        this.codigoOrganismoMatriculaVehiculo = codigoOrganismoMatriculaVehiculo;
    }

    public String getNumeroLicenciaTransito() {
        return this.numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public Integer getIdMarcaVehiculo() {
        return this.idMarcaVehiculo;
    }

    public void setIdMarcaVehiculo(Integer idMarcaVehiculo) {
        this.idMarcaVehiculo = idMarcaVehiculo;
    }

    public Integer getIdLineaVehiculo() {
        return this.idLineaVehiculo;
    }

    public void setIdLineaVehiculo(Integer idLineaVehiculo) {
        this.idLineaVehiculo = idLineaVehiculo;
    }

    public Integer getIdColor() {
        return this.idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getNumeroMotor() {
        return this.numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlacaAgente() {
        return this.placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public Integer getPesoNeto() {
        return this.pesoNeto;
    }

    public void setPesoNeto(Integer pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public Integer getPesoSeco() {
        return this.pesoSeco;
    }

    public void setPesoSeco(Integer pesoSeco) {
        this.pesoSeco = pesoSeco;
    }

    public Integer getCodigoTipoTransPasajero() {
        return this.codigoTipoTransPasajero;
    }

    public void setCodigoTipoTransPasajero(Integer codigoTipoTransPasajero) {
        this.codigoTipoTransPasajero = codigoTipoTransPasajero;
    }

    public Integer getIdAgenteTransito() {
        return this.idAgenteTransito;
    }

    public void setIdAgenteTransito(Integer idAgenteTransito) {
        this.idAgenteTransito = idAgenteTransito;
    }

    public String getObservacionesAgente() {
        return this.observacionesAgente;
    }

    public void setObservacionesAgente(String observacionesAgente) {
        this.observacionesAgente = observacionesAgente;
    }

    public Integer getIdTipoIdentificacionAgente() {
        return this.idTipoIdentificacionAgente;
    }

    public void setIdTipoIdentificacionAgente(Integer idTipoIdentificacionAgente) {
        this.idTipoIdentificacionAgente = idTipoIdentificacionAgente;
    }

    public String getNumeroIdentificacionAgente() {
        return this.numeroIdentificacionAgente;
    }

    public void setNumeroIdentificacionAgente(String numeroIdentificacionAgente) {
        this.numeroIdentificacionAgente = numeroIdentificacionAgente;
    }

    public Long getIdUnificacionResponsable() {
        return this.idUnificacionResponsable;
    }

    public void setIdUnificacionResponsable(Long idUnificacionResponsable) {
        this.idUnificacionResponsable = idUnificacionResponsable;
    }

    public String getNombreResponsable() {
        return this.nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellido1Agente() {
        return this.apellido1Agente;
    }

    public void setApellido1Agente(String apellido1Agente) {
        this.apellido1Agente = apellido1Agente;
    }

    public String getApellido2Agente() {
        return this.apellido2Agente;
    }

    public void setApellido2Agente(String apellido2Agente) {
        this.apellido2Agente = apellido2Agente;
    }

    public String getNombre1Agente() {
        return this.nombre1Agente;
    }

    public void setNombre1Agente(String nombre1Agente) {
        this.nombre1Agente = nombre1Agente;
    }

    public String getNombre2Agente() {
        return this.nombre2Agente;
    }

    public void setNombre2Agente(String nombre2Agente) {
        this.nombre2Agente = nombre2Agente;
    }

    public Integer getIdPatio() {
        return this.idPatio;
    }

    public void setIdPatio(Integer idPatio) {
        this.idPatio = idPatio;
    }

    public Integer getNumeroGrua() {
        return this.numeroGrua;
    }

    public void setNumeroGrua(Integer numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getPlacaGrua() {
        return this.placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public String getConsecutivoInmovilizacion() {
        return this.consecutivoInmovilizacion;
    }

    public void setConsecutivoInmovilizacion(String consecutivoInmovilizacion) {
        this.consecutivoInmovilizacion = consecutivoInmovilizacion;
    }

    public Integer getNumeroPatio() {
        return this.numeroPatio;
    }

    public void setNumeroPatio(Integer numeroPatio) {
        this.numeroPatio = numeroPatio;
    }

    public String getNombrePatio() {
        return this.nombrePatio;
    }

    public void setNombrePatio(String nombrePatio) {
        this.nombrePatio = nombrePatio;
    }

    public String getNumeroInforme() {
        return this.numeroInforme;
    }

    public void setNumeroInforme(String numeroInforme) {
        this.numeroInforme = numeroInforme;
    }

    public ProcesaDireccionDTO getProcesaDireccionPatio() {
        return this.procesaDireccionPatio;
    }

    public void setProcesaDireccionPatio(ProcesaDireccionDTO procesaDireccionPatio) {
        this.procesaDireccionPatio = procesaDireccionPatio;
    }

    public Date getFechaRecepcion() {
        return this.fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaCorreccion() {
        return this.fechaCorreccion;
    }

    public void setFechaCorreccion(Date fechaCorreccion) {
        this.fechaCorreccion = fechaCorreccion;
    }

    public Date getFechaReporte() {
        return this.fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ProcesaEvidenciaDTO> getProcesaEvidencias() {
        if (this.procesaEvidencias == null) {
            this.procesaEvidencias = new java.util.ArrayList<>(1);
        }
        return this.procesaEvidencias;
    }

    public void setProcesaEvidencias(List<ProcesaEvidenciaDTO> procesaEvidencias) {
        this.procesaEvidencias = procesaEvidencias;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleProcesamientoDTO> getDetalleProcesamientos() {
        if (this.detalleProcesamientos == null) {
            this.detalleProcesamientos = new java.util.ArrayList<>(1);
        }
        return this.detalleProcesamientos;
    }

    public void setDetalleProcesamientos(List<DetalleProcesamientoDTO> detalleProcesamientos) {
        this.detalleProcesamientos = detalleProcesamientos;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ProcesaComparendoPersonaDTO> getProcesaComparendoPersonas() {
        if (this.procesaComparendoPersonas == null) {
            this.procesaComparendoPersonas = new java.util.ArrayList<>(1);
        }
        return this.procesaComparendoPersonas;
    }

    public void setProcesaComparendoPersonas(List<ProcesaComparendoPersonaDTO> procesaComparendoPersonas) {
        this.procesaComparendoPersonas = procesaComparendoPersonas;
    }

    public Integer getIdInfraccion() {
        return idInfraccion;
    }

    public void setIdInfraccion(Integer idInfraccion) {
        this.idInfraccion = idInfraccion;
    }

    public Integer getIdGradoAlcoholemia() {
        return idGradoAlcoholemia;
    }

    public void setIdGradoAlcoholemia(Integer idGradoAlcoholemia) {
        this.idGradoAlcoholemia = idGradoAlcoholemia;
    }

    public Integer getCodigoOrganismoLicenciaTransito() {
        return codigoOrganismoLicenciaTransito;
    }

    public void setCodigoOrganismoLicenciaTransito(Integer codigoOrganismoLicenciaTransito) {
        this.codigoOrganismoLicenciaTransito = codigoOrganismoLicenciaTransito;
    }

    public BigDecimal getValorConcepto() {
        return valorConcepto;
    }

    public void setValorConcepto(BigDecimal valorConcepto) {
        this.valorConcepto = valorConcepto;
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

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getSecuenciaUnica() {
        return secuenciaUnica;
    }

    public void setSecuenciaUnica(String secuenciaUnica) {
        this.secuenciaUnica = secuenciaUnica;
    }

}
