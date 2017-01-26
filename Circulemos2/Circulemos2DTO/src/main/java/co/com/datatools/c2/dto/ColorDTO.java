package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author felipe.martinez
 */
public class ColorDTO extends ItemCatalogoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    public ColorDTO() {
        super();
    }

    public ColorDTO(Integer id) {
        super(id);
    }

}
