/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author sergio.torres
 * 
 */
public enum EnumTipoServicio implements SearchableEnumeration<Integer> {

    PUBLICO(3), //
    PARTICULAR(4), //
    ;

    private Integer idTipoServicio;

    private EnumTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    @Override
    public Integer getValue() {
        return idTipoServicio;
    }
}
