package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ParametroDTO;
import co.com.datatools.c2.entidades.Modulo;
import co.com.datatools.c2.entidades.Parametro;
import co.com.datatools.c2.entidades.TipoVariable;

/**
 * @author Generated
 * @version 3.0 - Wed May 28 11:36:01 COT 2014
 */
public class ParametroHelper {
    // --- to DTO
    public static ParametroDTO toLevel0DTO(Parametro entidad) {
        ParametroDTO dto = new ParametroDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setValorDefecto(entidad.getValorDefecto());
        dto.setCodigoUnidad(entidad.getCodigoUnidad());
        dto.setFormato(entidad.getFormato());
        dto.setEditableOrganismo(entidad.getEditableOrganismo());

        return dto;
    }

    public static ParametroDTO toLevel1DTO(Parametro entidad) {
        ParametroDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoVariable() != null)
            dto.setTipoVariable(TipoVariableHelper.toLevel0DTO(entidad.getTipoVariable()));
        if (entidad.getModulo() != null)
            dto.setModulo(ModuloHelper.toLevel0DTO(entidad.getModulo()));

        return dto;
    }

    public static List<ParametroDTO> toListLevel0DTO(List<Parametro> listEntidad) {
        List<ParametroDTO> listDto = new ArrayList<ParametroDTO>();
        for (Parametro entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ParametroDTO> toListLevel1DTO(List<Parametro> listEntidad) {
        List<ParametroDTO> listDto = new ArrayList<ParametroDTO>();
        for (Parametro entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Parametro toLevel0Entity(ParametroDTO dto, Parametro entidad) {
        if (null == entidad) {
            entidad = new Parametro();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setValorDefecto(dto.getValorDefecto());
        entidad.setCodigoUnidad(dto.getCodigoUnidad());
        entidad.setFormato(dto.getFormato());
        entidad.setEditableOrganismo(dto.getEditableOrganismo());

        return entidad;
    }

    public static Parametro toLevel1Entity(ParametroDTO dto, Parametro entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoVariable() != null) {
            entidad.setTipoVariable(new TipoVariable());
            entidad.getTipoVariable().setCodigo(dto.getTipoVariable().getCodigo());
        }
        if (dto.getModulo() != null) {
            entidad.setModulo(new Modulo());
            entidad.getModulo().setId(dto.getModulo().getId());
        }

        return entidad;
    }

    public static List<Parametro> toListLevel0Entity(List<ParametroDTO> listDto) {
        List<Parametro> listEntidad = new ArrayList<Parametro>();
        for (ParametroDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Parametro> toListLevel1Entity(List<ParametroDTO> listDto) {
        List<Parametro> listEntidad = new ArrayList<Parametro>();
        for (ParametroDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
