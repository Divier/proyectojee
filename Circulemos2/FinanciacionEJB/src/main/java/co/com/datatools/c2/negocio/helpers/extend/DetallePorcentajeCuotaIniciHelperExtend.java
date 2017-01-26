package co.com.datatools.c2.negocio.helpers.extend;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetallePorcentajeCuotaIniciDTO;
import co.com.datatools.c2.entidades.DetallePorcentajeCuotaInici;
import co.com.datatools.c2.negocio.helpers.ConfiguracionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.DetallePorcentajeCuotaIniciHelper;
import co.com.datatools.c2.negocio.helpers.VariableCondicionFinanHelper;

/**
 * @author luis.forero
 * 
 */
public class DetallePorcentajeCuotaIniciHelperExtend extends DetallePorcentajeCuotaIniciHelper {
    // TODO FIX Helper
    // TO DTO LEVEL 1
    public static DetallePorcentajeCuotaIniciDTO toLevel1DTO(DetallePorcentajeCuotaInici detallePorcentajeCuotaInici) {
        DetallePorcentajeCuotaIniciDTO detallePorcentajeCuotaIniciDTO = toLevel0DTO(detallePorcentajeCuotaInici);
        if (detallePorcentajeCuotaInici.getVariableCondicionFinan() != null) {
            detallePorcentajeCuotaIniciDTO.setVariableCondicionFinan(VariableCondicionFinanHelper
                    .toLevel1DTO(detallePorcentajeCuotaInici.getVariableCondicionFinan()));
        }
        if (detallePorcentajeCuotaInici.getConfiguracionFinanciacion() != null) {
            detallePorcentajeCuotaIniciDTO.setConfiguracionFinanciacion(ConfiguracionFinanciacionHelper
                    .toLevel1DTO(detallePorcentajeCuotaInici.getConfiguracionFinanciacion()));
        }
        return detallePorcentajeCuotaIniciDTO;
    }

    // TO LIST<DTO> LEVEL 1
    public static List<DetallePorcentajeCuotaIniciDTO> toListLevel1DTO(
            List<DetallePorcentajeCuotaInici> listDetallePorcentajeCuotaInici) {
        List<DetallePorcentajeCuotaIniciDTO> listDetallePorcentajeCuotaIniciDTO = new ArrayList<DetallePorcentajeCuotaIniciDTO>();
        for (DetallePorcentajeCuotaInici detallePorcentajeCuotaInici : listDetallePorcentajeCuotaInici) {
            listDetallePorcentajeCuotaIniciDTO.add(toLevel1DTO(detallePorcentajeCuotaInici));
        }
        return listDetallePorcentajeCuotaIniciDTO;
    }

}
