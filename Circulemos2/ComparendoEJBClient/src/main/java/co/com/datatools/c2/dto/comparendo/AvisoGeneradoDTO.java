package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

/**
 * Contiene el identificador del aviso generado y la cantidad de comparendos contenidos en dicho aviso.
 * 
 * @author luis.forero (2016-02-11)
 * 
 */
public class AvisoGeneradoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Cantidad de comparendos contenidos en el aviso de notificacion generado.
     */
    private int cantidadComparendos;
    /**
     * Identificador del aviso generado.
     */
    private Long idAviso;

    public AvisoGeneradoDTO() {
    }

    public int getCantidadComparendos() {
        return cantidadComparendos;
    }

    public void setCantidadComparendos(int cantidadComparendos) {
        this.cantidadComparendos = cantidadComparendos;
    }

    public Long getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

}
