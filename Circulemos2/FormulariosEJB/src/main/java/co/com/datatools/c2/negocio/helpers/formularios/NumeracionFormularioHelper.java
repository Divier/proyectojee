package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.entidades.NumeracionFormulario;
import co.com.datatools.c2.entidades.TipoFormulario;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 06 18:08:14 COT 2015
 */
public class NumeracionFormularioHelper {
    // --- to DTO
    public static NumeracionFormularioDTO toLevel0DTO(NumeracionFormulario entidad) {
        NumeracionFormularioDTO dto = new NumeracionFormularioDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicial(entidad.getFechaInicial());
        dto.setFechaFinal(entidad.getFechaFinal());
        dto.setActivo(entidad.getActivo());
        dto.setTrama(entidad.getTrama());
        dto.setDigitos(entidad.getDigitos());
        return dto;
    }

    public static NumeracionFormularioDTO toLevel1DTO(NumeracionFormulario entidad) {
        NumeracionFormularioDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoFormulario() != null)
            dto.setTipoFormulario(TipoFormularioHelper.toLevel0DTO(entidad.getTipoFormulario()));

        return dto;
    }

    public static List<NumeracionFormularioDTO> toListLevel0DTO(List<NumeracionFormulario> listEntidad) {
        List<NumeracionFormularioDTO> listDto = new ArrayList<NumeracionFormularioDTO>();
        for (NumeracionFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NumeracionFormularioDTO> toListLevel1DTO(List<NumeracionFormulario> listEntidad) {
        List<NumeracionFormularioDTO> listDto = new ArrayList<NumeracionFormularioDTO>();
        for (NumeracionFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NumeracionFormulario toLevel0Entity(NumeracionFormularioDTO dto, NumeracionFormulario entidad) {
        if (null == entidad) {
            entidad = new NumeracionFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicial(dto.getFechaInicial());
        entidad.setFechaFinal(dto.getFechaFinal());
        entidad.setActivo(dto.getActivo());
        entidad.setTrama(dto.getTrama());
        entidad.setDigitos(dto.getDigitos());
        return entidad;
    }

    public static NumeracionFormulario toLevel1Entity(NumeracionFormularioDTO dto, NumeracionFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);

        if (dto.getTipoFormulario() != null) {
            entidad.setTipoFormulario(new TipoFormulario());
            entidad.getTipoFormulario().setId(dto.getTipoFormulario().getId());
        }

        return entidad;
    }

    public static List<NumeracionFormulario> toListLevel0Entity(List<NumeracionFormularioDTO> listDto) {
        List<NumeracionFormulario> listEntidad = new ArrayList<NumeracionFormulario>();
        for (NumeracionFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NumeracionFormulario> toListLevel1Entity(List<NumeracionFormularioDTO> listDto) {
        List<NumeracionFormulario> listEntidad = new ArrayList<NumeracionFormulario>();
        for (NumeracionFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
