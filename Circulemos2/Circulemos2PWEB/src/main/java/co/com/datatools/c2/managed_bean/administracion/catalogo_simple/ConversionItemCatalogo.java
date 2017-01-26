package co.com.datatools.c2.managed_bean.administracion.catalogo_simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;

@FacesConverter("conversionItemCatalogo")
public class ConversionItemCatalogo implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
        SelectCheckboxMenu select = (SelectCheckboxMenu) component;
        List<UIComponent> children = select.getChildren();

        for (@SuppressWarnings("rawtypes")
        Iterator iterator = children.iterator(); iterator.hasNext();) {

            UISelectItems uiComponent = (UISelectItems) iterator.next();
            @SuppressWarnings("unchecked")
            ArrayList<ItemCatalogoDTO> valores = (ArrayList<ItemCatalogoDTO>) uiComponent.getValue();
            for (ItemCatalogoDTO itemCatalogoDTO : valores) {
                if (value.equals(String.valueOf(itemCatalogoDTO.getId()))) {
                    return itemCatalogoDTO;
                }
            }
        }

        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((ItemCatalogoDTO) object).getId());
        } else {
            return null;
        }
    }
}
