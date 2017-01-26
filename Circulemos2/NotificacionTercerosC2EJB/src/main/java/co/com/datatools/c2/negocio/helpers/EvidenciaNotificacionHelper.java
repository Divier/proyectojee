package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EvidenciaNotificacionDTO;
import co.com.datatools.c2.entidades.DetalleNotificacion;
import co.com.datatools.c2.entidades.EvidenciaNotificacion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 14:25:05 COT 2016
 */
public class EvidenciaNotificacionHelper {
    // --- to DTO
    public static EvidenciaNotificacionDTO toLevel0DTO(EvidenciaNotificacion entidad) {
        EvidenciaNotificacionDTO dto = new EvidenciaNotificacionDTO();
        dto.setId(entidad.getId());
        dto.setCodigoEvidencia(entidad.getCodigoEvidencia());
        dto.setFechaModificacion(entidad.getFechaModificacion());
        dto.setIdEvidenciaExterno(entidad.getIdEvidenciaExterno());
        dto.setTipoEvidencia(entidad.getTipoEvidencia());
        return dto;
    }

    public static EvidenciaNotificacionDTO toLevel1DTO(EvidenciaNotificacion entidad) {
        EvidenciaNotificacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getDetalleNotificacion() != null)
            dto.setDetalleNotificacion(DetalleNotificacionHelper.toLevel0DTO(entidad.getDetalleNotificacion()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));

        return dto;
    }

    public static List<EvidenciaNotificacionDTO> toListLevel0DTO(List<EvidenciaNotificacion> listEntidad) {
        List<EvidenciaNotificacionDTO> listDto = new ArrayList<EvidenciaNotificacionDTO>();
        for (EvidenciaNotificacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EvidenciaNotificacionDTO> toListLevel1DTO(List<EvidenciaNotificacion> listEntidad) {
        List<EvidenciaNotificacionDTO> listDto = new ArrayList<EvidenciaNotificacionDTO>();
        for (EvidenciaNotificacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EvidenciaNotificacion toLevel0Entity(EvidenciaNotificacionDTO dto, EvidenciaNotificacion entidad) {
        if (null == entidad) {
            entidad = new EvidenciaNotificacion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoEvidencia(dto.getCodigoEvidencia());
        entidad.setFechaModificacion(dto.getFechaModificacion());
        entidad.setIdEvidenciaExterno(dto.getIdEvidenciaExterno());
        entidad.setTipoEvidencia(dto.getTipoEvidencia());
        return entidad;
    }

    public static EvidenciaNotificacion toLevel1Entity(EvidenciaNotificacionDTO dto, EvidenciaNotificacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDetalleNotificacion() != null) {
            entidad.setDetalleNotificacion(new DetalleNotificacion());
            entidad.getDetalleNotificacion().setId(dto.getDetalleNotificacion().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }
        return entidad;
    }

    public static List<EvidenciaNotificacion> toListLevel0Entity(List<EvidenciaNotificacionDTO> listDto) {
        List<EvidenciaNotificacion> listEntidad = new ArrayList<EvidenciaNotificacion>();
        for (EvidenciaNotificacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EvidenciaNotificacion> toListLevel1Entity(List<EvidenciaNotificacionDTO> listDto) {
        List<EvidenciaNotificacion> listEntidad = new ArrayList<EvidenciaNotificacion>();
        for (EvidenciaNotificacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}