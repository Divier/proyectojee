package co.com.datatools.c2.negocio.helpers.cargue;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cargue.EstadoCargueArchivoDTO;
import co.com.datatools.c2.entidades.cargue.EstadoCargueArchivo;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:57 COT 2016
 */
public class EstadoCargueArchivoHelper {
    // --- to DTO
    public static EstadoCargueArchivoDTO toLevel0DTO(EstadoCargueArchivo entidad) {
        EstadoCargueArchivoDTO dto = new EstadoCargueArchivoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoCargueArchivoDTO toLevel1DTO(EstadoCargueArchivo entidad) {
        EstadoCargueArchivoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoCargueArchivoDTO> toListLevel0DTO(List<EstadoCargueArchivo> listEntidad) {
        List<EstadoCargueArchivoDTO> listDto = new ArrayList<EstadoCargueArchivoDTO>();
        for (EstadoCargueArchivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoCargueArchivoDTO> toListLevel1DTO(List<EstadoCargueArchivo> listEntidad) {
        List<EstadoCargueArchivoDTO> listDto = new ArrayList<EstadoCargueArchivoDTO>();
        for (EstadoCargueArchivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoCargueArchivo toLevel0Entity(EstadoCargueArchivoDTO dto, EstadoCargueArchivo entidad) {
        if (null == entidad) {
            entidad = new EstadoCargueArchivo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoCargueArchivo toLevel1Entity(EstadoCargueArchivoDTO dto, EstadoCargueArchivo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoCargueArchivo> toListLevel0Entity(List<EstadoCargueArchivoDTO> listDto) {
        List<EstadoCargueArchivo> listEntidad = new ArrayList<EstadoCargueArchivo>();
        for (EstadoCargueArchivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoCargueArchivo> toListLevel1Entity(List<EstadoCargueArchivoDTO> listDto) {
        List<EstadoCargueArchivo> listEntidad = new ArrayList<EstadoCargueArchivo>();
        for (EstadoCargueArchivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
