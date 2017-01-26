package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoRechazo implements SearchableEnumeration<Integer> {
    INCREMENTAR_SUSTENTACION_JURIDICA(1), //
    CAMBIAR_DECISION_JURIDICA(2), //
    ;

    /**
     * Contiene el código del tipo de rechazo.
     */
    private int codigo;

    /**
     * Constructor con el código del tipo de rechazo.
     * 
     * @param codigo
     *            código del tipo de rechazo
     */
    private EnumTipoRechazo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    @Override
    public Integer getValue() {
        return this.codigo;
    }

}
