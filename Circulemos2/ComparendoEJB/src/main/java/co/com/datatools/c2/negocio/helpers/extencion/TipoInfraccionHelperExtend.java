package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoInfraccionDTO;
import co.com.datatools.c2.entidades.TipoInfraccion;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoInfraccionHelper;

/**
 * @author rodrigo.cruz
 * @since 2014-09-16
 */
public class TipoInfraccionHelperExtend extends TipoInfraccionHelper {

    public static TipoInfraccionDTO toLevel1DTO(TipoInfraccion entidad) {
        TipoInfraccionDTO dto = TipoInfraccionHelper.toLevel1DTO(entidad);
        if (entidad.getInfraccionList() != null) {
            dto.setInfraccionList(InfraccionHelperExtend.toListLevel1DTO(entidad.getInfraccionList()));
        }
        return dto;
    }

    public static List<TipoInfraccionDTO> toListLevel1DTO(List<TipoInfraccion> listEntidad) {
        List<TipoInfraccionDTO> listDto = new ArrayList<TipoInfraccionDTO>();
        for (TipoInfraccion entidad : listEntidad) {
            listDto.add(TipoInfraccionHelperExtend.toLevel1DTO(entidad));
        }
        return listDto;
    }

}
