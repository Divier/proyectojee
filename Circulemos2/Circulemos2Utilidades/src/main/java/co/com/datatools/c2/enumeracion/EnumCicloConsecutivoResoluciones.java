package co.com.datatools.c2.enumeracion;

public enum EnumCicloConsecutivoResoluciones {

    COMIENZA_CADA_ANIO(1, "COMIENZA_CADA_ANIO"), //
    CONTINUA_ULTIMO_NUMERO_ASIGNADO(2, "CONTINUA_ULTIMO_NUMERO_ASIGNADO"), //
    ;

    private int codigo;
    private String nombre;

    EnumCicloConsecutivoResoluciones(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

}
