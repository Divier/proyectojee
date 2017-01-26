package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoPatio;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 20 17:48:53 COT 2015
 */
public class ComparendoPatioHelper {
    // --- to DTO
    public static ComparendoPatioDTO toLevel0DTO(ComparendoPatio entidad) {
        ComparendoPatioDTO dto = new ComparendoPatioDTO();
        dto.setId(entidad.getId());
        dto.setConsecutivoAsignadoGrua(entidad.getConsecutivoAsignadoGrua());
        dto.setNombre(entidad.getNombre());
        dto.setNumeroGrua(entidad.getNumeroGrua());
        dto.setNumeroInforme(entidad.getNumeroInforme());
        dto.setNumeroPatio(entidad.getNumeroPatio());
        dto.setPlacaGrua(entidad.getPlacaGrua());
        dto.setIdPatio(entidad.getIdPatio());

        return dto;
    }

    public static ComparendoPatioDTO toLevel1DTO(ComparendoPatio entidad) {
        ComparendoPatioDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));

        return dto;
    }

    public static List<ComparendoPatioDTO> toListLevel0DTO(List<ComparendoPatio> listEntidad) {
        List<ComparendoPatioDTO> listDto = new ArrayList<ComparendoPatioDTO>();
        for (ComparendoPatio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoPatioDTO> toListLevel1DTO(List<ComparendoPatio> listEntidad) {
        List<ComparendoPatioDTO> listDto = new ArrayList<ComparendoPatioDTO>();
        for (ComparendoPatio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ComparendoPatio toLevel0Entity(ComparendoPatioDTO dto, ComparendoPatio entidad) {
        if (null == entidad) {
            entidad = new ComparendoPatio();
        }
        entidad.setId(dto.getId());
        entidad.setConsecutivoAsignadoGrua(dto.getConsecutivoAsignadoGrua());
        entidad.setNombre(dto.getNombre());
        entidad.setNumeroGrua(dto.getNumeroGrua());
        entidad.setNumeroInforme(dto.getNumeroInforme());
        entidad.setNumeroPatio(dto.getNumeroPatio());
        entidad.setPlacaGrua(dto.getPlacaGrua());
        entidad.setIdPatio(dto.getIdPatio());

        return entidad;
    }

    public static ComparendoPatio toLevel1Entity(ComparendoPatioDTO dto, ComparendoPatio entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }

        return entidad;
    }

    public static List<ComparendoPatio> toListLevel0Entity(List<ComparendoPatioDTO> listDto) {
        List<ComparendoPatio> listEntidad = new ArrayList<ComparendoPatio>();
        for (ComparendoPatioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ComparendoPatio> toListLevel1Entity(List<ComparendoPatioDTO> listDto) {
        List<ComparendoPatio> listEntidad = new ArrayList<ComparendoPatio>();
        for (ComparendoPatioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
