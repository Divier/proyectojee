package co.com.datatools.seguridad.mb.usuarios;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;

/**
 * Conversor para el componente PickList con el cual se seleccionan Grupos en la interfaz. Permite convertir los valores del picklist de String a
 * objetos GrupoDto y viceversa
 * 
 * @author claudia.rodriguez
 * 
 */
@FacesConverter("grupoPickListConverter")
public class GrupoPickListConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // logger.info("String value: {} " + value);
        return getObjectFromUIPickListComponent(component, value);
    }

    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
        // logger.info("Object value: {} " + object);
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(((GrupoDto) object).getIdGrupo());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private GrupoDto getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<GrupoDto> dualList;
        try {
            dualList = (DualListModel<GrupoDto>) ((PickList) component).getValue();
            GrupoDto grupoDto = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (grupoDto == null) {
                grupoDto = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }

            return grupoDto;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private GrupoDto getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final GrupoDto grupoDto = (GrupoDto) object;
            if (grupoDto.getIdGrupo().equals(identifier)) {
                return grupoDto;
            }
        }
        return null;
    }

}