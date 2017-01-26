package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.entidades.Evidencia;
import co.com.datatools.c2.negocio.helpers.comparendos.EvidenciaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoEvidenciaHelper;

/**
 * @author robert.bautista
 * @since 2013-11-13
 */
public class EvidenciaHelperExtend extends EvidenciaHelper {

    // TO LIST<DTO> LEVEL 1
    public static List<EvidenciaDTO> toListLevel0DTO(List<Evidencia> listEvidencia) {
        List<EvidenciaDTO> listEvidenciaDTO = new ArrayList<EvidenciaDTO>();
        for (Evidencia evidencia : listEvidencia) {
            EvidenciaDTO evidenciaDTO = EvidenciaHelper.toLevel0DTO(evidencia);
            if (evidencia.getTipoEvidencia() != null) {
                evidenciaDTO.setTipoEvidencia(TipoEvidenciaHelper.toLevel0DTO(evidencia.getTipoEvidencia()));
            }
            listEvidenciaDTO.add(evidenciaDTO);
        }

        return listEvidenciaDTO;
    }

}
