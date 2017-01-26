package co.com.datatools.c2.negocio.helpers.extend;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ValorCondicionCoactivoDTO;
import co.com.datatools.c2.entidades.ValorCondicionCoactivo;
import co.com.datatools.c2.negocio.helpers.ConfiguracionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.ValorCondicionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.VariableCondicionCoacHelper;

/**
 * 
 * @author Dixon.Alvarez
 * 
 */

public class ValorCondicionCoactivoHelperExtend extends ValorCondicionCoactivoHelper {

    // TO DTO LEVEL 1
    public static ValorCondicionCoactivoDTO toLevel1DTO(ValorCondicionCoactivo valorCondicionCoactivo) {
        ValorCondicionCoactivoDTO valorCondicionFinanciacionDTO = toLevel0DTO(valorCondicionCoactivo);
        if (valorCondicionCoactivo.getConfiguracionCoactivo() != null) {
            valorCondicionFinanciacionDTO.setConfiguracionCoactivo(ConfiguracionCoactivoHelper
                    .toLevel1DTO(valorCondicionCoactivo.getConfiguracionCoactivo()));
        }
        if (valorCondicionCoactivo.getVariableCondicionCoac() != null) {
            valorCondicionFinanciacionDTO.setVariableCondicionCoac(VariableCondicionCoacHelper
                    .toLevel1DTO(valorCondicionCoactivo.getVariableCondicionCoac()));
        }
        return valorCondicionFinanciacionDTO;
    }

    // TO LIST<DTO> LEVEL 1
    public static List<ValorCondicionCoactivoDTO> toListLevel1DTO(
            List<ValorCondicionCoactivo> listValorCondicionCoactivo) {
        List<ValorCondicionCoactivoDTO> listValorCondicionFinanciacionDTO = new ArrayList<ValorCondicionCoactivoDTO>();
        for (ValorCondicionCoactivo valorCondicionFinanciacion : listValorCondicionCoactivo) {
            listValorCondicionFinanciacionDTO.add(toLevel1DTO(valorCondicionFinanciacion));
        }
        return listValorCondicionFinanciacionDTO;
    }
}
