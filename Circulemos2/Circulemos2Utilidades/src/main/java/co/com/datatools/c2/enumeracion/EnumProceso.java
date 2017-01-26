package co.com.datatools.c2.enumeracion;

/**
 * Enumeracion para los registros de la tabla proceso
 * 
 * @author felipe.martinez
 * 
 */
public enum EnumProceso {

    INGRESAR_COMPARENDO(1001, "/Ingresar Comparendo"), //
    NOTIFICACION_COMPARENDO(1002, "/Notificacion Comparendo"), //
    PROCESO_IMPUGNACION(1003, "/Proceso Impugnacion"), //
    PROCESO_FINANCIACION(1004, "/Proceso Financiacion"), //
    PROCESO_COBRO_COACTIVO(1005, "/Proceso Cobro Coactivo"), //
    RECTIFICAR_COMPARENDO(1006, "/Rectificar Comparendo"), //
    GENERAR_ACTO_ADMINISTRATIVO_AUTO(1012), //
    GENERAR_CARTERA_AUTO(1015), //
    ENVIAR_EMAIL(1023), //
    ANULACION_COMPARENDO(1011)//
    ;

    private int codigo;
    private String ruta;

    EnumProceso(int codigo) {
        this.codigo = codigo;
    }

    EnumProceso(int codigo, String ruta) {
        this.codigo = codigo;
        this.ruta = ruta;
    }

    public static EnumProceso buscarEnum(int primaryKey) {
        EnumProceso[] lista = EnumProceso.values();
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].getCodigo() == primaryKey) {
                return lista[i];
            }
        }
        return null;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getRuta() {
        return ruta;
    }

}
