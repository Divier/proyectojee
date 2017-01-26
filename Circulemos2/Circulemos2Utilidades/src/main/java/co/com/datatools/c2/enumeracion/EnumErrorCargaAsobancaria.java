package co.com.datatools.c2.enumeracion;

/**
 * Contiene los errores que se pueden dar al validar el archivo de recaudo de Asobancaria. Los valores corresponden a la tabla
 * error_carga_asobancaria.
 * 
 * @author robert.bautista
 * @since 2013-11-19
 */
public enum EnumErrorCargaAsobancaria {

    NUMERO_LINEAS_INVALIDO(1, "N�mero de l�neas inv�lido."), //
    LONGITUD_LINEA_INVALIDO(2, "Longitud de l�nea inv�lido."), //
    TIPO_REGISTRO_ENCABEZADO_INVALIDO(3, "Tipo de registro del encabezado inv�lido."), //
    REGISTRO_ENCABEZADO_CONTENIDO_INVALIDO(4, "Contenido del registro del encabezado inv�lido."), //
    REGISTRO_ENCABEZADO_FORMATO_FECHA_INVALIDO(5, "Formato de fecha de registro encabezado inv�lido."), //
    REGISTRO_ENCABEZADO_FECHA_INVALIDA(6, "Fecha de registro encabezado inv�lida."), //
    REGISTRO_ENCABEZADO_FORMATO_HORA_INVALIDO(7, "Formato de hora inv�lido para registro de encabezado."), //
    REGISTRO_ENCABEZADO_HORA_INVALIDA(8, "Hora de registro de encabezado inv�lida."), //
    REGISTRO_ENCABEZADO_MODIFICADOR_ARCHIVO_INVALIDO(9,
            "Campo modificador de archivo inv�lido para registro de encabezado."), //
    REGISTRO_ENCABEZADO_CUENTA_INVALIDA(10, "Cuenta inv�lida en registro de encabezado."), //
    REGISTRO_ENCABEZADO_RESERVADO_INVALIDO(11, "Campo reservado inv�lido para registro de encabezado."), //
    TIPO_REGISTRO_ENCABEZADO_LOTE_INVALIDO(12, "Campo tipo del registro encabezado lote inv�lido."), //
    REGISTRO_ENCABEZADO_LOTE_SERVICIO_INVALIDO(13, "Campo servicio para registro encabezado lote inv�lido."), //
    REGISTRO_ENCABEZADO_LOTE_NUMERO_LOTE_INVALIDO(14, "Campo n�mero lote de registro encabezado lote inv�lido."), //
    REGISTRO_ENCABEZADO_LOTE_RESERVADO_INVALIDO(15, "Campo reservado de registro encabezado de lote inv�lido."), //
    TIPO_REGISTRO_DETALLE_INVALIDO(16, "Campo tipo del registro detalle inv�lido."), //
    REGISTRO_DETALLE_REFERENCIA_PRINCIPAL_INVALIDA(17, "Referencia principal del registro detalle inv�lida."), //
    REGISTRO_DETALLE_VALOR_RECAUDADO_INVALIDO(18, "Valor recaudado del registro detalle inv�lido."), //
    REGISTRO_DETALLE_PROCEDENCIA_PAGO_INVALIDO(19, "Procedencia de pago del registro detalle inv�lida."), //
    REGISTRO_DETALLE_MEDIO_PAGO_INVALIDO(20, "Medio de pago del registro detalle inv�lido."), //
    REGISTRO_DETALLE_NUMERO_OPERACION_INVALIDO(21, "N�mero de operaci�n del registro detalle inv�lido."), //
    REGISTRO_DETALLE_NUMERO_AUTORIZACION_INVALIDO(22, "N�mero autorizaci�n del registro detalle inv�lido."), //
    REGISTRO_DETALLE_CODIGO_ENTIDAD_DEBITADA_INVALIDO(23, "C�digo entidad debitada del registro detalle inv�lido."), //
    REGISTRO_DETALLE_CODIGO_SUCURSAL_INVALIDO(24, "C�digo de la sucursal del registro detalle inv�lido."), //
    REGISTRO_DETALLE_SECUENCIA_INVALIDO(25, "N�mero de secuencia inv�lido del registro detalle."), //
    REGISTRO_DETALLE_CAUSA_DEVOLUCION_INVALIDO(26, "Causa devoluci�n del registro detalle inv�lido."), //
    REGISTRO_DETALLE_RESERVADO_INVALIDO(27, "Campo reservado del registro detalle inv�lido."), //
    TIPO_REGISTRO_CONTROL_LOTE_INVALIDO(28, "Tipo del registro de control de lote inv�lido."), //
    REGISTRO_CONTROL_LOTE_TOTAL_REGISTROS_INVALIDO(29, "Total de registros del registro control lote inv�lido."), //
    REGISTRO_CONTROL_LOTE_TOTAL_RECAUDO_INVALIDO(30, "Total recaudado del registro control lote inv�lido."), //
    REGISTRO_CONTROL_LOTE_NUMERO_LOTE_INVALIDO(31, "N�mero lote del registro control lote inv�lido."), //
    REGISTRO_CONTROL_LOTE_RESERVADO_INVALIDO(32, "Campo reservado del registro control lote inv�lido."), //
    REGISTRO_CONTROL_LOTE_TOTAL_RECAUDO_DESIGUAL(33,
            "Total recaudado del registro control lote no corresponde a la suma de detalles."), //
    REGISTRO_CONTROL_LOTE_TOTAL_REGISTROS_DESIGUAL(34,
            "Total registros del registro control lote no corresponde al n�mero de detalles."), //
    REGISTRO_CONTROL_LOTE_NUMERO_LOTE_DESIGUAL(35, "N�mero del lote del registro control lote inv�lido."), //
    TIPO_REGISTRO_CONTROL_ARCHIVO_INVALIDO(36, "Campo tipo de registro del registro control de archivo inv�lido."), //
    REGISTRO_CONTROL_ARCHIVO_TOTAL_REGISTROS_INVALIDO(37,
            "Total de registros del registro control de archivo inv�lido."), //
    REGISTRO_CONTROL_ARCHIVO_TOTAL_RECAUDO_INVALIDO(38, "Total recaudado del registro control de archivo inv�lido."), //
    REGISTRO_CONTROL_ARCHIVO_RESERVADO_INVALIDO(39, "Campo reservado del registro control de archivo inv�lido."), //
    REGISTRO_CONTROL_ARCHIVO_TOTAL_REGISTROS_DESIGUAL(40,
            "Total de registros del registro control de archivo no corresponde al n�mero de detalles."), //
    REGISTRO_CONTROL_ARCHIVO_TOTAL_RECAUDO_DESIGUAL(41,
            "Total recaudado del registro control de archivo no corresponde a la suma de los detalles."), //
    ORDEN_ESTRUCTURA_INVALIDO(42, "Orden de la estructura del archivo inv�lido."), //
    REFERENCIA_PAGO_INEXISTENTE(43, "Referencia de pago inexistente."), //
    MULTIPLES_OBLIGACIONES_POR_REFERENCIA(44, "M�ltiples obligaciones para la referencia de pago."), //
    REFERENCIA_SIN_OBLIGACION_NI_REFERENCIA_VIRTUAL(45,
            "La referencia no est� asociada a una obligaci�n real o virtual."), //
    PERSONA_CON_ORGANISMO_DISTINTO(46, "La persona est� asociada a otro organismo de tr�nsito."), //
    PROBLEMAS_CONSULTANDO_DEUDOR_REFERENCIA_VIRTUAL(47, "No se pudo consultar el deudor de la referencia virtual."), //
    RESULTADO_AFECTAR_CARTERA_COMPARENDO_PAGO(48, "No se logr� afectar la cartera por el comparendo."), //
    ERROR_AFECTAR_CARTERA_COMPARENDO_PAGO(49, "Error al afectar la cartera por el comparendo."), //
    ERROR_DESCONOCIDO_PROCESANDO_REGISTRO(50, "Error desconocido al procesar el registro."), //
    ;

    /**
     * Contiene el codigo del error
     */
    final private int codigo;

    final private String descripcion;

    private EnumErrorCargaAsobancaria(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

}
