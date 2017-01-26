package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.TipoCategLicenciaConduccionDTO;
import co.com.datatools.c2.entidades.TipoCategLicenciaConduccion;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 17:17:50 COT 2016
 */
public class TipoCategLicenciaConduccionHelper {
    // --- to DTO
    public static TipoCategLicenciaConduccionDTO toLevel0DTO(TipoCategLicenciaConduccion entidad) {
        TipoCategLicenciaConduccionDTO dto = new TipoCategLicenciaConduccionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoCategLicenciaConduccionDTO toLevel1DTO(TipoCategLicenciaConduccion entidad) {
        TipoCategLicenciaConduccionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoCategLicenciaConduccionDTO> toListLevel0DTO(List<TipoCategLicenciaConduccion> listEntidad) {
        List<TipoCategLicenciaConduccionDTO> listDto = new ArrayList<TipoCategLicenciaConduccionDTO>();
        for (TipoCategLicenciaConduccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoCategLicenciaConduccionDTO> toListLevel1DTO(List<TipoCategLicenciaConduccion> listEntidad) {
        List<TipoCategLicenciaConduccionDTO> listDto = new ArrayList<TipoCategLicenciaConduccionDTO>();
        for (TipoCategLicenciaConduccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoCategLicenciaConduccion toLevel0Entity(TipoCategLicenciaConduccionDTO dto,
            TipoCategLicenciaConduccion entidad) {
        if (null == entidad) {
            entidad = new TipoCategLicenciaConduccion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoCategLicenciaConduccion toLevel1Entity(TipoCategLicenciaConduccionDTO dto,
            TipoCategLicenciaConduccion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoCategLicenciaConduccion> toListLevel0Entity(List<TipoCategLicenciaConduccionDTO> listDto) {
        List<TipoCategLicenciaConduccion> listEntidad = new ArrayList<TipoCategLicenciaConduccion>();
        for (TipoCategLicenciaConduccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoCategLicenciaConduccion> toListLevel1Entity(List<TipoCategLicenciaConduccionDTO> listDto) {
        List<TipoCategLicenciaConduccion> listEntidad = new ArrayList<TipoCategLicenciaConduccion>();
        for (TipoCategLicenciaConduccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
