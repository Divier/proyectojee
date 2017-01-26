package co.com.datatools.c2.dto.ws;

/**
 * Contiene la respuesta a la invocacion del servicio IServicioRecibirComparendo.
 * 
 * @author luis.forero (2015-11-12)
 * @author julio.pinzon mod (2016-04-26)
 * 
 */
public class RespuestaRecibirComparendoWSDTO extends RespuestaWSDTO {

    private static final long serialVersionUID = 1L;

    private String errorExepcion;

    public String getErrorExepcion() {
        return errorExepcion;
    }

    public void setErrorExepcion(String errorExepcion) {
        this.errorExepcion = errorExepcion;
    }
}
