package co.com.datatools.c2.enumeracion;

public enum EnumLimite {

    MENOR_QUE("<"), //
    IGUAL_QUE("="), //
    MAYOR_QUE(">"), //
    MENOR_IGUAL_QUE("<="), //
    MAYOR_IGUAL_QUE(">="), //
    ;
    private String limite;

    private EnumLimite(String limite) {
        this.limite = limite;
    }

    public String getLimite() {
        return limite;
    }

}
