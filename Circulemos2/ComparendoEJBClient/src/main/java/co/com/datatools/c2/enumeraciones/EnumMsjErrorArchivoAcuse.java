package co.com.datatools.c2.enumeraciones;

/**
 * CU_CIR20_DAT_COM_054: Contiene el listado de errores posibles para el cargue de archivos de acuse de notificacion de comparendos
 * 
 * @author rodrigo.cruz
 * 
 */
public enum EnumMsjErrorArchivoAcuse {

    FALTA_VALOR(1, "Falta valor"), //
    LONGITUD_INVALIDA(2, "Longitud inv�lida"), //
    FORMATO_INVALIDO(3, "Formato inv�lido"), //
    COMPARENDO_NO_REGISTRADO(4, "El comparendo no se encuentra registrado en el organismo seleccionado"), //
    COMPARENDO_NOTIFICADO(5, "El comparendo ya fue notificado"), //
    FECHA_NOTIF_MAYOR_FECHA_ACTUAL(6, "Fecha de notificaci�n mayor que la fecha actual"), //
    FECHA_NOTIF_ANTERIOR_FECHA_IMPOS(7, "Fecha de notificaci�n anterior a fecha de imposici�n"), //
    FECHA_IMPOS_MAYOR_FECHA_ACTUAL(8, "Fecha de imposici�n mayor que la fecha actual"), //
    ;

    private int id;
    private String descripcion;

    EnumMsjErrorArchivoAcuse(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
