package co.com.datatools.c2.enumeracion;

/**
 * Enumeración que representa el medio por el cual se realizó el pago. Su contenido equivale a la tabla medio_pago
 * 
 * @author robert.bautista
 * @since 2013-11-18
 */
public enum EnumMedioPago {

    VENTANILLA_EN_EFECTIVO(1), //
    VENTANILLA_EN_CHEQUE(2), //
    BUZON_DE_AUTOSERVICIO(3), DEBITO_CUENTA_SISTEMA_AUDIORESPUESTA(11), //
    DEBITO_CUENTA_CAJERO_ELECTRONICO(12), //
    DEBITO_CUENTA_DATAFONO(13), //
    DEBITO_CUENTA_DOMICILIACION(14), //
    DEBITO_CUENTA_INTERNET(15), TARJETA_CREDITO_SISTEMA_AUDIORESPUESTA(21), //
    TARJETA_CREDITO_CAJERO_ELECTRONICO(22), //
    TARJETA_CREDITO_DATAFONO(23), //
    TARJETA_CREDITO_DOMICILIACION(24), //
    TARJETA_CREDITO_INTERNET(25), //
    ;

    /**
     * Contiene el código de medio de pago.
     */
    private int codigoMedioPago;

    /**
     * @param codigoMedioPago
     *            contiene el código de medio de pago
     */
    private EnumMedioPago(int codigoMedioPago) {
        this.codigoMedioPago = codigoMedioPago;
    }

    /**
     * @return the codigoMedioPago
     */
    public int getCodigoMedioPago() {
        return this.codigoMedioPago;
    }

    /**
     * Retorna el código de procedencia de pago como un String de dos caracteres. Rellena con ceros a la izquierda.
     * 
     * @return 0*d
     */
    public String getCodigoMedioPagoAsString() {
        String codigo = "" + this.codigoMedioPago;

        if (this.codigoMedioPago < 10) {
            codigo = "0" + this.codigoMedioPago;
        }

        return codigo;
    }
}
