package co.com.datatools.c2.test.comparendo;

import java.util.ArrayList;
import java.util.Calendar;

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
import co.com.datatools.c2.negocio.ejb.RecibirComparendoEJB;
import co.com.datatools.c2.negocio.ejb.aud.UsuarioSesionEJB;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.JBossLoginContextFactory;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;

/**
 * <b>COM_002</b> Se deben ingresar comparendos de manera satisfactoria
 * 
 * @author giovanni.velandia<br>
 *         luis.forero(mod 2016-03-14)
 * 
 */
@RunWith(Arquillian.class)
public class IngresarComparendosTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(IngresarComparendosTest.class);

    @EJB
    private RecibirComparendoEJB iRRecibirComparendo;
    @EJB
    private UsuarioSesionEJB usuarioSesionEJB;

    private LoginContext loginContext = null;

    @Before
    public void before() throws LoginException {
        loginContext = JBossLoginContextFactory.createLoginContext("admin-c2", "admin1");
        loginContext.login();
    }

    @After
    public void after() throws LoginException {
        if (loginContext != null) {
            loginContext.logout();
        }
    }

    @Test
    public void recibirComparendoRegistroSatisfactorioBasico() {
        logger.debug("recibirComparendoRegistroSatisfactorioBasico");
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();

        // Validaciones bloqueantes

        // Origen de validacion
        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.RECIBIR_COMPARENDO);
        RespuestaValidacionDTO respuestaValidacionDTO = null;
        try {
            ProcesaComparendoDTO procesaComparendoDTO = new ProcesaComparendoDTO();

            // Codigo de la infraccion
            procesaComparendoDTO.setCodigoInfraccion("A01");

            // Fecha infraccion
            procesaComparendoDTO.setFechaInfraccion(Calendar.getInstance().getTime());

            // Hora de registro de la infraccion
            procesaComparendoDTO.setHoraInfraccion(Calendar.getInstance().getTime());

            // Polca
            procesaComparendoDTO.setEsPolca(false);

            // Medio de imposicion
            procesaComparendoDTO.setCodigoMedioImposicion(EnumMedioImposicion.MANUAL.getPk());

            // Numero de comparendo
            procesaComparendoDTO.setNumeroComparendo("11001000000000000000");

            // Fecha registro
            procesaComparendoDTO.setFechaRecepcion(Calendar.getInstance().getTime());

            // Codigo organismo de transito
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(11001);
            procesaComparendoDTO.setOrganismoTransito(organismoTransitoDTO);

            // Direcion comparendo
            ProcesaDireccionDTO procesaDireccionDTO = new ProcesaDireccionDTO();
            // TODO
            procesaComparendoDTO.setProcesaDireccionComparendo(procesaDireccionDTO);

            // Existe fuga del infractor
            procesaComparendoDTO.setExisteFugaInfractor(false);

            // Primer apellido
            procesaComparendoDTO.setApellido1Agente("Apel");

            // Primer nombre del agente
            procesaComparendoDTO.setNombre1Agente("Nomb");

            // Tipo documento del agente
            procesaComparendoDTO.setIdTipoIdentificacionAgente(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA.getValor());

            // Numero documento del agente
            procesaComparendoDTO.setNumeroIdentificacionAgente("6000002");

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
            procesaComparendoPersonaInfractorDTO.setApellido1("Jimenez");// Papellido
            procesaComparendoPersonaInfractorDTO.setNombre1("Julieta");// Pnombre
            procesaComparendoDTO.getProcesaComparendoPersonas().add(procesaComparendoPersonaInfractorDTO);

            // Validaciones de negocio
            procesaComparendoDTO.setNumeroPatio(1234);

            procesarComparendoDTO.setProcesaComparendoDTO(procesaComparendoDTO);
            respuestaValidacionDTO = iRRecibirComparendo.recibirComparendo(procesarComparendoDTO);

            if (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getValue())) {
                Assert.assertTrue(true);
            } else {
                Assert.fail(respuestaValidacionDTO.getCodigoResultado()
                        + ""
                        + respuestaValidacionDTO.getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento()
                                .getDescripcion());
            }
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void recibirComparendoRegistroSatisfactorioBasico2() {
        logger.debug("recibirComparendoRegistroSatisfactorioBasico");
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();

        // Validaciones bloqueantes

        // Origen de validacion
        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.RECIBIR_COMPARENDO);
        RespuestaValidacionDTO respuestaValidacionDTO = null;
        try {
            ProcesaComparendoDTO procesaComparendoDTO = new ProcesaComparendoDTO();

            // Codigo de la infraccion
            procesaComparendoDTO.setCodigoInfraccion("A01");

            // Fecha infraccion
            procesaComparendoDTO.setFechaInfraccion(Calendar.getInstance().getTime());

            // Hora de registro de la infraccion
            procesaComparendoDTO.setHoraInfraccion(Calendar.getInstance().getTime());

            // Polca
            procesaComparendoDTO.setEsPolca(false);

            // Medio de imposicion
            procesaComparendoDTO.setCodigoMedioImposicion(EnumMedioImposicion.MANUAL.getPk());

            // Numero de comparendo
            procesaComparendoDTO.setNumeroComparendo("11001000000000000003");

            // Fecha registro
            procesaComparendoDTO.setFechaRecepcion(Calendar.getInstance().getTime());

            // Codigo organismo de transito
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(11001);
            procesaComparendoDTO.setOrganismoTransito(organismoTransitoDTO);

            // Direcion comparendo
            ProcesaDireccionDTO procesaDireccionDTO = new ProcesaDireccionDTO();
            // TODO
            procesaComparendoDTO.setProcesaDireccionComparendo(procesaDireccionDTO);

            // Existe fuga del infractor
            procesaComparendoDTO.setExisteFugaInfractor(false);

            // Primer apellido
            procesaComparendoDTO.setApellido1Agente("Apel");

            // Primer nombre del agente
            procesaComparendoDTO.setNombre1Agente("Nomb");

            // Tipo documento del agente
            procesaComparendoDTO.setIdTipoIdentificacionAgente(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA.getValor());

            // Numero documento del agente
            procesaComparendoDTO.setNumeroIdentificacionAgente("6000002");

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
            procesaComparendoPersonaInfractorDTO.setApellido1("Jimenez");// Papellido
            procesaComparendoPersonaInfractorDTO.setNombre1("Julieta");// Pnombre
            procesaComparendoDTO.getProcesaComparendoPersonas().add(procesaComparendoPersonaInfractorDTO);

            // Validaciones de negocio
            procesaComparendoDTO.setNumeroPatio(1234);

            procesarComparendoDTO.setProcesaComparendoDTO(procesaComparendoDTO);
            respuestaValidacionDTO = iRRecibirComparendo.recibirComparendo(procesarComparendoDTO);

            if (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getValue())) {
                Assert.assertTrue(true);
            } else {
                Assert.fail(respuestaValidacionDTO.getCodigoResultado()
                        + ""
                        + respuestaValidacionDTO.getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento()
                                .getDescripcion());
            }
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void recibirComparendoRegistroSatisfactorioCompleto() {
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();

        // Validaciones bloqueantes

        // Origen de validacion
        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.RECIBIR_COMPARENDO);
        RespuestaValidacionDTO respuestaValidacionDTO = null;
        try {
            ProcesaComparendoDTO procesaComparendoDTO = new ProcesaComparendoDTO();
            procesaComparendoDTO.setProcesaComparendoPersonas(new ArrayList<ProcesaComparendoPersonaDTO>());

            // Codigo de la infraccion
            procesaComparendoDTO.setCodigoInfraccion("A01");

            // Fecha infraccion
            procesaComparendoDTO.setFechaInfraccion(Calendar.getInstance().getTime());

            // Hora de registro de la infraccion
            procesaComparendoDTO.setHoraInfraccion(Calendar.getInstance().getTime());

            // Polca
            procesaComparendoDTO.setEsPolca(false);

            // Medio de imposicion
            procesaComparendoDTO.setCodigoMedioImposicion(EnumMedioImposicion.MANUAL.getPk());

            // Numero de comparendo
            procesaComparendoDTO.setNumeroComparendo("11001000000000000001");

            // Fecha registro
            procesaComparendoDTO.setFechaRecepcion(Calendar.getInstance().getTime());

            // Codigo organismo de transito
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(11001);
            procesaComparendoDTO.setOrganismoTransito(organismoTransitoDTO);

            // Direcion comparendo
            ProcesaDireccionDTO procesaDireccionDTO = new ProcesaDireccionDTO();
            // TODO
            procesaComparendoDTO.setProcesaDireccionComparendo(procesaDireccionDTO);

            // Existe fuga del infractor
            procesaComparendoDTO.setExisteFugaInfractor(false);

            // Primer apellido
            procesaComparendoDTO.setApellido1Agente("Apel");

            // Primer nombre del agente
            procesaComparendoDTO.setNombre1Agente("Nomb");

            // Tipo documento del agente
            procesaComparendoDTO.setIdTipoIdentificacionAgente(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA.getValor());

            // Numero documento del agente
            procesaComparendoDTO.setNumeroIdentificacionAgente("6000002");

            // Usuario
            UsuarioPersonaDTO usuarioPersonaDTO = new UsuarioPersonaDTO();
            UsuarioDetalleDto usuarioDetalleDto = new UsuarioDetalleDto();
            usuarioDetalleDto.setId(-1);
            usuarioPersonaDTO.setUsuario(usuarioDetalleDto);
            procesaComparendoDTO.setUsuarioPersona(usuarioPersonaDTO);

            // Validaciones de negocio

            procesaComparendoDTO.setNumeroPatio(1234);

            // Datos completos comparendo

            // Segundo apellido del agente
            procesaComparendoDTO.setApellido2Agente("Sapellido");

            // VEHICULO
            procesaComparendoDTO.setPlacaVehiculo("VEN285");
            procesaComparendoDTO.setCodigoOrganismoMatriculaVehiculo(11001);
            procesaComparendoDTO.setIdClaseVehiculo(6);
            procesaComparendoDTO.setIdTipoServicio(3);
            procesaComparendoDTO.setIdRadioAccion(3);
            procesaComparendoDTO.setNumeroTarjetaOperacion("938271");

            // EMPRESA
            ProcesaComparendoPersonaDTO procesaComparendoPersonaEmpresaDTO = new ProcesaComparendoPersonaDTO();

            // Tipo de persona comparendo
            procesaComparendoPersonaEmpresaDTO
                    .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getCodigo());
            procesaComparendoPersonaEmpresaDTO.setIdTipoIdentificacion(EnumTipoIdentificacion.NUMERO_TRIBUTARIO
                    .getValor());
            procesaComparendoPersonaEmpresaDTO.setNumeroIdentificacion("10000000000");
            procesaComparendoPersonaEmpresaDTO.setRazonSocial("razon social");
            procesaComparendoDTO.getProcesaComparendoPersonas().add(procesaComparendoPersonaEmpresaDTO);

            // INFRACTOR
            ProcesaComparendoPersonaDTO procesaComparendoPersonaInfractorDTO = new ProcesaComparendoPersonaDTO();

            // Tipo de persona comparendo
            procesaComparendoPersonaInfractorDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR
                    .getCodigo());
            procesaComparendoPersonaInfractorDTO.setIdTipoIdentificacion(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA
                    .getValor());
            procesaComparendoPersonaInfractorDTO.setNumeroIdentificacion("6000002");
            procesaComparendoPersonaInfractorDTO.setApellido1("Papellido");
            procesaComparendoPersonaInfractorDTO.setNombre1("Pnombre");
            procesaComparendoDTO.getProcesaComparendoPersonas().add(procesaComparendoPersonaInfractorDTO);

            // PROPIETARIO
            ProcesaComparendoPersonaDTO procesaComparendoPersonaPropietarioDTO = new ProcesaComparendoPersonaDTO();

            // Tipo de persona comparendo
            procesaComparendoPersonaPropietarioDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO
                    .getCodigo());
            procesaComparendoPersonaPropietarioDTO.setIdTipoIdentificacion(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA
                    .getValor());
            procesaComparendoPersonaPropietarioDTO.setNumeroIdentificacion("6000002");
            procesaComparendoPersonaPropietarioDTO.setApellido1("Papellido");
            procesaComparendoPersonaPropietarioDTO.setNombre1("Pnombre");
            procesaComparendoDTO.getProcesaComparendoPersonas().add(procesaComparendoPersonaPropietarioDTO);

            // Observaciones
            procesaComparendoDTO.setObservacionesAgente("Prueba Unitaria");

            // TESTIGO
            ProcesaComparendoPersonaDTO procesaComparendoPersonaTestigoDTO = new ProcesaComparendoPersonaDTO();

            // Tipo de persona comparendo
            procesaComparendoPersonaTestigoDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO
                    .getCodigo());
            procesaComparendoPersonaTestigoDTO.setIdTipoIdentificacion(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA
                    .getValor());
            procesaComparendoPersonaTestigoDTO.setNumeroIdentificacion("6000002");
            procesaComparendoPersonaTestigoDTO.setApellido1("Papellido");
            procesaComparendoPersonaTestigoDTO.setNombre1("Pnombre");
            procesaComparendoDTO.getProcesaComparendoPersonas().add(procesaComparendoPersonaTestigoDTO);

            procesarComparendoDTO.setProcesaComparendoDTO(procesaComparendoDTO);
            respuestaValidacionDTO = iRRecibirComparendo.recibirComparendo(procesarComparendoDTO);

            if (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getValue())) {
                Assert.assertTrue(true);
            } else {
                Assert.fail(respuestaValidacionDTO.getCodigoResultado());
            }
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void recibirComparendoRechazado() {
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();

        // Validaciones bloqueantes

        // Origen de validacion
        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.RECIBIR_COMPARENDO);
        RespuestaValidacionDTO respuestaValidacionDTO = null;
        try {
            ProcesaComparendoDTO procesaComparendoDTO = new ProcesaComparendoDTO();

            // Codigo de la infraccion
            procesaComparendoDTO.setCodigoInfraccion("A01");

            // Fecha infraccion
            procesaComparendoDTO.setFechaInfraccion(Calendar.getInstance().getTime());

            // Hora de registro de la infraccion
            procesaComparendoDTO.setHoraInfraccion(Calendar.getInstance().getTime());

            // Polca
            procesaComparendoDTO.setEsPolca(false);

            // Medio de imposicion
            procesaComparendoDTO.setCodigoMedioImposicion(EnumMedioImposicion.MANUAL.getPk());

            // Fecha registro
            procesaComparendoDTO.setFechaRecepcion(Calendar.getInstance().getTime());

            // Codigo organismo de transito
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(11001);
            procesaComparendoDTO.setOrganismoTransito(organismoTransitoDTO);

            // Direcion comparendo
            ProcesaDireccionDTO procesaDireccionDTO = new ProcesaDireccionDTO();
            // TODO
            procesaComparendoDTO.setProcesaDireccionComparendo(procesaDireccionDTO);

            // Existe fuga del infractor
            procesaComparendoDTO.setExisteFugaInfractor(false);

            // Primer apellido
            procesaComparendoDTO.setApellido1Agente("Apel");

            // Primer nombre del agente
            procesaComparendoDTO.setNombre1Agente("Nomb");

            // Tipo documento del agente
            procesaComparendoDTO.setIdTipoIdentificacionAgente(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA.getValor());

            // Numero documento del agente
            procesaComparendoDTO.setNumeroIdentificacionAgente("6000002");

            // Usuario
            UsuarioPersonaDTO usuarioPersonaDTO = new UsuarioPersonaDTO();
            UsuarioDetalleDto usuarioDetalleDto = new UsuarioDetalleDto();
            usuarioDetalleDto.setId(-1);
            usuarioPersonaDTO.setUsuario(usuarioDetalleDto);
            procesaComparendoDTO.setUsuarioPersona(usuarioPersonaDTO);

            procesarComparendoDTO.setProcesaComparendoDTO(procesaComparendoDTO);
            respuestaValidacionDTO = iRRecibirComparendo.recibirComparendo(procesarComparendoDTO);

            if (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.RECHAZADO.getValue())) {
                Assert.assertTrue(true);
            } else {
                Assert.fail(respuestaValidacionDTO.getCodigoResultado());
            }
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
    }

}
