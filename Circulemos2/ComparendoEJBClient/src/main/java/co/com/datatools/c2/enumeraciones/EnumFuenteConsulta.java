package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumFuenteConsulta implements SearchableEnumeration<Integer> {

    SIMIT(1), //
    CIRCULEMOS_1(2), //
    CIRCULEMOS_2(3), //
    RUNT(4), //
    ;

    private int id;

    private EnumFuenteConsulta(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
