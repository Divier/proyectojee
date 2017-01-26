package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoReporteRecaudo implements SearchableEnumeration<Integer> {
    NO_RECIBIDOS(1), //
    INCONSISTENCIAS(2), //
    CONCILIACION(3);

    EnumTipoReporteRecaudo(int id) {
        this.id = id;
    }

    int id;

    @Override
    public Integer getValue() {
        // TODO Auto-generated method stub
        return id;
    }//

}
