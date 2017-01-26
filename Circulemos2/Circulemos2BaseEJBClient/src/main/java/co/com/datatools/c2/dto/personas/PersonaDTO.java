package co.com.datatools.c2.dto.personas;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * DTO no autogenerable
 * 
 * Se remueve la informacion
 * 
 * @author felipe.martinez
 * @version 3.0 - Tue May 27 18:03:44 COT 2014 - editado
 */
public class PersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TipoIdentificacionPersonaDTO tipoIdentificacion;
    private String numeroIdentificacion;
    private TipoFuenteInformacionDTO fuenteInformacion;
    private OrganismoTransitoDTO organismoTransito;
    private Date fechaUltimaActualizacion;
    private MunicipioDTO municipioExpedicionDocumento;
    private Date fechaExpedicionDocumento;
    private Date fechaNacimiento;
    private Date fechaFallecimiento;
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
    private String telefonoEmpresaLabora;
    private String faxEmpresaLabora;
    private Boolean notificaDirEmpresa;
    private String cargoEmpresaLabora;
    private byte[] huellaDigital;
    private String rutaFoto;
    private Date fechaFoto;
    private TipoViviendaDTO tipoVivienda;
    private NivelEducativoDTO nivelEducativo;
    private List<DireccionPersonaDTO> direccionPersonaList;
    private List<ParentescoPersonaDTO> parentescoPersonaList;
    private Long idDocumento;
    private List<CorreoPersonaDTO> correoPersonaList;
    private List<TelefonoPersonaDTO> telefonoPersonaDTOs;

    // --- Adicionales
    private boolean validarCamposMinimos = false;

    // --- Nombre completo
    private String nombreCompleto;

    // --- Constructor
    public PersonaDTO() {
    }

    public PersonaDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    /**
     * ID de la persona no es el id de las clases q heredan de esta
     * 
     * @return id de la entidad Persona
     */
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

    public Date getFechaFoto() {
        return this.fechaFoto;
    }

    public void setFechaFoto(Date fechaFoto) {
        this.fechaFoto = fechaFoto;
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
    public List<DireccionPersonaDTO> getDireccionPersonaList() {
        if (this.direccionPersonaList == null) {
            this.direccionPersonaList = new java.util.ArrayList<>(1);
        }

        return this.direccionPersonaList;
    }

    public void setDireccionPersonaList(List<DireccionPersonaDTO> direccionPersonaList) {
        this.direccionPersonaList = direccionPersonaList;
    }

    public List<ParentescoPersonaDTO> getParentescoPersonaList() {
        if (this.parentescoPersonaList == null) {
            this.parentescoPersonaList = new java.util.ArrayList<>(1);
        }

        return parentescoPersonaList;
    }

    public void setParentescoPersonaList(List<ParentescoPersonaDTO> parentescoPersonaList) {
        this.parentescoPersonaList = parentescoPersonaList;
    }

    // --- Adicionales
    public boolean isValidarCamposMinimos() {
        return validarCamposMinimos;
    }

    public void setValidarCamposMinimos(boolean validarCamposMinimos) {
        this.validarCamposMinimos = validarCamposMinimos;
    }

    public String getNombreCompleto() {
        PersonaDTO persona = this;
        StringBuilder strNombre = new StringBuilder(1);
        if (persona != null) {
            if (persona instanceof PersonaJuridicaDTO) {
                strNombre.append(((PersonaJuridicaDTO) persona).getNombreComercial());
            } else {
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre1()) ? persona.getNombre1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre2()) ? persona.getNombre2() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido1()) ? persona.getApellido1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido2()) ? persona.getApellido2() : "");
            }
        }
        return strNombre.toString();
    }

    /**
     * Metodo que se encarga de devolver la direccion vigente para ese persona
     * 
     * @return
     */
    public String getDireccionVigente() {
        String direccion = null;
        if (direccionPersonaList != null && !direccionPersonaList.isEmpty()) {
            for (DireccionPersonaDTO direccionPersonaDTO : direccionPersonaList) {
                if (direccionPersonaDTO.getEstado() == null || direccionPersonaDTO.getEstado()) {
                    direccion = direccionPersonaDTO.getDireccion().getComplemento();
                    break;
                }
            }
        }
        return direccion;
    }

    public String getCorreoVigente() {
        String correo = null;
        if (correoPersonaList != null && !correoPersonaList.isEmpty()) {
            for (CorreoPersonaDTO correoPersonaDTO : correoPersonaList) {
                if (correoPersonaDTO.getEstado() == null || correoPersonaDTO.getEstado()) {
                    correo = correoPersonaDTO.getCorreoElectronico();
                    break;
                }
            }
        }
        return correo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public List<CorreoPersonaDTO> getCorreoPersonaList() {
        return correoPersonaList;
    }

    public void setCorreoPersonaList(List<CorreoPersonaDTO> correoPersonaList) {
        this.correoPersonaList = correoPersonaList;
    }

    public List<TelefonoPersonaDTO> getTelefonoPersonaDTOs() {
        return telefonoPersonaDTOs;
    }

    public void setTelefonoPersonaDTOs(List<TelefonoPersonaDTO> telefonoPersonaDTOs) {
        this.telefonoPersonaDTOs = telefonoPersonaDTOs;
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

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }
}