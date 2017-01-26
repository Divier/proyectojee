package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoRespuestaWebServices implements SearchableEnumeration<Integer> {
    RESPUESTA_EXITOSA(1), //
    BLOQUEANTES_DE_ESTRUCTURA(3), //
    ALERTAS(4);

    private int codigo;

    EnumTipoRespuestaWebServices(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return getCodigo();
    }

};
