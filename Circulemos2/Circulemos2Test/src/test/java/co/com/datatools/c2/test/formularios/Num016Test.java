package co.com.datatools.c2.test.formularios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.util.date.UtilFecha;

/**
 * Clase que encapsula las pruebas unitarias de los metodos del EJB: AdministracionFormulariosEJB, relacionados con el caso de uso
 * CU_CRI20_DAT_NUM_016 Administrar numeración de formularios
 * 
 * @author claudia.rodriguez<br>
 *         luis.forero(mod 2015-06-04)
 */
@Ignore
@RunWith(Arquillian.class)
public class Num016Test extends BaseTest {

    public enum EnumTipoFormulario {
        COMPARENDERA_MANUAL_NACIONAL(1), //
        INFORME_DE_ACCIDENTALIDAD(2), //
        COMPARENDERA_TRANSPORTE_PUBLICO(3), //
        COMPARENDOS_ELECTRONICOS(4), //
        AYUDAS_TECNOLOGICAS(5), //
        ;
        private int id;

        private EnumTipoFormulario(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private static final Logger logger = Logger.getLogger(Num016Test.class);
    private static final String LOGIN_USUARIO_AUTENTICADO = "admin";
    private static final Integer CODIGO_ORGANISMO = 11001;
    private static final Integer ID_TIPO_FORMULARIO = 1;

    @EJB
    IRAdministracionFormularios administracionFormulariosEjb;

    @Test
    public void consultarNumeracionFormulario() {
        logger.debug("Num016Test::consultarFormularios()");
        List<NumeracionFormularioDTO> numeraciones = administracionFormulariosEjb.consultarNumeracionFormulario(null,
                null);
        Assert.assertTrue(numeraciones.size() > 0);
    }

    @Test
    public void registrarNumeracionExitoso() {
        logger.debug("Num016Test::registrarNumeracionExitoso()");
        NumeracionFormularioDTO numeracionFormularioDTO = new co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO();
        List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();
        DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();
        detalle.setIncremental(true);
        detalle.setNumerico(true);
        detalle.setCaracterInicio('0');
        detalle.setCaracterFin('9');
        detalle.setDigito((long) 1);
        detalleNumeracion.add(detalle);
        numeracionFormularioDTO.setDetalleNumeracionList(detalleNumeracion);
        numeracionFormularioDTO.setDigitos(1);
        numeracionFormularioDTO.setFechaFinal(UtilFecha.stringToDate("dd/MM/yyyy", "3/12/2017"));
        numeracionFormularioDTO.setFechaInicial(new Date());
        OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
        organismoTransito.setCodigoOrganismo(CODIGO_ORGANISMO);
        TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
        tipoFormulario.setId(EnumTipoFormulario.COMPARENDERA_TRANSPORTE_PUBLICO.getId());
        numeracionFormularioDTO.setTipoFormulario(tipoFormulario);
        numeracionFormularioDTO.setActivo(true);
        try {
            administracionFormulariosEjb.registrarNumeracionFormulario(numeracionFormularioDTO);
            Assert.assertTrue(true);
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void registrarNumeracionFallidoYaExiste() {
        logger.debug("Num016Test::registrarNumeracionFallidoYaExiste()");
        NumeracionFormularioDTO numeracionFormularioDTO = new co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO();
        List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();
        DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();
        detalle.setIncremental(true);
        detalle.setNumerico(false);
        detalle.setCaracterInicio('a');
        detalle.setCaracterFin('z');
        detalle.setDigito((long) 1);
        detalleNumeracion.add(detalle);
        numeracionFormularioDTO.setDetalleNumeracionList(detalleNumeracion);
        numeracionFormularioDTO.setDigitos(1);
        numeracionFormularioDTO.setFechaFinal(UtilFecha.stringToDate("dd/MM/yyyy", "01/01/2015"));
        numeracionFormularioDTO.setFechaInicial(UtilFecha.stringToDate("dd/MM/yyyy", "03/01/2015"));
        OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
        organismoTransito.setCodigoOrganismo(CODIGO_ORGANISMO);
        TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
        tipoFormulario.setId(EnumTipoFormulario.COMPARENDERA_MANUAL_NACIONAL.getId());
        numeracionFormularioDTO.setTipoFormulario(tipoFormulario);
        numeracionFormularioDTO.setActivo(true);
        try {
            administracionFormulariosEjb.registrarNumeracionFormulario(numeracionFormularioDTO);
            Assert.fail();
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void registrarNumeracionFallidoCasillasInvalidas() {
        logger.debug("Num016Test::registrarNumeracionFallidoCasillasInvalidas()");
        NumeracionFormularioDTO numeracionFormularioDTO = new co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO();
        List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();
        DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();
        detalle.setIncremental(true);
        detalle.setNumerico(false);
        detalle.setCaracterInicio('B');
        detalle.setCaracterFin('z');
        detalle.setDigito((long) 1);
        detalleNumeracion.add(detalle);
        numeracionFormularioDTO.setDetalleNumeracionList(detalleNumeracion);
        numeracionFormularioDTO.setDigitos(1);
        numeracionFormularioDTO.setFechaFinal(UtilFecha.stringToDate("dd/MM/yyyy", "3/12/2017"));
        numeracionFormularioDTO.setFechaInicial(new Date());
        OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
        organismoTransito.setCodigoOrganismo(CODIGO_ORGANISMO);
        TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
        tipoFormulario.setId(ID_TIPO_FORMULARIO);
        numeracionFormularioDTO.setTipoFormulario(tipoFormulario);

        try {
            administracionFormulariosEjb.registrarNumeracionFormulario(numeracionFormularioDTO);
            Assert.fail();
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void eliminarNumeracionExitoso() {
        logger.debug("Num016Test::eliminarNumeracionExitoso()");
        try {
            administracionFormulariosEjb.eliminarNumeracionFormulario(2);
            Assert.assertTrue(true);
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void editarNumeracionExitoso() {
        logger.debug("Num016Test::editarNumeracionExitoso()");
        try {
            NumeracionFormularioDTO numeracionFormularioDTO = new co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO();
            numeracionFormularioDTO.setId(1);
            List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();
            DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();

            detalle.setIncremental(true);
            detalle.setNumerico(true);
            detalle.setCaracterInicio('0');
            detalle.setCaracterFin('9');
            detalle.setDigito((long) 1);
            detalleNumeracion.add(detalle);

            detalle.setIncremental(true);
            detalle.setNumerico(true);
            detalle.setCaracterInicio('0');
            detalle.setCaracterFin('9');
            detalle.setDigito((long) 2);
            detalleNumeracion.add(detalle);

            detalle.setIncremental(true);
            detalle.setNumerico(true);
            detalle.setCaracterInicio('A');
            detalle.setCaracterFin('Z');
            detalle.setDigito((long) 3);
            detalleNumeracion.add(detalle);

            detalle.setIncremental(false);
            detalle.setNumerico(true);
            detalle.setCaracterInicio('A');
            detalle.setCaracterFin('A');
            detalle.setDigito((long) 4);
            detalleNumeracion.add(detalle);

            numeracionFormularioDTO.setDetalleNumeracionList(detalleNumeracion);
            numeracionFormularioDTO.setDigitos(4);
            numeracionFormularioDTO.setFechaFinal(UtilFecha.stringToDate("dd/MM/yyyy", "3/12/2017"));
            numeracionFormularioDTO.setFechaInicial(new Date());
            OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
            organismoTransito.setCodigoOrganismo(CODIGO_ORGANISMO);
            TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
            tipoFormulario.setId(ID_TIPO_FORMULARIO);
            numeracionFormularioDTO.setTipoFormulario(tipoFormulario);
            numeracionFormularioDTO.setActivo(true);
            administracionFormulariosEjb.actualizarNumeracionFormulario(numeracionFormularioDTO);
            Assert.assertTrue(true);
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void editarNumeracionFallidoCasillasInvalidas() {
        logger.debug("Num016Test::editarNumeracionFallidoCasillasInvalidas()");
        try {
            NumeracionFormularioDTO numeracionFormularioDTO = new co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO();
            numeracionFormularioDTO.setId(1);
            List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();
            DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();

            detalle.setIncremental(true);
            detalle.setNumerico(true);
            detalle.setCaracterInicio('0');
            detalle.setCaracterFin('f');
            detalle.setDigito((long) 1);
            detalleNumeracion.add(detalle);

            numeracionFormularioDTO.setDetalleNumeracionList(detalleNumeracion);
            numeracionFormularioDTO.setDigitos(1);
            numeracionFormularioDTO.setFechaFinal(UtilFecha.stringToDate("dd/MM/yyyy", "3/12/2017"));
            numeracionFormularioDTO.setFechaInicial(new Date());
            OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
            organismoTransito.setCodigoOrganismo(CODIGO_ORGANISMO);
            TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
            tipoFormulario.setId(ID_TIPO_FORMULARIO);
            numeracionFormularioDTO.setTipoFormulario(tipoFormulario);
            administracionFormulariosEjb.actualizarNumeracionFormulario(numeracionFormularioDTO);
            Assert.fail();
        } catch (CirculemosNegocioException e) {
            Assert.assertTrue(true);
        }

    }

}
