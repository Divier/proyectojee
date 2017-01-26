package co.com.datatools.c2.test.comparendo;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumMedioImposicion;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.JBossLoginContextFactory;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;

@RunWith(Arquillian.class)
public class CorregirInconsistenciasTest extends BaseTest {

    @EJB
    private IRRecibirComparendo iRRecibirComparendo;

    LoginContext loginContext = null;

    private static final Logger logger = Logger.getLogger(IngresarInconsistenciasTest.class.getName());

    @Before
    public void before() {
        try {
            loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
            loginContext.login();
        } catch (LoginException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            loginContext.logout();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void corregirInconsistencia() {
        logger.debug("corregirInconsistencia");
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();

        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.CORREGIR_INCONSISTENCIA);
        RespuestaValidacionDTO respuestaValidacionDTO = null;
        try {

            ProcesaComparendoDTO procesaComparendoDTO = new ProcesaComparendoDTO();

            // Codigo de la infraccion
            procesaComparendoDTO.setCodigoInfraccion("A01");// A01

            // Fecha infraccion
            procesaComparendoDTO.setFechaInfraccion(new Date());

            // Hora de registro de la infraccion
            procesaComparendoDTO.setHoraInfraccion(Calendar.getInstance().getTime());

            // Polca
            procesaComparendoDTO.setEsPolca(false);// false

            // Medio de imposicion
            procesaComparendoDTO.setCodigoMedioImposicion(EnumMedioImposicion.MANUAL.getPk());

            // Numero de comparendo
            procesaComparendoDTO.setNumeroComparendo("11001000000000000004");

            // Fecha registro
            procesaComparendoDTO.setFechaRecepcion(new Date());

            // Codigo organismo de transito
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(11001);
            procesaComparendoDTO.setOrganismoTransito(organismoTransitoDTO);

            // Direcion comparendo
            ProcesaDireccionDTO procesaDireccionDTO = new ProcesaDireccionDTO();
            // procesaDireccionDTO.setId(8505L);
            // TODO
            procesaComparendoDTO.setProcesaDireccionComparendo(procesaDireccionDTO);

            // Existe fuga del infractor
            procesaComparendoDTO.setExisteFugaInfractor(false);

            // Primer apellido
            procesaComparendoDTO.setApellido1Agente("apel");

            // Primer nombre del agente
            procesaComparendoDTO.setNombre1Agente("nomb");

            // Tipo documento del agente
            procesaComparendoDTO.setIdTipoIdentificacionAgente(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA.getValor());

            // Numero documento del agente
            procesaComparendoDTO.setNumeroIdentificacionAgente("6000002");// 80831378

            // Usuario
            UsuarioPersonaDTO usuarioPersonaDTO = new UsuarioPersonaDTO();
            UsuarioDetalleDto usuarioDetalleDto = new UsuarioDetalleDto();
            usuarioDetalleDto.setId(-1);
            usuarioPersonaDTO.setUsuario(usuarioDetalleDto);
            procesaComparendoDTO.setUsuarioPersona(usuarioPersonaDTO);

            // INFRACTOR
            ProcesaComparendoPersonaDTO procesaComparendoPersonaInfractorDTO = new ProcesaComparendoPersonaDTO();
            procesaComparendoPersonaInfractorDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR
                    .getCodigo());
            procesaComparendoPersonaInfractorDTO.setIdTipoIdentificacion(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA
                    .getValor());
            procesaComparendoPersonaInfractorDTO.setNumeroIdentificacion("6548526");
            procesaComparendoPersonaInfractorDTO.setApellido1("Jimenez");
            procesaComparendoPersonaInfractorDTO.setNombre1("Julieta");
            procesaComparendoDTO.getProcesaComparendoPersonas().add(procesaComparendoPersonaInfractorDTO);

            // Validaciones de negocio
            procesaComparendoDTO.setNumeroPatio(1234);

            procesarComparendoDTO.setProcesaComparendoDTO(procesaComparendoDTO);

            procesarComparendoDTO.getProcesaComparendoDTO().setFechaOperacion(new Date());
            // UsuarioPersonaDTO usuarioPersona = iRSeguridadCirculemos.obtenerUsuarioDto();
            respuestaValidacionDTO = iRRecibirComparendo.recibirComparendo(procesarComparendoDTO);

            if (!respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getValue())) {
                Assert.fail(respuestaValidacionDTO.getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento()
                        .getDescripcion());
            }
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
    }

}
