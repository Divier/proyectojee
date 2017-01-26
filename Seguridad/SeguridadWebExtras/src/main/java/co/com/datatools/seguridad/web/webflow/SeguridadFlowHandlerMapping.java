package co.com.datatools.seguridad.web.webflow;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.webflow.conversation.NoSuchConversationException;
import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.repository.snapshot.SnapshotNotFoundException;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;
import org.springframework.webflow.mvc.servlet.FlowHandler;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import co.com.datatools.seguridad.excepciones.AccesoRestringidoRuntimeException;
import co.com.datatools.seguridad.web.excepciones.SesionInvalidaRuntimeException;

/**
 * Esta clase implementa solo con la intencion de definir un FlowHandler general para todos los flujos de la aplicacion
 * 
 * @author Felipe Martinez
 * 
 */
public class SeguridadFlowHandlerMapping extends FlowHandlerMapping {

    private FlowHandler defaultFlowHandler;

    private final static Logger logger = Logger.getLogger(SeguridadFlowHandlerMapping.class.getName());

    @Override
    protected FlowHandler createDefaultFlowHandler(String flowId) {

        return new SeguridadDefaultFlowHandler(flowId);
    }

    public FlowHandler getDefaultFlowHandler() {
        return defaultFlowHandler;
    }

    public void setDefaultFlowHandler(FlowHandler defaultFlowHandler) {
        this.defaultFlowHandler = defaultFlowHandler;
    }

    private static class SeguridadDefaultFlowHandler extends AbstractFlowHandler {

        private static final String PAGINA_ERROR_NOT_FOUND = "error/not_found.xhtml";
        private static final String PAGINA_ERROR_ACCESO = "error_acceso.xhtml";
        private static final String PAGINA_ERROR_GENERAL = "error_general.xhtml";
        private static final String PAGINA_LOGOUT = "logout.xhtml";

        final private String flowId;

        public SeguridadDefaultFlowHandler(String flowId) {
            super();
            this.flowId = flowId;
        }

        public String getFlowId() {
            return flowId;
        }

        public String handleException(FlowException e, HttpServletRequest request, HttpServletResponse response) {

            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            if (cause instanceof AccesoRestringidoRuntimeException) {
                return "contextRelative:" + PAGINA_ERROR_ACCESO;
            } else if (cause instanceof SesionInvalidaRuntimeException) {
                return "contextRelative:" + PAGINA_LOGOUT;
            } else if (cause instanceof NoSuchConversationException) {
                logger.infov("Error navegando: {0}\n{1}", cause.getMessage(), cause.fillInStackTrace());
                return "contextRelative:" + PAGINA_ERROR_NOT_FOUND;
            } else if (cause instanceof SnapshotNotFoundException) {
                logger.infov("Error navegando: {0}\n{1}", cause.getMessage(), cause.fillInStackTrace());
                return "contextRelative:" + PAGINA_ERROR_NOT_FOUND;
            } else {
                logger.error("", cause);
                while (cause instanceof EJBException && cause.getCause() != null) {
                    cause = e.getCause();
                    break;
                }
                request.getSession().setAttribute("stackTrace", createExceptionInfo(cause));
                return "contextRelative:" + PAGINA_ERROR_GENERAL;
            }
        }

        protected String createExceptionInfo(Throwable rootCause) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                rootCause.printStackTrace(pw);
                final String stackString = sw.toString();
                pw.close();
                sw.close();
                return stackString;
            } catch (IOException e) {
            }
            return "";
        }
    }
}
