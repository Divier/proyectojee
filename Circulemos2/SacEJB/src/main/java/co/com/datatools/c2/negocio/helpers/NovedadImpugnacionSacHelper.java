package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.NovedadImpugnacionSacDTO;
import co.com.datatools.c2.entidades.NovedadImpugnacionSac;

/**
 * @author Generated
 * @version 3.0 - Tue May 31 15:37:02 COT 2016
 */
public class NovedadImpugnacionSacHelper {
    // --- to DTO
    public static NovedadImpugnacionSacDTO toLevel0DTO(NovedadImpugnacionSac entidad) {
        NovedadImpugnacionSacDTO dto = new NovedadImpugnacionSacDTO();
        dto.setId(entidad.getId());
        dto.setIdProceso(entidad.getIdProceso());
        dto.setIdObligacionSac(entidad.getIdObligacionSac());
        dto.setIdCartera(entidad.getIdCartera());
        dto.setFechaReplicaApertura(entidad.getFechaReplicaApertura());
        dto.setFechaReplicaFallo(entidad.getFechaReplicaFallo());

        return dto;
    }

    public static NovedadImpugnacionSacDTO toLevel1DTO(NovedadImpugnacionSac entidad) {
        NovedadImpugnacionSacDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<NovedadImpugnacionSacDTO> toListLevel0DTO(List<NovedadImpugnacionSac> listEntidad) {
        List<NovedadImpugnacionSacDTO> listDto = new ArrayList<NovedadImpugnacionSacDTO>();
        for (NovedadImpugnacionSac entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NovedadImpugnacionSacDTO> toListLevel1DTO(List<NovedadImpugnacionSac> listEntidad) {
        List<NovedadImpugnacionSacDTO> listDto = new ArrayList<NovedadImpugnacionSacDTO>();
        for (NovedadImpugnacionSac entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NovedadImpugnacionSac toLevel0Entity(NovedadImpugnacionSacDTO dto, NovedadImpugnacionSac entidad) {
        if (null == entidad) {
            entidad = new NovedadImpugnacionSac();
        }
        entidad.setId(dto.getId());
        entidad.setIdProceso(dto.getIdProceso());
        entidad.setIdObligacionSac(dto.getIdObligacionSac());
        entidad.setIdCartera(dto.getIdCartera());
        entidad.setFechaReplicaApertura(dto.getFechaReplicaApertura());
        entidad.setFechaReplicaFallo(dto.getFechaReplicaFallo());

        return entidad;
    }

    public static NovedadImpugnacionSac toLevel1Entity(NovedadImpugnacionSacDTO dto, NovedadImpugnacionSac entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<NovedadImpugnacionSac> toListLevel0Entity(List<NovedadImpugnacionSacDTO> listDto) {
        List<NovedadImpugnacionSac> listEntidad = new ArrayList<NovedadImpugnacionSac>();
        for (NovedadImpugnacionSacDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NovedadImpugnacionSac> toListLevel1Entity(List<NovedadImpugnacionSacDTO> listDto) {
        List<NovedadImpugnacionSac> listEntidad = new ArrayList<NovedadImpugnacionSac>();
        for (NovedadImpugnacionSacDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
