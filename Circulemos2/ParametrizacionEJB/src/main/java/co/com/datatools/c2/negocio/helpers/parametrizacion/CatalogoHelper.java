package co.com.datatools.c2.negocio.helpers.parametrizacion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.entidades.Catalogo;

/**
 * @author Generated
 * @version 3.0 - Mon Feb 23 11:29:13 COT 2015
 */
public class CatalogoHelper {
    // --- to DTO
    public static CatalogoDTO toLevel0DTO(Catalogo entidad) {
        CatalogoDTO dto = new CatalogoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setCodigo(entidad.getCodigo());
        dto.setSigla(entidad.getSigla());
        dto.setEditable(entidad.getEditable());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setNombreEntidad(entidad.getNombreEntidad());
        dto.setPaqueteEntidad(entidad.getPaqueteEntidad());

        return dto;
    }

    public static CatalogoDTO toLevel1DTO(Catalogo entidad) {
        CatalogoDTO dto = toLevel0DTO(entidad);
        // Catalogo dependencia
        if (entidad.getIdCatalogoDependencia() != null) {
            dto.setCatalogoDependenciaDTO(toLevel1DTO(entidad.getIdCatalogoDependencia()));
            dto.setIdCatalogoDependencia(true);
        } else {
            dto.setIdCatalogoDependencia(false);
        }
        return dto;
    }

    public static List<CatalogoDTO> toListLevel0DTO(List<Catalogo> listEntidad) {
        List<CatalogoDTO> listDto = new ArrayList<CatalogoDTO>();
        for (Catalogo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CatalogoDTO> toListLevel1DTO(List<Catalogo> listEntidad) {
        List<CatalogoDTO> listDto = new ArrayList<CatalogoDTO>();
        for (Catalogo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Catalogo toLevel0Entity(CatalogoDTO dto, Catalogo entidad) {
        if (null == entidad) {
            entidad = new Catalogo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setCodigo(dto.getCodigo());
        entidad.setSigla(dto.getSigla());
        entidad.setEditable(dto.getEditable());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setNombreEntidad(dto.getNombreEntidad());
        entidad.setPaqueteEntidad(dto.getPaqueteEntidad());

        return entidad;
    }

    public static Catalogo toLevel1Entity(CatalogoDTO dto, Catalogo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Catalogo> toListLevel0Entity(List<CatalogoDTO> listDto) {
        List<Catalogo> listEntidad = new ArrayList<Catalogo>();
        for (CatalogoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Catalogo> toListLevel1Entity(List<CatalogoDTO> listDto) {
        List<Catalogo> listEntidad = new ArrayList<Catalogo>();
        for (CatalogoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
