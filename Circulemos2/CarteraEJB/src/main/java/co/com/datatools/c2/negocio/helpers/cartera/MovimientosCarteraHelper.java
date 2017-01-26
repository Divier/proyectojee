package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.ConceptoCartera;
import co.com.datatools.c2.entidades.MovimientosCartera;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:27:31 COT 2015
 */
public class MovimientosCarteraHelper {
    // --- to DTO
    public static MovimientosCarteraDTO toLevel0DTO(MovimientosCartera entidad) {
        MovimientosCarteraDTO dto = new MovimientosCarteraDTO();
        dto.setId(entidad.getId());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaMovimiento(entidad.getFechaMovimiento());
        dto.setLoginUsuario(entidad.getLoginUsuario());
        dto.setObservaciones(entidad.getObservaciones());
        dto.setValorMovimiento(entidad.getValorMovimiento());

        return dto;
    }

    public static MovimientosCarteraDTO toLevel1DTO(MovimientosCartera entidad) {
        MovimientosCarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getConceptoCartera() != null)
            dto.setConceptoCartera(ConceptoCarteraHelper.toLevel0DTO(entidad.getConceptoCartera()));
        if (entidad.getCartera() != null)
            dto.setCartera(CarteraHelper.toLevel0DTO(entidad.getCartera()));

        return dto;
    }

    public static List<MovimientosCarteraDTO> toListLevel0DTO(List<MovimientosCartera> listEntidad) {
        List<MovimientosCarteraDTO> listDto = new ArrayList<MovimientosCarteraDTO>();
        for (MovimientosCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<MovimientosCarteraDTO> toListLevel1DTO(List<MovimientosCartera> listEntidad) {
        List<MovimientosCarteraDTO> listDto = new ArrayList<MovimientosCarteraDTO>();
        for (MovimientosCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static MovimientosCartera toLevel0Entity(MovimientosCarteraDTO dto, MovimientosCartera entidad) {
        if (null == entidad) {
            entidad = new MovimientosCartera();
        }
        entidad.setId(dto.getId());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setFechaMovimiento(dto.getFechaMovimiento());
        entidad.setLoginUsuario(dto.getLoginUsuario());
        entidad.setObservaciones(dto.getObservaciones());
        entidad.setValorMovimiento(dto.getValorMovimiento());

        return entidad;
    }

    public static MovimientosCartera toLevel1Entity(MovimientosCarteraDTO dto, MovimientosCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getConceptoCartera() != null) {
            entidad.setConceptoCartera(new ConceptoCartera());
            entidad.getConceptoCartera().setId(dto.getConceptoCartera().getId());
        }
        if (dto.getCartera() != null) {
            entidad.setCartera(new Cartera());
            entidad.getCartera().setId(dto.getCartera().getId());
        }

        return entidad;
    }

    public static List<MovimientosCartera> toListLevel0Entity(List<MovimientosCarteraDTO> listDto) {
        List<MovimientosCartera> listEntidad = new ArrayList<MovimientosCartera>();
        for (MovimientosCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<MovimientosCartera> toListLevel1Entity(List<MovimientosCarteraDTO> listDto) {
        List<MovimientosCartera> listEntidad = new ArrayList<MovimientosCartera>();
        for (MovimientosCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
