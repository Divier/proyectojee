package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:18:51 COT 2014
 */
public class CorreoEnvioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String email;
    private OrganismoTransitoDTO organismoTransito;
    private ConfiguracionEmailDTO configuracionEmail;

    public enum TipoAgregacion {
        EXISTE, //
        NUEVO, //
        TEMPORAL, //
        ;
    }

    private TipoAgregacion tipoAgregacion;

    // --- Constructor
    public CorreoEnvioDTO() {
        tipoAgregacion = TipoAgregacion.EXISTE;
    }

    public CorreoEnvioDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public ConfiguracionEmailDTO getConfiguracionEmail() {
        return this.configuracionEmail;
    }

    public void setConfiguracionEmail(ConfiguracionEmailDTO configuracionEmail) {
        this.configuracionEmail = configuracionEmail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public TipoAgregacion getTipoAgregacion() {
        return tipoAgregacion;
    }

    public void setTipoAgregacion(TipoAgregacion tipoAgregacion) {
        this.tipoAgregacion = tipoAgregacion;
    }

}
