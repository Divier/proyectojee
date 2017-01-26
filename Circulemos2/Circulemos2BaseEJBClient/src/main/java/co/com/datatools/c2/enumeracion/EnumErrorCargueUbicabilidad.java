package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Errores en cargue de ubicabilidad
 * 
 * @author julio.pinzon
 *
 */
public enum EnumErrorCargueUbicabilidad implements SearchableEnumeration<Integer> {

    CAMPOS_VACIOS(1, "Campos vacíos"), //
    INFORMACION_INVALIDA(2, "Información de ubicabilidad no valida"), //
    NO_EXISTE_TIPO_DOCUMENTO(3, "No existe el tipo de documento"), //
    ERROR_INESPERADO(4, "Error inesperado de cargue masivo"), //
    PERSONA_NO_EXISTE(5, "Persona no existe"), //
    TIPO_DIRECCION_NO_EXISTE(6, "No existe el tipo de dirección"), //
    TIPO_DATO_CALIFICACION_INVALIDO(7, "Valor inválido calificación"), //
    VALOR_VALIDACION_INVALIDO(8, "Valor validación válido"), //
    TIPO_TELEFONO_NO_EXISTE(9, "No existe el tipo de telefono"), //
    PROVINCIA_NO_EXISTE(10, "No existe la provincia"), //
    CANTON_NO_EXISTE(11, "No existe el cantón para la provincia"), //
    PARROQUIA_NO_EXISTE(12, "No existe la parroquía para el cantón"), //
    VALOR_CALIFICACION_INVALIDO(13, "Valor inválido calificación"), //
    TIPO_IDENTIFICACION_NO_EXISTE(14, "Tipo identificación no existe"), //
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
