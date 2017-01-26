package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.BloqueoComparendoDTO;
import co.com.datatools.c2.entidades.BloqueoComparendo;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.enumeraciones.EnumTipoOrigenComparendo;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 13:49:59 COT 2016
 */
public class BloqueoComparendoHelper {
    // --- to DTO
    public static BloqueoComparendoDTO toLevel0DTO(BloqueoComparendo entidad) {
        BloqueoComparendoDTO dto = new BloqueoComparendoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroComparendo(entidad.getNumeroComparendo());
        dto.setCodigoOrigen(entidad.getCodigoOrigen());
        dto.setNombreOrigen(EnumTipoOrigenComparendo.getValue(entidad.getCodigoOrigen()).name());
        dto.setCodigoInfraccion(entidad.getCodigoInfraccion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaReporte(entidad.getFechaReporte());
        dto.setFechaInfraccion(entidad.getFechaInfraccion());
        dto.setHoraInfraccion(entidad.getHoraInfraccion());
        dto.setPlacaVehiculo(entidad.getPlacaVehiculo());
        dto.setIdTipoIdentificacionInfractor(entidad.getIdTipoIdentificacionInfractor());
        dto.setNumeroIdentificacionInfractor(entidad.getNumeroIdentificacionInfractor());
        dto.setNombre1Infractor(entidad.getNombre1Infractor());
        dto.setNombre2Infractor(entidad.getNombre2Infractor());
        dto.setApellido1Infractor(entidad.getApellido1Infractor());
        dto.setApellido2Infractor(entidad.getApellido2Infractor());

        return dto;
    }

    public static BloqueoComparendoDTO toLevel1DTO(BloqueoComparendo entidad) {
        BloqueoComparendoDTO dto = toLevel0DTO(entidad);
        if (entidad.getUsuarioPersona() != null)
            dto.setUsuarioPersona(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioPersona()));

        return dto;
    }

    public static List<BloqueoComparendoDTO> toListLevel0DTO(List<BloqueoComparendo> listEntidad) {
        List<BloqueoComparendoDTO> listDto = new ArrayList<BloqueoComparendoDTO>();
        for (BloqueoComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<BloqueoComparendoDTO> toListLevel1DTO(List<BloqueoComparendo> listEntidad) {
        List<BloqueoComparendoDTO> listDto = new ArrayList<BloqueoComparendoDTO>();
        for (BloqueoComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static BloqueoComparendo toLevel0Entity(BloqueoComparendoDTO dto, BloqueoComparendo entidad) {
        if (null == entidad) {
            entidad = new BloqueoComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroComparendo(dto.getNumeroComparendo());
        entidad.setCodigoOrigen(dto.getCodigoOrigen());
        entidad.setCodigoInfraccion(dto.getCodigoInfraccion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaReporte(dto.getFechaReporte());
        entidad.setFechaInfraccion(dto.getFechaInfraccion());
        entidad.setHoraInfraccion(dto.getHoraInfraccion());
        entidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        entidad.setIdTipoIdentificacionInfractor(dto.getIdTipoIdentificacionInfractor());
        entidad.setNumeroIdentificacionInfractor(dto.getNumeroIdentificacionInfractor());
        entidad.setNombre1Infractor(dto.getNombre1Infractor());
        entidad.setNombre2Infractor(dto.getNombre2Infractor());
        entidad.setApellido1Infractor(dto.getApellido1Infractor());
        entidad.setApellido2Infractor(dto.getApellido2Infractor());

        return entidad;
    }

    public static BloqueoComparendo toLevel1Entity(BloqueoComparendoDTO dto, BloqueoComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getUsuarioPersona() != null) {
            entidad.setUsuarioPersona(new UsuarioPersona());
            entidad.getUsuarioPersona().setIdUsuario(dto.getUsuarioPersona().getUsuario().getId());
        }

        return entidad;
    }

    public static List<BloqueoComparendo> toListLevel0Entity(List<BloqueoComparendoDTO> listDto) {
        List<BloqueoComparendo> listEntidad = new ArrayList<BloqueoComparendo>();
        for (BloqueoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<BloqueoComparendo> toListLevel1Entity(List<BloqueoComparendoDTO> listDto) {
        List<BloqueoComparendo> listEntidad = new ArrayList<BloqueoComparendo>();
        for (BloqueoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}