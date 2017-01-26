package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.entidades.ConfiguracionFinanciacion;
import co.com.datatools.c2.entidades.ValorCondicionFinanciacion;
import co.com.datatools.c2.entidades.VariableCondicionFinan;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 10:08:35 COT 2016
 */
public class ValorCondicionFinanciacionHelper {
    // --- to DTO
    public static ValorCondicionFinanciacionDTO toLevel0DTO(ValorCondicionFinanciacion entidad) {
        ValorCondicionFinanciacionDTO dto = new ValorCondicionFinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setValor(entidad.getValor());

        return dto;
    }

    public static ValorCondicionFinanciacionDTO toLevel1DTO(ValorCondicionFinanciacion entidad) {
        ValorCondicionFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getConfiguracionFinanciacion() != null)
            dto.setConfiguracionFinanciacion(ConfiguracionFinanciacionHelper.toLevel0DTO(entidad
                    .getConfiguracionFinanciacion()));
        if (entidad.getVariableCondicionFinan() != null)
            dto.setVariableCondicionFinan(VariableCondicionFinanHelper.toLevel0DTO(entidad.getVariableCondicionFinan()));

        return dto;
    }

    public static List<ValorCondicionFinanciacionDTO> toListLevel0DTO(List<ValorCondicionFinanciacion> listEntidad) {
        List<ValorCondicionFinanciacionDTO> listDto = new ArrayList<ValorCondicionFinanciacionDTO>();
        for (ValorCondicionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ValorCondicionFinanciacionDTO> toListLevel1DTO(List<ValorCondicionFinanciacion> listEntidad) {
        List<ValorCondicionFinanciacionDTO> listDto = new ArrayList<ValorCondicionFinanciacionDTO>();
        for (ValorCondicionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ValorCondicionFinanciacion toLevel0Entity(ValorCondicionFinanciacionDTO dto,
            ValorCondicionFinanciacion entidad) {
        if (null == entidad) {
            entidad = new ValorCondicionFinanciacion();
        }
        entidad.setId(dto.getId());
        entidad.setValor(dto.getValor());

        return entidad;
    }

    public static ValorCondicionFinanciacion toLevel1Entity(ValorCondicionFinanciacionDTO dto,
            ValorCondicionFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConfiguracionFinanciacion() != null) {
            entidad.setConfiguracionFinanciacion(new ConfiguracionFinanciacion());
            entidad.getConfiguracionFinanciacion().setId(dto.getConfiguracionFinanciacion().getId());
        }
        if (dto.getVariableCondicionFinan() != null) {
            entidad.setVariableCondicionFinan(new VariableCondicionFinan());
            entidad.getVariableCondicionFinan().setId(dto.getVariableCondicionFinan().getId());
        }

        return entidad;
    }

    public static List<ValorCondicionFinanciacion> toListLevel0Entity(List<ValorCondicionFinanciacionDTO> listDto) {
        List<ValorCondicionFinanciacion> listEntidad = new ArrayList<ValorCondicionFinanciacion>();
        for (ValorCondicionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ValorCondicionFinanciacion> toListLevel1Entity(List<ValorCondicionFinanciacionDTO> listDto) {
        List<ValorCondicionFinanciacion> listEntidad = new ArrayList<ValorCondicionFinanciacion>();
        for (ValorCondicionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
