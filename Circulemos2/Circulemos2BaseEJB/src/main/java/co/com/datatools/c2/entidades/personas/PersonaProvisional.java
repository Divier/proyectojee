package co.com.datatools.c2.entidades.personas;

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

import co.com.datatools.c2.entidades.comun.DireccionProvisional;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "persona_provisional")
@NamedQueries({ @NamedQuery(name = "PersonaProvisional.findAll", query = "SELECT p FROM PersonaProvisional p") })
public class PersonaProvisional implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_provisional")
    private Long id;

    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoIdentificacionPersona tipoIdentificacion;

    @Basic(optional = false)
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @JoinColumn(name = "id_municipio_expedicion_docum", referencedColumnName = "id_municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipioExpedicionDocumento;

    @Column(name = "fecha_expedicion_documento")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionDocumento;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "numero_telefonico")
    private String numeroTelefonico;

    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoCivil estadoCivil;

    // TODO FM convertir a una enumeracion
    @Column(name = "genero")
    private Character genero;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "nombre1")
    private String nombre1;

    @Column(name = "nombre2")
    private String nombre2;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "numero_celular")
    private String numeroCelular;

    @Column(name = "nombre_empresa_labora")
    private String nombreEmpresaLabora;

    @Column(name = "notif_direc_empre_labor")
    private Boolean notificaDirEmpresa;

    @Column(name = "cargo_empresa_labora")
    private String cargoEmpresaLabora;

    @Column(name = "telefono_empresa_labora")
    private String telefonoEmpresaLabora;

    @Column(name = "fax_empresa_labora")
    private String faxEmpresaLabora;

    @JoinColumn(name = "id_direccion_solicitud", referencedColumnName = "id_direccion_provisional")
    @ManyToOne(fetch = FetchType.LAZY)
    private DireccionProvisional direccionSolicitud;

    @JoinColumn(name = "codigo_nivel_educativo", referencedColumnName = "codigo_nivel_educativo")
    @ManyToOne(fetch = FetchType.LAZY)
    private NivelEducativo nivelEducativo;

    @JoinColumn(name = "codigo_tipo_vivienda", referencedColumnName = "codigo_tipo_vivienda")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoVivienda tipoVivienda;

    public PersonaProvisional() {
    }

    public PersonaProvisional(Long idPersonaProvisional) {
        this.setId(idPersonaProvisional);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Municipio getMunicipioExpedicionDocumento() {
        return municipioExpedicionDocumento;
    }

    public void setMunicipioExpedicionDocumento(Municipio municipioExpedicionDocumento) {
        this.municipioExpedicionDocumento = municipioExpedicionDocumento;
    }

    public Date getFechaExpedicionDocumento() {
        return fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getNombreEmpresaLabora() {
        return nombreEmpresaLabora;
    }

    public void setNombreEmpresaLabora(String nombreEmpresaLabora) {
        this.nombreEmpresaLabora = nombreEmpresaLabora;
    }

    public Boolean getNotificaDirEmpresa() {
        return notificaDirEmpresa;
    }

    public void setNotificaDirEmpresa(Boolean notificaDirEmpresa) {
        this.notificaDirEmpresa = notificaDirEmpresa;
    }

    public String getCargoEmpresaLabora() {
        return cargoEmpresaLabora;
    }

    public void setCargoEmpresaLabora(String cargoEmpresaLabora) {
        this.cargoEmpresaLabora = cargoEmpresaLabora;
    }

    public String getTelefonoEmpresaLabora() {
        return telefonoEmpresaLabora;
    }

    public void setTelefonoEmpresaLabora(String telefonoEmpresaLabora) {
        this.telefonoEmpresaLabora = telefonoEmpresaLabora;
    }

    public String getFaxEmpresaLabora() {
        return faxEmpresaLabora;
    }

    public void setFaxEmpresaLabora(String faxEmpresaLabora) {
        this.faxEmpresaLabora = faxEmpresaLabora;
    }

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public DireccionProvisional getDireccionSolicitud() {
        return direccionSolicitud;
    }

    public void setDireccionSolicitud(DireccionProvisional direccionSolicitud) {
        this.direccionSolicitud = direccionSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersonaProvisional)) {
            return false;
        }
        PersonaProvisional other = (PersonaProvisional) object;
        if ((this.getId() == null && other.getId() != null)
                || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.datatools.c2.entidades.PersonaProvisional[ idPersonaProvisional=" + getId() + " ]";
    }

}
