package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumErrorConciliacionPago implements SearchableEnumeration<Integer> {

    // Encabezado de pago
    FECHA_TRX_ANTERIOR_FECHA_CARTERA(1, "101", "Fecha Transacción Pago ANTERIOR a fecha cartera"), //

    // Detalles de pago
    NO_EXISTE_OBLIGACION_COMPARENDO(2, "201", "NO existe la Obligación de comparendo en cartera"), //
    NO_EXISTE_OBLIGACION_FINANCIACION(3, "202", "NO existe la Obligación de financiación en cartera"), //
    NO_VIGENTE_FINANCIACION(4, "203", "La financiación NO está vigente"), //
    NO_COINCIDE_VALOR_CUOTA_FINANCIACION(5, "204", "NO coincide Valor de Cuota de financiación"), //
    NO_COINCIDE_VALOR_OBLIGACION_VALOR_CONCEPTO(6, "205", "NO coincide Valor de Obligación con Valor Concepto"), //
    NO_CORRESPONDE_TIPO_CUPON(7, "206", "El Tipo de Cupón NO corresponde a la obligación"), //
    NO_COINCIDE_VALOR_CUOTA_1_SALDO_CARTERA(8, "207", "NO coincide valor pago y saldo cartera para cuota 1"), //
    ESTADO_COMPARENDO_INVALIDO(9, "208", "Estado de comparendo NO permite recaudo"), //
    ESTADO_FINANCIACION_INVALIDO(10, "209", "Estado de financiacion NO permite recaudo"), //
    NO_COINCIDE_VALOR_TOTAL_FINANCIACION(11, "210",
            "NO corresponde el valor del saldo en cartera con el valor total de la financiación."), //
    CUOTA_ANTERIOR_NO_PAGADA(12, "211", "Pago de cuota no permitido, NO se ha pagado la anterior."), //
    NO_EXISTE_OBLIGACION_COACTIVO(13, "212", "NO existe la Obligación de coactivo"), //
    NO_EXISTE_COACTIVO(14, "213", "No existe el coactivo"), //
    ;

    private int id;
    private String codigo;
    private String nombre;

    private EnumErrorConciliacionPago(int id) {
        this.id = id;
    }

    private EnumErrorConciliacionPago(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    private EnumErrorConciliacionPago(int id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

}
