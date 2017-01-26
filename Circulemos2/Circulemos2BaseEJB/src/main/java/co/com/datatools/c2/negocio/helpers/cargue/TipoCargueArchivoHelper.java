package co.com.datatools.c2.negocio.helpers.cargue;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cargue.TipoCargueArchivoDTO;
import co.com.datatools.c2.entidades.cargue.TipoCargueArchivo;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:57 COT 2016
 */
public class TipoCargueArchivoHelper {
    // --- to DTO
    public static TipoCargueArchivoDTO toLevel0DTO(TipoCargueArchivo entidad) {
        TipoCargueArchivoDTO dto = new TipoCargueArchivoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoCargueArchivoDTO toLevel1DTO(TipoCargueArchivo entidad) {
        TipoCargueArchivoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoCargueArchivoDTO> toListLevel0DTO(List<TipoCargueArchivo> listEntidad) {
        List<TipoCargueArchivoDTO> listDto = new ArrayList<TipoCargueArchivoDTO>();
        for (TipoCargueArchivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoCargueArchivoDTO> toListLevel1DTO(List<TipoCargueArchivo> listEntidad) {
        List<TipoCargueArchivoDTO> listDto = new ArrayList<TipoCargueArchivoDTO>();
        for (TipoCargueArchivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoCargueArchivo toLevel0Entity(TipoCargueArchivoDTO dto, TipoCargueArchivo entidad) {
        if (null == entidad) {
            entidad = new TipoCargueArchivo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoCargueArchivo toLevel1Entity(TipoCargueArchivoDTO dto, TipoCargueArchivo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoCargueArchivo> toListLevel0Entity(List<TipoCargueArchivoDTO> listDto) {
        List<TipoCargueArchivo> listEntidad = new ArrayList<TipoCargueArchivo>();
        for (TipoCargueArchivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoCargueArchivo> toListLevel1Entity(List<TipoCargueArchivoDTO> listDto) {
        List<TipoCargueArchivo> listEntidad = new ArrayList<TipoCargueArchivo>();
        for (TipoCargueArchivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
