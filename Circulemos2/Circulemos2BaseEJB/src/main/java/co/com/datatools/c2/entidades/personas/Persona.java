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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "persona")
@NamedQueries({ @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"), @NamedQuery(
        name = "Persona.countUnique",
        query = "SELECT COUNT(p) FROM Persona p WHERE p.organismoTransito.codigoOrganismo = :codOrganismo AND p.numeroIdentificacion = :numId AND p.tipoIdentificacion.id = :idTipoId ") })
public class Persona implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_COUNT_UNIQUE = "Persona.countUnique";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_identificacion")
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

    @Column(name = "nombre_empresa_labora")
    private String nombreEmpresaLabora;

    @Column(name = "notifica_direccion_empre_labor")
    private Boolean notificaDirEmpresa;

    @Column(name = "cargo_empresa_labora")
    private String cargoEmpresaLabora;

    // @Lob
    @Column(name = "huella_digital")
    private byte[] huellaDigital;

    @Column(name = "ruta_foto")
    private String rutaFoto;

    @Column(name = "fecha_foto")
    @Temporal(TemporalType.DATE)
    private Date fechaFoto;

    @JoinColumn(name = "codigo_tipo_vivienda", referencedColumnName = "codigo_tipo_vivienda")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoVivienda tipoVivienda;

    @JoinColumn(name = "codigo_nivel_educativo", referencedColumnName = "codigo_nivel_educativo")
    @ManyToOne(fetch = FetchType.LAZY)
    private NivelEducativo nivelEducativo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private PersonaJuridica personaJuridica;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<DireccionPersona> direccionPersonaList;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<TelefonoPersona> telefonoPersonaList;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<CorreoPersona> correoPersonaList;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<ArchivoPersona> archivoPersonaList;

    public Persona() {
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

    public void setHuellaDigital(byte[] huellaDigital) {
        this.huellaDigital = huellaDigital;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public Date getFechaFoto() {
        return fechaFoto;
    }

    public void setFechaFoto(Date fechaFoto) {
        this.fechaFoto = fechaFoto;
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

    public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public List<DireccionPersona> getDireccionPersonaList() {
        return direccionPersonaList;
    }

    public void setDireccionPersonaList(List<DireccionPersona> direccionPersonaList) {
        this.direccionPersonaList = direccionPersonaList;
    }

    public List<TelefonoPersona> getTelefonoPersonaList() {
        return telefonoPersonaList;
    }

    public void setTelefonoPersonaList(List<TelefonoPersona> telefonoPersonaList) {
        this.telefonoPersonaList = telefonoPersonaList;
    }

    public List<CorreoPersona> getCorreoPersonaList() {
        return correoPersonaList;
    }

    public void setCorreoPersonaList(List<CorreoPersona> correoPersonaList) {
        this.correoPersonaList = correoPersonaList;
    }

    public List<ArchivoPersona> getArchivoPersonaList() {
        return archivoPersonaList;
    }

    public void setArchivoPersonaList(List<ArchivoPersona> archivoPersonaList) {
        this.archivoPersonaList = archivoPersonaList;
    }

}
