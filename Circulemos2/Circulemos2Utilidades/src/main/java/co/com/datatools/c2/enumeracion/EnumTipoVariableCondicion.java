package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author luis.forero
 * 
 */
public enum EnumTipoVariableCondicion implements SearchableEnumeration<Integer> {

    ENTERO(1), //
    REAL(2), //
    BOOLEANO(3), //
    CADENA(4), //
    FECHA(5), //
    COMBO_BOX_SELECCION_UNICA(6), //
    COMBO_BOX_SELECCION_MULTIPLE(7), //
    CANTIDAD_CUOTAS(8), //
    PORCENTAJE_CUOTA_INICIAL(9), //
    ;

    private int codigo;

    private EnumTipoVariableCondicion(int codigo) {
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
