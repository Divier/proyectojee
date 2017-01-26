package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.entidades.ProcesaDireccion;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class ProcesaDireccionHelper {
    // --- to DTO
    public static ProcesaDireccionDTO toLevel0DTO(ProcesaDireccion entidad) {
        ProcesaDireccionDTO dto = new ProcesaDireccionDTO();
        dto.setId(entidad.getId());
        dto.setCodigoTipoViaPrincipal(entidad.getCodigoTipoViaPrincipal());
        dto.setNumeroViaPrincipal(entidad.getNumeroViaPrincipal());
        dto.setCodigoNombreViaPrincipal(entidad.getCodigoNombreViaPrincipal());
        dto.setLetraViaPrincipal(entidad.getLetraViaPrincipal());
        dto.setBisViaPrincipal(entidad.getBisViaPrincipal());
        dto.setLetraBisViaPrincipal(entidad.getLetraBisViaPrincipal());
        dto.setCodigoCardinalidadViaPrincipal(entidad.getCodigoCardinalidadViaPrincipal());
        dto.setCodigoTipoViaSecundaria(entidad.getCodigoTipoViaSecundaria());
        dto.setNumeroViaSecundaria(entidad.getNumeroViaSecundaria());
        dto.setCodigoNombreViaSecundaria(entidad.getCodigoNombreViaSecundaria());
        dto.setLetraViaSecundaria(entidad.getLetraViaSecundaria());
        dto.setBisViaSecundaria(entidad.getBisViaSecundaria());
        dto.setLetraBisViaSecundaria(entidad.getLetraBisViaSecundaria());
        dto.setCodigoCardinalidadViaSecundario(entidad.getCodigoCardinalidadViaSecundario());
        dto.setNumeroPlacaDomiciliaria(entidad.getNumeroPlacaDomiciliaria());
        dto.setComplemento(entidad.getComplemento());
        dto.setIdMunicipio(entidad.getIdMunicipio());
        dto.setIdLocalidad(entidad.getIdLocalidad());
        dto.setIdDepartamento(entidad.getIdDepartamento());
        dto.setIdPais(entidad.getIdPais());
        dto.setCodigoTipoUbicabilidad(entidad.getCodigoTipoUbicabilidad());
        dto.setBarrio(entidad.getBarrio());
        dto.setCodigoTipoCoordenada(entidad.getCodigoTipoCoordenada());
        dto.setLatitud(entidad.getLatitud());
        dto.setLongitud(entidad.getLongitud());

        return dto;
    }

    public static ProcesaDireccionDTO toLevel1DTO(ProcesaDireccion entidad) {
        ProcesaDireccionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ProcesaDireccionDTO> toListLevel0DTO(List<ProcesaDireccion> listEntidad) {
        List<ProcesaDireccionDTO> listDto = new ArrayList<ProcesaDireccionDTO>();
        for (ProcesaDireccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ProcesaDireccionDTO> toListLevel1DTO(List<ProcesaDireccion> listEntidad) {
        List<ProcesaDireccionDTO> listDto = new ArrayList<ProcesaDireccionDTO>();
        for (ProcesaDireccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ProcesaDireccion toLevel0Entity(ProcesaDireccionDTO dto, ProcesaDireccion entidad) {
        if (null == entidad) {
            entidad = new ProcesaDireccion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoTipoViaPrincipal(dto.getCodigoTipoViaPrincipal());
        entidad.setNumeroViaPrincipal(dto.getNumeroViaPrincipal());
        entidad.setCodigoNombreViaPrincipal(dto.getCodigoNombreViaPrincipal());
        entidad.setLetraViaPrincipal(dto.getLetraViaPrincipal());
        entidad.setBisViaPrincipal(dto.getBisViaPrincipal());
        entidad.setLetraBisViaPrincipal(dto.getLetraBisViaPrincipal());
        entidad.setCodigoCardinalidadViaPrincipal(dto.getCodigoCardinalidadViaPrincipal());
        entidad.setCodigoTipoViaSecundaria(dto.getCodigoTipoViaSecundaria());
        entidad.setNumeroViaSecundaria(dto.getNumeroViaSecundaria());
        entidad.setCodigoNombreViaSecundaria(dto.getCodigoNombreViaSecundaria());
        entidad.setLetraViaSecundaria(dto.getLetraViaSecundaria());
        entidad.setBisViaSecundaria(dto.getBisViaSecundaria());
        entidad.setLetraBisViaSecundaria(dto.getLetraBisViaSecundaria());
        entidad.setCodigoCardinalidadViaSecundario(dto.getCodigoCardinalidadViaSecundario());
        entidad.setNumeroPlacaDomiciliaria(dto.getNumeroPlacaDomiciliaria());
        entidad.setComplemento(dto.getComplemento());
        entidad.setIdMunicipio(dto.getIdMunicipio());
        entidad.setIdLocalidad(dto.getIdLocalidad());
        entidad.setIdDepartamento(dto.getIdDepartamento());
        entidad.setIdPais(dto.getIdPais());
        entidad.setCodigoTipoUbicabilidad(dto.getCodigoTipoUbicabilidad());
        entidad.setBarrio(dto.getBarrio());
        entidad.setCodigoTipoCoordenada(dto.getCodigoTipoCoordenada());
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());

        return entidad;
    }

    public static ProcesaDireccion toLevel1Entity(ProcesaDireccionDTO dto, ProcesaDireccion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ProcesaDireccion> toListLevel0Entity(List<ProcesaDireccionDTO> listDto) {
        List<ProcesaDireccion> listEntidad = new ArrayList<ProcesaDireccion>();
        for (ProcesaDireccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ProcesaDireccion> toListLevel1Entity(List<ProcesaDireccionDTO> listDto) {
        List<ProcesaDireccion> listEntidad = new ArrayList<ProcesaDireccion>();
        for (ProcesaDireccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
