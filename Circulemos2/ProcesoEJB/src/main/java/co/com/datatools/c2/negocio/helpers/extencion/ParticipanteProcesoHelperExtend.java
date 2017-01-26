package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.entidades.ParticipanteProceso;
import co.com.datatools.c2.negocio.helpers.ParticipanteProcesoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesarPersonasHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.TipoParticipanteHelper;

/**
 * Extend de participante para traer las relaciones completas
 * 
 * @author julio.pinzon 2016-06-16
 */
public class ParticipanteProcesoHelperExtend extends ParticipanteProcesoHelper {

    /**
     * Obtiene datos adicionales del participante
     * 
     * @param entidad
     * @return dto de participante
     * @author julio.pinzon 2016-06-16
     */
    public static ParticipanteProcesoDTO toLevel2DTO(ParticipanteProceso entidad) {
        ParticipanteProcesoDTO dto = toLevel0DTO(entidad);
        if (entidad.getProceso() != null)
            dto.setProceso(ProcesoHelper.toLevel1DTO(entidad.getProceso()));
        if (entidad.getPersona() != null)
            dto.setPersona(ProcesarPersonasHelper.toPersonaLevel1DTO(entidad.getPersona()));
        if (entidad.getTipoParticipante() != null)
            dto.setTipoParticipante(TipoParticipanteHelper.toLevel1DTO(entidad.getTipoParticipante()));

        return dto;
    }

    /**
     * Obtiene datos adicionales del participante
     * 
     * @param lista
     *            de entidades
     * @return Lista de dto de participante
     * @author julio.pinzon 2016-06-16
     */
    public static List<ParticipanteProcesoDTO> toListLevel2DTO(List<ParticipanteProceso> listEntidad) {
        List<ParticipanteProcesoDTO> listDto = new ArrayList<ParticipanteProcesoDTO>();
        for (ParticipanteProceso entidad : listEntidad) {
            listDto.add(toLevel2DTO(entidad));
        }
        return listDto;
    }
}
