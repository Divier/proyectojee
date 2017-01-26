package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.DocumentoFormulario;
import co.com.datatools.c2.entidades.NumeracionFormulario;
import co.com.datatools.c2.entidades.RangoFormulario;
import co.com.datatools.c2.entidades.TipoFormulario;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 02 10:47:14 COT 2015
 */
public class RangoFormularioHelper {
    // --- to DTO
    public static RangoFormularioDTO toLevel0DTO(RangoFormulario entidad) {
        RangoFormularioDTO dto = new RangoFormularioDTO();
        dto.setId(entidad.getId());
        dto.setNumeroInicial(entidad.getNumeroInicial());
        dto.setNumeroFinal(entidad.getNumeroFinal());
        dto.setFechaAutorizacion(entidad.getFechaAutorizacion());
        dto.setAlertaStock(entidad.getAlertaStock());
        dto.setCantidadTotal(entidad.getCantidadTotal());
        dto.setCantidadDisponible(entidad.getCantidadDisponible());

        return dto;
    }

    public static RangoFormularioDTO toLevel1DTO(RangoFormulario entidad) {
        RangoFormularioDTO dto = toLevel0DTO(entidad);
        if (entidad.getCodigoOrganismo() != null)
            dto.setCodigoOrganismo(OrganismoTransitoHelper.toLevel0DTO(entidad.getCodigoOrganismo()));
        if (entidad.getDocumentoFormulario() != null)
            dto.setDocumentoFormulario(DocumentoFormularioHelper.toLevel0DTO(entidad.getDocumentoFormulario()));
        if (entidad.getTipoFormulario() != null)
            dto.setTipoFormulario(TipoFormularioHelper.toLevel0DTO(entidad.getTipoFormulario()));
        if (entidad.getNumeracion() != null)
            dto.setNumeracion(NumeracionFormularioHelper.toLevel0DTO(entidad.getNumeracion()));
        if (entidad.getDetalleCambioEstadoList() != null) {
            for (DetalleCambioEstado detalle : entidad.getDetalleCambioEstadoList()) {
                dto.setTieneDetalles(true);
                break;
            }
        }

        return dto;
    }

    public static List<RangoFormularioDTO> toListLevel0DTO(List<RangoFormulario> listEntidad) {
        List<RangoFormularioDTO> listDto = new ArrayList<RangoFormularioDTO>();
        for (RangoFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RangoFormularioDTO> toListLevel1DTO(List<RangoFormulario> listEntidad) {
        List<RangoFormularioDTO> listDto = new ArrayList<RangoFormularioDTO>();
        for (RangoFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RangoFormulario toLevel0Entity(RangoFormularioDTO dto, RangoFormulario entidad) {
        if (null == entidad) {
            entidad = new RangoFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroInicial(dto.getNumeroInicial());
        entidad.setNumeroFinal(dto.getNumeroFinal());
        entidad.setFechaAutorizacion(dto.getFechaAutorizacion());
        entidad.setAlertaStock(dto.getAlertaStock());
        entidad.setCantidadTotal(dto.getCantidadTotal());
        entidad.setCantidadDisponible(dto.getCantidadDisponible());

        return entidad;
    }

    public static RangoFormulario toLevel1Entity(RangoFormularioDTO dto, RangoFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCodigoOrganismo() != null) {
            entidad.setCodigoOrganismo(new OrganismoTransito());
            entidad.getCodigoOrganismo().setCodigoOrganismo(dto.getCodigoOrganismo().getCodigoOrganismo());
        }
        if (dto.getDocumentoFormulario() != null) {
            entidad.setDocumentoFormulario(new DocumentoFormulario());
            entidad.getDocumentoFormulario().setId(dto.getDocumentoFormulario().getId());
        }
        if (dto.getTipoFormulario() != null) {
            entidad.setTipoFormulario(new TipoFormulario());
            entidad.getTipoFormulario().setId(dto.getTipoFormulario().getId());
        }
        if (dto.getNumeracion() != null) {
            entidad.setNumeracion(new NumeracionFormulario());
            entidad.getNumeracion().setId(dto.getNumeracion().getId());
        }

        return entidad;
    }

    public static List<RangoFormulario> toListLevel0Entity(List<RangoFormularioDTO> listDto) {
        List<RangoFormulario> listEntidad = new ArrayList<RangoFormulario>();
        for (RangoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RangoFormulario> toListLevel1Entity(List<RangoFormularioDTO> listDto) {
        List<RangoFormulario> listEntidad = new ArrayList<RangoFormulario>();
        for (RangoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
