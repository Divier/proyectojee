package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.UbicabilidadSacDTO;
import co.com.datatools.c2.entidades.UbicabilidadSac;

/**
 * @author Generated
 * @version 3.0 - Wed May 11 18:37:36 COT 2016
 */
public class UbicabilidadSacHelper {
    // --- to DTO
    public static UbicabilidadSacDTO toLevel0DTO(UbicabilidadSac entidad) {
        UbicabilidadSacDTO dto = new UbicabilidadSacDTO();
        dto.setId(entidad.getId());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setDireccion(entidad.getDireccion());
        dto.setPais(entidad.getPais());
        dto.setDepartamento(entidad.getDepartamento());
        dto.setMunicipio(entidad.getMunicipio());
        dto.setLocalidad(entidad.getLocalidad());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());
        dto.setPrimerApellido(entidad.getPrimerApellido());
        dto.setPrimerNombre(entidad.getPrimerNombre());
        dto.setRazonSocial(entidad.getRazonSocial());
        dto.setSegundoApellido(entidad.getSegundoApellido());
        dto.setSegundoNombre(entidad.getSegundoNombre());
        dto.setTelefonoFijo(entidad.getTelefonoFijo());
        dto.setTelefonoMovil(entidad.getTelefonoMovil());

        return dto;
    }

    public static UbicabilidadSacDTO toLevel1DTO(UbicabilidadSac entidad) {
        UbicabilidadSacDTO dto = toLevel0DTO(entidad);
        dto.setIdEstadoTransaccionSac(entidad.getIdEstadoTransaccionSac());
        dto.setIdOrigenUbicabilidadSac(entidad.getIdOrigenUbicabilidadSac());
        dto.setIdTipoDocumentoSac(entidad.getIdTipoDocumentoSac());

        return dto;
    }

    public static List<UbicabilidadSacDTO> toListLevel0DTO(List<UbicabilidadSac> listEntidad) {
        List<UbicabilidadSacDTO> listDto = new ArrayList<UbicabilidadSacDTO>();
        for (UbicabilidadSac entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<UbicabilidadSacDTO> toListLevel1DTO(List<UbicabilidadSac> listEntidad) {
        List<UbicabilidadSacDTO> listDto = new ArrayList<UbicabilidadSacDTO>();
        for (UbicabilidadSac entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static UbicabilidadSac toLevel0Entity(UbicabilidadSacDTO dto, UbicabilidadSac entidad) {
        if (null == entidad) {
            entidad = new UbicabilidadSac();
        }
        entidad.setId(dto.getId());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setDireccion(dto.getDireccion());
        entidad.setPais(dto.getPais());
        entidad.setDepartamento(dto.getDepartamento());
        entidad.setMunicipio(dto.getMunicipio());
        entidad.setLocalidad(dto.getLocalidad());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());
        entidad.setPrimerApellido(dto.getPrimerApellido());
        entidad.setPrimerNombre(dto.getPrimerNombre());
        entidad.setRazonSocial(dto.getRazonSocial());
        entidad.setSegundoApellido(dto.getSegundoApellido());
        entidad.setSegundoNombre(dto.getSegundoNombre());
        entidad.setTelefonoFijo(dto.getTelefonoFijo());
        entidad.setTelefonoMovil(dto.getTelefonoMovil());

        return entidad;
    }

    public static UbicabilidadSac toLevel1Entity(UbicabilidadSacDTO dto, UbicabilidadSac entidad) {
        entidad = toLevel0Entity(dto, entidad);
        entidad.setIdEstadoTransaccionSac(dto.getIdEstadoTransaccionSac());
        entidad.setIdOrigenUbicabilidadSac(dto.getIdOrigenUbicabilidadSac());
        entidad.setIdTipoDocumentoSac(dto.getIdTipoDocumentoSac());

        return entidad;
    }

    public static List<UbicabilidadSac> toListLevel0Entity(List<UbicabilidadSacDTO> listDto) {
        List<UbicabilidadSac> listEntidad = new ArrayList<UbicabilidadSac>();
        for (UbicabilidadSacDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<UbicabilidadSac> toListLevel1Entity(List<UbicabilidadSacDTO> listDto) {
        List<UbicabilidadSac> listEntidad = new ArrayList<UbicabilidadSac>();
        for (UbicabilidadSacDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
