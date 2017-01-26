package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleAccidentalidadDTO;
import co.com.datatools.c2.entidades.Accidentalidad;
import co.com.datatools.c2.entidades.Carril;
import co.com.datatools.c2.entidades.ClaseVehiculo;
import co.com.datatools.c2.entidades.DetalleAccidentalidad;
import co.com.datatools.c2.entidades.EstadoFisico;
import co.com.datatools.c2.entidades.Sentido;
import co.com.datatools.c2.entidades.TipoCategLicenciaConduccion;
import co.com.datatools.c2.entidades.TipoPersonaIPAT;
import co.com.datatools.c2.entidades.TipoServicio;
import co.com.datatools.c2.negocio.helpers.v2.ClaseVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoCategLicenciaConduccionHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoServicioHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:59:30 COT 2016
 */
public class DetalleAccidentalidadHelper {
    // --- to DTO
    public static DetalleAccidentalidadDTO toLevel0DTO(DetalleAccidentalidad entidad) {
        DetalleAccidentalidadDTO dto = new DetalleAccidentalidadDTO();
        dto.setId(entidad.getId());
        dto.setBreveRelacion(entidad.getBreveRelacion());
        dto.setNombrePersona(entidad.getNombrePersona());
        dto.setLicencia(entidad.getLicencia());
        dto.setOrigen(entidad.getOrigen());
        dto.setEdad(entidad.getEdad());
        dto.setEmbriaguez(entidad.getEmbriaguez());
        dto.setAprehendido(entidad.getAprehendido());
        dto.setTipoVehiculo(entidad.getTipoVehiculo());
        dto.setPlaca(entidad.getPlaca());
        dto.setCalleCarretera(entidad.getCalleCarretera());
        dto.setRetenido(entidad.getRetenido());
        dto.setVersion(entidad.getVersion());
        dto.setDanosVehiculo(entidad.getDanosVehiculo());
        dto.setCircustancias(entidad.getCircustancias());
        dto.setObservaciones(entidad.getObservaciones());
        dto.setZona_impacto(entidad.getZona_impacto());
        dto.setDatos_adjuntos(entidad.getDatos_adjuntos());

        return dto;
    }

    public static DetalleAccidentalidadDTO toLevel1DTO(DetalleAccidentalidad entidad) {
        DetalleAccidentalidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getAccidentalidad() != null)
            dto.setAccidentalidad(AccidentalidadHelper.toLevel0DTO(entidad.getAccidentalidad()));
        if (entidad.getTipoPersonaIPAT() != null)
            dto.setTipoPersonaIPAT(TipoPersonaIPATHelper.toLevel0DTO(entidad.getTipoPersonaIPAT()));
        if (entidad.getTipoLicencia() != null)
            dto.setTipoLicencia(TipoCategLicenciaConduccionHelper.toLevel0DTO(entidad.getTipoLicencia()));
        if (entidad.getEstadoFisico() != null)
            dto.setEstadoFisico(EstadoFisicoHelper.toLevel0DTO(entidad.getEstadoFisico()));
        if (entidad.getClaseVehiculo() != null)
            dto.setClaseVehiculo(ClaseVehiculoHelper.toLevel0DTO(entidad.getClaseVehiculo()));
        if (entidad.getTipoServicio() != null)
            dto.setTipoServicio(TipoServicioHelper.toLevel0DTO(entidad.getTipoServicio()));
        if (entidad.getSentido() != null)
            dto.setSentido(SentidoHelper.toLevel0DTO(entidad.getSentido()));
        if (entidad.getCarril() != null)
            dto.setCarril(CarrilHelper.toLevel0DTO(entidad.getCarril()));

        return dto;
    }

    public static List<DetalleAccidentalidadDTO> toListLevel0DTO(List<DetalleAccidentalidad> listEntidad) {
        List<DetalleAccidentalidadDTO> listDto = new ArrayList<DetalleAccidentalidadDTO>();
        for (DetalleAccidentalidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleAccidentalidadDTO> toListLevel1DTO(List<DetalleAccidentalidad> listEntidad) {
        List<DetalleAccidentalidadDTO> listDto = new ArrayList<DetalleAccidentalidadDTO>();
        for (DetalleAccidentalidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleAccidentalidad toLevel0Entity(DetalleAccidentalidadDTO dto, DetalleAccidentalidad entidad) {
        if (null == entidad) {
            entidad = new DetalleAccidentalidad();
        }
        entidad.setId(dto.getId());
        entidad.setBreveRelacion(dto.getBreveRelacion());
        entidad.setNombrePersona(dto.getNombrePersona());
        entidad.setLicencia(dto.getLicencia());
        entidad.setOrigen(dto.getOrigen());
        entidad.setEdad(dto.getEdad());
        entidad.setEmbriaguez(dto.getEmbriaguez());
        entidad.setAprehendido(dto.getAprehendido());
        entidad.setTipoVehiculo(dto.getTipoVehiculo());
        entidad.setPlaca(dto.getPlaca());
        entidad.setCalleCarretera(dto.getCalleCarretera());
        entidad.setRetenido(dto.getRetenido());
        entidad.setVersion(dto.getVersion());
        entidad.setDanosVehiculo(dto.getDanosVehiculo());
        entidad.setCircustancias(dto.getCircustancias());
        entidad.setObservaciones(dto.getObservaciones());
        entidad.setZona_impacto(dto.getZona_impacto());
        entidad.setDatos_adjuntos(dto.getDatos_adjuntos());

        return entidad;
    }

    public static DetalleAccidentalidad toLevel1Entity(DetalleAccidentalidadDTO dto, DetalleAccidentalidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getAccidentalidad() != null) {
            entidad.setAccidentalidad(new Accidentalidad());
            entidad.getAccidentalidad().setId(dto.getAccidentalidad().getId());
        }
        if (dto.getTipoPersonaIPAT() != null) {
            entidad.setTipoPersonaIPAT(new TipoPersonaIPAT());
            entidad.getTipoPersonaIPAT().setId(dto.getTipoPersonaIPAT().getId());
        }
        if (dto.getTipoLicencia() != null) {
            entidad.setTipoLicencia(new TipoCategLicenciaConduccion());
            entidad.getTipoLicencia().setId(dto.getTipoLicencia().getId());
        }
        if (dto.getEstadoFisico() != null) {
            entidad.setEstadoFisico(new EstadoFisico());
            entidad.getEstadoFisico().setId(dto.getEstadoFisico().getId());
        }
        if (dto.getClaseVehiculo() != null) {
            entidad.setClaseVehiculo(new ClaseVehiculo());
            entidad.getClaseVehiculo().setId(dto.getClaseVehiculo().getId());
        }
        if (dto.getTipoServicio() != null) {
            entidad.setTipoServicio(new TipoServicio());
            entidad.getTipoServicio().setId(dto.getTipoServicio().getId());
        }
        if (dto.getSentido() != null) {
            entidad.setSentido(new Sentido());
            entidad.getSentido().setId(dto.getSentido().getId());
        }
        if (dto.getCarril() != null) {
            entidad.setCarril(new Carril());
            entidad.getCarril().setId(dto.getCarril().getId());
        }

        return entidad;
    }

    public static List<DetalleAccidentalidad> toListLevel0Entity(List<DetalleAccidentalidadDTO> listDto) {
        List<DetalleAccidentalidad> listEntidad = new ArrayList<DetalleAccidentalidad>();
        for (DetalleAccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleAccidentalidad> toListLevel1Entity(List<DetalleAccidentalidadDTO> listDto) {
        List<DetalleAccidentalidad> listEntidad = new ArrayList<DetalleAccidentalidad>();
        for (DetalleAccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
