package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Actividades por ID
 * 
 * @author giovanni.velandia
 */
public enum EnumActividad implements SearchableEnumeration<Integer> {

    IMPOSICION_DEL_COMPARENDO(1), //
    COMPARENDOS_NOTIFICACION(2), //
    NOTIFICACION_PERSONAL_COMPARENDO(3), //
    NOTIFICACION_POR_CONDUCTA_CONCLUYENTE_COMPARENDO(4), //
    RECTIFICACION_COMPARENDO(5), //
    NOTIFICACION_CORREO_CERTIFICACO(6), //
    NOTIFICACION_CORREO_CERTIFICADO(7), //
    GENERACION_RESOLUCION_SANCION(8), //
    NOTIFICACION_POR_AVISO_COMPARENDO(9), //
    RADICACION_PROCESO_IMPUGNACION(10), //
    COMPARENDO_ENVIADO_SIMIT(11), //
    RECTIFICACION_COMPARENDO_ENVIADA_SIMIT(12), //
    NOTIFICACION_COMPARENDO_ENVIADA_SIMIT(13), //
    IMPOSICION_DEL_COMPARENDO_RECTIFICADO(14), //
    REGISTRO_DEL_COMPARENDO(15), //
    PAGO_COMPARENDO(16), //
    SUSTITUCION(17), //
    IMPOSICION_SUSTITUCION(18), //
    FINANCIACION_COMPARENDO(19), //
    FALLAR_PROCESO_IMPUGNACION(20), //
    APROBAR_PROCESO_IMPUGNACION(21), //
    CITACION_ANULADA(22), //
    INICIAR_COACTIVO(23), //
    ;

    private int codigo;

    EnumActividad(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
