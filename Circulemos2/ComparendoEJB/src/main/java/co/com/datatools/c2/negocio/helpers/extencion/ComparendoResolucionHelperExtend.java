package co.com.datatools.c2.negocio.helpers.extencion;

import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.entidades.ComparendoResolucion;
import co.com.datatools.c2.entidades.Resolucion;
import co.com.datatools.c2.negocio.helpers.EstadoResolucionHelper;
import co.com.datatools.c2.negocio.helpers.ResolucionHelper;
import co.com.datatools.c2.negocio.helpers.TipoResolucionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoResolucionHelper;

/**
 * Comparendo Resolucion Helper Extend se encarga de llevar a cabo la transformacion de un comparendo Resolucion y su Resolucion respectiva a
 * ComparendoResolucionDTO
 * 
 * @author luis.forero (2016-02-02)
 */
public class ComparendoResolucionHelperExtend extends ComparendoResolucionHelper {

    public static ComparendoResolucionDTO toLevel1DTO(ComparendoResolucion entidad, Resolucion resolucion) {
        ComparendoResolucionDTO dto = toLevel1DTO(entidad);
        if (resolucion != null) {
            dto.setIdResolucion(resolucion.getId());
            dto.setAnoResolucion(resolucion.getAnoResolucion());
            dto.setCodigoOrganismo(resolucion.getCodigoOrganismo());
            dto.setFechaResolucion(resolucion.getFechaResolucion());
            dto.setIdDocumento(resolucion.getIdDocumento());
            dto.setNumeroResolucion(resolucion.getNumeroResolucion());
            dto.setResolucionExitosa(resolucion.getResolucionExitosa());

            if (resolucion.getTipoResolucion() != null)
                dto.setTipoResolucion(TipoResolucionHelper.toLevel0DTO(resolucion.getTipoResolucion()));
            if (resolucion.getEstadoResolucion() != null)
                dto.setEstadoResolucion(EstadoResolucionHelper.toLevel0DTO(resolucion.getEstadoResolucion()));
            if (resolucion.getResolucion() != null)
                dto.setResolucion(ResolucionHelper.toLevel0DTO(resolucion.getResolucion()));
        }

        return dto;
    }

    public static ComparendoResolucionDTO toLevel1DTOResolucion(Resolucion entidad) {
        ComparendoResolucionDTO dto = new ComparendoResolucionDTO();
        dto.setId(entidad.getId());
        dto.setAnoResolucion(entidad.getAnoResolucion());
        dto.setCodigoOrganismo(entidad.getCodigoOrganismo());
        dto.setFechaResolucion(entidad.getFechaResolucion());
        dto.setIdDocumento(entidad.getIdDocumento());
        dto.setNumeroResolucion(entidad.getNumeroResolucion());
        dto.setResolucionExitosa(entidad.getResolucionExitosa());
        if (entidad.getTipoResolucion() != null)
            dto.setTipoResolucion(TipoResolucionHelper.toLevel0DTO(entidad.getTipoResolucion()));
        if (entidad.getEstadoResolucion() != null)
            dto.setEstadoResolucion(EstadoResolucionHelper.toLevel0DTO(entidad.getEstadoResolucion()));
        if (entidad.getResolucion() != null)
            dto.setResolucion(ResolucionHelper.toLevel0DTO(entidad.getResolucion()));

        return dto;
    }
}
