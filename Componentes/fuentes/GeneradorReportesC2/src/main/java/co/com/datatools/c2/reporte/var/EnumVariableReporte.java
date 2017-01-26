package co.com.datatools.c2.reporte.var;

/**
 * Variables especiales a agregar en los XML de encabezado y cabecera de una plantilla o en el contenido del reporte.<br>
 * Deben aparecer con el formato #{...}, ejem: #{fecha}
 * 
 * @author felipe.martinez
 * 
 */
public enum EnumVariableReporte {
    fecha, // Fecha actual del sistema
    hora, // Hora actual del sistema
    nombre_reporte, // Nombre de reporte generado
    ;
}
