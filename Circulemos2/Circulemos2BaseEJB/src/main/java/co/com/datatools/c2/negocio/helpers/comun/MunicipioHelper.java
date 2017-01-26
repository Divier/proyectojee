package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.entidades.comun.Departamento;
import co.com.datatools.c2.entidades.comun.Municipio;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class MunicipioHelper {
    // --- to DTO
    public static MunicipioDTO toLevel0DTO(Municipio entidad) {
        MunicipioDTO dto = new MunicipioDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static MunicipioDTO toLevel1DTO(Municipio entidad) {
        MunicipioDTO dto = toLevel0DTO(entidad);
        if (entidad.getDepartamento() != null)
            dto.setDepartamento(DepartamentoHelper.toLevel0DTO(entidad.getDepartamento()));

        return dto;
    }

    public static List<MunicipioDTO> toListLevel0DTO(List<Municipio> listEntidad) {
        List<MunicipioDTO> listDto = new ArrayList<MunicipioDTO>();
        for (Municipio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<MunicipioDTO> toListLevel1DTO(List<Municipio> listEntidad) {
        List<MunicipioDTO> listDto = new ArrayList<MunicipioDTO>();
        for (Municipio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Municipio toLevel0Entity(MunicipioDTO dto, Municipio entidad) {
        if (null == entidad) {
            entidad = new Municipio();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static Municipio toLevel1Entity(MunicipioDTO dto, Municipio entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDepartamento() != null) {
            entidad.setDepartamento(new Departamento());
            entidad.getDepartamento().setId(dto.getDepartamento().getId());
        }

        return entidad;
    }

    public static List<Municipio> toListLevel0Entity(List<MunicipioDTO> listDto) {
        List<Municipio> listEntidad = new ArrayList<Municipio>();
        for (MunicipioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Municipio> toListLevel1Entity(List<MunicipioDTO> listDto) {
        List<Municipio> listEntidad = new ArrayList<Municipio>();
        for (MunicipioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
