package co.com.datatools.c2.enumeracion;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumeracion que permite identificar los codigos de determinados parametros definidos para los tipos de responsables solidarios (Tabla:
 * tipo_responsable_solidario)
 * 
 * @author luis.forero
 * 
 */
public enum EnumTipoResponsableSolidario {

    PROPIETARIO(1), //
    EMPRESA(2), //
    CONDUCTOR(3), //
    ;

    private static final Map<Integer, EnumTipoResponsableSolidario> lookup = new HashMap<Integer, EnumTipoResponsableSolidario>();

    static {
        for (EnumTipoResponsableSolidario e : EnumSet.allOf(EnumTipoResponsableSolidario.class))
            lookup.put(e.getCodigoResponsable(), e);
    }

    private Integer codigoResponsable;

    private EnumTipoResponsableSolidario(Integer codigoResponsable) {
        this.codigoResponsable = codigoResponsable;
    }

    public static EnumTipoResponsableSolidario getEnumTipoResponsableSolidario(Integer codigoResponsable) {
        return lookup.get(codigoResponsable);
    }

    public Integer getCodigoResponsable() {
        return codigoResponsable;
    }

}
