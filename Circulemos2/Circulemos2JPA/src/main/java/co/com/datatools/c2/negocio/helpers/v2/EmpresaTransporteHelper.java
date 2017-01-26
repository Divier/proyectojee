package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EmpresaTransporteDTO;
import co.com.datatools.c2.entidades.EmpresaTransporte;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.negocio.helpers.personas.PersonaJuridicaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 14:39:17 COT 2015
 */
public class EmpresaTransporteHelper {
    // --- to DTO
    public static EmpresaTransporteDTO toLevel0DTO(EmpresaTransporte entidad) {
        EmpresaTransporteDTO dto = new EmpresaTransporteDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static EmpresaTransporteDTO toLevel1DTO(EmpresaTransporte entidad) {
        EmpresaTransporteDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersonaJuridica() != null)
            dto.setPersonaJuridica(PersonaJuridicaHelper.toLevel0DTO(entidad.getPersonaJuridica()));

        return dto;
    }

    public static List<EmpresaTransporteDTO> toListLevel0DTO(List<EmpresaTransporte> listEntidad) {
        List<EmpresaTransporteDTO> listDto = new ArrayList<EmpresaTransporteDTO>();
        for (EmpresaTransporte entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EmpresaTransporteDTO> toListLevel1DTO(List<EmpresaTransporte> listEntidad) {
        List<EmpresaTransporteDTO> listDto = new ArrayList<EmpresaTransporteDTO>();
        for (EmpresaTransporte entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EmpresaTransporte toLevel0Entity(EmpresaTransporteDTO dto, EmpresaTransporte entidad) {
        if (null == entidad) {
            entidad = new EmpresaTransporte();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static EmpresaTransporte toLevel1Entity(EmpresaTransporteDTO dto, EmpresaTransporte entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPersonaJuridica() != null) {
            entidad.setPersonaJuridica(new PersonaJuridica());
            entidad.getPersonaJuridica().setIdPersonaJuridica(dto.getPersonaJuridica().getId());
        }

        return entidad;
    }

    public static List<EmpresaTransporte> toListLevel0Entity(List<EmpresaTransporteDTO> listDto) {
        List<EmpresaTransporte> listEntidad = new ArrayList<EmpresaTransporte>();
        for (EmpresaTransporteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EmpresaTransporte> toListLevel1Entity(List<EmpresaTransporteDTO> listDto) {
        List<EmpresaTransporte> listEntidad = new ArrayList<EmpresaTransporte>();
        for (EmpresaTransporteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
