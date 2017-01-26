package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.entidades.GestionCobroSac;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 17 12:04:47 COT 2016
 */
public class GestionCobroSacHelper {
    // --- to DTO
    public static GestionCobroSacDTO toLevel0DTO(GestionCobroSac entidad) {
        GestionCobroSacDTO dto = new GestionCobroSacDTO();
        dto.setId(entidad.getId());
        dto.setIdHistoriaGestion(entidad.getIdHistoriaGestion());
        dto.setIdDeudor(entidad.getIdDeudor());
        dto.setIdCuenta(entidad.getIdCuenta());
        dto.setIdentificacion(entidad.getIdentificacion());
        dto.setMulta(entidad.getMulta());
        dto.setNumeroCitacion(entidad.getNumeroCitacion());
        dto.setFechaGestion(entidad.getFechaGestion());
        dto.setFechaNotificado(entidad.getFechaNotificado());
        dto.setTipoGestionSac(entidad.getTipoGestionSac());
        dto.setAccion(entidad.getAccion());
        dto.setRespuesta(entidad.getRespuesta());
        dto.setContacto(entidad.getContacto());
        dto.setResultadoAccion(entidad.getResultadoAccion());
        dto.setResultadoGestion(entidad.getResultadoGestion());
        dto.setTipoEvidencia(entidad.getTipoEvidencia());
        dto.setDatoUbicabilidad(entidad.getDatoUbicabilidad());
        dto.setObservaciones(entidad.getObservaciones());
        dto.setObservacionesInternas(entidad.getObservacionesInternas());
        dto.setIdCartera(entidad.getIdCartera());
        dto.setFechaRegistro(entidad.getFechaRegistro());

        return dto;
    }

    public static GestionCobroSacDTO toLevel1DTO(GestionCobroSac entidad) {
        GestionCobroSacDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<GestionCobroSacDTO> toListLevel0DTO(List<GestionCobroSac> listEntidad) {
        List<GestionCobroSacDTO> listDto = new ArrayList<GestionCobroSacDTO>();
        for (GestionCobroSac entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<GestionCobroSacDTO> toListLevel1DTO(List<GestionCobroSac> listEntidad) {
        List<GestionCobroSacDTO> listDto = new ArrayList<GestionCobroSacDTO>();
        for (GestionCobroSac entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static GestionCobroSac toLevel0Entity(GestionCobroSacDTO dto, GestionCobroSac entidad) {
        if (null == entidad) {
            entidad = new GestionCobroSac();
        }
        entidad.setId(dto.getId());
        entidad.setIdHistoriaGestion(dto.getIdHistoriaGestion());
        entidad.setIdDeudor(dto.getIdDeudor());
        entidad.setIdCuenta(dto.getIdCuenta());
        entidad.setIdentificacion(dto.getIdentificacion());
        entidad.setMulta(dto.getMulta());
        entidad.setNumeroCitacion(dto.getNumeroCitacion());
        entidad.setFechaGestion(dto.getFechaGestion());
        entidad.setFechaNotificado(dto.getFechaNotificado());
        entidad.setTipoGestionSac(dto.getTipoGestionSac());
        entidad.setAccion(dto.getAccion());
        entidad.setRespuesta(dto.getRespuesta());
        entidad.setContacto(dto.getContacto());
        entidad.setResultadoAccion(dto.getResultadoAccion());
        entidad.setResultadoGestion(dto.getResultadoGestion());
        entidad.setTipoEvidencia(dto.getTipoEvidencia());
        entidad.setDatoUbicabilidad(dto.getDatoUbicabilidad());
        entidad.setObservaciones(dto.getObservaciones());
        entidad.setObservacionesInternas(dto.getObservacionesInternas());
        entidad.setIdCartera(dto.getIdCartera());
        entidad.setFechaRegistro(dto.getFechaRegistro());

        return entidad;
    }

    public static GestionCobroSac toLevel1Entity(GestionCobroSacDTO dto, GestionCobroSac entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<GestionCobroSac> toListLevel0Entity(List<GestionCobroSacDTO> listDto) {
        List<GestionCobroSac> listEntidad = new ArrayList<GestionCobroSac>();
        for (GestionCobroSacDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<GestionCobroSac> toListLevel1Entity(List<GestionCobroSacDTO> listDto) {
        List<GestionCobroSac> listEntidad = new ArrayList<GestionCobroSac>();
        for (GestionCobroSacDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
