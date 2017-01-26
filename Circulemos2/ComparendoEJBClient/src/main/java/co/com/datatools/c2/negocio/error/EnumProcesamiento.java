package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enum que se encarga de los procesamientos para un ingreso de comparendo
 * 
 * @author giovanni.velandia 2015-10-07<br>
 *         luis.forero (mod 2015-11-12)
 * 
 */
public enum EnumProcesamiento implements SearchableEnumeration<Integer> {

    RECIBIR_COMPARENDO(1), //
    CORREGIR_INCONSISTENCIA(2), //
    RECTIFICAR_COMPARENDO(3), //

    ;
    private Integer codigo;

    private EnumProcesamiento(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
