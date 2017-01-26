package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.VariableCondicionCoacDTO;
import co.com.datatools.c2.entidades.CondicionCoactivo;
import co.com.datatools.c2.entidades.TipoVariableCoactivo;
import co.com.datatools.c2.entidades.VariableCondicionCoac;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:26:30 COT 2016
 */
public class VariableCondicionCoacHelper {
    // --- to DTO
    public static VariableCondicionCoacDTO toLevel0DTO(VariableCondicionCoac entidad) {
        VariableCondicionCoacDTO dto = new VariableCondicionCoacDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setOrden(entidad.getOrden());
        dto.setFuenteData(entidad.getFuenteData());

        return dto;
    }

    public static VariableCondicionCoacDTO toLevel1DTO(VariableCondicionCoac entidad) {
        VariableCondicionCoacDTO dto = toLevel0DTO(entidad);
        if (entidad.getCondicionCoactivo() != null)
            dto.setCondicionCoactivo(CondicionCoactivoHelper.toLevel0DTO(entidad.getCondicionCoactivo()));
        if (entidad.getTipoVariableCoactivo() != null)
            dto.setTipoVariableCoactivo(TipoVariableCoactivoHelper.toLevel0DTO(entidad.getTipoVariableCoactivo()));

        return dto;
    }

    public static List<VariableCondicionCoacDTO> toListLevel0DTO(List<VariableCondicionCoac> listEntidad) {
        List<VariableCondicionCoacDTO> listDto = new ArrayList<VariableCondicionCoacDTO>();
        for (VariableCondicionCoac entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<VariableCondicionCoacDTO> toListLevel1DTO(List<VariableCondicionCoac> listEntidad) {
        List<VariableCondicionCoacDTO> listDto = new ArrayList<VariableCondicionCoacDTO>();
        for (VariableCondicionCoac entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static VariableCondicionCoac toLevel0Entity(VariableCondicionCoacDTO dto, VariableCondicionCoac entidad) {
        if (null == entidad) {
            entidad = new VariableCondicionCoac();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setOrden(dto.getOrden());
        entidad.setFuenteData(dto.getFuenteData());

        return entidad;
    }

    public static VariableCondicionCoac toLevel1Entity(VariableCondicionCoacDTO dto, VariableCondicionCoac entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCondicionCoactivo() != null) {
            entidad.setCondicionCoactivo(new CondicionCoactivo());
            entidad.getCondicionCoactivo().setCodigo(dto.getCondicionCoactivo().getCodigo());
        }
        if (dto.getTipoVariableCoactivo() != null) {
            entidad.setTipoVariableCoactivo(new TipoVariableCoactivo());
            entidad.getTipoVariableCoactivo().setCodigo(dto.getTipoVariableCoactivo().getCodigo());
        }

        return entidad;
    }

    public static List<VariableCondicionCoac> toListLevel0Entity(List<VariableCondicionCoacDTO> listDto) {
        List<VariableCondicionCoac> listEntidad = new ArrayList<VariableCondicionCoac>();
        for (VariableCondicionCoacDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<VariableCondicionCoac> toListLevel1Entity(List<VariableCondicionCoacDTO> listDto) {
        List<VariableCondicionCoac> listEntidad = new ArrayList<VariableCondicionCoac>();
        for (VariableCondicionCoacDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
