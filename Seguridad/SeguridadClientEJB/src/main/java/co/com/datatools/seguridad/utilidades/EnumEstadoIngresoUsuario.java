package co.com.datatools.seguridad.utilidades;

/**
 * Enumeracion que contiene los valores para el catalogo de estados de ingreso del usuario
 * 
 * @author claudia.rodriguez
 * 
 */
public enum EnumEstadoIngresoUsuario {

    EXITOSO(1, "Exitoso"), //
    FALLIDO_PW_ERRONEO(2, "Fallido por contraseña errónea"), //
    FALLIDO_PW_BLOQUEADO(3, "Fallido por contraseña bloqueada"), //
    FALLIDO_PW_VENCIDO(4, "Fallido por contraseña vencida"), //
    FALLIDO_USUARIO_NO_ACTIVO(5, "Fallido por usuario no activo"), //
    FALLIDO_CAMBIO_PW(6, "Fallido por contraseña temporal");

    private int id;
    private String nombre;

    private EnumEstadoIngresoUsuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
