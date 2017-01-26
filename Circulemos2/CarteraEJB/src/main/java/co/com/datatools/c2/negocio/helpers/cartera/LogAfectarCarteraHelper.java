package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.LogAfectarCarteraDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.ConceptoCartera;
import co.com.datatools.c2.entidades.LogAfectarCartera;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 10:47:09 COT 2015
 */
public class LogAfectarCarteraHelper {
    // --- to DTO
    public static LogAfectarCarteraDTO toLevel0DTO(LogAfectarCartera entidad) {
        LogAfectarCarteraDTO dto = new LogAfectarCarteraDTO();
        dto.setId(entidad.getId());
        dto.setCodigoExcepcion(entidad.getCodigoExcepcion());
        dto.setDescripcionExcepcion(entidad.getDescripcionExcepcion());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaMovimiento(entidad.getFechaMovimiento());
        dto.setValorMovimiento(entidad.getValorMovimiento());

        return dto;
    }

    public static LogAfectarCarteraDTO toLevel1DTO(LogAfectarCartera entidad) {
        LogAfectarCarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getCartera() != null)
            dto.setCartera(CarteraHelper.toLevel0DTO(entidad.getCartera()));
        if (entidad.getConceptoCartera() != null)
            dto.setConceptoCartera(ConceptoCarteraHelper.toLevel0DTO(entidad.getConceptoCartera()));
        if (entidad.getUsuarioPersona() != null)
            dto.setUsuarioPersona(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioPersona()));

        return dto;
    }

    public static List<LogAfectarCarteraDTO> toListLevel0DTO(List<LogAfectarCartera> listEntidad) {
        List<LogAfectarCarteraDTO> listDto = new ArrayList<LogAfectarCarteraDTO>();
        for (LogAfectarCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LogAfectarCarteraDTO> toListLevel1DTO(List<LogAfectarCartera> listEntidad) {
        List<LogAfectarCarteraDTO> listDto = new ArrayList<LogAfectarCarteraDTO>();
        for (LogAfectarCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static LogAfectarCartera toLevel0Entity(LogAfectarCarteraDTO dto, LogAfectarCartera entidad) {
        if (null == entidad) {
            entidad = new LogAfectarCartera();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoExcepcion(dto.getCodigoExcepcion());
        entidad.setDescripcionExcepcion(dto.getDescripcionExcepcion());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setFechaMovimiento(dto.getFechaMovimiento());
        entidad.setValorMovimiento(dto.getValorMovimiento());

        return entidad;
    }

    public static LogAfectarCartera toLevel1Entity(LogAfectarCarteraDTO dto, LogAfectarCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCartera() != null) {
            entidad.setCartera(new Cartera());
            entidad.getCartera().setId(dto.getCartera().getId());
        }
        if (dto.getConceptoCartera() != null) {
            entidad.setConceptoCartera(new ConceptoCartera());
            entidad.getConceptoCartera().setId(dto.getConceptoCartera().getId());
        }
        if (dto.getUsuarioPersona() != null) {
            entidad.setUsuarioPersona(UsuarioPersonaHelper.toLevel0Entity(dto.getUsuarioPersona(), null));
        }

        return entidad;
    }

    public static List<LogAfectarCartera> toListLevel0Entity(List<LogAfectarCarteraDTO> listDto) {
        List<LogAfectarCartera> listEntidad = new ArrayList<LogAfectarCartera>();
        for (LogAfectarCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<LogAfectarCartera> toListLevel1Entity(List<LogAfectarCarteraDTO> listDto) {
        List<LogAfectarCartera> listEntidad = new ArrayList<LogAfectarCartera>();
        for (LogAfectarCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
