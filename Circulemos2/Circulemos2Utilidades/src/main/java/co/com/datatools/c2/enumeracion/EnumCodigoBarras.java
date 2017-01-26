package co.com.datatools.c2.enumeracion;

public enum EnumCodigoBarras {

    CODIGO_REFERENCIA("CODIGO_REFERENCIA"), //
    VALOR_PAGAR("VALOR_PAGAR"), //
    FECHA("FECHA");

    private String key;

    EnumCodigoBarras(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
