package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoVariableCondicionCoactivo implements SearchableEnumeration<Integer> {

    ENTERO(1), //
    REAL(2), //
    BOOLEANO(3), //
    CADENA(4), //
    FECHA(5), //
    COMBO_BOX_SELECCION_UNICA(6), //
    COMBO_BOX_SELECCION_MULTIPLE(7), //
    ;

    private int codigo;

    private EnumTipoVariableCondicionCoactivo(int codigo) {
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
