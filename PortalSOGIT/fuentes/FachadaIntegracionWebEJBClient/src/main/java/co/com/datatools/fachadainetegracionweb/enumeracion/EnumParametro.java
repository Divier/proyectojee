package co.com.datatools.fachadainetegracionweb.enumeracion;

import co.com.datatools.fachadainetegracionweb.util.SearchableEnumeration;

public enum EnumParametro implements SearchableEnumeration<Integer> {
    FORMATO_MONEDA(207), //
    FORMATO_FECHA(208), //
    RUTA_EVIDENCIAS(220),//
    ;

    private int codigo;

    EnumParametro(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }
}
