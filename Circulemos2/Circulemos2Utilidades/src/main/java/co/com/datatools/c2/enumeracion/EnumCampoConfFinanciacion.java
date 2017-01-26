package co.com.datatools.c2.enumeracion;

/**
 * Enumeracion que identifica los campos que puede contener una Configuracion de fincanciacion incluso sus campos listas de detalle junto con los
 * objetos a los cuales pertenece.
 * 
 * @author luis.forero
 * 
 */
public enum EnumCampoConfFinanciacion {

    /**
     * Campo que hace referencia al objeto como tal.
     */
    CONFIGURACION_FINANCIACION(1), //
    /**
     * Campo NOMBRE del objeto Configuracion de financiacion
     */
    NOMBRE_CONFIGURACION_FINANCIACION(1001), //
    /**
     * Campo Fecha Inicial del objeto Configuracion de financiacion
     */
    FECHA_INICIAL_VIGENCIA(1002), //
    /**
     * Campo Fecha Final del objeto Configuracion de financiacion
     */
    FECHA_FINAL_VIGENCIA(1003), //
    /**
     * Identifica tanto Fecha Inicial Como Fecha Final del objeto Configuracion de financiacion
     */
    FECHAS_VIGENCIA(1023), //
    /**
     * Identifica un campo de condicion presentada en la interfaz.
     */
    CAMPO_CONDICION(2), //
    /**
     * Identifica al objeto de Detalle Cantidad Cuotas como campo.
     */
    DETALLE_CANTIDAD_CUOTA(3), //
    /**
     * Campo Valor Minimo a Financiar de la tabla DetalleCantidadCuota
     */
    CAMPO_VALOR_MINIMO_FINANCIAR_DCC(3002), //
    /**
     * Campo Valor Maximo a Financiar de la tabla DetalleCantidadCuota
     */
    CAMPO_VALOR_MAXIMO_FINANCIAR_DCC(3003), //
    /**
     * Campo Cantidad Maxima de Cuotas de la tabla DetalleCantidadCuota.
     */
    CAMPO_CANTIDAD_MINIMA_CUOTAS_DCC(3004), //
    /**
     * Campo Cantidad Maxima de Cuotas de la tabla DetalleCantidadCuota.
     */
    CAMPO_CANTIDAD_MAXIMA_CUOTAS_DCC(3005), //
    /**
     * Identifica al objeto de Detalle Porcentaje Cuota Inicial
     */
    DETALLE_PORCENTAJE_CUOTA_INICIAL(4), //
    /**
     * Campo Valor Minimo a Financiar de tabla Detalle Porcentaje Cuota Inicial
     */
    CAMPO_VALOR_MINIMO_FINANCIAR_DPCI(4002), //
    /**
     * Campo Valor Maximo a Financiar de tabla Detalle Porcentaje Cuota Inicial
     */
    CAMPO_VALOR_MAXIMO_FINANCIAR_DPCI(4003), //
    /**
     * Campo Porcentaje de la financiacion para cuota inicial de tabla Detalle Porcentaje Cuota Inicial
     */
    CAMPO_PORCENTAJE_FINANCIACION_CUOTA_INICIAL_DPCI(4004), //
    ;
    /**
     * Identifica la posicion que se define del determinado campo.
     */
    private int index;

    private EnumCampoConfFinanciacion(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
