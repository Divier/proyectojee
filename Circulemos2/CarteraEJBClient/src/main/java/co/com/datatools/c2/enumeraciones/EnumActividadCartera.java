package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumActividadCartera implements SearchableEnumeration<Integer> {

    REGISTRAR_OBLIGACION_CARTERA(1), //
    ACTIVAR_OBLIGACION_CARTERA(2), //
    PAGO_OBLIGACION(3), //
    FINANCIA_OBLIGACION(4), //
    REGISTRO_RECARGOS_OBLIGACION(5), //
    PAGO_RECARGOS_OBLIGACION(6), //
    EXONERACION_CARTERA(7), //
    CITACION_ANULADA(8), //
    PREFINANCIAR_OBLIGACION(9), //
    INICIAR_COACTIVO(10), //
    ;

    private int codigo;

    EnumActividadCartera(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
