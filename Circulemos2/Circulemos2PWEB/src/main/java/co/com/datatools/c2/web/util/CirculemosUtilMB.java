package co.com.datatools.c2.web.util;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Utilidades para interactuar con los managed beans de la capa web.
 * 
 * @author luis.forero
 * 
 */
@ManagedBean
@SessionScoped
public class CirculemosUtilMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    /**
     * Permite instanciar un mb e ingresarlo a la sesion actual.
     * 
     * @param nombreMB
     *            nombre como es llamado para una expresion
     * @author luis.forero
     */
    public void instanciarMB(String nombreMB) {

        AbstractManagedBean abstractManagedBean = findSessionObject(null, nombreMB);
        if (abstractManagedBean != null) {
            removeSessionObject(nombreMB);
        }
        ELContext elContext = getFacesContext().getELContext();
        MethodExpression createMethodExpression = getFacesContext().getApplication().getExpressionFactory()
                .createMethodExpression(elContext, "#{" + nombreMB + ".toString()}", String.class, null);
        createMethodExpression.invoke(elContext, null);
    }

}
