package co.com.datatools.seguridad.util;

import java.io.IOException;
import java.util.Properties;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

@ManagedBean
@SessionScoped
public class SeguridadUtilMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;

    /**
     * Permite instanciar un mb e ingresarlo a la sesion actual.
     * 
     * @param nombreMB
     *            nombre del MB
     * @author luis.forero
     */
    public void instanciarMB(String nombreMB) {
        ELContext elContext = getFacesContext().getELContext();
        MethodExpression createMethodExpression = getFacesContext().getApplication().getExpressionFactory()
                .createMethodExpression(elContext, "#{" + nombreMB + ".toString()}", String.class, null);
        createMethodExpression.invoke(elContext, null);
    }

    /**
     * @author giovanni.velandia
     * @return
     */
    public static String cargarVersionArtefacto() {
        Properties propiedades = new Properties();
        try {
            propiedades.load(SeguridadUtilMB.class.getResourceAsStream("/seg-artefacto.properties"));
            return propiedades.getProperty("version");
        } catch (IOException e) {
            throw new SeguridadRuntimeException(
                    "No se encuentra el archivo de propiedades de versiones seg-artefacto.properties");
        }
    }

}
