package co.com.datatools.c2.enumeracion;

public enum EnumErrorReferenciaPago {

    NUMERO_COMPARENDO_PERDIDO(0, ":No se encuentra el numero de comparendo:"), //
    LIQUIDACION_PERDIDA(0, ":No se encuentra la liquidacion del comparendo:"), //
    REFERENCIA_DE_PAGO_EN_LIQUIDACION_PERDIDA(0, ":No se encuentra la referencia de pago en la liquidacion:"), //
    VALOR_DE_LA_LIQUIDACION_PERDIDA(0, ":No se encuentra el valor de la liquidacion:"), //
    NUMERO_DE_DOCUMENTO_PERDIDO(0, ":No se encuentra el numero de documento:"), //
    TIPO_DE_DOCUMENTO_PERDIDO(0, ":No se encuentra el tipo de documento:"), //
    CIA_PERDIDA(0, ":No se encuentra la cia para aplicar el descuento por curso:"), //
    NO_EXISTE_OBLIGACION_PARA_EL_COMPARENDO(0, ":No se encuentra una obligacion para el numero de comparendo ingresado"), //
    NO_EXISTE_EL_CONCEPTO_PAGO_DE_COMPARENDO(0, ":No se encuentra el concepto para pago de comparendo registrado"), //
    NO_EXISTE_EL_CONCEPTO_PAGO_DE_COMPARENDO_DESCUENTO_POR_CURSO(0,
            ":No se encuentra el concepto para pago de comparendo con descuento por curso"), //
    LLAVE_NO_ENCONTRADA_CODIGO_BARRAS(0, ":No se encuentra la llave configurada en el formato para el codigo de barras"), //
    FORMATO_CODIGO_BARRAS_NO_ENCONTRADO(0,
            ":No se encuentra el formato de codigo de barras configurado o la regla de negocio no esta disponible"), //
    FORMATO_CODIGO_BARRAS_CIA_NO_ENCONTRADO(0,
            ":No se encuentra el formato de codigo de barras para la CIA, o la regla de negocio no esta disponible"), //
    REFERENCIA_PAGO_SIN_CONCEPTOS_DESCUENTOS_CON_TIPO_DESCUENTO_CURSO(0,
            ":No se puede generar la referencia de pago ya que la liquidacion no contiene descuentos"), //
    PORCENTAJE_A_PAGAR_CIA_NO_ENCONTRADO(0,
            ":No se encuentra configurado el porcentaje para el pago a las CIAs o el valor es cero"), //
    EXISTE_MAS_DE_UN_DESCUENTO_EN_LA_LIQUIDACION(0,
            ":No se puede continuar ya que hay mas un descuento dentro de la liquidacion"), //
    NO_EXISTE_CONCEPTO_PAGO_CURSO_PEDAGOGICO(0, ":No se encuentra el concepto para el pago a curso pedagogico"), //
    lA_CIA_NO_EXISTE_EN_EL_SISTEMA(0, ":El numero de CIA no correponde a ninguna registrada en el sistema"), //
    REFERENCIA_DE_PAGO_SIN_CONCEPTOS_DE_AMNISTIAS(0,
            ":No se puede generar la referencia de pago ya que la liquidacion no contiene conceptos por amnistias"), //
    EXISTE_MAS_DE_UNA_AMNISTIA_EN_LA_LIQUIDACION(0,
            ":No se puede generar la referencia de pago ya que existe mas de un concepto por amnistias en la liquidacion"), //
    PARAMETRO_ORGANISMO_RECAUDA_PAGO_CURSO_PARA_OTRAS_ENTIDADES_NO_CONFIGURADO(0,
            ":El parametro para saber si el organismo recauda pago de curso de otras entidades no está configurado"), //
    VALOR_DE_PARAMETRO_ORGANISMO_RECAUDA_PAGO_CURSO_PARA_OTRAS_ENTIDADES_INVALIDO(0,
            ":Parametro para saber si el organismo recauda pago de curso de otras entidades configurado incorrectamente"), //
    ;

    /**
     * Codigo de error por el que se identifica.
     */
    private int codigo;
    /**
     * Descripcion del error.
     */
    private String descripcion;

    private EnumErrorReferenciaPago(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
