package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author felipe.martinez
 */
public class EstadoFormularioDTO extends ItemCatalogoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    public EstadoFormularioDTO() {
        super();
    }

    public EstadoFormularioDTO(Integer id) {
        super(id);
    }

}
