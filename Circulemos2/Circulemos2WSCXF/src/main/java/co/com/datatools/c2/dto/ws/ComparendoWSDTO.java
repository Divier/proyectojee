package co.com.datatools.c2.dto.ws;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Datos del comparendo recibidos a traves del WebService.
 * 
 * @author luis.forero(2015-12-03)
 */
public class ComparendoWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    // ENCABEZADO
    private Integer codigoOrganismoTransito;
    private String codigoMedioImposicion;
    private String codigoTipoAgenteImpositor;
    private Long numeroFactura;
    private Long numeroCitacion;
    private String codigoEstado;
    private XMLGregorianCalendar fechaImposicion;
    private XMLGregorianCalendar horaImposicion;
    private Boolean existeFugaInfractor;
    private String codigoInfraccion;

    private BigDecimal velocidadVehiculo;

    private DireccionWSDTO direccionInfraccion;

    private Integer codigoOrigen;
    private String codigoTipoNotificacion;
    private XMLGregorianCalendar fechaNotificacion;
    private Integer codigoOrigenValidacion;

    // VEHICULO
    private String placaVehiculo;
    private String identificacionVehiculo;
    private Integer codigoOrganismoMatriculaVehiculo;
    private String codigoTipoServicio;
    private String codigoClaseVehiculo;
    private String codigoRadioAccion;
    private String codigoModalidad;
    private String codigoTipoTransportePasajero;
    private String numeroTarjetaOperacion;
    private String numeroLicenciaTransito;
    private Integer codigoOrgTransLicenciaTransito;
    private PersonaWSDTO empresa;

    // INFRACTOR
    private String codigoTipoInfractor;
    private PersonaWSDTO infractor;
    private String observacionesInfractor;

    // PROPIETARIO
    private PersonaWSDTO propietario;

    // AGENTE
    private String placaAgente;
    private String codigoTipoIdentificacionAgente;
    private String numeroIdentificacionAgente;
    private String apellido1Agente;
    private String apellido2Agente;
    private String nombre1Agente;
    private String nombre2Agente;
    private String observacionesAgente;

    // INMOVILIZACION
    private Boolean inmoviliza;
    private Integer numeroPatio;
    private String nombrePatio;
    private Integer numeroGrua;
    private String placaGrua;
    private String consecutivoInmovilizacion;
    private DireccionWSDTO direccionPatio;

    // TESTIGO
    private PersonaWSDTO testigo;

    // EMBRIAGUEZ
    private Boolean niegaPruebaAlcoholemia;
    private Boolean retieneLicencia;
    private Integer gradoAlcoholemia;
    private XMLGregorianCalendar fechaPruebaAlcoholemia;
    private String numeroPruebaAlcoholemia;
    private Integer valorGradoAlcoholemia;
    private Integer numeroReincidencia;

    // EVIDENCIAS
    private EvidenciaWSDTO evidencias[];

    // VALOR COMPARENDO
    private BigDecimal valorConcepto;

    private String secuenciaUnica;

    public Integer getCodigoOrganismoTransito() {
        return codigoOrganismoTransito;
    }

    public void setCodigoOrganismoTransito(Integer codigoOrganismoTransito) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

    public String getCodigoMedioImposicion() {
        return codigoMedioImposicion;
    }

    public void setCodigoMedioImposicion(String codigoMedioImposicion) {
        this.codigoMedioImposicion = codigoMedioImposicion;
    }

    public XMLGregorianCalendar getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(XMLGregorianCalendar fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public XMLGregorianCalendar getHoraImposicion() {
        return horaImposicion;
    }

    public void setHoraImposicion(XMLGregorianCalendar horaImposicion) {
        this.horaImposicion = horaImposicion;
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

    public DireccionWSDTO getDireccionInfraccion() {
        return direccionInfraccion;
    }

    public void setDireccionInfraccion(DireccionWSDTO direccionInfraccion) {
        this.direccionInfraccion = direccionInfraccion;
    }

    public XMLGregorianCalendar getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(XMLGregorianCalendar fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Integer getCodigoOrigenValidacion() {
        return codigoOrigenValidacion;
    }

    public void setCodigoOrigenValidacion(Integer codigoOrigenValidacion) {
        this.codigoOrigenValidacion = codigoOrigenValidacion;
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

    public Integer getCodigoOrganismoMatriculaVehiculo() {
        return codigoOrganismoMatriculaVehiculo;
    }

    public void setCodigoOrganismoMatriculaVehiculo(Integer codigoOrganismoMatriculaVehiculo) {
        this.codigoOrganismoMatriculaVehiculo = codigoOrganismoMatriculaVehiculo;
    }

    public String getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(String codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public String getCodigoClaseVehiculo() {
        return codigoClaseVehiculo;
    }

    public void setCodigoClaseVehiculo(String codigoClaseVehiculo) {
        this.codigoClaseVehiculo = codigoClaseVehiculo;
    }

    public String getCodigoRadioAccion() {
        return codigoRadioAccion;
    }

    public void setCodigoRadioAccion(String codigoRadioAccion) {
        this.codigoRadioAccion = codigoRadioAccion;
    }

    public String getCodigoModalidad() {
        return codigoModalidad;
    }

    public void setCodigoModalidad(String codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }

    public String getCodigoTipoTransportePasajero() {
        return codigoTipoTransportePasajero;
    }

    public void setCodigoTipoTransportePasajero(String codigoTipoTransportePasajero) {
        this.codigoTipoTransportePasajero = codigoTipoTransportePasajero;
    }

    public String getNumeroTarjetaOperacion() {
        return numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(String numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public String getNumeroLicenciaTransito() {
        return numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public Integer getCodigoOrgTransLicenciaTransito() {
        return codigoOrgTransLicenciaTransito;
    }

    public void setCodigoOrgTransLicenciaTransito(Integer codigoOrgTransLicenciaTransito) {
        this.codigoOrgTransLicenciaTransito = codigoOrgTransLicenciaTransito;
    }

    public PersonaWSDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PersonaWSDTO empresa) {
        this.empresa = empresa;
    }

    public String getCodigoTipoInfractor() {
        return codigoTipoInfractor;
    }

    public void setCodigoTipoInfractor(String codigoTipoInfractor) {
        this.codigoTipoInfractor = codigoTipoInfractor;
    }

    public PersonaWSDTO getInfractor() {
        return infractor;
    }

    public void setInfractor(PersonaWSDTO infractor) {
        this.infractor = infractor;
    }

    public String getObservacionesInfractor() {
        return observacionesInfractor;
    }

    public void setObservacionesInfractor(String observacionesInfractor) {
        this.observacionesInfractor = observacionesInfractor;
    }

    public PersonaWSDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(PersonaWSDTO propietario) {
        this.propietario = propietario;
    }

    public String getPlacaAgente() {
        return placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public String getCodigoTipoIdentificacionAgente() {
        return codigoTipoIdentificacionAgente;
    }

    public void setCodigoTipoIdentificacionAgente(String codigoTipoIdentificacionAgente) {
        this.codigoTipoIdentificacionAgente = codigoTipoIdentificacionAgente;
    }

    public String getNumeroIdentificacionAgente() {
        return numeroIdentificacionAgente;
    }

    public void setNumeroIdentificacionAgente(String numeroIdentificacionAgente) {
        this.numeroIdentificacionAgente = numeroIdentificacionAgente;
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

    public String getObservacionesAgente() {
        return observacionesAgente;
    }

    public void setObservacionesAgente(String observacionesAgente) {
        this.observacionesAgente = observacionesAgente;
    }

    public Boolean getInmoviliza() {
        return inmoviliza;
    }

    public void setInmoviliza(Boolean inmoviliza) {
        this.inmoviliza = inmoviliza;
    }

    public Integer getNumeroPatio() {
        return numeroPatio;
    }

    public void setNumeroPatio(Integer numeroPatio) {
        this.numeroPatio = numeroPatio;
    }

    public String getNombrePatio() {
        return nombrePatio;
    }

    public void setNombrePatio(String nombrePatio) {
        this.nombrePatio = nombrePatio;
    }

    public Integer getNumeroGrua() {
        return numeroGrua;
    }

    public void setNumeroGrua(Integer numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getPlacaGrua() {
        return placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public String getConsecutivoInmovilizacion() {
        return consecutivoInmovilizacion;
    }

    public void setConsecutivoInmovilizacion(String consecutivoInmovilizacion) {
        this.consecutivoInmovilizacion = consecutivoInmovilizacion;
    }

    public DireccionWSDTO getDireccionPatio() {
        return direccionPatio;
    }

    public void setDireccionPatio(DireccionWSDTO direccionPatio) {
        this.direccionPatio = direccionPatio;
    }

    public PersonaWSDTO getTestigo() {
        return testigo;
    }

    public void setTestigo(PersonaWSDTO testigo) {
        this.testigo = testigo;
    }

    public Boolean getNiegaPruebaAlcoholemia() {
        return niegaPruebaAlcoholemia;
    }

    public void setNiegaPruebaAlcoholemia(Boolean niegaPruebaAlcoholemia) {
        this.niegaPruebaAlcoholemia = niegaPruebaAlcoholemia;
    }

    public Boolean getRetieneLicencia() {
        return retieneLicencia;
    }

    public void setRetieneLicencia(Boolean retieneLicencia) {
        this.retieneLicencia = retieneLicencia;
    }

    public Integer getGradoAlcoholemia() {
        return gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(Integer gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public XMLGregorianCalendar getFechaPruebaAlcoholemia() {
        return fechaPruebaAlcoholemia;
    }

    public void setFechaPruebaAlcoholemia(XMLGregorianCalendar fechaPruebaAlcoholemia) {
        this.fechaPruebaAlcoholemia = fechaPruebaAlcoholemia;
    }

    public String getNumeroPruebaAlcoholemia() {
        return numeroPruebaAlcoholemia;
    }

    public void setNumeroPruebaAlcoholemia(String numeroPruebaAlcoholemia) {
        this.numeroPruebaAlcoholemia = numeroPruebaAlcoholemia;
    }

    public Integer getValorGradoAlcoholemia() {
        return valorGradoAlcoholemia;
    }

    public void setValorGradoAlcoholemia(Integer valorGradoAlcoholemia) {
        this.valorGradoAlcoholemia = valorGradoAlcoholemia;
    }

    public Integer getNumeroReincidencia() {
        return numeroReincidencia;
    }

    public void setNumeroReincidencia(Integer numeroReincidencia) {
        this.numeroReincidencia = numeroReincidencia;
    }

    public EvidenciaWSDTO[] getEvidencias() {
        return evidencias;
    }

    public void setEvidencias(EvidenciaWSDTO[] evidencias) {
        this.evidencias = evidencias;
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

    public String getCodigoTipoAgenteImpositor() {
        return codigoTipoAgenteImpositor;
    }

    public void setCodigoTipoAgenteImpositor(String codigoTipoAgenteImpositor) {
        this.codigoTipoAgenteImpositor = codigoTipoAgenteImpositor;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(Long numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Integer getCodigoOrigen() {
        return codigoOrigen;
    }

    public void setCodigoOrigen(Integer codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public String getCodigoTipoNotificacion() {
        return codigoTipoNotificacion;
    }

    public void setCodigoTipoNotificacion(String codigoTipoNotificacion) {
        this.codigoTipoNotificacion = codigoTipoNotificacion;
    }

    public String getSecuenciaUnica() {
        return secuenciaUnica;
    }

    public void setSecuenciaUnica(String secuenciaUnica) {
        this.secuenciaUnica = secuenciaUnica;
    }

}