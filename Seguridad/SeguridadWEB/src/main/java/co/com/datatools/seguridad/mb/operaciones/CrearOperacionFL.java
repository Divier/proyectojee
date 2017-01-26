package co.com.datatools.seguridad.mb.operaciones;

import org.jboss.logging.Logger;

import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de creacion de Operaciones
 * 
 * @author claudia.rodriguez
 * 
 */
public class CrearOperacionFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(CrearOperacionFL.class.getName());

    public static final String NOMBRE_BEAN = "crearOperacionFL";

    private String nombre;

    public CrearOperacionFL() {
        logger.debug("CrearOperacionFL::CrearOperacionFL");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
