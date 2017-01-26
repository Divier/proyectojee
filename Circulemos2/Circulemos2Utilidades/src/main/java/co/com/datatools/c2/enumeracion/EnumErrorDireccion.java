package co.com.datatools.c2.enumeracion;

/**
 * Tipos de errores q pueden surgir de la validacion de una direccion CU_CIR20_DAT_ADM_005
 * 
 * @author felipe.martinez
 */
public enum EnumErrorDireccion {

    DIRECCION_INVALIDA("La direcci�n es inv�lida"), //
    TIPO_VIA_PRINCIPAL_INVALIDO("El tipo de v�a principal es inv�lido"), //
    TIPO_VIA_PRINCIPAL_OBLIGATORIO("El tipo de v�a principal es obligatorio"), //
    TIPO_VIA_SECUNDARIA_INVALIDO("El tipo de v�a secundaria es inv�lido"), //
    TIPO_VIA_SECUNDARIA_INVALIDO_IGUAL_TVP("El tipo de v�a secundaria es inv�lido: igual a tipo de v�a primaria"), //
    TIPO_VIA_SECUNDARIA_OBLIGATORIO("El tipo de v�a secundaria es obligatorio"), //
    NOMBRE_VIA_PRINCIPAL_INVALIDO("El nombre de v�a principal es inv�lido"), //
    NOMBRE_VIA_PRINCIPAL_OBLIGATORIO("El nombre de v�a principal es obligatorio"), //
    NOMBRE_VIA_SECUNDARIA_INVALIDO("El nombre de v�a secundaria es inv�lido"), //
    NOMBRE_VIA_SECUNDARIA_OBLIGATORIO("El nombre de v�a secundaria es obligatorio"), //
    NUMERO_VIA_PRINCIPAL_INVALIDO("El n�mero de v�a principal es inv�lido"), //
    NUMERO_VIA_PRINCIPAL_OBLIGATORIO("El n�mero de v�a principal es obligatorio"), //
    NUMERO_VIA_SECUNDARIA_INVALIDO("El n�mero de v�a secundaria es inv�lido"), //
    NUMERO_VIA_SECUNDARIA_OBLIGATORIO("El n�mero de v�a secundaria es obligatorio"), //
    LETRA_VIA_PRINCIPAL_INVALIDA("La letra de v�a principal es inv�lida"), //
    LETRA_VIA_PRINCIPAL_INVALIDA_NUMERICO(
            "La letra de v�a principal es inv�lida: solo para tipo de v�a principal num�rico"), //
    LETRA_VIA_SECUNDARIA_INVALIDA("La letra de v�a secundaria es inv�lida"), //
    LETRA_VIA_SECUNDARIA_INVALIDA_NUMERICO(
            "La letra de v�a secundaria es inv�lida: solo para tipo de v�a secundaria num�rico"), //
    BIS_VIA_PRINCIPAL_INVALIDO("El bis de v�a principal es inv�lido"), //
    BIS_VIA_PRINCIPAL_INVALIDO_NUMERICO(
            "El bis de v�a principal es inv�lido: solo para tipo de v�a principal num�rico"), //
    BIS_VIA_SECUNDARIA_INVALIDO("El bis de v�a principal es inv�lido"), //
    BIS_VIA_SECUNDARIA_INVALIDO_NUMERICO(
            "El bis de v�a principal es inv�lido: solo para tipo de v�a secundaria num�rico"), //
    LETRA_BIS_VIA_PRINCIPAL_INVALIDA("La letra bis de v�a principal es inv�lida"), //
    LETRA_BIS_VIA_PRINCIPAL_INVALIDA_BIS("La letra bis de v�a principal es inv�lida: solo si existe bis v�a principal"), //
    LETRA_BIS_VIA_PRINCIPAL_INVALIDA_NUMERICO(
            "La letra bis de v�a principal es inv�lida: solo para tipo de v�a principal num�rico"), //
    LETRA_BIS_VIA_SECUNDARIA_INVALIDA("La letra bis de v�a secundaria es inv�lida"), //
    LETRA_BIS_VIA_SECUNDARIA_INVALIDA_BIS(
            "La letra bis de v�a secundaria es inv�lida: solo si existe bis v�a secundaria"), //
    LETRA_BIS_VIA_SECUNDARIA_INVALIDA_NUMERICO(
            "La letra bis de v�a secundaria es inv�lida: solo para tipo de v�a secundaria num�rico"), //
    CARDINALIDAD_VIA_PRINCIPAL_INVALIDA("La cardinalidad de v�a principal es inv�lida"), //
    CARDINALIDAD_VIA_SECUNDARIA_INVALIDA("La cardinalidad de v�a secundaria es inv�lida"), //
    CARDINALIDAD_VIA_SECUNDARIA_INVALIDA_IGUAL_CVP(
            "La cardinalidad de v�a secundaria es inv�lida: igual a cardinalidad v�a primaria"), //
    NUMERO_PLACA_DOMICILIARIA_INVALIDA("El n�mero de placa domiciliaria es inv�lida: debe estar entre 0 y 99"), //
    COMPLEMENTO_INVALIDO("La direcci�n es inv�lida"), //
    COMPLEMENTO_OBLIGATORIO("La direcci�n es obligatoria si el tipo de v�a principal es Sin Identificar"), //
    COMPLEMENTO_UNICO("La direcci�n es el �nico dato si el tipo de v�a principal es Sin Identificar"), //
    PAIS_INVALIDO("El c�digo de pa�s es inv�lido"), //
    PAIS_OBLIGATORIO("El pa�s es obligatorio"), //
    DEPARTAMENTO_INVALIDO("El c�digo de departamento es inv�lido"), //
    DEPARTAMENTO_OBLIGATORIO("El departamento es obligatorio"), //
    MUNICIPIO_INVALIDO("El c�digo de municipio es inv�lido"), //
    MUNICIPIO_OBLIGATORIO("El municipio es obligatorio"), //
    LOCALIDAD_INVALIDO("El c�digo de localidad es inv�lido"), //
    ;

    private String desc;

    EnumErrorDireccion(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
