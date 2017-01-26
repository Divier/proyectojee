package co.com.datatools.c2.test.administracion;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.RespuestaDTO;
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
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumCardindalidadDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.negocio.interfaces.ILDireccion;
import co.com.datatools.c2.test.BaseTest;

/**
 * Circulemos 2.0 - CU_ADM_005 Validar direccion
 * 
 * @author rodrigo.cruz
 * 
 */
@RunWith(Arquillian.class)
public class Adm005Test extends BaseTest {

    private final static Logger logger = Logger.getLogger(Adm005Test.class);

    @EJB
    private ILDireccion direccionEJB;

    /**
     * TVP Sin identificar solo con Complemento
     */
    @Test
    public void validarDireccionTest1() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setComplemento("CASA 25");
        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.COMPLEMENTO)
                    .contains(EnumErrorDireccion.COMPLEMENTO_UNICO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest1");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest1");
        }
    }

    /**
     * Validar NVP numerico o catalogo: NVP debe ser numerico
     */
    @Test
    public void validarDireccionTest2() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        NombreViaDTO nvp = new NombreViaDTO(1);
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNombreViaPrincipal(nvp);
        direccion.setNumeroPlacaDomiciliaria(46);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.NUMERO_VIA_PRINCIPAL)
                    .contains(EnumErrorDireccion.NUMERO_VIA_PRINCIPAL_OBLIGATORIO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest2");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest2");
        }
    }

    /**
     * Validar NVS numerico o catalogo: NVS debe ser catalogo
     */
    @Test
    public void validarDireccionTest3() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.AVENIDA_CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.NOMBRE_VIA_SECUNDARIA)
                    .contains(EnumErrorDireccion.NOMBRE_VIA_SECUNDARIA_OBLIGATORIO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest3");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest3");
        }
    }

    /**
     * LVP solo si NVP es numerico
     */
    @Test
    public void validarDireccionTest4() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.AVENIDA_CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        NombreViaDTO nvp = new NombreViaDTO(1);
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNombreViaPrincipal(nvp);
        direccion.setNumeroPlacaDomiciliaria(12);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.LETRA_VIA_PRINCIPAL)
                    .contains(EnumErrorDireccion.LETRA_VIA_PRINCIPAL_INVALIDA_NUMERICO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest4");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest4");
        }
    }

    /**
     * BVS solo si NVS es numerico
     */
    @Test
    public void validarDireccionTest5() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.AUTOPISTA.getCodigo());
        NombreViaDTO nvs = new NombreViaDTO(1);
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setBisViaSecundaria("BIS");
        direccion.setLetraViaPrincipal('B');
        direccion.setMunicipio(mun);
        direccion.setNombreViaSecundaria(nvs);
        direccion.setNumeroPlacaDomiciliaria(8);
        direccion.setNumeroViaPrincipal(93);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.BIS_VIA_SECUNDARIA)
                    .contains(EnumErrorDireccion.BIS_VIA_SECUNDARIA_INVALIDO_NUMERICO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest5");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest5");
        }
    }

    /**
     * LBVP solo si tiene BVP
     */
    @Test
    public void validarDireccionTest6() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraBisViaPrincipal('A');
        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.LETRA_BIS_VIA_PRINCIPAL)
                    .contains(EnumErrorDireccion.LETRA_BIS_VIA_PRINCIPAL_INVALIDA_BIS));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest6");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest6");
        }
    }

    /**
     * Validar CVP
     */
    @Test
    public void validarDireccionTest7() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        CardinalidadDireccionDTO cvp = new CardinalidadDireccionDTO(EnumCardindalidadDireccion.OESTE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);
        DepartamentoDTO dep = new DepartamentoDTO(6);
        PaisDTO pais = new PaisDTO(47);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setPais(pais);
        direccion.setDepartamento(dep);
        direccion.setCardinalidadViaPrincipal(cvp);
        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        Assert.assertTrue(rta.getRespuesta().equals(EnumRespuestaSistema.OK));
    }

    /**
     * Validar Complemento
     */
    @Test
    public void validarDireccionTest8() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setComplemento("12345678901234567890123456789012345678901");
        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.COMPLEMENTO)
                    .contains(EnumErrorDireccion.COMPLEMENTO_INVALIDO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest8");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest8");
        }
    }

    /**
     * Si TVP es diferente de Sin identificar NVP es obligatorio
     */
    @Test
    public void validarDireccionTest9() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        // direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.NUMERO_VIA_PRINCIPAL)
                    .contains(EnumErrorDireccion.NUMERO_VIA_PRINCIPAL_OBLIGATORIO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest9");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest9");
        }
    }

    /**
     * Si TVP es diferente de Sin identificar TVS es obligatorio
     */
    @Test
    public void validarDireccionTest10() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        // TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        // direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.TIPO_VIA_SECUNDARIA)
                    .contains(EnumErrorDireccion.TIPO_VIA_SECUNDARIA_OBLIGATORIO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest10");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest10");
        }
    }

    /**
     * Si TVP es diferente de Sin identificar NVS es obligatorio
     */
    @Test
    public void validarDireccionTest11() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        // direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.NUMERO_VIA_SECUNDARIA)
                    .contains(EnumErrorDireccion.NUMERO_VIA_SECUNDARIA_OBLIGATORIO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest11");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest11");
        }
    }

    /**
     * Validar TVS igual a TVP (Caso: Sin identificar)
     */
    @Test
    public void validarDireccionTest12() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setComplemento("KILOMETRO CINCO");
        direccion.setMunicipio(mun);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.COMPLEMENTO)
                    .contains(EnumErrorDireccion.COMPLEMENTO_UNICO));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest12");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest12");
        }
    }

    /**
     * Validar TVS igual a TVP (Caso: Calle)
     */
    @Test
    public void validarDireccionTest13() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CALLE.getCodigo());
        MunicipioDTO mun = new MunicipioDTO(149);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setLetraViaPrincipal('B');
        direccion.setLetraViaSecundaria('A');
        direccion.setMunicipio(mun);
        direccion.setNumeroPlacaDomiciliaria(52);
        direccion.setNumeroViaPrincipal(93);
        direccion.setNumeroViaSecundaria(129);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.TIPO_VIA_SECUNDARIA)
                    .contains(EnumErrorDireccion.TIPO_VIA_SECUNDARIA_INVALIDO_IGUAL_TVP));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest13");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest13");
        }
    }

    /**
     * Validar TVS igual a TVP (Caso: Autopista)
     */
    @Test
    public void validarDireccionTest14() {
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.AUTOPISTA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.AUTOPISTA.getCodigo());
        NombreViaDTO nvp = new NombreViaDTO(1);
        NombreViaDTO nvs = new NombreViaDTO(2);
        MunicipioDTO mun = new MunicipioDTO(149);
        DepartamentoDTO dep = new DepartamentoDTO(6);
        PaisDTO pais = new PaisDTO(47);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setPais(pais);
        direccion.setDepartamento(dep);
        direccion.setMunicipio(mun);
        direccion.setNombreViaPrincipal(nvp);
        direccion.setNombreViaSecundaria(nvs);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        Assert.assertTrue(rta.getRespuesta().equals(EnumRespuestaSistema.OK));
    }

    /**
     * Validar CVS no en catalogo - Error campo CVS
     */
    @Test
    public void validarDireccionTest15() {
        CardinalidadDireccionDTO cvp = new CardinalidadDireccionDTO(EnumCardindalidadDireccion.SUR.getCodigo());
        CardinalidadDireccionDTO cvs = new CardinalidadDireccionDTO(0);
        TipoCoordenadaDTO tc = new TipoCoordenadaDTO(1);
        NombreViaDTO nvp = new NombreViaDTO(1);
        NombreViaDTO nvs = new NombreViaDTO(1);
        TipoUbicabilidadDTO tu = new TipoUbicabilidadDTO(1);
        TipoViaDTO tvp = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        TipoViaDTO tvs = new TipoViaDTO(EnumTipoVia.CARRERA.getCodigo());
        LocalidadDTO loc = new LocalidadDTO(1);
        MunicipioDTO mun = new MunicipioDTO(1);

        DireccionDTO direccion = new DireccionDTO();

        direccion.setBarrio("Barrio3");
        direccion.setBisViaPrincipal("1");
        direccion.setBisViaSecundaria("1");
        direccion.setCardinalidadViaPrincipal(cvp);
        direccion.setCardinalidadViaSecundaria(cvs);
        direccion.setNombreViaPrincipal(nvp);
        direccion.setNombreViaSecundaria(nvs);
        direccion.setNumeroViaPrincipal(12);
        direccion.setNumeroViaSecundaria(8);
        direccion.setTipoCoordenada(tc);
        direccion.setTipoUbicabilidad(tu);
        direccion.setTipoViaPrincipal(tvp);
        direccion.setTipoViaSecundaria(tvs);
        direccion.setComplemento("complementoTestigo");
        direccion.setLocalidad(loc);
        direccion.setMunicipio(mun);
        direccion.setLetraBisViaPrincipal('a');
        direccion.setLetraBisViaSecundaria('b');
        direccion.setLetraViaPrincipal('c');
        direccion.setLetraViaSecundaria('d');
        direccion.setNumeroPlacaDomiciliaria(58);
        direccion.setNumeroViaPrincipal(12);
        direccion.setNumeroViaSecundaria(8);

        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = direccionEJB.validarDireccion(direccion);

        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            Assert.assertTrue(rta.getErroresCampo().get(EnumCampoDireccion.CARDINALIDAD_VIA_SECUNDARIA)
                    .contains(EnumErrorDireccion.CARDINALIDAD_VIA_SECUNDARIA_INVALIDA));
        } else {
            logger.error("ERROR - Adm005Test::validarDireccionTest15");
            Assert.fail("ERROR - Adm005Test::validarDireccionTest15");
        }
    }

}
