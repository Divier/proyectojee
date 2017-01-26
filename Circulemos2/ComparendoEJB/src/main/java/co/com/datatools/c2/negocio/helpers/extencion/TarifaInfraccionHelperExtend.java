package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TarifaInfraccionDTO;
import co.com.datatools.c2.entidades.TarifaInfraccion;
import co.com.datatools.c2.negocio.helpers.comparendos.InfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TarifaInfraccionHelper;

/**
 * @author robert.bautista
 * @since 2013-08-11
 */
public class TarifaInfraccionHelperExtend extends TarifaInfraccionHelper {

    public static List<String> checkNullList(List<String> lista) {
        if (lista.isEmpty()) {
            return null;
        }
        return lista;
    }

    public static TarifaInfraccionDTO toLevel1DTO(TarifaInfraccion entidad) {
        TarifaInfraccionDTO dto = TarifaInfraccionHelper.toLevel1DTO(entidad);
        if (entidad.getInfraccion() != null)
            dto.setInfraccion(InfraccionHelper.toLevel1DTO(entidad.getInfraccion()));

        return dto;
    }

    public static List<TarifaInfraccionDTO> toListLevel1DTO(List<TarifaInfraccion> listEntidad) {
        List<TarifaInfraccionDTO> listDto = new ArrayList<TarifaInfraccionDTO>();
        for (TarifaInfraccion entidad : listEntidad) {
            listDto.add(TarifaInfraccionHelperExtend.toLevel1DTO(entidad));
        }
        return listDto;
    }

}
