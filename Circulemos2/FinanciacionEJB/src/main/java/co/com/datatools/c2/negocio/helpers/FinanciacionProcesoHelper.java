package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.FinanciacionProcesoDTO;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.entidades.FinanciacionProceso;

/**
 * @author Generated
 * @version 3.0 - Tue Jun 14 10:14:52 COT 2016
 */
public class FinanciacionProcesoHelper {
    // --- to DTO
    public static FinanciacionProcesoDTO toLevel0DTO(FinanciacionProceso entidad) {
        FinanciacionProcesoDTO dto = new FinanciacionProcesoDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static FinanciacionProcesoDTO toLevel1DTO(FinanciacionProceso entidad) {
        FinanciacionProcesoDTO dto = toLevel0DTO(entidad);
        dto.setIdProceso(entidad.getIdProceso());
        if (entidad.getFinanciacion() != null)
            dto.setFinanciacion(FinanciacionHelper.toLevel0DTO(entidad.getFinanciacion()));

        return dto;
    }

    public static List<FinanciacionProcesoDTO> toListLevel0DTO(List<FinanciacionProceso> listEntidad) {
        List<FinanciacionProcesoDTO> listDto = new ArrayList<FinanciacionProcesoDTO>();
        for (FinanciacionProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<FinanciacionProcesoDTO> toListLevel1DTO(List<FinanciacionProceso> listEntidad) {
        List<FinanciacionProcesoDTO> listDto = new ArrayList<FinanciacionProcesoDTO>();
        for (FinanciacionProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static FinanciacionProceso toLevel0Entity(FinanciacionProcesoDTO dto, FinanciacionProceso entidad) {
        if (null == entidad) {
            entidad = new FinanciacionProceso();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static FinanciacionProceso toLevel1Entity(FinanciacionProcesoDTO dto, FinanciacionProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);
        entidad.setIdProceso(dto.getIdProceso());
        if (dto.getFinanciacion() != null) {
            entidad.setFinanciacion(new Financiacion());
            entidad.getFinanciacion().setId(dto.getFinanciacion().getId());
        }

        return entidad;
    }

    public static List<FinanciacionProceso> toListLevel0Entity(List<FinanciacionProcesoDTO> listDto) {
        List<FinanciacionProceso> listEntidad = new ArrayList<FinanciacionProceso>();
        for (FinanciacionProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<FinanciacionProceso> toListLevel1Entity(List<FinanciacionProcesoDTO> listDto) {
        List<FinanciacionProceso> listEntidad = new ArrayList<FinanciacionProceso>();
        for (FinanciacionProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
