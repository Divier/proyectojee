/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

/**
 * @author sergio.torres
 * 
 */
public enum EnumCampoInconsistenciaAgente {
    NUMERO_FACTURA("N�mero factura"), //
    FECHA_IMPOSICION("Fecha y hora de imposici�n"), //
    PLACA_AGENTE("C�digo operador agente"), //
    FECHA_VALIDACION("Fecha y hora de validaci�n"), //
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
