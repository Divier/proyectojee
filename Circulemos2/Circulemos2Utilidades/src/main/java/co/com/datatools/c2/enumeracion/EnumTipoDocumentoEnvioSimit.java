package co.com.datatools.c2.enumeracion;

public enum EnumTipoDocumentoEnvioSimit {

    COMPARENDO(1, "Comparendo"), PAGOS(2, "Pagos"), RESOLUCION(3, "Resolución");

    private int codigo;
    private String nombre;

    EnumTipoDocumentoEnvioSimit(int codigo, String nombre) {
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
