package co.com.datatools.seguridad.utilidades;

/**
 * Enumeracion que contiene los valores para el catalogo de estados de usuario
 * 
 * @author claudia.rodriguez
 * 
 */
public enum EnumEstadoUsuario {

    ACTIVO(1, "Activo"),//
    INACTIVO(2, "Inactivo"),//
    CANCELADO(3, "Cancelado");

    private int id;
    private String nombre;

    private EnumEstadoUsuario(int id, String nombre) {
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
