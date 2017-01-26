package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.AccionImpugnacionBackDTO;
import co.com.datatools.c2.entidades.AccionImpugnacionBack;
import co.com.datatools.c2.entidades.CaracteristicaPrueba;
import co.com.datatools.c2.entidades.TipoAccionBack;
import co.com.datatools.c2.entidades.TrazabilidadProceso;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:09:19 COT 2016
 */
public class AccionImpugnacionBackHelper {
    // --- to DTO
    public static AccionImpugnacionBackDTO toLevel0DTO(AccionImpugnacionBack entidad) {
        AccionImpugnacionBackDTO dto = new AccionImpugnacionBackDTO();
        dto.setId(entidad.getId());
        dto.setFechaAccion(entidad.getFechaAccion());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static AccionImpugnacionBackDTO toLevel1DTO(AccionImpugnacionBack entidad) {
        AccionImpugnacionBackDTO dto = toLevel0DTO(entidad);
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));
        if (entidad.getTipoAccionBack() != null)
            dto.setTipoAccionBack(TipoAccionBackHelper.toLevel0DTO(entidad.getTipoAccionBack()));
        if (entidad.getCaracteristicaPrueba() != null)
            dto.setCaracteristicaPrueba(CaracteristicaPruebaHelper.toLevel0DTO(entidad.getCaracteristicaPrueba()));

        return dto;
    }

    public static List<AccionImpugnacionBackDTO> toListLevel0DTO(List<AccionImpugnacionBack> listEntidad) {
        List<AccionImpugnacionBackDTO> listDto = new ArrayList<AccionImpugnacionBackDTO>();
        for (AccionImpugnacionBack entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<AccionImpugnacionBackDTO> toListLevel1DTO(List<AccionImpugnacionBack> listEntidad) {
        List<AccionImpugnacionBackDTO> listDto = new ArrayList<AccionImpugnacionBackDTO>();
        for (AccionImpugnacionBack entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static AccionImpugnacionBack toLevel0Entity(AccionImpugnacionBackDTO dto, AccionImpugnacionBack entidad) {
        if (null == entidad) {
            entidad = new AccionImpugnacionBack();
            entidad.setFechaAccion(dto.getFechaAccion());
            entidad.setDescripcion(dto.getDescripcion());
        }

        entidad.setId(dto.getId());
        return entidad;
    }

    public static AccionImpugnacionBack toLevel1Entity(AccionImpugnacionBackDTO dto, AccionImpugnacionBack entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }
        if (dto.getTipoAccionBack() != null) {
            entidad.setTipoAccionBack(new TipoAccionBack());
            entidad.getTipoAccionBack().setId(dto.getTipoAccionBack().getId());
        }
        if (dto.getCaracteristicaPrueba() != null) {
            entidad.setCaracteristicaPrueba(new CaracteristicaPrueba());
            entidad.getCaracteristicaPrueba().setId(dto.getCaracteristicaPrueba().getId());
        }

        return entidad;
    }

    public static List<AccionImpugnacionBack> toListLevel0Entity(List<AccionImpugnacionBackDTO> listDto) {
        List<AccionImpugnacionBack> listEntidad = new ArrayList<AccionImpugnacionBack>();
        for (AccionImpugnacionBackDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<AccionImpugnacionBack> toListLevel1Entity(List<AccionImpugnacionBackDTO> listDto) {
        List<AccionImpugnacionBack> listEntidad = new ArrayList<AccionImpugnacionBack>();
        for (AccionImpugnacionBackDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
