package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.LineaVehiculoDTO;
import co.com.datatools.c2.entidades.LineaVehiculo;
import co.com.datatools.c2.entidades.MarcaVehiculo;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 09:08:03 COT 2015
 */
public class LineaVehiculoHelper {
    // --- to DTO
    public static LineaVehiculoDTO toLevel0DTO(LineaVehiculo entidad) {
        LineaVehiculoDTO dto = new LineaVehiculoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static LineaVehiculoDTO toLevel1DTO(LineaVehiculo entidad) {
        LineaVehiculoDTO dto = toLevel0DTO(entidad);
        if (entidad.getDependencia() != null)
            dto.setMarca(MarcaVehiculoHelper.toLevel0DTO((MarcaVehiculo) entidad.getDependencia()));

        return dto;
    }

    public static List<LineaVehiculoDTO> toListLevel0DTO(List<LineaVehiculo> listEntidad) {
        List<LineaVehiculoDTO> listDto = new ArrayList<LineaVehiculoDTO>();
        for (LineaVehiculo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LineaVehiculoDTO> toListLevel1DTO(List<LineaVehiculo> listEntidad) {
        List<LineaVehiculoDTO> listDto = new ArrayList<LineaVehiculoDTO>();
        for (LineaVehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static LineaVehiculo toLevel0Entity(LineaVehiculoDTO dto, LineaVehiculo entidad) {
        if (null == entidad) {
            entidad = new LineaVehiculo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static LineaVehiculo toLevel1Entity(LineaVehiculoDTO dto, LineaVehiculo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getMarca() != null) {
            entidad.setDependencia(new MarcaVehiculo());
            entidad.getDependencia().setId(dto.getMarca().getId());
        }

        return entidad;
    }

    public static List<LineaVehiculo> toListLevel0Entity(List<LineaVehiculoDTO> listDto) {
        List<LineaVehiculo> listEntidad = new ArrayList<LineaVehiculo>();
        for (LineaVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<LineaVehiculo> toListLevel1Entity(List<LineaVehiculoDTO> listDto) {
        List<LineaVehiculo> listEntidad = new ArrayList<LineaVehiculo>();
        for (LineaVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
