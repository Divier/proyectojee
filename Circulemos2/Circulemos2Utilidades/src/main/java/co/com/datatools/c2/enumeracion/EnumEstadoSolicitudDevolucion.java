package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoSolicitudDevolucion implements SearchableEnumeration<Integer> {
    /**
     * Indica que tiene un saldo a favor el infractor.
     */
    SALDO_FAVOR(1), //
    /**
     * La solicitud de devolucion de saldo a favor por parte del infractor fue enviada para ser aprobada.
     */
    PENDIENTE_POR_APROBAR(2), //
    /**
     * La solicitud de devolucion de saldo a favor fue aprobada.
     */
    APROBADO(3), //
    /**
     * La solicitud de devolucion de saldo a favor fue rechazada por el aprobador.
     */
    RECHAZADA_APROBADOR(4), //
    /**
     * Indica que el saldo a favor que tenia el infractor, se utilizó para pagar otra obligacion.
     */
    ABONO_OTRA_OBLIGACION(5), //
    /**
     * Estado en que el saldo a favor fue devuelvo al infractor satisfactoriamente.
     */
    DEVUELTO(6), //
    ;

    private int codigo;

    private EnumEstadoSolicitudDevolucion(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
