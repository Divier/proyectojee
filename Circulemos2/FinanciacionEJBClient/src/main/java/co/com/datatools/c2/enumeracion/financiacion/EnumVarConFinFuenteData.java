package co.com.datatools.c2.enumeracion.financiacion;

/**
 * Este Enum marca los separadores y palabras clave para identificar y procesar la fuente de los datos en las variables de condicion de las
 * financiaciones.
 * 
 * @author luis.forero
 * 
 */
public enum EnumVarConFinFuenteData {

    /*
     * Marca el separador de los items del catalogo que hay dentro del campo fuente data de las variables de condicion de financiacion
     */
    CHAR_SEPARATOR_ITEMS(","), //
    /*
     * Marca el separador que permite identificar el nombre de la variable y su valor correspondiente de los catalogosf
     */
    CHAR_SEPARATOR_VALUES(":"), //
    /*
     * Marca el identificador de una fuente de datos desde una consulta JPQL
     */
    WORD_KEY_JPQL_VALUE_1("SELECT NEW"), //
    /*
     * Marca el identificador de una fuente de datos desde una consulta JPQL
     */
    WORD_KEY_JPQL_VALUE_2("FROM"), //
    ;

    private String value;

    private EnumVarConFinFuenteData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
