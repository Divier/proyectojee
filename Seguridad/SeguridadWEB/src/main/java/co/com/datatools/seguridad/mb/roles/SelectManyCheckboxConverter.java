package co.com.datatools.seguridad.mb.roles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;

/**
 * Conversor para el componente SelectManyCheckbox con el cual se seleccionan Operaciones de los recursos, en la interfaz de creación y modificación
 * de Roles. Permite convertir los valores del SelectManyCheckbox de String a objetos OperacionDto y viceversa
 * 
 * @author claudia.rodriguez
 * 
 */
@FacesConverter("selectManyCheckboxConverter")
public class SelectManyCheckboxConverter implements Converter {

    /**
     * Obtiene el valor en String del identificador de un OperacionDto
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(((OperacionDto) object).getIdOperacion());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    /**
     * Obtiene un objeto OperacionDto a partir del valor String de su identificador
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        SelectManyCheckbox select = (SelectManyCheckbox) component;
        List<UIComponent> children = select.getChildren();

        for (@SuppressWarnings("rawtypes")
        Iterator iterator = children.iterator(); iterator.hasNext();) {

            UISelectItems uiComponent = (UISelectItems) iterator.next();
            @SuppressWarnings("unchecked")
            ArrayList<OperacionDto> val = (ArrayList<OperacionDto>) uiComponent.getValue();
            for (OperacionDto operacionDto : val) {
                if (value.equals(String.valueOf(operacionDto.getIdOperacion()))) {
                    return operacionDto;
                }
            }
        }

        return null;

    }

}
