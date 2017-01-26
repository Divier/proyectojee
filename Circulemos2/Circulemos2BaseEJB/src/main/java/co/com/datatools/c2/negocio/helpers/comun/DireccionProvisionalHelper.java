package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.DireccionProvisionalDTO;
import co.com.datatools.c2.entidades.comun.CardinalidadDireccion;
import co.com.datatools.c2.entidades.comun.DireccionProvisional;
import co.com.datatools.c2.entidades.comun.Localidad;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.NombreVia;
import co.com.datatools.c2.entidades.comun.TipoUbicabilidad;
import co.com.datatools.c2.entidades.comun.TipoVia;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DireccionProvisionalHelper {
    // --- to DTO
    public static DireccionProvisionalDTO toLevel0DTO(DireccionProvisional entidad) {
        DireccionProvisionalDTO dto = new DireccionProvisionalDTO();
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

        return dto;
    }

    public static DireccionProvisionalDTO toLevel1DTO(DireccionProvisional entidad) {
        DireccionProvisionalDTO dto = toLevel0DTO(entidad);
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

        return dto;
    }

    public static List<DireccionProvisionalDTO> toListLevel0DTO(List<DireccionProvisional> listEntidad) {
        List<DireccionProvisionalDTO> listDto = new ArrayList<DireccionProvisionalDTO>();
        for (DireccionProvisional entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DireccionProvisionalDTO> toListLevel1DTO(List<DireccionProvisional> listEntidad) {
        List<DireccionProvisionalDTO> listDto = new ArrayList<DireccionProvisionalDTO>();
        for (DireccionProvisional entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DireccionProvisional toLevel0Entity(DireccionProvisionalDTO dto, DireccionProvisional entidad) {
        if (null == entidad) {
            entidad = new DireccionProvisional();
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

        return entidad;
    }

    public static DireccionProvisional toLevel1Entity(DireccionProvisionalDTO dto, DireccionProvisional entidad) {
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

        return entidad;
    }

    public static List<DireccionProvisional> toListLevel0Entity(List<DireccionProvisionalDTO> listDto) {
        List<DireccionProvisional> listEntidad = new ArrayList<DireccionProvisional>();
        for (DireccionProvisionalDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DireccionProvisional> toListLevel1Entity(List<DireccionProvisionalDTO> listDto) {
        List<DireccionProvisional> listEntidad = new ArrayList<DireccionProvisional>();
        for (DireccionProvisionalDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
