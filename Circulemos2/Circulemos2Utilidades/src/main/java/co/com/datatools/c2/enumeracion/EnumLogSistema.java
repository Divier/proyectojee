package co.com.datatools.c2.enumeracion;

/**
 * Contiene el listado de logs del sistema.
 * 
 * @author julio.pinzon
 * 
 */
public enum EnumLogSistema {

    PROCESAR_COMPARENDO("co.com.datatools.c2.enumeracion.PROCESAR_COMPARENDO"), // COM_002 | Validacion y recepcion de comparendos
    RECIBIR_COMPARENDO_WS("co.com.datatools.c2.enumeracion.RECIBIR_COMPARENDO_WS"), // COM_001 | Recepcion de comparendos por WS
    SERVICIOS_SOGIT_WS("co.com.datatools.c2.enumeracion.SERVICIOS_SOGIT_WS"), // Servicios sogit
    NOTIFICACION_SIMIT("co.com.datatools.c2.enumeracion.NOTIFICACION_SIMIT"), // SIM_008 | Creacion de archivo por demanda para envio a SIMIT
    NOTIFI_AUTO_COMPA_SIMIT("co.com.datatools.c2.enumeracion.NOTIFI_AUTO_COMPA_SIMIT"), // SIM_007 | Creacion de archivo automatica para envio a SIMIT
    NOTIF_COMPA_CORREO("co.com.datatools.c2.enumeration.NOTIF_COMPA_CORREO"), // COM_052 | Validacion de conexion repositorio
    RESOL_SANCION_AUTO("co.com.datatools.c2.enumeracion.RESOL_SANCION_AUTO"), // COM_022 | Generar Resoluciones de Sanción.
    GENERAR_EDICTO("co.com.datatools.c2.enumeracion.GENERAR_EDICTO"), // COM_055 | Generacion de Notificaciones por aviso.
    NOTIF_COMPA_AVISO("co.com.datatools.c2.enumeracion.NOTIF_COMPA_AVISO"), // COM_057 | Actualizar los comparendos con la notificación por aviso
    SOLICITUD_OCN("co.com.datatools.c2.enumeracion.SOLICITUD_OCN"), // COM_050 |
    NOTIF_COMPA_SIMIT_WS("co.com.datatools.c2.enumeracion.NOTIF_COMPA_SIMIT_WS"), // SIM_001 | Notificar comparendo a SIMIT por WebService
    REPLICAR_PAGO_WS("co.com.datatools.c2.enumeracion.REPLICAR_PAGO_WS"), // REC_005 | Replicar pagos por WebService
    GESTION_COBRO_SAC("co.com.datatools.c2.enumeracion.GESTION_COBRO_SAC"), // Gestionar cobro SAC.
    NOTIFI_INCONSISTENCIAS_COMPARENDOS("co.com.datatools.c2.enumeracion.NOTIFI_INCONSISTENCIAS_COMPARENDOS"), // HU-COM_077
    GENERACION_REPORTE_INCONSISTENCIAS_RECAUDO(
            "co.com.datatools.c2.enumeracion.GENERACION_REPORTE_INCONSISTENCIAS_RECAUDO"), // HU_CIR20_DAT_REC_009
    SOLICITUD_COMPARENDOS_TERCEROS("co.com.datatools.c2.enumeracion.SOLICITUD_COMPARENDOS_TERCEROS"), // HU_CIR20_DAT_COM_081
    RECEPCION_DATOS_RECAUDO_TERCEROS("co.com.datatools.c2.enumeracion.RECEPCION_DATOS_RECAUDO_TERCEROS"), // HU_CIR20_DAT_REC_014
    GENERACION_REPORTE_RECEPCION_DATOS_RECAUDO(
            "co.com.datatools.c2.enumeracion.GENERACION_REPORTE_RECEPCION_DATOS_RECAUDO"), // HU_CIR20_DAT_REC_015
    REPLICAR_PAGO_TERCEROS("co.com.datatools.c2.enumeracion.REPLICAR_PAGO_TERCEROS"), // REC_012 | Replicar pagos TERCEROS
    REPLICAR_NOVEDADES_SAC("co.com.datatools.c2.enumeracion.REPLICAR_NOVEDADES_SAC"), // SAC_002 | Replicar novedades SAC
    REPLICAR_UBICABILIDAD_SAC("co.com.datatools.c2.enumeracion.REPLICAR_UBICABILIDAD_SAC"), //
    SOLICITUD_UBICABILIDAD_TERCEROS("co.com.datatools.c2.enumeracion.SOLICITUD_UBICABILIDAD_TERCEROS"), //
    GENERACION_REPORTE_INCONSISTENCIAS_CONCILIACION_RECAUDO(
            "co.com.datatools.c2.enumeracion.GENERACION_REPORTE_INCONSISTENCIAS_CONCILIACION_RECAUDO"), //
    REPLICAR_FINANCIACIONES("co.com.datatools.c2.enumeracion.REPLICAR_FINANCIACIONES"), // )
    VALIDAR_CONCILIACION_RECAUDO("co.com.datatools.c2.enumeracion.VALIDAR_CONCILIACION_RECAUDO"), // REC_017
    REPLICAR_FINANCIACIONES_DETALLE("co.com.datatools.c2.enumeracion.REPLICAR_FINANCIACIONES_DETALLE"), // FIN_014
    ACTUALIZAR_OBLIGACION_FINANCIADA_SAC("co.com.datatools.c2.enumeracion.ACTUALIZAR_OBLIGACION_FINANCIADA_SAC"), // FIN_014
    VALIDAR_FINANCIACION_CUOTA_PENDIENTE("co.com.datatools.c2.enumeracion.VALIDAR_FINANCIACION_CUOTA_PENDIENTE"), // FIN_022
    ABRIR_PROCESO_COACTIVO_OBLIGACIONES("co.com.datatools.c2.enumeracion.ABRIR_PROCESO_COACTIVO_OBLIGACIONES"), // COAC_004
    RECIBIR_GESTION_COBRO_SAC("co.com.datatools.c2.enumeracion.RECIBIR_GESTION_COBRO_SAC"), // HU_CIR20_DAT_COB_017 | Recibir gestion de cobranza SAC
    VALIDAR_AGENTE("co.com.datatools.c2.enumeracion.VALIDAR_AGENTE"), // COM_089 | Validar agente en comparendo
    NOTIFICACION_E_NOTIFICA("co.com.datatools.c2.enumeracion.NOTIFICACION_E_NOTIFICA"), // Notificación e-Notifica
    CONSULTA_CIRCUITO_E_NOTIFICA("co.com.datatools.c2.enumeracion.CONSULTA_CIRCUITO_E_NOTIFICA"),// Notificación e-Notifica
    ;

    private String nombreLog;

    public String getNombreLog() {
        return nombreLog;
    }

    private EnumLogSistema(String nombreLog) {
        this.nombreLog = nombreLog;
    }
}