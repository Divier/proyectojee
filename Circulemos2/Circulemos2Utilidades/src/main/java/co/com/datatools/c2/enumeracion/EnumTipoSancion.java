package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion de tipos de sanciones
 * 
 * @author rodrigo.cruzh
 */
public enum EnumTipoSancion implements SearchableEnumeration<Integer> {

    AMONESTACION(1), //
    MULTA(2), //
    RETENCIONA_PREVENTIVA_LICENCIA_CONDUCCION(3), //
    SUSPENSION_LICENCIA_CONDUCCION(4), //
    SUSPENSION_CANCELACION_PERMISO_REGISTRO(5), //
    INMOVILIZACION_VEHICULO(6), //
    RETENCION_PREVENTIVA_VEHICULO(7), //
    CANCELACION_DEFINITIVA_LICENCIA_CONDUCCION(8), //
    ;

    private int codigo;

    EnumTipoSancion(int codigo) {
        this.codigo = codigo;
    }

    public int getPk() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
