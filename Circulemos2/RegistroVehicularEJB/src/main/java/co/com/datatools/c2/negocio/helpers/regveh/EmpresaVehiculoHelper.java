package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.entidades.EmpresaVehiculo;
import co.com.datatools.c2.entidades.Vehiculo;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 09:07:10 COT 2015
 */
public class EmpresaVehiculoHelper {
    // --- to DTO
    public static EmpresaVehiculoDTO toLevel0DTO(EmpresaVehiculo entidad) {
        EmpresaVehiculoDTO dto = new EmpresaVehiculoDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static EmpresaVehiculoDTO toLevel1DTO(EmpresaVehiculo entidad) {
        EmpresaVehiculoDTO dto = toLevel0DTO(entidad);
        if (entidad.getVehiculo() != null)
            dto.setVehiculo(VehiculoHelper.toLevel0DTO(entidad.getVehiculo()));

        return dto;
    }

    public static List<EmpresaVehiculoDTO> toListLevel0DTO(List<EmpresaVehiculo> listEntidad) {
        List<EmpresaVehiculoDTO> listDto = new ArrayList<EmpresaVehiculoDTO>();
        for (EmpresaVehiculo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EmpresaVehiculoDTO> toListLevel1DTO(List<EmpresaVehiculo> listEntidad) {
        List<EmpresaVehiculoDTO> listDto = new ArrayList<EmpresaVehiculoDTO>();
        for (EmpresaVehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EmpresaVehiculo toLevel0Entity(EmpresaVehiculoDTO dto, EmpresaVehiculo entidad) {
        if (null == entidad) {
            entidad = new EmpresaVehiculo();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static EmpresaVehiculo toLevel1Entity(EmpresaVehiculoDTO dto, EmpresaVehiculo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getVehiculo() != null) {
            entidad.setVehiculo(new Vehiculo());
            entidad.getVehiculo().setId(dto.getVehiculo().getId());
        }
        if (dto.getPersonaJuridica() != null) {
            entidad.setPersonaJuridica(new PersonaJuridica());
            entidad.getPersonaJuridica().setIdPersonaJuridica(dto.getPersonaJuridica().getId());
        }

        return entidad;
    }

    public static List<EmpresaVehiculo> toListLevel0Entity(List<EmpresaVehiculoDTO> listDto) {
        List<EmpresaVehiculo> listEntidad = new ArrayList<EmpresaVehiculo>();
        for (EmpresaVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EmpresaVehiculo> toListLevel1Entity(List<EmpresaVehiculoDTO> listDto) {
        List<EmpresaVehiculo> listEntidad = new ArrayList<EmpresaVehiculo>();
        for (EmpresaVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
