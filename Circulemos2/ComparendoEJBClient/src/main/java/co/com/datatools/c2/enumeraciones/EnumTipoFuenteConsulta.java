package co.com.datatools.c2.enumeraciones;

public enum EnumTipoFuenteConsulta {

    REINCIDENCIA(1), //
    UBICABILIDAD(2), //
    REGISTRO_VEHICULAR(3), //
    ;

    private int id;

    private EnumTipoFuenteConsulta(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
