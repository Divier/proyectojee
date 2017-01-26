package co.com.datatools.util.web.swf;

import java.util.logging.Logger;

import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.execution.EnterStateVetoException;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;

/**
 * Clase utilitaria para hacer seguimiento de las peticiones realizadas sobre los flujos de Spring
 * 
 * @author felipe.martinez
 */
public class DebugFlowExecutionListener extends FlowExecutionListenerAdapter {
    private final static Logger logger = Logger.getLogger(DebugFlowExecutionListener.class.getName());

    @Override
    public void requestSubmitted(RequestContext context) {
        logger.info("Peticion entrante = " + context.getFlowExecutionUrl());
    }

    @Override
    public void sessionCreating(RequestContext context, FlowDefinition definition) {
        logger.info("Inicializando { Flujo= " + definition.getId() + " }");
    }

    @Override
    public void sessionStarting(RequestContext context, FlowSession session, MutableAttributeMap input) {
        logger.info("Ejecutando { Flujo= " + session.getDefinition().getId() + " }");
    }

    @Override
    public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
        logger.info("Ejecutando { Transicion={ on='" + transition.getId() + "', to='" + transition.getTargetStateId()
                + "'} }");
    }

    @Override
    public void stateEntering(RequestContext context, StateDefinition state) throws EnterStateVetoException {
        logger.info("Ejecutando { Flujo= " + state.getOwner().getId() + ", Estado= " + state.getId() + " }");
    }

}
