package co.com.datatools.c2.enumeracion.financiacion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumErrorInconsistenciaFinanciacion implements SearchableEnumeration<Integer> {
    // Errores de la financiación
    CAMPO_TIPO_DOCUMENTO_DEUDOR_VACIO(1, "El campo código tipo documento deudor esta vacío"), //
    CAMPO_RAZON_SOCIAL_NECESARIO(2,
            "Debe ingresar la razon social debido a que el campo codigo tipo documento deudor esta con el código \"RUC\""), //
    CAMPO_NUMERO_DOCUMENTO_DEUDOR_VACIO(3, "El campo número de identificación deudor esta vacio"), //
    CAMPO_PRIMER_NOMBRE_DEUDOR_VACIO(4, "El campo primer nombre deudor esta vacio"), //
    CAMPO_PRIMER_APELLIDO_DEUDOR_VACIO(5, "El campo primer apellido deudor esta vació"), //
    FORMATO_CORREO_DEUDOR_INVALIDO(6, "Formato de correo invalido del deudor"), //
    FORMATO_CORREO_CODEUDOR_INVALIDO(7, "Formato de correo invalido del codeudor"), //
    FORMATO_TELEFONO_DEUDOR_INVALIDO(8, "Formato de teléfono invalido del deudor"), //
    FORMATO_TELEFONO_CODEUDOR_INVALIDO(9, "Formato de teléfono invalido del codeudor"), //
    FINANCIACION_DEBE_TENER_AL_MENOS_UNA_CUOTA(10, "La financiación debe tener al menos una cuota"), //
    FINANCIACION_DEBE_TENER_AL_MENOS_UNA_OBLIGACION(11, "La financiación debe tener al menos una obligacion asociada"), //
    FINANCIACION_YA_EXISTE(12, "La financiación ya existe"),

    // Errores del detalle de la financiación
    CARTERA_NO_EXISTE(13, "Cartera no existe para numero y tipo de obligacion"), //
    SALDO_CARTERA_NO_COINCIDE(14, "El saldo de la cartera no coincide con el valor de la obligacion"), //
    OBLIGACION_REPETIDA(15, "Obligacion repetida en financiacion"), //
    OBLIGACION_EN_OTRA_FINANCIACION_EN_FIRME(16, "obligacion se encuentra en otra financiación en firme"), //

    // Errores del detalle de la cuota de financiación

    CUOTA_REPETIDA(17, "Cuota repetida en financiacion"), //
    ;
    private Integer id;
    private String codigo;

    private EnumErrorInconsistenciaFinanciacion(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
