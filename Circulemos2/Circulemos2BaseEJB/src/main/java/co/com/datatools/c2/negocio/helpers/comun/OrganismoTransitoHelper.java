package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.entidades.comun.Departamento;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;

/**
 * @author Generated
 * @version 3.0 - Fri Jan 02 09:33:22 COT 2015
 */
public class OrganismoTransitoHelper {
    // --- to DTO
    public static OrganismoTransitoDTO toLevel0DTO(OrganismoTransito entidad) {
        OrganismoTransitoDTO dto = new OrganismoTransitoDTO();
        dto.setCodigoOrganismo(entidad.getCodigoOrganismo());
        dto.setNombreOrganismo(entidad.getNombreOrganismo());
        dto.setNit(entidad.getNit());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setCodigoRunt(entidad.getCodigoRunt());
        dto.setCodigoMinisterio(entidad.getCodigoMinisterio());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static OrganismoTransitoDTO toLevel1DTO(OrganismoTransito entidad) {
        OrganismoTransitoDTO dto = toLevel0DTO(entidad);
        if (entidad.getDepartamento() != null)
            dto.setDepartamento(DepartamentoHelper.toLevel0DTO(entidad.getDepartamento()));
        if (entidad.getMunicipio() != null)
            dto.setMunicipio(MunicipioHelper.toLevel0DTO(entidad.getMunicipio()));

        return dto;
    }

    public static List<OrganismoTransitoDTO> toListLevel0DTO(List<OrganismoTransito> listEntidad) {
        List<OrganismoTransitoDTO> listDto = new ArrayList<OrganismoTransitoDTO>();
        for (OrganismoTransito entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<OrganismoTransitoDTO> toListLevel1DTO(List<OrganismoTransito> listEntidad) {
        List<OrganismoTransitoDTO> listDto = new ArrayList<OrganismoTransitoDTO>();
        for (OrganismoTransito entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static OrganismoTransito toLevel0Entity(OrganismoTransitoDTO dto, OrganismoTransito entidad) {
        if (null == entidad) {
            entidad = new OrganismoTransito();
        }
        entidad.setCodigoOrganismo(dto.getCodigoOrganismo());
        entidad.setNombreOrganismo(dto.getNombreOrganismo());
        entidad.setNit(dto.getNit());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setCodigoRunt(dto.getCodigoRunt());
        entidad.setCodigoMinisterio(dto.getCodigoMinisterio());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static OrganismoTransito toLevel1Entity(OrganismoTransitoDTO dto, OrganismoTransito entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDepartamento() != null) {
            entidad.setDepartamento(new Departamento());
            entidad.getDepartamento().setId(dto.getDepartamento().getId());
        }
        if (dto.getMunicipio() != null) {
            entidad.setMunicipio(new Municipio());
            entidad.getMunicipio().setId(dto.getMunicipio().getId());
        }

        return entidad;
    }

    public static List<OrganismoTransito> toListLevel0Entity(List<OrganismoTransitoDTO> listDto) {
        List<OrganismoTransito> listEntidad = new ArrayList<OrganismoTransito>();
        for (OrganismoTransitoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<OrganismoTransito> toListLevel1Entity(List<OrganismoTransitoDTO> listDto) {
        List<OrganismoTransito> listEntidad = new ArrayList<OrganismoTransito>();
        for (OrganismoTransitoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }

    public static ItemCatalogoDTO toItemCatalogoDTO(OrganismoTransito organismoTransito) {
        ItemCatalogoDTO itemCatalogoDTO = new ItemCatalogoDTO();
        itemCatalogoDTO.setId(organismoTransito.getCodigoOrganismo());
        itemCatalogoDTO.setNombre(organismoTransito.getNombreOrganismo());
        return itemCatalogoDTO;
    }
    // --- fin to Entidad
}
