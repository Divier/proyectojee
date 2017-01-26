package co.com.datatools.fachadaintegracionweb.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.fachadainetegracionweb.dto.CatalogoDTO;
import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;
import co.com.datatools.fachadaintegracionweb.entidades.EntidadCatalogoC2;
import co.com.datatools.fachadaintegracionweb.entidades.EntidadCatalogoCompuestoC2;

/**
 * Clase Helper creada a la medida de la funcionalidad no es generada automaticamente
 * 
 * @author giovanni.velandia
 * 
 */
public class ItemCatalogoHelper {

    // --- to DTO
    public static ItemCatalogoDTO toLevel0DTO(EntidadCatalogoC2 entidadCatalogoC2) {
        ItemCatalogoDTO dto = new ItemCatalogoDTO();
        dto.setId(entidadCatalogoC2.getId());
        dto.setNombre(entidadCatalogoC2.getNombre());
        dto.setCodigo(entidadCatalogoC2.getCodigo());
        dto.setSigla(entidadCatalogoC2.getSigla());
        dto.setActivo(entidadCatalogoC2.getActivo());
        dto.setDescripcion(entidadCatalogoC2.getDescripcion());
        return dto;
    }

    public static ItemCatalogoDTO toLevel1DTO(EntidadCatalogoCompuestoC2 entidadCatalogoCompuestoC2) {
        ItemCatalogoDTO dto = toLevel0DTO(entidadCatalogoCompuestoC2);

        if (entidadCatalogoCompuestoC2.getDependencia() != null) {
            dto.setItemCatalogoDependenciaDTO(toLevel0DTO(entidadCatalogoCompuestoC2.getDependencia()));
        }
        return dto;
    }

    public static List<ItemCatalogoDTO> toListLevel0DTO(List<EntidadCatalogoC2> listEntidad) {
        List<ItemCatalogoDTO> listDto = new ArrayList<ItemCatalogoDTO>();
        for (EntidadCatalogoC2 entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ItemCatalogoDTO> toListLevel1DTO(List<EntidadCatalogoCompuestoC2> listEntidad) {
        List<ItemCatalogoDTO> listDto = new ArrayList<ItemCatalogoDTO>();
        for (EntidadCatalogoCompuestoC2 entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to Entidad
    // --- to Entidad
    public static EntidadCatalogoC2 toLevel0Entity(ItemCatalogoDTO dto, String nombreEntidad, String nombrePaquete)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        @SuppressWarnings("rawtypes")
        Class entidadClass;
        EntidadCatalogoC2 entidadCatalogoC2 = null;

        entidadClass = Class.forName(nombrePaquete + "." + nombreEntidad);
        entidadCatalogoC2 = (EntidadCatalogoC2) entidadClass.newInstance();

        entidadCatalogoC2.setId(dto.getId());
        entidadCatalogoC2.setNombre(dto.getNombre());
        entidadCatalogoC2.setActivo(dto.isActivo());
        entidadCatalogoC2.setCodigo(dto.getCodigo());
        entidadCatalogoC2.setDescripcion(dto.getDescripcion());
        entidadCatalogoC2.setSigla(dto.getSigla());

        return entidadCatalogoC2;
    }

    public static EntidadCatalogoC2 toLevel1Entity(ItemCatalogoDTO dto, CatalogoDTO catalogoDTO)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        EntidadCatalogoC2 entidadCatalogoC2 = toLevel0Entity(dto, catalogoDTO.getNombreEntidad(),
                catalogoDTO.getPaqueteEntidad());
        if (entidadCatalogoC2 instanceof EntidadCatalogoCompuestoC2) {
            if (dto.getItemCatalogoDependenciaDTO() != null) {
                EntidadCatalogoC2 dependenciaC2 = toLevel0Entity(dto.getItemCatalogoDependenciaDTO(),
                        catalogoDTO.getCatalogoDependenciaDTO().getNombreEntidad(),
                        catalogoDTO.getCatalogoDependenciaDTO().getPaqueteEntidad());
                ((EntidadCatalogoCompuestoC2) entidadCatalogoC2).setDependencia(dependenciaC2);
            }
        }

        return entidadCatalogoC2;
    }
}
