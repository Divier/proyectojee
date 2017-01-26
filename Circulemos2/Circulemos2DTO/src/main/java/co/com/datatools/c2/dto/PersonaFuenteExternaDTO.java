package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author luis.forero (mod 2014-11-19)
 * @version 3.0 - Wed Nov 19 11:23:37 COT 2014
 */
public class PersonaFuenteExternaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id; //
    private TipoIdentificacionPersonaDTO tipoIdentificacion; //
    private String numeroIdentificacion;//
    private TipoFuenteInformacionDTO fuenteInformacion; //
    // OrganismoTransito
    private Date fechaUltimaActualizacion; //
    // MUNICIO EXPEDICION DOCUMENTO
    private Date fechaExpedicionDocumento; //
    private Date fechaNacimiento; //
    private Date fechaFallecimiento; //
    private String numeroTelefonico; //
    private EstadoCivilDTO estadoCivil; //
    private Character genero; //
    private String apellido1; //
    private String apellido2; //
    private String nombre1; //
    private String nombre2; //
    private String correoElectronico; //
    private String numeroCelular;//
    private String nombreEmpresaLabora;//
    private Boolean notificaDirEmpresa;//
    private String cargoEmpresaLabora;//
    // FALTA TELEFONO EMPRESA LABORA
    private String faxEmpresaLabora;//
    private byte[] huellaDigital;//
    private byte[] foto;// *** tipo dato
    // FECHA FOTO
    private TipoViviendaDTO tipoVivienda;//
    private NivelEducativoDTO nivelEducativo;//
    private List<DireccionPersonaFuenteExternaDTO> direccionFuenteExternaList;// **TABLA DISTINTA DE ROMPIMIENTO.

    // Atributos mostrados sobre interfaz
    private Integer edad;

    // --- Constructor
    public PersonaFuenteExternaDTO() {
    }

    public PersonaFuenteExternaDTO(Long id) {
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

    public TipoFuenteInformacionDTO getFuenteInformacion() {
        return this.fuenteInformacion;
    }

    public void setFuenteInformacion(TipoFuenteInformacionDTO fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    public Date getFechaUltimaActualizacion() {
        return this.fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
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

    public String getFaxEmpresaLabora() {
        return this.faxEmpresaLabora;
    }

    public void setFaxEmpresaLabora(String faxEmpresaLabora) {
        this.faxEmpresaLabora = faxEmpresaLabora;
    }

    public byte[] getHuellaDigital() {
        return this.huellaDigital;
    }

    public void setHuellaDigital(byte[] huellaDigital) {
        this.huellaDigital = huellaDigital;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DireccionPersonaFuenteExternaDTO> getDireccionFuenteExternaList() {
        if (this.direccionFuenteExternaList == null) {
            this.direccionFuenteExternaList = new java.util.ArrayList<>(1);
        }
        return this.direccionFuenteExternaList;
    }

    public void setDireccionFuenteExternaList(List<DireccionPersonaFuenteExternaDTO> direccionFuenteExternaList) {
        this.direccionFuenteExternaList = direccionFuenteExternaList;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
