package co.com.datatools.c2.enumeraciones;

/**
 * Enumeracion que identifica los campos que puede contener una Configuracion de coactivo incluso sus campos listas de detalle junto con los objetos a
 * los cuales pertenece.
 * 
 * @author Dixon.Alvarez
 * 
 */
public enum EnumCampoConfiguracionCoactivo {

    /**
     * Campo que hace referencia al objeto como tal.
     */
    CONFIGURACION_COACTIVO(1), //
    /**
     * Campo NOMBRE del objeto Configuracion de coactivo
     */
    NOMBRE_CONFIGURACION_COACTIVO(1001), //
    /**
     * Campo Fecha Inicial del objeto Configuracion de coactivo
     */
    FECHA_INICIAL_VIGENCIA(1002), //
    /**
     * Campo Fecha Final del objeto Configuracion de coactivo
     */
    FECHA_FINAL_VIGENCIA(1003), //
    /**
     * Identifica tanto Fecha Inicial Como Fecha Final del objeto Configuracion de coactivo
     */
    FECHAS_VIGENCIA(1023), //
    /**
     * Identifica un campo de condicion presentada en la interfaz.
     */
    CAMPO_CONDICION(2), //
    ;

    /**
     * Identifica la posicion que se define del determinado campo.
     */
    private int index;

    private EnumCampoConfiguracionCoactivo(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
