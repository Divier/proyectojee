package co.com.datatools.c2.enumeracion;

public enum EnumTipoConsecutivoResoluciones {

    POR_TIPO_ACTO_ADMINISTRATIVO(1, "POR_TIPO_ACTO_ADMINISTRATIVO"), //
    GENERAL_PARA_TODOS(2, "GENERAL_PARA_TODOS"), //
    ;

    private int codigo;
    private String nombre;

    EnumTipoConsecutivoResoluciones(int codigo, String nombre) {
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
