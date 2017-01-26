package co.com.datatools.seguridad.mb.operaciones;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de modificacion de Operaciones
 * 
 * @author claudia.rodriguez
 * 
 */
public class ModificarOperacionFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ModificarOperacionFL.class.getName());

    public static final String NOMBRE_BEAN = "modificarOperacionFL";

    private OperacionDto operacionModificar;
    private boolean primerIngreso = true;

    public ModificarOperacionFL() {
        logger.debug("ModificarOperacionFL::ModificarOperacionFL");
    }

    public OperacionDto getOperacionModificar() {
        return operacionModificar;
    }

    public void setOperacionModificar(OperacionDto operacionModificar) {
        this.operacionModificar = operacionModificar;
    }

    public boolean isPrimerIngreso() {
        return primerIngreso;
    }

    public void setPrimerIngreso(boolean primerIngreso) {
        this.primerIngreso = primerIngreso;
    }

    public static String getNombreBean() {
        return NOMBRE_BEAN;
    }

}
