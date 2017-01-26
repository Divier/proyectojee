package co.com.datatools.c2.numeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Contiene los tipos de consecutivos manejados en el sistema.
 * 
 * @author julio.pinzon
 * 
 */
public enum EnumConsecutivo implements SearchableEnumeration<Integer> {
    GENERAL(1), //
    NOTIF_COMPA_CORREO(2), //
    RESOL_SANCION(3), //
    RESOL_RECTIFICA(4), //
    NOTIF_COMPA_AVISO(5), //
    NUMERO_IMPUGNACION_ECUADOR(6), //
    NUMERO_FINANCIACION_ECUADOR(7), //
    NUMERO_VOLANTE_PAGO_ECUADOR(8), //
    NUMERO_ANULACION_ECUADOR(9), //
    NUMERO_COACTIVO_ECUADOR(10), //
    NOTIFICACION_COURIER_COACTIVO_ECUADOR(11), //
    NUMERO_ACCIDENTALIDAD(12), //
    NUMERO_CARGUE_ARCHIVO_COACTIVOS(13), //
    NUMERO_CARGUE_ARCHIVO_ANALISIS_UBIC(14), //
    NUMERO_CARGUE_MASIVO_UBIC(15), //
    NUMERO_OFICIO_SOLIC_PRUEBAS(16), //
    NUMERO_MEMORANDO_SOLIC_PRUEBAS(17), //
    NUMERO_SOLICITUD_OFICIO_BIEN(18),//
    ;
    private int pk;

    private EnumConsecutivo(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }
}
