package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeración utilizada en el caso de uso consultar detalle comparendo. Sirve para identificar los códigos de los campos de un comparendo y
 * corroborar cuáles de estos son editables acorde a la parametrización.
 * 
 * Los valores deben corresponder a los ingresados en la tabla campo_entidad para la entidad comparendo.
 * 
 * @author robert.bautista 25/10/2013
 */
public enum EnumCamposDetalleComparendo implements SearchableEnumeration<Integer> {

    // estas 2 no corresponde a la numeracion EnumCampoEntidad.
    ORGANISMO_NUMERO_COMPARENDO(-1, 8, EnumContenidoCampo.ALFANUMERICO), //
    CONSECUTIVO_NUMERO_COMPARENDO(-2, 12, EnumContenidoCampo.ALFANUMERICO), //
    DESCRIPCION_INFRACCION(-3, 200, EnumContenidoCampo.ALFANUMERICO), //
    // ENCABEZADO//
    CODIGO_ORGANISMO_DE_TRANSITO_DE_LICENCIA(EnumCampoEntidad.CODIGO_ORG_TRANS_LICENCIA_PCC.getValue(), -1,
            EnumContenidoCampo.NUMERICO), //
    TIPO_COMPARENDO(EnumCampoEntidad.TIPO_COMPARENDO_PCC.getValue(), 50, EnumContenidoCampo.ALFANUMERICO), // ok
    ES_POLCA(EnumCampoEntidad.ES_POLCA_PCC.getValue(), 2, EnumContenidoCampo.ALFANUMERICO), //
    EXISTE_FUGA_INFRACTOR(EnumCampoEntidad.EXISTE_FUGA_INFRACTOR_PCC.getValue(), 2, EnumContenidoCampo.ALFANUMERICO), //
    INFRACCION(EnumCampoEntidad.INFRACCION_PCC.getValue(), 35, EnumContenidoCampo.ALFANUMERICO), //
    ORGANISMO_TRANSITO(EnumCampoEntidad.ORGANISMO_TRANSITO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), // duda porq es integer
    NUMERO_COMPARENDO(EnumCampoEntidad.NUMERO_COMPARENDO_PCC.getValue(), 20, EnumContenidoCampo.ALFANUMERICO), // ok

    NUMERO_FACTURA(EnumCampoEntidad.NUMERO_FACTURA_PCC.getValue(), 20, EnumContenidoCampo.NUMERICO), //
    NUMERO_CITACION(EnumCampoEntidad.NUMERO_CITACION_PCC.getValue(), 20, EnumContenidoCampo.NUMERICO), //

    CODIGO_DE_ORIGEN_DEL_COMPARENDO(EnumCampoEntidad.CODIGO_ORIGEN_COMPARENDO_PCC.getValue(), -1,
            EnumContenidoCampo.NUMERICO), // duda porq es integer
    FECHA_INFRACCION(EnumCampoEntidad.FECHA_INFRACCION_PCC.getValue(), -1, EnumContenidoCampo.FECHA), //
    HORA_INFRACCION(EnumCampoEntidad.HORA_INFRACCION_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    MEDIO_IMPOSICION(EnumCampoEntidad.MEDIO_IMPOSICION_PCC.getValue(), 50, EnumContenidoCampo.ALFANUMERICO), //
    DIRECCION_COMPARENDO(EnumCampoEntidad.DIRECCION_COMPARENDO_PCC.getValue(), 150, EnumContenidoCampo.ALFANUMERICO), //
    // ENCABEZADO//
    // VEHICULO//
    PLACA_VEHICULO(EnumCampoEntidad.PLACA_VEHICULO_PCC.getValue(), 10, EnumContenidoCampo.ALFANUMERICO), //
    IDENTIFICACION_VEHICULO(EnumCampoEntidad.IDENTIFICACION_VEHICULO_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    ORGANISMO_MATRICULA_VEHICULO(EnumCampoEntidad.ORGANISMO_MATRICULA_VEHICULO_PCC.getValue(), 50,
            EnumContenidoCampo.ALFANUMERICO), //
    CLASE_VEHICULO(EnumCampoEntidad.CLASE_VEHICULO_PCC.getValue(), 50, EnumContenidoCampo.NUMERICO_COMO_ALFANUMERICO), //
    RADIO_ACCION(EnumCampoEntidad.RADIO_ACCION_PCC.getValue(), 50, EnumContenidoCampo.ALFANUMERICO), //
    MODALIDAD(EnumCampoEntidad.MODALIDAD_PCC.getValue(), 50, EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_TARJETA_OPERACION(EnumCampoEntidad.NUMERO_TARJETA_OPERACION_PCC.getValue(), 16,
            EnumContenidoCampo.NUMERICO_COMO_ALFANUMERICO), //
    TIPO_DOCUMENTO_EMPRESA(EnumCampoEntidad.TIPO_DOCUMENTO_EMPRESA_PCC.getValue(), 40, EnumContenidoCampo.ALFANUMERICO), //
    RAZON_SOCIAL_EMPRESA(EnumCampoEntidad.RAZON_SOCIAL_EMPRESA_PCC.getValue(), 200, EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_DOCUMENTO_EMPRESA(EnumCampoEntidad.NUMERO_DOCUMENTO_EMPRESA_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    TIPO_SERVICIO(EnumCampoEntidad.TIPO_SERVICIO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    NIVEL_SERVICIO(EnumCampoEntidad.NIVEL_SERVICIO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    MARCA_VEHICULO(EnumCampoEntidad.MARCA_VEHICULO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    LINEA_VEHICULO(EnumCampoEntidad.LINEA_VEHICULO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    COLOR(EnumCampoEntidad.COLOR_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    NUMERO_MOTOR(EnumCampoEntidad.NUMERO_MOTOR_PCC.getValue(), 25, EnumContenidoCampo.ALFANUMERICO), //
    MODELO(EnumCampoEntidad.MODELO_PCC.getValue(), 4, EnumContenidoCampo.ALFANUMERICO), //
    PESO_SECO(EnumCampoEntidad.PESO_SECO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    PESO_NETO(EnumCampoEntidad.PESO_NETO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    // VEHICULO//
    // INFRACTOR//
    TIPO_DOCUMENTO_DEL_INFRACTOR(EnumCampoEntidad.TIPO_DOCUMENTO_INFRACTOR_PCC.getValue(), 40,
            EnumContenidoCampo.ALFANUMERICO), //
    TIPO_INFRACTOR(EnumCampoEntidad.TIPO_INFRACTOR_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //

    OBSERVACIONES_INFRACTOR(EnumCampoEntidad.OBSERVACIONES_INFRACTOR_PCC.getValue(), 255,
            EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_DOCUMENTO_DEL_INFRACTOR(EnumCampoEntidad.NUMERO_DOCUMENTO_INFRACTOR_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_LICENCIA_DE_CONDUCCION_DEL_INFRACTOR(EnumCampoEntidad.NUMERO_LICENCIA_CONDUCCION_INFRACTOR_PCC.getValue(),
            50, EnumContenidoCampo.NUMERICO_COMO_ALFANUMERICO), //
    FECHA_EXPEDICION_DE_LA_LICENCIA_DE_CONDUCCION_DEL_INFRACTOR(
            EnumCampoEntidad.FECHA_EXPEDICION_LICENCIA_CONDUC_INFRAC_PCC.getValue(), -1, EnumContenidoCampo.FECHA), //
    FECHA_VENCIMIENTO_DE_LA_LICENCIA_DE_CONDUCCION_DEL_INFRACTOR(
            EnumCampoEntidad.FECHA_VENCIMIENTO_LICENCIA_CONDUC_INFRAC_PCC.getValue(), -1, EnumContenidoCampo.FECHA), //
    CATEGORIA_DE_LA_LICENCIA_DE_CONDUCCION_DEL_INFRACTOR(
            EnumCampoEntidad.CATEGORIA_LICENCIA_CONDUCCION_INFRACTOR_PCC.getValue(), 2,
            EnumContenidoCampo.ALFANUMERICO), //
    PRIMER_APELLIDO_INFRACTOR(EnumCampoEntidad.PRIMER_APELLIDO_INFRACTOR_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_APELLIDO_INFRACTOR(EnumCampoEntidad.SEGUNDO_APELLIDO_INFRACTOR_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    PRIMER_NOMBRE_INFRACTOR(EnumCampoEntidad.PRIMER_NOMBRE_INFRACTOR_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_NOMBRE_INFRACTOR(EnumCampoEntidad.SEGUNDO_NOMBRE_INFRACTOR_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    RAZON_SOCIAL_DEL_INFRACTOR(EnumCampoEntidad.RAZON_SOCIAL_INFRACTOR_PCC.getValue(), 200,
            EnumContenidoCampo.ALFANUMERICO), //
    EDAD_DEL_INFRACTOR(EnumCampoEntidad.EDAD_INFRACTOR_PCC.getValue(), 3,
            EnumContenidoCampo.NUMERICO_COMO_ALFANUMERICO), //
    DIRECCION_DEL_INFRACTOR(EnumCampoEntidad.DIRECCION_INFRACTOR_PCC.getValue(), 150, EnumContenidoCampo.ALFANUMERICO), //
    EMAIL_DEL_INFRACTOR(EnumCampoEntidad.EMAIL_INFRACTOR_PCC.getValue(), 100, EnumContenidoCampo.ALFANUMERICO), //
    TELEFONO_FIJO_INFRACTOR(EnumCampoEntidad.TELEFONO_FIJO_INFRACTOR_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    TELEFONO_MOVIL_INFRACTOR(EnumCampoEntidad.TELEFONO_MOVIL_INFRACTOR_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    ORGANISMO_LICENCIA_TRANSITO(EnumCampoEntidad.ORGANISMO_LICENCIA_TRANSITO_PCC.getValue(), -1,
            EnumContenidoCampo.NUMERICO), //
    // INFRACTOR//
    // PROPIETARIO//
    TIPO_DOCUMENTO_DEL_PROPIETARIO(EnumCampoEntidad.TIPO_DOCUMENTO_PROPIETARIO_PCC.getValue(), 40,
            EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_DE_DOCUMENTO_DEL_PROPIETARIO(EnumCampoEntidad.NUMERO_DOCUMENTO_PROPIETARIO_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    PRIMER_APELLIDO_PROPIETARIO(EnumCampoEntidad.PRIMER_APELLIDO_PROPIETARIO_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_APELLIDO_PROPIETARIO(EnumCampoEntidad.SEGUNDO_APELLIDO_PROPIETARIO_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    PRIMER_NOMBRE_PROPIETARIO(EnumCampoEntidad.PRIMER_NOMBRE_PROPIETARIO_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_NOMBRE_PROPIETARIO(EnumCampoEntidad.SEGUNDO_NOMBRE_PROPIETARIO_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    DIRECCION_DEL_PROPIETARIO(EnumCampoEntidad.DIRECCION_PROPIETARIO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    LICENCIA_DE_TRANSITO(EnumCampoEntidad.LICENCIA_TRANSITO_PCC.getValue(), 20,
            EnumContenidoCampo.NUMERICO_COMO_ALFANUMERICO), //
    // PROPIETARIO//
    // AGENTE TRANSITO//
    PLACA_DE_AGENTE(EnumCampoEntidad.PLACA_AGENTE_PCC.getValue(), 10, EnumContenidoCampo.NUMERICO_COMO_ALFANUMERICO), //
    PRIMER_APELLIDO_AGENTE(EnumCampoEntidad.PRIMER_APELLIDO_AGENTE_PCC.getValue(), 30, EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_APELLIDO_AGENTE(EnumCampoEntidad.SEGUNDO_APELLIDO_AGENTE_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    PRIMER_NOMBRE_AGENTE(EnumCampoEntidad.PRIMER_NOMBRE_AGENTE_PCC.getValue(), 30, EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_NOMBRE_AGENTE(EnumCampoEntidad.SEGUNDO_NOMBRE_AGENTE_PCC.getValue(), 30, EnumContenidoCampo.ALFANUMERICO), //
    AGENTE_TRANSITO(EnumCampoEntidad.AGENTE_TRANSITO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    OBSERVACIONES_AGENTE(EnumCampoEntidad.OBSERVACIONES_AGENTE_PCC.getValue(), 500, EnumContenidoCampo.ALFANUMERICO), //
    TIPO_IDENTIFICACION_AGENTE(EnumCampoEntidad.TIPO_IDENTIFICACION_AGENTE_PCC.getValue(), 40,
            EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_IDENTIFICACION_AGENTE(EnumCampoEntidad.NUMERO_IDENTIFICACION_AGENTE_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    NOMBRE_RESPONSABLE(EnumCampoEntidad.NOMBRE_RESPONSABLE_PCC.getValue(), 150, EnumContenidoCampo.ALFANUMERICO), //
    // AGENTE TRANSITO//
    // INMOVIVILIZACION//
    INMOVILIZADO(EnumCampoEntidad.INMOVILIZADO_PCC.getValue(), -1,
            EnumContenidoCampo.NUMERICO), PATIO(EnumCampoEntidad.PATIO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    NUMERO_GRUA(EnumCampoEntidad.NUMERO_GRUA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    PLACA_GRUA(EnumCampoEntidad.PLACA_GRUA_PCC.getValue(), 7, EnumContenidoCampo.ALFANUMERICO), //
    CONSECUTIVO_INMOVILIZACION(EnumCampoEntidad.CONSECUTIVO_INMOVILIZACION_PCC.getValue(), -1,
            EnumContenidoCampo.NUMERICO), //
    NUMERO_PATIO(EnumCampoEntidad.NUMERO_PATIO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    NOMBRE_PATIO(EnumCampoEntidad.NOMBRE_PATIO_PCC.getValue(), 150, EnumContenidoCampo.ALFANUMERICO), //
    DIRECCION_PATIO(EnumCampoEntidad.DIRECCION_PATIO_PCC.getValue(), 150, EnumContenidoCampo.ALFANUMERICO), //
    // INMOVILIZACION//
    // TESTIGO//
    TIPO_DE_DOCUMENTO_DEL_TESTIGO(EnumCampoEntidad.TIPO_DOCUMENTO_TESTIGO_PCC.getValue(), 40,
            EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_DE_DOCUMENTO_DEL_TESTIGO(EnumCampoEntidad.NUMERO_DOCUMENTO_TESTIGO_PCC.getValue(), 20,
            EnumContenidoCampo.ALFANUMERICO), //
    PRIMER_APELLIDO_TESTIGO(EnumCampoEntidad.PRIMER_APELLIDO_TESTIGO_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_APELLIDO_TESTIGO(EnumCampoEntidad.SEGUNDO_APELLIDO_TESTIGO_PCC.getValue(), 30,
            EnumContenidoCampo.ALFANUMERICO), //
    PRIMER_NOMBRE_TESTIGO(EnumCampoEntidad.PRIMER_NOMBRE_TESTIGO_PCC.getValue(), 30, EnumContenidoCampo.ALFANUMERICO), //
    SEGUNDO_NOMBRE_TESTIGO(EnumCampoEntidad.SEGUNDO_NOMBRE_TESTIGO_PCC.getValue(), 30, EnumContenidoCampo.ALFANUMERICO), //
    DIRECCION_DEL_TESTIGO(EnumCampoEntidad.DIRECCION_TESTIGO_PCC.getValue(), 150, EnumContenidoCampo.ALFANUMERICO), //
    TELEFONO_FIJO_TESTIGO(EnumCampoEntidad.TELEFONO_FIJO_TESTIGO_PCC.getValue(), 20, EnumContenidoCampo.ALFANUMERICO), //
    TELEFONO_MOVIL_TESTIGO(EnumCampoEntidad.TELEFONO_MOVIL_TESTIGO_PCC.getValue(), 20, EnumContenidoCampo.ALFANUMERICO), //
    // TESTIGO//
    // EMBRIAGUEZ//
    NIEGA_PRUEBA_ALCOHOLEMIA(EnumCampoEntidad.NIEGA_PRUEBA_ALCOHOLEMIA_PCC.getValue(), 2,
            EnumContenidoCampo.ALFANUMERICO), //
    NUMERO_PRUEBA_ALCOHOLEMIA(EnumCampoEntidad.NUMERO_PRUEBA_ALCOHOLEMIA_PCC.getValue(), 10,
            EnumContenidoCampo.NUMERICO_COMO_ALFANUMERICO), //
    GRADO_ALCOHOLEMIA(EnumCampoEntidad.GRADO_ALCOHOLEMIA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    VALOR_GRADO_ALCOHOLEMIA(EnumCampoEntidad.VALOR_GRADO_ALCOHOLEMIA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    FECHA_PRUEBA_ALCOHOLEMIA(EnumCampoEntidad.FECHA_PRUEBA_ALCOHOLEMIA_PCC.getValue(), -1, EnumContenidoCampo.FECHA), //
    // EMBRIAGUEZ//
    // EVIDENCIAS FISICAS//
    EVIDENCIA(EnumCampoEntidad.EVIDENCIA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    // EVIDENCIAS FISICAS//
    // OTROS CAMPOS//
    USUARIO(EnumCampoEntidad.USUARIO_PCC.getValue(), 16, EnumContenidoCampo.ALFANUMERICO), //
    // OTROS CAMPOS//
    FECHA_OPERACION(EnumCampoEntidad.FECHA_OPERACION_PCC.getValue(), -1, EnumContenidoCampo.FECHA), // ?
    TIPO_NOTIFICACION(EnumCampoEntidad.TIPO_NOTIFICACION_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), // ?
    RETIENE_LICENCIA(EnumCampoEntidad.RETIENE_LICENCIA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), // ?
    NUMERO_REINCIDENCIA(EnumCampoEntidad.NUMERO_REINCIDENCIA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), // ? embriaguez
    RUTA(EnumCampoEntidad.RUTA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), // ?
    UNIFICACION_RESPONSABLE(EnumCampoEntidad.UNIFICACION_RESPONSABLE_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    FECHA_NOTIFICACION(EnumCampoEntidad.FECHA_NOTIFICACION_PCC.getValue(), -1, EnumContenidoCampo.FECHA), //
    NUMERO_INFORME(EnumCampoEntidad.NUMERO_INFORME_PCC.getValue(), 20, EnumContenidoCampo.ALFANUMERICO), //
    FECHA_RECEPCION(EnumCampoEntidad.FECHA_RECEPCION_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    FECHA_CORRECCION(EnumCampoEntidad.FECHA_CORRECCION_PCC.getValue(), -1, EnumContenidoCampo.FECHA), //
    ACTIVO(EnumCampoEntidad.ACTIVO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    DIGITO_DE_VERIFICACION_DEL_NIT(EnumCampoEntidad.DIGITO_VERIFICACION_NIT_PCC.getValue(), -1,
            EnumContenidoCampo.NUMERICO), //
    DIRECCION_EMPRESA(EnumCampoEntidad.DIRECCION_EMPRESA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    TELEFONO_FIJO_EMPRESA(EnumCampoEntidad.TELEFONO_FIJO_EMPRESA_PCC.getValue(), 20, EnumContenidoCampo.ALFANUMERICO), //
    TELEFONO_MOVIL_EMPRESA(EnumCampoEntidad.TELEFONO_MOVIL_EMPRESA_PCC.getValue(), 20, EnumContenidoCampo.ALFANUMERICO), //
    ESTADO_COMPARENDO(EnumCampoEntidad.ESTADO_COMPARENDO_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //

    PAIS_DIRECCION(EnumCampoEntidad.PAIS_DIRECCION_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    TIPO_TRANSPORTE_PASAJERO(EnumCampoEntidad.TIPO_TRANSPORTE_PASAJEROS_PCC.getValue(), -1,
            EnumContenidoCampo.NUMERICO), //
    DEPARTAMENTO_DIRECCION(EnumCampoEntidad.DEPARTAMENTO_DIRECCION_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    MUNICIPIO_DIRECCION(EnumCampoEntidad.MUNICIPIO_DIRECCION_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    LOCALIDAD_DIRECCION(EnumCampoEntidad.LOCALIDAD_DIRECCION_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    TIPO_EVIDENCIA(EnumCampoEntidad.TIPO_EVIDENCIA_PCC.getValue(), -1, EnumContenidoCampo.NUMERICO), //
    NO_ES_CAMPO_PCC(EnumCampoEntidad.NO_ES_CAMPO_PCC.getValue(), -1, EnumContenidoCampo.ALFANUMERICO), //

    // NUMERO_LICENCIA_TRANSITO(41, 16), //
    // CONSECUTIVO_ASIGNADO_GRUA(64, 16), //

    TIPO_AGENTE_IMPOSITOR(EnumCampoEntidad.TIPO_AGENTE_IMPOSITOR.getValue(), -1, EnumContenidoCampo.NUMERICO), //

    ;//
    /**
     * Contiene el código del campo
     */
    private Integer codigoCampo;

    /**
     * Longitud maxima q puede tener el campo, -1 si no aplica
     */
    private int longitud;

    /**
     * 
     */
    private EnumContenidoCampo tipo;

    /**
     * Constructor con id del código del campo
     * 
     * @param codigoCampo
     *            código del campo del comparendo
     */
    EnumCamposDetalleComparendo(int codigoCampo, int longitud, EnumContenidoCampo tipo) {
        this.codigoCampo = codigoCampo;
        this.longitud = longitud;
        this.tipo = tipo;
    }

    /**
     * Retorna el código del campo de la enumeración
     * 
     * @return codigoCampo
     */
    public int getCodigoCampo() {
        return this.codigoCampo;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public EnumContenidoCampo getTipo() {
        return tipo;
    }

    @Override
    public Integer getValue() {
        return codigoCampo;
    }
}
