package co.com.datatools.c2.adaptador.comparendo;

import java.math.BigDecimal;
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
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.cartera.ILCarteraComparendo;
import co.com.datatools.c2.adaptador.dto.CarteraComparendoDTO;
import co.com.datatools.c2.adaptador.dto.RegistroCarteraComparendoDTO;
import co.com.datatools.c2.adaptador.dto.RegistroNotaCarteraDTO;
import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.IngresoNotaCarteraDTO;
import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;
import co.com.datatools.c2.dto.cartera.SaldoCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoSaldoDTO;
import co.com.datatools.c2.dto.common.cartera.CambioEstadoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SaldoComparendoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoCartera;
import co.com.datatools.c2.entidades.EstadoObligacion;
import co.com.datatools.c2.enumeracion.EnumClaseInteres;
import co.com.datatools.c2.enumeracion.EnumPais;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumOpcConsultaCarteraComp;
import co.com.datatools.c2.enumeraciones.EnumTipoConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoSaldo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.helpers.cartera.EstadoObligacionHelper;
import co.com.datatools.c2.negocio.interfaces.ILComparendo;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.date.UtilFecha;

/**
 * Clase que se encarga de implementar el adpatador entre los modulos de cartera y comparendos
 */
@Stateless(name = "AdaptadorCarteraComparendoEJB")
@LocalBean
public class AdaptadorCarteraComparendoEJB implements ILCarteraComparendo {

    private final static Logger logger = Logger.getLogger(AdaptadorCarteraComparendoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRCarteraContable iRCarteraContable;
    @EJB
    private IRPersona iRPersona;
    @EJB
    private ILComparendo comparendoEJB;
    @EJB
    private IRSeguridadCirculemos seguridadCirculemosEJB;

    /**
     * Default constructor.
     */
    public AdaptadorCarteraComparendoEJB() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void activarCarteraComparendo(long idObligacion, Date fechaActivacion) throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::activarCarteraComparendo(long, Date)"));
        CambioEstadoCarteraDTO cambioEstadoCarteraDTO = new CambioEstadoCarteraDTO();
        cambioEstadoCarteraDTO.setActividadCartera(EnumActividadCartera.ACTIVAR_OBLIGACION_CARTERA);
        cambioEstadoCarteraDTO.setEstadoObligacion(EnumEstadoObligacion.ACTIVO);
        cambioEstadoCarteraDTO.setIdCartera(idObligacion);
        cambioEstadoCarteraDTO.setFechaCambio(fechaActivacion);
        iRCarteraContable.actualizarEstadoCartera(cambioEstadoCarteraDTO);
    }

    @Override
    public long registrarCarteraComparendo(RegistroCarteraComparendoDTO registroCarteraComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName()
                .concat("::registrarCarteraComparendo(RegistroCarteraComparendoDTO)"));
        RegistroCarteraDTO registroCarteraDTO = new RegistroCarteraDTO();

        // Consultar la persona
        PersonaDTO personaDTO = iRPersona.consultarPersona(registroCarteraComparendoDTO.getCodigoOrganismoTransito(),
                registroCarteraComparendoDTO.getIdTipoDocumento(), registroCarteraComparendoDTO.getNumeroDocumento());
        registroCarteraDTO.setIdPersona(personaDTO.getId());

        // Fecha Obligacion
        registroCarteraDTO.setFechaObligacion(registroCarteraComparendoDTO.getFechaImposicion());
        // Origen
        registroCarteraDTO.setIdOrigen(registroCarteraComparendoDTO.getOrigenObligacion());
        // Tipo Obligacion
        registroCarteraDTO.setIdTipoObligacion(EnumTipoObligacion.COMPARENDO.getValue());
        // Referencia Obligacion
        registroCarteraDTO.setReferenciaObligacion(registroCarteraComparendoDTO.getNumeroComparendo());
        // Tarifa infraccion
        registroCarteraDTO.setValor(registroCarteraComparendoDTO.getTarifaLiquidacion().getValorLiquidado());

        Long idCartera = iRCarteraContable.registrarCartera(registroCarteraDTO);

        // Registramos el comparendo cartera
        ComparendoCartera comparendoCartera = new ComparendoCartera();
        comparendoCartera.setIdCartera(idCartera);
        Comparendo comparendo = new Comparendo();
        comparendo.setCicomparendo(registroCarteraComparendoDTO.getIdComparendo());
        comparendoCartera.setComparendo(comparendo);

        em.persist(comparendoCartera);
        return idCartera;
    }

    @Override
    public CarteraComparendoDTO consultarCartera(long id, EnumOpcConsultaCarteraComp... opciones)
            throws CirculemosNegocioException {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::consultarCartera(long)"));
        CarteraComparendoDTO carteraComparendoDTO = null;
        CarteraDTO cartera = iRCarteraContable.consultarCartera(id);
        if (cartera != null) {
            carteraComparendoDTO = new CarteraComparendoDTO();
            EnumCarteraComparendo estado = null;
            if (EnumEstadoObligacion.ACTIVO.getValue() == cartera.getEstadoObligacion().getId()) {
                estado = EnumCarteraComparendo.OBLIGACION_ACTIVO;
            } else if (EnumEstadoObligacion.PENDIENTE_POR_ACTIVAR.getValue() == cartera.getEstadoObligacion().getId()) {
                estado = EnumCarteraComparendo.OBLIGACION_PENDIENTE_ACTIVAR;
            }
            carteraComparendoDTO.setEstado(estado);
            carteraComparendoDTO.setIdTipoObligacion(cartera.getTipoObligacion().getId());
            carteraComparendoDTO.setNomTipoObligacion(cartera.getTipoObligacion().getNombre());
            carteraComparendoDTO.setId(cartera.getId());
            final int MAX_TIPO_SALDO = 2;
            Map<EnumTipoSaldo, SaldoCarteraDTO> saldos = new HashMap<EnumTipoSaldo, SaldoCarteraDTO>(MAX_TIPO_SALDO);

            // Se utiliza la lista de saldos (SaldoDTO) y suma los valores donde el tipo de saldo sea saldo al capital
            // if (cartera.getSaldoCarteras() != null) {
            // for (SaldoCarteraDTO saldo : cartera.getSaldoCarteras()) {
            // EnumTipoSaldo tipoSaldo = Utilidades.buscarElemEnum(EnumTipoSaldo.class,
            // saldo.getTipoSaldo().getId());
            // if (!saldos.containsKey(tipoSaldo)) {
            // saldos.put(tipoSaldo, saldo);
            // }
            // if (saldos.size() == MAX_TIPO_SALDO) {
            // break;
            // }
            // }
            // }
            // Dixon Alvarez 28/07/2016
            // Se modifica el modo de obtener el saldo capital, se obtiene directamente de la cartera
            carteraComparendoDTO.setValorSaldoCapital(cartera.getSaldoCapital());
            carteraComparendoDTO.setValorSaldoCostasProcesales(cartera.getSaldoCostasProcesales());
            // carteraComparendoDTO.setValorSaldoCapital(saldos.get(EnumTipoSaldo.CAPITAL) != null ? saldos.get(
            // EnumTipoSaldo.CAPITAL).getSaldo() : BigDecimal.ZERO);

            // Se obtiene de ser necesario el saldo de los intereses
            if (opciones != null) {
                for (EnumOpcConsultaCarteraComp enumOpcConsultaCarteraComp : opciones) {
                    switch (enumOpcConsultaCarteraComp) {
                    case CONSULTA_SALDO_INTERES:
                        SaldoCarteraDTO saldoInteres = saldos.get(EnumTipoSaldo.INTERES);
                        if (saldoInteres != null
                                && saldoInteres.getFechaRegistro().compareTo(UtilFecha.currentZeroTimeDate()) == 0) {
                            carteraComparendoDTO.setValorSaldoInteres(saldoInteres.getSaldo());
                        } else {
                            // Se consulta sobre que pais se lleva a cabo el calculo de los intereses.

                            EnumPais pais = Utilidades.buscarElemEnum(EnumPais.class,
                                    seguridadCirculemosEJB.obtenerPais().getId());
                            switch (pais) {
                            case ECUADOR:
                                // TODO calcular intereses del comparendo con funcion desde AXIS.
                                carteraComparendoDTO.setValorSaldoInteres(BigDecimal.ZERO);
                                break;
                            default:
                                // Se consulta el comparendo con el id de la cartera y se verifica si existe para llevar a cabo el caluclo del saldo
                                // del
                                // comparendo.
                                ComparendoCartera comparendoCartera = consultarComparendoCartera(cartera.getId());
                                if (comparendoCartera != null) {
                                    // Se calcula los intereses al dia de hoy.
                                    SaldoComparendoDTO saldoComparendoDTO = comparendoEJB.calcularSaldoComparendo(
                                            comparendoCartera.getComparendo().getOrdenComparendoNacional()
                                                    .getOrganismoTransito().getCodigoOrganismo(),
                                            comparendoCartera.getComparendo().getCicomparendo(),
                                            UtilFecha.currentZeroTimeDate());
                                    if (saldoComparendoDTO != null) {
                                        carteraComparendoDTO.setValorSaldoInteres(saldoComparendoDTO.getSaldoInteres());
                                    }
                                }
                                break;
                            }

                        }
                        break;
                    }
                }
            }

        }
        return carteraComparendoDTO;
    }

    /**
     * Consulta el comparendo desde una determinada cartera
     * 
     * @param idCartera
     *            identificador de la cartera
     * @return comparendo cartera de la cartera ingresada, null si no existe una cartera asociada a un comparendo con el id ingresado
     * @author luis.forero (2016-02-29)
     */
    private ComparendoCartera consultarComparendoCartera(long idCartera) {
        TypedQuery<ComparendoCartera> queryCompCart = em.createNamedQuery(ComparendoCartera.SQ_FIND_BY_CARTERA,
                ComparendoCartera.class);
        queryCompCart.setParameter("pIdCartera", idCartera);
        List<ComparendoCartera> resCompCart = queryCompCart.getResultList();
        if (resCompCart.isEmpty()) {
            return null;
        }
        return resCompCart.get(0);
    }

    @Override
    public void actualizarDeudorCartera(Long cicomparendo, Long idDeudor) {
        // Consulta la cartera por medio del comparendo y que sea de tipo comparendo en estado activo
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT cc FROM ComparendoCartera cc");
        jpql.append(" WHERE cc.comparendo.cicomparendo = :cicomparendo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Cicomparendo
        query.setParameter("cicomparendo", cicomparendo);

        @SuppressWarnings("unchecked")
        List<ComparendoCartera> comparendoCarteras = query.getResultList();
        if (comparendoCarteras != null && !comparendoCarteras.isEmpty()) {
            StringBuilder jpqlC = new StringBuilder();

            jpqlC.append("SELECT c FROM Cartera c");
            jpqlC.append(" WHERE c.id = :idCartera");

            Query queryC = em.createQuery(jpqlC.toString());

            /*
             * Parametros
             */
            // Cicomparendo
            queryC.setParameter("idCartera", comparendoCarteras.get(0).getIdCartera());

            @SuppressWarnings("unchecked")
            List<Cartera> carteras = queryC.getResultList();
            if (carteras != null && !carteras.isEmpty()) {
                Cartera cartera = carteras.get(0);
                cartera.setIdDeudor(idDeudor);
                em.merge(cartera);
            }
        }
    }

    @Override
    public void registrarNotaCartera(RegistroNotaCarteraDTO registroNotaCarteraDTO) {
        IngresoNotaCarteraDTO ingresoNotaCarteraDTO = new IngresoNotaCarteraDTO();
        // fecha obligacion
        ingresoNotaCarteraDTO.setFechaObligacion(registroNotaCarteraDTO.getFechaObligacion());
        // identificador cartera
        // Consulta la cartera por medio del comparendo y que sea de tipo comparendo en estado activo
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT cc FROM ComparendoCartera cc");
        jpql.append(" WHERE cc.comparendo.cicomparendo = :cicomparendo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Cicomparendo
        query.setParameter("cicomparendo", registroNotaCarteraDTO.getCicomparendo());

        @SuppressWarnings("unchecked")
        List<ComparendoCartera> comparendoCarteras = query.getResultList();
        if (comparendoCarteras != null && !comparendoCarteras.isEmpty()) {
            for (ComparendoCartera comparendoCartera : comparendoCarteras) {

                StringBuilder jpqlC = new StringBuilder();

                jpqlC.append("SELECT c FROM Cartera c");
                jpqlC.append(" WHERE c.id = :idCartera");

                Query queryC = em.createQuery(jpqlC.toString());

                /*
                 * Parametros
                 */
                // Cicomparendo
                queryC.setParameter("idCartera", comparendoCartera.getIdCartera());

                @SuppressWarnings("unchecked")
                List<Cartera> carteras = queryC.getResultList();
                for (Cartera cartera : carteras) {
                    if (cartera.getTipoObligacion().getId().equals(EnumTipoObligacion.COMPARENDO.getValue())
                            && cartera.getEstadoObligacion().getEstado()) {
                        ingresoNotaCarteraDTO.setIdCartera(cartera.getId());
                        break;
                    }
                }
            }
        }

        // Concepto cartera
        ingresoNotaCarteraDTO.setIdConceptoNota(registroNotaCarteraDTO.getEnumCarteraComparendo().getValue());
        // Deudor
        // Consultar la persona
        PersonaDTO personaDTO = iRPersona.consultarPersona(registroNotaCarteraDTO.getCodigoOrganismoTransito(),
                registroNotaCarteraDTO.getIdTipoDocumento(), registroNotaCarteraDTO.getNumeroDocumento());
        ingresoNotaCarteraDTO.setIdDeudor(personaDTO.getId());
        // Valor
        ingresoNotaCarteraDTO.setValor(registroNotaCarteraDTO.getValorConcepto());
        iRCarteraContable.registrarNotaCartera(ingresoNotaCarteraDTO);
    }

    @Override
    public BigDecimal calcularInteresesComparendo(Long cicomparendo, Date fechaGeneracionInteres, Date fechaCalculo)
            throws CirculemosNegocioException {

        logger.debug(AdaptadorCarteraComparendoEJB.class.getName()
                .concat("::calcularInteresesComparendo(Long, Date, Date)"));
        BigDecimal interes = null;
        ComparendoDTO comparendoDTO = null;
        List<ComparendoCarteraDTO> lComparendoCartera;
        ComparendoCarteraDTO comparendoCarteraDTO;
        CarteraComparendoDTO carteraComparendoDTO;

        if (cicomparendo != null) {
            comparendoDTO = comparendoEJB.consultarComparendo(cicomparendo);
        }
        if (comparendoDTO != null) {
            lComparendoCartera = comparendoDTO.getComparendoCarteraList();
            if (lComparendoCartera != null && !lComparendoCartera.isEmpty()) {
                comparendoCarteraDTO = lComparendoCartera.get(0);
                carteraComparendoDTO = consultarCartera(comparendoCarteraDTO.getIdCartera());
                if (carteraComparendoDTO != null) {
                    interes = iRCarteraContable.calcularIntereses(carteraComparendoDTO.getId(),
                            EnumClaseInteres.MORA_COMPARENDOS.getValue(), fechaGeneracionInteres, fechaCalculo);
                }
            }
        }
        return interes;
    }

    @Override
    public void anularCartera(Long idCartera) {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::anularCartera(Long)"));
        CarteraDTO carteraDTO = iRCarteraContable.consultarCartera(idCartera);
        EstadoObligacion estadoObligacion = em.find(EstadoObligacion.class, EnumEstadoObligacion.ANULADA.getValue());
        carteraDTO.setEstadoObligacion(EstadoObligacionHelper.toLevel0DTO(estadoObligacion));

        // Movimiento para anular intereses de cartera
        MovimientoCarteraDTO movimientoCarteraDTO = new MovimientoCarteraDTO();
        movimientoCarteraDTO.setSaldoCapital(carteraDTO.getSaldoCapital());
        movimientoCarteraDTO.setSaldoIntereses(BigDecimal.ZERO);
        movimientoCarteraDTO.setIdActividad(EnumActividadCartera.CITACION_ANULADA.getValue());

        MovimientosCarteraDTO movimientosCarteraDTO = new MovimientosCarteraDTO();
        movimientosCarteraDTO.setCartera(carteraDTO);

        TipoConceptoCarteraDTO tipoConceptoCarteraDTO = new TipoConceptoCarteraDTO();
        tipoConceptoCarteraDTO.setId(EnumTipoConceptoCartera.CREDITO.getCodigo());

        ConceptoCarteraDTO conceptoCarteraDTO = new ConceptoCarteraDTO();
        conceptoCarteraDTO.setTipoConceptoCartera(tipoConceptoCarteraDTO);
        conceptoCarteraDTO.setId(EnumConceptoCartera.ANULACION_POR_OPERATIVO.getValue());
        conceptoCarteraDTO.setCodigo(EnumConceptoCartera.ANULACION_POR_OPERATIVO.getCodigo());

        TipoSaldoDTO tipoSaldoDTO = new TipoSaldoDTO();
        tipoSaldoDTO.setId(EnumTipoSaldo.INTERES.getId());

        conceptoCarteraDTO.setTipoSaldo(tipoSaldoDTO);
        movimientosCarteraDTO.setConceptoCartera(conceptoCarteraDTO);
        movimientosCarteraDTO.setFechaCreacion(UtilFecha.buildCalendar().getTime());
        movimientosCarteraDTO.setFechaMovimiento(UtilFecha.buildCalendar().getTime());
        movimientosCarteraDTO.setLoginUsuario(seguridadCirculemosEJB.obtenerUsuarioDto().getLogin());
        movimientosCarteraDTO.setObservaciones(EnumConceptoCartera.ANULACION_POR_OPERATIVO.name());
        movimientosCarteraDTO.setValorMovimiento(carteraDTO.getSaldoInteres());// .add(carteraDTO.getSaldoCapital())
        movimientoCarteraDTO.setMovimientosCartera(movimientosCarteraDTO);

        iRCarteraContable.registrarMovimiento(movimientoCarteraDTO);

        // Movimiento para anular capital de cartera
        movimientoCarteraDTO.setSaldoCapital(BigDecimal.ZERO);
        movimientoCarteraDTO.setSaldoIntereses(BigDecimal.ZERO);
        movimientoCarteraDTO.setIdActividad(EnumActividadCartera.CITACION_ANULADA.getValue());

        conceptoCarteraDTO.setId(EnumConceptoCartera.ANULACION_POR_OPERATIVO_CAPITAL.getValue());
        conceptoCarteraDTO.setCodigo(EnumConceptoCartera.ANULACION_POR_OPERATIVO_CAPITAL.getCodigo());

        tipoSaldoDTO.setId(EnumTipoSaldo.CAPITAL.getId());
        conceptoCarteraDTO.setTipoSaldo(tipoSaldoDTO);

        movimientosCarteraDTO.setObservaciones(EnumConceptoCartera.ANULACION_POR_OPERATIVO_CAPITAL.name());
        movimientosCarteraDTO.setValorMovimiento(carteraDTO.getSaldoCapital());
        movimientoCarteraDTO.setMovimientosCartera(movimientosCarteraDTO);

        iRCarteraContable.registrarMovimiento(movimientoCarteraDTO);
    }

    @Override
    public void cambiarNombreCartera(Long idCartera, String nuevoNombre) {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::cambiarNombreCartera(Long, String)"));
        Cartera cartera = em.find(Cartera.class, idCartera);
        if (cartera != null) {
            cartera.setNombre(nuevoNombre);
            cartera.setFechaActualizacion(UtilFecha.buildCalendar().getTime());
            em.merge(cartera);
        }
    }

    @Override
    public BigDecimal consultarValorDeInfraccion(Long idCartera) {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName().concat("::consultarValorDeInfraccion(Long)"));

        SaldoCarteraDTO saldoCarteraDTO = new SaldoCarteraDTO();
        saldoCarteraDTO.setCartera(new CarteraDTO(idCartera));

        List<SaldoCarteraDTO> saldosCartera = iRCarteraContable.consultarSaldoCartera(saldoCarteraDTO);

        return saldosCartera.get(0).getSaldo();
    }

}