package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.OrdenamientoPaisDTO;
import co.com.datatools.c2.entidades.OrdenamientoPais;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:57:46 COT 2015
 */
public class OrdenamientoPaisHelper {
    // --- to DTO
    public static OrdenamientoPaisDTO toLevel0DTO(OrdenamientoPais entidad) {
        OrdenamientoPaisDTO dto = new OrdenamientoPaisDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static OrdenamientoPaisDTO toLevel1DTO(OrdenamientoPais entidad) {
        OrdenamientoPaisDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<OrdenamientoPaisDTO> toListLevel0DTO(List<OrdenamientoPais> listEntidad) {
        List<OrdenamientoPaisDTO> listDto = new ArrayList<OrdenamientoPaisDTO>();
        for (OrdenamientoPais entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<OrdenamientoPaisDTO> toListLevel1DTO(List<OrdenamientoPais> listEntidad) {
        List<OrdenamientoPaisDTO> listDto = new ArrayList<OrdenamientoPaisDTO>();
        for (OrdenamientoPais entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static OrdenamientoPais toLevel0Entity(OrdenamientoPaisDTO dto, OrdenamientoPais entidad) {
        if (null == entidad) {
            entidad = new OrdenamientoPais();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static OrdenamientoPais toLevel1Entity(OrdenamientoPaisDTO dto, OrdenamientoPais entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<OrdenamientoPais> toListLevel0Entity(List<OrdenamientoPaisDTO> listDto) {
        List<OrdenamientoPais> listEntidad = new ArrayList<OrdenamientoPais>();
        for (OrdenamientoPaisDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<OrdenamientoPais> toListLevel1Entity(List<OrdenamientoPaisDTO> listDto) {
        List<OrdenamientoPais> listEntidad = new ArrayList<OrdenamientoPais>();
        for (OrdenamientoPaisDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
