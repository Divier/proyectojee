package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Valores de negocio para la tabla tipo_concepto_cartera
 * 
 * @author felipe.martinez
 */
public enum EnumTipoConceptoCartera implements SearchableEnumeration<String> {
    DEBITO(1, "Debito"), //
    CREDITO(2, "Credito"), //
    ;

    private final int codigo;
    private final String value;

    private EnumTipoConceptoCartera(int codigo, String value) {
        this.codigo = codigo;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public int getCodigo() {
        return codigo;
    }

}
