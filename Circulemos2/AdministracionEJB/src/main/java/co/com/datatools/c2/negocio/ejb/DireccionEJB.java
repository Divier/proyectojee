package co.com.datatools.c2.negocio.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.entidades.comun.CardinalidadDireccion;
import co.com.datatools.c2.entidades.comun.Departamento;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.Localidad;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.NombreVia;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.comun.TipoVia;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILDireccion;
import co.com.datatools.c2.negocio.interfaces.IRDireccion;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;
import co.com.datatools.util.GenericDao;

@Stateless(name = "DireccionEJB")
@LocalBean
public class DireccionEJB implements ILDireccion, IRDireccion {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(DireccionEJB.class.getName());

    @Override
    public RespuestaIngresarDireccionDTO ingresarDireccion(DireccionDTO direccionDTO,
            EnumTipoValidacionDireccion tipoValidacion) {
        RespuestaIngresarDireccionDTO rtaIngresar = new RespuestaIngresarDireccionDTO();

        // Se valida la direccion completa
        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = validarDireccion(direccionDTO, tipoValidacion);
        rtaIngresar.setRespuesta(rta.getRespuesta());

        // Si la direccion es incorrecta, no realiza insercion y retorna la lista de errores
        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            rtaIngresar.setDescripcionRespuesta(rta.getDescripcionRespuesta());
            rtaIngresar.setErroresCampo(rta.getErroresCampo());
            rtaIngresar.setNombreRespuesta(rta.getNombreRespuesta());

            return rtaIngresar;
        }

        // Si la direccion es correcta realiza la persistencia
        Direccion direccion = DireccionHelperExtend.toLevel1Entity(direccionDTO, null);
        em.persist(direccion);
        rtaIngresar.setIdDireccion(direccion.getId());

        return rtaIngresar;
    }

    @Override
    public RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> validarDireccion(DireccionDTO direccion,
            EnumTipoValidacionDireccion tipoValidacion) {
        PaisDTO paisDTO = DireccionHelperExtend.checkNullPais(direccion.getPais());
        DepartamentoDTO departamentoDTO = DireccionHelperExtend.checkNullDepartamento(direccion.getDepartamento());
        MunicipioDTO municipioDTO = DireccionHelperExtend.checkNullMunicipio(direccion.getMunicipio());
        LocalidadDTO localidadDTO = DireccionHelperExtend.checkNullLocalidad(direccion.getLocalidad());

        TipoViaDTO tipoViaPriDTO = DireccionHelperExtend.checkNullTipoVia(direccion.getTipoViaPrincipal());
        TipoViaDTO tipoViaSecDTO = DireccionHelperExtend.checkNullTipoVia(direccion.getTipoViaSecundaria());
        NombreViaDTO nombreViaPriDTO = DireccionHelperExtend.checkNullNombreVia(direccion.getNombreViaPrincipal());
        NombreViaDTO nombreViaSecDTO = DireccionHelperExtend.checkNullNombreVia(direccion.getNombreViaSecundaria());
        Integer numeroViaPri = direccion.getNumeroViaPrincipal();
        Integer numeroViaSec = direccion.getNumeroViaSecundaria();
        Character letraViaPri = direccion.getLetraViaPrincipal();
        Character letraViaSec = direccion.getLetraViaSecundaria();
        String bisViaPri = direccion.getBisViaPrincipal();
        String bisViaSec = direccion.getBisViaSecundaria();
        Character letraBisViaPri = direccion.getLetraBisViaPrincipal();
        Character letraBisViaSec = direccion.getLetraBisViaSecundaria();
        CardinalidadDireccionDTO cardiViaPriDTO = DireccionHelperExtend
                .checkNullCardinalidad(direccion.getCardinalidadViaPrincipal());
        CardinalidadDireccionDTO cardiViaSecDTO = DireccionHelperExtend
                .checkNullCardinalidad(direccion.getCardinalidadViaSecundaria());

        Integer numeroPlaca = direccion.getNumeroPlacaDomiciliaria();
        String complemento = direccion.getComplemento();

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> respuesta = new RespuestaDTO<>();
        boolean tipoViaPriNumerico = false, tipoViaSecNumerico = false, tipoViaPriCatalogo = false,
                tipoViaSecCatalogo = false;
        // ----------------------------------
        // 1 - VALIDAR OBLIGATORIO Y CATALOGO
        // ----------------------------------
        // [TVP]
        if (tipoViaPriDTO == null)
            respuesta.addErrorCampo(EnumCampoDireccion.TIPO_VIA_PRINCIPAL,
                    EnumErrorDireccion.TIPO_VIA_PRINCIPAL_OBLIGATORIO);
        else if (em.find(TipoVia.class, tipoViaPriDTO.getCodigo()) == null)
            respuesta.addErrorCampo(EnumCampoDireccion.TIPO_VIA_PRINCIPAL,
                    EnumErrorDireccion.TIPO_VIA_PRINCIPAL_INVALIDO);
        else {
            tipoViaPriNumerico = EnumTipoVia.obtenerTiposViaNumericos().contains(tipoViaPriDTO.getCodigo());
            tipoViaPriCatalogo = EnumTipoVia.obtenerTiposViaCatalogo().contains(tipoViaPriDTO.getCodigo());
        }

        // Datos obligatorios si [TVP] diferente a Sin Identificar
        if (tipoViaPriDTO != null && !tipoViaPriDTO.getCodigo().equals(EnumTipoVia.SIN_IDENTIFICAR.getCodigo())) {
            // [UBICACION]
            if (paisDTO == null)
                respuesta.addErrorCampo(EnumCampoDireccion.PAIS, EnumErrorDireccion.PAIS_OBLIGATORIO);
            else if (em.find(Pais.class, paisDTO.getId()) == null)
                respuesta.addErrorCampo(EnumCampoDireccion.PAIS, EnumErrorDireccion.PAIS_INVALIDO);
            if (departamentoDTO == null)
                respuesta.addErrorCampo(EnumCampoDireccion.DEPARTAMENTO, EnumErrorDireccion.DEPARTAMENTO_OBLIGATORIO);
            else if (em.find(Departamento.class, departamentoDTO.getId()) == null)
                respuesta.addErrorCampo(EnumCampoDireccion.DEPARTAMENTO, EnumErrorDireccion.DEPARTAMENTO_INVALIDO);
            if (municipioDTO == null)
                respuesta.addErrorCampo(EnumCampoDireccion.MUNICIPIO, EnumErrorDireccion.MUNICIPIO_OBLIGATORIO);
            else if (em.find(Municipio.class, municipioDTO.getId()) == null)
                respuesta.addErrorCampo(EnumCampoDireccion.MUNICIPIO, EnumErrorDireccion.MUNICIPIO_INVALIDO);
            if (localidadDTO != null && em.find(Localidad.class, localidadDTO.getId()) == null)
                respuesta.addErrorCampo(EnumCampoDireccion.LOCALIDAD, EnumErrorDireccion.LOCALIDAD_INVALIDO);

            // [TVS]
            if (tipoViaSecDTO == null)
                respuesta.addErrorCampo(EnumCampoDireccion.TIPO_VIA_SECUNDARIA,
                        EnumErrorDireccion.TIPO_VIA_SECUNDARIA_OBLIGATORIO);
            else if (em.find(TipoVia.class, tipoViaSecDTO.getCodigo()) == null)
                respuesta.addErrorCampo(EnumCampoDireccion.TIPO_VIA_SECUNDARIA,
                        EnumErrorDireccion.TIPO_VIA_SECUNDARIA_INVALIDO);
            else {
                tipoViaSecNumerico = EnumTipoVia.obtenerTiposViaNumericos().contains(tipoViaSecDTO.getCodigo());
                tipoViaSecCatalogo = EnumTipoVia.obtenerTiposViaCatalogo().contains(tipoViaSecDTO.getCodigo());
            }

            // [NVP]
            if (tipoViaPriCatalogo) {
                if (nombreViaPriDTO == null)
                    respuesta.addErrorCampo(EnumCampoDireccion.NOMBRE_VIA_PRINCIPAL,
                            EnumErrorDireccion.NOMBRE_VIA_PRINCIPAL_OBLIGATORIO);
                else if (em.find(NombreVia.class, nombreViaPriDTO.getCodigo()) == null)
                    respuesta.addErrorCampo(EnumCampoDireccion.NOMBRE_VIA_PRINCIPAL,
                            EnumErrorDireccion.NOMBRE_VIA_PRINCIPAL_INVALIDO);
            } else if (numeroViaPri == null)
                respuesta.addErrorCampo(EnumCampoDireccion.NUMERO_VIA_PRINCIPAL,
                        EnumErrorDireccion.NUMERO_VIA_PRINCIPAL_OBLIGATORIO);

            // [NVS]
            if (tipoViaSecCatalogo) {
                if (nombreViaSecDTO == null)
                    respuesta.addErrorCampo(EnumCampoDireccion.NOMBRE_VIA_SECUNDARIA,
                            EnumErrorDireccion.NOMBRE_VIA_SECUNDARIA_OBLIGATORIO);
                else if (em.find(NombreVia.class, nombreViaSecDTO.getCodigo()) == null)
                    respuesta.addErrorCampo(EnumCampoDireccion.NOMBRE_VIA_SECUNDARIA,
                            EnumErrorDireccion.NOMBRE_VIA_SECUNDARIA_INVALIDO);
            } else if (numeroViaSec == null)
                respuesta.addErrorCampo(EnumCampoDireccion.NUMERO_VIA_SECUNDARIA,
                        EnumErrorDireccion.NUMERO_VIA_SECUNDARIA_OBLIGATORIO);
        } else if (tipoViaSecDTO != null || nombreViaPriDTO != null || nombreViaSecDTO != null //
                || numeroViaPri != null || numeroViaSec != null //
                || letraViaPri != null || letraViaSec != null //
                || bisViaPri != null || bisViaSec != null //
                || letraBisViaPri != null || letraBisViaSec != null //
                || cardiViaPriDTO != null || cardiViaSecDTO != null)
            respuesta.addErrorCampo(EnumCampoDireccion.COMPLEMENTO, EnumErrorDireccion.COMPLEMENTO_UNICO);
        else if (complemento == null)
            // [COMPLEMENTO]
            respuesta.addErrorCampo(EnumCampoDireccion.COMPLEMENTO, EnumErrorDireccion.COMPLEMENTO_OBLIGATORIO);

        // [CVP, CVS]
        if (cardiViaPriDTO != null && em.find(CardinalidadDireccion.class, cardiViaPriDTO.getCodigo()) == null)
            respuesta.addErrorCampo(EnumCampoDireccion.CARDINALIDAD_VIA_PRINCIPAL,
                    EnumErrorDireccion.CARDINALIDAD_VIA_PRINCIPAL_INVALIDA);
        if (cardiViaSecDTO != null && em.find(CardinalidadDireccion.class, cardiViaSecDTO.getCodigo()) == null)
            respuesta.addErrorCampo(EnumCampoDireccion.CARDINALIDAD_VIA_SECUNDARIA,
                    EnumErrorDireccion.CARDINALIDAD_VIA_SECUNDARIA_INVALIDA);
        // ----------------------------------
        // 2 - VALIDAR REGLAS
        // ----------------------------------
        // [TVS] no puede ser igual a [TVP] en algunos casos (ver EnumTipoVia#obtenerTiposViaIguales)
        if (tipoViaPriDTO != null && !EnumTipoVia.obtenerTiposViaIguales().contains(tipoViaPriDTO.getCodigo()))
            if (tipoViaSecDTO != null && tipoViaPriDTO.getCodigo().equals(tipoViaSecDTO.getCodigo()))
                respuesta.addErrorCampo(EnumCampoDireccion.TIPO_VIA_SECUNDARIA,
                        EnumErrorDireccion.TIPO_VIA_SECUNDARIA_INVALIDO_IGUAL_TVP);

        // [LVP, BVP, LBVP]
        if (tipoViaPriNumerico) {
            if (letraViaPri != null && !Character.isUpperCase(letraViaPri))
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_VIA_PRINCIPAL,
                        EnumErrorDireccion.LETRA_VIA_PRINCIPAL_INVALIDA);
            if (bisViaPri != null) {
                if (!bisViaPri.matches(ConstantesComparendo.REGEX_DIRECCION_BIS))
                    respuesta.addErrorCampo(EnumCampoDireccion.BIS_VIA_PRINCIPAL,
                            EnumErrorDireccion.BIS_VIA_PRINCIPAL_INVALIDO);
                else if (letraBisViaPri != null && !Character.isUpperCase(letraBisViaPri))
                    respuesta.addErrorCampo(EnumCampoDireccion.LETRA_BIS_VIA_PRINCIPAL,
                            EnumErrorDireccion.LETRA_BIS_VIA_PRINCIPAL_INVALIDA);
            } else if (letraBisViaPri != null)
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_BIS_VIA_PRINCIPAL,
                        EnumErrorDireccion.LETRA_BIS_VIA_PRINCIPAL_INVALIDA_BIS);
        } else {
            if (letraViaPri != null)
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_VIA_PRINCIPAL,
                        EnumErrorDireccion.LETRA_VIA_PRINCIPAL_INVALIDA_NUMERICO);
            if (bisViaPri != null)
                respuesta.addErrorCampo(EnumCampoDireccion.BIS_VIA_PRINCIPAL,
                        EnumErrorDireccion.BIS_VIA_PRINCIPAL_INVALIDO_NUMERICO);
            if (letraBisViaPri != null)
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_BIS_VIA_PRINCIPAL,
                        EnumErrorDireccion.LETRA_BIS_VIA_PRINCIPAL_INVALIDA_NUMERICO);
        }

        // [LVS, BVS, LBVS]
        if (tipoViaSecNumerico) {
            if (letraBisViaSec != null && !Character.isUpperCase(letraViaSec))
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_VIA_SECUNDARIA,
                        EnumErrorDireccion.LETRA_VIA_SECUNDARIA_INVALIDA);
            if (bisViaSec != null) {
                if (!bisViaSec.matches(ConstantesComparendo.REGEX_DIRECCION_BIS))
                    respuesta.addErrorCampo(EnumCampoDireccion.BIS_VIA_SECUNDARIA,
                            EnumErrorDireccion.BIS_VIA_SECUNDARIA_INVALIDO);
                else if (letraBisViaSec != null && !Character.isUpperCase(letraBisViaSec))
                    respuesta.addErrorCampo(EnumCampoDireccion.LETRA_BIS_VIA_SECUNDARIA,
                            EnumErrorDireccion.LETRA_BIS_VIA_SECUNDARIA_INVALIDA);
            } else if (letraBisViaSec != null)
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_BIS_VIA_SECUNDARIA,
                        EnumErrorDireccion.LETRA_BIS_VIA_SECUNDARIA_INVALIDA_BIS);
        } else {
            if (letraViaSec != null)
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_VIA_SECUNDARIA,
                        EnumErrorDireccion.LETRA_VIA_SECUNDARIA_INVALIDA_NUMERICO);
            if (bisViaSec != null)
                respuesta.addErrorCampo(EnumCampoDireccion.BIS_VIA_SECUNDARIA,
                        EnumErrorDireccion.BIS_VIA_SECUNDARIA_INVALIDO_NUMERICO);
            if (letraBisViaSec != null)
                respuesta.addErrorCampo(EnumCampoDireccion.LETRA_BIS_VIA_SECUNDARIA,
                        EnumErrorDireccion.LETRA_BIS_VIA_SECUNDARIA_INVALIDA_NUMERICO);
        }

        // [CVP, CVS]
        if (cardiViaPriDTO != null && cardiViaSecDTO != null)
            if (cardiViaPriDTO.getCodigo().equals(cardiViaSecDTO.getCodigo()))
                respuesta.addErrorCampo(EnumCampoDireccion.CARDINALIDAD_VIA_SECUNDARIA,
                        EnumErrorDireccion.CARDINALIDAD_VIA_SECUNDARIA_INVALIDA_IGUAL_CVP);

        // [NUMPLACA]
        if (numeroPlaca != null && (Integer.compare(numeroPlaca, 0) < 0 || Integer.compare(numeroPlaca, 100) >= 0))
            respuesta.addErrorCampo(EnumCampoDireccion.NUMERO_PLACA_DOMICILIARIA,
                    EnumErrorDireccion.NUMERO_PLACA_DOMICILIARIA_INVALIDA);

        // [COMPLEMENTO]
        if (complemento != null) {
            boolean direccionValida = false;
            if (tipoValidacion.equals(EnumTipoValidacionDireccion.INFRACTOR)) {
                if (StringUtils.isNotBlank(complemento)
                        && complemento.matches(ConstantesComparendo.REGEX_DIRECCION_COMPLEMENTO)
                        && complemento.matches(ConstantesComparendo.REGEX_MAS_DE_UNA_PALABRA)
                        && !complemento.matches(ConstantesComparendo.REGEX_CARACTER_REPETIDO)) {
                    direccionValida = true;
                }
            } else if (tipoValidacion.equals(EnumTipoValidacionDireccion.INFRACCION)) {
                if (complemento.matches(ConstantesComparendo.REGEX_DIRECCION_COMPLEMENTO_SIMPLE)) {
                    direccionValida = true;
                }
            }
            if (!direccionValida) {
                respuesta.addErrorCampo(EnumCampoDireccion.COMPLEMENTO, EnumErrorDireccion.COMPLEMENTO_INVALIDO);
            }
        }

        // ----------------------------------
        // 2 - RETORNAR RESPUESTA
        // ----------------------------------
        if (respuesta.getErroresCampo().isEmpty())
            respuesta.setRespuesta(EnumRespuestaSistema.OK);
        else
            respuesta.setRespuesta(EnumRespuestaSistema.ERRORES);

        return respuesta;

    }

    @Override
    public DireccionDTO consultarDireccion(long idDireccion) {
        logger.debug("DireccionEJB::consultarDireccion(long)");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idDireccion", idDireccion);
        DireccionDTO direccionDTO = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d FROM Direccion d");
        sql.append(" WHERE d.id=:idDireccion");

        GenericDao<Direccion> resultado = new GenericDao<>(Direccion.class, em);
        List<Direccion> results = resultado.buildAndExecuteQuery(sql, filtros);

        if (results.size() > 0) {
            direccionDTO = DireccionHelperExtend.toLevel1DTO(results.get(0));
        }

        return direccionDTO;
    }

}
