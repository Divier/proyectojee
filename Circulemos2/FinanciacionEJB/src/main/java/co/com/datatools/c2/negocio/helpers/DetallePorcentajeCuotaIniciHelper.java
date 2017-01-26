package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetallePorcentajeCuotaIniciDTO;
import co.com.datatools.c2.entidades.ConfiguracionFinanciacion;
import co.com.datatools.c2.entidades.DetallePorcentajeCuotaInici;
import co.com.datatools.c2.entidades.VariableCondicionFinan;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 10:07:02 COT 2016
 */
public class DetallePorcentajeCuotaIniciHelper {
    // --- to DTO
    public static DetallePorcentajeCuotaIniciDTO toLevel0DTO(DetallePorcentajeCuotaInici entidad) {
        DetallePorcentajeCuotaIniciDTO dto = new DetallePorcentajeCuotaIniciDTO();
        dto.setId(entidad.getId());
        dto.setValorMinimoFinanciar(entidad.getValorMinimoFinanciar());
        dto.setValorMaximoFinanciar(entidad.getValorMaximoFinanciar());
        dto.setPorcentajeCuotaInicial(entidad.getPorcentajeCuotaInicial());

        return dto;
    }

    public static DetallePorcentajeCuotaIniciDTO toLevel1DTO(DetallePorcentajeCuotaInici entidad) {
        DetallePorcentajeCuotaIniciDTO dto = toLevel0DTO(entidad);
        if (entidad.getVariableCondicionFinan() != null)
            dto.setVariableCondicionFinan(VariableCondicionFinanHelper.toLevel0DTO(entidad.getVariableCondicionFinan()));
        if (entidad.getConfiguracionFinanciacion() != null)
            dto.setConfiguracionFinanciacion(ConfiguracionFinanciacionHelper.toLevel0DTO(entidad
                    .getConfiguracionFinanciacion()));

        return dto;
    }

    public static List<DetallePorcentajeCuotaIniciDTO> toListLevel0DTO(List<DetallePorcentajeCuotaInici> listEntidad) {
        List<DetallePorcentajeCuotaIniciDTO> listDto = new ArrayList<DetallePorcentajeCuotaIniciDTO>();
        for (DetallePorcentajeCuotaInici entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetallePorcentajeCuotaIniciDTO> toListLevel1DTO(List<DetallePorcentajeCuotaInici> listEntidad) {
        List<DetallePorcentajeCuotaIniciDTO> listDto = new ArrayList<DetallePorcentajeCuotaIniciDTO>();
        for (DetallePorcentajeCuotaInici entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetallePorcentajeCuotaInici toLevel0Entity(DetallePorcentajeCuotaIniciDTO dto,
            DetallePorcentajeCuotaInici entidad) {
        if (null == entidad) {
            entidad = new DetallePorcentajeCuotaInici();
        }
        entidad.setId(dto.getId());
        entidad.setValorMinimoFinanciar(dto.getValorMinimoFinanciar());
        entidad.setValorMaximoFinanciar(dto.getValorMaximoFinanciar());
        entidad.setPorcentajeCuotaInicial(dto.getPorcentajeCuotaInicial());

        return entidad;
    }

    public static DetallePorcentajeCuotaInici toLevel1Entity(DetallePorcentajeCuotaIniciDTO dto,
            DetallePorcentajeCuotaInici entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getVariableCondicionFinan() != null) {
            entidad.setVariableCondicionFinan(new VariableCondicionFinan());
            entidad.getVariableCondicionFinan().setId(dto.getVariableCondicionFinan().getId());
        }
        if (dto.getConfiguracionFinanciacion() != null) {
            entidad.setConfiguracionFinanciacion(new ConfiguracionFinanciacion());
            entidad.getConfiguracionFinanciacion().setId(dto.getConfiguracionFinanciacion().getId());
        }

        return entidad;
    }

    public static List<DetallePorcentajeCuotaInici> toListLevel0Entity(List<DetallePorcentajeCuotaIniciDTO> listDto) {
        List<DetallePorcentajeCuotaInici> listEntidad = new ArrayList<DetallePorcentajeCuotaInici>();
        for (DetallePorcentajeCuotaIniciDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetallePorcentajeCuotaInici> toListLevel1Entity(List<DetallePorcentajeCuotaIniciDTO> listDto) {
        List<DetallePorcentajeCuotaInici> listEntidad = new ArrayList<DetallePorcentajeCuotaInici>();
        for (DetallePorcentajeCuotaIniciDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
