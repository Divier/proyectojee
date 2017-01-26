package co.com.datatools.c2.dto.multas;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu May 19 13:50:27 COT 2016
 */
public class ItMultaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idMulta;
    private String apellido1Agente;
    private String apellido1Infractor;
    private String apellido1Propietario;
    private String apellido1Testigo;
    private String apellido2Agente;
    private String apellido2Infractor;
    private String apellido2Propietario;
    private String apellido2Testigo;
    private String codigoCategLicenConduInfr;
    private String codigoClaseVehiculo;
    private String codigoDepartamentoInfraccion;
    private String codigoDepartamentoInfractor;
    private String codigoDepartamentoPatio;
    private String codigoDepartamentoTestigo;
    private String codigoInfraccion;
    private String codigoLocalidadInfraccion;
    private String codigoLocalidadInfractor;
    private String codigoLocalidadPatio;
    private String codigoLocalidadTestigo;
    private String codigoMedioImposicion;
    private String codigoModalidad;
    private String codigoMunicipioInfraccion;
    private String codigoMunicipioInfractor;
    private String codigoMunicipioPatio;
    private String codigoMunicipioTestigo;
    private Integer codigoOrganLicenConduInfr;
    private Integer codigoOrganismoLicenTrans;
    private Integer codigoOrganismoMatriVehic;
    private Integer codigoOrganismoTransito;
    private String codigoOrigenComparendo;
    private String codigoPaisDirecInfractor;
    private String codigoPaisDirecPatio;
    private String codigoPaisDirecTestigo;
    private String codigoPaisInfraccion;
    private String codigoRadioAccion;
    private String codigoTipoAgenteImpositor;
    private String codigoTipoIdentAgente;
    private String codigoTipoIdentEmpresa;
    private String codigoTipoIdentInfractor;
    private String codigoTipoIdentPropietario;
    private String codigoTipoIdentTestigo;
    private String codigoTipoInfractor;
    private String codigoTipoNotifCompa;
    private String codigoTipoServicio;
    private String codigoTipoTransPasajero;
    private String consecutivoInmovilizacion;
    private String direccionBasicaInfraccion;
    private String direccionBasicaInfractor;
    private String direccionBasicaPatio;
    private String direccionBasicaTestigo;
    private Short edadInfractor;
    private String email;
    private String codigoEstadoComparendo;
    private Short estadoLectura;
    private String evidencia1CodigoTipo;
    private String evidencia1Nombre;
    private String evidencia1Url;
    private String evidencia2CodigoTipo;
    private String evidencia2Nombre;
    private String evidencia2Url;
    private Boolean existeFugaInfractor;
    private Date fechaExpedLicenConduInfra;
    private Date fechaHoraRecibido;
    private Date fechaInfraccion;
    private Date fechaNotificacion;
    private Date fechaPruebaAlcoholemia;
    private Date fechaRegistro;
    private Date fechaVenciLicenConduInfra;
    private Integer gradoAlcoholemia;
    private Date horaInfraccion;
    private String identificacionVehiculo;
    private Boolean inmoviliza;
    private Boolean niegaPruebaAlcoholemia;
    private String nombrePatio;
    private String nombre1Agente;
    private String nombre1Infractor;
    private String nombre1Propietario;
    private String nombre1Testigo;
    private String nombre2Agente;
    private String nombre2Infractor;
    private String nombre2Propietario;
    private String nombre2Testigo;
    private String numeroCitacion;
    private Integer numeroGrua;
    private String numeroIdentPropietario;
    private String numeroIdentTestigo;
    private String numeroIdentificacionAgente;
    private String numeroIdentificacionEmpresa;
    private String numeroIdentificacionInfractor;
    private String numeroLicencia;
    private String numeroLicenciaTransito;
    private Integer numeroPatio;
    private String numeroPruebaAlcoholemia;
    private Integer numeroReincidencia;
    private String numeroTarjetaOperacion;
    private String observacionesAgente;
    private String placaAgente;
    private String placaGrua;
    private String placaVehiculo;
    private String razonSocialEmpresa;
    private String razonSocialInfractor;
    private String razonSocialPropietario;
    private String telefonoFijoInfractor;
    private String telefonoMovilInfractor;
    private String telefonoMovilTestigo;
    private BigDecimal valorConcepto;
    private Integer valorGradoAlcoholemia;
    private BigDecimal velocidadVehiculo;
    private Long idFacturaAxis;
    private BigDecimal longitudDireccionInfraccion;
    private BigDecimal latitudDireccionInfraccion;
    private String nombreLocalidadInfraccion;
    private String secuenciaUnica;

    // --- Constructor
    public ItMultaDTO() {
    }

    public ItMultaDTO(Long idMulta) {
        this.idMulta = idMulta;

    }

    // --- Getters-Setters
    public Long getIdMulta() {
        return this.idMulta;
    }

    public void setIdMulta(Long idMulta) {
        this.idMulta = idMulta;
    }

    public String getApellido1Agente() {
        return this.apellido1Agente;
    }

    public void setApellido1Agente(String apellido1Agente) {
        this.apellido1Agente = apellido1Agente;
    }

    public String getApellido1Infractor() {
        return this.apellido1Infractor;
    }

    public void setApellido1Infractor(String apellido1Infractor) {
        this.apellido1Infractor = apellido1Infractor;
    }

    public String getApellido1Propietario() {
        return this.apellido1Propietario;
    }

    public void setApellido1Propietario(String apellido1Propietario) {
        this.apellido1Propietario = apellido1Propietario;
    }

    public String getApellido1Testigo() {
        return this.apellido1Testigo;
    }

    public void setApellido1Testigo(String apellido1Testigo) {
        this.apellido1Testigo = apellido1Testigo;
    }

    public String getApellido2Agente() {
        return this.apellido2Agente;
    }

    public void setApellido2Agente(String apellido2Agente) {
        this.apellido2Agente = apellido2Agente;
    }

    public String getApellido2Infractor() {
        return this.apellido2Infractor;
    }

    public void setApellido2Infractor(String apellido2Infractor) {
        this.apellido2Infractor = apellido2Infractor;
    }

    public String getApellido2Propietario() {
        return this.apellido2Propietario;
    }

    public void setApellido2Propietario(String apellido2Propietario) {
        this.apellido2Propietario = apellido2Propietario;
    }

    public String getApellido2Testigo() {
        return this.apellido2Testigo;
    }

    public void setApellido2Testigo(String apellido2Testigo) {
        this.apellido2Testigo = apellido2Testigo;
    }

    public String getCodigoCategLicenConduInfr() {
        return this.codigoCategLicenConduInfr;
    }

    public void setCodigoCategLicenConduInfr(String codigoCategLicenConduInfr) {
        this.codigoCategLicenConduInfr = codigoCategLicenConduInfr;
    }

    public String getCodigoClaseVehiculo() {
        return this.codigoClaseVehiculo;
    }

    public void setCodigoClaseVehiculo(String codigoClaseVehiculo) {
        this.codigoClaseVehiculo = codigoClaseVehiculo;
    }

    public String getCodigoDepartamentoInfraccion() {
        return this.codigoDepartamentoInfraccion;
    }

    public void setCodigoDepartamentoInfraccion(String codigoDepartamentoInfraccion) {
        this.codigoDepartamentoInfraccion = codigoDepartamentoInfraccion;
    }

    public String getCodigoDepartamentoInfractor() {
        return this.codigoDepartamentoInfractor;
    }

    public void setCodigoDepartamentoInfractor(String codigoDepartamentoInfractor) {
        this.codigoDepartamentoInfractor = codigoDepartamentoInfractor;
    }

    public String getCodigoDepartamentoPatio() {
        return this.codigoDepartamentoPatio;
    }

    public void setCodigoDepartamentoPatio(String codigoDepartamentoPatio) {
        this.codigoDepartamentoPatio = codigoDepartamentoPatio;
    }

    public String getCodigoDepartamentoTestigo() {
        return this.codigoDepartamentoTestigo;
    }

    public void setCodigoDepartamentoTestigo(String codigoDepartamentoTestigo) {
        this.codigoDepartamentoTestigo = codigoDepartamentoTestigo;
    }

    public String getCodigoInfraccion() {
        return this.codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getCodigoLocalidadInfraccion() {
        return this.codigoLocalidadInfraccion;
    }

    public void setCodigoLocalidadInfraccion(String codigoLocalidadInfraccion) {
        this.codigoLocalidadInfraccion = codigoLocalidadInfraccion;
    }

    public String getCodigoLocalidadInfractor() {
        return this.codigoLocalidadInfractor;
    }

    public void setCodigoLocalidadInfractor(String codigoLocalidadInfractor) {
        this.codigoLocalidadInfractor = codigoLocalidadInfractor;
    }

    public String getCodigoLocalidadPatio() {
        return this.codigoLocalidadPatio;
    }

    public void setCodigoLocalidadPatio(String codigoLocalidadPatio) {
        this.codigoLocalidadPatio = codigoLocalidadPatio;
    }

    public String getCodigoLocalidadTestigo() {
        return this.codigoLocalidadTestigo;
    }

    public void setCodigoLocalidadTestigo(String codigoLocalidadTestigo) {
        this.codigoLocalidadTestigo = codigoLocalidadTestigo;
    }

    public String getCodigoMedioImposicion() {
        return this.codigoMedioImposicion;
    }

    public void setCodigoMedioImposicion(String codigoMedioImposicion) {
        this.codigoMedioImposicion = codigoMedioImposicion;
    }

    public String getCodigoModalidad() {
        return this.codigoModalidad;
    }

    public void setCodigoModalidad(String codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }

    public String getCodigoMunicipioInfraccion() {
        return this.codigoMunicipioInfraccion;
    }

    public void setCodigoMunicipioInfraccion(String codigoMunicipioInfraccion) {
        this.codigoMunicipioInfraccion = codigoMunicipioInfraccion;
    }

    public String getCodigoMunicipioInfractor() {
        return this.codigoMunicipioInfractor;
    }

    public void setCodigoMunicipioInfractor(String codigoMunicipioInfractor) {
        this.codigoMunicipioInfractor = codigoMunicipioInfractor;
    }

    public String getCodigoMunicipioPatio() {
        return this.codigoMunicipioPatio;
    }

    public void setCodigoMunicipioPatio(String codigoMunicipioPatio) {
        this.codigoMunicipioPatio = codigoMunicipioPatio;
    }

    public String getCodigoMunicipioTestigo() {
        return this.codigoMunicipioTestigo;
    }

    public void setCodigoMunicipioTestigo(String codigoMunicipioTestigo) {
        this.codigoMunicipioTestigo = codigoMunicipioTestigo;
    }

    public Integer getCodigoOrganLicenConduInfr() {
        return this.codigoOrganLicenConduInfr;
    }

    public void setCodigoOrganLicenConduInfr(Integer codigoOrganLicenConduInfr) {
        this.codigoOrganLicenConduInfr = codigoOrganLicenConduInfr;
    }

    public Integer getCodigoOrganismoLicenTrans() {
        return this.codigoOrganismoLicenTrans;
    }

    public void setCodigoOrganismoLicenTrans(Integer codigoOrganismoLicenTrans) {
        this.codigoOrganismoLicenTrans = codigoOrganismoLicenTrans;
    }

    public Integer getCodigoOrganismoMatriVehic() {
        return this.codigoOrganismoMatriVehic;
    }

    public void setCodigoOrganismoMatriVehic(Integer codigoOrganismoMatriVehic) {
        this.codigoOrganismoMatriVehic = codigoOrganismoMatriVehic;
    }

    public Integer getCodigoOrganismoTransito() {
        return this.codigoOrganismoTransito;
    }

    public void setCodigoOrganismoTransito(Integer codigoOrganismoTransito) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

    public String getCodigoOrigenComparendo() {
        return this.codigoOrigenComparendo;
    }

    public void setCodigoOrigenComparendo(String codigoOrigenComparendo) {
        this.codigoOrigenComparendo = codigoOrigenComparendo;
    }

    public String getCodigoPaisDirecInfractor() {
        return this.codigoPaisDirecInfractor;
    }

    public void setCodigoPaisDirecInfractor(String codigoPaisDirecInfractor) {
        this.codigoPaisDirecInfractor = codigoPaisDirecInfractor;
    }

    public String getCodigoPaisDirecPatio() {
        return this.codigoPaisDirecPatio;
    }

    public void setCodigoPaisDirecPatio(String codigoPaisDirecPatio) {
        this.codigoPaisDirecPatio = codigoPaisDirecPatio;
    }

    public String getCodigoPaisDirecTestigo() {
        return this.codigoPaisDirecTestigo;
    }

    public void setCodigoPaisDirecTestigo(String codigoPaisDirecTestigo) {
        this.codigoPaisDirecTestigo = codigoPaisDirecTestigo;
    }

    public String getCodigoPaisInfraccion() {
        return this.codigoPaisInfraccion;
    }

    public void setCodigoPaisInfraccion(String codigoPaisInfraccion) {
        this.codigoPaisInfraccion = codigoPaisInfraccion;
    }

    public String getCodigoRadioAccion() {
        return this.codigoRadioAccion;
    }

    public void setCodigoRadioAccion(String codigoRadioAccion) {
        this.codigoRadioAccion = codigoRadioAccion;
    }

    public String getCodigoTipoAgenteImpositor() {
        return this.codigoTipoAgenteImpositor;
    }

    public void setCodigoTipoAgenteImpositor(String codigoTipoAgenteImpositor) {
        this.codigoTipoAgenteImpositor = codigoTipoAgenteImpositor;
    }

    public String getCodigoTipoIdentAgente() {
        return this.codigoTipoIdentAgente;
    }

    public void setCodigoTipoIdentAgente(String codigoTipoIdentAgente) {
        this.codigoTipoIdentAgente = codigoTipoIdentAgente;
    }

    public String getCodigoTipoIdentEmpresa() {
        return this.codigoTipoIdentEmpresa;
    }

    public void setCodigoTipoIdentEmpresa(String codigoTipoIdentEmpresa) {
        this.codigoTipoIdentEmpresa = codigoTipoIdentEmpresa;
    }

    public String getCodigoTipoIdentInfractor() {
        return this.codigoTipoIdentInfractor;
    }

    public void setCodigoTipoIdentInfractor(String codigoTipoIdentInfractor) {
        this.codigoTipoIdentInfractor = codigoTipoIdentInfractor;
    }

    public String getCodigoTipoIdentPropietario() {
        return this.codigoTipoIdentPropietario;
    }

    public void setCodigoTipoIdentPropietario(String codigoTipoIdentPropietario) {
        this.codigoTipoIdentPropietario = codigoTipoIdentPropietario;
    }

    public String getCodigoTipoIdentTestigo() {
        return this.codigoTipoIdentTestigo;
    }

    public void setCodigoTipoIdentTestigo(String codigoTipoIdentTestigo) {
        this.codigoTipoIdentTestigo = codigoTipoIdentTestigo;
    }

    public String getCodigoTipoInfractor() {
        return this.codigoTipoInfractor;
    }

    public void setCodigoTipoInfractor(String codigoTipoInfractor) {
        this.codigoTipoInfractor = codigoTipoInfractor;
    }

    public String getCodigoTipoNotifCompa() {
        return this.codigoTipoNotifCompa;
    }

    public void setCodigoTipoNotifCompa(String codigoTipoNotifCompa) {
        this.codigoTipoNotifCompa = codigoTipoNotifCompa;
    }

    public String getCodigoTipoServicio() {
        return this.codigoTipoServicio;
    }

    public void setCodigoTipoServicio(String codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public String getCodigoTipoTransPasajero() {
        return this.codigoTipoTransPasajero;
    }

    public void setCodigoTipoTransPasajero(String codigoTipoTransPasajero) {
        this.codigoTipoTransPasajero = codigoTipoTransPasajero;
    }

    public String getConsecutivoInmovilizacion() {
        return this.consecutivoInmovilizacion;
    }

    public void setConsecutivoInmovilizacion(String consecutivoInmovilizacion) {
        this.consecutivoInmovilizacion = consecutivoInmovilizacion;
    }

    public String getDireccionBasicaInfraccion() {
        return this.direccionBasicaInfraccion;
    }

    public void setDireccionBasicaInfraccion(String direccionBasicaInfraccion) {
        this.direccionBasicaInfraccion = direccionBasicaInfraccion;
    }

    public String getDireccionBasicaInfractor() {
        return this.direccionBasicaInfractor;
    }

    public void setDireccionBasicaInfractor(String direccionBasicaInfractor) {
        this.direccionBasicaInfractor = direccionBasicaInfractor;
    }

    public String getDireccionBasicaPatio() {
        return this.direccionBasicaPatio;
    }

    public void setDireccionBasicaPatio(String direccionBasicaPatio) {
        this.direccionBasicaPatio = direccionBasicaPatio;
    }

    public String getDireccionBasicaTestigo() {
        return this.direccionBasicaTestigo;
    }

    public void setDireccionBasicaTestigo(String direccionBasicaTestigo) {
        this.direccionBasicaTestigo = direccionBasicaTestigo;
    }

    public Short getEdadInfractor() {
        return this.edadInfractor;
    }

    public void setEdadInfractor(Short edadInfractor) {
        this.edadInfractor = edadInfractor;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoEstadoComparendo() {
        return this.codigoEstadoComparendo;
    }

    public void setCodigoEstadoComparendo(String codigoEstadoComparendo) {
        this.codigoEstadoComparendo = codigoEstadoComparendo;
    }

    public Short getEstadoLectura() {
        return this.estadoLectura;
    }

    public void setEstadoLectura(Short estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public String getEvidencia1CodigoTipo() {
        return this.evidencia1CodigoTipo;
    }

    public void setEvidencia1CodigoTipo(String evidencia1CodigoTipo) {
        this.evidencia1CodigoTipo = evidencia1CodigoTipo;
    }

    public String getEvidencia1Nombre() {
        return this.evidencia1Nombre;
    }

    public void setEvidencia1Nombre(String evidencia1Nombre) {
        this.evidencia1Nombre = evidencia1Nombre;
    }

    public String getEvidencia1Url() {
        return this.evidencia1Url;
    }

    public void setEvidencia1Url(String evidencia1Url) {
        this.evidencia1Url = evidencia1Url;
    }

    public String getEvidencia2CodigoTipo() {
        return this.evidencia2CodigoTipo;
    }

    public void setEvidencia2CodigoTipo(String evidencia2CodigoTipo) {
        this.evidencia2CodigoTipo = evidencia2CodigoTipo;
    }

    public String getEvidencia2Nombre() {
        return this.evidencia2Nombre;
    }

    public void setEvidencia2Nombre(String evidencia2Nombre) {
        this.evidencia2Nombre = evidencia2Nombre;
    }

    public String getEvidencia2Url() {
        return this.evidencia2Url;
    }

    public void setEvidencia2Url(String evidencia2Url) {
        this.evidencia2Url = evidencia2Url;
    }

    public Boolean getExisteFugaInfractor() {
        return this.existeFugaInfractor;
    }

    public void setExisteFugaInfractor(Boolean existeFugaInfractor) {
        this.existeFugaInfractor = existeFugaInfractor;
    }

    public Date getFechaExpedLicenConduInfra() {
        return this.fechaExpedLicenConduInfra;
    }

    public void setFechaExpedLicenConduInfra(Date fechaExpedLicenConduInfra) {
        this.fechaExpedLicenConduInfra = fechaExpedLicenConduInfra;
    }

    public Date getFechaHoraRecibido() {
        return this.fechaHoraRecibido;
    }

    public void setFechaHoraRecibido(Date fechaHoraRecibido) {
        this.fechaHoraRecibido = fechaHoraRecibido;
    }

    public Date getFechaInfraccion() {
        return this.fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
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

    public Date getFechaVenciLicenConduInfra() {
        return this.fechaVenciLicenConduInfra;
    }

    public void setFechaVenciLicenConduInfra(Date fechaVenciLicenConduInfra) {
        this.fechaVenciLicenConduInfra = fechaVenciLicenConduInfra;
    }

    public Integer getGradoAlcoholemia() {
        return this.gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(Integer gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public Date getHoraInfraccion() {
        return this.horaInfraccion;
    }

    public void setHoraInfraccion(Date horaInfraccion) {
        this.horaInfraccion = horaInfraccion;
    }

    public String getIdentificacionVehiculo() {
        return this.identificacionVehiculo;
    }

    public void setIdentificacionVehiculo(String identificacionVehiculo) {
        this.identificacionVehiculo = identificacionVehiculo;
    }

    public Boolean getInmoviliza() {
        return this.inmoviliza;
    }

    public void setInmoviliza(Boolean inmoviliza) {
        this.inmoviliza = inmoviliza;
    }

    public Boolean getNiegaPruebaAlcoholemia() {
        return this.niegaPruebaAlcoholemia;
    }

    public void setNiegaPruebaAlcoholemia(Boolean niegaPruebaAlcoholemia) {
        this.niegaPruebaAlcoholemia = niegaPruebaAlcoholemia;
    }

    public String getNombrePatio() {
        return this.nombrePatio;
    }

    public void setNombrePatio(String nombrePatio) {
        this.nombrePatio = nombrePatio;
    }

    public String getNombre1Agente() {
        return this.nombre1Agente;
    }

    public void setNombre1Agente(String nombre1Agente) {
        this.nombre1Agente = nombre1Agente;
    }

    public String getNombre1Infractor() {
        return this.nombre1Infractor;
    }

    public void setNombre1Infractor(String nombre1Infractor) {
        this.nombre1Infractor = nombre1Infractor;
    }

    public String getNombre1Propietario() {
        return this.nombre1Propietario;
    }

    public void setNombre1Propietario(String nombre1Propietario) {
        this.nombre1Propietario = nombre1Propietario;
    }

    public String getNombre1Testigo() {
        return this.nombre1Testigo;
    }

    public void setNombre1Testigo(String nombre1Testigo) {
        this.nombre1Testigo = nombre1Testigo;
    }

    public String getNombre2Agente() {
        return this.nombre2Agente;
    }

    public void setNombre2Agente(String nombre2Agente) {
        this.nombre2Agente = nombre2Agente;
    }

    public String getNombre2Infractor() {
        return this.nombre2Infractor;
    }

    public void setNombre2Infractor(String nombre2Infractor) {
        this.nombre2Infractor = nombre2Infractor;
    }

    public String getNombre2Propietario() {
        return this.nombre2Propietario;
    }

    public void setNombre2Propietario(String nombre2Propietario) {
        this.nombre2Propietario = nombre2Propietario;
    }

    public String getNombre2Testigo() {
        return this.nombre2Testigo;
    }

    public void setNombre2Testigo(String nombre2Testigo) {
        this.nombre2Testigo = nombre2Testigo;
    }

    public String getNumeroCitacion() {
        return this.numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public Integer getNumeroGrua() {
        return this.numeroGrua;
    }

    public void setNumeroGrua(Integer numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getNumeroIdentPropietario() {
        return this.numeroIdentPropietario;
    }

    public void setNumeroIdentPropietario(String numeroIdentPropietario) {
        this.numeroIdentPropietario = numeroIdentPropietario;
    }

    public String getNumeroIdentTestigo() {
        return this.numeroIdentTestigo;
    }

    public void setNumeroIdentTestigo(String numeroIdentTestigo) {
        this.numeroIdentTestigo = numeroIdentTestigo;
    }

    public String getNumeroIdentificacionAgente() {
        return this.numeroIdentificacionAgente;
    }

    public void setNumeroIdentificacionAgente(String numeroIdentificacionAgente) {
        this.numeroIdentificacionAgente = numeroIdentificacionAgente;
    }

    public String getNumeroIdentificacionEmpresa() {
        return this.numeroIdentificacionEmpresa;
    }

    public void setNumeroIdentificacionEmpresa(String numeroIdentificacionEmpresa) {
        this.numeroIdentificacionEmpresa = numeroIdentificacionEmpresa;
    }

    public String getNumeroIdentificacionInfractor() {
        return this.numeroIdentificacionInfractor;
    }

    public void setNumeroIdentificacionInfractor(String numeroIdentificacionInfractor) {
        this.numeroIdentificacionInfractor = numeroIdentificacionInfractor;
    }

    public String getNumeroLicencia() {
        return this.numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getNumeroLicenciaTransito() {
        return this.numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public Integer getNumeroPatio() {
        return this.numeroPatio;
    }

    public void setNumeroPatio(Integer numeroPatio) {
        this.numeroPatio = numeroPatio;
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

    public String getNumeroTarjetaOperacion() {
        return this.numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(String numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public String getObservacionesAgente() {
        return this.observacionesAgente;
    }

    public void setObservacionesAgente(String observacionesAgente) {
        this.observacionesAgente = observacionesAgente;
    }

    public String getPlacaAgente() {
        return this.placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public String getPlacaGrua() {
        return this.placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getRazonSocialEmpresa() {
        return this.razonSocialEmpresa;
    }

    public void setRazonSocialEmpresa(String razonSocialEmpresa) {
        this.razonSocialEmpresa = razonSocialEmpresa;
    }

    public String getRazonSocialInfractor() {
        return this.razonSocialInfractor;
    }

    public void setRazonSocialInfractor(String razonSocialInfractor) {
        this.razonSocialInfractor = razonSocialInfractor;
    }

    public String getRazonSocialPropietario() {
        return this.razonSocialPropietario;
    }

    public void setRazonSocialPropietario(String razonSocialPropietario) {
        this.razonSocialPropietario = razonSocialPropietario;
    }

    public String getTelefonoFijoInfractor() {
        return this.telefonoFijoInfractor;
    }

    public void setTelefonoFijoInfractor(String telefonoFijoInfractor) {
        this.telefonoFijoInfractor = telefonoFijoInfractor;
    }

    public String getTelefonoMovilInfractor() {
        return this.telefonoMovilInfractor;
    }

    public void setTelefonoMovilInfractor(String telefonoMovilInfractor) {
        this.telefonoMovilInfractor = telefonoMovilInfractor;
    }

    public String getTelefonoMovilTestigo() {
        return this.telefonoMovilTestigo;
    }

    public void setTelefonoMovilTestigo(String telefonoMovilTestigo) {
        this.telefonoMovilTestigo = telefonoMovilTestigo;
    }

    public BigDecimal getValorConcepto() {
        return this.valorConcepto;
    }

    public void setValorConcepto(BigDecimal valorConcepto) {
        this.valorConcepto = valorConcepto;
    }

    public Integer getValorGradoAlcoholemia() {
        return this.valorGradoAlcoholemia;
    }

    public void setValorGradoAlcoholemia(Integer valorGradoAlcoholemia) {
        this.valorGradoAlcoholemia = valorGradoAlcoholemia;
    }

    public BigDecimal getVelocidadVehiculo() {
        return this.velocidadVehiculo;
    }

    public void setVelocidadVehiculo(BigDecimal velocidadVehiculo) {
        this.velocidadVehiculo = velocidadVehiculo;
    }

    public Long getIdFacturaAxis() {
        return this.idFacturaAxis;
    }

    public void setIdFacturaAxis(Long idFacturaAxis) {
        this.idFacturaAxis = idFacturaAxis;
    }

    public BigDecimal getLongitudDireccionInfraccion() {
        return this.longitudDireccionInfraccion;
    }

    public void setLongitudDireccionInfraccion(BigDecimal longitudDireccionInfraccion) {
        this.longitudDireccionInfraccion = longitudDireccionInfraccion;
    }

    public BigDecimal getLatitudDireccionInfraccion() {
        return this.latitudDireccionInfraccion;
    }

    public void setLatitudDireccionInfraccion(BigDecimal latitudDireccionInfraccion) {
        this.latitudDireccionInfraccion = latitudDireccionInfraccion;
    }

    public String getNombreLocalidadInfraccion() {
        return this.nombreLocalidadInfraccion;
    }

    public void setNombreLocalidadInfraccion(String nombreLocalidadInfraccion) {
        this.nombreLocalidadInfraccion = nombreLocalidadInfraccion;
    }

    public String getSecuenciaUnica() {
        return secuenciaUnica;
    }

    public void setSecuenciaUnica(String secuenciaUnica) {
        this.secuenciaUnica = secuenciaUnica;
    }

}
