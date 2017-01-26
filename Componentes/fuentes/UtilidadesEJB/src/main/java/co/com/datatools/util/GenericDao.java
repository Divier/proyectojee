package co.com.datatools.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacion de DAO generico para cualquier Entity, implementa metodos genericos q se pueden aplicar sobre una entidad
 * 
 * @author felipe.martinez
 * 
 * @param <T>
 *            Tipo de entidad base del DAO
 */
public class GenericDao<T extends Serializable> {

    private EntityManager em;
    private Session session;
    private Class<T> entityType;

    /**
     * Construye un DAO Generico para un tipo de entidad
     * 
     * @param entityType
     *            tipo de entidad base del DAO
     * @param em
     *            contexto de base de datos para la ejecucion de operaciones
     */
    public GenericDao(Class<T> entityType, EntityManager em) {
        super();
        this.em = em;
        session = this.em.unwrap(Session.class);
        this.entityType = entityType;
    }

    /**
     * Permite realizar una consulta sobre la entidad de tipo <b>T</b> agregando multiple condiciones al WHERE de la consulta.
     * 
     * @param parameters
     *            K: nombre de la propiedad, E: valor q debe tener la propiedad, si es null o vacio no agrega parametros a la consulta
     * 
     * @return lista de objetos consultados, Null Safe
     */
    public <E extends Object> List<T> findByAttributes(Map<String, E> parameters) {
        Criteria criteria = buildFindByCriteria(parameters);
        @SuppressWarnings("unchecked")
        List<T> result = criteria.list();
        return result;
    }

    /**
     * Permite realizar una consulta sobre la entidad de tipo <b>T</b> agregando una condicion al WHERE de la consulta.
     * 
     * @param attrName
     *            nombre del atributo de la entidad
     * @param attrValue
     *            valor del atributo
     * @return lista de objetos consultados, Null Safe
     */
    public List<T> findByAttribute(String attrName, Object attrValue) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put(attrName, attrValue);
        Criteria criteria = buildFindByCriteria(parameters);
        @SuppressWarnings("unchecked")
        List<T> result = criteria.list();
        return result;
    }

    /**
     * Permite realizar una consulta sobre la entidad de tipo <b>T</b> agregando una condicion al WHERE de la consulta, donde el resultado es <b>UN
     * SOLO REGISTRO</b>.
     * 
     * @param attrName
     *            nombre del atributo de la entidad, requerido
     * @param attrValue
     *            valor del atributo, requerido
     * @return registro unico consultado, null si no encuentran registros
     */
    public T findUniqueByAttribute(String attrName, Object attrValue) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put(attrName, attrValue);
        Criteria criteria = buildFindByCriteria(parameters);
        @SuppressWarnings("unchecked")
        T uniqueResult = (T) criteria.uniqueResult();
        return uniqueResult;
    }

    /**
     * Permite realizar una consulta sobre la entidad de tipo <b>T</b> agregando multiple condiciones al WHERE de la consulta, donde el resultado es
     * <b>UN SOLO REGISTRO</b>.
     * 
     * @param parameters
     *            K: nombre de la propiedad, E: valor q debe tener la propiedad, si es null o vacio no agrega parametros a la consulta
     * 
     * @return registro unico consultado, null si no encuentran registros
     */
    public <E extends Object> T findUniqueByAttributes(Map<String, E> parameters) {
        Criteria criteria = buildFindByCriteria(parameters);
        @SuppressWarnings("unchecked")
        T uniqueResult = (T) criteria.uniqueResult();
        return uniqueResult;
    }

    private <E extends Object> Criteria buildFindByCriteria(Map<String, E> parameters) {
        Criteria criteria = session.createCriteria(entityType);
        if (parameters != null) {
            for (Iterator<Entry<String, E>> iterator = parameters.entrySet().iterator(); iterator.hasNext();) {
                Entry<String, E> type = iterator.next();
                criteria.add(Restrictions.eq(type.getKey(), type.getValue()));
            }
        }
        return criteria;
    }

    /**
     * Permite crear ó actualizar una entidad dependiendo de su estado en el Persistence Context. Ver {@link Session#saveOrUpdate(Object)}
     * 
     * @param entity
     *            entidad a crear ó actualizar
     * @see Session#saveOrUpdate(Object)
     */
    public void saveOrUpdate(T entity) {
        session.saveOrUpdate(entity);
    }

    /**
     * Enmascara la creacion y ejecucion de una Query sobre la entidad de tipo <b>T</b> evita la validacion de resultado nulo
     * 
     * @param sql
     *            String con el contenido de la Consulta
     * @param parameters
     *            K: nombre del parametro, E: valor del parametro
     * @return lista de objetos consultados, Null Safe
     */
    public <E extends Object> List<T> buildAndExecuteQuery(CharSequence sql, Map<String, E> parameters) {
        Query query = em.createQuery(sql.toString());
        if (parameters != null) {
            for (Iterator<Entry<String, E>> it = parameters.entrySet().iterator(); it.hasNext();) {
                Entry<String, E> param = it.next();
                query.setParameter(param.getKey(), param.getValue());

            }
        }
        @SuppressWarnings("unchecked")
        List<T> resultList = query.getResultList();
        if (resultList == null) {
            return new ArrayList<T>();
        } else {
            return resultList;
        }
    }

    /**
     * Enmascara la creacion de una Query sobre la entidad de tipo <b>T</b>
     * 
     * @param sql
     *            String con el contenido de la Consulta
     * @param parameters
     *            K: nombre del parametro, E: valor del parametro
     * @return objeto Query con los parametros inicializados
     */
    public <E extends Object> Query buildQuery(CharSequence sql, Map<String, E> parameters) {
        Query query = em.createQuery(sql.toString());
        if (parameters != null) {
            for (Iterator<Entry<String, E>> it = parameters.entrySet().iterator(); it.hasNext();) {
                Entry<String, E> param = it.next();
                query.setParameter(param.getKey(), param.getValue());
            }
        }
        return query;
    }

    /**
     * Facilidad para crear un mapa agregarle un objeto
     * 
     * @param key
     *            llave de la entrada a agregar al mapa
     * @param value
     *            valore de la entrada a agregar al mapa
     * @return mapa con la entrada {key,value}
     */
    public static InlineMap buildMap(String key, Object value) {
        return new InlineMap().addEntry(key, value);
    }

    /**
     * Clase para crear un Mapa facilmente
     * 
     * @author Felipe Martinez
     */
    public static class InlineMap extends HashMap<String, Object> {
        private static final long serialVersionUID = 4360214419384810152L;

        /**
         * Agrega una llave valor al mapa y devuelve todo el mapa
         * 
         * @param key
         *            llave de la entrada a agregar al mapa
         * @param value
         *            valore de la entrada a agregar al mapa
         * @return mapa con la entrada {key,value}
         */
        public InlineMap addEntry(String key, Object value) {
            this.put(key, value);
            return this;
        }
    }
}
