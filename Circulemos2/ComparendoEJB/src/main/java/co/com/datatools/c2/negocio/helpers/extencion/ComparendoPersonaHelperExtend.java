package co.com.datatools.c2.negocio.helpers.extencion;

import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.entidades.ComparendoPersona;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoCategLicenciaConduccionHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoPersonaComparendoHelper;

/**
 * @author julio.pinzon
 */
public class ComparendoPersonaHelperExtend extends ComparendoPersonaHelper {
    // --- to DTO

    public static ComparendoPersonaDTO toLevel1DTO(ComparendoPersona entidad) {
        ComparendoPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getTipoPersonaComparendo() != null)
            dto.setTipoPersonaComparendo(TipoPersonaComparendoHelper.toLevel0DTO(entidad.getTipoPersonaComparendo()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getTipoIdentificacion() != null)
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));
        if (entidad.getCategoriaLicencia() != null)
            dto.setCategoriaLicencia(TipoCategLicenciaConduccionHelper.toLevel0DTO(entidad.getCategoriaLicencia()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel1DTO(entidad.getOrganismoTransito()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel1DTO(entidad.getDireccion()));

        return dto;
    }

}
