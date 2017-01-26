package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Errores en cargue de ubicabilidad
 * 
 * @author julio.pinzon
 *
 */
public enum EnumErrorCargueUbicabilidad implements SearchableEnumeration<Integer> {

    CAMPOS_VACIOS(1, "Campos vac�os"), //
    INFORMACION_INVALIDA(2, "Informaci�n de ubicabilidad no valida"), //
    NO_EXISTE_TIPO_DOCUMENTO(3, "No existe el tipo de documento"), //
    ERROR_INESPERADO(4, "Error inesperado de cargue masivo"), //
    PERSONA_NO_EXISTE(5, "Persona no existe"), //
    TIPO_DIRECCION_NO_EXISTE(6, "No existe el tipo de direcci�n"), //
    TIPO_DATO_CALIFICACION_INVALIDO(7, "Valor inv�lido calificaci�n"), //
    VALOR_VALIDACION_INVALIDO(8, "Valor validaci�n v�lido"), //
    TIPO_TELEFONO_NO_EXISTE(9, "No existe el tipo de telefono"), //
    PROVINCIA_NO_EXISTE(10, "No existe la provincia"), //
    CANTON_NO_EXISTE(11, "No existe el cant�n para la provincia"), //
    PARROQUIA_NO_EXISTE(12, "No existe la parroqu�a para el cant�n"), //
    VALOR_CALIFICACION_INVALIDO(13, "Valor inv�lido calificaci�n"), //
    TIPO_IDENTIFICACION_NO_EXISTE(14, "Tipo identificaci�n no existe"), //
    ;

    private Integer id;
    private String descripcion;

    EnumErrorCargueUbicabilidad(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public Integer getValue() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
