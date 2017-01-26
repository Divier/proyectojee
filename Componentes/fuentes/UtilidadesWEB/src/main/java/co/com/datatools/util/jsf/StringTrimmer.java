package co.com.datatools.util.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase a registrar en el faces-config.xml para procesar todos los parametros de tipo String, removiendole sus espacios
 * 
 * @author felipe.martinez
 */
public class StringTrimmer implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value != null ? StringUtils.trimToNull(value) : null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}