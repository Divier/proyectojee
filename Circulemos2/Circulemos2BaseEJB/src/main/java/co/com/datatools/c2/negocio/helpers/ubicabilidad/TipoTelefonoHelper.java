package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.TipoTelefonoDTO;
import co.com.datatools.c2.entidades.ubicabilidad.TipoTelefono;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:57 COT 2016
 */
public class TipoTelefonoHelper {
    // --- to DTO
    public static TipoTelefonoDTO toLevel0DTO(TipoTelefono entidad) {
        TipoTelefonoDTO dto = new TipoTelefonoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoTelefonoDTO toLevel1DTO(TipoTelefono entidad) {
        TipoTelefonoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoTelefonoDTO> toListLevel0DTO(List<TipoTelefono> listEntidad) {
        List<TipoTelefonoDTO> listDto = new ArrayList<TipoTelefonoDTO>();
        for (TipoTelefono entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoTelefonoDTO> toListLevel1DTO(List<TipoTelefono> listEntidad) {
        List<TipoTelefonoDTO> listDto = new ArrayList<TipoTelefonoDTO>();
        for (TipoTelefono entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoTelefono toLevel0Entity(TipoTelefonoDTO dto, TipoTelefono entidad) {
        if (null == entidad) {
            entidad = new TipoTelefono();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoTelefono toLevel1Entity(TipoTelefonoDTO dto, TipoTelefono entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoTelefono> toListLevel0Entity(List<TipoTelefonoDTO> listDto) {
        List<TipoTelefono> listEntidad = new ArrayList<TipoTelefono>();
        for (TipoTelefonoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoTelefono> toListLevel1Entity(List<TipoTelefonoDTO> listDto) {
        List<TipoTelefono> listEntidad = new ArrayList<TipoTelefono>();
        for (TipoTelefonoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
