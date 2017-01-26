package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumCampoDetalleProcesoContra implements SearchableEnumeration<Integer> {

    VALOR_COMPARENDO_FALLO(1), //
    NUMERO_SOPORTE(2), //
    FECHA_APERTURA_PROCESO(3), //
    NUMERO_PROCESO(4), //
    ;

    private int codigo;

    EnumCampoDetalleProcesoContra(int codigo) {
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
