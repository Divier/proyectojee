package co.com.datatools.c2.numeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Contiene los tipos de validacion que seran utilizados para las diferentes validaciones que se realizaran a una dirección
 * 
 * @author Dixon.Alvarez
 *
 */
public enum EnumTipoValidacionDireccion implements SearchableEnumeration<Integer> {
    INFRACTOR(1), //
    INFRACCION(2), //
    ;
    private int pk;

    private EnumTipoValidacionDireccion(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }
}
