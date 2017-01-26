package co.com.datatools.c2.negocio.helpers.extencion;

import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoComparendoDTO;

public class ReincidenciaHelperExtend {

    public static void toHistoricoComparendoDTO(HistoricoComparendoDTO reincidencia, ComparendoDTO comparendo) {
        reincidencia.setNumeroComparendo(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
        reincidencia.setCodigoOrganismo(comparendo.getOrdenComparendoNacional().getOrganismoTransito()
                .getCodigoOrganismo());
        reincidencia.setCodigoInfraccion(comparendo.getInfraccion().getCodigo());
        reincidencia.setNombreInfractor(comparendo.getInfractor().getNombreCompleto());
        reincidencia.setFechaImposicion(comparendo.getFechaInfraccion());
        reincidencia.setIdInfraccion(comparendo.getInfraccion().getId());
    }

}
