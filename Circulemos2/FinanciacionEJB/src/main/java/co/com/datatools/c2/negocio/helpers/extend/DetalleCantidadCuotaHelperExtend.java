package co.com.datatools.c2.negocio.helpers.extend;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;
import co.com.datatools.c2.entidades.DetalleCantidadCuota;
import co.com.datatools.c2.negocio.helpers.ConfiguracionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.DetalleCantidadCuotaHelper;
import co.com.datatools.c2.negocio.helpers.VariableCondicionFinanHelper;

/**
 * @author luis.forero
 * 
 */
public class DetalleCantidadCuotaHelperExtend extends DetalleCantidadCuotaHelper {
    // TODO FIX Helper
    // TO DTO LEVEL 1
    public static DetalleCantidadCuotaDTO toLevel1DTO(DetalleCantidadCuota detalleCantidadCuota) {
        DetalleCantidadCuotaDTO detalleCantidadCuotaDTO = toLevel0DTO(detalleCantidadCuota);
        if (detalleCantidadCuota.getVariableCondicionFinan() != null) {
            detalleCantidadCuotaDTO.setVariableCondicionFinan(VariableCondicionFinanHelper
                    .toLevel1DTO(detalleCantidadCuota.getVariableCondicionFinan()));
        }
        if (detalleCantidadCuota.getConfiguracionFinanciacion() != null) {
            detalleCantidadCuotaDTO.setConfiguracionFinanciacion(ConfiguracionFinanciacionHelper
                    .toLevel0DTO(detalleCantidadCuota.getConfiguracionFinanciacion()));
        }
        return detalleCantidadCuotaDTO;
    }

    // TO LIST<DTO> LEVEL 1
    public static List<DetalleCantidadCuotaDTO> toListLevel1DTO(List<DetalleCantidadCuota> listDetalleCantidadCuota) {
        List<DetalleCantidadCuotaDTO> listDetalleCantidadCuotaDTO = new ArrayList<DetalleCantidadCuotaDTO>();
        for (DetalleCantidadCuota detalleCantidadCuota : listDetalleCantidadCuota) {
            listDetalleCantidadCuotaDTO.add(toLevel1DTO(detalleCantidadCuota));
        }
        return listDetalleCantidadCuotaDTO;
    }
}
