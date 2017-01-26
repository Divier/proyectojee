package co.com.datatools.c2.entidades.multas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the it_multa database table.
 * 
 */
@Entity
@Table(name = "it_multa")
@NamedQueries({ @NamedQuery(name = "ItMulta.findAll", query = "SELECT m FROM ItMulta m"), @NamedQuery(
        name = "ItMulta.findByCodOrgAndEstLec",
        query = "SELECT m FROM ItMulta m WHERE m.codigoOrganismoTransito = :pCodOrg AND m.estadoLectura = :pEstLec") })
public class ItMulta implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta las multas con un determinado condigo de organismo y estado de lectura<br>
     * <p>
     * Parametros:<br>
     * <li><b>pCodOrg</b> Codigo del organismo de transito</li>
     * <li><b>pEstLec</b> estado de lectura de la multa o comparendo</li>
     * </p>
     * <br>
     * Consulta:<br>
     * SELECT m FROM ItMulta m WHERE m.codigoOrganismoTransito = :pCodOrg AND m.estadoLectura = :pEstLec
     * 
     * @author luis.forero(2016-05-05)
     */
    public static final String SQ_FIND_BY_COD_ORG_AND_EST_LECT = "ItMulta.findByCodOrgAndEstLec";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa")
    private Long idMulta;

    @Column(name = "apellido1_agente")
    private String apellido1Agente;

    @Column(name = "apellido1_infractor")
    private String apellido1Infractor;

    @Column(name = "apellido1_propietario")
    private String apellido1Propietario;

    @Column(name = "apellido1_testigo")
    private String apellido1Testigo;

    @Column(name = "apellido2_agente")
    private String apellido2Agente;

    @Column(name = "apellido2_infractor")
    private String apellido2Infractor;

    @Column(name = "apellido2_propietario")
    private String apellido2Propietario;

    @Column(name = "apellido2_testigo")
    private String apellido2Testigo;

    @Column(name = "codigo_categ_licen_condu_infr")
    private String codigoCategLicenConduInfr;

    @Column(name = "codigo_clase_vehiculo")
    private String codigoClaseVehiculo;

    @Column(name = "codigo_departamento_infraccion")
    private String codigoDepartamentoInfraccion;

    @Column(name = "codigo_departamento_infractor")
    private String codigoDepartamentoInfractor;

    @Column(name = "codigo_departamento_patio")
    private String codigoDepartamentoPatio;

    @Column(name = "codigo_departamento_testigo")
    private String codigoDepartamentoTestigo;

    @Column(name = "codigo_infraccion")
    private String codigoInfraccion;

    @Column(name = "codigo_localidad_infraccion")
    private String codigoLocalidadInfraccion;

    @Column(name = "codigo_localidad_infractor")
    private String codigoLocalidadInfractor;

    @Column(name = "codigo_localidad_patio")
    private String codigoLocalidadPatio;

    @Column(name = "codigo_localidad_testigo")
    private String codigoLocalidadTestigo;

    @Column(name = "codigo_medio_imposicion")
    private String codigoMedioImposicion;

    @Column(name = "codigo_modalidad")
    private String codigoModalidad;

    @Column(name = "codigo_municipio_infraccion")
    private String codigoMunicipioInfraccion;

    @Column(name = "codigo_municipio_infractor")
    private String codigoMunicipioInfractor;

    @Column(name = "codigo_municipio_patio")
    private String codigoMunicipioPatio;

    @Column(name = "codigo_municipio_testigo")
    private String codigoMunicipioTestigo;

    @Column(name = "codigo_organ_licen_condu_infr")
    private Integer codigoOrganLicenConduInfr;

    @Column(name = "codigo_organismo_licen_trans")
    private Integer codigoOrganismoLicenTrans;

    @Column(name = "codigo_organismo_matri_vehic")
    private Integer codigoOrganismoMatriVehic;

    @Column(name = "codigo_organismo_transito")
    private Integer codigoOrganismoTransito;

    @Column(name = "codigo_origen_comparendo")
    private String codigoOrigenComparendo;

    @Column(name = "codigo_pais_direc_infractor")
    private String codigoPaisDirecInfractor;

    @Column(name = "codigo_pais_direc_patio")
    private String codigoPaisDirecPatio;

    @Column(name = "codigo_pais_direc_testigo")
    private String codigoPaisDirecTestigo;

    @Column(name = "codigo_pais_infraccion")
    private String codigoPaisInfraccion;

    @Column(name = "codigo_radio_accion")
    private String codigoRadioAccion;

    @Column(name = "codigo_tipo_agente_impositor")
    private String codigoTipoAgenteImpositor;

    @Column(name = "codigo_tipo_ident_agente")
    private String codigoTipoIdentAgente;

    @Column(name = "codigo_tipo_ident_empresa")
    private String codigoTipoIdentEmpresa;

    @Column(name = "codigo_tipo_ident_infractor")
    private String codigoTipoIdentInfractor;

    @Column(name = "codigo_tipo_ident_propietario")
    private String codigoTipoIdentPropietario;

    @Column(name = "codigo_tipo_ident_testigo")
    private String codigoTipoIdentTestigo;

    @Column(name = "codigo_tipo_infractor")
    private String codigoTipoInfractor;

    @Column(name = "codigo_tipo_notif_compa")
    private String codigoTipoNotifCompa;

    @Column(name = "codigo_tipo_servicio")
    private String codigoTipoServicio;

    @Column(name = "codigo_tipo_trans_pasajero")
    private String codigoTipoTransPasajero;

    @Column(name = "consecutivo_inmovilizacion")
    private String consecutivoInmovilizacion;

    @Column(name = "direccion_basica_infraccion")
    private String direccionBasicaInfraccion;

    @Column(name = "direccion_basica_infractor")
    private String direccionBasicaInfractor;

    @Column(name = "direccion_basica_patio")
    private String direccionBasicaPatio;

    @Column(name = "direccion_basica_testigo")
    private String direccionBasicaTestigo;

    @Column(name = "edad_infractor")
    private Short edadInfractor;

    @Column(name = "email")
    private String email;

    @Column(name = "codigo_estado_comparendo")
    private String codigoEstadoComparendo;

    @Column(name = "estado_lectura")
    private Short estadoLectura;

    @Column(name = "evidencia1_codigo_tipo")
    private String evidencia1CodigoTipo;

    @Column(name = "evidencia1_nombre")
    private String evidencia1Nombre;

    @Column(name = "evidencia1_url")
    private String evidencia1Url;

    @Column(name = "evidencia2_codigo_tipo")
    private String evidencia2CodigoTipo;

    @Column(name = "evidencia2_nombre")
    private String evidencia2Nombre;

    @Column(name = "evidencia2_url")
    private String evidencia2Url;

    @Column(name = "existe_fuga_infractor")
    private Boolean existeFugaInfractor;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_exped_licen_condu_infra")
    private Date fechaExpedLicenConduInfra;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_recibido")
    private Date fechaHoraRecibido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_infraccion")
    private Date fechaInfraccion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_prueba_alcoholemia")
    private Date fechaPruebaAlcoholemia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_venci_licen_condu_infra")
    private Date fechaVenciLicenConduInfra;

    @Column(name = "grado_alcoholemia")
    private Integer gradoAlcoholemia;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_infraccion")
    private Date horaInfraccion;

    @Column(name = "identificacion_vehiculo")
    private String identificacionVehiculo;

    @Column(name = "inmoviliza")
    private Boolean inmoviliza;

    @Column(name = "niega_prueba_alcoholemia")
    private Boolean niegaPruebaAlcoholemia;

    @Column(name = "nombre_patio")
    private String nombrePatio;

    @Column(name = "nombre1_agente")
    private String nombre1Agente;

    @Column(name = "nombre1_infractor")
    private String nombre1Infractor;

    @Column(name = "nombre1_propietario")
    private String nombre1Propietario;

    @Column(name = "nombre1_testigo")
    private String nombre1Testigo;

    @Column(name = "nombre2_agente")
    private String nombre2Agente;

    @Column(name = "nombre2_infractor")
    private String nombre2Infractor;

    @Column(name = "nombre2_propietario")
    private String nombre2Propietario;

    @Column(name = "nombre2_testigo")
    private String nombre2Testigo;

    @Column(name = "numero_citacion")
    private String numeroCitacion;

    @Column(name = "numero_grua")
    private Integer numeroGrua;

    @Column(name = "numero_ident_propietario")
    private String numeroIdentPropietario;

    @Column(name = "numero_ident_testigo")
    private String numeroIdentTestigo;

    @Column(name = "numero_identificacion_agente")
    private String numeroIdentificacionAgente;

    @Column(name = "numero_identificacion_empresa")
    private String numeroIdentificacionEmpresa;

    @Column(name = "numero_identificacion_infractor")
    private String numeroIdentificacionInfractor;

    @Column(name = "numero_licencia")
    private String numeroLicencia;

    @Column(name = "numero_licencia_transito")
    private String numeroLicenciaTransito;

    @Column(name = "numero_patio")
    private Integer numeroPatio;

    @Column(name = "numero_prueba_alcoholemia")
    private String numeroPruebaAlcoholemia;

    @Column(name = "numero_reincidencia")
    private Integer numeroReincidencia;

    @Column(name = "numero_tarjeta_operacion")
    private String numeroTarjetaOperacion;

    @Column(name = "observaciones_agente")
    private String observacionesAgente;

    @Column(name = "placa_agente")
    private String placaAgente;

    @Column(name = "placa_grua")
    private String placaGrua;

    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    @Column(name = "razon_social_empresa")
    private String razonSocialEmpresa;

    @Column(name = "razon_social_infractor")
    private String razonSocialInfractor;

    @Column(name = "razon_social_propietario")
    private String razonSocialPropietario;

    @Column(name = "telefono_fijo_infractor")
    private String telefonoFijoInfractor;

    @Column(name = "telefono_movil_infractor")
    private String telefonoMovilInfractor;

    @Column(name = "telefono_movil_testigo")
    private String telefonoMovilTestigo;

    @Column(name = "valor_concepto")
    private BigDecimal valorConcepto;

    @Column(name = "valor_grado_alcoholemia")
    private Integer valorGradoAlcoholemia;

    @Column(name = "velocidad_vehiculo")
    private BigDecimal velocidadVehiculo;

    @Column(name = "id_factura_axis")
    private Long idFacturaAxis;

    @Column(name = "secuencia_unica")
    private String secuenciaUnica;

    @Column(name = "longitud_direccion_infraccion")
    private BigDecimal longitudDireccionInfraccion;

    @Column(name = "latitud_direccion_infraccion")
    private BigDecimal latitudDireccionInfraccion;

    @Column(name = "nombre_localidad_infraccion")
    private String nombreLocalidadInfraccion;

    public ItMulta() {
    }

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

    public String getCodigoEstadoComparendo() {
        return codigoEstadoComparendo;
    }

    public void setCodigoEstadoComparendo(String codigoEstadoComparendo) {
        this.codigoEstadoComparendo = codigoEstadoComparendo;
    }

    public Long getIdFacturaAxis() {
        return idFacturaAxis;
    }

    public void setIdFacturaAxis(Long idFacturaAxis) {
        this.idFacturaAxis = idFacturaAxis;
    }

    public BigDecimal getLongitudDireccionInfraccion() {
        return longitudDireccionInfraccion;
    }

    public void setLongitudDireccionInfraccion(BigDecimal longitudDireccionInfraccion) {
        this.longitudDireccionInfraccion = longitudDireccionInfraccion;
    }

    public BigDecimal getLatitudDireccionInfraccion() {
        return latitudDireccionInfraccion;
    }

    public void setLatitudDireccionInfraccion(BigDecimal latitudDireccionInfraccion) {
        this.latitudDireccionInfraccion = latitudDireccionInfraccion;
    }

    public String getNombreLocalidadInfraccion() {
        return nombreLocalidadInfraccion;
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