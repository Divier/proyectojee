package co.com.datatools.c2.enumeracion;

public enum EnumTipoDeudor {
    PRINCIPAL(1), SOLIDARIO(2);

    private int pk;

    EnumTipoDeudor(int pk) {
        this.pk = pk;
    }

    public int getValor() {
        return pk;
    }

}
