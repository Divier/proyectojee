package co.com.datatools.c2.enumeracion;

/**
 * Contiene las distintas opciones de respuesta al ser cliente de un WS
 * 
 * @author luis.forero(2016-03-29)
 * 
 */
public enum EnumRespuestaWS {
    /**
     * Problemas de comunicacion con el WS
     */
    PROB_COM,
    /**
     * Indica que el WS ejecuto satisfactoriamente la tarea
     */
    SATISFACTORIO,
    /**
     * Indica que el WS retorno un error.
     */
    ERROR,
    //
    ;
}
