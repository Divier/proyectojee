package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.entidades.ClaseInteres;
import co.com.datatools.c2.entidades.Interes;
import co.com.datatools.c2.entidades.PeriodoInteres;
import co.com.datatools.c2.negocio.helpers.v2.ClaseInteresHelper;
import co.com.datatools.c2.negocio.helpers.v2.PeriodoInteresHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 17:17:07 COT 2015
 */
public class InteresHelper {
    // --- to DTO
    public static InteresDTO toLevel0DTO(Interes entidad) {
        InteresDTO dto = new InteresDTO();
        dto.setId(entidad.getId());
        dto.setFechaFinal(entidad.getFechaFinal());
        dto.setFechaInicial(entidad.getFechaInicial());
        dto.setPorcentajeInteresDiario(entidad.getPorcentajeInteresDiario());
        dto.setPorcentajeTasaInteres(entidad.getPorcentajeTasaInteres());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    public static InteresDTO toLevel1DTO(Interes entidad) {
        InteresDTO dto = toLevel0DTO(entidad);
        if (entidad.getClaseInteres() != null)
            dto.setClaseInteres(ClaseInteresHelper.toLevel0DTO(entidad.getClaseInteres()));
        if (entidad.getPeriodoInteres() != null)
            dto.setPeriodoInteres(PeriodoInteresHelper.toLevel0DTO(entidad.getPeriodoInteres()));

        return dto;
    }

    public static List<InteresDTO> toListLevel0DTO(List<Interes> listEntidad) {
        List<InteresDTO> listDto = new ArrayList<InteresDTO>();
        for (Interes entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<InteresDTO> toListLevel1DTO(List<Interes> listEntidad) {
        List<InteresDTO> listDto = new ArrayList<InteresDTO>();
        for (Interes entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Interes toLevel0Entity(InteresDTO dto, Interes entidad) {
        if (null == entidad) {
            entidad = new Interes();
        }
        entidad.setId(dto.getId());
        entidad.setFechaFinal(dto.getFechaFinal());
        entidad.setFechaInicial(dto.getFechaInicial());
        entidad.setPorcentajeInteresDiario(dto.getPorcentajeInteresDiario());
        entidad.setPorcentajeTasaInteres(dto.getPorcentajeTasaInteres());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    public static Interes toLevel1Entity(InteresDTO dto, Interes entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getClaseInteres() != null) {
            entidad.setClaseInteres(new ClaseInteres());
            entidad.getClaseInteres().setId(dto.getClaseInteres().getId());
        }
        if (dto.getPeriodoInteres() != null) {
            entidad.setPeriodoInteres(new PeriodoInteres());
            entidad.getPeriodoInteres().setId(dto.getPeriodoInteres().getId());
        }

        return entidad;
    }

    public static List<Interes> toListLevel0Entity(List<InteresDTO> listDto) {
        List<Interes> listEntidad = new ArrayList<Interes>();
        for (InteresDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Interes> toListLevel1Entity(List<InteresDTO> listDto) {
        List<Interes> listEntidad = new ArrayList<Interes>();
        for (InteresDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}