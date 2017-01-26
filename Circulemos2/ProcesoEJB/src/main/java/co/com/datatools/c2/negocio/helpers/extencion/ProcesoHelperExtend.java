package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.negocio.helpers.ParticipanteProcesoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.TrazabilidadProcesoHelper;

/**
 * Helper extendido de un proceso
 * 
 * @author divier.casas
 */
public class ProcesoHelperExtend extends ProcesoHelper {

    /**
     * Este metodo retorna la trazabilidad asociada a un proceso
     * 
     * @param proceso
     * @return dto de proceso
     * 
     * @author diego.lozano
     */
    public static ProcesoDTO toLevel1DTOExtTrazabilidad(Proceso proceso) {
        ProcesoDTO procesoDTO = toLevel1DTO(proceso);
        procesoDTO.setTrazabilidadProcesoList(
                TrazabilidadProcesoHelper.toListLevel1DTO(proceso.getTrazabilidadProceso()));
        return procesoDTO;
    }

    /**
     * Este metodo retorna la trazabilidad asociada a un proceso
     * 
     * @param listProceso
     *            lista de entidades de tipo Proceso
     * @return Lista de procesos
     * @author divier.casas
     */
    public static List<ProcesoDTO> toListLevel1DTOExtTrazabilidad(List<Proceso> listProceso) {
        List<ProcesoDTO> lsProcesoDTO = new ArrayList<ProcesoDTO>();
        for (Proceso proceso : listProceso) {
            lsProcesoDTO.add(toLevel1DTOExtTrazabilidad(proceso));
        }
        return lsProcesoDTO;
    }

    /**
     * Este metodo retorna la trazabilidad asociada a un proceso
     * 
     * @param proceso
     * @return dto de proceso
     * @author julio.pinzon
     */
    public static ProcesoDTO toLevel1DTOExt(Proceso proceso) {
        ProcesoDTO procesoDTO = toLevel1DTO(proceso);
        if (proceso.getTrazabilidadProceso() != null) {
            procesoDTO.setTrazabilidadProcesoList(
                    TrazabilidadProcesoHelper.toListLevel1DTO(proceso.getTrazabilidadProceso()));
        }
        if (proceso.getParticipantesProceso() != null) {
            procesoDTO.setParticipantesProcesoList(
                    ParticipanteProcesoHelper.toListLevel1DTO(proceso.getParticipantesProceso()));
        }
        return procesoDTO;
    }
}