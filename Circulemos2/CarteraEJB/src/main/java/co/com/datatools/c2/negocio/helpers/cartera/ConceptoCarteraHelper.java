package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.entidades.ConceptoCartera;
import co.com.datatools.c2.entidades.TipoConceptoCartera;
import co.com.datatools.c2.entidades.TipoSaldo;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:26:49 COT 2015
 */
public class ConceptoCarteraHelper {
    // --- to DTO
    public static ConceptoCarteraDTO toLevel0DTO(ConceptoCartera entidad) {
        ConceptoCarteraDTO dto = new ConceptoCarteraDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    public static ConceptoCarteraDTO toLevel1DTO(ConceptoCartera entidad) {
        ConceptoCarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoSaldo() != null)
            dto.setTipoSaldo(TipoSaldoHelper.toLevel0DTO(entidad.getTipoSaldo()));
        if (entidad.getTipoConceptoCartera() != null)
            dto.setTipoConceptoCartera(TipoConceptoCarteraHelper.toLevel0DTO(entidad.getTipoConceptoCartera()));

        return dto;
    }

    public static List<ConceptoCarteraDTO> toListLevel0DTO(List<ConceptoCartera> listEntidad) {
        List<ConceptoCarteraDTO> listDto = new ArrayList<ConceptoCarteraDTO>();
        for (ConceptoCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ConceptoCarteraDTO> toListLevel1DTO(List<ConceptoCartera> listEntidad) {
        List<ConceptoCarteraDTO> listDto = new ArrayList<ConceptoCarteraDTO>();
        for (ConceptoCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ConceptoCartera toLevel0Entity(ConceptoCarteraDTO dto, ConceptoCartera entidad) {
        if (null == entidad) {
            entidad = new ConceptoCartera();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static ConceptoCartera toLevel1Entity(ConceptoCarteraDTO dto, ConceptoCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoSaldo() != null) {
            entidad.setTipoSaldo(new TipoSaldo());
            entidad.getTipoSaldo().setId(dto.getTipoSaldo().getId());
        }
        if (dto.getTipoConceptoCartera() != null) {
            entidad.setTipoConceptoCartera(new TipoConceptoCartera());
            entidad.getTipoConceptoCartera().setId(dto.getTipoConceptoCartera().getId());
        }

        return entidad;
    }

    public static List<ConceptoCartera> toListLevel0Entity(List<ConceptoCarteraDTO> listDto) {
        List<ConceptoCartera> listEntidad = new ArrayList<ConceptoCartera>();
        for (ConceptoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ConceptoCartera> toListLevel1Entity(List<ConceptoCarteraDTO> listDto) {
        List<ConceptoCartera> listEntidad = new ArrayList<ConceptoCartera>();
        for (ConceptoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
