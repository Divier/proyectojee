package co.com.datatools.c2.negocio.helpers.extend;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.VariableCondicionCoacDTO;
import co.com.datatools.c2.entidades.VariableCondicionCoac;
import co.com.datatools.c2.negocio.helpers.ValorCondicionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.VariableCondicionCoacHelper;

/**
 * Helper extendido de variable condicion de coactivo
 * 
 * @author julio.pinzon 2016-08-05
 */
public class VariableCondicionCoacHelperExtend extends VariableCondicionCoacHelper {
    // --- to DTO

    /**
     * Convierte una entidad de variable condicion de coactivo a su correspondiente dto con sus valores
     * 
     * @param entidad
     * @return dto con sus valores
     * @author julio.pinzon 2016-08-05
     */
    public static VariableCondicionCoacDTO toLevel2DTO(VariableCondicionCoac entidad) {
        VariableCondicionCoacDTO dto = toLevel1DTO(entidad);
        if (entidad.getLstValorCondicionCoactivo() != null) {
            dto.setLstValorCondicionCoactivo(
                    ValorCondicionCoactivoHelper.toListLevel0DTO(entidad.getLstValorCondicionCoactivo()));
        }

        return dto;
    }

    /**
     * Convierte una lista de variable condicion de coactivo a su correspondiente lista de dto con sus valores
     * 
     * @param listEntidad
     * @return lista de dto con sus valores
     * @author julio.pinzon 2016-08-05
     */
    public static List<VariableCondicionCoacDTO> toListLevel2DTO(List<VariableCondicionCoac> listEntidad) {
        List<VariableCondicionCoacDTO> listDto = new ArrayList<VariableCondicionCoacDTO>();
        for (VariableCondicionCoac entidad : listEntidad) {
            listDto.add(toLevel2DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO

}
