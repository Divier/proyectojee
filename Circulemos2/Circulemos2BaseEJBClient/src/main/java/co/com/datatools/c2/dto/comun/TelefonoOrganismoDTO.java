package co.com.datatools.c2.dto.comun;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 08 18:24:31 COT 2015
 */
public class TelefonoOrganismoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String telefono;
    private Boolean fax;
    private OrganismoTransitoDTO codigoOrganismo;

    // --- Constructor
    public TelefonoOrganismoDTO() {
    }

    public TelefonoOrganismoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getFax() {
        return this.fax;
    }

    public void setFax(Boolean fax) {
        this.fax = fax;
    }

    public OrganismoTransitoDTO getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransitoDTO codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

}
