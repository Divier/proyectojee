package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoViviendaHelper {
    // --- to DTO
    public static TipoViviendaDTO toLevel0DTO(TipoVivienda entidad) {
        TipoViviendaDTO dto = new TipoViviendaDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoViviendaDTO toLevel1DTO(TipoVivienda entidad) {
        TipoViviendaDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<TipoViviendaDTO> toListLevel0DTO(List<TipoVivienda> listEntidad) {
        List<TipoViviendaDTO> listDto = new ArrayList<TipoViviendaDTO>();
        for (TipoVivienda entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoViviendaDTO> toListLevel1DTO(List<TipoVivienda> listEntidad) {
        List<TipoViviendaDTO> listDto = new ArrayList<TipoViviendaDTO>();
        for (TipoVivienda entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoVivienda toLevel0Entity(TipoViviendaDTO dto, TipoVivienda entidad) {
        if (null == entidad) {
            entidad = new TipoVivienda();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoVivienda toLevel1Entity(TipoViviendaDTO dto, TipoVivienda entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<TipoVivienda> toListLevel0Entity(List<TipoViviendaDTO> listDto) {
        List<TipoVivienda> listEntidad = new ArrayList<TipoVivienda>();
        for (TipoViviendaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoVivienda> toListLevel1Entity(List<TipoViviendaDTO> listDto) {
        List<TipoVivienda> listEntidad = new ArrayList<TipoVivienda>();
        for (TipoViviendaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
