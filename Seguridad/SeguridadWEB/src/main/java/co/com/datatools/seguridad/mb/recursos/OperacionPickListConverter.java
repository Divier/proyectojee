package co.com.datatools.seguridad.mb.recursos;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;

/**
 * Conversor para el componente PickList con el cual se seleccionan Operaciones en la interfaz. Permite convertir los valores del picklist de String a
 * objetos OperacionDto y viceversa
 * 
 * @author claudia.rodriguez
 * 
 */
@FacesConverter("operacionPickListConverter")
public class OperacionPickListConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return getObjectFromUIPickListComponent(component, value);
    }

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

    @SuppressWarnings("unchecked")
    private OperacionDto getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<OperacionDto> dualList;
        try {
            dualList = (DualListModel<OperacionDto>) ((PickList) component).getValue();

            OperacionDto operacionDto = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (operacionDto == null) {
                operacionDto = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }

            return operacionDto;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private OperacionDto getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final OperacionDto operacionDto = (OperacionDto) object;
            if (operacionDto.getIdOperacion().equals(identifier)) {
                return operacionDto;
            }
        }
        return null;
    }

}