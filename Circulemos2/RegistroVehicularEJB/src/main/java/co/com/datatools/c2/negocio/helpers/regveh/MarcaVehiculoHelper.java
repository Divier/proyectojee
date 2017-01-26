package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.MarcaVehiculoDTO;
import co.com.datatools.c2.entidades.MarcaVehiculo;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 09:08:18 COT 2015
 */
public class MarcaVehiculoHelper {
    // --- to DTO
    public static MarcaVehiculoDTO toLevel0DTO(MarcaVehiculo entidad) {
        MarcaVehiculoDTO dto = new MarcaVehiculoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static MarcaVehiculoDTO toLevel1DTO(MarcaVehiculo entidad) {
        MarcaVehiculoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<MarcaVehiculoDTO> toListLevel0DTO(List<MarcaVehiculo> listEntidad) {
        List<MarcaVehiculoDTO> listDto = new ArrayList<MarcaVehiculoDTO>();
        for (MarcaVehiculo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<MarcaVehiculoDTO> toListLevel1DTO(List<MarcaVehiculo> listEntidad) {
        List<MarcaVehiculoDTO> listDto = new ArrayList<MarcaVehiculoDTO>();
        for (MarcaVehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static MarcaVehiculo toLevel0Entity(MarcaVehiculoDTO dto, MarcaVehiculo entidad) {
        if (null == entidad) {
            entidad = new MarcaVehiculo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static MarcaVehiculo toLevel1Entity(MarcaVehiculoDTO dto, MarcaVehiculo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<MarcaVehiculo> toListLevel0Entity(List<MarcaVehiculoDTO> listDto) {
        List<MarcaVehiculo> listEntidad = new ArrayList<MarcaVehiculo>();
        for (MarcaVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<MarcaVehiculo> toListLevel1Entity(List<MarcaVehiculoDTO> listDto) {
        List<MarcaVehiculo> listEntidad = new ArrayList<MarcaVehiculo>();
        for (MarcaVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
