package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.NovedadAnulacionSacDTO;
import co.com.datatools.c2.entidades.NovedadAnulacionSac;

/**
 * @author Generated
 * @version 3.0 - Tue May 31 15:37:02 COT 2016
 */
public class NovedadAnulacionSacHelper {
    // --- to DTO
    public static NovedadAnulacionSacDTO toLevel0DTO(NovedadAnulacionSac entidad) {
        NovedadAnulacionSacDTO dto = new NovedadAnulacionSacDTO();
        dto.setId(entidad.getId());
        dto.setIdProceso(entidad.getIdProceso());
        dto.setIdObligacionSac(entidad.getIdObligacionSac());
        dto.setIdCartera(entidad.getIdCartera());
        dto.setFechaReplicaSac(entidad.getFechaReplicaSac());

        return dto;
    }

    public static NovedadAnulacionSacDTO toLevel1DTO(NovedadAnulacionSac entidad) {
        NovedadAnulacionSacDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<NovedadAnulacionSacDTO> toListLevel0DTO(List<NovedadAnulacionSac> listEntidad) {
        List<NovedadAnulacionSacDTO> listDto = new ArrayList<NovedadAnulacionSacDTO>();
        for (NovedadAnulacionSac entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NovedadAnulacionSacDTO> toListLevel1DTO(List<NovedadAnulacionSac> listEntidad) {
        List<NovedadAnulacionSacDTO> listDto = new ArrayList<NovedadAnulacionSacDTO>();
        for (NovedadAnulacionSac entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NovedadAnulacionSac toLevel0Entity(NovedadAnulacionSacDTO dto, NovedadAnulacionSac entidad) {
        if (null == entidad) {
            entidad = new NovedadAnulacionSac();
        }
        entidad.setId(dto.getId());
        entidad.setIdProceso(dto.getIdProceso());
        entidad.setIdObligacionSac(dto.getIdObligacionSac());
        entidad.setIdCartera(dto.getIdCartera());
        entidad.setFechaReplicaSac(dto.getFechaReplicaSac());

        return entidad;
    }

    public static NovedadAnulacionSac toLevel1Entity(NovedadAnulacionSacDTO dto, NovedadAnulacionSac entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<NovedadAnulacionSac> toListLevel0Entity(List<NovedadAnulacionSacDTO> listDto) {
        List<NovedadAnulacionSac> listEntidad = new ArrayList<NovedadAnulacionSac>();
        for (NovedadAnulacionSacDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NovedadAnulacionSac> toListLevel1Entity(List<NovedadAnulacionSacDTO> listDto) {
        List<NovedadAnulacionSac> listEntidad = new ArrayList<NovedadAnulacionSac>();
        for (NovedadAnulacionSacDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
