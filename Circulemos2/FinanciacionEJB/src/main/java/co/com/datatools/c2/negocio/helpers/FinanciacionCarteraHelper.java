package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.FinanciacionCarteraDTO;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.entidades.FinanciacionCartera;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 16 11:03:39 COT 2015
 */
public class FinanciacionCarteraHelper {
    // --- to DTO
    public static FinanciacionCarteraDTO toLevel0DTO(FinanciacionCartera entidad) {
        FinanciacionCarteraDTO dto = new FinanciacionCarteraDTO();
        dto.setIdFinanciacionCartera(entidad.getIdFinanciacionCartera());
        dto.setIdCartera(entidad.getIdCartera());

        return dto;
    }

    public static FinanciacionCarteraDTO toLevel1DTO(FinanciacionCartera entidad) {
        FinanciacionCarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getFinanciacion() != null)
            dto.setFinanciacion(FinanciacionHelper.toLevel0DTO(entidad.getFinanciacion()));

        return dto;
    }

    public static List<FinanciacionCarteraDTO> toListLevel0DTO(List<FinanciacionCartera> listEntidad) {
        List<FinanciacionCarteraDTO> listDto = new ArrayList<FinanciacionCarteraDTO>();
        for (FinanciacionCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<FinanciacionCarteraDTO> toListLevel1DTO(List<FinanciacionCartera> listEntidad) {
        List<FinanciacionCarteraDTO> listDto = new ArrayList<FinanciacionCarteraDTO>();
        for (FinanciacionCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static FinanciacionCartera toLevel0Entity(FinanciacionCarteraDTO dto, FinanciacionCartera entidad) {
        if (null == entidad) {
            entidad = new FinanciacionCartera();
        }
        entidad.setIdFinanciacionCartera(dto.getIdFinanciacionCartera());
        entidad.setIdCartera(dto.getIdCartera());

        return entidad;
    }

    public static FinanciacionCartera toLevel1Entity(FinanciacionCarteraDTO dto, FinanciacionCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getFinanciacion() != null) {
            entidad.setFinanciacion(new Financiacion());
            entidad.getFinanciacion().setId(dto.getFinanciacion().getId());
        }

        return entidad;
    }

    public static List<FinanciacionCartera> toListLevel0Entity(List<FinanciacionCarteraDTO> listDto) {
        List<FinanciacionCartera> listEntidad = new ArrayList<FinanciacionCartera>();
        for (FinanciacionCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<FinanciacionCartera> toListLevel1Entity(List<FinanciacionCarteraDTO> listDto) {
        List<FinanciacionCartera> listEntidad = new ArrayList<FinanciacionCartera>();
        for (FinanciacionCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
