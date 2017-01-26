package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 11 17:26:14 COT 2016
 */
public class TrazaEjecucionSoporteDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ConfiguracionSoporteDTO configSoporte;
    private Date fechaFin;
    private UsuarioPersonaDTO usuario;

    // --- Constructor
    public TrazaEjecucionSoporteDTO() {
    }

    public TrazaEjecucionSoporteDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConfiguracionSoporteDTO getConfigSoporte() {
        return this.configSoporte;
    }

    public void setConfigSoporte(ConfiguracionSoporteDTO configSoporte) {
        this.configSoporte = configSoporte;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }
}