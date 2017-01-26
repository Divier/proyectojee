package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.NotificacionSimitDTO;
import co.com.datatools.c2.entidades.NotificacionSimit;
import co.com.datatools.c2.entidades.TipoDocumentoEnvioSimit;
import co.com.datatools.c2.entidades.TipoResultadoEnvioSimit;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 02 15:31:39 COT 2015
 */
public class NotificacionSimitHelper {
    // --- to DTO
    public static NotificacionSimitDTO toLevel0DTO(NotificacionSimit entidad) {
        NotificacionSimitDTO dto = new NotificacionSimitDTO();
        dto.setId(entidad.getId());
        dto.setCantidadRegistros(entidad.getCantidadRegistros());
        dto.setFechaGeneracion(entidad.getFechaGeneracion());

        return dto;
    }

    public static NotificacionSimitDTO toLevel1DTO(NotificacionSimit entidad) {
        NotificacionSimitDTO dto = toLevel0DTO(entidad);
        if (entidad.getCodigoOrganismo() != null)
            dto.setCodigoOrganismo(OrganismoTransitoHelper.toLevel0DTO(entidad.getCodigoOrganismo()));
        if (entidad.getTipoDocumentoEnvioSimit() != null)
            dto.setTipoDocumentoEnvioSimit(TipoDocumentoEnvioSimitHelper.toLevel0DTO(entidad
                    .getTipoDocumentoEnvioSimit()));
        if (entidad.getTipoResultadoEnvioSimit() != null)
            dto.setTipoResultadoEnvioSimit(TipoResultadoEnvioSimitHelper.toLevel0DTO(entidad
                    .getTipoResultadoEnvioSimit()));

        return dto;
    }

    public static List<NotificacionSimitDTO> toListLevel0DTO(List<NotificacionSimit> listEntidad) {
        List<NotificacionSimitDTO> listDto = new ArrayList<NotificacionSimitDTO>();
        for (NotificacionSimit entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NotificacionSimitDTO> toListLevel1DTO(List<NotificacionSimit> listEntidad) {
        List<NotificacionSimitDTO> listDto = new ArrayList<NotificacionSimitDTO>();
        for (NotificacionSimit entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NotificacionSimit toLevel0Entity(NotificacionSimitDTO dto, NotificacionSimit entidad) {
        if (null == entidad) {
            entidad = new NotificacionSimit();
        }
        entidad.setId(dto.getId());
        entidad.setCantidadRegistros(dto.getCantidadRegistros());
        entidad.setFechaGeneracion(dto.getFechaGeneracion());

        return entidad;
    }

    public static NotificacionSimit toLevel1Entity(NotificacionSimitDTO dto, NotificacionSimit entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCodigoOrganismo() != null) {
            entidad.setCodigoOrganismo(new OrganismoTransito());
            entidad.getCodigoOrganismo().setCodigoOrganismo(dto.getCodigoOrganismo().getCodigoOrganismo());
        }
        if (dto.getTipoDocumentoEnvioSimit() != null) {
            entidad.setTipoDocumentoEnvioSimit(new TipoDocumentoEnvioSimit());
            entidad.getTipoDocumentoEnvioSimit().setId(dto.getTipoDocumentoEnvioSimit().getId());
        }
        if (dto.getTipoResultadoEnvioSimit() != null) {
            entidad.setTipoResultadoEnvioSimit(new TipoResultadoEnvioSimit());
            entidad.getTipoResultadoEnvioSimit().setId(dto.getTipoResultadoEnvioSimit().getId());
        }

        return entidad;
    }

    public static List<NotificacionSimit> toListLevel0Entity(List<NotificacionSimitDTO> listDto) {
        List<NotificacionSimit> listEntidad = new ArrayList<NotificacionSimit>();
        for (NotificacionSimitDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NotificacionSimit> toListLevel1Entity(List<NotificacionSimitDTO> listDto) {
        List<NotificacionSimit> listEntidad = new ArrayList<NotificacionSimit>();
        for (NotificacionSimitDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
