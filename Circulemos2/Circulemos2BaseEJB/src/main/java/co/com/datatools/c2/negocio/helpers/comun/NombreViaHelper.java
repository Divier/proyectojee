package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.NombreVia;
import co.com.datatools.c2.entidades.comun.TipoVia;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class NombreViaHelper {
    // --- to DTO
    public static NombreViaDTO toLevel0DTO(NombreVia entidad) {
        NombreViaDTO dto = new NombreViaDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static NombreViaDTO toLevel1DTO(NombreVia entidad) {
        NombreViaDTO dto = toLevel0DTO(entidad);
        if (entidad.getMunicipio() != null)
            dto.setMunicipio(MunicipioHelper.toLevel0DTO(entidad.getMunicipio()));
        if (entidad.getTipoVia() != null)
            dto.setTipoVia(TipoViaHelper.toLevel0DTO(entidad.getTipoVia()));

        return dto;
    }

    public static List<NombreViaDTO> toListLevel0DTO(List<NombreVia> listEntidad) {
        List<NombreViaDTO> listDto = new ArrayList<NombreViaDTO>();
        for (NombreVia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NombreViaDTO> toListLevel1DTO(List<NombreVia> listEntidad) {
        List<NombreViaDTO> listDto = new ArrayList<NombreViaDTO>();
        for (NombreVia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NombreVia toLevel0Entity(NombreViaDTO dto, NombreVia entidad) {
        if (null == entidad) {
            entidad = new NombreVia();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static NombreVia toLevel1Entity(NombreViaDTO dto, NombreVia entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getMunicipio() != null) {
            entidad.setMunicipio(new Municipio());
            entidad.getMunicipio().setId(dto.getMunicipio().getId());
        }
        if (dto.getTipoVia() != null) {
            entidad.setTipoVia(new TipoVia());
            entidad.getTipoVia().setCodigo(dto.getTipoVia().getCodigo());
        }

        return entidad;
    }

    public static List<NombreVia> toListLevel0Entity(List<NombreViaDTO> listDto) {
        List<NombreVia> listEntidad = new ArrayList<NombreVia>();
        for (NombreViaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NombreVia> toListLevel1Entity(List<NombreViaDTO> listDto) {
        List<NombreVia> listEntidad = new ArrayList<NombreVia>();
        for (NombreViaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
