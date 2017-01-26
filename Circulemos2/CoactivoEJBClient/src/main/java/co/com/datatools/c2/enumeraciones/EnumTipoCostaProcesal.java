package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoCostaProcesal implements SearchableEnumeration<Integer> {
    VALOR_ABSOLUTO(1), //
    PROCENTAJE_DEUDA(2), //
    SIN_COBRO(3), //
    ;

    private int codigo;

    private EnumTipoCostaProcesal(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public static EnumTipoCostaProcesal getEnum(int codigo) {
        EnumTipoCostaProcesal enumResultado = null;
        for (EnumTipoCostaProcesal enumVariable : EnumTipoCostaProcesal.values()) {
            if (enumVariable.getValue().equals(codigo)) {
                enumResultado = enumVariable;
            }
        }
        return enumResultado;
    }
}
