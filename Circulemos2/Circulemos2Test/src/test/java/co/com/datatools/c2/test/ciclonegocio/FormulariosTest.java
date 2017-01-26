package co.com.datatools.c2.test.ciclonegocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.AsignacionDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.CantidadRangoDTO;
import co.com.datatools.c2.dto.formularios.CausalCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaSeguimientoFormularioDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoDTO;
import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.enumeraciones.EnumCausalCambioEstado;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.formularios.Num016Test.EnumTipoFormulario;

/**
 * 
 * @author divier.casas
 * 
 */
@RunWith(Arquillian.class)
public class FormulariosTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(FormulariosTest.class.getName());
    private static final Integer CODIGO_ORGANISMO = 11001;

    @EJB
    IRAdministracionFormularios administracionFormulariosEjb;

    @EJB
    IRFormulario formularioEJB;

    @EJB
    IRPersona personaEJB;

    /**
     * Método que permite ejecutar todo el ciclo de negocio de formularios
     */
    @Test
    public void ejecutarCicloFormulariosExitoso() {
        logger.debug("FormulariosTest::ejecutarCicloFormulariosExitoso()");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 1);
        final OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
        final TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
        tipoFormulario.setId(EnumTipoFormulario.COMPARENDERA_TRANSPORTE_PUBLICO.getId());
        final NumeracionFormularioDTO numeracionFormularioDTO = crearNumeracion(cal, organismoTransito, tipoFormulario);

        try {
            administracionFormulariosEjb.registrarNumeracionFormulario(numeracionFormularioDTO);
            Assert.assertTrue(true);
            UnificacionResponsableDTO unificacionResponsableDTO = new UnificacionResponsableDTO();
            TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
            ResponsableFormularioDTO responsableFormularioDTO = new ResponsableFormularioDTO();
            TipoResponsableFormularioDTO tipoResponsableFormularioDTO = new TipoResponsableFormularioDTO();

            tipoResponsableFormularioDTO.setEstado(true);
            tipoResponsableFormularioDTO.setCodigo(EnumTipoResponsableFormulario.ORGANISMO_TRANSITO.toString());
            tipoResponsableFormularioDTO.setId(1);

            responsableFormularioDTO.setCorreoResponsableFormulario("a@a.com");
            responsableFormularioDTO.setOrganismoTransito(organismoTransito);
            responsableFormularioDTO.setTipoResponsable(tipoResponsableFormularioDTO);
            responsableFormularioDTO.setFechaInicioVincula(new Date());
            cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, 1);
            responsableFormularioDTO.setFechaFinVincula(cal.getTime());

            tipoIdentificacionPersonaDTO.setCodigo(EnumTipoIdentificacion.CEDULA_DE_CIUDADANIA.toString());
            unificacionResponsableDTO.setOrganismoTransito(organismoTransito);

            responsableFormularioDTO.setUnificacionResponsableDTO(unificacionResponsableDTO);
            unificacionResponsableDTO.setResponsableFormulario(responsableFormularioDTO);

            Long salidaEvento = administracionFormulariosEjb.registrarResponsableFormularios(unificacionResponsableDTO);
            unificacionResponsableDTO.setId(salidaEvento);

            Assert.assertTrue(salidaEvento > 0);

            StockTipoResponsableDTO stockTipoResponsableDTO = new StockTipoResponsableDTO();
            stockTipoResponsableDTO.setCodigoOrganismo(organismoTransito);
            stockTipoResponsableDTO.setTipoFormulario(tipoFormulario);
            stockTipoResponsableDTO.setTipoResponsable(tipoResponsableFormularioDTO);
            stockTipoResponsableDTO.setStockMinimo(10);
            stockTipoResponsableDTO.setStockMaximo(20);

            formularioEJB.registrarStockTipoResponsable(stockTipoResponsableDTO);
            Assert.assertTrue(true);

            RelacionEstadosDTO relacionEstadosDTO = new RelacionEstadosDTO();
            relacionEstadosDTO.setOrganismoTranisto(organismoTransito);
            relacionEstadosDTO.setTipoFormulario(tipoFormulario);
            EstadoFormularioDTO estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getValue());
            relacionEstadosDTO.setEstadoFormularioActual(estadoFormularioDTO);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ASIGNADO.getValue());
            relacionEstadosDTO.setEstadoFormularioSiguiente(estadoFormularioDTO);

            salidaEvento = administracionFormulariosEjb.registrarRelacionEstados(relacionEstadosDTO);
            Assert.assertTrue(salidaEvento > 0);

            relacionEstadosDTO = new RelacionEstadosDTO();
            relacionEstadosDTO.setOrganismoTranisto(organismoTransito);
            relacionEstadosDTO.setTipoFormulario(tipoFormulario);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ASIGNADO.getValue());
            relacionEstadosDTO.setEstadoFormularioActual(estadoFormularioDTO);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ANULADO.getValue());
            relacionEstadosDTO.setEstadoFormularioSiguiente(estadoFormularioDTO);

            salidaEvento = administracionFormulariosEjb.registrarRelacionEstados(relacionEstadosDTO);
            Assert.assertTrue(salidaEvento > 0);

            relacionEstadosDTO = new RelacionEstadosDTO();
            relacionEstadosDTO.setOrganismoTranisto(organismoTransito);
            relacionEstadosDTO.setTipoFormulario(tipoFormulario);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ASIGNADO.getValue());
            relacionEstadosDTO.setEstadoFormularioActual(estadoFormularioDTO);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.DEVUELTO.getValue());
            relacionEstadosDTO.setEstadoFormularioSiguiente(estadoFormularioDTO);

            salidaEvento = administracionFormulariosEjb.registrarRelacionEstados(relacionEstadosDTO);
            Assert.assertTrue(salidaEvento > 0);

            relacionEstadosDTO = new RelacionEstadosDTO();
            relacionEstadosDTO.setOrganismoTranisto(organismoTransito);
            relacionEstadosDTO.setTipoFormulario(tipoFormulario);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.DEVUELTO.getValue());
            relacionEstadosDTO.setEstadoFormularioActual(estadoFormularioDTO);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ANULADO.getValue());
            relacionEstadosDTO.setEstadoFormularioSiguiente(estadoFormularioDTO);

            salidaEvento = administracionFormulariosEjb.registrarRelacionEstados(relacionEstadosDTO);
            Assert.assertTrue(salidaEvento > 0);

            StockTipoFormularioDTO stockTipoFormularioDTO = new StockTipoFormularioDTO();
            stockTipoFormularioDTO.setOrganismoTransitoDTO(organismoTransito);
            stockTipoFormularioDTO.setPorcentajeMaximoConsumo(50);
            stockTipoFormularioDTO.setTipoFormulario(tipoFormulario);
            administracionFormulariosEjb.registrarStockTipoFormulario(stockTipoFormularioDTO);
            Assert.assertTrue(true);

            RangoFormularioDTO rangoFormularioDTO = new RangoFormularioDTO();
            rangoFormularioDTO.setCodigoOrganismo(organismoTransito);
            rangoFormularioDTO.setFechaAutorizacion(new Date());
            numeracionFormularioDTO.setId(1);
            rangoFormularioDTO.setNumeracion(numeracionFormularioDTO);
            rangoFormularioDTO.setNumeroInicial("00");
            rangoFormularioDTO.setNumeroFinal("49");
            rangoFormularioDTO.setTipoFormulario(tipoFormulario);
            formularioEJB.registrarRangoFormulario(rangoFormularioDTO);
            Assert.assertTrue(true);

            AsignacionDTO asignacionDTO = new AsignacionDTO();
            DetalleCambioEstadoDTO detalleCambioEstadoDTO = new DetalleCambioEstadoDTO();
            CausalCambioEstadoDTO causalCambioEstadoDTO = new CausalCambioEstadoDTO();
            causalCambioEstadoDTO.setId(EnumCausalCambioEstado.RESOLUCION.getValue());
            detalleCambioEstadoDTO.setCausalCambioEstado(causalCambioEstadoDTO);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ASIGNADO.getValue());
            detalleCambioEstadoDTO.setEstadoFormulario(estadoFormularioDTO);
            detalleCambioEstadoDTO.setFechaAplicacionEstado(new Date());

            responsableFormularioDTO = administracionFormulariosEjb.consultarResponsableFormularios(
                    unificacionResponsableDTO).getResponsableFormulario();
            detalleCambioEstadoDTO.setResponsableFormulario(responsableFormularioDTO);
            asignacionDTO.setDetalleCambioEstado(detalleCambioEstadoDTO);

            List<CantidadRangoDTO> lCantidadRangoDTO = new ArrayList<CantidadRangoDTO>();
            CantidadRangoDTO cantidadRangoDTO = new CantidadRangoDTO();
            cantidadRangoDTO.setCantidad(50);
            cantidadRangoDTO.setIdRango(1);
            lCantidadRangoDTO.add(cantidadRangoDTO);
            asignacionDTO.setCantidadRango(lCantidadRangoDTO);
            Integer salidaEventoInt = formularioEJB.asignarFormularios(asignacionDTO);
            Assert.assertTrue(salidaEventoInt > 0);

            /* Devolucion Conjunto Formularios */

            CambioEstadoFormularioDTO cambioEstadoFormularioDTO = new CambioEstadoFormularioDTO();
            cambioEstadoFormularioDTO.setCodigoOrganismo(organismoTransito.getCodigoOrganismo());
            cambioEstadoFormularioDTO.setFechaAplicacionEstado(new Date());
            cambioEstadoFormularioDTO.setIdCausalCambioEstado(EnumCausalCambioEstado.RESOLUCION.getValue());
            cambioEstadoFormularioDTO.setIdDetalleCambioEstado(1L);
            cambioEstadoFormularioDTO.setIdEstadoFinal(EnumEstadoFomulario.DEVUELTO.getValue());
            cambioEstadoFormularioDTO.setNumeroDocumentoSoporte("123456");
            cambioEstadoFormularioDTO.setObservaciones("Prueba Devolucion");
            ConsultaRangoFormularioDTO consultaRangoFormularioDTO = new ConsultaRangoFormularioDTO();
            consultaRangoFormularioDTO.setIdRango(1L);
            List<RangoFormularioDTO> rangosF = formularioEJB.consultarRangoFormulario(consultaRangoFormularioDTO);
            cambioEstadoFormularioDTO.setListRangoDTO(new ArrayList<RangoDTO>());
            for (RangoFormularioDTO rangoFormulario : rangosF) {
                RangoDTO rango = new RangoDTO(rangoFormulario.getNumeroInicial(), rangoFormulario.getNumeroFinal());
                cambioEstadoFormularioDTO.getListRangoDTO().add(rango);
            }
            salidaEventoInt = formularioEJB.cambiarEstadoFormularios(cambioEstadoFormularioDTO);
            Assert.assertTrue(salidaEventoInt > 0);

            /* Devolucion Conjunto Formularios Previamente Devueltos */

            cambioEstadoFormularioDTO = new CambioEstadoFormularioDTO();
            cambioEstadoFormularioDTO.setCodigoOrganismo(organismoTransito.getCodigoOrganismo());
            cambioEstadoFormularioDTO.setFechaAplicacionEstado(new Date());
            cambioEstadoFormularioDTO.setIdCausalCambioEstado(EnumCausalCambioEstado.RESOLUCION.getValue());
            cambioEstadoFormularioDTO.setIdDetalleCambioEstado(2L);
            cambioEstadoFormularioDTO.setIdEstadoFinal(EnumEstadoFomulario.DEVUELTO.getValue());
            cambioEstadoFormularioDTO.setNumeroDocumentoSoporte("123456");
            cambioEstadoFormularioDTO.setObservaciones("Prueba Devolucion Formularios Ya Devueltos");
            consultaRangoFormularioDTO = new ConsultaRangoFormularioDTO();
            consultaRangoFormularioDTO.setIdRango(1L);
            rangosF.clear();
            rangosF = formularioEJB.consultarRangoFormulario(consultaRangoFormularioDTO);
            cambioEstadoFormularioDTO.setListRangoDTO(new ArrayList<RangoDTO>());
            for (RangoFormularioDTO rangoFormulario : rangosF) {
                RangoDTO rango = new RangoDTO(rangoFormulario.getNumeroInicial(), rangoFormulario.getNumeroFinal());
                cambioEstadoFormularioDTO.getListRangoDTO().add(rango);
            }
            salidaEventoInt = formularioEJB.cambiarEstadoFormularios(cambioEstadoFormularioDTO);
            Assert.assertTrue(salidaEventoInt == 0);

            /* Anulacion Conjunto Formularios */

            cambioEstadoFormularioDTO = new CambioEstadoFormularioDTO();
            cambioEstadoFormularioDTO.setCodigoOrganismo(organismoTransito.getCodigoOrganismo());
            cambioEstadoFormularioDTO.setFechaAplicacionEstado(new Date());
            cambioEstadoFormularioDTO.setIdCausalCambioEstado(EnumCausalCambioEstado.RESOLUCION.getValue());
            cambioEstadoFormularioDTO.setIdDetalleCambioEstado(1L);
            cambioEstadoFormularioDTO.setIdEstadoFinal(EnumEstadoFomulario.ANULADO.getValue());
            cambioEstadoFormularioDTO.setNumeroDocumentoSoporte("123456");
            cambioEstadoFormularioDTO.setObservaciones("Prueba Anulacion");
            consultaRangoFormularioDTO = new ConsultaRangoFormularioDTO();
            consultaRangoFormularioDTO.setIdRango(1L);
            rangosF.clear();
            rangosF = formularioEJB.consultarRangoFormulario(consultaRangoFormularioDTO);
            cambioEstadoFormularioDTO.setListRangoDTO(new ArrayList<RangoDTO>());
            for (RangoFormularioDTO rangoFormulario : rangosF) {
                RangoDTO rango = new RangoDTO(rangoFormulario.getNumeroInicial(), rangoFormulario.getNumeroFinal());
                cambioEstadoFormularioDTO.getListRangoDTO().add(rango);
            }
            salidaEventoInt = formularioEJB.cambiarEstadoFormularios(cambioEstadoFormularioDTO);
            Assert.assertTrue(salidaEventoInt > 0);

            /* Anulacion Conjunto Formularios Previamente Anulados */

            cambioEstadoFormularioDTO = new CambioEstadoFormularioDTO();
            cambioEstadoFormularioDTO.setCodigoOrganismo(organismoTransito.getCodigoOrganismo());
            cambioEstadoFormularioDTO.setFechaAplicacionEstado(new Date());
            cambioEstadoFormularioDTO.setIdCausalCambioEstado(EnumCausalCambioEstado.RESOLUCION.getValue());
            cambioEstadoFormularioDTO.setIdDetalleCambioEstado(2L);
            cambioEstadoFormularioDTO.setIdEstadoFinal(EnumEstadoFomulario.ANULADO.getValue());
            cambioEstadoFormularioDTO.setNumeroDocumentoSoporte("123456");
            cambioEstadoFormularioDTO.setObservaciones("Prueba Anulacion Formularios Ya Anulados");
            consultaRangoFormularioDTO = new ConsultaRangoFormularioDTO();
            consultaRangoFormularioDTO.setIdRango(1L);
            rangosF.clear();
            rangosF = formularioEJB.consultarRangoFormulario(consultaRangoFormularioDTO);
            cambioEstadoFormularioDTO.setListRangoDTO(new ArrayList<RangoDTO>());
            for (RangoFormularioDTO rangoFormulario : rangosF) {
                RangoDTO rango = new RangoDTO(rangoFormulario.getNumeroInicial(), rangoFormulario.getNumeroFinal());
                cambioEstadoFormularioDTO.getListRangoDTO().add(rango);
            }
            salidaEventoInt = formularioEJB.cambiarEstadoFormularios(cambioEstadoFormularioDTO);
            Assert.assertTrue(salidaEventoInt == 0);

            /* Consulta estados de formulario por asignación */

            List<UnificacionResponsableDTO> unificacionResponsable = administracionFormulariosEjb
                    .consultarResponsablesOrganismo(CODIGO_ORGANISMO, 3);// No hay ese tipo de formulario

            Assert.assertTrue(unificacionResponsable.size() == 1);

            EstadoFormularioAsignacionDTO estadoFormularioAsignacionDTO = formularioEJB
                    .consultarEstadosFormulariosAsignacion(3, unificacionResponsable.get(0).getId());

            Assert.assertTrue(estadoFormularioAsignacionDTO.getCifrasControl().size() == 4);

            /* Consultar seguimiento de formulario */

            ConsultaSeguimientoFormularioDTO consultaSeguimientoFormularioDTO = new ConsultaSeguimientoFormularioDTO();
            consultaSeguimientoFormularioDTO.setNumeroFormulario("04");
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(CODIGO_ORGANISMO);
            consultaSeguimientoFormularioDTO.setOrganismoTransito(organismoTransitoDTO);
            TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO();
            tipoFormularioDTO.setId(3);
            consultaSeguimientoFormularioDTO.setTipoFormularioDTO(tipoFormularioDTO);

            FormularioDTO formularioDTO = formularioEJB
                    .consultarSeguimientoFormulario(consultaSeguimientoFormularioDTO);

            Assert.assertTrue(formularioDTO.getLstDetalleCambiosEstados().size() >= 1);

            salidaEventoInt = ejecutarCicloFormulariosPreComparendos();
            Assert.assertTrue(salidaEventoInt > 0);

        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        } catch (CirculemosAlertaException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * crea una instancia de la numeracion del formulario y le asigna unos valores determiandos para probar.
     * 
     * @param cal
     *            fecha final vigencia
     * @param organismoTransito
     *            organismo de transito al que pertenece la numeracion
     * @param tipoFormulario
     *            tipo de formulario a crear
     * @return numeracion nueva a crear
     */
    private NumeracionFormularioDTO crearNumeracion(Calendar cal, OrganismoTransitoDTO organismoTransito,
            TipoFormularioDTO tipoFormulario) {
        NumeracionFormularioDTO numeracionFormularioDTO = new co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO();
        final List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();
        DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();
        detalle.setIncremental(true);
        detalle.setNumerico(true);
        detalle.setCaracterInicio('0');
        detalle.setCaracterFin('9');
        detalle.setDigito((long) 1);
        detalleNumeracion.add(detalle);
        detalle = new DetalleNumeracionDTO();
        detalle.setIncremental(true);
        detalle.setNumerico(true);
        detalle.setCaracterInicio('0');
        detalle.setCaracterFin('9');
        detalle.setDigito((long) 2);
        detalleNumeracion.add(detalle);
        numeracionFormularioDTO.setDetalleNumeracionList(detalleNumeracion);
        numeracionFormularioDTO.setDigitos(2);
        numeracionFormularioDTO.setFechaInicial(new Date());

        numeracionFormularioDTO.setFechaFinal(cal.getTime());
        organismoTransito.setCodigoOrganismo(CODIGO_ORGANISMO);

        numeracionFormularioDTO.setTipoFormulario(tipoFormulario);
        numeracionFormularioDTO.setActivo(true);
        return null;
    }

    /**
     * Método para dejar un conjunto de formularios asignados para ser utilizados para la generación de comparendos
     */
    private Integer ejecutarCicloFormulariosPreComparendos() {
        logger.debug("FormulariosTest::ejecutarCicloFormulariosPreComparendos()");
        Integer salidaEventoInt = 0;
        NumeracionFormularioDTO numeracionFormularioDTO = new co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO();
        List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();
        DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();
        detalle.setIncremental(true);
        detalle.setNumerico(true);
        detalle.setCaracterInicio('0');
        detalle.setCaracterFin('9');
        detalle.setDigito((long) 1);
        detalleNumeracion.add(detalle);
        detalle = new DetalleNumeracionDTO();
        detalle.setIncremental(true);
        detalle.setNumerico(true);
        detalle.setCaracterInicio('0');
        detalle.setCaracterFin('9');
        detalle.setDigito((long) 2);
        detalleNumeracion.add(detalle);
        numeracionFormularioDTO.setDetalleNumeracionList(detalleNumeracion);
        numeracionFormularioDTO.setDigitos(2);
        numeracionFormularioDTO.setFechaInicial(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 1);
        numeracionFormularioDTO.setFechaFinal(cal.getTime());
        OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
        organismoTransito.setCodigoOrganismo(CODIGO_ORGANISMO);
        TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
        tipoFormulario.setId(EnumTipoFormulario.COMPARENDERA_MANUAL_NACIONAL.getId());
        numeracionFormularioDTO.setTipoFormulario(tipoFormulario);
        numeracionFormularioDTO.setActivo(true);

        try {
            administracionFormulariosEjb.registrarNumeracionFormulario(numeracionFormularioDTO);
            Assert.assertTrue(true);
            UnificacionResponsableDTO unificacionResponsableDTO = new UnificacionResponsableDTO();
            TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
            ResponsableFormularioDTO responsableFormularioDTO = new ResponsableFormularioDTO();
            TipoResponsableFormularioDTO tipoResponsableFormularioDTO = new TipoResponsableFormularioDTO();

            tipoResponsableFormularioDTO.setEstado(true);
            tipoResponsableFormularioDTO.setCodigo(EnumTipoResponsableFormulario.EMPRESA.toString());
            tipoResponsableFormularioDTO.setId(2);

            responsableFormularioDTO.setCorreoResponsableFormulario("b@b.com");
            responsableFormularioDTO.setTipoResponsable(tipoResponsableFormularioDTO);
            responsableFormularioDTO.setOrganismoTransito(organismoTransito);
            responsableFormularioDTO.setFechaInicioVincula(new Date());
            cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, 1);
            responsableFormularioDTO.setFechaFinVincula(cal.getTime());

            tipoIdentificacionPersonaDTO.setId(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor());

            PersonaDTO personaDTO = new PersonaDTO();
            personaDTO.setOrganismoTransito(organismoTransito);
            personaDTO.setTipoIdentificacion(tipoIdentificacionPersonaDTO);
            personaDTO.setNumeroIdentificacion("99999999");
            TipoFuenteInformacionDTO fuenteInformacionDTO = new TipoFuenteInformacionDTO();
            fuenteInformacionDTO.setId(EnumTipoFuenteInformacion.ACTUALIZACION_POR_SOLICITUD.getValue());
            personaDTO.setFuenteInformacion(fuenteInformacionDTO);
            personaDTO.setFechaUltimaActualizacion(new Date());
            personaDTO.setNombre1("LOBO");
            personaDTO.setApellido1("ZAGALO");
            personaDTO.setId(personaEJB.registrarPersona(personaDTO));

            unificacionResponsableDTO.setPersona(personaDTO);
            responsableFormularioDTO.setUnificacionResponsableDTO(unificacionResponsableDTO);
            unificacionResponsableDTO.setResponsableFormulario(responsableFormularioDTO);

            Long salidaEvento = administracionFormulariosEjb.registrarResponsableFormularios(unificacionResponsableDTO);
            Assert.assertTrue(salidaEvento > 0);

            StockTipoResponsableDTO stockTipoResponsableDTO = new StockTipoResponsableDTO();
            stockTipoResponsableDTO.setCodigoOrganismo(organismoTransito);
            stockTipoResponsableDTO.setTipoFormulario(tipoFormulario);
            stockTipoResponsableDTO.setTipoResponsable(tipoResponsableFormularioDTO);
            stockTipoResponsableDTO.setStockMinimo(10);
            stockTipoResponsableDTO.setStockMaximo(20);

            formularioEJB.registrarStockTipoResponsable(stockTipoResponsableDTO);
            Assert.assertTrue(true);

            RelacionEstadosDTO relacionEstadosDTO = new RelacionEstadosDTO();
            relacionEstadosDTO.setOrganismoTranisto(organismoTransito);
            relacionEstadosDTO.setTipoFormulario(tipoFormulario);
            EstadoFormularioDTO estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getValue());
            relacionEstadosDTO.setEstadoFormularioActual(estadoFormularioDTO);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ASIGNADO.getValue());
            relacionEstadosDTO.setEstadoFormularioSiguiente(estadoFormularioDTO);

            salidaEvento = administracionFormulariosEjb.registrarRelacionEstados(relacionEstadosDTO);
            Assert.assertTrue(salidaEvento > 0);

            StockTipoFormularioDTO stockTipoFormularioDTO = new StockTipoFormularioDTO();
            stockTipoFormularioDTO.setOrganismoTransitoDTO(organismoTransito);
            stockTipoFormularioDTO.setPorcentajeMaximoConsumo(50);
            stockTipoFormularioDTO.setTipoFormulario(tipoFormulario);
            administracionFormulariosEjb.registrarStockTipoFormulario(stockTipoFormularioDTO);
            Assert.assertTrue(true);

            RangoFormularioDTO rangoFormularioDTO = new RangoFormularioDTO();
            rangoFormularioDTO.setCodigoOrganismo(organismoTransito);
            rangoFormularioDTO.setFechaAutorizacion(new Date());
            numeracionFormularioDTO.setId(1);
            rangoFormularioDTO.setNumeracion(numeracionFormularioDTO);
            rangoFormularioDTO.setNumeroInicial("00");
            rangoFormularioDTO.setNumeroFinal("99");
            rangoFormularioDTO.setTipoFormulario(tipoFormulario);
            formularioEJB.registrarRangoFormulario(rangoFormularioDTO);
            Assert.assertTrue(true);

            AsignacionDTO asignacionDTO = new AsignacionDTO();
            DetalleCambioEstadoDTO detalleCambioEstadoDTO = new DetalleCambioEstadoDTO();
            CausalCambioEstadoDTO causalCambioEstadoDTO = new CausalCambioEstadoDTO();
            causalCambioEstadoDTO.setId(EnumCausalCambioEstado.RESOLUCION.getValue());
            detalleCambioEstadoDTO.setCausalCambioEstado(causalCambioEstadoDTO);
            estadoFormularioDTO = new EstadoFormularioDTO();
            estadoFormularioDTO.setId(EnumEstadoFomulario.ASIGNADO.getValue());
            detalleCambioEstadoDTO.setEstadoFormulario(estadoFormularioDTO);
            detalleCambioEstadoDTO.setFechaAplicacionEstado(new Date());
            unificacionResponsableDTO.setId(2L);
            responsableFormularioDTO = administracionFormulariosEjb.consultarResponsableFormularios(
                    unificacionResponsableDTO).getResponsableFormulario();
            detalleCambioEstadoDTO.setResponsableFormulario(responsableFormularioDTO);
            asignacionDTO.setDetalleCambioEstado(detalleCambioEstadoDTO);

            List<CantidadRangoDTO> lCantidadRangoDTO = new ArrayList<CantidadRangoDTO>();
            CantidadRangoDTO cantidadRangoDTO = new CantidadRangoDTO();
            cantidadRangoDTO.setCantidad(50);
            cantidadRangoDTO.setIdRango(2);
            lCantidadRangoDTO.add(cantidadRangoDTO);
            asignacionDTO.setCantidadRango(lCantidadRangoDTO);
            salidaEventoInt = formularioEJB.asignarFormularios(asignacionDTO);
        } catch (CirculemosNegocioException e) {
            Assert.fail(e.getMessage());
        } catch (CirculemosAlertaException e) {
            Assert.fail(e.getMessage());
        }
        return salidaEventoInt;
    }
}