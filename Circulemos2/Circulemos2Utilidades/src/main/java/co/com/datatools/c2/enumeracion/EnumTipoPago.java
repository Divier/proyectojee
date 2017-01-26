package co.com.datatools.c2.enumeracion;

/**
 * Contiene los tipos de pago. Corresponde a la tabla tipo_pago
 * 
 * @author robert.bautista
 * @since 2013-11-26
 */
public enum EnumTipoPago {
    ARCHIVO_ASOBANCARIA(1), //
    PAGO_ELECTRONICO(2), //
    PAGO_SIMIT(3), //
    SALDO_A_FAVOR(4) //
    ;

    /**
     * Contiene el código del tipo de pago.
     */
    private int codigo;

    /**
     * Constructor con el código del tipo de pago.
     * 
     * @param codigo
     *            código del tipo de pago
     */
    private EnumTipoPago(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

}
