package co.com.datatools.seguridad.web.webflow;

import java.util.LinkedList;

import org.jboss.logging.Logger;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;

import co.com.datatools.seguridad.excepciones.AccesoRestringidoRuntimeException;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.web.mb.AutorizacionBean;

/**
 * Recurso de AUTORIZACION<br>
 * 
 * Listener de Spring utilizado para obtener el contexto de una peticion de entrada a la aplicacion, a traves de esta clase se determina el flujo
 * actual de ejecucion y la operacion para poder realizar la operaciones de Autorizacion
 * 
 * @author Felipe Martinez
 * 
 */
public class SeguridadFlowExecutionListener extends FlowExecutionListenerAdapter {

    private final static Logger logger = Logger.getLogger(SeguridadFlowExecutionListener.class.getName());

    private static final String CONTEXT_KEY = "breadcrumb";

    @Override
    public void sessionCreating(RequestContext context, FlowDefinition definition) {
        logger.debug("Autorizando Recurso { Flujo= " + definition.getId() + " }");

        AutorizacionBean autBean = (AutorizacionBean) context.getExternalContext().getSessionMap()
                .get(ConstantesSeguridad.NombresManagedBeans.AUTORIZACION_BEAN);
        if (autBean == null) {
            autBean = new AutorizacionBean();
            context.getExternalContext().getSessionMap().put(ConstantesSeguridad.NombresManagedBeans.AUTORIZACION_BEAN,
                    autBean);
        }
        final String nombreRecurso = definition.getId();
        boolean recursoValido = autBean.validarSolicitudRecurso(definition.getId());
        if (!recursoValido) {
            throw new AccesoRestringidoRuntimeException(
                    "El recurso solicitado no es permitido. Recurso: " + nombreRecurso);
        }
    }

    @Override
    public void stateEntering(RequestContext context, StateDefinition state) {
        logger.debug(
                "Autorizando Operacion { Flujo= " + state.getOwner().getId() + ", Estado= " + state.getId() + " }");
        final String nombreRecurso = state.getOwner().getId();
        final String nombreOperacion = state.getId();
        final AutorizacionBean autBean = (AutorizacionBean) context.getExternalContext().getSessionMap()
                .get(ConstantesSeguridad.NombresManagedBeans.AUTORIZACION_BEAN);
        boolean operacionValida = autBean.validarSolicitudOperacion(nombreRecurso, nombreOperacion);
        if (!operacionValida) {
            throw new AccesoRestringidoRuntimeException("La operacion solicitada no es permitida. Recurso: "
                    + nombreRecurso + " Operacion: " + nombreOperacion);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void viewRendering(RequestContext context, View view, StateDefinition state) {
        super.viewRendering(context, view, state);

        // Obtiene la lista de recursos actuales del flujo
        LinkedList<Breadcrumb> crumb = (LinkedList<Breadcrumb>) context.getConversationScope().get(CONTEXT_KEY);

        if (crumb != null) {

            // Crea un nuevo item
            Breadcrumb breadcrumb = new Breadcrumb(state.getOwner().getId(), state.getId(),
                    context.getFlowExecutionUrl());

            if (crumb.contains(breadcrumb)) {
                // Item ya existe, entonces se borra
                int pos = crumb.indexOf(breadcrumb);
                crumb.subList(pos + 1, crumb.size()).clear();
                logger.debug("Item de miga de pan borrado");
            } else {
                // Add new breascrumb to trail.
                crumb.add(breadcrumb);
                logger.debug("Item adicionado a la miga de pan");
            }
        } else {
            logger.error("No encuentra la lista de items");
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void sessionStarting(RequestContext context, FlowSession session, MutableAttributeMap input) {
        super.sessionStarting(context, session, input);
        FlowDefinition flowDefinition = session.getDefinition();

        logger.debug("Inicia session para: " + flowDefinition.getId());

        // Se inicia una nueva session del flujo, se incluye la variable de miga de pan
        LinkedList<Breadcrumb> crumb = (LinkedList<Breadcrumb>) context.getConversationScope().get(CONTEXT_KEY);
        if (crumb == null) {
            crumb = new LinkedList<Breadcrumb>();
            context.getConversationScope().put(CONTEXT_KEY, crumb);
        }

    }

}
