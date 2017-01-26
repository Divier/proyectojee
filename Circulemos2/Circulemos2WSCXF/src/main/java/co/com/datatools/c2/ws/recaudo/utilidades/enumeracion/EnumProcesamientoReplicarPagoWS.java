/**
 * 
 */
package co.com.datatools.c2.ws.recaudo.utilidades.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que identifica los posibles codigos de respuesta generales del web service de replicar pago
 * 
 * @author julio.pinzon (2016-04-25)
 */
public enum EnumProcesamientoReplicarPagoWS implements SearchableEnumeration<String> {
    ERROR_PAGO_RECHAZADO("9999", "-1"), // Define un error o rechazo
    REGISTRO_SATISFACTORIO("0000", "0"), // Define el estado satisfactorio
    PAGO_RECIBIDO_SIN_PROCESAR("0002", "1"), // Pago recibido sin procesar
    ;
    private String codigoOrigen;
    String codigo;

    private EnumProcesamientoReplicarPagoWS(String codigoOrigen, String codigo) {
        this.codigoOrigen = codigoOrigen;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getValue() {
        return codigoOrigen;
    }

    public String getCodigoOrigen() {
        return codigoOrigen;
    }
}