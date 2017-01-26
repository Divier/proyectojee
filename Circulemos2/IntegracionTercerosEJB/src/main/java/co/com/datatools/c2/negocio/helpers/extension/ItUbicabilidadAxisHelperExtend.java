package co.com.datatools.c2.negocio.helpers.extension;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.ItUbicabilidadAxisDTO;
import co.com.datatools.c2.entidades.ItUbicabilidadAxis;
import co.com.datatools.c2.negocio.helpers.ItUbicabilidadAxisHelper;

public class ItUbicabilidadAxisHelperExtend extends ItUbicabilidadAxisHelper {

    public static List<ItUbicabilidadAxisDTO> toListLevel1DTOExtend(List<ItUbicabilidadAxis> listEntidad) {
        List<ItUbicabilidadAxisDTO> listDto = new ArrayList<ItUbicabilidadAxisDTO>();
        for (ItUbicabilidadAxis entidad : listEntidad) {
            listDto.add(toLevel0DTOExtend(entidad));
        }
        return listDto;
    }

    public static ItUbicabilidadAxisDTO toLevel0DTOExtend(ItUbicabilidadAxis entidad) {
        ItUbicabilidadAxisDTO dto = new ItUbicabilidadAxisDTO();
        dto.setId(entidad.getId());
        dto.setIdTipoDocumentoAxis(entidad.getIdTipoDocumentoAxis().trim());
        dto.setNumeroDocumento(entidad.getNumeroDocumento().trim());
        dto.setPrimerNombre(
                entidad.getPrimerNombre() != null ? entidad.getPrimerNombre().trim() : entidad.getPrimerNombre());
        dto.setSegundoNombre(
                entidad.getSegundoNombre() != null ? entidad.getSegundoNombre().trim() : entidad.getSegundoNombre());
        dto.setPrimerApellido(
                entidad.getPrimerApellido() != null ? entidad.getPrimerApellido().trim() : entidad.getPrimerApellido());
        dto.setSegundoApellido(entidad.getSegundoApellido() != null ? entidad.getSegundoApellido().trim()
                : entidad.getSegundoApellido());
        dto.setRazonSocial(
                entidad.getRazonSocial() != null ? entidad.getRazonSocial().trim() : entidad.getRazonSocial());
        dto.setDireccion(entidad.getDireccion().trim());
        dto.setLatitud(entidad.getLatitud());
        dto.setLongitud(entidad.getLongitud());
        dto.setTelefonoFijo(
                entidad.getTelefonoFijo() != null ? entidad.getTelefonoFijo().trim() : entidad.getTelefonoFijo());
        dto.setTelefonoMovil(
                entidad.getTelefonoMovil() != null ? entidad.getTelefonoMovil().trim() : entidad.getTelefonoMovil());
        dto.setCorreoElectronico(entidad.getCorreoElectronico() != null ? entidad.getCorreoElectronico().trim()
                : entidad.getCorreoElectronico());
        dto.setIdFuenteInformacion(entidad.getIdFuenteInformacion());
        dto.setIdEstadoLecturaAxis(entidad.getIdEstadoLecturaAxis());
        dto.setFechaReporte(entidad.getFechaReporte());

        return dto;
    }
}