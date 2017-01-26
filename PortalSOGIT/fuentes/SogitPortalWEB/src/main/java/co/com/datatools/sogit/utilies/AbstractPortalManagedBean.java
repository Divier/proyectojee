package co.com.datatools.sogit.utilies;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

/**
 * Definicion abstracta de un managed bean para el portal
 * 
 * @author giovanni.velandia
 */
public abstract class AbstractPortalManagedBean extends AbstractManagedBean {

    private static final long serialVersionUID = 9148377567088023852L;

    /**
     * Obtiene el managed bean que centraliza el acceso a los catalogos de la aplicacion
     * 
     * @return objeto fachada de acceso
     */
    public FachadaCatalogosMB getCatalogosApp() {
        return findSessionObject(ConstantesManagedBean.CLASE_OBJ_FACHADA_CATALOGOS,
                ConstantesManagedBean.NOMBRE_FACHADA_CATALOGOS);
    }

    /**
     * Permite limpiar los elementos transientes de la sesion web
     * 
     */
    public void limpiarSesion() {
        HttpSession sesion = getHttpRequest().getSession();
        Enumeration<String> objetosSesion = sesion.getAttributeNames();
        while (objetosSesion.hasMoreElements()) {
            String llave = objetosSesion.nextElement();
            boolean remover = true;
            // Solo saca de sesion los ManagedBean
            if (!llave.endsWith("MB")) {
                remover = false;
            }
            // Remover objeto sesion
            if (remover) {
                removeSessionObject(llave);
            }
        }
    }

}
