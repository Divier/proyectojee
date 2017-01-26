package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 20 15:14:16 COT 2015
 */
public class ComparendoAgenteDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private ComparendoDTO comparendo;
    private AgenteDTO agente;
    private TipoIdentificacionPersonaDTO tipoIdentificacionPersona;
    private String numeroIdentificacion;
    private String placa;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private UnificacionResponsableDTO unificacionResponsable;
    private String nombreResponsable;
    private String observacionesAgente;

    // --- Constructor
    public ComparendoAgenteDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ComparendoDTO getComparendo() {
        return comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public AgenteDTO getAgente() {
        return agente;
    }

    public void setAgente(AgenteDTO agente) {
        this.agente = agente;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacionPersona() {
        return tipoIdentificacionPersona;
    }

    public void setTipoIdentificacionPersona(TipoIdentificacionPersonaDTO tipoIdentificacionPersona) {
        this.tipoIdentificacionPersona = tipoIdentificacionPersona;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public UnificacionResponsableDTO getUnificacionResponsable() {
        return unificacionResponsable;
    }

    public void setUnificacionResponsable(UnificacionResponsableDTO unificacionResponsable) {
        this.unificacionResponsable = unificacionResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getObservacionesAgente() {
        return observacionesAgente;
    }

    public void setObservacionesAgente(String observacionesAgente) {
        this.observacionesAgente = observacionesAgente;
    }

    /**
     * Clona la informacion del comparendoAgenteDTO.
     * 
     * @author luis.forero(2016-02-02)
     */
    public ComparendoAgenteDTO clone() {
        ComparendoAgenteDTO clone = new ComparendoAgenteDTO();

        clone.setApellido1(this.apellido1 != null ? new String(this.apellido1) : null);
        clone.setApellido2(this.apellido2 != null ? new String(this.apellido2) : null);
        clone.setNombreResponsable(this.nombreResponsable != null ? new String(this.nombreResponsable) : null);
        clone.setNombre1(this.nombre1 != null ? new String(this.nombre1) : null);
        clone.setNombre2(this.nombre2 != null ? new String(this.nombre2) : null);
        clone.setNumeroIdentificacion(this.numeroIdentificacion != null ? new String(this.numeroIdentificacion) : null);
        clone.setObservacionesAgente(this.observacionesAgente != null ? new String(this.observacionesAgente) : null);
        clone.setPlaca(this.placa != null ? new String(this.placa) : null);
        if (this.comparendo != null) {
            ComparendoDTO agentedto = new ComparendoDTO(this.comparendo.getCicomparendo());
            clone.setComparendo(agentedto);
        }
        if (this.agente != null) {
            AgenteDTO agentedto = new AgenteDTO(this.agente.getId());
            clone.setAgente(agentedto);
        }
        if (this.tipoIdentificacionPersona != null) {
            TipoIdentificacionPersonaDTO identificacionPersonaDTO = new TipoIdentificacionPersonaDTO(
                    this.tipoIdentificacionPersona.getId() != null ? new Integer(this.tipoIdentificacionPersona.getId())
                            : null);
            identificacionPersonaDTO.setNombre(this.tipoIdentificacionPersona.getNombre() != null
                    ? new String(this.tipoIdentificacionPersona.getNombre()) : null);
            clone.setTipoIdentificacionPersona(identificacionPersonaDTO);
        }
        if (this.unificacionResponsable != null) {
            clone.setUnificacionResponsable(this.unificacionResponsable);
        }

        return clone;
    }
}