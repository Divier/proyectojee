package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.entidades.Infraccion;
import co.com.datatools.c2.negocio.helpers.comparendos.ConfiguracionInfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.InfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoInfraccionHelper;

/**
 * @author rodrigo.cruz
 * @since 2014-09-11
 */
public class InfraccionHelperExtend extends InfraccionHelper {

    public static InfraccionDTO toLevel1DTO(Infraccion entidad) {
        InfraccionDTO dto = InfraccionHelper.toLevel1DTO(entidad);
        if (entidad.getConfiguracionInfraccionList() != null) {
            dto.setConfiguracionInfraccionList(ConfiguracionInfraccionHelper.toListLevel1DTO(entidad
                    .getConfiguracionInfraccionList()));
        }
        return dto;
    }

    public static List<InfraccionDTO> toListLevel1DTO(List<Infraccion> listEntidad) {
        List<InfraccionDTO> listDto = new ArrayList<InfraccionDTO>();
        for (Infraccion entidad : listEntidad) {
            listDto.add(InfraccionHelperExtend.toLevel1DTO(entidad));
        }
        return listDto;
    }

    public static InfraccionDTO toLevel1DTOExtend(Infraccion entidad) {
        InfraccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoInfraccion() != null) {
            dto.setTipoInfraccion(TipoInfraccionHelper.toLevel1DTO(entidad.getTipoInfraccion()));
        }
        if (entidad.getTipoComparendo() != null) {
            dto.setTipoComparendo(TipoComparendoHelper.toLevel0DTO(entidad.getTipoComparendo()));
        }
        return dto;
    }
}
