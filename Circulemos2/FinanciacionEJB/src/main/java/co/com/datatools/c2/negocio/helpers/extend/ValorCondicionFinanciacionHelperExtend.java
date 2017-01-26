package co.com.datatools.c2.negocio.helpers.extend;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.entidades.ValorCondicionFinanciacion;
import co.com.datatools.c2.negocio.helpers.ConfiguracionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ValorCondicionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.VariableCondicionFinanHelper;

/**
 * @author luis.forero
 * 
 */
public class ValorCondicionFinanciacionHelperExtend extends ValorCondicionFinanciacionHelper {

    // TO DTO LEVEL 1
    public static ValorCondicionFinanciacionDTO toLevel1DTO(ValorCondicionFinanciacion valorCondicionFinanciacion) {
        ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO = toLevel0DTO(valorCondicionFinanciacion);
        if (valorCondicionFinanciacion.getConfiguracionFinanciacion() != null) {
            valorCondicionFinanciacionDTO.setConfiguracionFinanciacion(ConfiguracionFinanciacionHelper
                    .toLevel1DTO(valorCondicionFinanciacion.getConfiguracionFinanciacion()));
        }
        if (valorCondicionFinanciacion.getVariableCondicionFinan() != null) {
            valorCondicionFinanciacionDTO.setVariableCondicionFinan(VariableCondicionFinanHelper
                    .toLevel1DTO(valorCondicionFinanciacion.getVariableCondicionFinan()));
        }
        return valorCondicionFinanciacionDTO;
    }

    // TO LIST<DTO> LEVEL 1
    public static List<ValorCondicionFinanciacionDTO> toListLevel1DTO(
            List<ValorCondicionFinanciacion> listValorCondicionFinanciacion) {
        List<ValorCondicionFinanciacionDTO> listValorCondicionFinanciacionDTO = new ArrayList<ValorCondicionFinanciacionDTO>();
        for (ValorCondicionFinanciacion valorCondicionFinanciacion : listValorCondicionFinanciacion) {
            listValorCondicionFinanciacionDTO.add(toLevel1DTO(valorCondicionFinanciacion));
        }
        return listValorCondicionFinanciacionDTO;
    }
}
