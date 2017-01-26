package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.MotivacionImpugnacionDTO;
import co.com.datatools.c2.entidades.MotivacionImpugnacion;
import co.com.datatools.c2.entidades.OrigenImpugnacion;
import co.com.datatools.c2.entidades.TipoPeticion;
import co.com.datatools.c2.entidades.TrazabilidadProceso;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:06:01 COT 2016
 */
public class MotivacionImpugnacionHelper {
    // --- to DTO
    public static MotivacionImpugnacionDTO toLevel0DTO(MotivacionImpugnacion entidad) {
        MotivacionImpugnacionDTO dto = new MotivacionImpugnacionDTO();
        dto.setId(entidad.getId());
        dto.setDescripcionHechos(entidad.getDescripcionHechos());
        dto.setNumeroArchivoEscrito(entidad.getNumeroArchivoEscrito());
        dto.setFechaCargueArchivo(entidad.getFechaCargueArchivo());
        return dto;
    }

    public static MotivacionImpugnacionDTO toLevel1DTO(MotivacionImpugnacion entidad) {
        MotivacionImpugnacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));
        if (entidad.getTipoPeticion() != null)
            dto.setTipoPeticion(TipoPeticionHelper.toLevel0DTO(entidad.getTipoPeticion()));
        if (entidad.getOrigenImpugnacion() != null)
            dto.setOrigenImpugnacion(OrigenImpugnacionHelper.toLevel0DTO(entidad.getOrigenImpugnacion()));
        return dto;
    }

    public static List<MotivacionImpugnacionDTO> toListLevel0DTO(List<MotivacionImpugnacion> listEntidad) {
        List<MotivacionImpugnacionDTO> listDto = new ArrayList<MotivacionImpugnacionDTO>();
        for (MotivacionImpugnacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<MotivacionImpugnacionDTO> toListLevel1DTO(List<MotivacionImpugnacion> listEntidad) {
        List<MotivacionImpugnacionDTO> listDto = new ArrayList<MotivacionImpugnacionDTO>();
        for (MotivacionImpugnacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static MotivacionImpugnacion toLevel0Entity(MotivacionImpugnacionDTO dto, MotivacionImpugnacion entidad) {
        if (null == entidad) {
            entidad = new MotivacionImpugnacion();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcionHechos(dto.getDescripcionHechos());
        entidad.setNumeroArchivoEscrito(dto.getNumeroArchivoEscrito());
        entidad.setFechaCargueArchivo(dto.getFechaCargueArchivo());
        return entidad;
    }

    public static MotivacionImpugnacion toLevel1Entity(MotivacionImpugnacionDTO dto, MotivacionImpugnacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }
        if (dto.getTipoPeticion() != null) {
            entidad.setTipoPeticion(new TipoPeticion());
            entidad.getTipoPeticion().setId(dto.getTipoPeticion().getId());
        }
        if (dto.getOrigenImpugnacion() != null) {
            entidad.setOrigenImpugnacion(new OrigenImpugnacion());
            entidad.getOrigenImpugnacion().setId(dto.getOrigenImpugnacion().getId());
        }
        return entidad;
    }

    public static List<MotivacionImpugnacion> toListLevel0Entity(List<MotivacionImpugnacionDTO> listDto) {
        List<MotivacionImpugnacion> listEntidad = new ArrayList<MotivacionImpugnacion>();
        for (MotivacionImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<MotivacionImpugnacion> toListLevel1Entity(List<MotivacionImpugnacionDTO> listDto) {
        List<MotivacionImpugnacion> listEntidad = new ArrayList<MotivacionImpugnacion>();
        for (MotivacionImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
