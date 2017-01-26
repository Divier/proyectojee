package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.entidades.Agente;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoAgente;
import co.com.datatools.c2.entidades.UnificacionResponsable;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.negocio.helpers.formularios.UnificacionResponsableHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 20 15:15:33 COT 2015
 */
public class ComparendoAgenteHelper {
    // --- to DTO
    public static ComparendoAgenteDTO toLevel0DTO(ComparendoAgente entidad) {
        ComparendoAgenteDTO dto = new ComparendoAgenteDTO();
        dto.setId(entidad.getId());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setPlaca(entidad.getPlaca());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        dto.setNombreResponsable(entidad.getNombreResponsable());
        dto.setObservacionesAgente(entidad.getObservacionesAgente());

        return dto;
    }

    public static ComparendoAgenteDTO toLevel1DTO(ComparendoAgente entidad) {
        ComparendoAgenteDTO dto = toLevel0DTO(entidad);
        if (entidad.getAgente() != null)
            dto.setAgente(AgenteHelper.toLevel0DTO(entidad.getAgente()));
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getTipoIdentificacionPersona() != null)
            dto.setTipoIdentificacionPersona(
                    TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacionPersona()));
        if (entidad.getUnificacionResponsable() != null)
            dto.setUnificacionResponsable(
                    UnificacionResponsableHelper.toLevel0DTO(entidad.getUnificacionResponsable()));

        return dto;
    }

    public static List<ComparendoAgenteDTO> toListLevel0DTO(List<ComparendoAgente> listEntidad) {
        List<ComparendoAgenteDTO> listDto = new ArrayList<ComparendoAgenteDTO>();
        for (ComparendoAgente entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoAgenteDTO> toListLevel1DTO(List<ComparendoAgente> listEntidad) {
        List<ComparendoAgenteDTO> listDto = new ArrayList<ComparendoAgenteDTO>();
        for (ComparendoAgente entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ComparendoAgente toLevel0Entity(ComparendoAgenteDTO dto, ComparendoAgente entidad) {
        if (null == entidad) {
            entidad = new ComparendoAgente();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setPlaca(dto.getPlaca());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setNombreResponsable(dto.getNombreResponsable());
        entidad.setObservacionesAgente(dto.getObservacionesAgente());

        return entidad;
    }

    public static ComparendoAgente toLevel1Entity(ComparendoAgenteDTO dto, ComparendoAgente entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getAgente() != null) {
            entidad.setAgente(new Agente());
            entidad.getAgente().setId(dto.getAgente().getId());
        }
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }
        if (dto.getTipoIdentificacionPersona() != null) {
            entidad.setTipoIdentificacionPersona(new TipoIdentificacionPersona());
            entidad.getTipoIdentificacionPersona().setId(dto.getTipoIdentificacionPersona().getId());
        }
        if (dto.getUnificacionResponsable() != null) {
            entidad.setUnificacionResponsable(new UnificacionResponsable());
            entidad.getUnificacionResponsable().setId(dto.getUnificacionResponsable().getId());
        }

        return entidad;
    }

    public static List<ComparendoAgente> toListLevel0Entity(List<ComparendoAgenteDTO> listDto) {
        List<ComparendoAgente> listEntidad = new ArrayList<ComparendoAgente>();
        for (ComparendoAgenteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ComparendoAgente> toListLevel1Entity(List<ComparendoAgenteDTO> listDto) {
        List<ComparendoAgente> listEntidad = new ArrayList<ComparendoAgente>();
        for (ComparendoAgenteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}