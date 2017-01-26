package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CargueCoactivoDTO;
import co.com.datatools.c2.entidades.CargueCoactivo;
import co.com.datatools.c2.entidades.ConfiguracionCoactivo;
import co.com.datatools.c2.entidades.cargue.CargueArchivo;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.cargue.CargueArchivoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class CargueCoactivoHelper {
    // --- to DTO
    public static CargueCoactivoDTO toLevel0DTO(CargueCoactivo entidad) {
        CargueCoactivoDTO dto = new CargueCoactivoDTO();
        dto.setId(entidad.getId());
        dto.setFechaAprobacion(entidad.getFechaAprobacion());

        return dto;
    }

    public static CargueCoactivoDTO toLevel1DTO(CargueCoactivo entidad) {
        CargueCoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getUsuarioAprobacion() != null)
            dto.setUsuarioAprobacion(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioAprobacion()));
        if (entidad.getCargueArchivo() != null)
            dto.setCargueArchivo(CargueArchivoHelper.toLevel0DTO(entidad.getCargueArchivo()));
        if (entidad.getConfiguracionCoactivo() != null)
            dto.setConfiguracionCoactivo(ConfiguracionCoactivoHelper.toLevel0DTO(entidad.getConfiguracionCoactivo()));

        return dto;
    }

    public static List<CargueCoactivoDTO> toListLevel0DTO(List<CargueCoactivo> listEntidad) {
        List<CargueCoactivoDTO> listDto = new ArrayList<CargueCoactivoDTO>();
        for (CargueCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CargueCoactivoDTO> toListLevel1DTO(List<CargueCoactivo> listEntidad) {
        List<CargueCoactivoDTO> listDto = new ArrayList<CargueCoactivoDTO>();
        for (CargueCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CargueCoactivo toLevel0Entity(CargueCoactivoDTO dto, CargueCoactivo entidad) {
        if (null == entidad) {
            entidad = new CargueCoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setFechaAprobacion(dto.getFechaAprobacion());

        return entidad;
    }

    public static CargueCoactivo toLevel1Entity(CargueCoactivoDTO dto, CargueCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getUsuarioAprobacion() != null) {
            entidad.setUsuarioAprobacion(new UsuarioPersona());
            entidad.getUsuarioAprobacion().setIdUsuario(dto.getUsuarioAprobacion().getUsuario().getId());
        }
        if (dto.getCargueArchivo() != null) {
            entidad.setCargueArchivo(new CargueArchivo());
            entidad.getCargueArchivo().setId(dto.getCargueArchivo().getId());
        }
        if (dto.getConfiguracionCoactivo() != null) {
            entidad.setConfiguracionCoactivo(new ConfiguracionCoactivo());
            entidad.getConfiguracionCoactivo().setId(dto.getConfiguracionCoactivo().getId());
        }

        return entidad;
    }

    public static List<CargueCoactivo> toListLevel0Entity(List<CargueCoactivoDTO> listDto) {
        List<CargueCoactivo> listEntidad = new ArrayList<CargueCoactivo>();
        for (CargueCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CargueCoactivo> toListLevel1Entity(List<CargueCoactivoDTO> listDto) {
        List<CargueCoactivo> listEntidad = new ArrayList<CargueCoactivo>();
        for (CargueCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
