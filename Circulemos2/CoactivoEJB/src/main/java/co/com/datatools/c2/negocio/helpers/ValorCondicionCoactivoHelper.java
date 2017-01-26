package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ValorCondicionCoactivoDTO;
import co.com.datatools.c2.entidades.ConfiguracionCoactivo;
import co.com.datatools.c2.entidades.ValorCondicionCoactivo;
import co.com.datatools.c2.entidades.VariableCondicionCoac;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 13:27:34 COT 2016
 */
public class ValorCondicionCoactivoHelper {
    // --- to DTO
    public static ValorCondicionCoactivoDTO toLevel0DTO(ValorCondicionCoactivo entidad) {
        ValorCondicionCoactivoDTO dto = new ValorCondicionCoactivoDTO();
        dto.setId(entidad.getId());
        dto.setValor(entidad.getValor());

        return dto;
    }

    public static ValorCondicionCoactivoDTO toLevel1DTO(ValorCondicionCoactivo entidad) {
        ValorCondicionCoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getConfiguracionCoactivo() != null)
            dto.setConfiguracionCoactivo(ConfiguracionCoactivoHelper.toLevel0DTO(entidad.getConfiguracionCoactivo()));
        if (entidad.getVariableCondicionCoac() != null)
            dto.setVariableCondicionCoac(VariableCondicionCoacHelper.toLevel0DTO(entidad.getVariableCondicionCoac()));

        return dto;
    }

    public static List<ValorCondicionCoactivoDTO> toListLevel0DTO(List<ValorCondicionCoactivo> listEntidad) {
        List<ValorCondicionCoactivoDTO> listDto = new ArrayList<ValorCondicionCoactivoDTO>();
        for (ValorCondicionCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ValorCondicionCoactivoDTO> toListLevel1DTO(List<ValorCondicionCoactivo> listEntidad) {
        List<ValorCondicionCoactivoDTO> listDto = new ArrayList<ValorCondicionCoactivoDTO>();
        for (ValorCondicionCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ValorCondicionCoactivo toLevel0Entity(ValorCondicionCoactivoDTO dto, ValorCondicionCoactivo entidad) {
        if (null == entidad) {
            entidad = new ValorCondicionCoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setValor(dto.getValor());

        return entidad;
    }

    public static ValorCondicionCoactivo toLevel1Entity(ValorCondicionCoactivoDTO dto, ValorCondicionCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConfiguracionCoactivo() != null) {
            entidad.setConfiguracionCoactivo(new ConfiguracionCoactivo());
            entidad.getConfiguracionCoactivo().setId(dto.getConfiguracionCoactivo().getId());
        }
        if (dto.getVariableCondicionCoac() != null) {
            entidad.setVariableCondicionCoac(new VariableCondicionCoac());
            entidad.getVariableCondicionCoac().setId(dto.getVariableCondicionCoac().getId());
        }

        return entidad;
    }

    public static List<ValorCondicionCoactivo> toListLevel0Entity(List<ValorCondicionCoactivoDTO> listDto) {
        List<ValorCondicionCoactivo> listEntidad = new ArrayList<ValorCondicionCoactivo>();
        for (ValorCondicionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ValorCondicionCoactivo> toListLevel1Entity(List<ValorCondicionCoactivoDTO> listDto) {
        List<ValorCondicionCoactivo> listEntidad = new ArrayList<ValorCondicionCoactivo>();
        for (ValorCondicionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
