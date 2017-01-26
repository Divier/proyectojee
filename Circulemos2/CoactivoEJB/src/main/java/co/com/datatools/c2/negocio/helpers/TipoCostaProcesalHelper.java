package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoCostaProcesalDTO;
import co.com.datatools.c2.entidades.TipoCostaProcesal;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:24:54 COT 2016
 */
public class TipoCostaProcesalHelper {
    // --- to DTO
    public static TipoCostaProcesalDTO toLevel0DTO(TipoCostaProcesal entidad) {
        TipoCostaProcesalDTO dto = new TipoCostaProcesalDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoCostaProcesalDTO toLevel1DTO(TipoCostaProcesal entidad) {
        TipoCostaProcesalDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoCostaProcesalDTO> toListLevel0DTO(List<TipoCostaProcesal> listEntidad) {
        List<TipoCostaProcesalDTO> listDto = new ArrayList<TipoCostaProcesalDTO>();
        for (TipoCostaProcesal entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoCostaProcesalDTO> toListLevel1DTO(List<TipoCostaProcesal> listEntidad) {
        List<TipoCostaProcesalDTO> listDto = new ArrayList<TipoCostaProcesalDTO>();
        for (TipoCostaProcesal entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoCostaProcesal toLevel0Entity(TipoCostaProcesalDTO dto, TipoCostaProcesal entidad) {
        if (null == entidad) {
            entidad = new TipoCostaProcesal();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoCostaProcesal toLevel1Entity(TipoCostaProcesalDTO dto, TipoCostaProcesal entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoCostaProcesal> toListLevel0Entity(List<TipoCostaProcesalDTO> listDto) {
        List<TipoCostaProcesal> listEntidad = new ArrayList<TipoCostaProcesal>();
        for (TipoCostaProcesalDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoCostaProcesal> toListLevel1Entity(List<TipoCostaProcesalDTO> listDto) {
        List<TipoCostaProcesal> listEntidad = new ArrayList<TipoCostaProcesal>();
        for (TipoCostaProcesalDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
