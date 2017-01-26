package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.entidades.comun.CardinalidadDireccion;
import co.com.datatools.c2.entidades.comun.Departamento;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.Localidad;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.NombreVia;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.comun.TipoCoordenada;
import co.com.datatools.c2.entidades.comun.TipoUbicabilidad;
import co.com.datatools.c2.entidades.comun.TipoVia;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DireccionHelper {
    // --- to DTO
    public static DireccionDTO toLevel0DTO(Direccion entidad) {
        DireccionDTO dto = new DireccionDTO();
        dto.setId(entidad.getId());
        dto.setNumeroViaPrincipal(entidad.getNumeroViaPrincipal());
        dto.setLetraViaPrincipal(entidad.getLetraViaPrincipal());
        dto.setBisViaPrincipal(entidad.getBisViaPrincipal());
        dto.setLetraBisViaPrincipal(entidad.getLetraBisViaPrincipal());
        dto.setNumeroViaSecundaria(entidad.getNumeroViaSecundaria());
        dto.setLetraViaSecundaria(entidad.getLetraViaSecundaria());
        dto.setBisViaSecundaria(entidad.getBisViaSecundaria());
        dto.setLetraBisViaSecundaria(entidad.getLetraBisViaSecundaria());
        dto.setNumeroPlacaDomiciliaria(entidad.getNumeroPlacaDomiciliaria());
        dto.setComplemento(entidad.getComplemento());
        dto.setBarrio(entidad.getBarrio());
        dto.setLatitud(entidad.getLatitud());
        dto.setLongitud(entidad.getLongitud());

        return dto;
    }

    public static DireccionDTO toLevel1DTO(Direccion entidad) {
        DireccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoViaPrincipal() != null)
            dto.setTipoViaPrincipal(TipoViaHelper.toLevel0DTO(entidad.getTipoViaPrincipal()));
        if (entidad.getNombreViaPrincipal() != null)
            dto.setNombreViaPrincipal(NombreViaHelper.toLevel0DTO(entidad.getNombreViaPrincipal()));
        if (entidad.getCardinalidadViaPrincipal() != null)
            dto.setCardinalidadViaPrincipal(CardinalidadDireccionHelper.toLevel0DTO(entidad
                    .getCardinalidadViaPrincipal()));
        if (entidad.getTipoViaSecundaria() != null)
            dto.setTipoViaSecundaria(TipoViaHelper.toLevel0DTO(entidad.getTipoViaSecundaria()));
        if (entidad.getNombreViaSecundaria() != null)
            dto.setNombreViaSecundaria(NombreViaHelper.toLevel0DTO(entidad.getNombreViaSecundaria()));
        if (entidad.getCardinalidadViaSecundaria() != null)
            dto.setCardinalidadViaSecundaria(CardinalidadDireccionHelper.toLevel0DTO(entidad
                    .getCardinalidadViaSecundaria()));
        if (entidad.getMunicipio() != null)
            dto.setMunicipio(MunicipioHelper.toLevel0DTO(entidad.getMunicipio()));
        if (entidad.getLocalidad() != null)
            dto.setLocalidad(LocalidadHelper.toLevel0DTO(entidad.getLocalidad()));
        if (entidad.getTipoUbicabilidad() != null)
            dto.setTipoUbicabilidad(TipoUbicabilidadHelper.toLevel0DTO(entidad.getTipoUbicabilidad()));
        if (entidad.getTipoCoordenada() != null)
            dto.setTipoCoordenada(TipoCoordenadaHelper.toLevel0DTO(entidad.getTipoCoordenada()));
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));
        if (entidad.getDepartamento() != null)
            dto.setDepartamento(DepartamentoHelper.toLevel0DTO(entidad.getDepartamento()));

        return dto;
    }

    public static List<DireccionDTO> toListLevel0DTO(List<Direccion> listEntidad) {
        List<DireccionDTO> listDto = new ArrayList<DireccionDTO>();
        for (Direccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DireccionDTO> toListLevel1DTO(List<Direccion> listEntidad) {
        List<DireccionDTO> listDto = new ArrayList<DireccionDTO>();
        for (Direccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Direccion toLevel0Entity(DireccionDTO dto, Direccion entidad) {
        if (null == entidad) {
            entidad = new Direccion();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroViaPrincipal(dto.getNumeroViaPrincipal());
        entidad.setLetraViaPrincipal(dto.getLetraViaPrincipal());
        entidad.setBisViaPrincipal(dto.getBisViaPrincipal());
        entidad.setLetraBisViaPrincipal(dto.getLetraBisViaPrincipal());
        entidad.setNumeroViaSecundaria(dto.getNumeroViaSecundaria());
        entidad.setLetraViaSecundaria(dto.getLetraViaSecundaria());
        entidad.setBisViaSecundaria(dto.getBisViaSecundaria());
        entidad.setLetraBisViaSecundaria(dto.getLetraBisViaSecundaria());
        entidad.setNumeroPlacaDomiciliaria(dto.getNumeroPlacaDomiciliaria());
        entidad.setComplemento(dto.getComplemento());
        entidad.setBarrio(dto.getBarrio());
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());

        return entidad;
    }

    public static Direccion toLevel1Entity(DireccionDTO dto, Direccion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoViaPrincipal() != null) {
            entidad.setTipoViaPrincipal(new TipoVia());
            entidad.getTipoViaPrincipal().setCodigo(dto.getTipoViaPrincipal().getCodigo());
        }
        if (dto.getNombreViaPrincipal() != null) {
            entidad.setNombreViaPrincipal(new NombreVia());
            entidad.getNombreViaPrincipal().setCodigo(dto.getNombreViaPrincipal().getCodigo());
        }
        if (dto.getCardinalidadViaPrincipal() != null) {
            entidad.setCardinalidadViaPrincipal(new CardinalidadDireccion());
            entidad.getCardinalidadViaPrincipal().setCodigo(dto.getCardinalidadViaPrincipal().getCodigo());
        }
        if (dto.getTipoViaSecundaria() != null) {
            entidad.setTipoViaSecundaria(new TipoVia());
            entidad.getTipoViaSecundaria().setCodigo(dto.getTipoViaSecundaria().getCodigo());
        }
        if (dto.getNombreViaSecundaria() != null) {
            entidad.setNombreViaSecundaria(new NombreVia());
            entidad.getNombreViaSecundaria().setCodigo(dto.getNombreViaSecundaria().getCodigo());
        }
        if (dto.getCardinalidadViaSecundaria() != null) {
            entidad.setCardinalidadViaSecundaria(new CardinalidadDireccion());
            entidad.getCardinalidadViaSecundaria().setCodigo(dto.getCardinalidadViaSecundaria().getCodigo());
        }
        if (dto.getMunicipio() != null) {
            entidad.setMunicipio(new Municipio());
            entidad.getMunicipio().setId(dto.getMunicipio().getId());
        }
        if (dto.getLocalidad() != null) {
            entidad.setLocalidad(new Localidad());
            entidad.getLocalidad().setId(dto.getLocalidad().getId());
        }
        if (dto.getTipoUbicabilidad() != null) {
            entidad.setTipoUbicabilidad(new TipoUbicabilidad());
            entidad.getTipoUbicabilidad().setCodigo(dto.getTipoUbicabilidad().getCodigo());
        }
        if (dto.getTipoCoordenada() != null) {
            entidad.setTipoCoordenada(new TipoCoordenada());
            entidad.getTipoCoordenada().setCodigo(dto.getTipoCoordenada().getCodigo());
        }
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }
        if (dto.getDepartamento() != null) {
            entidad.setDepartamento(new Departamento());
            entidad.getDepartamento().setId(dto.getDepartamento().getId());
        }

        return entidad;
    }

    public static List<Direccion> toListLevel0Entity(List<DireccionDTO> listDto) {
        List<Direccion> listEntidad = new ArrayList<Direccion>();
        for (DireccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Direccion> toListLevel1Entity(List<DireccionDTO> listDto) {
        List<Direccion> listEntidad = new ArrayList<Direccion>();
        for (DireccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
