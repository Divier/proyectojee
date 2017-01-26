package co.com.datatools.seguridad.utilidades;

/**
 * Enumeracion con los diferentes valores posibles de tipos de datos para los parametros de seguridad
 * 
 * @author claudia.rodriguez
 * 
 */
public enum EnumTipoDato {

    ENTERO(1, "Numerico Entero"),//
    DECIMAL(2, "Numerico Decimal"),//
    TEXTO(3, "Texto"),//
    FECHA(4, "Fecha"),//
    HORA(5, "Hora"),//
    FECHA_HORA(6, "Fecha y hora"),//
    SI_NO(7, "SI/NO");

    private int id;
    private String nombre;

    private EnumTipoDato(int id, String nombre) {
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
