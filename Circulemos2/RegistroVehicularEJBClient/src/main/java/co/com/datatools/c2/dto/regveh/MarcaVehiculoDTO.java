package co.com.datatools.c2.dto.regveh;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author felipe.martinez
 */
public class MarcaVehiculoDTO extends ItemCatalogoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    public MarcaVehiculoDTO() {
        super();
    }

    public MarcaVehiculoDTO(Integer id) {
        super(id);
    }

}
