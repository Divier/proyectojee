package co.com.datatools.datatimer.enumeraciones;

/**
 * @author divier.casas
 * 
 */
public enum EnumEstadosTrigger {

    ACTIVO(1, "Activo"), //
    INACTIVO(2, "Inactivo"), //
    ELIMINADO(3, "Eliminado");

    private int id;
    private String nombre;

    private EnumEstadosTrigger(int id, String nombre) {
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