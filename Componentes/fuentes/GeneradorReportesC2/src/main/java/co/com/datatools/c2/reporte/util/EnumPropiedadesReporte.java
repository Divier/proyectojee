package co.com.datatools.c2.reporte.util;

public enum EnumPropiedadesReporte {
    /**
     * Tamaño de la fuente general del contenido del reporte para los formatos que soportan fuentes
     */
    FUENTE_TAMANIO("co.com.datatools.c2.reporte.conector.FUENTE_TAMANIO"),
    /**
     * Tipo de fuente para todo el contenido del formato
     */
    FUENTE_TIPO("co.com.datatools.c2.reporte.conector.FUENTE_TIPO"),
    /**
     * Margen Superior e Inferior para los formatos que soportan margenes de pagina
     */
    MARGEN_VERTICAL("co.com.datatools.c2.reporte.conector.MARGEN_VERTICAL"),
    /**
     * Margen Izquierda y Derecha para los formatos que soportan margenes de pagina
     */
    MARGEN_HORIZONTAL("co.com.datatools.c2.reporte.conector.MARGEN_HORIZONTAL"), ;

    public String value;

    EnumPropiedadesReporte(String value) {
        this.value = value;
    }

}
