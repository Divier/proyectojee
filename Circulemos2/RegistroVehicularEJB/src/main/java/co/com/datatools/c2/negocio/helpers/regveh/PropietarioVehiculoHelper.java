package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.entidades.PropietarioVehiculo;
import co.com.datatools.c2.entidades.TipoPropietario;
import co.com.datatools.c2.entidades.Vehiculo;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 09:08:53 COT 2015
 */
public class PropietarioVehiculoHelper {
    // --- to DTO
    public static PropietarioVehiculoDTO toLevel0DTO(PropietarioVehiculo entidad) {
        PropietarioVehiculoDTO dto = new PropietarioVehiculoDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());
        dto.setPorcentaje(entidad.getPorcentaje());

        return dto;
    }

    public static PropietarioVehiculoDTO toLevel1DTO(PropietarioVehiculo entidad) {
        PropietarioVehiculoDTO dto = toLevel0DTO(entidad);
        if (entidad.getVehiculo() != null)
            dto.setVehiculo(VehiculoHelper.toLevel0DTO(entidad.getVehiculo()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getTipoPropietario() != null)
            dto.setTipoPropietario(TipoPropietarioHelper.toLevel0DTO(entidad.getTipoPropietario()));

        return dto;
    }

    public static List<PropietarioVehiculoDTO> toListLevel0DTO(List<PropietarioVehiculo> listEntidad) {
        List<PropietarioVehiculoDTO> listDto = new ArrayList<PropietarioVehiculoDTO>();
        for (PropietarioVehiculo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PropietarioVehiculoDTO> toListLevel1DTO(List<PropietarioVehiculo> listEntidad) {
        List<PropietarioVehiculoDTO> listDto = new ArrayList<PropietarioVehiculoDTO>();
        for (PropietarioVehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static PropietarioVehiculo toLevel0Entity(PropietarioVehiculoDTO dto, PropietarioVehiculo entidad) {
        if (null == entidad) {
            entidad = new PropietarioVehiculo();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());
        entidad.setPorcentaje(dto.getPorcentaje());

        return entidad;
    }

    public static PropietarioVehiculo toLevel1Entity(PropietarioVehiculoDTO dto, PropietarioVehiculo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getVehiculo() != null) {
            entidad.setVehiculo(new Vehiculo());
            entidad.getVehiculo().setId(dto.getVehiculo().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getTipoPropietario() != null) {
            entidad.setTipoPropietario(new TipoPropietario());
            entidad.getTipoPropietario().setId(dto.getTipoPropietario().getId());
        }

        return entidad;
    }

    public static List<PropietarioVehiculo> toListLevel0Entity(List<PropietarioVehiculoDTO> listDto) {
        List<PropietarioVehiculo> listEntidad = new ArrayList<PropietarioVehiculo>();
        for (PropietarioVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<PropietarioVehiculo> toListLevel1Entity(List<PropietarioVehiculoDTO> listDto) {
        List<PropietarioVehiculo> listEntidad = new ArrayList<PropietarioVehiculo>();
        for (PropietarioVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
