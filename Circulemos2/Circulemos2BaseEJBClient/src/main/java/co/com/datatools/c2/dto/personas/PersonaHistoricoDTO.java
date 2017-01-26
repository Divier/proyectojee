package co.com.datatools.c2.dto.personas;

import java.util.Date;

import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class PersonaHistoricoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private PersonaDTO persona;
    private TipoIdentificacionPersonaDTO tipoIdentificacion;
    private String numeroIdentificacion;
    private TipoFuenteInformacionDTO fuenteInformacion;
    private OrganismoTransitoDTO organismoTransito;
    private Date fechaUltimaActualizacion;
    private MunicipioDTO municipioExpedicionDocumento;
    private Date fechaExpedicionDocumento;
    private Date fechaNacimiento;
    private Date fechaFallecimiento;
    private EstadoCivilDTO estadoCivil;
    private Character genero;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String nombreEmpresaLabora;
    private Boolean notificaDirEmpresa;
    private String cargoEmpresaLabora;
    private byte[] huellaDigital;
    private String rutaFoto;
    private TipoViviendaDTO tipoVivienda;
    private NivelEducativoDTO nivelEducativo;
    private Short digitoVerificacion;
    private String nombreComercial;
    private String sigla;
    private TipoSociedadDTO tipoSociedad;
    private ClaseActividadEconomicaDTO claseActividadEconomica;
    private Date fechaRegistro;
    private Long idDocumento;
    private UsuarioPersonaDTO usuarioRegistro;

    // --- Constructor
    public PersonaHistoricoDTO() {
    }

    public PersonaHistoricoDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionPersonaDTO tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return this.numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public TipoFuenteInformacionDTO getFuenteInformacion() {
        return this.fuenteInformacion;
    }

    public void setFuenteInformacion(TipoFuenteInformacionDTO fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaUltimaActualizacion() {
        return this.fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public MunicipioDTO getMunicipioExpedicionDocumento() {
        return this.municipioExpedicionDocumento;
    }

    public void setMunicipioExpedicionDocumento(MunicipioDTO municipioExpedicionDocumento) {
        this.municipioExpedicionDocumento = municipioExpedicionDocumento;
    }

    public Date getFechaExpedicionDocumento() {
        return this.fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaFallecimiento() {
        return this.fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public EstadoCivilDTO getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilDTO estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Character getGenero() {
        return this.genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return this.nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return this.nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNombreEmpresaLabora() {
        return this.nombreEmpresaLabora;
    }

    public void setNombreEmpresaLabora(String nombreEmpresaLabora) {
        this.nombreEmpresaLabora = nombreEmpresaLabora;
    }

    public Boolean getNotificaDirEmpresa() {
        return this.notificaDirEmpresa;
    }

    public void setNotificaDirEmpresa(Boolean notificaDirEmpresa) {
        this.notificaDirEmpresa = notificaDirEmpresa;
    }

    public String getCargoEmpresaLabora() {
        return this.cargoEmpresaLabora;
    }

    public void setCargoEmpresaLabora(String cargoEmpresaLabora) {
        this.cargoEmpresaLabora = cargoEmpresaLabora;
    }

    public byte[] getHuellaDigital() {
        return this.huellaDigital;
    }

    public void setHuellaDigital(byte[] huellaDigital) {
        this.huellaDigital = huellaDigital;
    }

    public String getRutaFoto() {
        return this.rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public TipoViviendaDTO getTipoVivienda() {
        return this.tipoVivienda;
    }

    public void setTipoVivienda(TipoViviendaDTO tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public NivelEducativoDTO getNivelEducativo() {
        return this.nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativoDTO nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public Short getDigitoVerificacion() {
        return this.digitoVerificacion;
    }

    public void setDigitoVerificacion(Short digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getNombreComercial() {
        return this.nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoSociedadDTO getTipoSociedad() {
        return this.tipoSociedad;
    }

    public void setTipoSociedad(TipoSociedadDTO tipoSociedad) {
        this.tipoSociedad = tipoSociedad;
    }

    public ClaseActividadEconomicaDTO getClaseActividadEconomica() {
        return this.claseActividadEconomica;
    }

    public void setClaseActividadEconomica(ClaseActividadEconomicaDTO claseActividadEconomica) {
        this.claseActividadEconomica = claseActividadEconomica;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public UsuarioPersonaDTO getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersonaDTO usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

}