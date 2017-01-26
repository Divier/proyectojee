package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumCampoTrazabilidadComparendo implements SearchableEnumeration<Integer> {

    ID_TRAZABILIDAD_COMPARENDO(1, -1), //
    CICOMPARENDO(2, -1), //
    ID_ACTIVIDAD(3, -1), //
    FECHA_EJECUCION(4, -1), //
    FECHA_REGISTRO(5, -1), //
    ID_USUARIO(6, -1), //
    USUARIO_EXTERNO(7, 15), //
    ACTIVO(8, -1);

    int codigo;

    /**
     * Longitud maxima q puede tener el campo, -1 si no aplica
     */
    int longitud;

    private EnumCampoTrazabilidadComparendo(int codigo, int longitud) {
        this.codigo = codigo;
        this.longitud = longitud;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getLongitud() {
        return longitud;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
