package co.com.datatools.c2.managed_bean.comparendo.administracion;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoCoordenadaDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;

/**
 * @author julio.pinzon
 */
public class AdminComparendoHelper {
    // --- to DTO
    public static ProcesaDireccionDTO toProcesaDireccionDTO(DireccionDTO direccionDTO) {
        ProcesaDireccionDTO dto = null;
        if (direccionDTO != null) {
            dto = new ProcesaDireccionDTO();
            if (direccionDTO.getTipoViaPrincipal() != null) {
                dto.setCodigoTipoViaPrincipal(direccionDTO.getTipoViaPrincipal().getCodigo());
            }
            dto.setNumeroViaPrincipal(direccionDTO.getNumeroViaPrincipal());
            if (direccionDTO.getNombreViaPrincipal() != null) {
                dto.setCodigoNombreViaPrincipal(direccionDTO.getNombreViaPrincipal().getCodigo());
            }
            dto.setLetraViaPrincipal(direccionDTO.getLetraViaPrincipal());
            dto.setBisViaPrincipal(direccionDTO.getBisViaPrincipal());
            dto.setLetraBisViaPrincipal(direccionDTO.getLetraBisViaPrincipal());
            if (direccionDTO.getCardinalidadViaPrincipal() != null) {
                dto.setCodigoCardinalidadViaPrincipal(direccionDTO.getCardinalidadViaPrincipal().getCodigo());
            }
            if (direccionDTO.getTipoViaSecundaria() != null) {
                dto.setCodigoTipoViaSecundaria(direccionDTO.getTipoViaSecundaria().getCodigo());
            }
            dto.setNumeroViaSecundaria(direccionDTO.getNumeroViaSecundaria());
            if (direccionDTO.getNombreViaSecundaria() != null) {
                dto.setCodigoNombreViaSecundaria(direccionDTO.getNombreViaSecundaria().getCodigo());
            }
            dto.setLetraViaSecundaria(direccionDTO.getLetraViaSecundaria());
            dto.setBisViaSecundaria(direccionDTO.getBisViaSecundaria());
            dto.setLetraBisViaSecundaria(direccionDTO.getLetraBisViaSecundaria());
            if (direccionDTO.getCardinalidadViaSecundaria() != null) {
                dto.setCodigoCardinalidadViaSecundario(direccionDTO.getCardinalidadViaSecundaria().getCodigo());
            }
            dto.setNumeroPlacaDomiciliaria(direccionDTO.getNumeroPlacaDomiciliaria());
            dto.setComplemento(direccionDTO.getComplemento());
            if (direccionDTO.getMunicipio() != null) {
                dto.setIdMunicipio(direccionDTO.getMunicipio().getId());
            }
            if (direccionDTO.getLocalidad() != null) {
                dto.setIdLocalidad(direccionDTO.getLocalidad().getId());
            }
            if (direccionDTO.getTipoUbicabilidad() != null) {
                dto.setCodigoTipoUbicabilidad(direccionDTO.getTipoUbicabilidad().getId());
            }
            dto.setBarrio(direccionDTO.getBarrio());
            if (direccionDTO.getTipoCoordenada() != null) {
                dto.setCodigoTipoCoordenada(direccionDTO.getTipoCoordenada().getCodigo());
            }
            dto.setLatitud(direccionDTO.getLatitud());
            dto.setLongitud(direccionDTO.getLongitud());
            if (direccionDTO.getPais() != null) {
                dto.setIdPais(direccionDTO.getPais().getId());
            }
            if (direccionDTO.getDepartamento() != null) {
                dto.setIdDepartamento(direccionDTO.getDepartamento().getId());
            }
        }

        return dto;
    }

    /**
     * Transforma el objeto ProcesaDireccionDTO en un objeto DireccionDTO
     * 
     * @param ProcesaDireccionDTO
     * @return
     * @author giovanni.velandia
     */
    public static DireccionDTO toDireccionDTO(ProcesaDireccionDTO procesaDireccionDTO) {
        DireccionDTO dto = null;
        if (procesaDireccionDTO != null) {
            dto = new DireccionDTO();
            if (procesaDireccionDTO.getCodigoTipoViaPrincipal() != null) {
                TipoViaDTO tipoViaDTO = new TipoViaDTO();
                tipoViaDTO.setCodigo(procesaDireccionDTO.getCodigoTipoViaPrincipal());
                dto.setTipoViaPrincipal(tipoViaDTO);
            }
            dto.setNumeroViaPrincipal(procesaDireccionDTO.getNumeroViaPrincipal());
            if (procesaDireccionDTO.getCodigoNombreViaPrincipal() != null) {
                NombreViaDTO nombreViaDTO = new NombreViaDTO();
                nombreViaDTO.setCodigo(procesaDireccionDTO.getCodigoNombreViaPrincipal());
                dto.setNombreViaPrincipal(nombreViaDTO);
            }
            dto.setLetraViaPrincipal(procesaDireccionDTO.getLetraViaPrincipal());
            dto.setBisViaPrincipal(procesaDireccionDTO.getBisViaPrincipal());
            dto.setLetraBisViaPrincipal(procesaDireccionDTO.getLetraBisViaPrincipal());
            if (procesaDireccionDTO.getCodigoCardinalidadViaPrincipal() != null) {
                CardinalidadDireccionDTO cardinalidadDireccionDTO = new CardinalidadDireccionDTO();
                cardinalidadDireccionDTO.setCodigo(procesaDireccionDTO.getCodigoCardinalidadViaPrincipal());
                dto.setCardinalidadViaPrincipal(cardinalidadDireccionDTO);
            }
            if (procesaDireccionDTO.getCodigoTipoViaSecundaria() != null) {
                TipoViaDTO tipoViaDTO = new TipoViaDTO();
                tipoViaDTO.setCodigo(procesaDireccionDTO.getCodigoTipoViaSecundaria());
                dto.setTipoViaSecundaria(tipoViaDTO);
            }
            dto.setNumeroViaSecundaria(procesaDireccionDTO.getNumeroViaSecundaria());
            if (procesaDireccionDTO.getCodigoNombreViaSecundaria() != null) {
                NombreViaDTO nombreViaDTO = new NombreViaDTO();
                nombreViaDTO.setCodigo(procesaDireccionDTO.getCodigoNombreViaSecundaria());
                dto.setNombreViaSecundaria(nombreViaDTO);
            }
            dto.setLetraViaSecundaria(procesaDireccionDTO.getLetraViaSecundaria());
            dto.setBisViaSecundaria(procesaDireccionDTO.getBisViaSecundaria());
            dto.setLetraBisViaSecundaria(procesaDireccionDTO.getLetraBisViaSecundaria());
            if (procesaDireccionDTO.getCodigoCardinalidadViaSecundario() != null) {
                CardinalidadDireccionDTO cardinalidadDireccionDTO = new CardinalidadDireccionDTO();
                cardinalidadDireccionDTO.setCodigo(procesaDireccionDTO.getCodigoCardinalidadViaSecundario());
                dto.setCardinalidadViaSecundaria(cardinalidadDireccionDTO);
            }
            dto.setNumeroPlacaDomiciliaria(procesaDireccionDTO.getNumeroPlacaDomiciliaria());
            dto.setComplemento(procesaDireccionDTO.getComplemento());
            if (procesaDireccionDTO.getIdMunicipio() != null) {
                MunicipioDTO municipioDTO = new MunicipioDTO();
                municipioDTO.setId(procesaDireccionDTO.getIdMunicipio());
                dto.setMunicipio(municipioDTO);
            }
            if (procesaDireccionDTO.getIdLocalidad() != null) {
                LocalidadDTO localidadDTO = new LocalidadDTO();
                localidadDTO.setId(procesaDireccionDTO.getIdLocalidad());
                dto.setLocalidad(localidadDTO);
            }
            if (procesaDireccionDTO.getCodigoTipoUbicabilidad() != null) {
                TipoUbicabilidadDTO tipoUbicabilidadDTO = new TipoUbicabilidadDTO();
                tipoUbicabilidadDTO.setId(procesaDireccionDTO.getCodigoTipoUbicabilidad());
                dto.setTipoUbicabilidad(tipoUbicabilidadDTO);
            }
            dto.setBarrio(procesaDireccionDTO.getBarrio());
            if (procesaDireccionDTO.getCodigoTipoCoordenada() != null) {
                TipoCoordenadaDTO tipoCoordenadaDTO = new TipoCoordenadaDTO();
                tipoCoordenadaDTO.setCodigo(procesaDireccionDTO.getCodigoTipoCoordenada());
                dto.setTipoCoordenada(tipoCoordenadaDTO);
            }
            dto.setLatitud(procesaDireccionDTO.getLatitud());
            dto.setLongitud(procesaDireccionDTO.getLongitud());
            if (procesaDireccionDTO.getIdPais() != null) {
                PaisDTO paisDTO = new PaisDTO();
                paisDTO.setId(procesaDireccionDTO.getIdPais());
                dto.setPais(paisDTO);
            }
            if (procesaDireccionDTO.getIdDepartamento() != null) {
                DepartamentoDTO departamentoDTO = new DepartamentoDTO();
                departamentoDTO.setId(procesaDireccionDTO.getIdDepartamento());
                dto.setDepartamento(departamentoDTO);
            }
        }

        return dto;
    }

    public static ProcesaComparendoPersonaDTO toProcesaComparendoPersonaDTO(PersonaDTO personaDTO,
            ProcesaComparendoPersonaDTO dto) {
        if (personaDTO.getTipoIdentificacion() != null) {
            dto.setIdTipoIdentificacion(personaDTO.getTipoIdentificacion().getId());
        }
        dto.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
        dto.setApellido1(personaDTO.getApellido1());
        dto.setApellido2(personaDTO.getApellido2());
        dto.setNombre1(personaDTO.getNombre1());
        dto.setNombre2(personaDTO.getNombre2());
        dto.setRazonSocial(null);
        if (personaDTO instanceof PersonaJuridicaDTO) {
            PersonaJuridicaDTO personaJuridicaDTO = (PersonaJuridicaDTO) personaDTO;
            dto.setRazonSocial(personaJuridicaDTO.getNombreComercial());
        }
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // if (dto.getCodigoTipoPersonaComparendo() != null) {
        // EnumTipoPersonaComparendo tipoPersonaComparendo = Utilidades.buscarElemEnum(
        // EnumTipoPersonaComparendo.class, dto.getCodigoTipoPersonaComparendo());
        // if (tipoPersonaComparendo.equals(EnumTipoPersonaComparendo.INFRACTOR)) {
        // dto.setEmail(personaDTO.getCorreoElectronico());
        // dto.setTelefonoFijo(personaDTO.getNumeroTelefonico());
        // }
        // if (tipoPersonaComparendo.equals(EnumTipoPersonaComparendo.TESTIGO)) {
        // dto.setTelefonoMovil(personaDTO.getNumeroCelular());
        // }
        // } else {
        // dto.setEmail(personaDTO.getCorreoElectronico());
        // dto.setTelefonoFijo(personaDTO.getNumeroTelefonico());
        // dto.setTelefonoMovil(personaDTO.getNumeroCelular());
        // }

        return dto;
    }

}
