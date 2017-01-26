package co.com.datatools.c2.enumeracion;

public enum EnumVariablesCorreo {

    // Cartera
    numero_solicitud(EnumTipoCorreo.NOTIFICACION_SOLICITUD_DEVOLUCION), //
    nombre_infractor(EnumTipoCorreo.NOTIFICACION_SOLICITUD_DEVOLUCION), //
    nombre_apoderado(EnumTipoCorreo.NOTIFICACION_SOLICITUD_DEVOLUCION), //
    fecha_solicitud(EnumTipoCorreo.NOTIFICACION_SOLICITUD_DEVOLUCION), //
    estado_solicitud(EnumTipoCorreo.NOTIFICACION_SOLICITUD_DEVOLUCION), //
    // Formularios
    fecha_asignacion(EnumTipoCorreo.NOTIFICACION_ASIGNACION_FORMULARIOS), //
    fecha_devolucion(EnumTipoCorreo.NOTIFICACION_DEVOLUCION_FORMULARIOS), //
    fecha_anulacion(EnumTipoCorreo.NOTIFICACION_ANULACION_FORMULARIOS), //
    tipo_formulario(EnumTipoCorreo.NOTIFICACION_ASIGNACION_FORMULARIOS,
            EnumTipoCorreo.NOTIFICACION_ANULACION_FORMULARIOS, EnumTipoCorreo.NOTIFICACION_DEVOLUCION_FORMULARIOS), //
    intervalos(EnumTipoCorreo.NOTIFICACION_ASIGNACION_FORMULARIOS, EnumTipoCorreo.NOTIFICACION_ANULACION_FORMULARIOS,
            EnumTipoCorreo.NOTIFICACION_DEVOLUCION_FORMULARIOS), //
    organismo(EnumTipoCorreo.NOTIFICACION_ASIGNACION_FORMULARIOS, EnumTipoCorreo.NOTIFICACION_ANULACION_FORMULARIOS,
            EnumTipoCorreo.NOTIFICACION_DEVOLUCION_FORMULARIOS), ;

    EnumTipoCorreo[] tipoCorreo;

    private EnumVariablesCorreo(EnumTipoCorreo... tipoCorreo) {
        this.tipoCorreo = tipoCorreo;
    }

}
