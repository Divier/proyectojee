package co.com.datatools.c2.negocio.ejb.cartera;

import static co.com.datatools.c2.util.Utilidades.safeList;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.IngresoNotaCarteraDTO;
import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;
import co.com.datatools.c2.dto.cartera.SaldoCarteraDTO;
import co.com.datatools.c2.dto.common.ConsultaTasaInteresDTO;
import co.com.datatools.c2.dto.common.ConsultaValorInteresesDTO;
import co.com.datatools.c2.dto.common.cartera.CambioEstadoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.entidades.ActividadCartera;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.ConceptoCartera;
import co.com.datatools.c2.entidades.EstadoObligacion;
import co.com.datatools.c2.entidades.MovimientosCartera;
import co.com.datatools.c2.entidades.SaldoCartera;
import co.com.datatools.c2.entidades.TipoObligacion;
import co.com.datatools.c2.entidades.TipoSaldo;
import co.com.datatools.c2.entidades.TrazabilidadCartera;
import co.com.datatools.c2.enumeracion.EnumPeriodoInteres;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoSaldo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.ErrorCartera;
import co.com.datatools.c2.negocio.helpers.cartera.CarteraHelper;
import co.com.datatools.c2.negocio.helpers.cartera.ConceptoCarteraHelper;
import co.com.datatools.c2.negocio.helpers.cartera.MovimientosCarteraHelper;
import co.com.datatools.c2.negocio.helpers.cartera.SaldoCarteraHelper;
import co.com.datatools.c2.negocio.helpers.extension.CarteraHelperExtend;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.cartera.ILCarteraContable;
import co.com.datatools.c2.negocio.interfaces.cartera.ILInteres;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class CarteraContableEJB
 */
@Stateless(name = "CarteraContableEJB")
@LocalBean
public class CarteraContableEJB implements IRCarteraContable, ILCarteraContable {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(CarteraContableEJB.class.getName());

    @EJB
    private IRSeguridadCirculemos usuarioAppEJB;
    @EJB
    private ILInteres iLInteres;

    public CarteraContableEJB() {

    }

    @Override
    public Long registrarMovimiento(MovimientoCarteraDTO movimiento) {
        logger.debug("CarteraContableEJB::registrarMovimiento");

        checkNotNull(movimiento, "Movimiento de cartera es obligatorio");

        final Long idCartera = checkNotNull(movimiento.getMovimientosCartera().getCartera().getId(),
                "Cartera de obligacion es obligatorio");
        final String codEstadoObligacion = checkNotNull(
                movimiento.getMovimientosCartera().getCartera().getEstadoObligacion().getCodigo(),
                "Estado de obligación es obligatorio");
        final Integer idActividad = checkNotNull(movimiento.getIdActividad(), "Actividad de cartera es obligatorio");
        final BigDecimal valorMovimiento = checkNotNull(movimiento.getMovimientosCartera().getValorMovimiento(),
                "Valor de movimiento es obligatorio");
        final Integer codConceptoCartera = checkNotNull(
                movimiento.getMovimientosCartera().getConceptoCartera().getCodigo(),
                "Concepto de cartera es obligatorio");
        final BigDecimal valorSaldoCapital = checkNotNull(movimiento.getSaldoCapital(),
                "Saldo de capital es obligatorio");
        final BigDecimal valorSaldoIntereses = checkNotNull(movimiento.getSaldoIntereses(),
                "Saldo de intereses es obligatorio");
        final Date fechaMovimiento = checkNotNull(movimiento.getMovimientosCartera().getFechaMovimiento(),
                "Fecha de movimiento es obligatorio");
        final Date fechaCreacion = checkNotNull(movimiento.getMovimientosCartera().getFechaCreacion(),
                "Fecha de movimiento es obligatorio");
        final String loginUsuario = checkNotNull(movimiento.getMovimientosCartera().getLoginUsuario(),
                "Usuario es obligatorio");

        BigDecimal valorSaldoCostas = movimiento.getSaldoCostasProcesales();
        if (valorSaldoCostas == null) {
            valorSaldoCostas = BigDecimal.ZERO;
        }
        Date ahora = UtilFecha.buildCalendar().getTime();

        checkArgument(valorMovimiento.signum() != -1, "Valor de movimiento debe ser positivo");
        checkArgument(valorSaldoCapital.signum() != -1, "Saldo de capital debe ser positivo");
        checkArgument(valorSaldoIntereses.signum() != -1, "Saldo de intereses debe ser positivo");
        checkArgument(valorSaldoCostas.signum() != -1, "Saldo de costas procesales debe ser positivo");
        checkArgument(UtilFecha.resetTime(fechaCreacion).compareTo(UtilFecha.resetTime(ahora)) == 0,
                "Fecha de creación debe ser hoy");
        checkArgument(
                UtilFecha.resetTime(fechaMovimiento).getTime().compareTo(UtilFecha.resetTime(ahora).getTime()) <= 0,
                "Fecha de movimiento no puede ser mayor a hoy");

        // Existe concepto cartera
        TypedQuery<ConceptoCartera> tq1 = em.createNamedQuery(ConceptoCartera.SQ_FIND_BY_COD, ConceptoCartera.class);
        tq1.setParameter("codigo", codConceptoCartera);
        List<ConceptoCartera> conceptos = safeList(tq1.getResultList());
        if (conceptos.isEmpty())
            throw new CirculemosRuntimeException("No se encuentra concepto [ codigoConcepto: {0} ]",
                    codConceptoCartera);
        movimiento.getMovimientosCartera().getConceptoCartera().setId(conceptos.get(0).getId());

        // Existe estado obligacion
        TypedQuery<EstadoObligacion> tq2 = em.createNamedQuery(EstadoObligacion.SQ_FIND_BY_COD, EstadoObligacion.class);
        tq2.setParameter("codigo", codEstadoObligacion);
        List<EstadoObligacion> estadosObligacion = safeList(tq2.getResultList());
        if (estadosObligacion.isEmpty())
            throw new CirculemosRuntimeException("No se encuentra estado obligacion [ codigoEstadoObligacion: {0} ]",
                    codEstadoObligacion);
        EstadoObligacion estadoObligacion = estadosObligacion.get(0);
        movimiento.getMovimientosCartera().getCartera().getEstadoObligacion().setId(estadoObligacion.getId());

        // Existe cartera
        Cartera cartera = em.find(Cartera.class, idCartera);
        if (cartera == null)
            throw new CirculemosRuntimeException("No se encuentra cartera [ idCartera: {0} ]", idCartera);

        // Existe actividad
        ActividadCartera actividadCartera = em.find(ActividadCartera.class, idActividad);
        if (actividadCartera == null)
            throw new CirculemosRuntimeException("No se encuentra actividad cartera [ idActividadCartera: {0} ]",
                    idActividad);

        // Registro movimiento
        MovimientosCartera movimientosCartera = MovimientosCarteraHelper
                .toLevel1Entity(movimiento.getMovimientosCartera(), null);
        em.persist(movimientosCartera);

        logger.debugv("Registro movimiento cartera [ idCartera:{0}, valor:{1}, idMovimientoCartera:{2} ]",
                cartera.getId(), valorMovimiento, movimientosCartera.getId());

        // Registrar saldo capital/interes
        SaldoCartera saldoCapital = new SaldoCartera();
        saldoCapital.setFechaCalculo(movimientosCartera.getFechaMovimiento());
        saldoCapital.setFechaRegistro(ahora);
        saldoCapital.setCartera(cartera);
        saldoCapital.setTipoSaldo(new TipoSaldo());
        saldoCapital.getTipoSaldo().setId(EnumTipoSaldo.CAPITAL.getId());
        saldoCapital.setSaldo(valorSaldoCapital);
        saldoCapital.setMovimientoCartera(movimientosCartera);

        SaldoCartera saldoInteres = new SaldoCartera();
        saldoInteres.setFechaCalculo(movimientosCartera.getFechaMovimiento());
        saldoInteres.setFechaRegistro(ahora);
        saldoInteres.setCartera(cartera);
        saldoInteres.setTipoSaldo(new TipoSaldo());
        saldoInteres.getTipoSaldo().setId(EnumTipoSaldo.INTERES.getId());
        saldoInteres.setSaldo(valorSaldoIntereses);
        saldoInteres.setMovimientoCartera(movimientosCartera);

        SaldoCartera saldoCostas = new SaldoCartera();
        saldoCostas.setFechaCalculo(movimientosCartera.getFechaMovimiento());
        saldoCostas.setFechaRegistro(ahora);
        saldoCostas.setCartera(cartera);
        saldoCostas.setTipoSaldo(new TipoSaldo());
        saldoCostas.getTipoSaldo().setId(EnumTipoSaldo.COSTAS_PROCESALES.getId());
        saldoCostas.setSaldo(valorSaldoCostas);
        saldoCostas.setMovimientoCartera(movimientosCartera);

        em.persist(saldoCapital);
        em.persist(saldoInteres);
        em.persist(saldoCostas);

        logger.debugv("Registro saldo capital [ idCartera:{0}, valor:{1} ]", cartera.getId(), valorSaldoCapital);
        logger.debugv("Registro saldo intereses [ idCartera:{0}, valor:{1} ]", cartera.getId(), valorSaldoIntereses);
        logger.debugv("Registro saldo costas procesales [ idCartera:{0}, valor:{1} ]", cartera.getId(),
                valorSaldoCostas);

        // Si el estado es distinto cambia el estado de la cartera
        if (!cartera.getEstadoObligacion().getId().equals(estadoObligacion.getId())) {
            // Cambia el estado de la cartera
            cartera.setEstadoObligacion(estadoObligacion);
            cartera.setFechaActualizacion(ahora);
        }
        // Actualiza la cartera con los nuevos saldos
        cartera.setSaldoCapital(saldoCapital.getSaldo());
        cartera.setSaldoInteres(saldoInteres.getSaldo());
        cartera.setSaldoCostasProcesales(saldoCostas.getSaldo());
        em.merge(cartera);

        // Registro trazabilidad
        TrazabilidadCartera trazabilidadCartera = new TrazabilidadCartera();
        trazabilidadCartera.setEstadoObligacion(estadoObligacion);
        trazabilidadCartera.setFechaRegistro(ahora);
        trazabilidadCartera.setActividadCartera(actividadCartera);
        trazabilidadCartera.setCartera(cartera);
        trazabilidadCartera.setLoginUsuario(loginUsuario);
        em.persist(trazabilidadCartera);

        logger.debugv("Registro trazabilidad cartera [ idCartera:{0}, idActividad:{1}, idTrazabilidadCartera:{2} ]",
                cartera.getId(), idActividad, trazabilidadCartera.getId());

        return movimientosCartera.getId();
    }

    @Override
    public Long registrarCartera(RegistroCarteraDTO registroCartera) throws CirculemosNegocioException {
        logger.debug("CarteraContableEJB::registrarCartera");

        checkNotNull(registroCartera, "Registro de cartera es obligatorio");

        final Date fechaObligacion = checkNotNull(registroCartera.getFechaObligacion(),
                "Fecha de obligacion es obligatorio");
        final Integer idOrigen = checkNotNull(registroCartera.getIdOrigen(), "Origen es obligatorio");
        final Long idPersona = checkNotNull(registroCartera.getIdPersona(), "Persona es obligatorio");
        final Integer idTipoObligacion = checkNotNull(registroCartera.getIdTipoObligacion(),
                "Tipo de obligación es obligatorio");
        final String referenciaObligacion = checkNotNull(registroCartera.getReferenciaObligacion(),
                "Referencia de obligación es obligatorio");
        final BigDecimal valorObligacion = checkNotNull(registroCartera.getValor(),
                "Valor de obligación es obligatorio");

        Date ahora = UtilFecha.buildCalendar().getTime();

        checkArgument(
                UtilFecha.resetTime(fechaObligacion).getTime().compareTo(UtilFecha.resetTime(ahora).getTime()) <= 0,
                "Fecha de obligacion no puede ser mayor a hoy");
        checkArgument(valorObligacion.signum() != -1, "Valor de obligacion debe ser mayor que cero");

        // Existe tipo obligacion
        TipoObligacion tipoObligacion = em.find(TipoObligacion.class, idTipoObligacion);
        if (tipoObligacion == null)
            throw new CirculemosRuntimeException("No se encuentra tipo obligacion [ idTipoObligacion: {0} ]",
                    idTipoObligacion);

        // Existe obligacion cartera creada
        TypedQuery<Cartera> tq = em.createNamedQuery(Cartera.SQ_CARTERA_VIGENTE_REF_TIPO_OBL, Cartera.class);
        tq.setParameter("idTipoObligacion", idTipoObligacion);
        tq.setParameter("referenciaObligacion", referenciaObligacion);
        tq.setParameter("idEstadoObligacionL", EnumEstadoObligacion.obtenerEstadosNoFinales());
        List<Cartera> carteraCreadaL = safeList(tq.getResultList());
        if (!carteraCreadaL.isEmpty())
            throw new CirculemosNegocioException(ErrorCartera.RegistrarCartera.CAR_003001);

        // Registrar cartera nueva
        Cartera cartera = new Cartera();
        cartera.setFechaActualizacion(ahora);
        cartera.setFechaCreacion(fechaObligacion);
        cartera.setFechaRegistro(ahora);
        cartera.setIdDeudor(idPersona);
        cartera.setNombre(referenciaObligacion);
        cartera.setOrigen(idOrigen);
        cartera.setTipoObligacion(tipoObligacion);
        cartera.setEstadoObligacion(
                em.find(EstadoObligacion.class, EnumEstadoObligacion.PENDIENTE_POR_ACTIVAR.getValue()));

        em.persist(cartera);

        logger.debugv("Registro cartera nueva [ idCartera:{0}, referenciaObligacion:{1} ]", cartera.getId(),
                referenciaObligacion);

        // Registrar movimiento de cartera (registra trazabilidad)
        MovimientosCarteraDTO movimientosCartera = new MovimientosCarteraDTO();
        movimientosCartera.setCartera(CarteraHelper.toLevel1DTO(cartera));
        movimientosCartera.setFechaCreacion(ahora);
        movimientosCartera.setFechaMovimiento(fechaObligacion);
        movimientosCartera.setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getLogin());
        movimientosCartera.setObservaciones("REGISTRO CARTERA NUEVA");
        movimientosCartera.setValorMovimiento(valorObligacion);
        movimientosCartera.setConceptoCartera(ConceptoCarteraHelper
                .toLevel0DTO(em.find(ConceptoCartera.class, EnumConceptoCartera.REGISTRO_CARTERA.getId())));

        MovimientoCarteraDTO movimiento = new MovimientoCarteraDTO();
        movimiento.setIdActividad(EnumActividadCartera.REGISTRAR_OBLIGACION_CARTERA.getValue());
        movimiento.setMovimientosCartera(movimientosCartera);
        movimiento.setSaldoCapital(valorObligacion);
        movimiento.setSaldoIntereses(BigDecimal.ZERO);
        movimiento.setSaldoCostasProcesales(BigDecimal.ZERO);

        registrarMovimiento(movimiento);

        return cartera.getId();
    }

    @Override
    public void actualizarEstadoCartera(CambioEstadoCarteraDTO cambioEstadoCartera) throws CirculemosNegocioException {
        logger.debug("CarteraContableEJB::actualizarEstadoCartera");

        checkNotNull(cambioEstadoCartera, "DTO cambio estado de cartera es obligatorio");

        final Date fechaCambio = checkNotNull(cambioEstadoCartera.getFechaCambio(), "Fecha de cambio es obligatorio");
        final Long idCartera = checkNotNull(cambioEstadoCartera.getIdCartera(), "Número de cartera es obligatorio");
        final EnumEstadoObligacion enumEstadoObligacion = checkNotNull(cambioEstadoCartera.getEstadoObligacion(),
                "Estado de obligacion es obligatorio");
        final EnumActividadCartera enumActividadCartera = checkNotNull(cambioEstadoCartera.getActividadCartera(),
                "Activdad de cartera es obligatorio");

        Date ahora = UtilFecha.buildCalendar().getTime();

        checkArgument(UtilFecha.resetTime(fechaCambio).compareTo(UtilFecha.resetTime(ahora)) <= 0,
                "Fecha de cambio no debe ser mayor a hoy");

        // Existe cartera
        GenericDao<Cartera> carDAO = new GenericDao<>(Cartera.class, em);
        Cartera cartera = carDAO.findUniqueByAttribute("id", idCartera);
        if (cartera == null)
            throw new CirculemosRuntimeException("No se encuentra cartera [ idCartera: {0} ]", idCartera);

        // Existe estado de obligacion
        EstadoObligacion estadoObligacion = em.find(EstadoObligacion.class, enumEstadoObligacion.getValue());
        if (estadoObligacion == null)
            throw new CirculemosRuntimeException("No se encuentra estado obligacion [ idEstadoObligacion: {0} ]",
                    enumEstadoObligacion.getValue());

        // Existe actividad de cartera
        ActividadCartera actividadCartera = em.find(ActividadCartera.class, enumActividadCartera.getValue());
        if (actividadCartera == null)
            throw new CirculemosRuntimeException("No se encuentra actividad cartera [ idActividadCartera: {0} ]",
                    enumActividadCartera.getValue());

        // Estado de obligacion actual es diferente a nuevo estado
        if (estadoObligacion.getId().equals(cartera.getEstadoObligacion().getId()))
            throw new CirculemosNegocioException(ErrorCartera.CambiarEstadoCartera.CAR_031001);

        // Estado de obligacion nuevo es activo (activar cartera)
        if (estadoObligacion.getId().equals(EnumEstadoObligacion.ACTIVO.getValue()))
            cartera.setFechaInicio(fechaCambio); // Fecha de activacion

        cartera.setEstadoObligacion(estadoObligacion);
        cartera.setFechaActualizacion(fechaCambio);

        // Registro estado de cartera
        carDAO.saveOrUpdate(cartera);

        logger.debugv(
                "Cambio estado cartera [ idCartera:{0}, referenciaObligacion:{1}, idEstadoObligacion:{2}, "
                        + "fechaActivacion:{3} ]",
                cartera.getId(), cartera.getNombre(), estadoObligacion.getId(), fechaCambio);

        // Registro trazabilidad
        TrazabilidadCartera trazabilidadCartera = new TrazabilidadCartera();
        trazabilidadCartera.setEstadoObligacion(estadoObligacion);
        trazabilidadCartera.setFechaRegistro(ahora);
        trazabilidadCartera.setActividadCartera(actividadCartera);
        trazabilidadCartera.setCartera(cartera);
        trazabilidadCartera.setLoginUsuario(usuarioAppEJB.obtenerUsuarioDto().getUsuario().getLogin());

        em.persist(trazabilidadCartera);

        logger.debugv("Registro trazabilidad cartera [ idCartera:{0}, idActividad:{1}, idTrazabilidadCartera:{2} ]",
                cartera.getId(), actividadCartera.getId(), trazabilidadCartera.getId());
    }

    @Override
    public BigDecimal consultarValorIntereses(ConsultaValorInteresesDTO consultaValorInteresesDTO)
            throws CirculemosNegocioException {

        logger.debug("CarteraContableEJB::consultarValorIntereses");
        BigDecimal valorIntereses = new BigDecimal(BigDecimal.ZERO.intValue());
        if (consultaValorInteresesDTO != null && consultaValorInteresesDTO.getFechaInicial() != null
                && consultaValorInteresesDTO.getFechaFinal() != null
                && consultaValorInteresesDTO.getValSujetoAplicacion() != null) {
            valorIntereses = recorrerPeriodoIntereses(consultaValorInteresesDTO);
            valorIntereses = valorIntereses
                    .round(new MathContext(String.valueOf(valorIntereses.intValue()).length(), RoundingMode.HALF_UP));
        }
        return valorIntereses;
    }

    private BigDecimal recorrerPeriodoIntereses(ConsultaValorInteresesDTO consultaValorInteresesDTO)
            throws CirculemosNegocioException {

        Calendar fechaAplicacionDesde = Calendar.getInstance();
        fechaAplicacionDesde.setTime(consultaValorInteresesDTO.getFechaInicial());
        Calendar fechaAplicacionHasta = Calendar.getInstance();
        fechaAplicacionHasta.setTime(consultaValorInteresesDTO.getFechaFinal());

        // NO DEBE ser mayor a hoy.
        // Se compara que la Fecha inicio sea MENOR a la Fecha actual
        if (UtilFecha.resetTime(consultaValorInteresesDTO.getFechaInicial())
                .compareTo(UtilFecha.resetTime(UtilFecha.buildCalendar().getTime())) > 0) {
            throw new CirculemosNegocioException(ErrorCartera.LiquidarIntereses.CAR_032002);
        }

        // NO puede ser menor a "Fecha inicial para generar interes".
        // Se compara que la Fecha final sea MENOR a la Fecha inicial
        if (UtilFecha.resetTime(consultaValorInteresesDTO.getFechaFinal())
                .compareTo(UtilFecha.resetTime(consultaValorInteresesDTO.getFechaInicial())) < 0) {
            throw new CirculemosNegocioException(ErrorCartera.LiquidarIntereses.CAR_032003);
        }
        ConsultaTasaInteresDTO consultaTasaInteresDTO;
        consultaTasaInteresDTO = new ConsultaTasaInteresDTO();
        BigDecimal valorIntereses;
        valorIntereses = new BigDecimal(BigDecimal.ZERO.intValue());
        while (fechaAplicacionDesde.compareTo(fechaAplicacionHasta) <= 0) {
            consultaTasaInteresDTO.setFechaAplicacion(fechaAplicacionDesde.getTime());
            consultaTasaInteresDTO.setClaseInteres(consultaValorInteresesDTO.getClaseInteres());
            consultaTasaInteresDTO.setPeriodoInteres(consultaValorInteresesDTO.getPeriodoInteres());
            InteresDTO interesDTO = iLInteres.consultarTasasInteres(consultaTasaInteresDTO);
            if (interesDTO == null) {
                throw new CirculemosNegocioException(ErrorCartera.LiquidarIntereses.CAR_032001);
            } else if (interesDTO.getPorcentajeInteresDiario().compareTo(BigDecimal.ZERO) >= 0) {
                valorIntereses = valorIntereses.add(consultaValorInteresesDTO.getValSujetoAplicacion()
                        .multiply(interesDTO.getPorcentajeInteresDiario()));
            }
            fechaAplicacionDesde.add(Calendar.DAY_OF_YEAR, 1);
        }
        return valorIntereses;
    }

    @Override
    public CarteraDTO consultarCartera(long id) {
        logger.debug("CarteraContableEJB::consultarCartera(long)");
        CarteraDTO resultado = null;
        GenericDao<Cartera> interesDao = new GenericDao<>(Cartera.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT c FROM Cartera c");
        jpql.append(" LEFT JOIN FETCH c.estadoObligacion");
        jpql.append(" LEFT JOIN FETCH c.tipoObligacion");
        jpql.append(" LEFT JOIN FETCH c.saldoCarteras sc");
        jpql.append(" WHERE c.id = :id");
        jpql.append(" ORDER BY sc.fechaRegistro DESC");
        filtros.put("id", id);

        List<Cartera> resultadoConsulta = interesDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            resultado = CarteraHelperExtend.toLevel1DTOSaldoCartera(resultadoConsulta.get(0));
        }
        return resultado;
    }

    @Override
    public void registrarNotaCartera(IngresoNotaCarteraDTO ingresoNotaCarteraDTO) {
        // TODO Auto-generated method stub

    }

    @Override
    public BigDecimal calcularIntereses(Long idObligacion, Integer idClaseInteres, Date fechaInicioInteres,
            Date fechaCalculo) throws CirculemosNegocioException {

        logger.debug(CarteraContableEJB.class.getName().concat("::calcularIntereses(Long, Integer, Date, Date)"));
        BigDecimal interes = null;
        CarteraDTO carteraDTO;
        ConsultaValorInteresesDTO consultaValorInteresesDTO;

        if (fechaCalculo == null) {
            Date fechaSistema = UtilFecha.buildCalendar().getTime();
            fechaCalculo = fechaSistema;
        }

        if (idObligacion != null && idClaseInteres != null && fechaInicioInteres != null) {
            consultaValorInteresesDTO = new ConsultaValorInteresesDTO();
            consultaValorInteresesDTO.setClaseInteres(idClaseInteres);
            consultaValorInteresesDTO.setFechaInicial(fechaInicioInteres);
            consultaValorInteresesDTO.setFechaFinal(fechaCalculo);
            consultaValorInteresesDTO.setPeriodoInteres(EnumPeriodoInteres.ANUAL.getValue());
            carteraDTO = consultarCartera(idObligacion);

            if (carteraDTO != null) {
                registrarMovimientoSaldos(carteraDTO, consultaValorInteresesDTO);
            }
            interes = consultarValorIntereses(consultaValorInteresesDTO);
        }
        return interes;
    }

    private void registrarMovimientoSaldos(CarteraDTO carteraDTO, ConsultaValorInteresesDTO consultaValorInteresesDTO)
            throws CirculemosNegocioException {

        BigDecimal ultimoSaldoCapital;
        Boolean registrarNuevoSaldo = Boolean.FALSE;
        List<SaldoCarteraDTO> lSaldosCapital = new ArrayList<>();
        List<SaldoCarteraDTO> lSaldoCarteraDTO = new ArrayList<>();
        if (carteraDTO != null) {
            lSaldoCarteraDTO = carteraDTO.getSaldoCarteras();
            registrarNuevoSaldo = Boolean.TRUE;
        }

        /*
         * Se recorren los saldos de tipo INTERES de la cartera, para determinar si ya existe un registro de interes mayor o igual a la fecha de
         * generacion(inicio intereses)
         */
        for (SaldoCarteraDTO saldoCarteraDTO : lSaldoCarteraDTO) {
            if (saldoCarteraDTO.getTipoSaldo().getId().equals(EnumTipoSaldo.INTERES.getValue())
                    && saldoCarteraDTO.getFechaCalculo().compareTo(consultaValorInteresesDTO.getFechaInicial()) >= 0) {
                registrarNuevoSaldo = Boolean.FALSE;
            }
            if (saldoCarteraDTO.getTipoSaldo().getId().equals(EnumTipoSaldo.CAPITAL.getValue())) {
                lSaldosCapital.add(saldoCarteraDTO);
            }
        }

        ultimoSaldoCapital = obtenerUltimoSaldoCapital(lSaldosCapital);

        // Se registran los saldos capital e interes, si no existe un registro de interes mayor o igual a la fecha de generacion(inicio intereses)
        if (registrarNuevoSaldo) {

            final Cartera cartera = CarteraHelper.toLevel0Entity(carteraDTO, null);
            ConsultaValorInteresesDTO cConsultaValorInteresesDTO = new ConsultaValorInteresesDTO();
            cConsultaValorInteresesDTO.setClaseInteres(consultaValorInteresesDTO.getClaseInteres());
            cConsultaValorInteresesDTO.setFechaInicial(consultaValorInteresesDTO.getFechaInicial());
            cConsultaValorInteresesDTO.setFechaFinal(consultaValorInteresesDTO.getFechaInicial());
            cConsultaValorInteresesDTO.setPeriodoInteres(consultaValorInteresesDTO.getPeriodoInteres());
            cConsultaValorInteresesDTO.setValSujetoAplicacion(ultimoSaldoCapital);

            Date fechaActual = UtilFecha.buildCalendar().getTime();
            SaldoCartera saldoCarteraCapital = new SaldoCartera();
            saldoCarteraCapital.setFechaCalculo(fechaActual);
            saldoCarteraCapital.setFechaRegistro(fechaActual);
            saldoCarteraCapital.setCartera(cartera);
            saldoCarteraCapital.setTipoSaldo(new TipoSaldo());
            saldoCarteraCapital.getTipoSaldo().setId(EnumTipoSaldo.CAPITAL.getId());
            saldoCarteraCapital.setSaldo(ultimoSaldoCapital);

            SaldoCartera saldoCarteraInteres = new SaldoCartera();
            saldoCarteraInteres.setFechaCalculo(fechaActual);
            saldoCarteraInteres.setFechaRegistro(fechaActual);
            saldoCarteraInteres.setCartera(cartera);
            saldoCarteraInteres.setTipoSaldo(new TipoSaldo());
            saldoCarteraInteres.getTipoSaldo().setId(EnumTipoSaldo.INTERES.getId());
            saldoCarteraInteres.setSaldo(consultarValorIntereses(cConsultaValorInteresesDTO));

            em.persist(saldoCarteraCapital);
            em.persist(saldoCarteraInteres);
        }
        consultaValorInteresesDTO.setValSujetoAplicacion(ultimoSaldoCapital);
    }

    private BigDecimal obtenerUltimoSaldoCapital(List<SaldoCarteraDTO> lSaldosCapital) {
        BigDecimal ultimoSaldoCapital = null;
        if (lSaldosCapital != null && !lSaldosCapital.isEmpty()) {
            // Se ordena la lista de saldos de capital para obtener el ultimo saldo capital calculado
            Collections.sort(lSaldosCapital, new Comparator<SaldoCarteraDTO>() {
                @Override
                public int compare(SaldoCarteraDTO sc1, SaldoCarteraDTO sc2) {
                    return sc1.getFechaCalculo().compareTo(sc2.getFechaCalculo());
                }
            });
            ultimoSaldoCapital = lSaldosCapital.get(lSaldosCapital.size() - 1).getSaldo();
        }
        return ultimoSaldoCapital;
    }

    @Override
    public List<ConceptoCarteraDTO> consultarConceptoCartera(ConceptoCarteraDTO filtros) {
        List<ConceptoCarteraDTO> listConcCartDTO = new ArrayList<ConceptoCarteraDTO>();
        if (filtros == null) {
            final StringBuilder sql = new StringBuilder();
            sql.append("SELECT cc FROM ConceptoCartera AS cc ORDER BY cc.nombre");
            final TypedQuery<ConceptoCartera> query = em.createQuery(sql.toString(), ConceptoCartera.class);
            final List<ConceptoCartera> listConcCart = query.getResultList();
            if (null != listConcCart) {
                for (ConceptoCartera cc : listConcCart) {
                    ConceptoCarteraDTO ccDTO = ConceptoCarteraHelper.toLevel0DTO(cc);
                    listConcCartDTO.add(ccDTO);
                }
            }
        } else if (filtros.getId() != null) {
            final ConceptoCartera cc = (ConceptoCartera) em.find(ConceptoCartera.class, filtros.getId());
            ConceptoCarteraDTO ccDTO = ConceptoCarteraHelper.toLevel1DTO(cc);
            listConcCartDTO.add(ccDTO);

        } /** Bloque consulta creado por Luis Forero */
        else {
            /*
             * Se definen los filtros posibles del concepto.
             */
            final Map<String, Object> fil = new HashMap<String, Object>(0);

            final StringBuilder sql = new StringBuilder();
            sql.append("SELECT cc FROM ConceptoCartera AS cc");
            sql.append(" WHERE 1=1");
            if (filtros.getId() != null) {
                sql.append(" AND cc.id = :idConcepto");
                fil.put("idConcepto", filtros.getId());
            }
            if (filtros.getCodigo() != null) {
                sql.append(" AND cc.codigo = :codConcepto");
                fil.put("codConcepto", filtros.getCodigo());
            }
            if (StringUtils.isNotBlank(filtros.getNombre())) {
                sql.append(" AND UPPER(cc.nombre) LIKE :nom");
                fil.put("nom", "%" + filtros.getNombre().toUpperCase() + "%");
            }
            if (filtros.getTipoConceptoCartera() != null && filtros.getTipoConceptoCartera().getCodigo() != null) {
                sql.append(" AND cc.tipoConceptoCartera.codigo = :codTipCon");
                fil.put("codTipCon", filtros.getTipoConceptoCartera().getCodigo());
            }
            if (filtros.getEstado() != null) {
                sql.append(" AND cc.estado = :estado");
                fil.put("estado", filtros.getEstado());
            }

            sql.append(" ORDER BY cc.codigo");

            final TypedQuery<ConceptoCartera> query = em.createQuery(sql.toString(), ConceptoCartera.class);
            for (Map.Entry<String, Object> entry : fil.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            final List<ConceptoCartera> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                listConcCartDTO = ConceptoCarteraHelper.toListLevel1DTO(resultados);
            }
        }
        return listConcCartDTO;
    }

    @Override
    public CarteraDTO consultarCartera(CarteraDTO cartera) {
        logger.debug("CarteraContableEJB::consultarCartera(CarteraDTO)");
        CarteraDTO resultado = null;
        GenericDao<Cartera> interesDao = new GenericDao<>(Cartera.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT DISTINCT c FROM Cartera c");
        jpql.append(" LEFT JOIN FETCH c.estadoObligacion");
        jpql.append(" LEFT JOIN FETCH c.tipoObligacion");
        jpql.append(" LEFT JOIN FETCH c.saldoCarteras sc");
        jpql.append(" WHERE 1 = 1");
        if (StringUtils.isNotEmpty(cartera.getNombre())) {
            jpql.append(" AND c.nombre = :nombre");
            filtros.put("nombre", cartera.getNombre());
        }
        if (cartera.getTipoObligacion() != null) {
            jpql.append(" AND c.tipoObligacion.id = :tipoObligacion");
            filtros.put("tipoObligacion", cartera.getTipoObligacion().getId());
        }
        if (cartera.getEstadoObligacion() != null) {
            jpql.append(" AND c.estadoObligacion.id = :estadoObligacion");
            filtros.put("estadoObligacion", cartera.getEstadoObligacion().getId());
        }

        jpql.append(" ORDER BY sc.fechaRegistro DESC");

        List<Cartera> resultadoConsulta = interesDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            resultado = CarteraHelperExtend.toLevel1DTOSaldoCartera(resultadoConsulta.get(0));
        }
        return resultado;
    }

    @Override
    public List<SaldoCarteraDTO> consultarSaldoCartera(SaldoCarteraDTO saldoCartera) {
        logger.debug("CarteraContableEJB::consultarSaldoCartera(SaldoCarteraDTO)");

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT sc FROM SaldoCartera sc");
        jpql.append(" JOIN sc.cartera c");
        jpql.append(" WHERE c.id=:idCartera");
        jpql.append(" ORDER BY c.fechaRegistro ASC");

        filtros.put("idCartera", saldoCartera.getCartera().getId());

        GenericDao<SaldoCartera> query = new GenericDao<SaldoCartera>(SaldoCartera.class, em);
        List<SaldoCartera> result = query.buildAndExecuteQuery(jpql, filtros);

        return SaldoCarteraHelper.toListLevel0DTO(result);
    }

}