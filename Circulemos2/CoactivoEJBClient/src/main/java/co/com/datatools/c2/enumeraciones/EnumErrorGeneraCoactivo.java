package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enum que permite identificar los errores posibles que pueden encontrarse al momento de validacion de una factura para el registro en coactivo.
 * 
 * @author Dixon.Alvarez
 * 
 */
public enum EnumErrorGeneraCoactivo implements SearchableEnumeration<Integer> {

    DIRECCION_INVALIDA(1, "No se encuentran direcciones validas"), //
    PERSONA_SIN_DIRECCION(2, "La persona no tiene asociada una dirección"), //
    EXISTE_COACTIVO(3, "La obligación ya existe en un coactivo"), //
    ESTADO_NO_ACTIVO(4, "El estado de la obligación no está \"Activo\""), //
    CARTERA_NO_EXISTE(5, "No existe cartera para la obligación"), //
    ESTADO_NO_VIGENTE(6, "El estado de la obligación no está \"Vigente\""), //
    NUMERO_FACTURA_NO_EXISTE(7, "El número de factura no existe en el sistema"), //
    VALOR_MINIMO_COACTIVO(8, "No cumple con el valor mínimo para un coactivo"), //
    OBLIGACION_NO_NOTIFICADA(9, "La obligación no ha sido notificada"), //
    EXISTE_PRECOACTIVO(10, "La obligación ya fue procesada previamente en el sistema"), //
    FACTURA_REPETIDA_ARCHIVO(11, "El número de factura se encuentra repetido en el archivo cargado"), //
    ERROR_INESPERADO(-1, "Error inesperado en validacion"), //
    CAMPOS_VACIOS(-2, "Campos vacios"),//
    ; //

    private int codigoError;
    private String descripcion;

    private EnumErrorGeneraCoactivo(int codigoError, String decripcion) {
        this.codigoError = codigoError;
        this.descripcion = decripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public Integer getValue() {
        return codigoError;
    }

}
