package co.com.datatools.c2.enumeracion;

public enum EnumTipoNotificacion {
    NOTIFICACION_CITACIONES(1, "NOTIFICACION DE CITACIONES"), //
    NOTIFICACION_IMPUGNACIONES(2, "NOTIFICACION DE IMPUGNACIONES"), //
    NOTIFICACION_UBICABILIDAD(3, "NOTIFICACION DE UBICABILIDAD"), //
    NOTIFICACION_FINANCIACIONES(4, "NOTIFICACION DE FINANCIACIONES"), //
    ;

    private int id;
    private String nombre;

    private EnumTipoNotificacion(int id, String nombre) {
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