/**
 * 
 */
package co.com.datatools.c2.enumeracion.financiacion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author JavierFajardo
 * 
 */
public enum EnumVariableCondicionFinanciacion implements SearchableEnumeration<Integer> {
    TIPO_CALCULO_CUOTA(1, "Tipo de cálculos de cuota"), //
    TIPO_OBLIGACIONES_PERMITEN_FINANCIACION(2, "Tipo de obligaciones que permiten financiación"), //
    CANTIDAD_OBLIGACIONES(3, "Cantidad de obligaciones"), //
    CANTIDAD_OBLIGACIONES_CIUDADANO(4, "Cantidad de obligaciones que puede tener el ciudadano financiadas al tiempo"), //
    CANTIDAD_FINANCIACIONES_VIGENTES_CIUDADANO(5, "Cantidad de financiaciones vigentes que puede tener el ciudadano"), //
    VALOR_MINIMO_FINANCIAR(6, "Valor mínimo a financiar"), //
    VALOR_MAXIMO_FINANCIAR(7, "Valor máximo a financiar"), //
    MONTO(8, "Monto"), //
    CARGOS_FUNCIONARIOS_SISTEMA(9, "Cargos de los funcionarios del sistema"), //
    VALOR_MAXIMO_FINANCIAR_NO_GARANTIA(10, "Valor  máximo de financiación para no solicitar garantías"), //
    MAXIMO_NUMERO_CUOTAS_NO_FINANCIACION(11, "Máximo número de cuotas  para no solicitar garantías"), //
    INCLUIR_INTERESES_OBLIGACIONES(12, "¿Incluir los intereses de las obligaciones en el primer pago?"), //
    FINANCIAR_COMPARENDOS_SANCIONADOS(13, "¿Se permite financiar solo comparendos sancionados?"), //
    PORCENTAJE_CARGO_ADMINISTRATIVO(14, "Porcentaje del cargo administrativo"), //
    CARGO_ADMINISTRATIVO_INTERESES(15,
            "¿Los cargos administrativos de la financiación incluyen intereses de las obligaciones?"), //
    INCLUIR_CARGOS_ADMINISTRATIVOS_FINANCIACION(16,
            "¿Incluir los cargos administrativos de la financiación en el primer pago?"), //
    INTERES_MORA_CUOTA_VENCIDA(17, "¿Se cobran intereses de mora sobre cuotas vencidas de la financiación?"), //
    PERMITE_REFINANCIAR(18, "Permitir financiar cuando existen otras financiaciones en mora"), //
    GENERA_ACTO_ADMINISTRATIVO(19,
            "Generar actos administrativos de sanción a los comparendos que no han sido sancionados en el momento de la solicitud de financiación"), //
    TIPO_CONVERSION_CANTIDAD_CUOTAS(20, "Tipo de conversión"), //
    NUMERO_DIAS(21, "Número de días"), //
    DIAS_HABILES_CALENDARIO(22, "Días hábiles o calendario"), //
    INCREMENTO_INTERESES_OBLIGACION(23,
            "Incremento de intereses de la obligación según fecha de pago de la cuota inicial"), //
    TIPO_CONVERSION_PORCENTAJE(24, "Tipo de conversión"), //
    TIPO_CONVERSION_VALOR(25, "Tipo de conversión"), //
    VALOR_MINIMO_CUOTA(26, "Valor mínimo de cuota"), //
    PERMITE_FINANCIAR_DEUDOR(27, "Permite financiar al deudor solidario"), //
    DETALLE_CANTIDAD_CUOTAS(28, "Detalle cantidad de cuotas"), //
    DETALLE_PORCENTAJE_CUOTA_INICIAL(29, "Detalle porcentaje de cuota inicial"), //
    FINANCIAR_OBLIGACIONES_NO_NOTIFICADAS(30, "¿Financiar obligaciones NO notificadas?")//
    ;

    private int codigo;
    private String nombre;

    private EnumVariableCondicionFinanciacion(int codigo) {
        this.codigo = codigo;
    }

    private EnumVariableCondicionFinanciacion(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public static EnumVariableCondicionFinanciacion getEnum(int codigo) {
        EnumVariableCondicionFinanciacion enumResultado = null;
        for (EnumVariableCondicionFinanciacion enumVariable : EnumVariableCondicionFinanciacion.values()) {
            if (enumVariable.getValue().equals(codigo)) {
                enumResultado = enumVariable;
            }
        }
        return enumResultado;
    }
}
