package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ObligacionCoactivoDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.ObligacionCoactivo;
import co.com.datatools.c2.negocio.helpers.cartera.CarteraHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:44 COT 2016
 */
public class ObligacionCoactivoHelper {
    // --- to DTO
    public static ObligacionCoactivoDTO toLevel0DTO(ObligacionCoactivo entidad) {
        ObligacionCoactivoDTO dto = new ObligacionCoactivoDTO();
        dto.setId(entidad.getId());
        dto.setCodigoTipoObligacion(entidad.getCodigoTipoObligacion());
        dto.setFechaObligacion(entidad.getFechaObligacion());
        dto.setNumeroObligacion(entidad.getNumeroObligacion());
        dto.setValorCostasProcesales(entidad.getValorCostasProcesales());
        dto.setValorInteresMoratorios(entidad.getValorInteresMoratorios());
        dto.setValorObligacion(entidad.getValorObligacion());

        return dto;
    }

    public static ObligacionCoactivoDTO toLevel1DTO(ObligacionCoactivo entidad) {
        ObligacionCoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getCartera() != null)
            dto.setCartera(CarteraHelper.toLevel0DTO(entidad.getCartera()));
        if (entidad.getCoactivo() != null)
            dto.setCoactivo(CoactivoHelper.toLevel0DTO(entidad.getCoactivo()));

        return dto;
    }

    public static List<ObligacionCoactivoDTO> toListLevel0DTO(List<ObligacionCoactivo> listEntidad) {
        List<ObligacionCoactivoDTO> listDto = new ArrayList<ObligacionCoactivoDTO>();
        for (ObligacionCoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ObligacionCoactivoDTO> toListLevel1DTO(List<ObligacionCoactivo> listEntidad) {
        List<ObligacionCoactivoDTO> listDto = new ArrayList<ObligacionCoactivoDTO>();
        for (ObligacionCoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ObligacionCoactivo toLevel0Entity(ObligacionCoactivoDTO dto, ObligacionCoactivo entidad) {
        if (null == entidad) {
            entidad = new ObligacionCoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoTipoObligacion(dto.getCodigoTipoObligacion());
        entidad.setFechaObligacion(dto.getFechaObligacion());
        entidad.setNumeroObligacion(dto.getNumeroObligacion());
        entidad.setValorCostasProcesales(dto.getValorCostasProcesales());
        entidad.setValorInteresMoratorios(dto.getValorInteresMoratorios());
        entidad.setValorObligacion(dto.getValorObligacion());

        return entidad;
    }

    public static ObligacionCoactivo toLevel1Entity(ObligacionCoactivoDTO dto, ObligacionCoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCartera() != null) {
            entidad.setCartera(new Cartera());
            entidad.getCartera().setId(dto.getCartera().getId());
        }
        if (dto.getCoactivo() != null) {
            entidad.setCoactivo(new Coactivo());
            entidad.getCoactivo().setId(dto.getCoactivo().getId());
        }

        return entidad;
    }

    public static List<ObligacionCoactivo> toListLevel0Entity(List<ObligacionCoactivoDTO> listDto) {
        List<ObligacionCoactivo> listEntidad = new ArrayList<ObligacionCoactivo>();
        for (ObligacionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ObligacionCoactivo> toListLevel1Entity(List<ObligacionCoactivoDTO> listDto) {
        List<ObligacionCoactivo> listEntidad = new ArrayList<ObligacionCoactivo>();
        for (ObligacionCoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
