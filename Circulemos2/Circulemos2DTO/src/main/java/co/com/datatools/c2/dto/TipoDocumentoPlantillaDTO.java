package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoDocumentoPlantillaDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer idTipoDocumento;
    private Integer codigoTipoDocumento;
    private String nombreTipoDocumento;
    private OrganismoTransitoDTO organismoTransitoDTO;

    // Constructors Declaration

    public TipoDocumentoPlantillaDTO() {

    }

    // Start Methods Declaration

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento = nombreTipoDocumento;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

    // Finish the class
}