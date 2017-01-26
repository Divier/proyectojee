package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.MotivoVigenciaAgenteDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 13 14:49:35 COT 2015
 */
public class AgenteDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Date fechaFinVigencia;
    private Date fechaInicioVigencia;
    private TipoEntidadAgenteTransitoDTO entidadAgenteTransito;
    private PersonaDTO persona;
    private String placa;
    private OrganismoTransitoDTO organismoTransito;
    private List<ComparendoAgenteDTO> comparendoAgentes;
    private Date fechaActualizacion;
    private UsuarioPersonaDTO usuarioActualizacion;
    private MotivoVigenciaAgenteDTO motivoVigenciaAgente;
    private Long numeroFirma;

    // --- Constructor
    public AgenteDTO() {
    }

    public AgenteDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaFinVigencia() {
        return this.fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Date getFechaInicioVigencia() {
        return this.fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public TipoEntidadAgenteTransitoDTO getEntidadAgenteTransito() {
        return this.entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(TipoEntidadAgenteTransitoDTO entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ComparendoAgenteDTO> getComparendoAgentes() {
        if (this.comparendoAgentes == null) {
            this.comparendoAgentes = new java.util.ArrayList<>(1);
        }
        return this.comparendoAgentes;
    }

    public void setComparendoAgentes(List<ComparendoAgenteDTO> comparendoAgentes) {
        this.comparendoAgentes = comparendoAgentes;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UsuarioPersonaDTO getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(UsuarioPersonaDTO usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public MotivoVigenciaAgenteDTO getMotivoVigenciaAgente() {
        return motivoVigenciaAgente;
    }

    public void setMotivoVigenciaAgente(MotivoVigenciaAgenteDTO motivoVigenciaAgente) {
        this.motivoVigenciaAgente = motivoVigenciaAgente;
    }

    public Long getNumeroFirma() {
        return numeroFirma;
    }

    public void setNumeroFirma(Long numeroFirma) {
        this.numeroFirma = numeroFirma;
    }

}
