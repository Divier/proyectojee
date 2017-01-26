package co.com.datatools.c2.enumeraciones;

/**
 * Enum que permite identificar los errores posibles que pueden encontrarse al momento de validacion del formulario que es cargado dinamicamente.
 * 
 * Los codigos que comienzan con codigo "2" Son errores de validaciones de campos cargados dinamicamente en la interfaz.
 * 
 * Los codigos de error que comienzan con codigo "1" Son errores de validaciones del objeto ConfiguracionCoactivo.
 * 
 * @author Dixon.Alvarez
 * 
 */
public enum EnumErrorConfiguracionCoactivo {

    CAMPO_VALIDO(0, "Campo validado correctamente."), //
    CONFIGURACION_COACTIVO_VALIDA(0, "Configuración de coactivo validada."), //
    CONFIGURACION_COACTIVO_REGISTRADA(0, "Configuración de coactivo registrada."), //
    CONFIGURACION_COACTIVO_MODIFICADA(0, "Configuración de coactivo actualizada."), //
    ERROR_VALIDACION_CAMPO_TIP_RANGO(2001, "El rango de valores del campo no es correcta."), //
    ERROR_VALIDACION_CAMPO_TIP_INDIVIDUAL(2002, "El valor del campo no es correcta."), //
    ERROR_VALIDACION_CAMPO_TIP_COMPARACION(2003, "El valor del campo no válido."), //
    ERROR_VALIDACION_CONFIGURACION_COACTIVO(1000, "La configuración de coactivo no fue validada correctamente."), //
    ERROR_FECHA_FIN_MENOR_FECHA_INI(1001,
            "La fecha final de vigencia de la configuración es menor a la fecha inicial de vigencia."), //
    ERROR_FECHA_INI_IGUAL_MENOR(1002, "La fecha inicial de vigencia de la configuración debé ser mayor a la actual."), //
    ERROR_FECHAS_VIGENCIA_CRUZADAS(1002,
            "Las fechas de vigencia ingresadas se cruzan con otras configuraciones de coactivo."), //
    ERROR_VALIDACION_CONFIGURACION_UTILIZADA(1003, "La configuración de coactivo está siendo utilizada."), //
    ERROR_VALIDACION_CAMPO_REQUERIDO(1004, "Campo Requerido"), //
    ERROR_CONFIGURACION_CERRADA(1005, "La configuración de coactivo se encuentra cerrada."), //
    ERROR_DETALLE_CANTIDAD_CUOTAS_NO_VALIDADO(3, "Detalle Cantidad Cuota no validado."), //
    ELIMINACION_CONFIGURACION_COACTIVO_SATISFACTORIA(0,
            "La Eliminación de la Configuración de coactivo se llevo a cabo satisfactoriamente."), //
    ERROR_ELIMINACION_CONFIGURACION_COACTIVO(5, "No se llevo a cabo la eliminación del registro seleccionado."), //
    ERROR_CONFIGURACION_COACTIVO_SIN_CERRAR(6,
            "Existe una configuración de coactivo sin cerrar en el sistema. Debé llevar a cabo su cierre."), //
    ;

    private int codigoError;
    private String descripcion;

    private EnumErrorConfiguracionCoactivo(int codigoError, String decripcion) {
        this.codigoError = codigoError;
        this.descripcion = decripcion;
    }

    public int getCodigoError() {
        return codigoError;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
