package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoComparendo implements SearchableEnumeration<Integer> {

    REGISTRADO(1), //
    VIGENTE(2), //
    PAGADO(3), //
    FINANCIADO(4), //
    IMPUGNADO(5), //
    CONVENIO(6), //
    ANULADO(7), //
    EXONERADO(8), //
    PREFINANCIADO(9), //
    PAGADO_FINANCIACION(10), //
    IMPUGNADO_AXIS(12), //
    COACTIVO(13), //
    ;

    private int codigo;

    EnumEstadoComparendo(int codigo) {
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
