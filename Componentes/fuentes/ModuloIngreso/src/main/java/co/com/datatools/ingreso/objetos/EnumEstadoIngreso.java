package co.com.datatools.ingreso.objetos;

/**
 * enumeración que almacena los estados de los ingresos controlados por el módulo JAAS de ingreso a la aplicación
 * 
 * @author sergio.torres
 * 
 */
public enum EnumEstadoIngreso {
    EXITOSO(1), //
    FALLO_PW_BLOQUEADO(3), //
    FALLO_PW_ERRONEO(2), //
    FALLO_PW_TEMPORAL(6), //
    FALLO_PW_VENCIDO(4), //
    FALLO_USUARIO_NO_ACTIVO(5), //
    ;

    private int identificador;

    private EnumEstadoIngreso(int identificador) {
        this.identificador = identificador;
    }

    public int getidentificador() {
        return identificador;
    }
}
