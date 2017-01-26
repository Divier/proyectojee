package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Basic;
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

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 * @author luis.forero(mod 2016-02-26)
 */
@Entity
@Table(name = "comparendo_persona")
@NamedQueries({ @NamedQuery(name = "ComparendoPersona.findAll", query = "SELECT c FROM ComparendoPersona c"),
        @NamedQuery(
                name = "ComparendoPersona.findByTipoPersComp",
                query = "SELECT c FROM ComparendoPersona c WHERE c.tipoPersonaComparendo.codigo = :pCodTipPerCom AND c.comparendo.cicomparendo = :pCiComp") })
public class ComparendoPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta una persona por si tipo persona comparendo.
     * 
     * SELECT c FROM ComparendoPersona c WHERE c.tipoPersonaComparendo.codigo = :pCodTipPerCom AND c.comparendo.cicomparendo = :pCiComp
     * 
     * @author luis.forero(mod 2016-02-26)
     */
    public static final String SQ_FIND_BY_TIPO_PERSONA_COMP = "ComparendoPersona.findByTipoPersComp";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comparendo_persona")
    private Long id;

    @JoinColumn(name = "cicomparendo", referencedColumnName = "cicomparendo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Comparendo comparendo;

    @JoinColumn(name = "codigo_tipo_persona_comparendo", referencedColumnName = "codigo_tipo_persona_comparendo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPersonaComparendo tipoPersonaComparendo;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;

    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoIdentificacionPersona tipoIdentificacion;

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

    @JoinColumn(name = "id_categoria_licencia_condu", referencedColumnName = "id_categoria_licencia_conduc")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoCategLicenciaConduccion categoriaLicencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_organismo_licencia")
    private OrganismoTransito organismoTransito;

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

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @Column(name = "edad")
    private Short edad;

    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Direccion direccion;

    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "fecha_incio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "id_licencia", referencedColumnName = "id_licencia")
    @ManyToOne(fetch = FetchType.LAZY)
    private LicenciaConduccion licenciaConduccion;

    public ComparendoPersona() {
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

    public TipoPersonaComparendo getTipoPersonaComparendo() {
        return tipoPersonaComparendo;
    }

    public void setTipoPersonaComparendo(TipoPersonaComparendo tipoPersonaComparendo) {
        this.tipoPersonaComparendo = tipoPersonaComparendo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoIdentificacionPersona getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionPersona tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public TipoCategLicenciaConduccion getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(TipoCategLicenciaConduccion categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
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

}
