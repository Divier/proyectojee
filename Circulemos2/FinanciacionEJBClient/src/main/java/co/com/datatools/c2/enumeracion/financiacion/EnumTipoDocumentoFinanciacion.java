package co.com.datatools.c2.enumeracion.financiacion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los tipos de documento para financiacion
 * 
 * @author dixon.alvarez
 * 
 */
public enum EnumTipoDocumentoFinanciacion implements SearchableEnumeration<Integer> {
    ACUERDO_PAGO(1, "1"), //
    ;
    private Integer id;
    private String codigo;

    private EnumTipoDocumentoFinanciacion(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return id;
    }
}
