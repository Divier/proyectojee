package co.com.datatools.c2.reporte.var;

/**
 * Contenido especial a agregar en los XML de encabezado de un reporte.<br>
 * Deben aparecer con el formato ${...}, ejem: ${filtros}
 * 
 * @author felipe.martinez
 * 
 */
public enum EnumEncabezadoEspecial {
    filtros("CRITERIOS DE CONSULTA INGRESADOS:"), // Procesamiento especial de filtros
    ;

    private String nombre;

    EnumEncabezadoEspecial(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}