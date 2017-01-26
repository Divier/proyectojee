package co.com.datatools.c2.dto.personas;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TipoArchivoPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 11:58:01 COT 2016
 */
public class ArchivoPersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaRegistro;
    private String numeroArchivo;
    private String pagina;
    private List<PersonaHistoricoDTO> personaHistoricos;
    private TipoArchivoPersonaDTO tipoArchivoPersona;
    private PersonaDTO persona;
    private UsuarioPersonaDTO usuarioRegistro;
    private TipoFuenteInformacionDTO tipoFuenteInformacion;

    // --- Constructor
    public ArchivoPersonaDTO() {
    }

    public ArchivoPersonaDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroArchivo() {
        return this.numeroArchivo;
    }

    public void setNumeroArchivo(String numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<PersonaHistoricoDTO> getPersonaHistoricos() {
        if (this.personaHistoricos == null) {
            this.personaHistoricos = new java.util.ArrayList<>(1);
        }
        return this.personaHistoricos;
    }

    public void setPersonaHistoricos(List<PersonaHistoricoDTO> personaHistoricos) {
        this.personaHistoricos = personaHistoricos;
    }

    public TipoArchivoPersonaDTO getTipoArchivoPersona() {
        return this.tipoArchivoPersona;
    }

    public void setTipoArchivoPersona(TipoArchivoPersonaDTO tipoArchivoPersona) {
        this.tipoArchivoPersona = tipoArchivoPersona;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public UsuarioPersonaDTO getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersonaDTO usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public TipoFuenteInformacionDTO getTipoFuenteInformacion() {
        return tipoFuenteInformacion;
    }

    public void setTipoFuenteInformacion(TipoFuenteInformacionDTO tipoFuenteInformacion) {
        this.tipoFuenteInformacion = tipoFuenteInformacion;
    }

}
