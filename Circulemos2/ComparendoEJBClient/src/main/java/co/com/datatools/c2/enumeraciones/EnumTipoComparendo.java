package co.com.datatools.c2.enumeraciones;

public enum EnumTipoComparendo {

    COMPARENDO_NACIONAL(1), //
    ACTA_CONTROL(2), //
    PAPELETA(3), //
    ;

    private int codigo;

    EnumTipoComparendo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
