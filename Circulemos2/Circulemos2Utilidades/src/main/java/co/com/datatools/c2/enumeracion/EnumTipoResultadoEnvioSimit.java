package co.com.datatools.c2.enumeracion;

public enum EnumTipoResultadoEnvioSimit {

    ARCHIVO_GENERADO(1, "Archivo generado"), //
    FALLO_ERROR_CONEXION_REPO(2, "Fallo - Error conexión al repositorio"), //
    ;

    private int codigo;
    private String nombre;

    EnumTipoResultadoEnvioSimit(int codigo, String nombre) {
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
