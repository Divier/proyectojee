package co.com.datatools.c2.dto.comparendo;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Objeto que almacena la respuesta de validacion de un comparendo
 * 
 * @author felipe.martinez - giovanni.velandia(mod 2015-10-06)
 */
public class RespuestaValidacionDTO extends AbstractDTO {

    private static final long serialVersionUID = 3199301276644992354L;

    /**
     * Listado de errores encontrados en la validacion
     */
    private List<DetalleProcesamientoDTO> detalleProcesamientoDTOs;
    private String codigoResultado;
    private boolean existeAlerta;

    public RespuestaValidacionDTO() {
        detalleProcesamientoDTOs = new ArrayList<DetalleProcesamientoDTO>();
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoDTOs() {
        return detalleProcesamientoDTOs;
    }

    public void setDetalleProcesamientoDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoDTOs) {
        this.detalleProcesamientoDTOs = detalleProcesamientoDTOs;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public boolean isExisteAlerta() {
        return existeAlerta;
    }

    public void setExisteAlerta(boolean existeAlerta) {
        this.existeAlerta = existeAlerta;
    }

}
