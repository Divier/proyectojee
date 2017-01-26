package co.com.datatools.c2.utilities;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Clase que implementa el phaseListener para modificar los mensajes de error en la aplicacion.
 * 
 * @author Ivan.Arteaga
 * @version 1.0
 */
@ManagedBean
public class ErrorPhaseListener implements PhaseListener {
    private static final long serialVersionUID = 1L;

    /** Determina si sucedio un error o no */
    private boolean flagError = false;

    /**
     * Metodo que se ejecuta <b>DESPUES</b> de cada fase
     * 
     * @param event
     */
    public void afterPhase(PhaseEvent event) {
        if (event.getPhaseId().equals(PhaseId.PROCESS_VALIDATIONS)) {
            if (FacesContext.getCurrentInstance().isValidationFailed()) {
                setFlagError(true);
            } else {
                setFlagError(false);
            }
        }
    }

    /**
     * Metodo que se ejecuta <b>ANTES</b> de cada fase
     * 
     * @param event
     */
    public void beforePhase(PhaseEvent event) {
    }

    /**
     * 
     * @return PhaseId
     */
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * Getter del atributo flagError
     * 
     * @return boolean
     */
    public boolean isFlagError() {
        return flagError;
    }

    /**
     * Setter del atributo flagError
     * 
     * @param flagError
     */
    public void setFlagError(boolean flagError) {
        this.flagError = flagError;
    }
}