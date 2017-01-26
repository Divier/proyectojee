package co.com.datatools.c2.ws.utilidades.enumeracion;

/**
 * @author luis.forero
 * 
 */
public enum EnumErrorWS {
    /**
     * # Message: Autenticación fallida, principal=%s <br>
     * # @param 1: principal -
     */
    AUTENTICACION_FALLIDA("autenticacionFallida"), //
    /**
     * # Message: Recurso no permitido
     */
    RECURSO_NO_PERMITIDO("recursoNoPermitido"), //
    /**
     * # Message: Caracteres no permitidos.
     */
    VALIDACION_XSS_ERR("validacionXSSErr");
    private String llave;

    private EnumErrorWS(String llave) {
        this.llave = llave;
    }

    public String getLlave() {
        return llave;
    }

}
