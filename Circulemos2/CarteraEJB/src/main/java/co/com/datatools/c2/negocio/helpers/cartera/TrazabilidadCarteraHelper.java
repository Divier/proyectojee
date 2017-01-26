package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.TrazabilidadCarteraDTO;
import co.com.datatools.c2.entidades.ActividadCartera;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.EstadoObligacion;
import co.com.datatools.c2.entidades.TrazabilidadCartera;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:28:36 COT 2015
 */
public class TrazabilidadCarteraHelper {
    // --- to DTO
    public static TrazabilidadCarteraDTO toLevel0DTO(TrazabilidadCartera entidad) {
        TrazabilidadCarteraDTO dto = new TrazabilidadCarteraDTO();
        dto.setId(entidad.getId());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setLoginUsuario(entidad.getLoginUsuario());

        return dto;
    }

    public static TrazabilidadCarteraDTO toLevel1DTO(TrazabilidadCartera entidad) {
        TrazabilidadCarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getEstadoObligacion() != null)
            dto.setEstadoObligacion(EstadoObligacionHelper.toLevel0DTO(entidad.getEstadoObligacion()));
        if (entidad.getCartera() != null)
            dto.setCartera(CarteraHelper.toLevel0DTO(entidad.getCartera()));
        if (entidad.getActividadCartera() != null)
            dto.setActividadCartera(ActividadCarteraHelper.toLevel0DTO(entidad.getActividadCartera()));

        return dto;
    }

    public static List<TrazabilidadCarteraDTO> toListLevel0DTO(List<TrazabilidadCartera> listEntidad) {
        List<TrazabilidadCarteraDTO> listDto = new ArrayList<TrazabilidadCarteraDTO>();
        for (TrazabilidadCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TrazabilidadCarteraDTO> toListLevel1DTO(List<TrazabilidadCartera> listEntidad) {
        List<TrazabilidadCarteraDTO> listDto = new ArrayList<TrazabilidadCarteraDTO>();
        for (TrazabilidadCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TrazabilidadCartera toLevel0Entity(TrazabilidadCarteraDTO dto, TrazabilidadCartera entidad) {
        if (null == entidad) {
            entidad = new TrazabilidadCartera();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setLoginUsuario(dto.getLoginUsuario());

        return entidad;
    }

    public static TrazabilidadCartera toLevel1Entity(TrazabilidadCarteraDTO dto, TrazabilidadCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEstadoObligacion() != null) {
            entidad.setEstadoObligacion(new EstadoObligacion());
            entidad.getEstadoObligacion().setId(dto.getEstadoObligacion().getId());
        }
        if (dto.getCartera() != null) {
            entidad.setCartera(new Cartera());
            entidad.getCartera().setId(dto.getCartera().getId());
        }
        if (dto.getActividadCartera() != null) {
            entidad.setActividadCartera(new ActividadCartera());
            entidad.getActividadCartera().setId(dto.getActividadCartera().getId());
        }

        return entidad;
    }

    public static List<TrazabilidadCartera> toListLevel0Entity(List<TrazabilidadCarteraDTO> listDto) {
        List<TrazabilidadCartera> listEntidad = new ArrayList<TrazabilidadCartera>();
        for (TrazabilidadCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TrazabilidadCartera> toListLevel1Entity(List<TrazabilidadCarteraDTO> listDto) {
        List<TrazabilidadCartera> listEntidad = new ArrayList<TrazabilidadCartera>();
        for (TrazabilidadCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
