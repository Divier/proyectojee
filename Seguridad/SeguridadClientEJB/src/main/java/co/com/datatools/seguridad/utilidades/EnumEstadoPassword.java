package co.com.datatools.seguridad.utilidades;

/**
 * Enumeracion que contiene los valores para el catalogo de estados de password
 * 
 * @author claudia.rodriguez
 * 
 */
public enum EnumEstadoPassword {

    ACTIVO(1, "Activo"), //
    VENCIDO(2, "Vencido"), //
    TEMPORAL(3, "Temporal"), //
    BLOQUEADO(4, "Bloqueado");//

    private int id;
    private String nombre;

    private EnumEstadoPassword(int id, String nombre) {
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
