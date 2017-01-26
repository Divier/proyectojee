package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoInconsistenciaAgente implements SearchableEnumeration<Integer> {

    MULTA_NO_TIENE_AGENTE(1), //
    AGENTE_NO_REGISTRADO(2), //
    AGENTE_NO_VIGENTE(3), //
    ;

    private Integer codigo;

    EnumTipoInconsistenciaAgente(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static EnumTipoInconsistenciaAgente obtenerPorCodigo(Integer codigo) {
        for (EnumTipoInconsistenciaAgente inconsistencia : EnumTipoInconsistenciaAgente.values()) {
            if (inconsistencia.getCodigo().equals(codigo)) {
                return inconsistencia;
            }
        }
        return null;
    }
}