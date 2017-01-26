package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Identifica los web service existentes en el sistema para una conexion
 * 
 * @author luis.forero(2016-03-31)
 * 
 */
public enum EnumWebService implements SearchableEnumeration<Integer> {
    RECIBIR_COMPARENDO(1), //
    SOLICITAR_OCN(2), //
    NOTIFICAR_COMPARENDO_SIMIT(3), //
    CONSULTAR_REINCIDENCIAS(4), //
    REPLICAR_PAGO(5), //
    GESTION_COBRO_SAC(6), //
    REGISTRAR_FINANCIAMIENTO(7), //
    ANULAR_FINANCIAMIENTO(8), //
    IMPUGNAR_COMPARENDO_AXIS(9), //
    COACTIVO_COMPARENDO_AXIS(10), //
    NOTIFICAR_E_NOTIFICA(11), //
    FIRMAR_E_NOTIFICA(12), //
    ;

    public Integer idWebService;

    private EnumWebService(Integer idWebService) {
        this.idWebService = idWebService;
    }

    @Override
    public Integer getValue() {
        return idWebService;
    }
}