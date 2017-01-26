package co.com.datatools.seguridad.mb.usuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;

import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;

/**
 * Conversor para el componente SelectCheckboxMenu con el cual se seleccionan Roles en la interfaz. Permite convertir los valores del picklist de
 * String a objetos RolDetalleDto y viceversa
 * 
 * @author claudia.rodriguez
 * 
 */
@FacesConverter("rolSelectCheckboxMenuConverter")
public class RolSelectCheckboxMenuConverter implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
        SelectCheckboxMenu select = (SelectCheckboxMenu) component;
        List<UIComponent> children = select.getChildren();

        for (@SuppressWarnings("rawtypes")
        Iterator iterator = children.iterator(); iterator.hasNext();) {

            UISelectItems uiComponent = (UISelectItems) iterator.next();
            @SuppressWarnings("unchecked")
            ArrayList<RolDetalleDto> valores = (ArrayList<RolDetalleDto>) uiComponent.getValue();
            for (RolDetalleDto rolDetalleDto : valores) {
                if (value.equals(String.valueOf(rolDetalleDto.getIdRol()))) {
                    return rolDetalleDto;
                }
            }
        }

        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((RolDetalleDto) object).getIdRol());
        } else {
            return null;
        }
    }
}