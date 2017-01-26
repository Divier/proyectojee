package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ObligacionSacC2DTO;
import co.com.datatools.c2.entidades.ObligacionSacC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 31 15:37:02 COT 2016
 */
public class ObligacionSacC2Helper {
    // --- to DTO
    public static ObligacionSacC2DTO toLevel0DTO(ObligacionSacC2 entidad) {
        ObligacionSacC2DTO dto = new ObligacionSacC2DTO();
        dto.setId(entidad.getId());
        dto.setIdCartera(entidad.getIdCartera());
        dto.setIdObligacionSac(entidad.getIdObligacionSac());
        dto.setFechaHoraReplicaSac(entidad.getFechaHoraReplicaSac());

        return dto;
    }

    public static ObligacionSacC2DTO toLevel1DTO(ObligacionSacC2 entidad) {
        ObligacionSacC2DTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ObligacionSacC2DTO> toListLevel0DTO(List<ObligacionSacC2> listEntidad) {
        List<ObligacionSacC2DTO> listDto = new ArrayList<ObligacionSacC2DTO>();
        for (ObligacionSacC2 entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ObligacionSacC2DTO> toListLevel1DTO(List<ObligacionSacC2> listEntidad) {
        List<ObligacionSacC2DTO> listDto = new ArrayList<ObligacionSacC2DTO>();
        for (ObligacionSacC2 entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ObligacionSacC2 toLevel0Entity(ObligacionSacC2DTO dto, ObligacionSacC2 entidad) {
        if (null == entidad) {
            entidad = new ObligacionSacC2();
        }
        entidad.setId(dto.getId());
        entidad.setIdCartera(dto.getIdCartera());
        entidad.setIdObligacionSac(dto.getIdObligacionSac());
        entidad.setFechaHoraReplicaSac(dto.getFechaHoraReplicaSac());

        return entidad;
    }

    public static ObligacionSacC2 toLevel1Entity(ObligacionSacC2DTO dto, ObligacionSacC2 entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ObligacionSacC2> toListLevel0Entity(List<ObligacionSacC2DTO> listDto) {
        List<ObligacionSacC2> listEntidad = new ArrayList<ObligacionSacC2>();
        for (ObligacionSacC2DTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ObligacionSacC2> toListLevel1Entity(List<ObligacionSacC2DTO> listDto) {
        List<ObligacionSacC2> listEntidad = new ArrayList<ObligacionSacC2>();
        for (ObligacionSacC2DTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
