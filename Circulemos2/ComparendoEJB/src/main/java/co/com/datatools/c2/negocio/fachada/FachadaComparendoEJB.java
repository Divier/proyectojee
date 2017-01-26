package co.com.datatools.c2.negocio.fachada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudComparendosDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendoSimit;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.helpers.extencion.ComparendoHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILAgente;
import co.com.datatools.c2.negocio.interfaces.ILComparendo;
import co.com.datatools.c2.negocio.interfaces.ILComparendoTercero;
import co.com.datatools.c2.negocio.interfaces.administracion.ILInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.ILNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.administracion.ILTarifaInfraccion;
import co.com.datatools.util.GenericDao;

@Stateless(name = "FachadaComparendoEJB")
@LocalBean
public class FachadaComparendoEJB implements IRFachadaComparendo {

    private static final Logger logger = Logger.getLogger(FachadaComparendoEJB.class.getName());

    @EJB
    private ILComparendo iLComparendo;
    @EJB
    private ILInfraccion infraccionEjb;
    @EJB
    private ILTarifaInfraccion iTarifaInfraccion;
    @EJB
    private ILComparendoTercero comparendoTerceroEJB;
    @EJB
    private ILAgente iLAgente;
    @EJB
    private ILNotificacionTerceros iLNotificacionTercero;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<ComparendoDTO> consultarComparendos(int codigoOrganismo, Long cicomparendo) {

        List<ComparendoDTO> lsComparendoDTO = null;

        if (cicomparendo == null) {
            GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();
            jpql.append("SELECT DISTINCT c FROM Comparendo c");
            jpql.append(" LEFT JOIN FETCH c.comparendoCarteraList cc");
            jpql.append(" LEFT JOIN FETCH c.infraccion");
            jpql.append(" WHERE");

            jpql.append(" c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
            filtros.put("codigoOrganismo", codigoOrganismo);

            List<Integer> estados = new ArrayList<Integer>();
            estados.add(EnumEstadoComparendo.REGISTRADO.getValue());
            estados.add(EnumEstadoComparendo.VIGENTE.getValue());
            jpql.append(" AND c.estadoComparendo.id IN :estados");
            filtros.put("estados", estados);

            jpql.append(
                    " AND (c.estadoComparendoSimit IS NULL OR c.estadoComparendoSimit.id = :estadoCompSimitCorregido )");
            filtros.put("estadoCompSimitCorregido", EnumEstadoComparendoSimit.CORREGIDO.getValue());

            // Realiza consulta de comparendos
            List<Comparendo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
            lsComparendoDTO = ComparendoHelperExtend.toListLevel1DTOExtComparendoCarteraProceso(resultadoConsulta);

        } else {
            ComparendoDTO comparendoDTO = iLComparendo.consultarComparendo(cicomparendo);
            if (comparendoDTO != null) {
                lsComparendoDTO = new ArrayList<ComparendoDTO>();
                lsComparendoDTO.add(comparendoDTO);
            }
        }
        return lsComparendoDTO;
    }

    @Override
    public ConfiguracionInfraccionDTO consultarConfiguracionInfraccion(String codigoAlfanumerico, Date fechaVigencia) {
        ConfiguracionInfraccionDTO configuracionInfraccionDTO = infraccionEjb.consultarInfraccion(codigoAlfanumerico,
                fechaVigencia);
        return configuracionInfraccionDTO;
    }

    @Override
    public BigDecimal consultarTarifaInfraccion(LiquidarTarifaInfraccionDTO datosInfraccion)
            throws CirculemosNegocioException {
        TarifaLiquidacionDTO tarifaLiquidacionDTO = iTarifaInfraccion.liquidarTarifaInfraccion(datosInfraccion);
        return tarifaLiquidacionDTO.getValorLiquidado();
    }

    @Override
    public void notificarComparendoSIMIT(Integer codigoOrganismo, Long cicomparendo, EnumAccionComparendo accion)
            throws CirculemosNegocioException {
        logger.debug(FachadaComparendoEJB.class.getName()
                .concat("::notificarComparendoSIMIT(Integer,Long,EnumAccionComparendo)"));
        iLComparendo.notificarComparendoSIMIT(codigoOrganismo, cicomparendo, accion);
    }

    @Override
    public void actualizarEstadoComparendo(CambioEstadoComparendoDTO cambioEstadoComparendo) {
        logger.debug(
                FachadaComparendoEJB.class.getName().concat("::actualizarEstadoComparendo(CambioEstadoComparendoDTO)"));
        iLComparendo.actualizarEstadoComparendo(cambioEstadoComparendo);
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaSolicitudComparendosDTO solicitarComparendosTerceros() {
        logger.debug(FachadaComparendoEJB.class.getName().concat("::solicitarComparendosTerceros()"));
        return comparendoTerceroEJB.solicitarComparendosTerceros();
    }

    @Override
    public ComparendoDTO consultarComparendo(Long cicomparendo) {
        logger.debug(FachadaComparendoEJB.class.getName().concat("::consultarComparendo(Long)"));
        return iLComparendo.consultarComparendo(cicomparendo);
    }

    @Override
    public ComparendoDTO consultarComparendo(String numeroComparendo, Integer codigoOrganismo) {
        logger.debug(FachadaComparendoEJB.class.getName().concat("::consultarComparendo(String)"));
        return iLComparendo.consultarComparendo(numeroComparendo, codigoOrganismo);
    }

    @Override
    public void cambiarNumeroFactura(Long cicomparendo, Long numeroFacturaNuevo) throws CirculemosNegocioException {
        logger.debug(FachadaComparendoEJB.class.getName().concat("::cambiarNumeroFactura(Long,Long)"));
        iLComparendo.cambiarNumeroFactura(cicomparendo, numeroFacturaNuevo);
    }

    @Override
    public HistoricoNumeroComparendoDTO consultarHistoricoNumeroComparendo(
            HistoricoNumeroComparendoDTO historicoNumeroComparendoDTO) {
        logger.debug(FachadaComparendoEJB.class.getName()
                .concat("::consultarHistoricoNumeroComparendo(HistoricoNumeroComparendoDTO)"));
        return iLComparendo.consultarHistoricoNumeroComparendo(historicoNumeroComparendoDTO);
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public ArrayList<Integer> verificarAgente(OrganismoTransitoDTO organismoTransitoDTO) {
        logger.debug(FachadaComparendoEJB.class.getName().concat("::vericarAgente()"));
        return iLAgente.validarAgente(organismoTransitoDTO);

    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public Integer[] enviarNotificaciones(Integer codigoOrganismo) {
        logger.debug(FachadaComparendoEJB.class.getName().concat("::enviarNotificaciones()"));
        return iLNotificacionTercero.enviarNotificaciones(codigoOrganismo);
    }

    @Override
    public void actualizarFechaNotificacion(Long ciComparendo, int codigoOrganismo, Date fechaNotificacion) {
        logger.debug(FachadaComparendoEJB.class.getName().concat("::actualizarFechaNotificacion(Long, int, Date)"));
        iLComparendo.actualizarFechaNotificacion(ciComparendo, codigoOrganismo, fechaNotificacion);
    }

    @Override
    public void registrarAnulacion(List<Long> idComparendos) throws CirculemosNegocioException {
        iLComparendo.registrarAnulacion(idComparendos);
    }

}