package co.com.datatools.fachadaintegracionweb.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.fachadainetegracionweb.dto.GacetaDTO;
import co.com.datatools.fachadaintegracionweb.entidades.Gaceta;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class GacetaHelper {
    // --- to DTO
    public static GacetaDTO toLevel0DTO(Gaceta entidad) {
        GacetaDTO dto = new GacetaDTO();
        dto.setId(entidad.getId());
        dto.setNumero(entidad.getNumero());
        dto.setFechaPublicacion(entidad.getFechaPublicacion());
        dto.setFechaPeriodoInicio(entidad.getFechaPeriodoInicio());
        dto.setFechaPeriodoFin(entidad.getFechaPeriodoFin());
        return dto;
    }

    public static GacetaDTO toLevel1DTO(Gaceta entidad) {
        GacetaDTO dto = toLevel0DTO(entidad);
        return dto;
    }

    public static List<GacetaDTO> toListLevel0DTO(List<Gaceta> listEntidad) {
        List<GacetaDTO> listDto = new ArrayList<GacetaDTO>();
        for (Gaceta entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<GacetaDTO> toListLevel1DTO(List<Gaceta> listEntidad) {
        List<GacetaDTO> listDto = new ArrayList<GacetaDTO>();
        for (Gaceta entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Gaceta
    public static Gaceta toLevel0Entity(GacetaDTO dto, Gaceta entidad) {
        if (null == entidad) {
            entidad = new Gaceta();
        }
        entidad.setId(dto.getId());
        entidad.setNumero(dto.getNumero());
        entidad.setFechaPublicacion(dto.getFechaPublicacion());
        entidad.setFechaPeriodoInicio(dto.getFechaPeriodoInicio());
        entidad.setFechaPeriodoFin(dto.getFechaPeriodoFin());
        return entidad;
    }

    public static Gaceta toLevel1Entity(GacetaDTO dto, Gaceta entidad) {
        entidad = toLevel0Entity(dto, entidad);
        return entidad;
    }

    public static List<Gaceta> toListLevel0Entity(List<GacetaDTO> listDto) {
        List<Gaceta> listEntidad = new ArrayList<Gaceta>();
        for (GacetaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Gaceta> toListLevel1Entity(List<GacetaDTO> listDto) {
        List<Gaceta> listEntidad = new ArrayList<Gaceta>();
        for (GacetaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
