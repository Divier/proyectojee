package co.com.datatools.c2.negocio.helpers.cargue;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.entidades.cargue.CargueArchivo;
import co.com.datatools.c2.entidades.cargue.EstadoCargueArchivo;
import co.com.datatools.c2.entidades.cargue.TipoCargueArchivo;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:45 COT 2016
 */
public class CargueArchivoHelper {
    // --- to DTO
    public static CargueArchivoDTO toLevel0DTO(CargueArchivo entidad) {
        CargueArchivoDTO dto = new CargueArchivoDTO();
        dto.setId(entidad.getId());
        dto.setConsecutivo(entidad.getConsecutivo());
        dto.setFechaCargue(entidad.getFechaCargue());
        dto.setIdDocumentoCargue(entidad.getIdDocumentoCargue());
        dto.setIdDocumentoInconsistencias(entidad.getIdDocumentoInconsistencias());
        dto.setTotalInconsistencias(entidad.getTotalInconsistencias());
        dto.setTotalLeidos(entidad.getTotalLeidos());
        dto.setTotalRegistros(entidad.getTotalRegistros());
        dto.setTotalActualizados(entidad.getTotalActualizados());
        dto.setTotalAgregados(entidad.getTotalAgregados());
        dto.setFechaFinaliza(entidad.getFechaFinaliza());

        return dto;
    }

    public static CargueArchivoDTO toLevel1DTO(CargueArchivo entidad) {
        CargueArchivoDTO dto = toLevel0DTO(entidad);

        if (entidad.getUsuarioCargue() != null)
            dto.setUsuarioCargue(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioCargue()));

        if (entidad.getTipoCargueArchivo() != null)
            dto.setTipoCargueArchivo(TipoCargueArchivoHelper.toLevel0DTO(entidad.getTipoCargueArchivo()));

        if (entidad.getEstadoCargueArchivo() != null)
            dto.setEstadoCargueArchivo(EstadoCargueArchivoHelper.toLevel0DTO(entidad.getEstadoCargueArchivo()));
        return dto;
    }

    public static List<CargueArchivoDTO> toListLevel0DTO(List<CargueArchivo> listEntidad) {
        List<CargueArchivoDTO> listDto = new ArrayList<CargueArchivoDTO>();
        for (CargueArchivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CargueArchivoDTO> toListLevel1DTO(List<CargueArchivo> listEntidad) {
        List<CargueArchivoDTO> listDto = new ArrayList<CargueArchivoDTO>();
        for (CargueArchivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CargueArchivo toLevel0Entity(CargueArchivoDTO dto, CargueArchivo entidad) {
        if (null == entidad) {
            entidad = new CargueArchivo();
        }
        entidad.setId(dto.getId());
        entidad.setConsecutivo(dto.getConsecutivo());
        entidad.setFechaCargue(dto.getFechaCargue());
        entidad.setIdDocumentoCargue(dto.getIdDocumentoCargue());
        entidad.setIdDocumentoInconsistencias(dto.getIdDocumentoInconsistencias());
        entidad.setTotalInconsistencias(dto.getTotalInconsistencias());
        entidad.setTotalLeidos(dto.getTotalLeidos());
        entidad.setTotalRegistros(dto.getTotalRegistros());
        entidad.setTotalActualizados(dto.getTotalActualizados());
        entidad.setTotalAgregados(dto.getTotalAgregados());
        entidad.setFechaFinaliza(dto.getFechaFinaliza());
        return entidad;
    }

    public static CargueArchivo toLevel1Entity(CargueArchivoDTO dto, CargueArchivo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        if (dto.getUsuarioCargue() != null) {
            entidad.setUsuarioCargue(new UsuarioPersona());
            entidad.getUsuarioCargue().setIdUsuario(dto.getUsuarioCargue().getUsuario().getId());
        }

        if (dto.getTipoCargueArchivo() != null) {
            entidad.setTipoCargueArchivo(new TipoCargueArchivo(dto.getTipoCargueArchivo().getId()));
        }

        if (dto.getEstadoCargueArchivo() != null) {
            entidad.setEstadoCargueArchivo(new EstadoCargueArchivo(dto.getEstadoCargueArchivo().getId()));
        }
        return entidad;
    }

    public static List<CargueArchivo> toListLevel0Entity(List<CargueArchivoDTO> listDto) {
        List<CargueArchivo> listEntidad = new ArrayList<CargueArchivo>();
        for (CargueArchivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CargueArchivo> toListLevel1Entity(List<CargueArchivoDTO> listDto) {
        List<CargueArchivo> listEntidad = new ArrayList<CargueArchivo>();
        for (CargueArchivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
