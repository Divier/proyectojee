package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeración encargada de contener los valores fijos que requiere cartera. Por ejemplo, los tipos de obligacion, estados, etc.
 * 
 * Esta Enumeración debe formar parte de la interface ICarteraComparendo para que sea utilizada por la interface y los clientes de la misma.
 * 
 * @author giovanni.velandia
 * 
 */
public enum EnumCarteraComparendo implements SearchableEnumeration<Integer> {

    OBLIGACION_ACTIVO(1), //
    OBLIGACION_PENDIENTE_ACTIVAR(2), //
    CONC_CART_NC_NOTA_RECT(3), //
    ;

    private int codigo;

    EnumCarteraComparendo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
