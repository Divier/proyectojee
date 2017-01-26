package co.com.datatools.c2.enumeracion;

/**
 * Enumeración que representa el tipo de institución que recibió el pago del cliente. Su contenido equivale a la tabla procedencia_pago
 * 
 * @author robert.bautista
 * @since 2013-11-18
 */
public enum EnumProcedenciaPago {

    PAGO_BANCOS(1), //
    PAGO_CORPORACION_AHORRO_Y_VIVIENDA(2), //
    PAGO_ACH_COLOMBIA(3), //
    PAGO_ASCREDIBANCO(4), //
    PAGO_ATH(5), //
    PAGO_CENIT(6), //
    PAGO_RED_MULTICOLOR(7), //
    PAGO_SERVIBANCA(8), //
    ;

    /**
     * Contiene el código de precedencia de pago
     */
    private int codigoProcedenciaPago;

    /**
     * @param codigoProcedenciaPago
     */
    private EnumProcedenciaPago(int codigoProcedenciaPago) {
        this.codigoProcedenciaPago = codigoProcedenciaPago;
    }

    /**
     * @return the codigoProcedenciaPago
     */
    public int getCodigoProcedenciaPago() {
        return this.codigoProcedenciaPago;
    }

    /**
     * Retorna el código de procedencia de pago como un String de dos caracteres. Rellena con ceros a la izquierda.
     * 
     * @return 0*d
     */
    public String getCodigoProcedenciaPagoAsString() {
        String codigo = "" + this.codigoProcedenciaPago;

        if (this.codigoProcedenciaPago < 10) {
            codigo = "0" + this.codigoProcedenciaPago;
        }

        return codigo;
    }

}
