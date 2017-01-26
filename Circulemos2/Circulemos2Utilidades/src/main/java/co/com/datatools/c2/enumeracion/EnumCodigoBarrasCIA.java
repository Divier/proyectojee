package co.com.datatools.c2.enumeracion;

public enum EnumCodigoBarrasCIA {

    NUMERO_CUENTA("NUMERO_CUENTA"), //
    NUMERO_COMPARENDO("NUMERO_COMPARENDO"), //
    VALOR_PAGAR_REFERENCIA("VALOR_PAGAR_REFERENCIA"), //
    FECHA("FECHA");

    private String key;

    EnumCodigoBarrasCIA(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
