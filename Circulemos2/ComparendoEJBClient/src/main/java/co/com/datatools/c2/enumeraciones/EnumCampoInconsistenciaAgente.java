/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

/**
 * @author sergio.torres
 * 
 */
public enum EnumCampoInconsistenciaAgente {
    NUMERO_FACTURA("Número factura"), //
    FECHA_IMPOSICION("Fecha y hora de imposición"), //
    PLACA_AGENTE("Código operador agente"), //
    FECHA_VALIDACION("Fecha y hora de validación"), //
    INCOSISTENCIA("Tipo de inconsistencia"), //
    ;

    private String titulo;

    private EnumCampoInconsistenciaAgente(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
