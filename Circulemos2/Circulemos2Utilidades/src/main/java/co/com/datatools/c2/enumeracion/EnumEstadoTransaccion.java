package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoTransaccion implements SearchableEnumeration<String> {
    REGISTRADO("-1"), // Define un error o rechazo
    INCONSISTENTE("0"), // Define el estado satisfactorio
    RECHAZADO("1"), // Pago recibido sin procesar
    ;
    String codigo;

    private EnumEstadoTransaccion(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getValue() {
        return codigo;
    }

}
