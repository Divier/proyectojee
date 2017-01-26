package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class NivelEducativoHelper {
    // --- to DTO
    public static NivelEducativoDTO toLevel0DTO(NivelEducativo entidad) {
        NivelEducativoDTO dto = new NivelEducativoDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static NivelEducativoDTO toLevel1DTO(NivelEducativo entidad) {
        NivelEducativoDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<NivelEducativoDTO> toListLevel0DTO(List<NivelEducativo> listEntidad) {
        List<NivelEducativoDTO> listDto = new ArrayList<NivelEducativoDTO>();
        for (NivelEducativo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NivelEducativoDTO> toListLevel1DTO(List<NivelEducativo> listEntidad) {
        List<NivelEducativoDTO> listDto = new ArrayList<NivelEducativoDTO>();
        for (NivelEducativo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NivelEducativo toLevel0Entity(NivelEducativoDTO dto, NivelEducativo entidad) {
        if (null == entidad) {
            entidad = new NivelEducativo();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static NivelEducativo toLevel1Entity(NivelEducativoDTO dto, NivelEducativo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<NivelEducativo> toListLevel0Entity(List<NivelEducativoDTO> listDto) {
        List<NivelEducativo> listEntidad = new ArrayList<NivelEducativo>();
        for (NivelEducativoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NivelEducativo> toListLevel1Entity(List<NivelEducativoDTO> listDto) {
        List<NivelEducativo> listEntidad = new ArrayList<NivelEducativo>();
        for (NivelEducativoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
