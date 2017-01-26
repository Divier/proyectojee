package co.com.datatools.util.web.mb;

import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

/**
 * Clase abstracta que extiende las funcionalidad desde del {@link AbstractManagedBean} para dar soporte al contexto de Spring
 * 
 * @author Felipe Martinez
 */
public abstract class AbstractSwfManagedBean extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    /**
     * Returns the object bound with the specified name in the current Spring Flow, or null if no object is bound under the name.
     * 
     * @param objType
     *            returning object type
     * @param name
     *            a string specifying the name of the object
     * @return the object with the specified name, otherwise null
     */
    @SuppressWarnings("unchecked")
    protected <T> T findFlowObject(Class<T> objType, String name) {
        final RequestContext springCxt = RequestContextHolder.getRequestContext();
        final Object attribute = springCxt.getFlowScope().get(name);
        if (attribute != null) {
            return (T) attribute;
        }
        return null;
    }
}
