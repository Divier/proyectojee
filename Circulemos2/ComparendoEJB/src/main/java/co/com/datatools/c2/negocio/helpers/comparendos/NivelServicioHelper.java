package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.NivelServicioDTO;
import co.com.datatools.c2.entidades.NivelServicio;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class NivelServicioHelper {
    // --- to DTO
    public static NivelServicioDTO toLevel0DTO(NivelServicio entidad) {
        NivelServicioDTO dto = new NivelServicioDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static NivelServicioDTO toLevel1DTO(NivelServicio entidad) {
        NivelServicioDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<NivelServicioDTO> toListLevel0DTO(List<NivelServicio> listEntidad) {
        List<NivelServicioDTO> listDto = new ArrayList<NivelServicioDTO>();
        for (NivelServicio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NivelServicioDTO> toListLevel1DTO(List<NivelServicio> listEntidad) {
        List<NivelServicioDTO> listDto = new ArrayList<NivelServicioDTO>();
        for (NivelServicio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NivelServicio toLevel0Entity(NivelServicioDTO dto, NivelServicio entidad) {
        if (null == entidad) {
            entidad = new NivelServicio();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static NivelServicio toLevel1Entity(NivelServicioDTO dto, NivelServicio entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<NivelServicio> toListLevel0Entity(List<NivelServicioDTO> listDto) {
        List<NivelServicio> listEntidad = new ArrayList<NivelServicio>();
        for (NivelServicioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NivelServicio> toListLevel1Entity(List<NivelServicioDTO> listDto) {
        List<NivelServicio> listEntidad = new ArrayList<NivelServicio>();
        for (NivelServicioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
