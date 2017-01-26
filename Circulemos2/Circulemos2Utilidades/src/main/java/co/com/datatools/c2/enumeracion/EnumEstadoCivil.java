package co.com.datatools.c2.enumeracion;

/**
 * @author robert.bautista
 * @since 2014-04-11
 */
public enum EnumEstadoCivil {

    SOLTERO(1), //
    CASADO(2), //
    UNION_LIBRE(3), //
    DIVORCIADO(4); //

    /**
     * Contiene el id del estado civil
     */
    private final int id;

    /**
     * Constructor con id
     * 
     * @param id
     *            el id del estado civil
     */
    private EnumEstadoCivil(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
