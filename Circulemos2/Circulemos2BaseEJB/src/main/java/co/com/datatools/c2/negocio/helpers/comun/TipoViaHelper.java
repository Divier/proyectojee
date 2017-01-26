package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.comun.TipoVia;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoViaHelper {
    // --- to DTO
    public static TipoViaDTO toLevel0DTO(TipoVia entidad) {
        TipoViaDTO dto = new TipoViaDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoViaDTO toLevel1DTO(TipoVia entidad) {
        TipoViaDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<TipoViaDTO> toListLevel0DTO(List<TipoVia> listEntidad) {
        List<TipoViaDTO> listDto = new ArrayList<TipoViaDTO>();
        for (TipoVia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoViaDTO> toListLevel1DTO(List<TipoVia> listEntidad) {
        List<TipoViaDTO> listDto = new ArrayList<TipoViaDTO>();
        for (TipoVia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoVia toLevel0Entity(TipoViaDTO dto, TipoVia entidad) {
        if (null == entidad) {
            entidad = new TipoVia();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoVia toLevel1Entity(TipoViaDTO dto, TipoVia entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<TipoVia> toListLevel0Entity(List<TipoViaDTO> listDto) {
        List<TipoVia> listEntidad = new ArrayList<TipoVia>();
        for (TipoViaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoVia> toListLevel1Entity(List<TipoViaDTO> listDto) {
        List<TipoVia> listEntidad = new ArrayList<TipoVia>();
        for (TipoViaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
