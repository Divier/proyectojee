package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion de periodos de intereses
 * 
 * @author divier.casas
 */
public enum EnumPeriodoInteres implements SearchableEnumeration<Integer> {

    DIARIO(1), //
    MENSUAL(2), //
    TRIMESTRAL(3), //
    ANUAL(4), //
    ;

    /**
     * columna id_periodo_interes de la tabla periodo_interes
     */
    int codigo;

    private EnumPeriodoInteres(int codigo) {
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