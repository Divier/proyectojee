package co.com.datatools.c2.adaptador.financiacion;

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

import co.com.datatools.c2.adaptador.comparendo.AdaptadorCarteraComparendoEJB;
import co.com.datatools.c2.adaptador.comparendo.IRComparendoFinanciacion;
import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.entidades.ComparendoProceso;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoHelper;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Clase que se encarga de implementar el adpatador entre los modulos de financiacion y comparendos
 */
@Stateless(name = "AdaptadorComparendoFinanciacionEJB")
@LocalBean
public class AdaptadorComparendoFinanciacionEJB implements IRComparendoFinanciacion {

    private final static Logger logger = Logger.getLogger(AdaptadorComparendoFinanciacionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRComparendo comparendoEJB;

    /**
     * Default constructor.
     */
    public AdaptadorComparendoFinanciacionEJB() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void financiarComparendo(String numeroComparendo, int codigoOrganismo, Date fechaCambio, Long idProceso) {
        logger.debug(
                AdaptadorCarteraComparendoEJB.class.getName().concat("::financiarComparendo(String, int, Date, Long)"));

        // Crea ComparendoProceso
        asociarComparendoProcesoFinanciacion(numeroComparendo, codigoOrganismo, idProceso);

        CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
        cambioEstadoComparendo.setActividad(EnumActividad.FINANCIACION_COMPARENDO);
        cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.FINANCIADO);
        cambioEstadoComparendo.setCodigoOrganismo(codigoOrganismo);
        cambioEstadoComparendo.setNumeroComparendo(numeroComparendo);
        cambioEstadoComparendo.setFechaCambio(fechaCambio);
        comparendoEJB.actualizarEstadoComparendo(cambioEstadoComparendo);
    }

    @Override
    public void preFinanciarComparendo(String numeroComparendo, int codigoOrganismo, Date fechaCambio, Long idProceso) {
        logger.debug(AdaptadorCarteraComparendoEJB.class.getName()
                .concat("::preFinanciarComparendo(String, int, Date, Long)"));

        // Crea ComparendoProceso
        asociarComparendoProcesoFinanciacion(numeroComparendo, codigoOrganismo, idProceso);

        // Cambia de estado al comparendo
        CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
        cambioEstadoComparendo.setActividad(EnumActividad.FINANCIACION_COMPARENDO);
        cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.PREFINANCIADO);
        cambioEstadoComparendo.setCodigoOrganismo(codigoOrganismo);
        cambioEstadoComparendo.setNumeroComparendo(numeroComparendo);
        cambioEstadoComparendo.setFechaCambio(fechaCambio);
        comparendoEJB.actualizarEstadoComparendo(cambioEstadoComparendo);

    }

    @Override
    public void financiarProceso(Date fechaCambio, Long idProceso) {

        StringBuilder jpql = new StringBuilder().append("SELECT cp FROM ComparendoProceso cp ")
                .append("WHERE cp.idProceso=:idProceso ");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idProceso", idProceso);

        GenericDao<ComparendoProceso> daoComparendoProceso = new GenericDao<ComparendoProceso>(ComparendoProceso.class,
                em);
        List<ComparendoProceso> resultadoConsulta = daoComparendoProceso.buildAndExecuteQuery(jpql, filtros);

        // Se actualiza el estado del comparendo a financiado
        for (ComparendoProceso comparendoProceso : resultadoConsulta) {
            CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
            cambioEstadoComparendo.setActividad(EnumActividad.FINANCIACION_COMPARENDO);
            cambioEstadoComparendo.setEstadoComparendo(EnumEstadoComparendo.FINANCIADO);
            cambioEstadoComparendo.setCodigoOrganismo(comparendoProceso.getComparendo().getOrdenComparendoNacional()
                    .getOrganismoTransito().getCodigoOrganismo());
            cambioEstadoComparendo.setNumeroComparendo(
                    comparendoProceso.getComparendo().getOrdenComparendoNacional().getNumeroComparendo());
            cambioEstadoComparendo.setFechaCambio(fechaCambio);
            comparendoEJB.actualizarEstadoComparendo(cambioEstadoComparendo);
        }

    }

    /**
     * Asocia un comparendo que será financiado a un proceso
     * 
     * @param numeroComparendo
     * @param codigoOrganismo
     * @param idProceso
     * @author julio.pinzon 2016-07-27
     */
    private void asociarComparendoProcesoFinanciacion(String numeroComparendo, Integer codigoOrganismo,
            Long idProceso) {

        ComparendoDTO comparendoDTO = comparendoEJB.consultarComparendo(numeroComparendo, codigoOrganismo);

        // Crea ComparendoProceso
        ComparendoProceso comparendoProcesoEntidad = new ComparendoProceso();
        comparendoProcesoEntidad.setComparendo(ComparendoHelper.toLevel0Entity(comparendoDTO, null));
        comparendoProcesoEntidad.setIdProceso(idProceso);
        em.persist(comparendoProcesoEntidad);
    }

    @Override
    public void actualizarEstadoAnteriorComparendo(Long idProceso) {
        logger.debug(AdaptadorComparendoFinanciacionEJB.class.getName().concat("::actualizarEstadoComparendo(Long)"));

        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT ");
        consulta.append("ocn.numero_comparendo, ");
        consulta.append("ocn.codigo_organismo_transito ");
        consulta.append("FROM comparendo_proceso cp ");
        consulta.append("INNER JOIN orden_comparendo_nacional ocn ");
        consulta.append("ON cp.cicomparendo = ocn.cicomparendo ");
        consulta.append("WHERE cp.id_proceso = :idProceso ");
        consulta.append("ORDER BY cp.cicomparendo ");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("idProceso", idProceso);
        @SuppressWarnings({ "unchecked" })
        List<Object[]> lsComparendos = Utilidades.safeList(query.getResultList());

        if (!lsComparendos.isEmpty()) {
            for (Object[] comparendo : lsComparendos) {
                String numeroComparendo = (String) comparendo[0];
                Integer codigoOrganismo = (Integer) comparendo[1];
                CambioEstadoComparendoDTO cambioEstadoComparendo = new CambioEstadoComparendoDTO();
                cambioEstadoComparendo.setActividad(EnumActividad.FINANCIACION_COMPARENDO);
                cambioEstadoComparendo.setFechaCambio(UtilFecha.buildCalendar().getTime());
                cambioEstadoComparendo.setNumeroComparendo(numeroComparendo);
                cambioEstadoComparendo.setCodigoOrganismo(codigoOrganismo);
                cambioEstadoComparendo.setActualizarToAnterior(true);
                comparendoEJB.actualizarEstadoComparendo(cambioEstadoComparendo);
            }
        }
    }

    @Override
    public String consultarMedioImposicionComparendo(String numeroComparendo, Integer codigoOrganismo) {
        logger.debug(
                AdaptadorComparendoFinanciacionEJB.class.getName().concat("::consultarComparendo(String,Integer)"));
        String medioImposicion = null;
        ComparendoDTO comparendo = comparendoEJB.consultarComparendo(numeroComparendo, codigoOrganismo);
        if (comparendo != null) {
            medioImposicion = comparendo.getMedioImposicion().getNombre();
        }
        return medioImposicion;
    }

    @Override
    public boolean validarComparendoNotificacion(String numeroComparendo, Integer codigoOrganismo) {
        logger.debug(AdaptadorComparendoFinanciacionEJB.class.getName()
                .concat("::validarComparendoNotificacion(String,Integer)"));
        ComparendoDTO comparendoDTO = comparendoEJB.consultarComparendo(numeroComparendo, codigoOrganismo);
        if (comparendoDTO.getFechaNotificacion() == null) {
            return false;
        } else {
            return true;
        }
    }
}