package co.com.datatools.c2.entidades;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "procesa_comparendo_persona")
@NamedQueries({
        @NamedQuery(name = "ProcesaComparendoPersona.findAll", query = "SELECT p FROM ProcesaComparendoPersona p") })
public class ProcesaComparendoPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comparendo_persona")
    private Long id;

    @Column(name = "codigo_tipo_persona_comparendo")
    private Integer codigoTipoPersonaComparendo;

    @Column(name = "id_tipo_identificacion")
    private Integer idTipoIdentificacion;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "digito_verificacion_nit")
    private Short digitoVerificacionNit;

    @Column(name = "numero_licencia")
    private String numeroLicencia;

    @Column(name = "fecha_expedicion_licen_condu")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionLicenCondu;

    @Column(name = "fecha_vencimiento_licen_condu")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoLicenCondu;

    @Column(name = "id_categoria_licencia_condu")
    private Integer idCategoriaLicenciaCondu;

    @Column(name = "codigo_organismo_licencia")
    private Integer codigoOrganismoLicencia;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "nombre1")
    private String nombre1;

    @Column(name = "nombre2")
    private String nombre2;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "edad")
    private Short edad;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @JoinColumn(name = "id_procesa_direccion", referencedColumnName = "id_procesa_direccion")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProcesaDireccion procesaDireccion;

    @JoinColumn(name = "id_procesamiento", referencedColumnName = "id_procesamiento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProcesaComparendo procesaComparendo;

    public ProcesaComparendoPersona() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoPersonaComparendo() {
        return codigoTipoPersonaComparendo;
    }

    public void setCodigoTipoPersonaComparendo(Integer codigoTipoPersonaComparendo) {
        this.codigoTipoPersonaComparendo = codigoTipoPersonaComparendo;
    }

    public Integer getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Short getDigitoVerificacionNit() {
        return digitoVerificacionNit;
    }

    public void setDigitoVerificacionNit(Short digitoVerificacionNit) {
        this.digitoVerificacionNit = digitoVerificacionNit;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public Date getFechaExpedicionLicenCondu() {
        return fechaExpedicionLicenCondu;
    }

    public void setFechaExpedicionLicenCondu(Date fechaExpedicionLicenCondu) {
        this.fechaExpedicionLicenCondu = fechaExpedicionLicenCondu;
    }

    public Date getFechaVencimientoLicenCondu() {
        return fechaVencimientoLicenCondu;
    }

    public void setFechaVencimientoLicenCondu(Date fechaVencimientoLicenCondu) {
        this.fechaVencimientoLicenCondu = fechaVencimientoLicenCondu;
    }

    public Integer getIdCategoriaLicenciaCondu() {
        return idCategoriaLicenciaCondu;
    }

    public void setIdCategoriaLicenciaCondu(Integer idCategoriaLicenciaCondu) {
        this.idCategoriaLicenciaCondu = idCategoriaLicenciaCondu;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Short getEdad() {
        return edad;
    }

    public void setEdad(Short edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProcesaDireccion getProcesaDireccion() {
        return procesaDireccion;
    }

    public void setProcesaDireccion(ProcesaDireccion procesaDireccion) {
        this.procesaDireccion = procesaDireccion;
    }

    public ProcesaComparendo getProcesaComparendo() {
        return procesaComparendo;
    }

    public void setProcesaComparendo(ProcesaComparendo procesaComparendo) {
        this.procesaComparendo = procesaComparendo;
    }

    public Integer getCodigoOrganismoLicencia() {
        return codigoOrganismoLicencia;
    }

    public void setCodigoOrganismoLicencia(Integer codigoOrganismoLicencia) {
        this.codigoOrganismoLicencia = codigoOrganismoLicencia;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

}
