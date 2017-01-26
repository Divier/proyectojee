package co.com.datatools.c2.negocio.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.DetallePagoDTO;
import co.com.datatools.c2.dto.DetallePagoInconsistenciaDTO;
import co.com.datatools.c2.dto.ErrorInconsistenciaPagoDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.ObligacionCoactivoDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.PagoInconsistenciaDTO;
import co.com.datatools.c2.dto.RecaudoRechazoDTO;
import co.com.datatools.c2.dto.RespuestaConciliarPagoDTO;
import co.com.datatools.c2.dto.RespuestaHomologarCatalogoRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaProcesarRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaReplicarPagoDTO;
import co.com.datatools.c2.dto.TipoRechazoRecaudoDTO;
import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.EstadoObligacionDTO;
import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoObligacionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.entidades.DetalleFinanciacion;
import co.com.datatools.c2.entidades.DetallePago;
import co.com.datatools.c2.entidades.DetallePagoConciliacionError;
import co.com.datatools.c2.entidades.ErrorConciliacionPago;
import co.com.datatools.c2.entidades.ErrorInconsistenciaPago;
import co.com.datatools.c2.entidades.EstadoPago;
import co.com.datatools.c2.entidades.Pago;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumEstadoPago;
import co.com.datatools.c2.enumeracion.EnumEstadoTransaccion;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumErrorConciliacionPago;
import co.com.datatools.c2.enumeraciones.EnumErrorInconsistenciaPago;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoRechazoRecaudo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.ErrorRecaudo;
import co.com.datatools.c2.negocio.error.ErrorRecaudo.ValidarReglaNegocioRecaudo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaCartera;
import co.com.datatools.c2.negocio.fachada.IRFachadaCoactivo;
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaFinanciacion;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.helper.extencion.PagoHelperExtend;
import co.com.datatools.c2.negocio.helpers.DetalleFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.DetallePagoHelper;
import co.com.datatools.c2.negocio.helpers.PagoHelper;
import co.com.datatools.c2.negocio.helpers.ReplicarRecaudoTercerosHelper;
import co.com.datatools.c2.negocio.interfaces.ILInconsistenciaRecaudo;
import co.com.datatools.c2.negocio.interfaces.ILRecaudo;
import co.com.datatools.c2.negocio.interfaces.ILRechazosRecaudo;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRRecaudo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.util.RecaudoUtil;
import co.com.datatools.c2.util.log.LogReplicarPago;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.c2.dto.CoactivoDTO;

@Stateless(mappedName = "RecaudoEJB")
@LocalBean
public class RecaudoEJB implements IRRecaudo, ILRecaudo {

    private final static Logger logger = Logger.getLogger(RecaudoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILInconsistenciaRecaudo iInconsistenciaRecaudo;
    @EJB
    private IRFachadaProceso iRFachadaProceso;
    @EJB
    private IRFachadaCartera iRFachadaCartera;
    @EJB
    private IRFachadaFinanciacion iFachadaFinanciacion;
    @EJB
    private IRSeguridadCirculemos usuarioAppEJB;
    @EJB
    private IRFachadaAdminNegocio iRFachadaAdminNegocio;
    @EJB
    private IRFachadaComparendo iRFachadaComparendo;
    @EJB
    private IRFachadaIntegracionTerceros iRFachadaIntegracionTerceros;
    @EJB
    private IRFachadaConfiguracion fachadaConfiguracionEJB;
    @EJB
    private IRFachadaAdminGeneral iFachadaAdminGeneral;
    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;
    @EJB
    private ILRecaudo recaudoEJB;
    @EJB
    private ILRechazosRecaudo rechazosRecaudoEJB;
    @EJB
    private IRFachadaCoactivo iRFachadaCoactivo;

    private static final ILog loggerReplicar = LoggerC2.getLogger(EnumLogSistema.REPLICAR_PAGO_TERCEROS);

    private static final String VALOR_NO_MAPEADO = "VALOR NO MAPEADO :";

    private static final int UNO = 1, CERO = 0;

    private static final int ESCALA_DECIMAL = 2;

    private static final int LONGITUD_NUMERO_CUENTA = 15;

    private static final int LONGITUD_NUMERO_OBLIGACION = 20;

    private static final int VALOR_OBLIGACION_PRECISION_NUMERICA = 10;

    private static final int LONGITUD_NUMERO_IDENTIFICACION = 15;

    private static final int LONGITUD_NUMERO_RECAUDO = 20;

    private static final int TOTAL_CHEQUE_PRECISION_NUMERICA = 10;

    private static final int TOTAL_EFECTIVO_PRECISION_NUMERICA = 10;

    private static final int TOTAL_RECAUDO_PRECISION_NUMERICA = 10;

    @Override
    public RespuestaProcesarRecaudoDTO procesarRecaudo(PagoDTO pagoDTO) throws CirculemosNegocioException {
        logger.debug("RecaudoEJB.procesarRecaudo(PagoDTO)");

        RespuestaProcesarRecaudoDTO respuesta = new RespuestaProcesarRecaudoDTO();
        respuesta.setErrores(new ArrayList<ErrorInconsistenciaPagoDTO>());

        // Valida las reglas de negocio
        List<PagoInconsistenciaDTO> inconsistencias = validarReglasRecaudo(pagoDTO);
        if (inconsistencias.isEmpty()) {
            // Valida las reglas de la conciliacion
            validarConciliacionRecaudo(pagoDTO);
            respuesta.setEstadoTransaccion(EnumEstadoTransaccion.REGISTRADO);
        } else {
            // Convierte las inconsistencias

            respuesta.setEstadoTransaccion(EnumEstadoTransaccion.INCONSISTENTE);
            for (PagoInconsistenciaDTO pago : inconsistencias) {
                respuesta.getErrores().addAll(pago.getErrorInconsistenciaPagos());
                if (!pago.getDetallePagoInconsistencias().isEmpty()) {
                    for (DetallePagoInconsistenciaDTO detalle : pago.getDetallePagoInconsistencias()) {
                        respuesta.getErrores().addAll(detalle.getErrorInconsistenciaPagos());
                    }
                }

            }

            // Elimina duplicados
            Set<ErrorInconsistenciaPagoDTO> linkedHashSet = new LinkedHashSet<ErrorInconsistenciaPagoDTO>();
            linkedHashSet.addAll(respuesta.getErrores());
            respuesta.getErrores().clear();
            respuesta.getErrores().addAll(linkedHashSet);
        }

        return respuesta;
    }

    /**
     * Realiza las validaciones de conciliacion. Aplica para pagos nuevos con un solo detalle de pago y pagos registrados con detalles de pago no
     * conciliados. <br>
     * 
     * CASOS:
     * <ul>
     * <li>1. Pago nuevo con detalle de pago nuevo (registrar pago y detalle de pago)
     * <li>2. Pago existente con detalle de pago nuevo (registrar detalle de pago)
     * <li>3. Pago existente con detalle de pago existente (procesar detalle de pago)
     * </ul>
     * 
     * @param pago
     *            El pago con reglas de negocio y forma validadas
     * @author rodrigo.cruz - mod julio.pinzon(2016-07-26)
     * @throws CirculemosNegocioException
     */
    private void validarConciliacionRecaudo(PagoDTO pagoDTO) throws CirculemosNegocioException {
        logger.debug("RecaudoEJB.validarConciliacionRecaudo(PagoDTO)");

        Pago pago = null;

        // Fecha actual
        Date ahora = UtilFecha.buildCalendar().getTime();

        // CASOS:
        // 1. Pago nuevo con detalle de pago nuevo (registrar pago y detalle de pago)
        // 2. Pago existente con detalle de pago nuevo (registrar detalle de pago)
        // 3. Pago existente con detalle de pago existente (procesar detalle de pago)

        PagoDTO pagoConsultaDTO = null;

        if (pagoDTO.getId() != null) {
            pagoConsultaDTO = consultarPago(pagoDTO.getId());
        } else {
            PagoDTO pagoTemp = new PagoDTO();
            pagoTemp.setNumeroPago(pagoDTO.getNumeroPago());
            pagoTemp.setFechaTransaccion(pagoDTO.getFechaTransaccion());
            List<PagoDTO> pagosConsulta = consultarPagos(pagoTemp, null);
            if (pagosConsulta != null && !pagosConsulta.isEmpty()) {
                pagoConsultaDTO = pagosConsulta.get(0);
            }
        }

        if (pagoConsultaDTO != null) {

            // Un pago existente siempre tiene detalles de pago
            List<DetallePagoDTO> detallesPagoConsultaDTO = pagoConsultaDTO.getDetallesPago();

            // Validar si detalles de pago existen
            for (DetallePagoDTO detallePagoDTO : pagoDTO.getDetallesPago()) {
                boolean existeDetalle = false;

                for (DetallePagoDTO detallePagoConsultaDTO : detallesPagoConsultaDTO) {
                    if (detallePagoConsultaDTO.equals(detallePagoDTO)) {
                        existeDetalle = true;
                        break;
                    }
                }

                if (!existeDetalle) {
                    detallesPagoConsultaDTO.add(detallePagoDTO); // CASO 2
                }
            }
            pagoConsultaDTO.setDetallesPago(detallesPagoConsultaDTO);
            pagoDTO = pagoConsultaDTO;
        } else {
            pago = PagoHelper.toLevel1Entity(pagoDTO, null); // CASO 1

            // Registrar pago
            GenericDao<Pago> pagoDAO = new GenericDao<>(Pago.class, em);
            pago.setIdUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getId());
            pago.setFechaRegistro(ahora);
            pagoDAO.saveOrUpdate(pago);
            pagoDTO.setId(pago.getId());
        }
        // Conciliacion del recaudo
        conciliarRecaudo(pagoDTO);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public int conciliarRecaudo(PagoDTO pagoDTO) throws CirculemosNegocioException {
        logger.debug("RecaudoEJB.conciliarRecaudo(Pago)");

        // Pago
        Pago pago = PagoHelperExtend.toLevel2Entity(pagoDTO, null);

        // Fecha actual
        Date ahora = UtilFecha.buildCalendar().getTime();
        int pagosRealizados = 0;
        ValorParametroDTO valorParametro = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.MAX_INTENTOS_CONCILIAR_PAGO, pago.getOrganismoTransito().getCodigoOrganismo(), true);
        int numeroMaximoIntentos = valorParametro.getValorParamInt();

        for (DetallePago detallePago : pago.getDetallesPago()) {
            detallePago.setDetallePagoConciliacionErrores(new ArrayList<DetallePagoConciliacionError>());
            if (detallePago.getEstadoPago() == null || (detallePago.getEstadoPago() != null
                    && detallePago.getEstadoPago().getId().equals(EnumEstadoPago.PAGO_NO_CONCILIADO.getValue()))) {

                DetalleFinanciacionDTO detalleFinanciacionPago = null;
                detallePago.setPago(pago);

                // Numero de intentos de conciliacion antes de notificar a organismo de transito
                if (detallePago.getNumeroIntentos() == null) {
                    detallePago.setNumeroIntentos(CERO);
                }

                // Consulta la cartera por tipo (comparendo o financiacion) y numero de obligacion
                // Trae el saldo mas reciente en el primer registro
                CarteraDTO carteraDTO = new CarteraDTO();
                carteraDTO.setNombre(detallePago.getNumeroObligacion());
                carteraDTO.setTipoObligacion(new TipoObligacionDTO(detallePago.getIdTipoRecaudo()));
                carteraDTO.setEstadoObligacion(new EstadoObligacionDTO(EnumEstadoObligacion.ACTIVO.getValue()));
                carteraDTO = iRFachadaCartera.consultarCartera(carteraDTO);

                if (carteraDTO != null && carteraDTO.getSaldoCapital().equals(BigDecimal.ZERO)) {
                    // Si la cartera esta en cero no se registra el detalle y continua con el siguiente
                    continue;
                }

                MovimientoCarteraDTO movimientoCarteraDTO = new MovimientoCarteraDTO();
                movimientoCarteraDTO.setMovimientosCartera(new MovimientosCarteraDTO());
                movimientoCarteraDTO.getMovimientosCartera().setCartera(carteraDTO);
                movimientoCarteraDTO.getMovimientosCartera().setFechaCreacion(UtilFecha.buildCalendar().getTime());
                movimientoCarteraDTO.getMovimientosCartera().setFechaMovimiento(pago.getFechaTransaccion());
                movimientoCarteraDTO.setIdActividad(EnumActividadCartera.PAGO_OBLIGACION.getValue());
                movimientoCarteraDTO.getMovimientosCartera()
                        .setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());
                movimientoCarteraDTO.setSaldoCapital(BigDecimal.ZERO);
                movimientoCarteraDTO.setSaldoIntereses(BigDecimal.ZERO);
                movimientoCarteraDTO.getMovimientosCartera().setValorMovimiento(detallePago.getValorObligacion());

                if (detallePago.getIdTipoRecaudo().equals(EnumTipoObligacion.COMPARENDO.getValue())) {
                    // Asigna el concepto del comparendo
                    detallePago.setIdConceptoCartera(EnumConceptoCartera.COMPARENDO_PAGADO.getId());

                    // Concilia el recaudo de obligacion de comparendo
                    conciliarComparendo(detallePago, movimientoCarteraDTO);
                } else if (detallePago.getIdTipoRecaudo().equals(EnumTipoObligacion.FINANCIACION.getValue())) {
                    // Asigna el concepto de la financiacion
                    detallePago.setIdConceptoCartera(EnumConceptoCartera.CUOTA_FINANCIACION_PAGADA.getId());

                    // Concilia el recaudo de obligacion de financiacion
                    detalleFinanciacionPago = conciliarFinanciacion(detallePago, movimientoCarteraDTO);
                } else if (detallePago.getIdTipoRecaudo().equals(EnumTipoObligacion.COACTIVO.getValue())) {
                    detallePago.setIdConceptoCartera(EnumConceptoCartera.COMPARENDO_PAGADO.getId());
                    conciliarCoactivo(detallePago, movimientoCarteraDTO);
                }

                // Validar si hubo errores de conciliacion
                detallePago.setEstadoPago(new EstadoPago());

                if (!detallePago.getDetallePagoConciliacionErrores().isEmpty()) {
                    detallePago.getEstadoPago().setId(EnumEstadoPago.PAGO_NO_CONCILIADO.getValue());
                    detallePago.setNumeroIntentos(detallePago.getNumeroIntentos() + UNO);

                    if (detallePago.getNumeroIntentos().intValue() > numeroMaximoIntentos)
                        detallePago.getEstadoPago().setId(EnumEstadoPago.PARA_NOTIFICAR.getValue());
                } else {
                    detallePago.getEstadoPago().setId(EnumEstadoPago.PAGO_APLICADO.getValue());
                    detallePago.setFechaAplicacionPago(UtilFecha.buildCalendar().getTime());

                    // Si es un comparendo, se realizó el pago completo y pasa a estado pagado la obligacion
                    if (detallePago.getIdTipoRecaudo().equals(EnumTipoObligacion.COMPARENDO.getValue())) {
                        movimientoCarteraDTO.getMovimientosCartera().getCartera()
                                .setEstadoObligacion(new EstadoObligacionDTO(EnumEstadoObligacion.PAGADO.getValue()));
                        movimientoCarteraDTO.getMovimientosCartera().getCartera().getEstadoObligacion()
                                .setCodigo(EnumEstadoObligacion.PAGADO.getCodigo());
                    } else if (detallePago.getIdTipoRecaudo().equals(EnumTipoObligacion.FINANCIACION.getValue())) {
                        if (detalleFinanciacionPago != null) {
                            // Pone en pagado la cuota
                            DetalleFinanciacion detalle = DetalleFinanciacionHelper
                                    .toLevel1Entity(detalleFinanciacionPago, null);
                            detalle.setFechaPago(pago.getFechaTransaccion());
                            em.merge(detalle);
                        }
                    }
                    // Registra el movimiento a la cartera
                    if (!detallePago.getIdTipoRecaudo().equals(EnumTipoObligacion.COACTIVO.getValue())) {
                        iRFachadaCartera.registrarMovimiento(movimientoCarteraDTO);
                    } else {
                        CoactivoDTO coactivoDTO = iRFachadaCoactivo
                                .consultarCoactivo(detallePago.getNumeroObligacion());

                        if (coactivoDTO != null) {
                            if (coactivoDTO.getObligacionCoactivos() != null
                                    && !coactivoDTO.getObligacionCoactivos().isEmpty()) {

                                for (ObligacionCoactivoDTO obligacionCoactivoDTO : coactivoDTO
                                        .getObligacionCoactivos()) {
                                    // consulta cartera
                                    carteraDTO = new CarteraDTO();
                                    carteraDTO.setNombre(obligacionCoactivoDTO.getNumeroObligacion());
                                    carteraDTO.setTipoObligacion(
                                            new TipoObligacionDTO(EnumTipoObligacion.COMPARENDO.getValue()));
                                    carteraDTO.setEstadoObligacion(
                                            new EstadoObligacionDTO(EnumEstadoObligacion.ACTIVO.getValue()));
                                    carteraDTO = iRFachadaCartera.consultarCartera(carteraDTO);

                                    movimientoCarteraDTO.getMovimientosCartera().setCartera(carteraDTO);

                                    movimientoCarteraDTO.getMovimientosCartera().getCartera().setEstadoObligacion(
                                            new EstadoObligacionDTO(EnumEstadoObligacion.PAGADO.getValue()));

                                    movimientoCarteraDTO.getMovimientosCartera().getCartera().getEstadoObligacion()
                                            .setCodigo(EnumEstadoObligacion.PAGADO.getCodigo());

                                    movimientoCarteraDTO.getMovimientosCartera().setConceptoCartera(
                                            new ConceptoCarteraDTO(EnumConceptoCartera.COMPARENDO_PAGADO.getId()));

                                    movimientoCarteraDTO.getMovimientosCartera().getConceptoCartera()
                                            .setCodigo(EnumConceptoCartera.COMPARENDO_PAGADO.getCodigo());

                                    iRFachadaCartera.registrarMovimiento(movimientoCarteraDTO);

                                    // Actualiza estado del comparendo asociado
                                    CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
                                    cambioEstadoComparendo.setActividad(EnumActividad.PAGO_COMPARENDO);
                                    cambioEstadoComparendo.setCodigoOrganismo(
                                            detallePago.getPago().getOrganismoTransito().getCodigoOrganismo());
                                    cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.PAGADO);
                                    cambioEstadoComparendo.setFechaCambio(new Date());
                                    cambioEstadoComparendo
                                            .setNumeroComparendo(obligacionCoactivoDTO.getNumeroObligacion());
                                    iRFachadaComparendo.actualizarEstadoComparendo(cambioEstadoComparendo);

                                }

                                iRFachadaProceso.actualizarEstadoProceso(coactivoDTO.getProceso().getId(),
                                        EnumEstadoProceso.ECUADOR_COACTIVO_CERRADO, true);

                            }

                        }
                    }
                }
                pagosRealizados++;
            }

            // Guarda los datos registrados en el detalle
            if (detallePago.getId() == null) {
                detallePago.setFechaRegistro(ahora);
                em.persist(detallePago);
            } else {
                em.merge(detallePago);
            }
        }

        return pagosRealizados;

    }

    /**
     * Realiza conciliacion de recaudo de tipo comparendo
     * 
     * @param detallePago
     * @param movimientoCarteraDTO
     * @author julio.pinzon 2016-07-26
     */
    private void conciliarComparendo(DetallePago detallePago, MovimientoCarteraDTO movimientoCarteraDTO) {
        logger.debug("RecaudoEJB.conciliarComparendo(DetallePago, MovimientoCarteraDTO)");
        CarteraDTO carteraDTO = movimientoCarteraDTO.getMovimientosCartera().getCartera();
        if (carteraDTO == null) {
            // Reglas de conciliacion detalles de pago
            // NO existe la Obligacion de comparendo en cartera
            agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.NO_EXISTE_OBLIGACION_COMPARENDO);
        } else {
            // Solo verificamos el saldo a capital
            BigDecimal valorSaldoCapital = carteraDTO.getSaldoCapital();
            // El comparendo debe encontrarse en estado Vigente o Registrado
            ComparendoDTO comparendo = iRFachadaComparendo.consultarComparendo(detallePago.getNumeroObligacion(),
                    detallePago.getPago().getOrganismoTransito().getCodigoOrganismo());
            if (!EnumEstadoComparendo.REGISTRADO.getValue().equals(comparendo.getEstadoComparendo().getId())
                    && !EnumEstadoComparendo.VIGENTE.getValue().equals(comparendo.getEstadoComparendo().getId())) {
                agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.ESTADO_COMPARENDO_INVALIDO);
            }

            // NO coincide el Valor de la Obligacion con el Valor del Concepto de la cartera
            if (detallePago.getValorObligacion().compareTo(valorSaldoCapital) < CERO) {
                agregarErrorConciliacionPago(detallePago,
                        EnumErrorConciliacionPago.NO_COINCIDE_VALOR_OBLIGACION_VALOR_CONCEPTO);
                // Si el valor del pago es mayor que la cartera
            }

            if (detallePago.getDetallePagoConciliacionErrores().isEmpty()
                    && detallePago.getValorObligacion().compareTo(valorSaldoCapital) > CERO) {
                // Se paga toda la obligacion y el resto se paga como recargos
                BigDecimal diferenciaRecargos = detallePago.getValorObligacion().subtract(valorSaldoCapital);
                movimientoCarteraDTO.getMovimientosCartera().setValorMovimiento(valorSaldoCapital);

                // Movimientos cartera
                // 1. Concepto cartera crear intereses, saldo intereses = diferenciaRecargos
                MovimientoCarteraDTO movimientoCarteraRecargo1DTO = new MovimientoCarteraDTO();
                movimientoCarteraRecargo1DTO.setMovimientosCartera(new MovimientosCarteraDTO());
                movimientoCarteraRecargo1DTO.getMovimientosCartera().setCartera(carteraDTO);
                movimientoCarteraRecargo1DTO.getMovimientosCartera()
                        .setFechaCreacion(UtilFecha.buildCalendar().getTime());
                movimientoCarteraRecargo1DTO.getMovimientosCartera()
                        .setFechaMovimiento(detallePago.getPago().getFechaTransaccion());
                movimientoCarteraRecargo1DTO
                        .setIdActividad(EnumActividadCartera.REGISTRO_RECARGOS_OBLIGACION.getValue());
                movimientoCarteraRecargo1DTO.getMovimientosCartera()
                        .setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());
                movimientoCarteraRecargo1DTO.setSaldoCapital(valorSaldoCapital);
                movimientoCarteraRecargo1DTO.setSaldoIntereses(diferenciaRecargos);
                movimientoCarteraRecargo1DTO.getMovimientosCartera().setValorMovimiento(diferenciaRecargos);
                movimientoCarteraRecargo1DTO.getMovimientosCartera().setConceptoCartera(
                        new ConceptoCarteraDTO(EnumConceptoCartera.CREAR_INTERESES_COMPARENDO.getId()));
                movimientoCarteraRecargo1DTO.getMovimientosCartera().getConceptoCartera()
                        .setCodigo(EnumConceptoCartera.CREAR_INTERESES_COMPARENDO.getCodigo());
                iRFachadaCartera.registrarMovimiento(movimientoCarteraRecargo1DTO);

                // 2. Concepto cartera pagar intereses, saldo intereses = cero
                MovimientoCarteraDTO movimientoCarteraRecargo2DTO = new MovimientoCarteraDTO();
                movimientoCarteraRecargo2DTO.setMovimientosCartera(new MovimientosCarteraDTO());
                movimientoCarteraRecargo2DTO.getMovimientosCartera().setCartera(carteraDTO);
                movimientoCarteraRecargo2DTO.getMovimientosCartera()
                        .setFechaCreacion(UtilFecha.buildCalendar().getTime());
                movimientoCarteraRecargo2DTO.getMovimientosCartera()
                        .setFechaMovimiento(detallePago.getPago().getFechaTransaccion());
                movimientoCarteraRecargo2DTO.setIdActividad(EnumActividadCartera.PAGO_RECARGOS_OBLIGACION.getValue());
                movimientoCarteraRecargo2DTO.getMovimientosCartera()
                        .setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());
                movimientoCarteraRecargo2DTO.setSaldoCapital(valorSaldoCapital);
                movimientoCarteraRecargo2DTO.setSaldoIntereses(BigDecimal.ZERO);
                movimientoCarteraRecargo2DTO.getMovimientosCartera().setValorMovimiento(diferenciaRecargos);
                movimientoCarteraRecargo2DTO.getMovimientosCartera().setConceptoCartera(
                        new ConceptoCarteraDTO(EnumConceptoCartera.PAGAR_INTERESES_COMPARENDO.getId()));
                movimientoCarteraRecargo2DTO.getMovimientosCartera().getConceptoCartera()
                        .setCodigo(EnumConceptoCartera.PAGAR_INTERESES_COMPARENDO.getCodigo());
                iRFachadaCartera.registrarMovimiento(movimientoCarteraRecargo2DTO);
            }

            if (detallePago.getDetallePagoConciliacionErrores().isEmpty()) {
                // Asigna el concepto de comparendo
                movimientoCarteraDTO.getMovimientosCartera()
                        .setConceptoCartera(new ConceptoCarteraDTO(EnumConceptoCartera.COMPARENDO_PAGADO.getId()));
                movimientoCarteraDTO.getMovimientosCartera().getConceptoCartera()
                        .setCodigo(EnumConceptoCartera.COMPARENDO_PAGADO.getCodigo());

                // Actualiza estado del comparendo asociado
                CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
                cambioEstadoComparendo.setActividad(EnumActividad.PAGO_COMPARENDO);
                cambioEstadoComparendo
                        .setCodigoOrganismo(detallePago.getPago().getOrganismoTransito().getCodigoOrganismo());
                cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.PAGADO);
                cambioEstadoComparendo.setFechaCambio(new Date());
                cambioEstadoComparendo.setNumeroComparendo(detallePago.getNumeroObligacion());
                iRFachadaComparendo.actualizarEstadoComparendo(cambioEstadoComparendo);
            }
        }
    }

    /**
     * Realiza conciliacion de recaudo de tipo financiacion
     * 
     * @param detallePago
     * @param movimientoCarteraDTO
     * @author julio.pinzon 2016-07-26
     * @return Detalle de financiacion que fue pagado
     * @throws CirculemosNegocioException
     */
    private DetalleFinanciacionDTO conciliarFinanciacion(DetallePago detallePago,
            MovimientoCarteraDTO movimientoCarteraDTO) throws CirculemosNegocioException {
        logger.debug("RecaudoEJB.conciliarFinanciacion(DetallePago, MovimientoCarteraDTO)");
        DetalleFinanciacionDTO detalleFinanciacionPago = null;

        CarteraDTO carteraDTO = movimientoCarteraDTO.getMovimientosCartera().getCartera();

        // Consulta de la financiacion asociada
        FinanciacionDTO financiacionDTO = new FinanciacionDTO();
        financiacionDTO = iFachadaFinanciacion.consultarFinanciacion(detallePago.getNumeroObligacion());

        if (financiacionDTO != null) {
            // Obtiene el estado de la financiacion
            EstadoProcesoDTO estadoFinanciacion = iRFachadaProceso
                    .consultarEstadoProceso(financiacionDTO.getProceso().getId());

            DetalleFinanciacionDTO cuotaAnterior = null;
            // El valor de la cuota de la financiacion NO coincide con el valor pagado
            for (DetalleFinanciacionDTO detalleFinanciacion : financiacionDTO.getDetallesFinanciacion()) {
                if (detalleFinanciacion.getNumeroCuota().equals(detallePago.getNumeroCuota())
                        && detallePago.getValorObligacion().compareTo(detalleFinanciacion.getValorTotal()) != CERO) {
                    agregarErrorConciliacionPago(detallePago,
                            EnumErrorConciliacionPago.NO_COINCIDE_VALOR_CUOTA_FINANCIACION);
                    break;
                } else if (detalleFinanciacion.getNumeroCuota().equals(detallePago.getNumeroCuota())) {
                    detalleFinanciacionPago = detalleFinanciacion;
                    break;
                }
                cuotaAnterior = detalleFinanciacion;
            }

            // Reglas cuando es la primera cuota
            if (detallePago.getNumeroCuota() == CERO) {
                /*
                 * Cuando el Nro. De Cuota es IGUAL a 0 La financiación: - Si se gestionó por Circulemos 2.0, DEBE estar 'prefinanciada' para aplicar
                 * el pago. - Si se recibió por AXIS, DEBE estar 'En firme' para aplicar el pago.
                 */
                if (!(estadoFinanciacion.getId().equals(EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME.getValue())
                        && financiacionDTO.getFuenteInformacion().getId()
                                .equals(EnumTipoFuenteInformacion.AXIS.getValue()))
                        && !(estadoFinanciacion.getId()
                                .equals(EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO.getValue())
                                && financiacionDTO.getFuenteInformacion().getId()
                                        .equals(EnumTipoFuenteInformacion.CIRCULEMOS2.getValue()))) {
                    agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.ESTADO_FINANCIACION_INVALIDO);
                }

                // Cuando la financiacion fue creada por simulacion desde C2 se debe crear la cartera de la financiacion en el primer pago
                if (carteraDTO == null && financiacionDTO != null
                        && financiacionDTO.getFuenteInformacion().getId()
                                .equals(EnumTipoFuenteInformacion.CIRCULEMOS2.getValue())
                        && detallePago.getDetallePagoConciliacionErrores().isEmpty()) {
                    // Crear la cartera de financiacion
                    Long idCartera = iFachadaFinanciacion.registrarCarteraFinanciacion(financiacionDTO);

                    // Consulta la cartera generada
                    carteraDTO = iRFachadaCartera.consultarCartera(idCartera);
                    movimientoCarteraDTO.getMovimientosCartera().setCartera(carteraDTO);
                } else if (carteraDTO != null
                        && !carteraDTO.getSaldoCapital().equals(financiacionDTO.getValorTotal())) {
                    // El valor del saldo en cartera DEBE ser igual al valor total de la financiación.
                    agregarErrorConciliacionPago(detallePago,
                            EnumErrorConciliacionPago.NO_COINCIDE_VALOR_TOTAL_FINANCIACION);
                }
            } else { // Validaciones cuando la cuota es mayor a uno
                // Se debe verificar que la cuota anterior este pagada, cuando la cuota es diferente de 1.
                if (cuotaAnterior.getFechaPago() == null) {
                    agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.CUOTA_ANTERIOR_NO_PAGADA);
                }

                /*
                 * Cuando el Nro. De Cuota es DIFERENTE a 1 La financiacion DEBE estar 'En firme' o 'Financiación incumplida' para aplicar el pago.
                 * Vigente
                 */
                if (!estadoFinanciacion.getId().equals(EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME.getValue())
                        && !estadoFinanciacion.getId()
                                .equals(EnumEstadoProceso.ECUADOR_FINANCIACION_INCUMPLIDO.getValue())) {
                    agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.ESTADO_FINANCIACION_INVALIDO);
                }
            }

            // Si no existe cartera no se puede aplicar el pago
            if (carteraDTO == null) {
                // NO existe la Obligacion de comparendo en cartera
                // if (financiacionDTO.getFuenteInformacion().getId().equals(EnumTipoFuenteInformacion.AXIS.getValue())) {
                agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.NO_EXISTE_OBLIGACION_FINANCIACION);
                // }
            } else if (detallePago.getDetallePagoConciliacionErrores().isEmpty()) {
                // Solo verificamos el saldo a capital
                BigDecimal valorSaldoCapital = carteraDTO.getSaldoCapital();

                // Asigna valor de movimiento
                movimientoCarteraDTO.setSaldoCapital(valorSaldoCapital.subtract(detallePago.getValorObligacion()));
                movimientoCarteraDTO.getMovimientosCartera().setConceptoCartera(
                        new ConceptoCarteraDTO(EnumConceptoCartera.CUOTA_FINANCIACION_PAGADA.getId()));
                movimientoCarteraDTO.getMovimientosCartera().getConceptoCartera()
                        .setCodigo(EnumConceptoCartera.CUOTA_FINANCIACION_PAGADA.getCodigo());

                // Si saldo capital es cero, obligacion pagada
                if (movimientoCarteraDTO.getSaldoCapital().compareTo(BigDecimal.ZERO) == CERO) {

                    // Estado de la cartera a pagado
                    movimientoCarteraDTO.getMovimientosCartera().getCartera()
                            .setEstadoObligacion(new EstadoObligacionDTO(EnumEstadoObligacion.PAGADO.getValue()));
                    movimientoCarteraDTO.getMovimientosCartera().getCartera().getEstadoObligacion()
                            .setCodigo(EnumEstadoObligacion.PAGADO.getCodigo());

                    // Financiacion pagada
                    iRFachadaProceso.actualizarEstadoProceso(financiacionDTO.getProceso().getId(),
                            EnumEstadoProceso.ECUADOR_FINANCIACION_PAGADO, true);

                    // Estado de comparendo(s) pagado(s)
                    for (ObligacionFinanciacionDTO obligacion : financiacionDTO.getObligacionesFinanciacion()) {
                        if (obligacion.getCodigoTipoObligacion().equals(EnumTipoObligacion.COMPARENDO.getValue())) {

                            // Consulta la cartera por tipo (comparendo o financiacion) y numero de obligacion
                            // Trae el saldo mas reciente en el primer registro
                            CarteraDTO carteraComparendoDTO = new CarteraDTO();
                            carteraComparendoDTO.setNombre(obligacion.getNumeroObligacion());
                            carteraComparendoDTO
                                    .setTipoObligacion(new TipoObligacionDTO(obligacion.getCodigoTipoObligacion()));
                            carteraComparendoDTO.setEstadoObligacion(
                                    new EstadoObligacionDTO(EnumEstadoObligacion.FINANCIADO.getValue()));
                            carteraComparendoDTO = iRFachadaCartera.consultarCartera(carteraComparendoDTO);

                            if (carteraComparendoDTO != null) {
                                // Movimientos cartera
                                if (carteraComparendoDTO.getSaldoInteres().compareTo(BigDecimal.ZERO) != CERO) {
                                    // 1. Concepto cartera comparendo pagado desde financiacion intereses
                                    MovimientoCarteraDTO movimientoCarteraRecargoDTO = new MovimientoCarteraDTO();
                                    movimientoCarteraRecargoDTO.setMovimientosCartera(new MovimientosCarteraDTO());
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setCartera(carteraComparendoDTO);
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setFechaCreacion(UtilFecha.buildCalendar().getTime());
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setFechaMovimiento(detallePago.getPago().getFechaTransaccion());
                                    movimientoCarteraRecargoDTO
                                            .setIdActividad(EnumActividadCartera.PAGO_RECARGOS_OBLIGACION.getValue());
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());
                                    movimientoCarteraRecargoDTO.setSaldoCapital(carteraComparendoDTO.getSaldoCapital());
                                    movimientoCarteraRecargoDTO.setSaldoIntereses(BigDecimal.ZERO);
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setValorMovimiento(carteraComparendoDTO.getSaldoInteres());
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setConceptoCartera(new ConceptoCarteraDTO(
                                                    EnumConceptoCartera.PAGAR_INTERESES_COMPARENDO_FINANCIACION
                                                            .getId()));
                                    movimientoCarteraRecargoDTO.getMovimientosCartera().getConceptoCartera().setCodigo(
                                            EnumConceptoCartera.PAGAR_INTERESES_COMPARENDO_FINANCIACION.getCodigo());
                                    iRFachadaCartera.registrarMovimiento(movimientoCarteraRecargoDTO);
                                }

                                // 2. Concepto cartera comparendo pagado desde financiacion
                                carteraComparendoDTO.setEstadoObligacion(
                                        new EstadoObligacionDTO(EnumEstadoObligacion.PAGADO_FINANCIACION.getValue()));
                                carteraComparendoDTO.getEstadoObligacion()
                                        .setCodigo(EnumEstadoObligacion.PAGADO_FINANCIACION.getCodigo());
                                MovimientoCarteraDTO movimientoCarteraCapitalDTO = new MovimientoCarteraDTO();
                                movimientoCarteraCapitalDTO.setMovimientosCartera(new MovimientosCarteraDTO());
                                movimientoCarteraCapitalDTO.getMovimientosCartera().setCartera(carteraComparendoDTO);
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setFechaCreacion(UtilFecha.buildCalendar().getTime());
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setFechaMovimiento(detallePago.getPago().getFechaTransaccion());
                                movimientoCarteraCapitalDTO
                                        .setIdActividad(EnumActividadCartera.PAGO_OBLIGACION.getValue());
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());
                                movimientoCarteraCapitalDTO.setSaldoCapital(BigDecimal.ZERO);
                                movimientoCarteraCapitalDTO.setSaldoIntereses(BigDecimal.ZERO);
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setValorMovimiento(carteraComparendoDTO.getSaldoCapital());
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setConceptoCartera(new ConceptoCarteraDTO(
                                                EnumConceptoCartera.COMPARENDO_PAGADO_FINANCIACION.getId()));
                                movimientoCarteraCapitalDTO.getMovimientosCartera().getConceptoCartera()
                                        .setCodigo(EnumConceptoCartera.COMPARENDO_PAGADO_FINANCIACION.getCodigo());
                                iRFachadaCartera.registrarMovimiento(movimientoCarteraCapitalDTO);

                                // Cambia el estado del comparendo que fue pagado
                                CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
                                cambioEstadoComparendo.setActividad(EnumActividad.PAGO_COMPARENDO);
                                cambioEstadoComparendo.setCodigoOrganismo(
                                        detallePago.getPago().getOrganismoTransito().getCodigoOrganismo());
                                cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.PAGADO_FINANCIACION);
                                cambioEstadoComparendo.setFechaCambio(new Date());
                                cambioEstadoComparendo.setNumeroComparendo(obligacion.getNumeroObligacion());
                                iRFachadaComparendo.actualizarEstadoComparendo(cambioEstadoComparendo);
                            }
                        }
                    } // fin for obligaciones
                } else {
                    // aplica el pago para cada obligacion incluida en la financiacion
                    BigDecimal valorCapitalCuota = detalleFinanciacionPago.getValorCapital();
                    BigDecimal totalAplicado = BigDecimal.ZERO;
                    int cantidadObligaciones = 1;
                    BigDecimal saldoCapitalObligaciones = financiacionDTO.getValorTotalSaldoCapitalObligaciones();
                    if (detalleFinanciacionPago.getNumeroCuota().intValue() != CERO) {
                        saldoCapitalObligaciones = cuotaAnterior.getValorSaldoObligacion();
                    }
                    for (ObligacionFinanciacionDTO obligacion : financiacionDTO.getObligacionesFinanciacion()) {
                        if (obligacion.getCodigoTipoObligacion().equals(EnumTipoObligacion.COMPARENDO.getValue())) {

                            // Consulta la cartera por tipo (comparendo o financiacion) y numero de obligacion
                            // Trae el saldo mas reciente en el primer registro
                            CarteraDTO carteraComparendoDTO = new CarteraDTO();
                            carteraComparendoDTO.setNombre(obligacion.getNumeroObligacion());
                            carteraComparendoDTO
                                    .setTipoObligacion(new TipoObligacionDTO(obligacion.getCodigoTipoObligacion()));
                            carteraComparendoDTO = iRFachadaCartera.consultarCartera(carteraComparendoDTO);

                            if (carteraComparendoDTO != null) {
                                // Valores a tener en cuenta para repartir los valores a aplicar del pago
                                // por cada obligacion
                                BigDecimal porcentajeCapital = carteraComparendoDTO.getSaldoCapital()
                                        .divide(saldoCapitalObligaciones, ESCALA_DECIMAL, BigDecimal.ROUND_HALF_UP);
                                BigDecimal porcentajeRecargos = carteraComparendoDTO.getSaldoInteres()
                                        .divide(saldoCapitalObligaciones, ESCALA_DECIMAL, BigDecimal.ROUND_HALF_UP);
                                BigDecimal saldoRecargos = valorCapitalCuota.multiply(porcentajeRecargos)
                                        .setScale(ESCALA_DECIMAL, BigDecimal.ROUND_HALF_UP);
                                BigDecimal saldoCapital = valorCapitalCuota.multiply(porcentajeCapital)
                                        .setScale(ESCALA_DECIMAL, BigDecimal.ROUND_HALF_UP);
                                totalAplicado = totalAplicado.add(saldoCapital).add(saldoRecargos);

                                // Si es la ultima de las obligaciones a aplicarle el pago, se verifica si el total de todos
                                // los valores aplicados a las obligaciones suma el total a capital pagado por la cuota
                                // en caso de que no sea igual se suma o resta la diferencia para que sea aplicada a la ultima obligacion
                                // se hace con el fin de garantizar que lo pagado a capital sea lo que se descuente en las obligaciones incluidas
                                if (cantidadObligaciones == financiacionDTO.getObligacionesFinanciacion().size()) {
                                    BigDecimal diferencia = valorCapitalCuota.subtract(totalAplicado);
                                    if (diferencia.compareTo(BigDecimal.ZERO) != CERO) {
                                        saldoCapital = saldoCapital.add(diferencia);
                                    }
                                }

                                BigDecimal valorMovimientoInteres = BigDecimal.valueOf(saldoRecargos.doubleValue());
                                BigDecimal valorMovimientoCapital = BigDecimal.valueOf(saldoCapital.doubleValue());
                                saldoRecargos = carteraComparendoDTO.getSaldoInteres().subtract(saldoRecargos);
                                saldoCapital = carteraComparendoDTO.getSaldoCapital().subtract(saldoCapital);

                                // Movimientos cartera
                                if (carteraComparendoDTO.getSaldoInteres().compareTo(BigDecimal.ZERO) != CERO) {
                                    // 1. Concepto cartera comparendo pagado desde financiacion intereses
                                    MovimientoCarteraDTO movimientoCarteraRecargoDTO = new MovimientoCarteraDTO();
                                    movimientoCarteraRecargoDTO.setMovimientosCartera(new MovimientosCarteraDTO());
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setCartera(carteraComparendoDTO);
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setFechaCreacion(UtilFecha.buildCalendar().getTime());
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setFechaMovimiento(detallePago.getPago().getFechaTransaccion());
                                    movimientoCarteraRecargoDTO
                                            .setIdActividad(EnumActividadCartera.PAGO_RECARGOS_OBLIGACION.getValue());
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());
                                    movimientoCarteraRecargoDTO.setSaldoCapital(carteraComparendoDTO.getSaldoCapital());
                                    movimientoCarteraRecargoDTO.setSaldoIntereses(saldoRecargos);
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setValorMovimiento(valorMovimientoInteres);
                                    movimientoCarteraRecargoDTO.getMovimientosCartera()
                                            .setConceptoCartera(new ConceptoCarteraDTO(
                                                    EnumConceptoCartera.PAGAR_INTERESES_COMPARENDO_FINANCIACION
                                                            .getId()));
                                    movimientoCarteraRecargoDTO.getMovimientosCartera().getConceptoCartera().setCodigo(
                                            EnumConceptoCartera.PAGAR_INTERESES_COMPARENDO_FINANCIACION.getCodigo());
                                    iRFachadaCartera.registrarMovimiento(movimientoCarteraRecargoDTO);
                                }

                                // 2. Concepto cartera comparendo pago cuota financiacion
                                MovimientoCarteraDTO movimientoCarteraCapitalDTO = new MovimientoCarteraDTO();
                                movimientoCarteraCapitalDTO.setMovimientosCartera(new MovimientosCarteraDTO());
                                movimientoCarteraCapitalDTO.getMovimientosCartera().setCartera(carteraComparendoDTO);
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setFechaCreacion(UtilFecha.buildCalendar().getTime());
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setFechaMovimiento(detallePago.getPago().getFechaTransaccion());
                                movimientoCarteraCapitalDTO
                                        .setIdActividad(EnumActividadCartera.PAGO_OBLIGACION.getValue());
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());
                                movimientoCarteraCapitalDTO.setSaldoCapital(saldoCapital);
                                movimientoCarteraCapitalDTO.setSaldoIntereses(saldoRecargos);
                                movimientoCarteraCapitalDTO.getMovimientosCartera()
                                        .setValorMovimiento(valorMovimientoCapital);
                                movimientoCarteraCapitalDTO.getMovimientosCartera().setConceptoCartera(
                                        new ConceptoCarteraDTO(EnumConceptoCartera.CUOTA_FINANCIACION_PAGADA.getId()));
                                movimientoCarteraCapitalDTO.getMovimientosCartera().getConceptoCartera()
                                        .setCodigo(EnumConceptoCartera.CUOTA_FINANCIACION_PAGADA.getCodigo());
                                iRFachadaCartera.registrarMovimiento(movimientoCarteraCapitalDTO);
                                cantidadObligaciones++;
                            }
                        }
                    } // for obligaciones
                }
            }
        } else {
            // NO existe la financiacion
            agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.NO_EXISTE_OBLIGACION_FINANCIACION);
        }

        return detalleFinanciacionPago;
    }

    /**
     * Agrega un error de concilicion
     * 
     * @param detallePago
     * @param errorConciliacionPago
     * @author rodrigo.cruz
     */
    private void agregarErrorConciliacionPago(DetallePago detallePago,
            EnumErrorConciliacionPago errorConciliacionPago) {
        ErrorConciliacionPago errorConciliacion = new ErrorConciliacionPago();
        errorConciliacion.setId(errorConciliacionPago.getValue());
        DetallePagoConciliacionError detallePagoConciError = new DetallePagoConciliacionError();
        detallePagoConciError.setDetallePago(detallePago);
        detallePagoConciError.setErrorConciliacionPago(errorConciliacion);
        detallePagoConciError.setFechaRegistro(new Date());
        detallePago.getDetallePagoConciliacionErrores().add(detallePagoConciError);
    }

    /**
     * Realiza las validaciones basicas de negocio de recaudo
     * 
     * @param pagoDTO
     * @return listado de ErrorInconsistenciaDTO
     * @author diego.lozano
     * @throws CirculemosNegocioException
     */
    private List<PagoInconsistenciaDTO> validarReglasRecaudo(PagoDTO pagoDTO) throws CirculemosNegocioException {

        logger.debug("RecaudoEJB.validarReglasRecaudo(PagoDTO)");

        List<PagoInconsistenciaDTO> inconsistencias = new ArrayList<PagoInconsistenciaDTO>();
        PagoInconsistenciaDTO pagoInconsistencia = null;

        // VALIDACION REGLAS BASICAS PAGO
        // Organismo pertenezca a un catalogo
        List<OrganismoTransitoDTO> resultadoOrganismos = iRFachadaAdminNegocio
                .consultarOrganismoTransito(pagoDTO.getOrganismoTransito());
        if (resultadoOrganismos.isEmpty() || !resultadoOrganismos.get(0).getActivo()) {
            throw new CirculemosNegocioException(ErrorRecaudo.ValidarReglaNegocioRecaudo.REC_006_001);
        } else {
            pagoDTO.setOrganismoTransito(resultadoOrganismos.get(0));
        }

        // Fecha de transaccion no mayor a hoy
        if (pagoDTO.getFechaTransaccion().after(new Date())) {
            throw new CirculemosNegocioException(ErrorRecaudo.ValidarReglaNegocioRecaudo.REC_006_002);
        }

        // Suma de valores de tipos de pago
        BigDecimal totalPago = new BigDecimal(0);
        if (pagoDTO.getTotalCheque() != null) {
            totalPago = totalPago.add(pagoDTO.getTotalCheque());
        }
        if (pagoDTO.getTotalEfectivo() != null) {
            totalPago = totalPago.add(pagoDTO.getTotalEfectivo());
        }

        // Validacion NO coincide Total del recaudo con los valores registrados en Valor de la obligacion.
        if (pagoDTO.getTotalRecaudo().compareTo(totalPago) != 0) {
            throw new CirculemosNegocioException(ErrorRecaudo.ValidarReglaNegocioRecaudo.REC_006_003);
        }

        // VALIDACION REGLAS NEGOCIO

        // Validacion Pago con datos incompletos
        for (DetallePagoDTO detalleDTO : pagoDTO.getDetallesPago()) {

            // Creacion del DTO
            DetallePagoInconsistenciaDTO detalle = crearDetallePagoInconsistenciaDTO(detalleDTO);

            // Nro. De Cuota, validar regla del campo (Numero)
            if (detalleDTO.getIdTipoRecaudo().equals(EnumTipoObligacion.FINANCIACION.getValue())
                    && detalleDTO.getNumeroCuota() == null) {
                pagoInconsistencia = adicionarInconsistenciaDetalle(pagoInconsistencia, pagoDTO, detalle,
                        EnumErrorInconsistenciaPago.FALTA_NUMERO_CUOTA.getValue());
            }

            // Validacion Registro duplicado, pago ya aplicado
            if (consultarCantidadPagos(pagoDTO, detalleDTO) > 0) {
                pagoInconsistencia = adicionarInconsistenciaDetalle(pagoInconsistencia, pagoDTO, detalle,
                        EnumErrorInconsistenciaPago.PAGO_YA_APLICADO.getValue());
            }

        }

        if (pagoInconsistencia != null) {
            inconsistencias.add(pagoInconsistencia);
            // Registra las inconsistencias
            iInconsistenciaRecaudo.registrarInconsistencia(pagoInconsistencia);

        }
        return inconsistencias;

    }

    /**
     * Adiciona inconistencia al detalle
     * 
     * @param pago
     * @param pagoDTO
     * @param detalleDTO
     * @param idInconsistencia
     * @author diego.lozano
     * @return
     */
    private PagoInconsistenciaDTO adicionarInconsistenciaDetalle(PagoInconsistenciaDTO pago, PagoDTO pagoDTO,
            DetallePagoInconsistenciaDTO detalleDTO, Integer idInconsistencia) {

        if (pago == null) {
            pago = crearPagoInconsistenciaDTO(pagoDTO);
        }

        // Creacion de DTO de error
        ErrorInconsistenciaPagoDTO errorDTO = crearInconsistenciaDTO(idInconsistencia);

        detalleDTO.getErrorInconsistenciaPagos().add(errorDTO);

        pago.getDetallePagoInconsistencias().add(detalleDTO);

        return pago;

    }

    /**
     * Crea el DTO de pagoInconsistencia
     * 
     * @param pagoDTO
     * @return
     * @author diego.lozano
     */
    private PagoInconsistenciaDTO crearPagoInconsistenciaDTO(PagoDTO pagoDTO) {
        PagoInconsistenciaDTO pago = new PagoInconsistenciaDTO();

        // Codigo Organismo
        pago.setCodigoOrganismo(pagoDTO.getOrganismoTransito().getCodigoOrganismo());

        // Fecha transaccion
        pago.setFechaTransaccion(pagoDTO.getFechaTransaccion());

        // Numero pago
        pago.setNumeroPago(pagoDTO.getNumeroPago());

        // Numero cuenta
        pago.setNumeroCuenta(pagoDTO.getNumeroCuenta());

        // Id Banco
        pago.setIdBanco(pagoDTO.getIdBanco());

        // Id tipo cuenta
        pago.setIdTipoCuenta(pagoDTO.getIdTipoCuenta());

        // Total efectivo
        pago.setTotalEfectivo(pagoDTO.getTotalEfectivo());

        // Total cheque
        pago.setTotalCheque(pagoDTO.getTotalCheque());

        // Total recaudo
        pago.setTotalRecaudo(pagoDTO.getTotalRecaudo());

        if (pagoDTO.getTipoIdentificacionPersona() != null) {
            pago.setTipoIdentificacion(pagoDTO.getTipoIdentificacionPersona().getId());
        }
        pago.setNumeroDocumento(pagoDTO.getNumeroDocumento());

        // Detalles
        pago.setDetallePagoInconsistencias(new ArrayList<DetallePagoInconsistenciaDTO>());

        // Errores
        pago.setErrorInconsistenciaPagos(new ArrayList<ErrorInconsistenciaPagoDTO>());
        return pago;
    }

    /**
     * Crea el DTO de detallePagoInconsistencia
     * 
     * @param pagoDTO
     * @return
     * @author diego.lozano
     */
    private DetallePagoInconsistenciaDTO crearDetallePagoInconsistenciaDTO(DetallePagoDTO detalleDTO) {
        DetallePagoInconsistenciaDTO detalle = new DetallePagoInconsistenciaDTO();

        detalle.setNumeroObligacion(detalleDTO.getNumeroObligacion());
        detalle.setValorObligacion(detalleDTO.getValorObligacion());
        detalle.setIdTipoRecaudo(detalleDTO.getIdTipoRecaudo());
        detalle.setNumeroCuota(detalleDTO.getNumeroCuota());
        detalle.setErrorInconsistenciaPagos(new ArrayList<ErrorInconsistenciaPagoDTO>());

        return detalle;
    }

    /**
     * Crea el DTo del error
     * 
     * @param idInconsistencia
     * @return
     * @author diego.lozano
     */
    private ErrorInconsistenciaPagoDTO crearInconsistenciaDTO(Integer idInconsistencia) {
        // Creacion de DTO de error
        ErrorInconsistenciaPago error = em.find(ErrorInconsistenciaPago.class, idInconsistencia);
        ErrorInconsistenciaPagoDTO errorDTO = new ErrorInconsistenciaPagoDTO();
        errorDTO.setId(error.getId());
        errorDTO.setCodigo(error.getCodigo());
        errorDTO.setDescripcion(error.getDescripcion());
        errorDTO.setNombre(error.getNombre());
        errorDTO.setSigla(error.getSigla());
        errorDTO.setEstado(error.getActivo());
        return errorDTO;
    }

    /**
     * Consulta la cantidad de pagos que conincidan con los criterios enviados
     * 
     * @param pagoDTO
     * @param detalleDTO
     * @return
     * @author diego.lozano
     */
    private Long consultarCantidadPagos(PagoDTO pagoDTO, DetallePagoDTO detalleDTO) {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT COUNT(p)");
        jpql.append(" FROM Pago p");
        jpql.append(" JOIN p.detallesPago dp");
        jpql.append(" JOIN p.organismoTransito ot");
        jpql.append(" WHERE ot.codigoOrganismo = :codigoOrganismo");
        jpql.append(" AND p.fechaTransaccion = :fechaTransaccion");
        jpql.append(" AND p.numeroPago = :numeroRecaudo");
        jpql.append(" AND dp.numeroObligacion = :obligacionPagada");
        jpql.append(" AND dp.idTipoRecaudo = :tipoRecaudo");
        if (detalleDTO.getIdTipoRecaudo().equals(EnumTipoObligacion.FINANCIACION.getValue())) {
            jpql.append(" AND dp.numeroCuota = :numeroCuota");
        }

        final Query query = em.createQuery(jpql.toString());
        query.setParameter("codigoOrganismo", pagoDTO.getOrganismoTransito().getCodigoOrganismo());
        query.setParameter("fechaTransaccion", pagoDTO.getFechaTransaccion());
        query.setParameter("numeroRecaudo", pagoDTO.getNumeroPago());
        query.setParameter("obligacionPagada", detalleDTO.getNumeroObligacion());
        query.setParameter("tipoRecaudo", detalleDTO.getIdTipoRecaudo());
        if (detalleDTO.getIdTipoRecaudo().equals(EnumTipoObligacion.FINANCIACION.getValue())) {
            query.setParameter("numeroCuota", detalleDTO.getNumeroCuota());
        }

        return (Long) query.getSingleResult();
    }

    /**
     * Consulta un pago por id
     * 
     * @param pagoDTO
     * @return Pago encontrado, de lo contrario null
     * @author julio.pinzon 2016-07-29
     */
    private PagoDTO consultarPago(Long idPago) {
        PagoDTO pagoDTO = null;
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p");
        jpql.append(" FROM Pago p");
        jpql.append(" JOIN FETCH p.detallesPago dp");
        jpql.append(" JOIN p.organismoTransito ot");
        jpql.append(" JOIN FETCH dp.estadoPago ep");
        jpql.append(" WHERE p.id = :idPago");
        final TypedQuery<Pago> query = em.createQuery(jpql.toString(), Pago.class);
        query.setParameter("idPago", idPago);

        Pago pago = query.getSingleResult();
        if (pago != null) {
            pagoDTO = PagoHelperExtend.toLevel1DTO(pago);
        }
        return pagoDTO;
    }

    @Override
    public List<PagoDTO> consultarPagos(PagoDTO pagoDTO, DetallePagoDTO detallePagoDTO) {
        final StringBuilder jpql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        jpql.append("SELECT DISTINCT p");
        jpql.append(" FROM Pago p");
        jpql.append(" JOIN FETCH p.detallesPago dp");
        jpql.append(" JOIN p.organismoTransito ot");
        jpql.append(" JOIN FETCH dp.estadoPago ep");
        jpql.append(" WHERE 1 = 1");

        // PAGO
        if (pagoDTO != null) {
            if (pagoDTO.getId() != null) {
                jpql.append(" AND p.id = :idPago");
                params.put("idPago", pagoDTO.getId());
            }
            if (pagoDTO.getFechaTransaccion() != null) {
                jpql.append(" AND p.fechaTransaccion = :fechaTransaccion");
                params.put("fechaTransaccion", pagoDTO.getFechaTransaccion());
            }
            if (pagoDTO.getIdBanco() != null) {
                jpql.append(" AND p.idBanco = :idBanco");
                params.put("idBanco", pagoDTO.getIdBanco());
            }
            if (pagoDTO.getIdTipoCuenta() != null) {
                jpql.append(" AND p.idTipoCuenta LIKE :idTipoCuenta");
                params.put("idTipoCuenta", pagoDTO.getIdTipoCuenta());
            }
            if (StringUtils.isNotBlank(pagoDTO.getNumeroCuenta())) {
                jpql.append(" AND p.numeroCuenta = :numeroCuenta");
                params.put("numeroCuenta", pagoDTO.getNumeroCuenta());
            }
            if (StringUtils.isNotBlank(pagoDTO.getNumeroPago())) {
                jpql.append(" AND p.numeroPago = :numeroPago");
                params.put("numeroPago", pagoDTO.getNumeroPago());
            }
            if (pagoDTO.getTotalCheque() != null) {
                jpql.append(" AND p.totalCheque = :totalCheque");
                params.put("totalCheque", pagoDTO.getTotalCheque());
            }
            if (pagoDTO.getTotalEfectivo() != null) {
                jpql.append(" AND p.totalEfectivo = :totalEfectivo");
                params.put("totalEfectivo", pagoDTO.getTotalEfectivo());
            }
            if (pagoDTO.getTotalRecaudo() != null) {
                jpql.append(" AND p.totalRecaudo = :totalRecaudo");
                params.put("totalRecaudo", pagoDTO.getTotalRecaudo());
            }
            if (pagoDTO.getOrganismoTransito() != null && pagoDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
                jpql.append(" AND p.organismoTransito.codigoOrganismo = :codigoOrganismo");
                params.put("codigoOrganismo", pagoDTO.getOrganismoTransito().getCodigoOrganismo());
            }
            if (pagoDTO.getTipoIdentificacionPersona() != null
                    && pagoDTO.getTipoIdentificacionPersona().getId() != null) {
                jpql.append(" AND p.tipoIdentificacionPersona.id = :idTipoIdentificacion");
                params.put("idTipoIdentificacion", pagoDTO.getTipoIdentificacionPersona().getId());
            }
            if (StringUtils.isNotBlank(pagoDTO.getNumeroDocumento())) {
                jpql.append(" AND p.numeroDocumento = :numeroDocumento");
                params.put("numeroDocumento", pagoDTO.getNumeroDocumento());
            }
        }
        // DETALLE DE PAGO
        if (detallePagoDTO != null) {
            if (StringUtils.isNotBlank(detallePagoDTO.getNumeroObligacion())) {
                jpql.append(" AND dp.numeroObligacion = :numeroObligacion");
                params.put("numeroObligacion", detallePagoDTO.getNumeroObligacion());
            }
            if (detallePagoDTO.getEstadoPago() != null && detallePagoDTO.getEstadoPago().getId() != null) {
                jpql.append(" AND dp.estadoPago.id = :idEstadoPago");
                params.put("idEstadoPago", detallePagoDTO.getEstadoPago().getId());
            }
            if (detallePagoDTO.getIdTipoRecaudo() != null) {
                jpql.append(" AND dp.idTipoRecaudo = :idTipoRecaudo");
                params.put("idTipoRecaudo", detallePagoDTO.getIdTipoRecaudo());
            }
            if (detallePagoDTO.getNumeroCuota() != null) {
                jpql.append(" AND dp.numeroCuota = :numeroCuota");
                params.put("numeroCuota", detallePagoDTO.getNumeroCuota());
            }
        }

        jpql.append(" ORDER BY dp.numeroObligacion, dp.numeroCuota");

        final GenericDao<Pago> dao = new GenericDao<>(Pago.class, em);
        final List<Pago> pagos = dao.buildAndExecuteQuery(jpql.toString(), params);
        List<PagoDTO> pagosDTO = null;

        if (!pagos.isEmpty()) {
            pagosDTO = new ArrayList<PagoDTO>(1);
            for (Pago pago : pagos) {
                List<DetallePagoDTO> detallesPagoDTO = DetallePagoHelper.toListLevel1DTO(pago.getDetallesPago());
                PagoDTO pagoConsultaDTO = PagoHelper.toLevel1DTO(pago);
                pagoConsultaDTO.setDetallesPago(detallesPagoDTO);
                pagosDTO.add(pagoConsultaDTO);
            }
        }

        return pagosDTO;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaReplicarPagoDTO replicarPagoTerceros(Integer codigoOrganismo) {
        logger.debug("RecaudoEJB.replicarPago(int)");
        RespuestaReplicarPagoDTO respuesta = new RespuestaReplicarPagoDTO();

        String loginUsuario = seguridadCirculemosEJB.obtenerUsuarioDto().getLogin();

        // Consulta los registros nuevos de recaudos
        List<ItRecaudoDTO> recaudos = iRFachadaIntegracionTerceros.consultarRecaudos(codigoOrganismo,
                EnumEstadoLectura.NUEVO);
        respuesta.setTotalRecaudosLeidos(recaudos.size());
        int totalRecaudosRecibidos = 0;
        int totalRecaudosNoRecibidos = 0;
        RecaudoUtil.inicializarCacheHomologacion();
        for (ItRecaudoDTO recaudoDTO : recaudos) {
            EnumEstadoLectura estadoLectura = EnumEstadoLectura.NO_RECIBIDO;
            try {
                boolean recibido = recaudoEJB.recibirRecaudoTerceros(recaudoDTO, loginUsuario);
                if (recibido) {
                    totalRecaudosRecibidos++;
                    estadoLectura = EnumEstadoLectura.RECIBIDO;
                } else {
                    totalRecaudosNoRecibidos++;
                }
            } catch (Exception e) {
                logger.error("Error al procesar recaudo: " + e.getMessage(), e);
                totalRecaudosNoRecibidos++;
            }
            // Actualiza la informacion de los recaudos
            iRFachadaIntegracionTerceros.actualizarEstadoRecaudo(recaudoDTO.getIdRecaudo(), estadoLectura);
        }
        RecaudoUtil.limpiarCacheHomologacion();
        respuesta.setTotalRecaudosRecibidos(totalRecaudosRecibidos);
        respuesta.setTotalRecaudosNoRecibidos(totalRecaudosNoRecibidos);
        return respuesta;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public boolean recibirRecaudoTerceros(ItRecaudoDTO recaudoDTO, String loginUsuario) {
        logger.debug("RecaudoEJB.recibirRecaudoTerceros(ItRecaudoDTO, String)");
        boolean recibido = false;
        LogReplicarPago logReplicarPago = new LogReplicarPago(recaudoDTO.getNumeroRecaudo(), null);

        try {
            // Convertir recaudos de IT a pagos
            PagoDTO pagoDTO = ReplicarRecaudoTercerosHelper.toPagoDTO(recaudoDTO);

            // Validar obligatoriedad datos
            List<EnumTipoRechazoRecaudo> tiposRechazoRecaudo = validarDatosRecaudo(recaudoDTO);

            // Homologacion de catalogos
            RespuestaHomologarCatalogoRecaudoDTO respuestaHomologacion = homologarCatalogosReplicarPago(pagoDTO,
                    recaudoDTO);
            tiposRechazoRecaudo.addAll(respuestaHomologacion.getErrores());
            if (tiposRechazoRecaudo.isEmpty()) {
                RespuestaProcesarRecaudoDTO respuestaProcesar = procesarRecaudo(pagoDTO);
                if (respuestaProcesar.getEstadoTransaccion().equals(EnumEstadoTransaccion.REGISTRADO)) {
                    recibido = true;
                }
                logReplicarPago.setEstadoTransaccion(respuestaProcesar.getEstadoTransaccion().getValue());
            } else {
                // Arma objeto con los datos del recudo rechazado
                RecaudoRechazoDTO recaudoRechazoDTO = ReplicarRecaudoTercerosHelper.toRecaudoRechazoDTO(recaudoDTO);
                List<TipoRechazoRecaudoDTO> motivosRechazoRecaudo = new ArrayList<TipoRechazoRecaudoDTO>();
                // Cambia la transaccion del log
                logReplicarPago.setEstadoTransaccion(EnumEstadoTransaccion.RECHAZADO.getValue());
                StringBuilder detalle = new StringBuilder(VALOR_NO_MAPEADO);

                for (EnumTipoRechazoRecaudo enumTipoRechazoRecaudo : tiposRechazoRecaudo) {
                    TipoRechazoRecaudoDTO motivo = new TipoRechazoRecaudoDTO();
                    String valor = null;
                    switch (enumTipoRechazoRecaudo) {
                    case BANCO_NO_EXISTE:
                        valor = recaudoDTO.getCodigoBanco();
                        break;
                    case CODIGO_BANCO_OBLIGATORIO:
                        valor = recaudoDTO.getCodigoBanco();
                        break;
                    case CODIGO_TIPO_CUENTA_OBLIGATORIO:
                        valor = recaudoDTO.getCodigoTipoCuenta();
                        break;
                    case CODIGO_TIPO_ID_OBLIGATORIO:
                        valor = recaudoDTO.getCodigoTipoIdentificacion();
                        break;
                    case CODIGO_TIPO_RECAUDO_OBLIGATORIO:
                        valor = recaudoDTO.getCodigoTipoRecaudo();
                        break;
                    case FECHA_RECAUDO_SUPERIOR_ACTUAL:
                        valor = recaudoDTO.getFechaTransaccion().toString();
                        break;
                    case NUMERO_CUENTA_OBLIGATORIO:
                        valor = recaudoDTO.getNumeroCuenta();
                        break;
                    case NUMERO_OBLIGACION_OBLIGATORIO:
                        valor = recaudoDTO.getNumeroObligacion();
                        break;
                    case NUMERO_RECAUDO_OBLIGATORIO:
                        valor = recaudoDTO.getNumeroRecaudo();
                        break;
                    case ORGANISMO_NO_EXISTE:
                        valor = recaudoDTO.getCodigoOrganismo() + "";
                        break;
                    case TIPO_CUENTA_NO_EXISTE:
                        valor = recaudoDTO.getCodigoTipoCuenta();
                        break;
                    case TIPO_DOCUMENTO_IDENTIFICACION_NO_EXISTE:
                        valor = recaudoDTO.getCodigoTipoIdentificacion();
                        break;
                    case TIPO_RECAUDO_NO_EXISTE:
                        valor = recaudoDTO.getCodigoTipoRecaudo();
                        break;
                    case TOTAL_RECAUDO_MENOR_IGUAL_CERO:
                        valor = recaudoDTO.getTotalRecaudo().toPlainString();
                        break;
                    case TOTAL_RECAUDO_OBLIGATORIO:
                        valor = recaudoDTO.getTotalRecaudo().toPlainString();
                        break;
                    case VALOR_CHEQUE_MENOR_IGUAL_CERO:
                        valor = recaudoDTO.getTotalCheque().toPlainString();
                        break;
                    case VALOR_EFECTIVO_MENOR_IGUAL_CERO:
                        valor = recaudoDTO.getTotalEfectivo().toPlainString();
                        break;
                    case TOTAL_RECAUDO_NO_SUMA_MEDIOS_PAGO:
                        valor = recaudoDTO.getTotalRecaudo().toPlainString();
                        break;
                    case NUMERO_IDENTIFICACION_OBLIGATORIO:
                        valor = recaudoDTO.getNumeroIdentificacion();
                        break;
                    case VALOR_OBLIGACION_MENOR_IGUAL_CERO:
                        valor = recaudoDTO.getValorObligacion().toPlainString();
                        break;
                    }
                    motivo.setId(enumTipoRechazoRecaudo.getValue());
                    detalle.append(enumTipoRechazoRecaudo.toString() + ":" + valor + ", ");
                    motivosRechazoRecaudo.add(motivo);
                }
                logReplicarPago.setDetalle(detalle.toString());

                // Guarda el reporte de los no enviados
                recaudoRechazoDTO.setMotivoRechazoRecaudo(motivosRechazoRecaudo);
                rechazosRecaudoEJB.registrarRechazosRecaudos(recaudoRechazoDTO);
            }
        } catch (CirculemosNegocioException e) {
            logger.error("Error al procesar recaudo: " + e.getMessage());

            logReplicarPago.setEstadoTransaccion(EnumEstadoTransaccion.RECHAZADO.getValue());
            logReplicarPago.setDetalle(e.getMessage());

            // Arma objeto con los datos del recudo rechazado
            RecaudoRechazoDTO itRecaudoRechazoDTO = ReplicarRecaudoTercerosHelper.toRecaudoRechazoDTO(recaudoDTO);
            // Guarda el reporte de los no enviados
            List<TipoRechazoRecaudoDTO> motivosRechazoRecaudo = new ArrayList<TipoRechazoRecaudoDTO>();
            ValidarReglaNegocioRecaudo valReglaNegocio = (ValidarReglaNegocioRecaudo) e.getErrorInfo();
            TipoRechazoRecaudoDTO motivo = null;

            switch (valReglaNegocio) {

            case REC_006_001:
                motivo = new TipoRechazoRecaudoDTO(EnumTipoRechazoRecaudo.ORGANISMO_NO_EXISTE.getValue());
                break;
            case REC_006_002:
                motivo = new TipoRechazoRecaudoDTO(EnumTipoRechazoRecaudo.FECHA_RECAUDO_SUPERIOR_ACTUAL.getValue());
                break;
            case REC_006_003:
                motivo = new TipoRechazoRecaudoDTO(EnumTipoRechazoRecaudo.TOTAL_RECAUDO_NO_SUMA_MEDIOS_PAGO.getValue());
                break;
            }
            motivosRechazoRecaudo.add(motivo);
            itRecaudoRechazoDTO.setMotivoRechazoRecaudo(motivosRechazoRecaudo);
            rechazosRecaudoEJB.registrarRechazosRecaudos(itRecaudoRechazoDTO);
        } finally {
            loggerReplicar.escribir(loginUsuario, logReplicarPago);
        }
        return recibido;
    }

    /**
     * Validar datos de recaudo de terceros
     * 
     * @param recaudoDTO
     * @return Lista de errores
     * @author julio.pinzon (2016-05-17)
     */
    private List<EnumTipoRechazoRecaudo> validarDatosRecaudo(ItRecaudoDTO recaudoDTO) {
        logger.debug("RecaudoEJB.validarDatosRecaudo(ItRecaudoDTO)");
        List<EnumTipoRechazoRecaudo> errores = new ArrayList<EnumTipoRechazoRecaudo>();

        // TOTAL_RECAUDO_OBLIGATORIO
        if (recaudoDTO.getTotalRecaudo() == null) {
            errores.add(EnumTipoRechazoRecaudo.TOTAL_RECAUDO_OBLIGATORIO);
        } else {
            // TOTAL_RECAUDO_MENOR_IGUAL_CERO
            if (recaudoDTO.getTotalRecaudo().compareTo(BigDecimal.ZERO) < 1) {
                errores.add(EnumTipoRechazoRecaudo.TOTAL_RECAUDO_MENOR_IGUAL_CERO);
            }
        }

        // VALOR_CHEQUE_MENOR_IGUAL_CERO
        if (recaudoDTO.getTotalCheque() != null) {
            if (recaudoDTO.getTotalCheque().compareTo(BigDecimal.ZERO) < 1) {
                errores.add(EnumTipoRechazoRecaudo.VALOR_CHEQUE_MENOR_IGUAL_CERO);
            }
        }

        // VALOR_EFECTIVO_MENOR_IGUAL_CERO
        if (recaudoDTO.getTotalEfectivo() != null) {
            if (recaudoDTO.getTotalEfectivo().compareTo(BigDecimal.ZERO) < 1) {
                errores.add(EnumTipoRechazoRecaudo.VALOR_EFECTIVO_MENOR_IGUAL_CERO);
            }
        }

        // NUMERO_RECAUDO_OBLIGATORIO
        if (StringUtils.isBlank(recaudoDTO.getNumeroRecaudo())) {
            errores.add(EnumTipoRechazoRecaudo.NUMERO_RECAUDO_OBLIGATORIO);
        }

        // NUMERO_OBLIGACION_OBLIGATORIO
        if (StringUtils.isBlank(recaudoDTO.getNumeroObligacion())) {
            errores.add(EnumTipoRechazoRecaudo.NUMERO_OBLIGACION_OBLIGATORIO);
        }

        // CODIGO_TIPO_RECAUDO_OBLIGATORIO
        if (StringUtils.isBlank(recaudoDTO.getCodigoTipoRecaudo())) {
            errores.add(EnumTipoRechazoRecaudo.CODIGO_TIPO_RECAUDO_OBLIGATORIO);
        }

        // VALOR_OBLIGACION_MENOR_IGUAL_CERO
        if (recaudoDTO.getValorObligacion() != null) {
            if (recaudoDTO.getValorObligacion().compareTo(BigDecimal.ZERO) < 1) {
                errores.add(EnumTipoRechazoRecaudo.VALOR_OBLIGACION_MENOR_IGUAL_CERO);
            }
        }
        return errores;
    }

    @Override
    public RespuestaHomologarCatalogoRecaudoDTO homologarCatalogosReplicarPago(PagoDTO pagoDTO, ItRecaudoDTO recaudo) {
        logger.debug("RecaudoEJB.homologarCatalogosReplicarPago(PagoDTO, ItRecaudoDTO)");
        RespuestaHomologarCatalogoRecaudoDTO respuesta = new RespuestaHomologarCatalogoRecaudoDTO();
        List<EnumTipoRechazoRecaudo> catalogosError = new ArrayList<EnumTipoRechazoRecaudo>();

        // **************************************
        // Homologacion de catalogos del encabezado
        // **************************************

        // CODIGO TIPO DE IDENTIFICACION DEL DEUDOR
        if (StringUtils.isNotBlank(recaudo.getCodigoTipoIdentificacion())) {
            Integer id = consultarIdCatalogo(EnumCatalogo.TipoIdentificacionPersona,
                    recaudo.getCodigoTipoIdentificacion());
            if (id != null) {
                pagoDTO.getTipoIdentificacionPersona().setId(id);
            } else {
                catalogosError.add(EnumTipoRechazoRecaudo.TIPO_DOCUMENTO_IDENTIFICACION_NO_EXISTE);
            }
        }

        // TIPO CUENTA-> TipoCuenta
        if (StringUtils.isNotBlank(recaudo.getCodigoTipoCuenta())) {
            Integer id = consultarIdCatalogo(EnumCatalogo.TipoCuentaBancaria, recaudo.getCodigoTipoCuenta());
            if (id != null) {
                pagoDTO.setIdTipoCuenta(id);
            } else {
                catalogosError.add(EnumTipoRechazoRecaudo.TIPO_CUENTA_NO_EXISTE);
            }
        }

        // BANCO-> banco
        if (StringUtils.isNotBlank(recaudo.getCodigoBanco())) {
            Integer id = consultarIdCatalogo(EnumCatalogo.Banco, recaudo.getCodigoBanco());
            if (id != null) {
                pagoDTO.setIdBanco(id);
            } else {
                catalogosError.add(EnumTipoRechazoRecaudo.BANCO_NO_EXISTE);
            }
        }

        // Organismo Transito
        if (recaudo.getCodigoOrganismo() == null) {
            catalogosError.add(EnumTipoRechazoRecaudo.ORGANISMO_INCORRECTO);
        }

        // Cuenta
        if (recaudo.getNumeroCuenta() != null && StringUtils.isNotBlank(recaudo.getNumeroCuenta())
                && recaudo.getNumeroCuenta().trim().length() > LONGITUD_NUMERO_CUENTA) {
            catalogosError.add(EnumTipoRechazoRecaudo.NUMERO_CUENTA_LONGITUD);
        }

        // Numero cuota
        if (recaudo.getNumeroCuota() == null
                || (recaudo.getNumeroCuota().compareTo(Integer.valueOf(BigInteger.ONE.intValue())) < 0
                        && !recaudo.getNumeroCuota().equals(BigDecimal.ZERO.intValue()))) {
            catalogosError.add(EnumTipoRechazoRecaudo.NUMERO_CUOTA_INCORRECTO);
            recaudo.setNumeroCuota(null);
        }

        // Numero obligacion
        if (recaudo.getNumeroObligacion() == null || StringUtils.isBlank(recaudo.getNumeroObligacion())
                || recaudo.getNumeroObligacion().trim().length() > LONGITUD_NUMERO_OBLIGACION) {
            catalogosError.add(EnumTipoRechazoRecaudo.NUMERO_OBLIGACION_LONGITUD);
        }

        // Tipo recaudo
        if (recaudo.getCodigoTipoRecaudo() == null || StringUtils.isBlank(recaudo.getCodigoTipoRecaudo())) {
            catalogosError.add(EnumTipoRechazoRecaudo.CODIGO_TIPO_RECAUDO_OBLIGATORIO);
        }

        // Valor Obligacion
        if (recaudo.getValorObligacion() != null && recaudo.getValorObligacion().scale() <= ESCALA_DECIMAL - 1) {
            recaudo.setValorObligacion(recaudo.getValorObligacion().setScale(ESCALA_DECIMAL));
        }
        if (recaudo.getValorObligacion() == null || StringUtils.isBlank(recaudo.getValorObligacion().toString())
                || recaudo.getValorObligacion().compareTo(BigDecimal.valueOf(BigDecimal.ONE.intValue())) < 0
                || recaudo.getValorObligacion().scale() > ESCALA_DECIMAL
                || recaudo.getValorObligacion().precision() > VALOR_OBLIGACION_PRECISION_NUMERICA) {
            catalogosError.add(EnumTipoRechazoRecaudo.VALOR_OBLIGACION_MENOR_IGUAL_CERO);
        }

        // Fecha Transaccion
        if (recaudo.getFechaTransaccion() == null || StringUtils.isBlank(recaudo.getFechaTransaccion().toString())) {
            catalogosError.add(EnumTipoRechazoRecaudo.FECHA_TRANSACCION_INCORRECTA);
        }

        // Hora Transaccion
        if (recaudo.getHoraTransaccion() == null || StringUtils.isBlank(recaudo.getHoraTransaccion().toString())) {
            catalogosError.add(EnumTipoRechazoRecaudo.HORA_TRANSACCION_INCORRECTA);
        }

        // Numero identificacion
        if (recaudo.getNumeroIdentificacion() != null && StringUtils.isNotBlank(recaudo.getNumeroIdentificacion())
                && recaudo.getNumeroIdentificacion().trim().length() > LONGITUD_NUMERO_IDENTIFICACION) {
            catalogosError.add(EnumTipoRechazoRecaudo.NUMERO_IDENTIFICACION_LONGITUD);
        }

        // Numero recaudo
        if (recaudo.getNumeroRecaudo() == null || StringUtils.isBlank(recaudo.getNumeroRecaudo())
                || recaudo.getNumeroRecaudo().trim().length() > LONGITUD_NUMERO_RECAUDO) {
            catalogosError.add(EnumTipoRechazoRecaudo.NUMERO_RECAUDO_INCORRECTO);
        }

        // Total cheque
        if (recaudo.getTotalCheque() != null && recaudo.getTotalCheque().scale() <= ESCALA_DECIMAL - 1) {
            recaudo.setTotalCheque(recaudo.getTotalCheque().setScale(ESCALA_DECIMAL));
        }
        if ((recaudo.getTotalCheque() != null && StringUtils.isNotBlank(recaudo.getTotalCheque().toString()))
                && (recaudo.getTotalCheque().compareTo(BigDecimal.valueOf(BigDecimal.ONE.intValue())) < 0
                        || recaudo.getTotalCheque().scale() > ESCALA_DECIMAL
                        || recaudo.getTotalCheque().precision() > TOTAL_CHEQUE_PRECISION_NUMERICA)) {
            catalogosError.add(EnumTipoRechazoRecaudo.VALOR_CHEQUE_MENOR_IGUAL_CERO);
        }

        // Total efectivo
        if (recaudo.getTotalEfectivo() != null && recaudo.getTotalEfectivo().scale() <= ESCALA_DECIMAL - 1) {
            recaudo.setTotalEfectivo(recaudo.getTotalEfectivo().setScale(ESCALA_DECIMAL));
        }
        if ((recaudo.getTotalEfectivo() != null && StringUtils.isNotBlank(recaudo.getTotalEfectivo().toString()))
                && (recaudo.getTotalEfectivo().compareTo(BigDecimal.valueOf(BigDecimal.ONE.intValue())) < 0
                        || recaudo.getTotalEfectivo().scale() > ESCALA_DECIMAL
                        || recaudo.getTotalEfectivo().precision() > TOTAL_EFECTIVO_PRECISION_NUMERICA)) {
            catalogosError.add(EnumTipoRechazoRecaudo.VALOR_EFECTIVO_MENOR_IGUAL_CERO);
        }

        // Total recaudo
        if (recaudo.getTotalRecaudo() != null && recaudo.getTotalRecaudo().scale() <= ESCALA_DECIMAL - 1) {
            recaudo.setTotalRecaudo(recaudo.getTotalRecaudo().setScale(ESCALA_DECIMAL));
        }
        if (recaudo.getTotalRecaudo() == null || StringUtils.isBlank(recaudo.getTotalRecaudo().toString())
                || recaudo.getTotalRecaudo().compareTo(BigDecimal.valueOf(BigDecimal.ONE.intValue())) < 0
                || recaudo.getTotalRecaudo().scale() > ESCALA_DECIMAL
                || recaudo.getTotalRecaudo().precision() > TOTAL_RECAUDO_PRECISION_NUMERICA) {
            catalogosError.add(EnumTipoRechazoRecaudo.TOTAL_RECAUDO_OBLIGATORIO);
        }

        // **************************************
        // Homologacion de catalogos del detalle
        // **************************************

        List<DetallePagoDTO> detalles = new ArrayList<DetallePagoDTO>();
        // TIPO RECAUDO
        if (StringUtils.isNotBlank(recaudo.getCodigoTipoRecaudo())) {
            Integer id = consultarIdCatalogo(EnumCatalogo.TipoObligacion, recaudo.getCodigoTipoRecaudo());
            if (id != null) {
                DetallePagoDTO detallePagoDTO = new DetallePagoDTO();
                detallePagoDTO.setIdTipoRecaudo(id);
                detallePagoDTO.setNumeroCuota(recaudo.getNumeroCuota());
                detallePagoDTO.setNumeroObligacion(recaudo.getNumeroObligacion());
                detallePagoDTO.setValorObligacion(recaudo.getValorObligacion());
                detalles.add(detallePagoDTO);
            } else {
                catalogosError.add(EnumTipoRechazoRecaudo.TIPO_RECAUDO_NO_EXISTE);
            }
        }
        pagoDTO.setDetallesPago(detalles);

        respuesta.setPagoDTO(pagoDTO);
        respuesta.setErrores(catalogosError);
        return respuesta;

    }

    /**
     * Consulta el id de un determinado item de catalogos desde modulos externos.
     * 
     * @param catalogo
     *            Enumeracion del catalogo a buscar
     * @param codigo
     *            el codigo del item del catalogo a consultar
     * @return id o identificador del catalogo ingresado.
     * @author julio.pinzon (2016-05-05)
     */
    private Integer consultarIdCatalogo(EnumCatalogo catalogo, String codigo) {
        logger.debug("RecaudoEJB.consultarIdCatalogo(ItRecaudoDTO, String)");
        Integer idCatalogo = RecaudoUtil.obtenerValorHomologacionCache(catalogo, codigo);
        if (idCatalogo == null) {
            ItemCatalogoDTO item = new ItemCatalogoDTO();
            item.setCodigo(codigo);
            item.setActivo(true);
            List<ItemCatalogoDTO> result = fachadaConfiguracionEJB.consultarItemsCatalogo(catalogo.toString(), item);
            if (result.isEmpty()) {
                return null;
            }
            idCatalogo = result.get(0).getId();
            RecaudoUtil.agregarValorHomologacionCache(catalogo, codigo, idCatalogo);
        }
        return idCatalogo;
    }

    @Override
    public RespuestaConciliarPagoDTO realizarConciliacionRecaudo() {
        logger.debug("RecaudoEJB.realizarConciliacionRecaudo()");
        // Consulta pagos no conciliados
        List<PagoDTO> pagosNoConciliados = consultarPagosNoConciliados();

        // Conciliar recaudos
        int numPagosValidados = 0, numPagosAplicados = 0;

        if (pagosNoConciliados != null) {
            for (PagoDTO pago : pagosNoConciliados) {
                numPagosValidados += pago.getDetallesPago().size();
                try {
                    numPagosAplicados += recaudoEJB.conciliarRecaudo(pago);
                } catch (Exception e) {
                    logger.error("Error al conciliar pago numero: " + pago.getNumeroPago(), e);
                }
            }
        }
        RespuestaConciliarPagoDTO respuesta = new RespuestaConciliarPagoDTO();
        respuesta.setTotalRecaudosConsultados(numPagosValidados);
        respuesta.setTotalRecaudosConciliados(numPagosAplicados);
        respuesta.setTotalRecaudosNoConciliados(numPagosValidados - numPagosAplicados);
        return respuesta;
    }

    /**
     * Consulta los detalles de pago no conciliados
     * 
     * @return Lista de pagos
     * @author julio.pinzon 2016-07-29
     */
    private List<PagoDTO> consultarPagosNoConciliados() {
        logger.debug("RecaudoEJB.consultarPagosNoConciliados()");
        final StringBuilder jpql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        jpql.append("SELECT dp");
        jpql.append(" FROM DetallePago dp");
        jpql.append(" JOIN FETCH dp.estadoPago ep");
        jpql.append(" JOIN FETCH dp.pago p");
        jpql.append(" JOIN p.organismoTransito ot");
        jpql.append(" WHERE dp.estadoPago.id = :idEstadoPago");
        jpql.append(" ORDER BY dp.numeroObligacion, dp.numeroCuota");

        params.put("idEstadoPago", EnumEstadoPago.PAGO_NO_CONCILIADO.getValue());
        final GenericDao<DetallePago> dao = new GenericDao<>(DetallePago.class, em);
        final List<DetallePago> detalles = dao.buildAndExecuteQuery(jpql.toString(), params);
        List<PagoDTO> pagos = null;

        if (!detalles.isEmpty()) {
            pagos = new ArrayList<PagoDTO>(1);
            for (DetallePago detalle : detalles) {
                PagoDTO pago = PagoHelper.toLevel1DTO(detalle.getPago());
                List<DetallePagoDTO> detallesPago = new ArrayList<>(1);
                detallesPago.add(DetallePagoHelper.toLevel1DTO(detalle));
                pago.setDetallesPago(detallesPago);
                pagos.add(pago);
            }
        }

        return pagos;
    }

    private void conciliarCoactivo(DetallePago detallePago, MovimientoCarteraDTO movimientoCarteraDTO) {
        logger.debug("RecaudoEJB.conciliacionCoactivo(DetallePago)");

        // consulta coactivo
        CoactivoDTO coactivoDTO = iRFachadaCoactivo.consultarCoactivo(detallePago.getNumeroObligacion());

        if (coactivoDTO != null) {
            if (coactivoDTO.getObligacionCoactivos() != null && !coactivoDTO.getObligacionCoactivos().isEmpty()) {

                BigDecimal suma = new BigDecimal(0);

                for (ObligacionCoactivoDTO obligacionCoactivoDTO : coactivoDTO.getObligacionCoactivos()) {
                    suma = suma.add(obligacionCoactivoDTO.getValorObligacion()
                            .add(obligacionCoactivoDTO.getValorCostasProcesales()));
                    suma = suma.add(calcularRecargoObligacion(obligacionCoactivoDTO.getNumeroObligacion()));
                }

                // valida si el valor de la obligación coincide con la suma de las obligaciones del coactivo más sus costas procesales más sus
                // respectivos recargos
                if (!suma.equals(detallePago.getValorObligacion())) {
                    agregarErrorConciliacionPago(detallePago,
                            EnumErrorConciliacionPago.NO_COINCIDE_VALOR_OBLIGACION_VALOR_CONCEPTO);
                }

                // valida que existan las carteras de las obligaciones
                for (ObligacionCoactivoDTO obligacionCoactivoDTO : coactivoDTO.getObligacionCoactivos()) {
                    CarteraDTO carteraDTO = new CarteraDTO();
                    carteraDTO.setNombre(obligacionCoactivoDTO.getNumeroObligacion());
                    carteraDTO.setTipoObligacion(new TipoObligacionDTO(EnumTipoObligacion.COMPARENDO.getValue()));
                    carteraDTO.setEstadoObligacion(new EstadoObligacionDTO(EnumEstadoObligacion.ACTIVO.getValue()));
                    carteraDTO = iRFachadaCartera.consultarCartera(carteraDTO);

                    if (carteraDTO == null) {
                        agregarErrorConciliacionPago(detallePago,
                                EnumErrorConciliacionPago.NO_EXISTE_OBLIGACION_COACTIVO);
                        break;
                    }
                }

            }

        } else {
            agregarErrorConciliacionPago(detallePago, EnumErrorConciliacionPago.NO_EXISTE_COACTIVO);
        }

    }

    private BigDecimal calcularRecargoObligacion(String numeroObligacion) {
        StringBuilder sql = new StringBuilder();
        sql.append("declare ");
        sql.append("@liquidacion   float ");
        sql.append("exec dbo.sp_LiquidacionAxis ");
        sql.append("@factura = :factura, ");
        sql.append("@liquidacion = @liquidacion OUTPUT ");
        sql.append("select @liquidacion");

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("factura", numeroObligacion);

        Object resultado = query.getSingleResult();
        if (resultado == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf((Double) resultado);
    }
}
