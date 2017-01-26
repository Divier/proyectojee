package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoFuenteInformacion implements SearchableEnumeration<Integer> {
    REGISTRADURIA(1), //
    SIM(2), //
    REGISTRADURIA_Y_SIM(3), //
    SICON(4), //
    COMPARENDO(5), //
    FINANCIACION(6), //
    PROCESO_DE_INSPECCION(7), //
    PROCESO_COBRO_PRESUASIVO(8), //
    CATASTRO(9), //
    IMPUESTOS(10), //
    ETB(11), //
    ADMINISTRACION(12), //
    REFERENCIA_PAGO_VIRTUAL(13), //
    ACTUALIZACION_POR_SOLICITUD(15), //
    REGISTROS_PRESENCIALES_INFRACTOR(16), //
    PROCESOS_CONTRAVENCIONALES(17), //
    AXIS(22), //
    CIRCULEMOS2(23), //
    AXIS_EXTERNOS(24), //
    CIUDADANO(25), //
    PROCESO_MANUAL(26), //
    COURIER(16), //
    ;

    private int pk;

    private EnumTipoFuenteInformacion(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }
}
