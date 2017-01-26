package co.com.datatools.c2.test.formularios;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.AsignacionDTO;
import co.com.datatools.c2.dto.formularios.CantidadRangoDTO;
import co.com.datatools.c2.dto.formularios.CausalCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DocumentoFormularioDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoDisponibleDTO;
import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.ejb.aud.UsuarioSesionEJB;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.formularios.Num016Test.EnumTipoFormulario;

/**
 * @author rodrigo.cruz
 */
@RunWith(Arquillian.class)
public class Num001AsignacionTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(Num001AsignacionTest.class.getName());
    private static final String[] scripts = { "scripts/pruebas/formularios/NUM_001001.sql",
            "scripts/pruebas/formularios/NUM_001002.sql", };

    private OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO(11001);

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    @EJB
    private IRAdministracionFormularios administracionFormulariosEJB;
    @EJB
    private IRFormulario formulariosEjb;
    @EJB
    private UsuarioSesionEJB usuarioSesionEJB;

    @Test
    public void consultarResponsableFormulariosTest001() {
        logger.debug("consultarResponsableFormulariosTest001");

        UnificacionResponsableDTO unificacionResponsable = new UnificacionResponsableDTO();
        unificacionResponsable.setOrganismoTransito(new OrganismoTransitoDTO(11001));
        unificacionResponsable.setResponsableFormulario(new ResponsableFormularioDTO(-1L));
        unificacionResponsable.getResponsableFormulario().setTipoResponsable(new TipoResponsableFormularioDTO(1));

        UnificacionResponsableDTO unificacionResponsableDTO = null;
        try {
            unificacionResponsableDTO = administracionFormulariosEJB
                    .consultarResponsableFormularios(unificacionResponsable);
        } catch (CirculemosNegocioException e) {
            logger.error("ERROR - Num001Test::consultarResponsableFormulariosTest001");
            fail("ERROR - Num001Test::consultarResponsableFormulariosTest001");
        }

        Assert.assertNotNull(unificacionResponsableDTO);

        logger.debugv("Organismo de tránsito [ nombreOrganismo:{0}, codigoOrganismo:{1} ]", unificacionResponsableDTO
                .getOrganismoTransito().getNombreOrganismo(), unificacionResponsableDTO.getOrganismoTransito()
                .getCodigoOrganismo());
    }

    @Test
    public void consultarResponsableFormulariosTest002() {
        logger.debug("consultarResponsableFormulariosTest002");

        UnificacionResponsableDTO unificacionResponsable = new UnificacionResponsableDTO();
        unificacionResponsable.setPersona(new PersonaDTO());
        unificacionResponsable.getPersona().setNumeroIdentificacion("10000000000");
        unificacionResponsable.getPersona().setTipoIdentificacion(new TipoIdentificacionPersonaDTO(1));
        unificacionResponsable.setResponsableFormulario(new ResponsableFormularioDTO(-1L));
        unificacionResponsable.getResponsableFormulario().setTipoResponsable(new TipoResponsableFormularioDTO(1));

        UnificacionResponsableDTO unificacionResponsableDTO = null;
        try {
            unificacionResponsableDTO = administracionFormulariosEJB
                    .consultarResponsableFormularios(unificacionResponsable);
        } catch (CirculemosNegocioException e) {
            logger.error("ERROR - Num001Test::consultarResponsableFormulariosTest002");
            fail("ERROR - Num001Test::consultarResponsableFormulariosTest002");
        }

        Assert.assertNotNull(unificacionResponsableDTO);

        if (unificacionResponsableDTO.getPersona() instanceof PersonaJuridicaDTO) {
            PersonaJuridicaDTO personaJuridica = (PersonaJuridicaDTO) unificacionResponsableDTO.getPersona();
            logger.debugv("Persona jurídica [ nombreComercial:{0}, numeroIdentificacion:{1} ]",
                    personaJuridica.getNombreComercial(), personaJuridica.getNumeroIdentificacion());
        }
    }

    @Test
    public void consultarStockTipoResponsableTest001() {
        TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO(
                EnumTipoFormulario.INFORME_DE_ACCIDENTALIDAD.getId());
        tipoFormularioDTO.setActivo(true);

        StockTipoResponsableDTO consultaStockDTO = new StockTipoResponsableDTO();
        consultaStockDTO.setCodigoOrganismo(organismoTransito);
        consultaStockDTO.setEstadoStock(true);
        consultaStockDTO.setTipoFormulario(tipoFormularioDTO);
        consultaStockDTO.setTipoResponsable(new TipoResponsableFormularioDTO(
                EnumTipoResponsableFormulario.ORGANISMO_TRANSITO.getValue()));

        List<StockTipoResponsableDTO> stockTipoRespDTOList = formulariosEjb
                .consultarStockTipoResponsable(consultaStockDTO);

        Assert.assertNotNull(stockTipoRespDTOList);
    }

    @Test
    public void consultarCantidadFormulariosTest001() {
        ResponsableFormularioDTO responsableFormularioDTO = new ResponsableFormularioDTO();
        responsableFormularioDTO.setId(2L);
        responsableFormularioDTO.setOrganismoTransito(organismoTransito);
        responsableFormularioDTO.setTipoResponsable(new TipoResponsableFormularioDTO(
                EnumTipoResponsableFormulario.EMPRESA.getValue()));

        TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO(
                EnumTipoFormulario.COMPARENDERA_MANUAL_NACIONAL.getId());
        tipoFormularioDTO.setActivo(true);

        FormularioDTO consultaFormDTO = new FormularioDTO();
        consultaFormDTO.setCodigoOrganismo(organismoTransito);
        consultaFormDTO.setEstadoFormulario(new EstadoFormularioDTO(EnumEstadoFomulario.ASIGNADO.getValue()));
        consultaFormDTO.setResponsableFormulario(responsableFormularioDTO);
        consultaFormDTO.setTipoFormulario(tipoFormularioDTO);

        int cantidadFormularios = formulariosEjb.consultarCantidadFormularios(consultaFormDTO);

        Assert.assertNotNull(cantidadFormularios);
    }

    @Test
    public void asignarFormulariosTest001() {
        List<RangoDisponibleDTO> rangosDisponiblesList = new ArrayList<>();

        TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO(
                EnumTipoFormulario.INFORME_DE_ACCIDENTALIDAD.getId());
        tipoFormularioDTO.setActivo(true);

        RangoFormularioDTO consultaRangoDTO = new RangoFormularioDTO();
        consultaRangoDTO.setCodigoOrganismo(organismoTransito);
        consultaRangoDTO.setTipoFormulario(tipoFormularioDTO);

        List<RangoFormularioDTO> rangoFormularioDTOList = null;
        try {
            rangoFormularioDTOList = formulariosEjb.consultarRangosFormularioDisponibles(consultaRangoDTO);

            for (RangoFormularioDTO rango : rangoFormularioDTOList) {
                RangoDisponibleDTO rangoDisponibleDTO = new RangoDisponibleDTO();
                rangoDisponibleDTO.setId(rango.getId());
                rangoDisponibleDTO.setNumeroInicial(rango.getNumeroInicial());
                rangoDisponibleDTO.setNumeroFinal(rango.getNumeroFinal());
                rangoDisponibleDTO.setCantidadTotal(rango.getCantidadTotal());
                rangoDisponibleDTO.setCantidadDisponible(rango.getCantidadDisponible());
                rangosDisponiblesList.add(rangoDisponibleDTO);
            }
        } catch (CirculemosNegocioException e) {
            logger.error("ERROR - Num001Test::asignarFormulariosTest001");
            fail("ERROR - Num001Test::asignarFormulariosTest001");
        }

        Assert.assertFalse(rangoFormularioDTOList.isEmpty());

        AsignacionDTO asignacionDTO = new AsignacionDTO();
        asignacionDTO.setCantidadRango(new ArrayList<CantidadRangoDTO>());
        asignacionDTO.setDetalleCambioEstado(new DetalleCambioEstadoDTO());

        rangosDisponiblesList.get(0).setCantidadIngresada(5);

        for (RangoDisponibleDTO rangoDisponibleDTO : rangosDisponiblesList)
            if (rangoDisponibleDTO.getCantidadIngresada() != null) {
                if (rangoDisponibleDTO.getCantidadIngresada() > rangoDisponibleDTO.getCantidadDisponible()) {
                    Assert.assertFalse(rangoFormularioDTOList.isEmpty());
                }

                CantidadRangoDTO cantidadRangoDTO = new CantidadRangoDTO();
                cantidadRangoDTO.setCantidad(rangoDisponibleDTO.getCantidadIngresada());
                cantidadRangoDTO.setIdRango(rangoDisponibleDTO.getId());
                asignacionDTO.getCantidadRango().add(cantidadRangoDTO);
            }

        CausalCambioEstadoDTO causaCambioEstDTO = new CausalCambioEstadoDTO(1);
        DocumentoFormularioDTO documentoFormularioDTO = new DocumentoFormularioDTO();
        documentoFormularioDTO.setNumeroDocumento("1");
        EstadoFormularioDTO estadoFormularDTO = new EstadoFormularioDTO(EnumEstadoFomulario.ASIGNADO.getValue());
        ResponsableFormularioDTO respFormulaDTO = new ResponsableFormularioDTO(-2L);
        TipoResponsableFormularioDTO tiReForDTO = new TipoResponsableFormularioDTO(
                EnumTipoResponsableFormulario.EMPRESA.getValue());
        respFormulaDTO.setTipoResponsable(tiReForDTO);
        respFormulaDTO.setCorreoResponsableFormulario("rodrigo.cruz@datatools.com.co");

        asignacionDTO.getDetalleCambioEstado().setCausalCambioEstado(causaCambioEstDTO);
        asignacionDTO.getDetalleCambioEstado().setDocumentoFormulario(documentoFormularioDTO);
        asignacionDTO.getDetalleCambioEstado().setEstadoFormulario(estadoFormularDTO);
        asignacionDTO.getDetalleCambioEstado().setFechaAplicacionEstado(Calendar.getInstance().getTime());
        asignacionDTO.getDetalleCambioEstado().setFolio("1");
        asignacionDTO.getDetalleCambioEstado().setObservaciones("UT");
        asignacionDTO.getDetalleCambioEstado().setResponsableFormulario(respFormulaDTO);

        try {
            usuarioSesionEJB.almacenarUsuario("pruebas-unitarias", "localhost");
            int cantidadTotalAsignada = formulariosEjb.asignarFormularios(asignacionDTO);

            Assert.assertNotNull(cantidadTotalAsignada);
        } catch (CirculemosAlertaException e) {
            logger.error("ERROR - Num001Test::asignarFormulariosTest001");
            fail("ERROR - Adm009Test::asignarFormulariosTest001");
        }

    }

}
