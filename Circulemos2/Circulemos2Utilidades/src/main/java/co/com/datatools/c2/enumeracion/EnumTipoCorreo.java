package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Tipos de correo permitidos dentro de la aplicacion
 * 
 * @author felipe.martinez
 */
public enum EnumTipoCorreo implements SearchableEnumeration<Integer> {

    COMUNICACIONES_INFRACTOR(1), //
    RECHAZOS_WS_CURSOS_PEDAGOGICOS(2), //
    REINCIDENCIA_COMPARENDO(3), //
    ARCHIVO_SIMIT_EXITOSO(4), //
    ARCHIVO_SIMIT_FALLIDO(5), //
    ARCHIVO_ASOBANCARIA_FALLIDO(6), //
    NOTIFICACION_SOLICITUD_DEVOLUCION(10), //
    NOTIFICACION_ASIGNACION_FORMULARIOS(7), //
    NOTIFICACION_ANULACION_FORMULARIOS(8), //
    NOTIFICACION_DEVOLUCION_FORMULARIOS(9), //
    ENVIO_RTA_RESOLUCIONES_SANCION(11), //
    NOTIFICACION_INCONSISTENCIAS_COMPARENDOS(12), //
    ENVIO_INCONSISTENCIAS_RECAUDO(13), //
    ENVIO_RECHAZOS_RECAUDO(14), //
    ENVIO_CONCILIACION_RECAUDO(15), //
    NOTIFICACION_FALLO_IMPUGNACION(16), //
    ENVIO_ACTUALIZACION_UBICABILIDAD(17), //
    ENVIO_ANULACION_MULTA(18), //
    PONER_FIRME_FINANCIACION(19), //
    NOTIFICACION_EVALUACION_IMPUGNACION(20), //
    ENVIO_INCONSISTENCIA_AGENTE(21), //
    NOTIFICACION_E_NOTIFICA(22), //
    NOTIFICACION_CIERRE_PRUEBAS(23), //
    NOTIFICACION_EVALUACION_IMPUGNACION_ENOTIFICA(24), //
    NOTIFICACION_FALLO_IMPUGNACION_ENOTIFICA(25), //
    NOTIFICACION_CIERRE_PRUEBAS_ENOTIFICA(26), //
    ENVIO_ACTUALIZACION_UBICABILIDAD_ENOTIFICA(27), //
    ;

    private int codigo;

    private EnumTipoCorreo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
