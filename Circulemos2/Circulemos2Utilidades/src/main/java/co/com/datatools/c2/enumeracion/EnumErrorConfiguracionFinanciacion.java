package co.com.datatools.c2.enumeracion;

/**
 * Enum que permite identificar los errores posibles que pueden encontrarse al momento de validacion del formulario que es cargado dinamicamente.
 * 
 * Los codigos que comienzan con codigo "2" Son errores de validaciones de campos cargados dinamicamente en la interfaz.
 * 
 * Los codigos de error que comienzan con codigo "1" Son errores de validaciones del objeto ConfiguracionFinanciacion.
 * 
 * Los codigos de error que comienzan con codigo "3" Son errores de validaciones de los campos de Detalle Cantidad Cuota.
 * 
 * Los codigos de error que comienzan con codigo "4" Son errores de validaciones de los campos de Detalle Porcentaje Cuota Inicial
 * 
 * @author luis.forero
 * 
 */
public enum EnumErrorConfiguracionFinanciacion {

    CAMPO_VALIDO(0, "Campo validado correctamente."), //
    CONFIGURACION_FINANCIACION_VALIDA(0, "Configuraci�n de Financiaci�n validada."), //
    CONFIGURACION_FINANCIACION_REGISTRADA(0, "Configuraci�n de Financiaci�n registrada."), //
    CONFIGURACION_FINANCIACION_MODIFICADA(0, "Configuraci�n de Financiaci�n actualizada."), //
    ERROR_VALIDACION_CAMPO_TIP_RANGO(2001, "El rango de valores del campo no es correcta."), //
    ERROR_VALIDACION_CAMPO_TIP_INDIVIDUAL(2002, "El valor del campo no es correcta."), //
    ERROR_VALIDACION_CAMPO_TIP_COMPARACION(2003, "El valor del campo no v�lido."), //
    ERROR_VALIDACION_CONFIGURACION_FINANCIACION(1000, "La configuraci�n de financiaci�n no fue validada correctamente."), //
    ERROR_FECHA_FIN_MENOR_FECHA_INI(1001,
            "La fecha final de vigencia de la configuraci�n es menor a la fecha inicial de vigencia."), //
    ERROR_FECHA_INI_IGUAL_MENOR(1002, "La fecha inicial de vigencia de la configuraci�n deb� ser mayor a la actual."), //
    ERROR_FECHAS_VIGENCIA_CRUZADAS(1002,
            "Las fechas de vigencia ingresadas se cruzan con otras configuraciones de Financiaci�n."), //
    ERROR_VALIDACION_CONFIGURACION_UTILIZADA(1003, "La configuraci�n de financiaci�n est� siendo utilizada."), //
    ERROR_VALIDACION_CAMPO_REQUERIDO(1004, "Campo Requerido"), //
    ERROR_CONFIGURACION_CERRADA(1005, "La configuraci�n de financiaci�n se encuentra cerrada."), //
    ERROR_DETALLE_CANTIDAD_CUOTAS_NO_VALIDADO(3, "Detalle Cantidad Cuota no validado."), //
    ERROR_VALIDACION_CAMPO_DCC_CANT_CUOTAS(3001,
            "Cantidad M�xima de Cuotas que debe ser mayor o igual a Cantidad M�nimo de Cuotas."), //
    ERROR_VALIDACION_CAMPO_ENTERO_POSITIVO(3002, "Campo debe ser positivo."), //
    ERROR_DETALLE_PORCENTAJE_CUOTA_INI_NO_VALIDADO(4000, "Detalle Porcentaje Cuota Inicial no validado."), //
    ERROR_VALIDACION_CAMPO_DPCI_PORC_FIN_CI(4001,
            "Porcentaje de la financiaci�n para cuota inicial debe ser Mayor a 0 Menor o igual a 100."), // Error para valiacion de porcentaje de la
    // financiacion para cuota inicial
    DETALLE_CANTIDAD_CUOTAS_VALIDADO(0, "Detalle Cantidad Cuotas Validado"), //

    ELIMINACION_CONFIGURACION_FINAN_SATISFACTORIA(0,
            "La Eliminaci�n de la Configuraci�n de Financiaci�n se llevo a cabo satisfactoriamente."), //
    ERROR_ELIMINACION_CONFIGURACION_FINAN(5, "No se llevo a cabo la eliminaci�n del registro seleccionado."), //
    ERROR_CONFIGURACION_FINANCIACION_SIN_CERRAR(6,
            "Existe una configuraci�n de financiaci�n sin cerrar en el sistema. Deb� llevar a cabo su cierre."), //
    ;

    private int codigoError;
    private String descripcion;

    private EnumErrorConfiguracionFinanciacion(int codigoError, String decripcion) {
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
