package co.com.datatools.c2.dto.personas;

import java.util.Date;

import co.com.datatools.c2.dto.comun.DireccionProvisionalDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class PersonaProvisionalDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TipoIdentificacionPersonaDTO tipoIdentificacion;
    private String numeroIdentificacion;
    private OrganismoTransitoDTO organismoTransito;
    private Date fechaRegistro;
    private MunicipioDTO municipioExpedicionDocumento;
    private Date fechaExpedicionDocumento;
    private Date fechaNacimiento;
    private String numeroTelefonico;
    private EstadoCivilDTO estadoCivil;
    private Character genero;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String correoElectronico;
    private String numeroCelular;
    private String nombreEmpresaLabora;
    private Boolean notificaDirEmpresa;
    private String cargoEmpresaLabora;
    private String telefonoEmpresaLabora;
    private String faxEmpresaLabora;
    private DireccionProvisionalDTO direccionSolicitud;
    private NivelEducativoDTO nivelEducativo;
    private TipoViviendaDTO tipoVivienda;

    // --- Constructor
    public PersonaProvisionalDTO() {
    }

    public PersonaProvisionalDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public String getNumeroTelefonico() {
        return this.numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
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

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroCelular() {
        return this.numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
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

    public String getTelefonoEmpresaLabora() {
        return this.telefonoEmpresaLabora;
    }

    public void setTelefonoEmpresaLabora(String telefonoEmpresaLabora) {
        this.telefonoEmpresaLabora = telefonoEmpresaLabora;
    }

    public String getFaxEmpresaLabora() {
        return this.faxEmpresaLabora;
    }

    public void setFaxEmpresaLabora(String faxEmpresaLabora) {
        this.faxEmpresaLabora = faxEmpresaLabora;
    }

    public DireccionProvisionalDTO getDireccionSolicitud() {
        return this.direccionSolicitud;
    }

    public void setDireccionSolicitud(DireccionProvisionalDTO direccionSolicitud) {
        this.direccionSolicitud = direccionSolicitud;
    }

    public NivelEducativoDTO getNivelEducativo() {
        return this.nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativoDTO nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public TipoViviendaDTO getTipoVivienda() {
        return this.tipoVivienda;
    }

    public void setTipoVivienda(TipoViviendaDTO tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

}