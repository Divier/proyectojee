package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.TipoConceptoCarteraDTO;
import co.com.datatools.c2.entidades.TipoConceptoCartera;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:27:59 COT 2015
 */
public class TipoConceptoCarteraHelper {
    // --- to DTO
    public static TipoConceptoCarteraDTO toLevel0DTO(TipoConceptoCartera entidad) {
        TipoConceptoCarteraDTO dto = new TipoConceptoCarteraDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoConceptoCarteraDTO toLevel1DTO(TipoConceptoCartera entidad) {
        TipoConceptoCarteraDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoConceptoCarteraDTO> toListLevel0DTO(List<TipoConceptoCartera> listEntidad) {
        List<TipoConceptoCarteraDTO> listDto = new ArrayList<TipoConceptoCarteraDTO>();
        for (TipoConceptoCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoConceptoCarteraDTO> toListLevel1DTO(List<TipoConceptoCartera> listEntidad) {
        List<TipoConceptoCarteraDTO> listDto = new ArrayList<TipoConceptoCarteraDTO>();
        for (TipoConceptoCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoConceptoCartera toLevel0Entity(TipoConceptoCarteraDTO dto, TipoConceptoCartera entidad) {
        if (null == entidad) {
            entidad = new TipoConceptoCartera();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoConceptoCartera toLevel1Entity(TipoConceptoCarteraDTO dto, TipoConceptoCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoConceptoCartera> toListLevel0Entity(List<TipoConceptoCarteraDTO> listDto) {
        List<TipoConceptoCartera> listEntidad = new ArrayList<TipoConceptoCartera>();
        for (TipoConceptoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoConceptoCartera> toListLevel1Entity(List<TipoConceptoCarteraDTO> listDto) {
        List<TipoConceptoCartera> listEntidad = new ArrayList<TipoConceptoCartera>();
        for (TipoConceptoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
