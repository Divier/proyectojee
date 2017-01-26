package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Contiene la respuesta detalla de la invocacion de un servicio para clientes externos de la aplicacion.
 * 
 * @author luis.forero (2015-11-12)
 * 
 */
public class DetalleRespuestaWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    /**
     * Contiene el codigo de la respuesta.
     */
    private String codigo;
    /**
     * Contiene la descripcion del codigo de error.
     */
    private String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
