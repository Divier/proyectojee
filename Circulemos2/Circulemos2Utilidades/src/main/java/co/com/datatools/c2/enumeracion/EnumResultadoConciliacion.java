package co.com.datatools.c2.enumeracion;

public enum EnumResultadoConciliacion {

    CORRECTO(1, "Correcto"), //
    INCORRECTO(2, "Incorrecto"), //
    ;

    private int codigo;
    private String nombre;

    EnumResultadoConciliacion(int codigo, String nombre) {
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
