package co.com.datatools.c2.negocio.helpers.comun;

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

public class DireccionHelperExtend extends DireccionHelper {

    public static Direccion toLevel1Entity(DireccionDTO direccionDTO, Direccion direccion) {
        direccion = DireccionHelper.toLevel0Entity(direccionDTO, direccion);

        if (direccionDTO.getLocalidad() != null && direccionDTO.getLocalidad().getId() != null) {
            Localidad localidad = new Localidad();
            localidad.setId(direccionDTO.getLocalidad().getId());
            direccion.setLocalidad(localidad);
        }

        if (direccionDTO.getMunicipio() != null && direccionDTO.getMunicipio().getId() != null) {
            Municipio municipio = new Municipio();
            municipio.setId(direccionDTO.getMunicipio().getId());
            direccion.setMunicipio(municipio);
        }

        if (direccionDTO.getNombreViaPrincipal() != null && direccionDTO.getNombreViaPrincipal().getCodigo() != null) {
            NombreVia nombreVia = new NombreVia();
            nombreVia.setCodigo(direccionDTO.getNombreViaPrincipal().getCodigo());
            direccion.setNombreViaPrincipal(nombreVia);
        }

        if (direccionDTO.getNombreViaSecundaria() != null
                && direccionDTO.getNombreViaSecundaria().getCodigo() != null) {
            NombreVia nombreVia = new NombreVia();
            nombreVia.setCodigo(direccionDTO.getNombreViaSecundaria().getCodigo());
            direccion.setNombreViaSecundaria(nombreVia);
        }

        if (direccionDTO.getTipoCoordenada() != null && direccionDTO.getTipoCoordenada().getCodigo() != null) {
            TipoCoordenada tipoCoordenada = new TipoCoordenada();
            tipoCoordenada.setCodigo(direccionDTO.getTipoCoordenada().getCodigo());
            direccion.setTipoCoordenada(tipoCoordenada);
        }
        if (direccionDTO.getTipoUbicabilidad() != null && direccionDTO.getTipoUbicabilidad().getId() != null) {
            TipoUbicabilidad tipoUbicabilidad = new TipoUbicabilidad();
            tipoUbicabilidad.setId(direccionDTO.getTipoUbicabilidad().getId());
            direccion.setTipoUbicabilidad(tipoUbicabilidad);
        }

        if (direccionDTO.getTipoViaPrincipal() != null && direccionDTO.getTipoViaPrincipal().getCodigo() != null) {
            TipoVia tipoVia = new TipoVia();
            tipoVia.setCodigo(direccionDTO.getTipoViaPrincipal().getCodigo());
            direccion.setTipoViaPrincipal(tipoVia);
        }

        if (direccionDTO.getTipoViaSecundaria() != null && direccionDTO.getTipoViaSecundaria().getCodigo() != null) {
            TipoVia tipoVia = new TipoVia();
            tipoVia.setCodigo(direccionDTO.getTipoViaSecundaria().getCodigo());
            direccion.setTipoViaSecundaria(tipoVia);
        }

        if (direccionDTO.getCardinalidadViaPrincipal() != null
                && direccionDTO.getCardinalidadViaPrincipal().getCodigo() != null) {
            CardinalidadDireccion cardinalidadDireccion = new CardinalidadDireccion();
            cardinalidadDireccion.setCodigo(direccionDTO.getCardinalidadViaPrincipal().getCodigo());
            direccion.setCardinalidadViaPrincipal(cardinalidadDireccion);
        }

        if (direccionDTO.getCardinalidadViaSecundaria() != null
                && direccionDTO.getCardinalidadViaSecundaria().getCodigo() != null) {
            CardinalidadDireccion cardinalidadDireccion = new CardinalidadDireccion();
            cardinalidadDireccion.setCodigo(direccionDTO.getCardinalidadViaSecundaria().getCodigo());
            direccion.setCardinalidadViaSecundaria(cardinalidadDireccion);
        }

        if (direccionDTO.getPais() != null && direccionDTO.getPais().getId() != null) {
            direccion.setPais(new Pais());
            direccion.getPais().setId(direccionDTO.getPais().getId());
        }

        if (direccionDTO.getDepartamento() != null && direccionDTO.getDepartamento().getId() != null) {
            direccion.setDepartamento(new Departamento());
            direccion.getDepartamento().setId(direccionDTO.getDepartamento().getId());
        }

        return direccion;
    }

    public static DireccionDTO toLevel1DTO(Direccion entidad) {
        DireccionDTO dto = DireccionHelper.toLevel0DTO(entidad);

        if (entidad.getTipoViaPrincipal() != null) {
            dto.setTipoViaPrincipal(TipoViaHelper.toLevel0DTO(entidad.getTipoViaPrincipal()));
        } else {
            dto.setTipoViaPrincipal(new TipoViaDTO());
        }

        if (entidad.getNombreViaPrincipal() != null) {
            dto.setNombreViaPrincipal(NombreViaHelper.toLevel0DTO(entidad.getNombreViaPrincipal()));
        } else {
            dto.setNombreViaPrincipal(new NombreViaDTO());
        }

        if (entidad.getCardinalidadViaPrincipal() != null) {
            dto.setCardinalidadViaPrincipal(
                    CardinalidadDireccionHelper.toLevel0DTO(entidad.getCardinalidadViaPrincipal()));
        } else {
            dto.setCardinalidadViaPrincipal(new CardinalidadDireccionDTO());
        }

        if (entidad.getTipoViaSecundaria() != null) {
            dto.setTipoViaSecundaria(TipoViaHelper.toLevel0DTO(entidad.getTipoViaSecundaria()));
        } else {
            dto.setTipoViaSecundaria(new TipoViaDTO());
        }

        if (entidad.getNombreViaSecundaria() != null) {
            dto.setNombreViaSecundaria(NombreViaHelper.toLevel0DTO(entidad.getNombreViaSecundaria()));
        } else {
            dto.setNombreViaSecundaria(new NombreViaDTO());
        }

        if (entidad.getCardinalidadViaSecundaria() != null) {
            dto.setCardinalidadViaSecundaria(
                    CardinalidadDireccionHelper.toLevel0DTO(entidad.getCardinalidadViaSecundaria()));
        } else {
            dto.setCardinalidadViaSecundaria(new CardinalidadDireccionDTO());
        }

        if (entidad.getMunicipio() != null) {
            dto.setMunicipio(MunicipioHelper.toLevel1DTO(entidad.getMunicipio()));
            if (entidad.getMunicipio().getDepartamento() != null
                    && entidad.getMunicipio().getDepartamento().getId() != null) {
                dto.getMunicipio()
                        .setDepartamento(DepartamentoHelper.toLevel1DTO(entidad.getMunicipio().getDepartamento()));
            }
        } else {
            dto.setMunicipio(new MunicipioDTO());
        }

        if (entidad.getLocalidad() != null) {
            dto.setLocalidad(LocalidadHelper.toLevel0DTO(entidad.getLocalidad()));
        } else {
            dto.setLocalidad(new LocalidadDTO());
        }
        if (entidad.getTipoUbicabilidad() != null) {
            dto.setTipoUbicabilidad(TipoUbicabilidadHelper.toLevel0DTO(entidad.getTipoUbicabilidad()));
        } else {
            dto.setTipoUbicabilidad(new TipoUbicabilidadDTO());
        }

        if (entidad.getTipoCoordenada() != null) {
            dto.setTipoCoordenada(TipoCoordenadaHelper.toLevel0DTO(entidad.getTipoCoordenada()));
        } else {
            dto.setTipoCoordenada(new TipoCoordenadaDTO());
        }

        if (entidad.getPais() != null) {
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));
        } else {
            dto.setPais(new PaisDTO());
        }

        if (entidad.getDepartamento() != null) {
            dto.setDepartamento(DepartamentoHelper.toLevel0DTO(entidad.getDepartamento()));
        } else {
            dto.setDepartamento(new DepartamentoDTO());
        }

        return dto;
    }

    public static TipoViaDTO checkNullTipoVia(TipoViaDTO tipoVia) {
        if (tipoVia != null && tipoVia.getCodigo() == null)
            tipoVia = null;
        return tipoVia;
    }

    public static NombreViaDTO checkNullNombreVia(NombreViaDTO nombreVia) {
        if (nombreVia != null && nombreVia.getCodigo() == null)
            nombreVia = null;
        return nombreVia;
    }

    public static CardinalidadDireccionDTO checkNullCardinalidad(CardinalidadDireccionDTO cardinalidad) {
        if (cardinalidad != null && cardinalidad.getCodigo() == null)
            cardinalidad = null;
        return cardinalidad;
    }

    public static PaisDTO checkNullPais(PaisDTO pais) {
        if (pais != null && pais.getId() == null)
            pais = null;
        return pais;
    }

    public static DepartamentoDTO checkNullDepartamento(DepartamentoDTO departamento) {
        if (departamento != null && departamento.getId() == null)
            departamento = null;
        return departamento;
    }

    public static MunicipioDTO checkNullMunicipio(MunicipioDTO municipio) {
        if (municipio != null && municipio.getId() == null)
            municipio = null;
        return municipio;
    }

    public static LocalidadDTO checkNullLocalidad(LocalidadDTO localidad) {
        if (localidad != null && localidad.getId() == null)
            localidad = null;
        return localidad;
    }

}
