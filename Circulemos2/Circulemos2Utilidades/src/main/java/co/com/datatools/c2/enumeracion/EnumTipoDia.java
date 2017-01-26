package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los tipos de dias existentes en el sistema con su respectivo valor definido.<br>
 * *************************<br>
 * *_____ TIPO _____** Val.*<br>
 * *************************<br>
 * * DIA CALENDARIO *** 1 **<br>
 * * DIA HABIL ******** 2 **<br>
 * *************************<br>
 * 
 * @author luis.forero (2016-02-11)
 * 
 */
public enum EnumTipoDia implements SearchableEnumeration<Integer> {
    DIA_CALENDARIO(1), //
    DIA_HABIL(2), //
    ;

    private Integer valor;

    private EnumTipoDia(Integer valor) {
        this.valor = valor;
    }

    @Override
    public Integer getValue() {
        return valor;
    }

}
