package co.com.datatools.seguridad.mb.usuarios;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.jboss.logging.Logger;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.RolDto;

/**
 * Conversor para el componente PickList con el cual se seleccionan Roles en la interfaz. Permite convertir los valores del picklist de String a
 * objetos RolDto y viceversa
 * 
 * @author claudia.rodriguez
 * 
 */
@FacesConverter("rolPickListConverter")
public class RolPickListConverter implements Converter {

    private final static Logger logger = Logger.getLogger(RolPickListConverter.class.getName());

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        logger.debug("String value: {} " + value);
        return getObjectFromUIPickListComponent(component, value);
    }

    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
        logger.debug("Object value: {} " + object);
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(((RolDto) object).getIdRol());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private RolDto getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<RolDto> dualList;
        try {
            dualList = (DualListModel<RolDto>) ((PickList) component).getValue();
            RolDto rolDto = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (rolDto == null) {
                rolDto = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }

            return rolDto;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private RolDto getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final RolDto rolDto = (RolDto) object;
            if (rolDto.getIdRol().equals(identifier)) {
                return rolDto;
            }
        }
        return null;
    }

}