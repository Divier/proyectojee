package co.com.datatools.sogit.utilies;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

/**
 * MB se encarga del manejo del menu
 * 
 * @author giovanni.velandia
 *
 */
@ManagedBean
@SessionScoped
public class MenuMB extends AbstractPortalManagedBean {

    private final static Logger LOGGER = Logger.getLogger(MenuMB.class.getName());
    private static final long serialVersionUID = 1L;

    /**
     * Se encarga de direccionar a la funcionalidad
     * 
     * @author giovanni.velandia
     * @param codigoRuta
     * @return
     */
    public String navegar(int codigoRuta) {
        LOGGER.debug("navegar(int)");
        limpiarSesion();
        return EnumNavegacion.buscarEnumNavegacion(codigoRuta).getRuta();
    }
}
