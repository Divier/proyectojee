package co.com.datatools.c2.enumeracion;

/**
 * @author robert.bautista
 * @since 2014-04-11
 */
public enum EnumTipoVivienda {

    PROPIA(1), //
    ARRIENDO(2), //
    FAMILIAR(3); //

    /**
     * Contiene el id del tipo de vivienda
     */
    private final int id;

    /**
     * Constructor con el id del tipo de vivienda
     * 
     * @param id
     *            Id del tipo de vivienda
     */
    private EnumTipoVivienda(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
