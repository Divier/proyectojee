package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Tipos de errores que pueden surgir a partir del registro de una resolucion (o acto administrativo) [CU_CIR20_DAT_COM_009]
 * 
 * @author rodrigo.cruz
 */
public enum EnumErrorResolucion implements SearchableEnumeration<Integer> {

    RESOLUCION_INVALIDA(1000, "La resoluci�n es inv�lida"), //
    NO_EXISTE_COMPARENDO(1001, "No existe comparendo"), //
    NO_EXISTE_CARTERA(1002, "La cartera no ha sido creada o tiene saldo cero"), //
    COMPARENDO_PROCESO_IMPUGNACION(1003, "El comparendo est� en proceso de impugnaci�n y no ha sido anulado"), //
    FECHA_ORIGEN_INDETERMINADA(1004, "Fecha de origen del comparendo no determinada"), //
    TIPO_CONFIGURACION(1005, "El tipo de configuraci�n de generaci�n de resoluciones no es v�lido"), //
    TERMINOS_NO_CONFIGURADOS(1006, "T�rminos de actos administrativos no configurados"), //
    VALIDADOS_TERMINOS_NECESARIOS(1007, "Se han validado los t�rminos necesarios"), //
    EXISTE_RESOLUCION(1008, "Acto administrativo ya generado"), //
    ;

    private Integer code;
    private String desc;

    EnumErrorResolucion(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getValue() {
        return code;
    }

}
