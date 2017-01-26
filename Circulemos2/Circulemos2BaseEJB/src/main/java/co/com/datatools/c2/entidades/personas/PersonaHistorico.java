package co.com.datatools.c2.entidades.personas;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersonaHistorico;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersonaHistorico;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersonaHistorico;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "persona_historico")
@NamedQueries({ @NamedQuery(name = "PersonaHistorico.findAll", query = "SELECT p FROM PersonaHistorico p") })
public class PersonaHistorico implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_historico")
    private Long id;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoIdentificacionPersona tipoIdentificacion;

    @Basic(optional = false)
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @ManyToOne
    @JoinColumn(name = "codigo_fuente_informacion")
    private TipoFuenteInformacion fuenteInformacion;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @Basic(optional = false)
    @Column(name = "fecha_ultima_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaActualizacion;

    @JoinColumn(name = "id_municipio_expedicion_docum", referencedColumnName = "id_municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipioExpedicionDocumento;

    @Column(name = "fecha_expedicion_documento")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionDocumento;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "fecha_fallecimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaFallecimiento;

    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoCivil estadoCivil;

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

    @Column(name = "nombre_empresa_labora")
    private String nombreEmpresaLabora;

    @Column(name = "notif_direc_empre_labor")
    private Boolean notificaDirEmpresa;

    @Column(name = "cargo_empresa_labora")
    private String cargoEmpresaLabora;

    // @Lob
    @Column(name = "huella_digital")
    private byte[] huellaDigital;

    @Column(name = "ruta_foto")
    private String rutaFoto;

    @JoinColumn(name = "codigo_tipo_vivienda", referencedColumnName = "codigo_tipo_vivienda")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoVivienda tipoVivienda;

    @JoinColumn(name = "codigo_nivel_educativo", referencedColumnName = "codigo_nivel_educativo")
    @ManyToOne(fetch = FetchType.LAZY)
    private NivelEducativo nivelEducativo;

    @Column(name = "digito_verificacion")
    private Short digitoVerificacion;

    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Column(name = "sigla")
    private String sigla;

    @JoinColumn(name = "id_tipo_sociedad", referencedColumnName = "id_tipo_sociedad")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoSociedad tipoSociedad;

    @JoinColumn(name = "id_clase_actividad_economica", referencedColumnName = "id_clase_actividad_economica")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClaseActividadEconomica claseActividadEconomica;

    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "id_documento")
    private Long idDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_registro")
    private UsuarioPersona usuarioRegistro;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "perso_histo_corre_perso_histo",
            joinColumns = { @JoinColumn(name = "id_persona_historico") },
            inverseJoinColumns = { @JoinColumn(name = "id_correo_persona_historico") })
    private List<CorreoPersonaHistorico> correoPersonas;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "perso_histo_telef_perso_histo",
            joinColumns = { @JoinColumn(name = "id_persona_historico") },
            inverseJoinColumns = { @JoinColumn(name = "id_telefono_persona_historico") })
    private List<TelefonoPersonaHistorico> telefonoPersonas;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "perso_histo_direc_perso_histo",
            joinColumns = { @JoinColumn(name = "id_persona_historico") },
            inverseJoinColumns = { @JoinColumn(name = "id_direccion_persona_historico") })
    private List<DireccionPersonaHistorico> direccionPersonas;

    public PersonaHistorico() {
    }

    public PersonaHistorico(Long id) {
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TipoFuenteInformacion getFuenteInformacion() {
        return fuenteInformacion;
    }

    public void setFuenteInformacion(TipoFuenteInformacion fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
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

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
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

    public byte[] getHuellaDigital() {
        return huellaDigital;
    }

    public void setHuellaDigital(byte[] huellaDigital) {
        this.huellaDigital = huellaDigital;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public Short getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(Short digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoSociedad getTipoSociedad() {
        return tipoSociedad;
    }

    public void setTipoSociedad(TipoSociedad tipoSociedad) {
        this.tipoSociedad = tipoSociedad;
    }

    public ClaseActividadEconomica getClaseActividadEconomica() {
        return claseActividadEconomica;
    }

    public void setClaseActividadEconomica(ClaseActividadEconomica claseActividadEconomica) {
        this.claseActividadEconomica = claseActividadEconomica;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersonaHistorico)) {
            return false;
        }
        PersonaHistorico other = (PersonaHistorico) object;
        if ((this.getId() == null && other.getId() != null)
                || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.datatools.c2.entidades.PersonaHistorico[ id=" + getId() + " ]";
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public List<CorreoPersonaHistorico> getCorreoPersonas() {
        return correoPersonas;
    }

    public void setCorreoPersonas(List<CorreoPersonaHistorico> correoPersonas) {
        this.correoPersonas = correoPersonas;
    }

    public List<TelefonoPersonaHistorico> getTelefonoPersonas() {
        return telefonoPersonas;
    }

    public void setTelefonoPersonas(List<TelefonoPersonaHistorico> telefonoPersonas) {
        this.telefonoPersonas = telefonoPersonas;
    }

    public List<DireccionPersonaHistorico> getDireccionPersonas() {
        return direccionPersonas;
    }

    public void setDireccionPersonas(List<DireccionPersonaHistorico> direccionPersonas) {
        this.direccionPersonas = direccionPersonas;
    }

    public UsuarioPersona getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersona usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

}
