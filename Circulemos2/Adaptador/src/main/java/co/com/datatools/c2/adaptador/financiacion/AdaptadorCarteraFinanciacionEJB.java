package co.com.datatools.c2.adaptador.financiacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.cartera.IRCarteraFinanciacion;
import co.com.datatools.c2.adaptador.comparendo.AdaptadorCarteraComparendoEJB;
import co.com.datatools.c2.adaptador.dto.CarteraFinanciacionDTO;
import co.com.datatools.c2.adaptador.dto.RegistroCarteraFinanciacionDTO;
import co.com.datatools.c2.adaptador.dto.TasaInteresDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;
import co.com.datatools.c2.dto.cartera.SaldoCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoObligacionDTO;
import co.com.datatools.c2.dto.common.ConsultaTasaInteresDTO;
import co.com.datatools.c2.dto.common.cartera.CambioEstadoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.entidades.FinanciacionCartera;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoSaldo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.c2.negocio.interfaces.cartera.IRInteres;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.date.UtilFecha;

/**
 * Clase que se encarga de implementar el adpatador entre los modulos de cartera y financiaciones
 */
@Stateless(name = "AdaptadorCarteraFinanciacionEJB")
@LocalBean
public class AdaptadorCarteraFinanciacionEJB implements IRCarteraFinanciacion {

    private final static Logger logger = Logger.getLogger(AdaptadorCarteraFinanciacionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRCarteraContable iRCarteraContable;
    @EJB
    private IRPersona iRPersona;
    @EJB
    private IRInteres iRInteres;

    @EJB
    private IRSeguridadCirculemos irSeguridadCirculemos;

    /**
     * Default constructor.
     */
    public AdaptadorCarteraFinanciacionEJB() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void activarCarteraFinanciacion(long idObligacion, Date fechaActivacion) throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::activarCarteraFinanciacion(long, Date)"));
        cambiaEstadoCarteraFinanciacion(idObligacion, fechaActivacion, EnumActividadCartera.ACTIVAR_OBLIGACION_CARTERA,
                EnumEstadoObligacion.ACTIVO);
    }

    @Override
    public void financiarCarteraFinanciacion(long idObligacion, Date fechaActivacion) throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::activarCarteraFinanciacion(long, Date)"));
        cambiaEstadoCarteraFinanciacion(idObligacion, fechaActivacion, EnumActividadCartera.FINANCIA_OBLIGACION,
                EnumEstadoObligacion.FINANCIADO);
    }

    /**
     * Cambia el estado de una cartera
     * 
     * @param idObligacion
     * @param fechaActivacion
     * @param actividad
     * @param estado
     * @throws CirculemosNegocioException
     * @author julio.pinzon 2016-05-18
     */
    private void cambiaEstadoCarteraFinanciacion(long idObligacion, Date fechaActivacion,
            EnumActividadCartera actividad, EnumEstadoObligacion estado) throws CirculemosNegocioException {
        CambioEstadoCarteraDTO cambioEstadoCarteraDTO = new CambioEstadoCarteraDTO();
        cambioEstadoCarteraDTO.setActividadCartera(actividad);
        cambioEstadoCarteraDTO.setEstadoObligacion(estado);
        cambioEstadoCarteraDTO.setIdCartera(idObligacion);
        cambioEstadoCarteraDTO.setFechaCambio(fechaActivacion);
        iRCarteraContable.actualizarEstadoCartera(cambioEstadoCarteraDTO);
    }

    @Override
    public long registrarCarteraFinanciacion(RegistroCarteraFinanciacionDTO registroCarteraFinanciacionDTO)
            throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraFinanciacionEJB.class.getName().concat(
                "::registrarCarteraFinanciacion(RegistroCarteraFinanciacionDTO)"));
        RegistroCarteraDTO registroCarteraDTO = new RegistroCarteraDTO();

        // Deudor
        registroCarteraDTO.setIdPersona(registroCarteraFinanciacionDTO.getIdDeudor());

        // Fecha Obligacion
        registroCarteraDTO.setFechaObligacion(registroCarteraFinanciacionDTO.getFechaFinanciacion());
        // Origen
        registroCarteraDTO.setIdOrigen(registroCarteraFinanciacionDTO.getOrigenObligacion());
        // Tipo Obligacion
        registroCarteraDTO.setIdTipoObligacion(EnumTipoObligacion.FINANCIACION.getValue());
        // Referencia Obligacion
        registroCarteraDTO.setReferenciaObligacion(registroCarteraFinanciacionDTO.getNumeroFinanciacion());
        // Tarifa infraccion
        registroCarteraDTO.setValor(registroCarteraFinanciacionDTO.getValorFinanciacion());

        Long idCartera = iRCarteraContable.registrarCartera(registroCarteraDTO);

        // Registramos el financiacion cartera
        FinanciacionCartera financiacionCartera = new FinanciacionCartera();
        financiacionCartera.setIdCartera(idCartera);
        Financiacion financiacion = new Financiacion();
        financiacion.setId(registroCarteraFinanciacionDTO.getIdFinanciacion());
        financiacionCartera.setFinanciacion(financiacion);

        em.persist(financiacionCartera);
        return idCartera;
    }

    @Override
    public CarteraFinanciacionDTO consultarCartera(long id) throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraFinanciacionEJB.class.getName().concat("::consultarCartera(long)"));
        CarteraFinanciacionDTO carteraFinanciacionDTO = null;
        CarteraDTO cartera = iRCarteraContable.consultarCartera(id);
        if (cartera != null) {
            carteraFinanciacionDTO = procesarCartera(cartera);
        }
        return carteraFinanciacionDTO;
    }

    @Override
    public CarteraFinanciacionDTO consultarCartera(String nombre, Integer idTipoObligacion) {
        logger.debug(AdaptadorCarteraFinanciacionEJB.class.getName().concat("::consultarCartera(String, Integer)"));
        CarteraFinanciacionDTO carteraFinanciacionDTO = null;

        CarteraDTO cartera = new CarteraDTO();
        cartera.setNombre(nombre);
        cartera.setTipoObligacion(new TipoObligacionDTO(idTipoObligacion));
        cartera = iRCarteraContable.consultarCartera(cartera);

        if (cartera != null) {
            carteraFinanciacionDTO = procesarCartera(cartera);
        }
        return carteraFinanciacionDTO;
    }

    /**
     * Procesa el resultado para obtener el saldo
     * 
     * @param id
     * @param cartera
     * @throws CirculemosNegocioException
     */
    private CarteraFinanciacionDTO procesarCartera(CarteraDTO cartera) {
        logger.debug(AdaptadorCarteraFinanciacionEJB.class.getName().concat("::consultarCartera(long)"));
        CarteraFinanciacionDTO carteraFinanciacionDTO = new CarteraFinanciacionDTO();
        EnumCarteraFinanciacion estado = null;
        if (EnumEstadoObligacion.ACTIVO.getValue() == cartera.getEstadoObligacion().getId()) {
            estado = EnumCarteraFinanciacion.OBLIGACION_ACTIVO;
        } else if (EnumEstadoObligacion.PENDIENTE_POR_ACTIVAR.getValue() == cartera.getEstadoObligacion().getId()) {
            estado = EnumCarteraFinanciacion.OBLIGACION_PENDIENTE_ACTIVAR;
        } else if (EnumEstadoObligacion.PAGADO.getValue() == cartera.getEstadoObligacion().getId()) {
            estado = EnumCarteraFinanciacion.PAGADO;
        } else if (EnumEstadoObligacion.FINANCIADO.getValue() == cartera.getEstadoObligacion().getId()) {
            estado = EnumCarteraFinanciacion.FINANCIADO;
        } else if (EnumEstadoObligacion.ANULADA.getValue() == cartera.getEstadoObligacion().getId()) {
            estado = EnumCarteraFinanciacion.ANULADA;
        } else if (EnumEstadoObligacion.PREFINANCIADA.getValue() == cartera.getEstadoObligacion().getId()) {
            estado = EnumCarteraFinanciacion.PREFINANCIADA;
        } else if (EnumEstadoObligacion.PAGADO_FINANCIACION.getValue() == cartera.getEstadoObligacion().getId()) {
            estado = EnumCarteraFinanciacion.PAGADO_FINANCIACION;
        }
        carteraFinanciacionDTO.setEstado(estado);
        carteraFinanciacionDTO.setId(cartera.getId());
        final int MAX_TIPO_SALDO = 2;
        Map<EnumTipoSaldo, SaldoCarteraDTO> saldos = new HashMap<EnumTipoSaldo, SaldoCarteraDTO>(MAX_TIPO_SALDO);

        // Se utiliza la lista de saldos (SaldoDTO) y suma los valores donde el tipo de saldo sea saldo al capital
        if (cartera.getSaldoCarteras() != null) {
            for (SaldoCarteraDTO saldo : cartera.getSaldoCarteras()) {
                EnumTipoSaldo tipoSaldo = Utilidades.buscarElemEnum(EnumTipoSaldo.class, saldo.getTipoSaldo().getId());
                if (!saldos.containsKey(tipoSaldo)) {
                    saldos.put(tipoSaldo, saldo);
                }
                if (saldos.size() == MAX_TIPO_SALDO) {
                    break;
                }
            }
        }
        carteraFinanciacionDTO.setValorSaldoCapital(saldos.get(EnumTipoSaldo.CAPITAL) != null ? saldos.get(
                EnumTipoSaldo.CAPITAL).getSaldo() : BigDecimal.ZERO);
        carteraFinanciacionDTO.setValorSaldoInteres(saldos.get(EnumTipoSaldo.INTERES) != null ? saldos.get(
                EnumTipoSaldo.INTERES).getSaldo() : BigDecimal.ZERO);
        return carteraFinanciacionDTO;
    }

    @Override
    public TasaInteresDTO consultarTasaInteresVigente(int claseInteres, Date fecha) {
        ConsultaTasaInteresDTO consultaTasaInteresDTO = new ConsultaTasaInteresDTO();
        consultaTasaInteresDTO.setClaseInteres(claseInteres);
        consultaTasaInteresDTO.setFechaAplicacion(fecha);

        InteresDTO tasasInteres = iRInteres.consultarTasasInteres(consultaTasaInteresDTO);
        if (tasasInteres != null) {
            TasaInteresDTO tasaInteresDTO = new TasaInteresDTO();
            tasaInteresDTO.setPorcentajeInteresDiario(tasasInteres.getPorcentajeInteresDiario());
            tasaInteresDTO.setPorcentajeTasaInteres(tasasInteres.getPorcentajeTasaInteres());
            return tasaInteresDTO;
        }
        return null;
    }

    @Override
    public void registrarMovimientoCartera(FinanciacionDTO financiacionDTO) {
        List<ObligacionFinanciacionDTO> obligaciones = Utilidades.safeList(financiacionDTO
                .getObligacionesFinanciacion());
        for (ObligacionFinanciacionDTO obligacion : obligaciones) {

            // Movimiento para registrar el recargo de las obligaciones financiadas
            MovimientoCarteraDTO movimientoCarteraDTO = new MovimientoCarteraDTO();
            movimientoCarteraDTO.setSaldoCapital(obligacion.getValorObligacion());
            movimientoCarteraDTO.setSaldoIntereses(obligacion.getValorInteresMoratorios());
            movimientoCarteraDTO.setIdActividad(EnumActividadCartera.PREFINANCIAR_OBLIGACION.getValue());

            MovimientosCarteraDTO movimientosCarteraDTO = new MovimientosCarteraDTO();
            CarteraDTO carteraDTO = iRCarteraContable.consultarCartera(obligacion.getIdCartera());
            movimientosCarteraDTO.setCartera(carteraDTO);

            TipoConceptoCarteraDTO tipoConceptoCarteraDTO = new TipoConceptoCarteraDTO();
            tipoConceptoCarteraDTO.setId(EnumTipoConceptoCartera.DEBITO.getCodigo());

            ConceptoCarteraDTO conceptoCarteraDTO = new ConceptoCarteraDTO();
            conceptoCarteraDTO.setTipoConceptoCartera(tipoConceptoCarteraDTO);
            conceptoCarteraDTO.setId(EnumConceptoCartera.CREAR_INTERESES_PREFINANCIACION.getValue());
            conceptoCarteraDTO.setCodigo(EnumConceptoCartera.CREAR_INTERESES_PREFINANCIACION.getCodigo());

            movimientosCarteraDTO.setConceptoCartera(conceptoCarteraDTO);
            movimientosCarteraDTO.setFechaCreacion(UtilFecha.buildCalendar().getTime());
            movimientosCarteraDTO.setFechaMovimiento(UtilFecha.buildCalendar().getTime());
            movimientosCarteraDTO.setLoginUsuario(irSeguridadCirculemos.obtenerUsuarioDto().getLogin());
            movimientosCarteraDTO.setObservaciones(EnumConceptoCartera.CREAR_INTERESES_PREFINANCIACION.name());
            movimientosCarteraDTO.setValorMovimiento(obligacion.getValorInteresMoratorios());
            movimientoCarteraDTO.setMovimientosCartera(movimientosCarteraDTO);

            iRCarteraContable.registrarMovimiento(movimientoCarteraDTO);
        }

    }

    @Override
    public CarteraFinanciacionDTO consultarCarteraFinanciacion(String numeroFinanciacion) {
        logger.debug(AdaptadorCarteraFinanciacionEJB.class.getName().concat("::consultarCarteraFinanciacion(String)"));
        return consultarCartera(numeroFinanciacion, EnumTipoObligacion.FINANCIACION.getValue());
    }

    @Override
    public void preFinanciarCarteraFinanciacion(long idObligacion, Date fechaActivacion)
            throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::activarCarteraFinanciacion(long, Date)"));
        cambiaEstadoCarteraFinanciacion(idObligacion, fechaActivacion, EnumActividadCartera.PREFINANCIAR_OBLIGACION,
                EnumEstadoObligacion.PREFINANCIADA);
    }

    @Override
    public void actualizarEstadoAnteriorCartera(Long idProceso) throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::actualizarEstadoCartera(Long)"));

        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT ");
        consulta.append("cp.cicomparendo, ");
        consulta.append("cc.id_cartera ");
        consulta.append("FROM comparendo_proceso cp ");
        consulta.append("INNER JOIN comparendo_cartera cc ");
        consulta.append("ON cp.cicomparendo = cc.cicomparendo ");
        consulta.append("WHERE cp.id_proceso = :idProceso ");
        consulta.append("ORDER BY cp.cicomparendo ");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("idProceso", idProceso);
        @SuppressWarnings({ "unchecked" })
        List<Object[]> lsComparendos = Utilidades.safeList(query.getResultList());

        if (!lsComparendos.isEmpty()) {
            for (Object[] comparendo : lsComparendos) {
                BigInteger idCartera = (BigInteger) comparendo[1];
                CambioEstadoCarteraDTO cambioEstadoCartera = new CambioEstadoCarteraDTO();
                cambioEstadoCartera.setActividadCartera(EnumActividadCartera.FINANCIA_OBLIGACION);
                cambioEstadoCartera.setFechaCambio(UtilFecha.buildCalendar().getTime());
                cambioEstadoCartera.setIdCartera(idCartera.longValue());

                /* Se busca el estado anterior a prefinanciado de la obligacion */
                consulta = new StringBuilder();
                consulta.append("SELECT ");
                consulta.append("tc.codigo_estado_obligacion ");
                consulta.append("FROM trazabilidad_cartera tc ");
                consulta.append("WHERE tc.id_cartera = :idCartera ");
                consulta.append("ORDER BY tc.fecha_registro DESC ");

                query = em.createNativeQuery(consulta.toString());
                query.setParameter("idCartera", idCartera);
                @SuppressWarnings({ "unchecked" })
                List<Object> lsEstadosCartera = Utilidades.safeList(query.getResultList());

                if (!lsEstadosCartera.isEmpty()) {
                    Integer ulEstado = (Integer) lsEstadosCartera.get(0);
                    for (Object estado : lsEstadosCartera) {
                        Integer idEstadoCartera = (Integer) estado;
                        if (!idEstadoCartera.equals(ulEstado)) {
                            EnumEstadoObligacion enumEstadoObligacion = EnumEstadoObligacion
                                    .encontrarPorId(idEstadoCartera);
                            cambioEstadoCartera.setEstadoObligacion(enumEstadoObligacion);
                            iRCarteraContable.actualizarEstadoCartera(cambioEstadoCartera);
                            break;
                        }
                    }
                }
            }
        }
    }

}