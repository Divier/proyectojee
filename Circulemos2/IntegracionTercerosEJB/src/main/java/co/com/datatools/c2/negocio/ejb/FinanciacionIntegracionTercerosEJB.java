package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.entidades.financiacion.ItFinanciacion;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.negocio.helpers.extension.ItFinanciacionHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILFinanciacionIntegracion;
import co.com.datatools.c2.negocio.interfaces.IRFinanciacionIntegracion;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class FinanciacionEJB
 * 
 * @author dixon.alvarez
 */
@Stateless(mappedName = "FinanciacionIntegracionTercerosEJB")
@LocalBean
public class FinanciacionIntegracionTercerosEJB implements IRFinanciacionIntegracion, ILFinanciacionIntegracion {

    private final static Logger logger = Logger.getLogger(FinanciacionIntegracionTercerosEJB.class.getName());

    @PersistenceContext(unitName = "IntegracionTercerosC2JPA")
    private EntityManager em;

    @Override
    public List<ItFinanciacionDTO> consultarFinanciaciones(Integer codigoOrganismo, EnumEstadoLectura estadoLectura) {
        logger.debug("FinanciacionIntegracionTercerosEJB.consultarFinanciaciones(Integer, EnumEstadoLectura)");
        List<ItFinanciacionDTO> financiaciones = new ArrayList<ItFinanciacionDTO>();

        GenericDao<ItFinanciacion> financiacionDao = new GenericDao<>(ItFinanciacion.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT f FROM ItFinanciacion f");
        jpql.append(" WHERE ");
        jpql.append(" f.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);
        if (estadoLectura != null) {
            jpql.append(" AND f.estadoLectura = :estadoLectura");
            filtros.put("estadoLectura", estadoLectura.getValue());
        }
        List<ItFinanciacion> resultadoConsulta = financiacionDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            financiaciones = ItFinanciacionHelperExtend.toListLevel1DTO(resultadoConsulta);
        }
        return financiaciones;
    }

    @Override
    public void actualizarEstadoFinanciacion(Long numeroFinanciacion, EnumEstadoLectura estadoLectura) {
        logger.debug("FinanciacionIntegracionTercerosEJB.actualizarEstadoFinanciacion(Long, EnumEstadoLectura)");
        checkNotNull(estadoLectura, "Estado de lectura es obligatorio");
        checkNotNull(numeroFinanciacion, "Número de la financiación es obligatorio");
        ItFinanciacion financiacion = em.find(ItFinanciacion.class, numeroFinanciacion);
        financiacion.setEstadoLectura(estadoLectura.getValue());
        em.merge(financiacion);
    }

}
