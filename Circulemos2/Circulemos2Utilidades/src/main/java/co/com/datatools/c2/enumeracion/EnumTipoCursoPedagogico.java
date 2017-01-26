package co.com.datatools.c2.enumeracion;

public enum EnumTipoCursoPedagogico {

    NORMAL(1, "Normal"), //
    ESPECIAL(2, "Especial"), //
    ;

    private int codigo;
    private String nombre;

    EnumTipoCursoPedagogico(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
