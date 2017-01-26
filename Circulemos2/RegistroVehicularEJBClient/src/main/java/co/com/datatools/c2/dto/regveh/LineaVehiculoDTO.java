package co.com.datatools.c2.dto.regveh;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author felipe.martinez
 */
public class LineaVehiculoDTO extends ItemCatalogoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    public LineaVehiculoDTO() {
        super();
    }

    public LineaVehiculoDTO(Integer id) {
        super(id);
    }

    private MarcaVehiculoDTO marca;

    public MarcaVehiculoDTO getMarca() {
        return marca;
    }

    public void setMarca(MarcaVehiculoDTO marca) {
        this.marca = marca;
    }

}
