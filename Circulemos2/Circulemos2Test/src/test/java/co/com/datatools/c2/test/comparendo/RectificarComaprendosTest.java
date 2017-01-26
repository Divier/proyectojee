package co.com.datatools.c2.test.comparendo;

import java.util.ArrayList;

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

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comparendo.CampoRectificaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.MedioImposicionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfractorDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.enumeracion.EnumCampoEntidad;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.ejb.aud.UsuarioSesionEJB;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.JBossLoginContextFactory;

/**
 * <b>COM_014</b> Se debe rectificar al menos un comparendo de los ingresados satisfactoriamente.
 * 
 * @author luis.forero(2016-03-14)
 */
@RunWith(Arquillian.class)
public class RectificarComaprendosTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(RectificarComaprendosTest.class);

    @EJB
    private IRComparendo comparendoEJB;

    @EJB
    private IRRecibirComparendo recibirComparendoEJB;

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
    public void rectificarComparendoTest() {
        logger.debug(RectificarComaprendosTest.class.getName().concat("::rectificarComparendoTest()"));

        try {
            Long cicomparendo = comparendoEJB.consultarComparendo("11001000000000000001", 11001).getCicomparendo();
            ProcesaComparendoRectificadoDTO comparendoRectificadoDTO = comparendoToProcesaCompRectificadoDTO(comparendoEJB
                    .consultarComparendo(cicomparendo));

            ArrayList<CampoRectificaComparendoDTO> campoRectificaComparendoDTOs = new ArrayList<CampoRectificaComparendoDTO>();
            String codigoInfraccion = "A03";

            CampoRectificaComparendoDTO campoRectificado = crearCambioCampo(EnumCampoEntidad.INFRACCION,
                    comparendoRectificadoDTO.getCodigoInfraccion(), codigoInfraccion);
            comparendoRectificadoDTO.setIdInfraccion(6);
            comparendoRectificadoDTO.setCodigoInfraccion(codigoInfraccion);
            campoRectificaComparendoDTOs.add(campoRectificado);
            comparendoRectificadoDTO.setCampoRectificaComparendoDTOs(campoRectificaComparendoDTOs);
            RespuestaValidacionDTO rectificarComparendo = recibirComparendoEJB
                    .rectificarComparendo(comparendoRectificadoDTO);
            if (!rectificarComparendo.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getValue())) {
                Assert.fail(rectificarComparendo.getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento()
                        .getDescripcion());
            }
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Crea una isntancia nueva de un campo rectificado del comparendo
     * 
     * @param campoEntidad
     *            campo sobre el cual se efectua la rectificacion
     * @param valorOriginal
     *            valor original del campo
     * @param valorNuevo
     *            valor nuevo que toma el campo
     * @return Retorna una instancia nueva del campo correspondiente.
     * @author luis.forero(2016-02-03)
     */
    private static CampoRectificaComparendoDTO crearCambioCampo(EnumCampoEntidad campoEntidad, String valorOriginal,
            String valorNuevo) {
        CampoRectificaComparendoDTO campo = new CampoRectificaComparendoDTO();
        campo.setCampoEntidad(new CampoEntidadDTO(campoEntidad.getValue()));
        campo.setValorOriginal(valorOriginal);
        campo.setValorNuevo(valorNuevo);
        return campo;
    }

    private ProcesaComparendoRectificadoDTO comparendoToProcesaCompRectificadoDTO(ComparendoDTO detalleComparendo) {
        ProcesaComparendoRectificadoDTO procesaComparendo = new ProcesaComparendoRectificadoDTO();
        procesaComparendo.setNumeroComparendo(detalleComparendo.getOrdenComparendoNacional().getNumeroComparendo());

        procesaComparendo.setOrganismoTransito(detalleComparendo.getOrdenComparendoNacional().getOrganismoTransito()
                .clone());
        procesaComparendo.setComparendo(detalleComparendo);
        MedioImposicionComparendoDTO medioImposicion = detalleComparendo.getMedioImposicion();
        if (medioImposicion != null) {
            procesaComparendo.setCodigoMedioImposicion(medioImposicion.getId() != null ? new Integer(medioImposicion
                    .getId()) : null);
        }
        procesaComparendo.setEsPolca(detalleComparendo.getEsPolca());
        procesaComparendo.setFechaInfraccion(detalleComparendo.getFechaInfraccion());
        procesaComparendo.setHoraInfraccion(procesaComparendo.getFechaInfraccion());

        procesaComparendo.setExisteFugaInfractor(detalleComparendo.getExisteFugaInfractor());

        procesaComparendo.setCodigoInfraccion(new String(detalleComparendo.getInfraccion().getCodigo()));
        procesaComparendo.setFechaRecepcion(detalleComparendo.getFechaRegistro());

        DireccionDTO direccionInfraccion = detalleComparendo.getDireccion().clone();

        procesaComparendo.setProcesaDireccionComparendo(toProcesaDireccionDTO(direccionInfraccion));

        // ********************************************************************************
        // ******************************* VEHICULO ***************************************
        // ********************************************************************************
        ComparendoVehiculoDTO comparendoVehiculo = detalleComparendo.getComparendoVehiculo();
        if (comparendoVehiculo != null) {
            comparendoVehiculo = comparendoVehiculo.clone();
            procesaComparendo.setPlacaVehiculo(comparendoVehiculo.getPlacaVehiculo());
            procesaComparendo.setIdentificacionVehiculo(comparendoVehiculo.getIdentificacionVehiculo());
            OrganismoTransitoDTO organismoMatriVehic = comparendoVehiculo.getOrganismoMatriVehic();
            if (organismoMatriVehic != null) {
                procesaComparendo.setCodigoOrganismoMatriculaVehiculo(organismoMatriVehic.getCodigoOrganismo());
            }
            procesaComparendo.setIdTipoServicio(comparendoVehiculo.getTipoServicio() != null ? comparendoVehiculo
                    .getTipoServicio().getId() : null);
            procesaComparendo.setIdClaseVehiculo(comparendoVehiculo.getClaseVehiculo() != null ? comparendoVehiculo
                    .getClaseVehiculo().getId() : null);
            procesaComparendo.setIdRadioAccion(comparendoVehiculo.getRadioAccion() != null ? comparendoVehiculo
                    .getRadioAccion().getId() : null);
            procesaComparendo.setIdModalidad(comparendoVehiculo.getModalidad() != null ? comparendoVehiculo
                    .getModalidad().getId() : null);
            procesaComparendo
                    .setCodigoTipoTransPasajero(comparendoVehiculo.getTipoTransPasajero() != null ? comparendoVehiculo
                            .getTipoTransPasajero().getId() : null);
            procesaComparendo.setNumeroTarjetaOperacion(comparendoVehiculo.getNumeroTarjetaOperacion());

        }
        ComparendoPersonaDTO empresa = detalleComparendo.getEmpresa();

        procesaComparendo.setProcesaComparendoPersonas(new ArrayList<ProcesaComparendoPersonaDTO>());

        ProcesaComparendoPersonaDTO empresaVehiculo = new ProcesaComparendoPersonaDTO();
        if (empresa != null) {
            empresaVehiculo.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue());
            empresaVehiculo.setIdTipoIdentificacion(empresa.getTipoIdentificacion().getId());
            empresaVehiculo.setNumeroIdentificacion(empresa.getNumeroIdentificacion());
            empresaVehiculo.setRazonSocial(empresa.getRazonSocial());
            procesaComparendo.getProcesaComparendoPersonas().add(empresaVehiculo);
        }

        // ********************************************************************************
        // ******************************* INFRACTOR **************************************
        // ********************************************************************************
        ComparendoPersonaDTO infractor = detalleComparendo.getInfractor();
        ComparendoPersonaDTO propietario = detalleComparendo.getPropietario();
        if (infractor != null) {
            ProcesaComparendoPersonaDTO procesaInfractor = getProcesaComparendoPersonaDTO(infractor);
            procesaInfractor.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getValue());

            TipoInfractorDTO tipoInfractor = detalleComparendo.getTipoInfractor();
            if (tipoInfractor != null) {
                procesaComparendo.setCodigoTipoInfractor(tipoInfractor.getId() != null ? new Integer(tipoInfractor
                        .getId()) : null);
            }

            // Datos direccion infractor
            DireccionDTO dirInfractor = infractor.getDireccion();
            if (dirInfractor != null) {
                procesaInfractor.setProcesaDireccion(toProcesaDireccionDTO(dirInfractor));
            }

            procesaComparendo.getProcesaComparendoPersonas().add(procesaInfractor);
        }

        // ********************************************************************************
        // ****************************** PROPIETARIO *************************************
        // ********************************************************************************
        ProcesaComparendoPersonaDTO procesaPropietarioDTO = new ProcesaComparendoPersonaDTO();
        if (propietario != null) {
            procesaPropietarioDTO = getProcesaComparendoPersonaDTO(propietario);
            procesaPropietarioDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
            if (comparendoVehiculo != null) {
                procesaComparendo
                        .setCodigoOrganismoLicenciaTransito(comparendoVehiculo.getOrganismoLicenciaTransito() != null ? comparendoVehiculo
                                .getOrganismoLicenciaTransito().getCodigoOrganismo() : null);

                procesaComparendo.setNumeroLicenciaTransito(comparendoVehiculo.getNumeroLicenciaTransito());
            }
            procesaComparendo.getProcesaComparendoPersonas().add(procesaPropietarioDTO);
        }

        // ********************************************************************************
        // ****************************** AGENTE DE TRANSITO ******************************
        // ********************************************************************************
        ComparendoAgenteDTO comparendoAgente = detalleComparendo.getComparendoAgente().clone();

        // reinicia valores
        procesaComparendo.setIdAgenteTransito(comparendoAgente.getAgente() != null ? comparendoAgente.getAgente()
                .getId() : null);
        procesaComparendo.setPlacaAgente(comparendoAgente.getPlaca());
        procesaComparendo
                .setIdTipoIdentificacionAgente(comparendoAgente.getTipoIdentificacionPersona() != null ? comparendoAgente
                        .getTipoIdentificacionPersona().getId() : null);
        procesaComparendo.setNumeroIdentificacionAgente(comparendoAgente.getNumeroIdentificacion());
        procesaComparendo.setNombre1Agente(comparendoAgente.getNombre1());
        procesaComparendo.setNombre2Agente(comparendoAgente.getNombre2());
        procesaComparendo.setApellido1Agente(comparendoAgente.getApellido1());
        procesaComparendo.setApellido2Agente(comparendoAgente.getApellido2());

        if (detalleComparendo.getEsPolca()) {
            procesaComparendo.setNombreResponsable(comparendoAgente.getNombreResponsable());

        } else {
            procesaComparendo.setNombreResponsable(null);
            UnificacionResponsableDTO unificacionResponsable = comparendoAgente.getUnificacionResponsable();
            if (unificacionResponsable != null) {
                procesaComparendo.setIdUnificacionResponsable(unificacionResponsable.getId());
            }
        }

        procesaComparendo.setObservacionesAgente(comparendoAgente.getObservacionesAgente());

        // ********************************************************************************
        // ********************************* INMOVILIZACION *******************************
        // ********************************************************************************
        ComparendoPatioDTO comparendoPatio = detalleComparendo.getComparendoPatio();
        if (comparendoPatio != null) {
            procesaComparendo.setNumeroPatio(comparendoPatio.getNumeroPatio());
            procesaComparendo.setNombrePatio(comparendoPatio.getNombre());
            procesaComparendo.setNumeroGrua(comparendoPatio.getNumeroGrua());
            procesaComparendo.setPlacaGrua(comparendoPatio.getPlacaGrua());
            procesaComparendo.setConsecutivoInmovilizacion(comparendoPatio.getConsecutivoAsignadoGrua());

            DireccionDTO dirPatioInmovili = comparendoPatio.getDireccion();
            if (dirPatioInmovili != null) {
                procesaComparendo.setProcesaDireccionPatio(toProcesaDireccionDTO(dirPatioInmovili));
            }
        }

        // ********************************************************************************
        // ********************************* TESTIGO **************************************
        // ********************************************************************************
        ComparendoPersonaDTO testigo = detalleComparendo.getTestigo();
        ProcesaComparendoPersonaDTO procesaTestigo = new ProcesaComparendoPersonaDTO();
        if (testigo != null) {
            procesaTestigo = getProcesaComparendoPersonaDTO(testigo);
            procesaTestigo.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getValue());

            DireccionDTO dirTestigo = testigo.getDireccion();
            if (dirTestigo != null) {
                procesaTestigo.setProcesaDireccion(toProcesaDireccionDTO(dirTestigo));
            }

            procesaComparendo.getProcesaComparendoPersonas().add(procesaTestigo);
        }

        // ********************************************************************************
        // ********************************* EMBRIAGUEZ ***********************************
        // ********************************************************************************
        procesaComparendo
                .setNiegaPruebaAlcoholemia(detalleComparendo.getNiegaPruebaAlcoholemia() != null ? new Boolean(
                        detalleComparendo.getNiegaPruebaAlcoholemia()) : null);
        procesaComparendo.setGradoAlcoholemia(detalleComparendo.getGradoAlcoholemia() != null ? new Integer(
                detalleComparendo.getGradoAlcoholemia().getValor()) : null);
        procesaComparendo
                .setFechaPruebaAlcoholemia(detalleComparendo.getFechaPruebaAlcoholemia() != null ? new java.util.Date(
                        detalleComparendo.getFechaPruebaAlcoholemia().getTime()) : null);
        procesaComparendo
                .setNumeroPruebaAlcoholemia(detalleComparendo.getNumeroPruebaAlcoholemia() != null ? new String(
                        detalleComparendo.getNumeroPruebaAlcoholemia()) : null);
        procesaComparendo.setValorGradoAlcoholemia(detalleComparendo.getValorGradoAlcoholemia() != null ? new Integer(
                detalleComparendo.getValorGradoAlcoholemia()) : null);
        procesaComparendo.setNumeroReincidencia(detalleComparendo.getNumeroReincidencia() != null ? new Integer(
                detalleComparendo.getNumeroReincidencia()) : null);
        return procesaComparendo;
    }

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
                dto.setCodigoTipoUbicabilidad(direccionDTO.getTipoUbicabilidad().getCodigo());
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
     * Transforma un ComparendoPersonaDTO a un ProcesaComparendoPersona
     * 
     * @param comparendoPersona
     *            datos del comparendo persona
     * @return retorna una persona procesa comparendo con sus datos correspondientes.
     * @author luis.forero(mod 2016-01-29)
     */
    private ProcesaComparendoPersonaDTO getProcesaComparendoPersonaDTO(ComparendoPersonaDTO comparendoPersona) {
        ProcesaComparendoPersonaDTO procesaInfractor = new ProcesaComparendoPersonaDTO();
        procesaInfractor.setIdTipoIdentificacion(comparendoPersona.getTipoIdentificacion().getId());
        procesaInfractor.setNumeroIdentificacion(comparendoPersona.getNumeroIdentificacion());
        procesaInfractor.setNombre1(comparendoPersona.getNombre1());
        procesaInfractor.setNombre2(comparendoPersona.getNombre2());
        procesaInfractor.setApellido1(comparendoPersona.getApellido1());
        procesaInfractor.setApellido2(comparendoPersona.getApellido2());
        procesaInfractor.setRazonSocial(comparendoPersona.getRazonSocial());

        procesaInfractor.setEdad(comparendoPersona.getEdad());
        procesaInfractor.setEmail(comparendoPersona.getEmail());
        procesaInfractor.setTelefonoFijo(comparendoPersona.getTelefonoFijo());
        procesaInfractor.setTelefonoMovil(comparendoPersona.getTelefonoMovil());

        // Datos licencia del infractor
        procesaInfractor
                .setCodigoOrganismoLicencia(comparendoPersona.getOrganismoTransito() != null ? comparendoPersona
                        .getOrganismoTransito().getCodigoOrganismo() : null);
        procesaInfractor.setNumeroLicencia(comparendoPersona.getNumeroLicencia());
        procesaInfractor
                .setIdCategoriaLicenciaCondu(comparendoPersona.getCategoriaLicencia() != null ? comparendoPersona
                        .getCategoriaLicencia().getId() : null);
        procesaInfractor.setFechaExpedicionLicenCondu(comparendoPersona.getFechaExpedicionLicenCondu());
        procesaInfractor.setFechaVencimientoLicenCondu(comparendoPersona.getFechaVencimientoLicenCondu());
        return procesaInfractor;
    }
}
