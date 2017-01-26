package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.entidades.CaracteristicaPrueba;
import co.com.datatools.c2.entidades.OrigenPrueba;
import co.com.datatools.c2.entidades.SolicitudPruebasBack;
import co.com.datatools.c2.entidades.TipoDestinoPruebaImpug;
import co.com.datatools.c2.entidades.TipoPrueba;
import co.com.datatools.c2.entidades.TrazabilidadProceso;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:07:11 COT 2016
 */
public class SolicitudPruebasBackHelper {
    // --- to DTO
    public static SolicitudPruebasBackDTO toLevel0DTO(SolicitudPruebasBack entidad) {
        SolicitudPruebasBackDTO dto = new SolicitudPruebasBackDTO();
        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setFechaSolicitud(entidad.getFechaSolicitud());
        dto.setFechaLimite(entidad.getFechaLimite());
        dto.setConsecutivoDocumento(entidad.getConsecutivoDocumento());
        return dto;
    }

    public static SolicitudPruebasBackDTO toLevel1DTO(SolicitudPruebasBack entidad) {
        SolicitudPruebasBackDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoPrueba() != null)
            dto.setTipoPrueba(TipoPruebaHelper.toLevel0DTO(entidad.getTipoPrueba()));
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));
        if (entidad.getCaracteristicaPrueba() != null)
            dto.setCaracteristicaPrueba(CaracteristicaPruebaHelper.toLevel0DTO(entidad.getCaracteristicaPrueba()));
        if (entidad.getOrigenPrueba() != null)
            dto.setOrigenPrueba(OrigenPruebaHelper.toLevel0DTO(entidad.getOrigenPrueba()));

        return dto;
    }

    public static List<SolicitudPruebasBackDTO> toListLevel0DTO(List<SolicitudPruebasBack> listEntidad) {
        List<SolicitudPruebasBackDTO> listDto = new ArrayList<SolicitudPruebasBackDTO>();
        for (SolicitudPruebasBack entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<SolicitudPruebasBackDTO> toListLevel1DTO(List<SolicitudPruebasBack> listEntidad) {
        List<SolicitudPruebasBackDTO> listDto = new ArrayList<SolicitudPruebasBackDTO>();
        for (SolicitudPruebasBack entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static SolicitudPruebasBack toLevel0Entity(SolicitudPruebasBackDTO dto, SolicitudPruebasBack entidad) {
        if (null == entidad) {
            entidad = new SolicitudPruebasBack();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setFechaSolicitud(dto.getFechaSolicitud());
        entidad.setFechaLimite(dto.getFechaLimite());
        // entidad.setPara(dto.getPara());
        entidad.setDestinoPruebaOtro(dto.getDestinoPruebaOtro());
        entidad.setConsecutivoDocumento(dto.getConsecutivoDocumento());

        return entidad;
    }

    public static SolicitudPruebasBack toLevel1Entity(SolicitudPruebasBackDTO dto, SolicitudPruebasBack entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoPrueba() != null) {
            entidad.setTipoPrueba(new TipoPrueba());
            entidad.getTipoPrueba().setId(dto.getTipoPrueba().getId());
        }
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }
        if (dto.getCaracteristicaPrueba() != null) {
            entidad.setCaracteristicaPrueba(new CaracteristicaPrueba());
            entidad.getCaracteristicaPrueba().setId(dto.getCaracteristicaPrueba().getId());
        }
        if (dto.getOrigenPrueba() != null) {
            entidad.setOrigenPrueba(new OrigenPrueba());
            entidad.getOrigenPrueba().setId(dto.getOrigenPrueba().getId());
        }
        if (dto.getTipoDestinoPruebaImpug() != null) {
            entidad.setTipoDestinoPruebaImpug(new TipoDestinoPruebaImpug());
            entidad.getTipoDestinoPruebaImpug().setId(dto.getTipoDestinoPruebaImpug().getId());
        }

        return entidad;
    }

    public static List<SolicitudPruebasBack> toListLevel0Entity(List<SolicitudPruebasBackDTO> listDto) {
        List<SolicitudPruebasBack> listEntidad = new ArrayList<SolicitudPruebasBack>();
        for (SolicitudPruebasBackDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<SolicitudPruebasBack> toListLevel1Entity(List<SolicitudPruebasBackDTO> listDto) {
        List<SolicitudPruebasBack> listEntidad = new ArrayList<SolicitudPruebasBack>();
        for (SolicitudPruebasBackDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
