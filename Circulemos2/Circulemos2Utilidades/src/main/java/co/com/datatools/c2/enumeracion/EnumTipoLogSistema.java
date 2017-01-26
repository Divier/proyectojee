package co.com.datatools.c2.enumeracion;

public enum EnumTipoLogSistema {

    LOG_GENERACION_ACTOS_ADMINISTRATIVOS(1), //
    LOG_PROCESO_ENVIO_PAGOS_SIMIT(2), //
    LOG_PROCESO_ENVIO_COMPARENDO_SIMIT(3), //
    LOG_PROCESO_CONCILIACION(4), //
    ;

    private int pk;

    EnumTipoLogSistema(int pk) {
        this.pk = pk;
    }

    public int getValor() {
        return pk;
    }

}
