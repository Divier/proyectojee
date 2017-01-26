package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.entidades.ParametroSeguridad;
import co.com.datatools.seguridad.helper.ParametroSeguridadHelper;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.ObjectToXML;

/**
 * Session Bean implementation class ParametrosSeguridadEJB
 */
@Stateless(name = "ParametrosSeguridadEJB")
@LocalBean
@ExcludeDefaultInterceptors
public class ParametrosSeguridadEJB implements IRParametrosSeguridad {

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(ParametrosSeguridadEJB.class.getName());

    @Override
    public String consultarValorParametroSeguridad(EnumParametro parametro) {
        GenericDao<ParametroSeguridad> paramDao = new GenericDao<>(ParametroSeguridad.class, em);
        final ParametroSeguridad result = paramDao.findUniqueByAttribute("nombreParametroSeguridad",
                parametro.toString());
        if (result != null) {
            logger.trace("Se encuentra el parametro de nombre: " + parametro + " con el valor= "
                    + result.getValorParametroSeguridad());
            return result.getValorParametroSeguridad();
        } else {
            logger.info("No se encontró el parametro: " + parametro);
            return "";
        }
    }

    @Override
    public List<ParametroSeguridadDto> consultarParametroSeguridad(String nombre) {
        List<ParametroSeguridadDto> resultados = new ArrayList<ParametroSeguridadDto>();
        StringBuilder qlString = new StringBuilder();
        qlString.append("SELECT p FROM ParametroSeguridad p ");
        qlString.append(" WHERE UPPER(p.nombreParametroSeguridad) != :nombreParametroInstalador");
        if (!StringUtils.isBlank(nombre)) {
            qlString.append(" AND UPPER(p.nombreParametroSeguridad) LIKE :nombre");
        }

        qlString.append(" ORDER BY p.nombreParametroSeguridad");
        TypedQuery<ParametroSeguridad> query = em.createQuery(qlString.toString(), ParametroSeguridad.class);
        if (!StringUtils.isBlank(nombre)) {
            query.setParameter("nombre", "%" + nombre.toUpperCase() + "%");
        }
        query.setParameter("nombreParametroInstalador", EnumParametro.INSTALADOR_EJECUTADO.toString());

        List<ParametroSeguridad> parametros = query.getResultList();
        if (parametros != null && parametros.size() > 0) {
            ParametroSeguridadHelper helper = new ParametroSeguridadHelper();
            for (ParametroSeguridad parametroSeguridad : parametros) {
                resultados.add(helper.toDto(parametroSeguridad));
            }
        }
        return resultados;
    }

    @Override
    public void actualizarParametroSeguridad(ParametroSeguridadDto paramDto) {
        logger.debug("actualizarParametroSeguridad con nombre= " + paramDto.getNombre());
        ParametroSeguridad parametro = null;
        if (paramDto.getId() != null)
            parametro = em.find(ParametroSeguridad.class, paramDto.getId());
        else
            parametro = new GenericDao<>(ParametroSeguridad.class, em).findByAttribute("nombreParametroSeguridad",
                    paramDto.getNombre()).get(0);
        parametro.setValorParametroSeguridad(paramDto.getValor());
        em.merge(parametro);
        logger.debug("Se actualizó el parámetro: " + parametro.getNombreParametroSeguridad() + "con el valor: "
                + parametro.getValorParametroSeguridad());
    }

    @Override
    public String consultarValorParametroUrlAplicacion(EnumParametro nombreParametro, String idAplicacion) {
        String urls = consultarValorParametroSeguridad(nombreParametro);
        @SuppressWarnings("unchecked")
        HashMap<String, String> xmlToObject = ObjectToXML.xmlToObject(HashMap.class, urls);
        String url = xmlToObject.get(idAplicacion);
        return url;
    }
}
