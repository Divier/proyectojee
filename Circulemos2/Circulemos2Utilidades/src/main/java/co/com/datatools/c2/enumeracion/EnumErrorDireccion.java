package co.com.datatools.c2.enumeracion;

/**
 * Tipos de errores q pueden surgir de la validacion de una direccion CU_CIR20_DAT_ADM_005
 * 
 * @author felipe.martinez
 */
public enum EnumErrorDireccion {

    DIRECCION_INVALIDA("La dirección es inválida"), //
    TIPO_VIA_PRINCIPAL_INVALIDO("El tipo de vía principal es inválido"), //
    TIPO_VIA_PRINCIPAL_OBLIGATORIO("El tipo de vía principal es obligatorio"), //
    TIPO_VIA_SECUNDARIA_INVALIDO("El tipo de vía secundaria es inválido"), //
    TIPO_VIA_SECUNDARIA_INVALIDO_IGUAL_TVP("El tipo de vía secundaria es inválido: igual a tipo de vía primaria"), //
    TIPO_VIA_SECUNDARIA_OBLIGATORIO("El tipo de vía secundaria es obligatorio"), //
    NOMBRE_VIA_PRINCIPAL_INVALIDO("El nombre de vía principal es inválido"), //
    NOMBRE_VIA_PRINCIPAL_OBLIGATORIO("El nombre de vía principal es obligatorio"), //
    NOMBRE_VIA_SECUNDARIA_INVALIDO("El nombre de vía secundaria es inválido"), //
    NOMBRE_VIA_SECUNDARIA_OBLIGATORIO("El nombre de vía secundaria es obligatorio"), //
    NUMERO_VIA_PRINCIPAL_INVALIDO("El número de vía principal es inválido"), //
    NUMERO_VIA_PRINCIPAL_OBLIGATORIO("El número de vía principal es obligatorio"), //
    NUMERO_VIA_SECUNDARIA_INVALIDO("El número de vía secundaria es inválido"), //
    NUMERO_VIA_SECUNDARIA_OBLIGATORIO("El número de vía secundaria es obligatorio"), //
    LETRA_VIA_PRINCIPAL_INVALIDA("La letra de vía principal es inválida"), //
    LETRA_VIA_PRINCIPAL_INVALIDA_NUMERICO(
            "La letra de vía principal es inválida: solo para tipo de vía principal numérico"), //
    LETRA_VIA_SECUNDARIA_INVALIDA("La letra de vía secundaria es inválida"), //
    LETRA_VIA_SECUNDARIA_INVALIDA_NUMERICO(
            "La letra de vía secundaria es inválida: solo para tipo de vía secundaria numérico"), //
    BIS_VIA_PRINCIPAL_INVALIDO("El bis de vía principal es inválido"), //
    BIS_VIA_PRINCIPAL_INVALIDO_NUMERICO(
            "El bis de vía principal es inválido: solo para tipo de vía principal numérico"), //
    BIS_VIA_SECUNDARIA_INVALIDO("El bis de vía principal es inválido"), //
    BIS_VIA_SECUNDARIA_INVALIDO_NUMERICO(
            "El bis de vía principal es inválido: solo para tipo de vía secundaria numérico"), //
    LETRA_BIS_VIA_PRINCIPAL_INVALIDA("La letra bis de vía principal es inválida"), //
    LETRA_BIS_VIA_PRINCIPAL_INVALIDA_BIS("La letra bis de vía principal es inválida: solo si existe bis vía principal"), //
    LETRA_BIS_VIA_PRINCIPAL_INVALIDA_NUMERICO(
            "La letra bis de vía principal es inválida: solo para tipo de vía principal numérico"), //
    LETRA_BIS_VIA_SECUNDARIA_INVALIDA("La letra bis de vía secundaria es inválida"), //
    LETRA_BIS_VIA_SECUNDARIA_INVALIDA_BIS(
            "La letra bis de vía secundaria es inválida: solo si existe bis vía secundaria"), //
    LETRA_BIS_VIA_SECUNDARIA_INVALIDA_NUMERICO(
            "La letra bis de vía secundaria es inválida: solo para tipo de vía secundaria numérico"), //
    CARDINALIDAD_VIA_PRINCIPAL_INVALIDA("La cardinalidad de vía principal es inválida"), //
    CARDINALIDAD_VIA_SECUNDARIA_INVALIDA("La cardinalidad de vía secundaria es inválida"), //
    CARDINALIDAD_VIA_SECUNDARIA_INVALIDA_IGUAL_CVP(
            "La cardinalidad de vía secundaria es inválida: igual a cardinalidad vía primaria"), //
    NUMERO_PLACA_DOMICILIARIA_INVALIDA("El número de placa domiciliaria es inválida: debe estar entre 0 y 99"), //
    COMPLEMENTO_INVALIDO("La dirección es inválida"), //
    COMPLEMENTO_OBLIGATORIO("La dirección es obligatoria si el tipo de vía principal es Sin Identificar"), //
    COMPLEMENTO_UNICO("La dirección es el único dato si el tipo de vía principal es Sin Identificar"), //
    PAIS_INVALIDO("El código de país es inválido"), //
    PAIS_OBLIGATORIO("El país es obligatorio"), //
    DEPARTAMENTO_INVALIDO("El código de departamento es inválido"), //
    DEPARTAMENTO_OBLIGATORIO("El departamento es obligatorio"), //
    MUNICIPIO_INVALIDO("El código de municipio es inválido"), //
    MUNICIPIO_OBLIGATORIO("El municipio es obligatorio"), //
    LOCALIDAD_INVALIDO("El código de localidad es inválido"), //
    ;

    private String desc;

    EnumErrorDireccion(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
