package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Determina el ingreso del comparendo origen coparendo
 * 
 * @author giovanni.velandia
 * 
 */
public enum EnumTipoOrigenComparendo implements SearchableEnumeration<Integer> {

    // Este campo para ecuador es correspondiente a AXIS
    SIMUR(1), //
    ;

    private int codigo;

    EnumTipoOrigenComparendo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public static EnumTipoOrigenComparendo getValue(int value) {
        for (EnumTipoOrigenComparendo e : EnumTipoOrigenComparendo.values()) {
            if (e.codigo == value) {
                return e;
            }
        }
        return null;
    }

}
