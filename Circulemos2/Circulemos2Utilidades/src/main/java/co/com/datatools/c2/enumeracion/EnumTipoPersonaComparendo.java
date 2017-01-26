package co.com.datatools.c2.enumeracion;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoPersonaComparendo implements SearchableEnumeration<Integer> {

    INFRACTOR(2), //
    PROPIETARIO(8), //
    EMPRESA_TRANSPORTE(1), //
    TESTIGO(14);//

    private static final Map<Integer, EnumTipoPersonaComparendo> lookup = new HashMap<Integer, EnumTipoPersonaComparendo>();

    static {
        for (EnumTipoPersonaComparendo enumTipPerComp : EnumSet.allOf(EnumTipoPersonaComparendo.class))
            lookup.put(enumTipPerComp.getCodigo(), enumTipPerComp);
    }

    private int codigoTipoPersonaComparendo;

    public int getCodigo() {
        return codigoTipoPersonaComparendo;
    }

    public static EnumTipoPersonaComparendo getEnumTipoPersonaComparendo(int codigoTipoPersonaComparendo) {
        return lookup.get(codigoTipoPersonaComparendo);
    }

    private EnumTipoPersonaComparendo(int codigoTipoPersonaComparendo) {
        this.codigoTipoPersonaComparendo = codigoTipoPersonaComparendo;
    }

    @Override
    public Integer getValue() {
        return codigoTipoPersonaComparendo;
    }
}
