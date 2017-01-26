package co.com.datatools.c2.enumeracion;

public enum EnumCardindalidadDireccion {

    SUR(1), //
    NORTE(2), //
    ESTE(3), //
    OESTE(4), //
    ;

    private int codigo;

    private EnumCardindalidadDireccion(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
