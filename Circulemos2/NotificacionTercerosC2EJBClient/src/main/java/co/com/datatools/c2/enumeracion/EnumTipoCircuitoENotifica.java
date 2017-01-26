package co.com.datatools.c2.enumeracion;

public enum EnumTipoCircuitoENotifica {

    NOTIFICACION("NOTIFICACION"), //
    INFORMACION("INFORMACION"), //
    PAGO("PAGO"), //
    ;

    private String codigo;

    private EnumTipoCircuitoENotifica(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}