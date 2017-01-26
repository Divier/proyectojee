package co.com.datatools.c2.enumeracion;

/**
 * Definicion de posibles errores al momento de la creacion, modificacion o elimninacion de una amnistia
 * 
 * @author luis.forero
 * 
 */
public enum EnumErrorAmnistia {

    AMNISTIA_VALIDA(0, "Amnistía validada satisfactoriamente."), //
    AMNISTIA_CREADA(0, "Amnistía creada."), //
    AMNISTIA_ACTUALIZADA(0, "Amnistía modificada."), //
    AMNISTIA_ELIMINADA(0, "Amnistía eliminada."), //
    CRUCE_AMNISTIA_TIPO_OBLIGACION(1,
            "La amnistía se crusa con otra en el sistema con el mismo tipo de obligación. No se completo la operación."), //
    FECHA_INICIAL_MENOR_IGUAL_ACTUAL(2, "Fecha inicial menor o igual a la actual. No se completo la operación."), //
    FECHA_MAXIMA_OBLIGACION_MAYOR_ACTUAL(3,
            "Fecha máxima de obligación no puede ser superior a la fecha actual. No se completo la operación."), //
    FECHA_FINAL_MENOR_INICIAL(4,
            "Fecha Final no puede ser menor a la Fecha Inicial de la amnistía. No se completo la operación."), //
    FECHA_FINAL_MENOR_ACTUAL(5, "Fecha Final solo puede ser igual a actual no menor. No se completo la operación."), //
    MOD_FECHA_INICIAL_MENOR_IGUAL_ACTUAL(6,
            "Modificación de fecha inicial menor igual a actual. No se completo la operación."), //
    DISTINTO_TIPO_OBLIGACION(7, "Amnistía de distinto tipo de obligación"), //
    AMNISTIA_NO_MODIFICABLE(
            8,
            "Amnistía no modificable por alguna de las siguientes razones: 1)La amnistía ya paso su vigencia. 2) La amnitía no tiene cambios."), //
    AMNISTIA_CON_MISMO_NOMBRE(8, "Amnistía con misma normatividad creada en el sistema.");

    ;
    /**
     * Codigo de error por el que se identifica.
     */
    private int codigo;
    /**
     * Descripcion del error.
     */
    private String descripcion;

    private EnumErrorAmnistia(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
