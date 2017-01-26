package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.DetalleCargueUbicabilidadDTO;
import co.com.datatools.c2.entidades.cargue.CargueArchivo;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersonaHistorico;
import co.com.datatools.c2.entidades.ubicabilidad.DetalleCargueUbicabilidad;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersonaHistorico;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersonaHistorico;
import co.com.datatools.c2.negocio.helpers.cargue.CargueArchivoHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:47 COT 2016
 */
public class DetalleCargueUbicabilidadHelper {
    // --- to DTO
    public static DetalleCargueUbicabilidadDTO toLevel0DTO(DetalleCargueUbicabilidad entidad) {
        DetalleCargueUbicabilidadDTO dto = new DetalleCargueUbicabilidadDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static DetalleCargueUbicabilidadDTO toLevel1DTO(DetalleCargueUbicabilidad entidad) {
        DetalleCargueUbicabilidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getCargueArchivo() != null)
            dto.setCargueArchivo(CargueArchivoHelper.toLevel0DTO(entidad.getCargueArchivo()));
        if (entidad.getDireccionPersonaHistorico() != null)
            dto.setDireccionPersonaHistorico(
                    DireccionPersonaHistoricoHelper.toLevel0DTO(entidad.getDireccionPersonaHistorico()));
        if (entidad.getTelefonoPersonaHistorico() != null)
            dto.setTelefonoPersonaHistorico(
                    TelefonoPersonaHistoricoHelper.toLevel0DTO(entidad.getTelefonoPersonaHistorico()));
        if (entidad.getCorreoPersonaHistorico() != null)
            dto.setCorreoPersonaHistorico(
                    CorreoPersonaHistoricoHelper.toLevel0DTO(entidad.getCorreoPersonaHistorico()));

        return dto;
    }

    public static List<DetalleCargueUbicabilidadDTO> toListLevel0DTO(List<DetalleCargueUbicabilidad> listEntidad) {
        List<DetalleCargueUbicabilidadDTO> listDto = new ArrayList<DetalleCargueUbicabilidadDTO>();
        for (DetalleCargueUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleCargueUbicabilidadDTO> toListLevel1DTO(List<DetalleCargueUbicabilidad> listEntidad) {
        List<DetalleCargueUbicabilidadDTO> listDto = new ArrayList<DetalleCargueUbicabilidadDTO>();
        for (DetalleCargueUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleCargueUbicabilidad toLevel0Entity(DetalleCargueUbicabilidadDTO dto,
            DetalleCargueUbicabilidad entidad) {
        if (null == entidad) {
            entidad = new DetalleCargueUbicabilidad();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static DetalleCargueUbicabilidad toLevel1Entity(DetalleCargueUbicabilidadDTO dto,
            DetalleCargueUbicabilidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCargueArchivo() != null) {
            entidad.setCargueArchivo(new CargueArchivo());
            entidad.getCargueArchivo().setId(dto.getCargueArchivo().getId());
        }
        if (dto.getDireccionPersonaHistorico() != null) {
            entidad.setDireccionPersonaHistorico(new DireccionPersonaHistorico());
            entidad.getDireccionPersonaHistorico().setId(dto.getDireccionPersonaHistorico().getId());
        }
        if (dto.getCorreoPersonaHistorico() != null) {
            entidad.setCorreoPersonaHistorico(new CorreoPersonaHistorico());
            entidad.getCorreoPersonaHistorico().setId(dto.getCorreoPersonaHistorico().getId());
        }
        if (dto.getTelefonoPersonaHistorico() != null) {
            entidad.setTelefonoPersonaHistorico(new TelefonoPersonaHistorico());
            entidad.getTelefonoPersonaHistorico().setId(dto.getTelefonoPersonaHistorico().getId());
        }

        return entidad;
    }

    public static List<DetalleCargueUbicabilidad> toListLevel0Entity(List<DetalleCargueUbicabilidadDTO> listDto) {
        List<DetalleCargueUbicabilidad> listEntidad = new ArrayList<DetalleCargueUbicabilidad>();
        for (DetalleCargueUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleCargueUbicabilidad> toListLevel1Entity(List<DetalleCargueUbicabilidadDTO> listDto) {
        List<DetalleCargueUbicabilidad> listEntidad = new ArrayList<DetalleCargueUbicabilidad>();
        for (DetalleCargueUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
