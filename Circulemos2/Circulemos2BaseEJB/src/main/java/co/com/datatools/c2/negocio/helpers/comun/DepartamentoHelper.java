package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.entidades.comun.Departamento;
import co.com.datatools.c2.entidades.comun.Pais;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DepartamentoHelper {
    // --- to DTO
    public static DepartamentoDTO toLevel0DTO(Departamento entidad) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static DepartamentoDTO toLevel1DTO(Departamento entidad) {
        DepartamentoDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<DepartamentoDTO> toListLevel0DTO(List<Departamento> listEntidad) {
        List<DepartamentoDTO> listDto = new ArrayList<DepartamentoDTO>();
        for (Departamento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DepartamentoDTO> toListLevel1DTO(List<Departamento> listEntidad) {
        List<DepartamentoDTO> listDto = new ArrayList<DepartamentoDTO>();
        for (Departamento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Departamento toLevel0Entity(DepartamentoDTO dto, Departamento entidad) {
        if (null == entidad) {
            entidad = new Departamento();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static Departamento toLevel1Entity(DepartamentoDTO dto, Departamento entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<Departamento> toListLevel0Entity(List<DepartamentoDTO> listDto) {
        List<Departamento> listEntidad = new ArrayList<Departamento>();
        for (DepartamentoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Departamento> toListLevel1Entity(List<DepartamentoDTO> listDto) {
        List<Departamento> listEntidad = new ArrayList<Departamento>();
        for (DepartamentoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
