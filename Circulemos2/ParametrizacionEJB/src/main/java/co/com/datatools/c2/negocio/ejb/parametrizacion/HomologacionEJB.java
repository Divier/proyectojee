package co.com.datatools.c2.negocio.ejb.parametrizacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.homologacion.ValorHomologacionDTO;
import co.com.datatools.c2.entidades.ValorHomologacion;
import co.com.datatools.c2.negocio.helpers.homologacion.ValorHomologacionHelper;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.ILHomologacion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRHomologacion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.Mapeable;
import co.com.datatools.util.GenericDao;

/**
 * Implementa todos los servicios expuestos por la Interface de IHomologacion.
 * 
 * @author julio.pinzon
 * 
 */
@Stateless(name = "HomologacionEJB")
@LocalBean
public class HomologacionEJB implements IRHomologacion, ILHomologacion {

    private final static Logger logger = Logger.getLogger(HomologacionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public String obtenerValor(Mapeable origenHomologacion, String valorHomologable) {
        logger.debug("HomologacionEJB::obtenerValor(Mapeable, String)");

        String resultado = null;
        GenericDao<ValorHomologacion> interesDao = new GenericDao<>(ValorHomologacion.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT v FROM ValorHomologacion v");
        jpql.append(" LEFT JOIN FETCH v.tipoHomologacion t");
        jpql.append(" WHERE t.id = :idTipoHomologacion");
        filtros.put("idTipoHomologacion", origenHomologacion.getValue());
        jpql.append(" AND t.servicioHomologacion.id = :idServicioHomologacion");
        filtros.put("idServicioHomologacion", origenHomologacion.getOrigen());
        jpql.append(" AND v.valorOrigen = :valorOrigen");
        filtros.put("valorOrigen", valorHomologable);

        List<ValorHomologacion> resultadoConsulta = interesDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            ValorHomologacionDTO valorHomologacionDTO = ValorHomologacionHelper.toLevel1DTO(resultadoConsulta.get(0));
            resultado = valorHomologacionDTO.getValorFinal();
        }
        return resultado;
    }

}
