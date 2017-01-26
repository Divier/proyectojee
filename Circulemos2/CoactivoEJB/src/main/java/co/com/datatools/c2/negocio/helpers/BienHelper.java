package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.BienDTO;
import co.com.datatools.c2.entidades.Bien;
import co.com.datatools.c2.entidades.EntidadBien;
import co.com.datatools.c2.entidades.TipoBien;
import co.com.datatools.c2.entidades.TipoEntidad;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class BienHelper {
    // --- to DTO
    public static BienDTO toLevel0DTO(Bien entidad) {
        BienDTO dto = new BienDTO();
        dto.setId(entidad.getId());
        dto.setNumeroReferencia(entidad.getNumeroReferencia());
        dto.setMontoRetenido(entidad.getMontoRetenido());
        dto.setValorEmbargado(entidad.getValorEmbargado());
        dto.setFechaModifiacion(entidad.getFechaModifiacion());
        dto.setFechaRegistro(entidad.getFechaRegistro());

        return dto;
    }

    public static BienDTO toLevel1DTO(Bien entidad) {
        BienDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersona() != null)
            dto.setPersonaDTO(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getTipoEntidad() != null)
            dto.setTipoEntidadDTO(TipoEntidadHelper.toLevel0DTO(entidad.getTipoEntidad()));
        if (entidad.getTipoBien() != null)
            dto.setTipoBienDTO(TipoBienHelper.toLevel0DTO(entidad.getTipoBien()));
        if (entidad.getEntidadBien() != null)
            dto.setEntidadBienDTO(EntidadBienHelper.toLevel0DTO(entidad.getEntidadBien()));

        return dto;
    }

    public static List<BienDTO> toListLevel0DTO(List<Bien> listEntidad) {
        List<BienDTO> listDto = new ArrayList<BienDTO>();
        for (Bien entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<BienDTO> toListLevel1DTO(List<Bien> listEntidad) {
        List<BienDTO> listDto = new ArrayList<BienDTO>();
        for (Bien entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Bien toLevel0Entity(BienDTO dto, Bien entidad) {
        if (null == entidad) {
            entidad = new Bien();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static Bien toLevel1Entity(BienDTO dto, Bien entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPersonaDTO() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersonaDTO().getId());
        }
        if (dto.getTipoEntidadDTO() != null) {
            entidad.setTipoEntidad(new TipoEntidad());
            entidad.getTipoBien().setId(dto.getTipoBienDTO().getId());
        }
        if (dto.getTipoBienDTO() != null) {
            entidad.setTipoBien(new TipoBien());
            entidad.getTipoBien().setId(dto.getTipoBienDTO().getId());
        }
        if (dto.getEntidadBienDTO() != null) {
            entidad.setEntidadBien(new EntidadBien());
            entidad.getEntidadBien().setId(dto.getEntidadBienDTO().getId());
        }

        return entidad;
    }

    public static List<Bien> toListLevel0Entity(List<BienDTO> listDto) {
        List<Bien> listEntidad = new ArrayList<Bien>();
        for (BienDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Bien> toListLevel1Entity(List<BienDTO> listDto) {
        List<Bien> listEntidad = new ArrayList<Bien>();
        for (BienDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
