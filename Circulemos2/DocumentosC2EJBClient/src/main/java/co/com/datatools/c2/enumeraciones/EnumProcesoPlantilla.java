package co.com.datatools.c2.enumeraciones;

/**
 * Enumeracion para los registros de la tabla proceso de documentos
 * 
 * @author julio.pinzon 2016-09-06
 * 
 */
public enum EnumProcesoPlantilla {

    PROCESO_IMPUGNACION("1", "/c2/%s/Proceso_Impugnacion-%s"), //
    PROCESO_FINANCIACION("2", "/c2/%s/Proceso_Financiacion-%s"), //
    PROCESO_ANULACION("3", "/c2/%s/Proceso_Anulacion-%s"), //
    PROCESO_COBRO_COACTIVO("4", "/c2/%s/Proceso_Anulacion-%s"), //
    UBICABILIDAD("5", "/c2/%s/Ubicabilidad/Autorizaciones_Actualizacion"), //
    IMPUGNACION_SOLUCION_INMEDIATA("6", "/c2/%s"), //
    IMPUGNACION_SIN_SOLUCION_INMEDIATA("7", "/c2/%s"), //
    IMPUGNACION_FALLO("8", "/c2/%s")//
    ;

    private String codigo;
    private String ruta;

    EnumProcesoPlantilla(String codigo, String ruta) {
        this.codigo = codigo;
        this.ruta = ruta;
    }

    public static EnumProcesoPlantilla buscarEnum(String primaryKey) {
        EnumProcesoPlantilla[] lista = EnumProcesoPlantilla.values();
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].getCodigo().equals(primaryKey)) {
                return lista[i];
            }
        }
        return null;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getRuta() {
        return ruta;
    }

}
