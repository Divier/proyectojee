package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.entidades.EmpresaTransporte;
import co.com.datatools.c2.entidades.Ruta;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 14:39:50 COT 2015
 */
public class RutaHelper {
    // --- to DTO
    public static RutaDTO toLevel0DTO(Ruta entidad) {
        RutaDTO dto = new RutaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    public static RutaDTO toLevel1DTO(Ruta entidad) {
        RutaDTO dto = toLevel0DTO(entidad);
        if (entidad.getEmpresaTransporte() != null)
            dto.setEmpresaTransporte(EmpresaTransporteHelper.toLevel0DTO(entidad.getEmpresaTransporte()));

        return dto;
    }

    public static List<RutaDTO> toListLevel0DTO(List<Ruta> listEntidad) {
        List<RutaDTO> listDto = new ArrayList<RutaDTO>();
        for (Ruta entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RutaDTO> toListLevel1DTO(List<Ruta> listEntidad) {
        List<RutaDTO> listDto = new ArrayList<RutaDTO>();
        for (Ruta entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Ruta toLevel0Entity(RutaDTO dto, Ruta entidad) {
        if (null == entidad) {
            entidad = new Ruta();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    public static Ruta toLevel1Entity(RutaDTO dto, Ruta entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEmpresaTransporte() != null) {
            entidad.setEmpresaTransporte(new EmpresaTransporte());
            entidad.getEmpresaTransporte().setId(dto.getEmpresaTransporte().getId());
        }

        return entidad;
    }

    public static List<Ruta> toListLevel0Entity(List<RutaDTO> listDto) {
        List<Ruta> listEntidad = new ArrayList<Ruta>();
        for (RutaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Ruta> toListLevel1Entity(List<RutaDTO> listDto) {
        List<Ruta> listEntidad = new ArrayList<Ruta>();
        for (RutaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
