package co.com.datatools.c2.negocio.util;

import co.com.datatools.c2.dto.ConsultaOrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;

/**
 * Helper que permite llevar a cabo las conversiones de los datos de entrada de la fachada a los correspondientes de los servicios.
 * 
 * @author luis.forero (2015-08-21)
 * 
 */
public class FachadaAdministracionHelper {

    /**
     * Conversion de datos de entrada de OrganismoTransitoDTO a ConsultaOrganismoTransitoDTO
     * 
     * @param organismoTransito
     *            DTO con los datos de entrada de conversion del dato
     * @return objeto con los datos correspondientes ingresados
     * @author luis.forero
     */
    public static ConsultaOrganismoTransitoDTO toConsultaOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransito) {
        ConsultaOrganismoTransitoDTO consultaOrganismoTransito = new ConsultaOrganismoTransitoDTO();
        consultaOrganismoTransito.setCodigoOrganismo(organismoTransito.getCodigoOrganismo());
        consultaOrganismoTransito.setEstadoOrganismo(organismoTransito.getActivo());
        consultaOrganismoTransito.setCodigoRunt(organismoTransito.getCodigoRunt());
        consultaOrganismoTransito.setCodigoMinisterio(organismoTransito.getCodigoMinisterio());
        if (organismoTransito.getMunicipio() != null && organismoTransito.getMunicipio().getDepartamento() != null
                && organismoTransito.getMunicipio().getDepartamento().getPais() != null
                && organismoTransito.getMunicipio().getDepartamento().getPais().getId() != null) {
            consultaOrganismoTransito.setIdPais(organismoTransito.getMunicipio().getDepartamento().getPais().getId());
        }
        return consultaOrganismoTransito;
    }

}
