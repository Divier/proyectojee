package co.com.datatools.seguridad.web.listener;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.web.mb.AutorizacionBean;

import com.sun.faces.util.DebugUtil;

/**
 * Recurso de AUTORIZACION<br>
 * 
 * Listener de la aplicacion encargado de controlar los componentes que se retornan al cliente en el momento de Render basado en los permisos del
 * usuario
 * 
 * @author Felipe Martinez
 */
public class AutorizacionListener implements SystemEventListener {

    private final static Logger logger = Logger.getLogger(AutorizacionListener.class.getName());

    @Override
    public boolean isListenerForSource(Object source) {
        return (source instanceof UIViewRoot);
    }

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        procesarView();
    }

    private void procesarView() {

        final AutorizacionBean autBean = (AutorizacionBean) findSessionObject(ConstantesSeguridad.NombresManagedBeans.AUTORIZACION_BEAN);
        if (autBean == null) {
            return;
        }
        RequestContext springCxt = RequestContextHolder.getRequestContext();
        if (springCxt != null) {
            final String nombreRecurso = springCxt.getActiveFlow().getId();

            final UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
            autBean.removerOperaciones(viewRoot, nombreRecurso);
        }

    }

    private Object findSessionObject(String name) {
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        return request.getSession().getAttribute(name);
    }

    public static void imprimirArbolComponentes(UIComponent nodo) {
        System.out.println("--------------------------");
        DebugUtil.printTree(nodo, System.out);
        System.out.println("--------------------------");
    }

}
