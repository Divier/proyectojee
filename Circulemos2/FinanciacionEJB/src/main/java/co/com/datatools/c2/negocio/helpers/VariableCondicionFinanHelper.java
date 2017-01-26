package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.VariableCondicionFinanDTO;
import co.com.datatools.c2.entidades.CondicionFinanciacion;
import co.com.datatools.c2.entidades.TipoVariableFinanciacion;
import co.com.datatools.c2.entidades.VariableCondicionFinan;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 10:08:55 COT 2016
 */
public class VariableCondicionFinanHelper {
    // --- to DTO
    public static VariableCondicionFinanDTO toLevel0DTO(VariableCondicionFinan entidad) {
        VariableCondicionFinanDTO dto = new VariableCondicionFinanDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setOrden(entidad.getOrden());
        dto.setFuenteData(entidad.getFuenteData());

        return dto;
    }

    public static VariableCondicionFinanDTO toLevel1DTO(VariableCondicionFinan entidad) {
        VariableCondicionFinanDTO dto = toLevel0DTO(entidad);
        if (entidad.getCondicionFinanciacion() != null)
            dto.setCondicionFinanciacion(CondicionFinanciacionHelper.toLevel0DTO(entidad.getCondicionFinanciacion()));
        if (entidad.getTipoVariableFinanciacion() != null)
            dto.setTipoVariableFinanciacion(TipoVariableFinanciacionHelper.toLevel0DTO(entidad
                    .getTipoVariableFinanciacion()));

        return dto;
    }

    public static List<VariableCondicionFinanDTO> toListLevel0DTO(List<VariableCondicionFinan> listEntidad) {
        List<VariableCondicionFinanDTO> listDto = new ArrayList<VariableCondicionFinanDTO>();
        for (VariableCondicionFinan entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<VariableCondicionFinanDTO> toListLevel1DTO(List<VariableCondicionFinan> listEntidad) {
        List<VariableCondicionFinanDTO> listDto = new ArrayList<VariableCondicionFinanDTO>();
        for (VariableCondicionFinan entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static VariableCondicionFinan toLevel0Entity(VariableCondicionFinanDTO dto, VariableCondicionFinan entidad) {
        if (null == entidad) {
            entidad = new VariableCondicionFinan();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setOrden(dto.getOrden());
        entidad.setFuenteData(dto.getFuenteData());

        return entidad;
    }

    public static VariableCondicionFinan toLevel1Entity(VariableCondicionFinanDTO dto, VariableCondicionFinan entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCondicionFinanciacion() != null) {
            entidad.setCondicionFinanciacion(new CondicionFinanciacion());
            entidad.getCondicionFinanciacion().setCodigo(dto.getCondicionFinanciacion().getCodigo());
        }
        if (dto.getTipoVariableFinanciacion() != null) {
            entidad.setTipoVariableFinanciacion(new TipoVariableFinanciacion());
            entidad.getTipoVariableFinanciacion().setCodigo(dto.getTipoVariableFinanciacion().getCodigo());
        }

        return entidad;
    }

    public static List<VariableCondicionFinan> toListLevel0Entity(List<VariableCondicionFinanDTO> listDto) {
        List<VariableCondicionFinan> listEntidad = new ArrayList<VariableCondicionFinan>();
        for (VariableCondicionFinanDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<VariableCondicionFinan> toListLevel1Entity(List<VariableCondicionFinanDTO> listDto) {
        List<VariableCondicionFinan> listEntidad = new ArrayList<VariableCondicionFinan>();
        for (VariableCondicionFinanDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
