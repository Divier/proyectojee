package co.com.datatools.c2.enumeracion;

public enum EnumEstadoConciliacion {

    EN_EJECUCION(1, "En ejecución"), //
    FINALIZADO_ERROR(2, "Finalizado con error"), //
    FINALIZADO_EXITO(3, "Finalizado con éxito"), //
    ;

    private int codigo;
    private String nombre;

    EnumEstadoConciliacion(int codigo, String nombre) {
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
