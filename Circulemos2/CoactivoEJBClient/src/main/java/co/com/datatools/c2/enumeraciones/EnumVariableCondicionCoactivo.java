package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * 
 * @author Dixon.Alvarez
 * 
 */
public enum EnumVariableCondicionCoactivo implements SearchableEnumeration<Integer> {
    NUMERO_DIAS_PERIODICIDAD_GENERACION(1, "Numero de dias para la periodicidad de generacion de job"), //
    TIPO_DIAS_PERIODICIDAD_GENERACION(2, "Tipo dias para la periodicidad de generacion de job"), //
    TIPO_DOCUMENTOS_PERMITEN_COACTIVO(3, "Tipos de documento a los que se les permite generar coactivo"), //
    TIPO_OBLIGACIONES_PERMITEN_COACTIVO(4, "Tipos de obligacion a los que se les permite generar coactivo"), //
    GENERAR_COACTIVO_OBLIGACIONES_NO_NOTIFICADAS(5, "¿Generar coactivo a obligaciones NO notificadas?"), //
    CANTIDAD_DIAS_GENERA_COBRO_COACTIVO(6,
            "Cantidad de dias a tener en cuenta desde la fecha de notificacion para generar coactivo"), //
    VALOR_MINIMO(7, "Valor minimo por el cual se genera un coactivo"), //
    AGRUPAR_OBLIGACIONES(8, "¿Agrupar obligaciones?"), //
    TIPO_COSTA_PROCESAL(9, "Tipo de costa procesal que se aplicara al cobro de coactivo"), //
    VALOR_TIPO_COSTA_PROCESAL(10, "Valor del tipo de costa procesal que se aplicara al cobro de coactivo"), //

    /**
     * @author giovanni.velandia
     */
    GENERAR_GASTOS_ADM(11, "Generar gastos administrativos?"), //
    VALOR_GASTOS_ADM(12, "Valor de gastos administrativos"), //
    GENERAR_OFICIO_SOLICITUD_BIENES(13, "Generar oficio de solicitud de bienes?"), //
    ENTIDAD(14, "Entidad(es)"), //
    TIPO_OFICIO(15, "Tipo de oficio"), //
    ;

    private int codigo;
    private String nombre;

    private EnumVariableCondicionCoactivo(int codigo) {
        this.codigo = codigo;
    }

    private EnumVariableCondicionCoactivo(int codigo, String nombre) {
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

    public static EnumVariableCondicionCoactivo getEnum(int codigo) {
        EnumVariableCondicionCoactivo enumResultado = null;
        for (EnumVariableCondicionCoactivo enumVariable : EnumVariableCondicionCoactivo.values()) {
            if (enumVariable.getValue().equals(codigo)) {
                enumResultado = enumVariable;
            }
        }
        return enumResultado;
    }
}
