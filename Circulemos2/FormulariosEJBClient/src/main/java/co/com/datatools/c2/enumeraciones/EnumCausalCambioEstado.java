package co.com.datatools.c2.enumeraciones;

public enum EnumCausalCambioEstado {

    RESOLUCION(1, 2), //
    ORGANISMO_TRANSITO(2, 3), //
    RESPONSABLE(3, 3), //
    EXTRAVIADO(4, 4), //
    DETERIORADO(5, 4), //
    IMPOSICION(6, 5), //
    DISPOSITIVO_ELECTRONICO(7, 6)//
    ;

    private final Integer id;
    private final Integer idEstadoFormulario;

    private EnumCausalCambioEstado(Integer id, Integer idEstadoFormulario) {
        this.id = id;
        this.idEstadoFormulario = idEstadoFormulario;
    }

    public Integer getValue() {
        return id;
    }

    public Integer getIdEstadoFormulario() {
        return idEstadoFormulario;
    }
}
