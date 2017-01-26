package co.com.datatools.util.jsf;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import org.apache.commons.lang3.StringUtils;

/**
 * Implementacion de componente faces para formatear Strings como numeros
 * 
 * @author felipe.martinez
 */
@FacesComponent(value = "outputNumberText")
public class OutputNumberTextComponent extends UINamingContainer {

    public OutputNumberTextComponent() {

    }

    @Override
    public String getFamily() {
        return "javax.faces.NamingContainer";
    }

    /**
     * Procesa el parametro de entrada para poder formatearlo como numero
     * 
     * @param input
     *            Objecto que pueda ser formateado como numero, si es un String se intenta convertir a un numero
     * @return Number o String
     */
    public Object processInput(Object input) {
        if (input instanceof String) {
            String str = (String) input;
            if (StringUtils.isNumeric(str))
                return new BigInteger(str);
            if (StringUtils.contains(str, ".") && StringUtils.isNumeric(StringUtils.remove(str, '.'))) {
                return new BigDecimal(str);
            }
        }
        return input;
    }
}
