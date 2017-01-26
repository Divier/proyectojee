package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.entidades.FalloImpugnacion;
import co.com.datatools.c2.entidades.TipoFallo;
import co.com.datatools.c2.entidades.TrazabilidadProceso;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:05:48 COT 2016
 */
public class FalloImpugnacionHelper {
    // --- to DTO
    public static FalloImpugnacionDTO toLevel0DTO(FalloImpugnacion entidad) {
        FalloImpugnacionDTO dto = new FalloImpugnacionDTO();
        dto.setId(entidad.getId());
        dto.setMotivo(entidad.getMotivo());
        dto.setPorcentaje(entidad.getPorcentaje());
        dto.setCodigoPlantilla(entidad.getCodigoPlantilla());
        dto.setValorDescuento(entidad.getValorDescuento());
        dto.setValorObligacion(entidad.getValorObligacion());
        dto.setPuntos(entidad.getPuntos());

        return dto;
    }

    public static FalloImpugnacionDTO toLevel1DTO(FalloImpugnacion entidad) {
        FalloImpugnacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));
        if (entidad.getTipoFallo() != null)
            dto.setTipoFallo(TipoFalloHelper.toLevel0DTO(entidad.getTipoFallo()));

        return dto;
    }

    public static List<FalloImpugnacionDTO> toListLevel0DTO(List<FalloImpugnacion> listEntidad) {
        List<FalloImpugnacionDTO> listDto = new ArrayList<FalloImpugnacionDTO>();
        for (FalloImpugnacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<FalloImpugnacionDTO> toListLevel1DTO(List<FalloImpugnacion> listEntidad) {
        List<FalloImpugnacionDTO> listDto = new ArrayList<FalloImpugnacionDTO>();
        for (FalloImpugnacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static FalloImpugnacion toLevel0Entity(FalloImpugnacionDTO dto, FalloImpugnacion entidad) {
        if (null == entidad) {
            entidad = new FalloImpugnacion();
        }
        entidad.setId(dto.getId());
        entidad.setMotivo(dto.getMotivo());
        entidad.setPorcentaje(dto.getPorcentaje());
        entidad.setCodigoPlantilla(dto.getCodigoPlantilla());
        entidad.setValorDescuento(dto.getValorDescuento());
        entidad.setValorObligacion(dto.getValorObligacion());
        entidad.setPuntos(dto.getPuntos());

        return entidad;
    }

    public static FalloImpugnacion toLevel1Entity(FalloImpugnacionDTO dto, FalloImpugnacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }
        if (dto.getTipoFallo() != null) {
            entidad.setTipoFallo(new TipoFallo());
            entidad.getTipoFallo().setId(dto.getTipoFallo().getId());
        }

        return entidad;
    }

    public static List<FalloImpugnacion> toListLevel0Entity(List<FalloImpugnacionDTO> listDto) {
        List<FalloImpugnacion> listEntidad = new ArrayList<FalloImpugnacion>();
        for (FalloImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<FalloImpugnacion> toListLevel1Entity(List<FalloImpugnacionDTO> listDto) {
        List<FalloImpugnacion> listEntidad = new ArrayList<FalloImpugnacion>();
        for (FalloImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
