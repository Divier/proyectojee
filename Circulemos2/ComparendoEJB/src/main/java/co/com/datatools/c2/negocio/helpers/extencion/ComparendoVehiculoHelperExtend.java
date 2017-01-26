package co.com.datatools.c2.negocio.helpers.extencion;

import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.entidades.ComparendoVehiculo;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * Helper extendido para obtener datos necesarios
 * 
 * @author julio.pinzon
 */
public class ComparendoVehiculoHelperExtend extends ComparendoVehiculoHelper {
    // --- to DTO

    public static ComparendoVehiculoDTO toLevel1DTOMunicipio(ComparendoVehiculo entidad) {
        ComparendoVehiculoDTO dto = toLevel1DTO(entidad);
        if (entidad.getOrganismoMatriVehic() != null)
            dto.setOrganismoMatriVehic(OrganismoTransitoHelper.toLevel1DTO(entidad.getOrganismoMatriVehic()));
        if (entidad.getOrganismoLicenciaTransito() != null)
            dto.setOrganismoLicenciaTransito(OrganismoTransitoHelper.toLevel1DTO(entidad.getOrganismoLicenciaTransito()));

        return dto;
    }

    // --- fin to DTO
}