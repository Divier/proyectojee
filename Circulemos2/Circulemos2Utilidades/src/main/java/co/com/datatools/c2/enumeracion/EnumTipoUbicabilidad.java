package co.com.datatools.c2.enumeracion;

/**
 * Contiene los tipos de ubicabilidad, contiene la informacion de la tabla tipo_ubicabilidad.
 * 
 * @author robert.bautista - 5/06/2014
 */
public enum EnumTipoUbicabilidad {

    RESIDENCIA(1), //
    TRABAJO(2), //
    EMPRESA(3);

    /**
     * Contiene el pk del tipo de ubicabilidad
     */
    final private int pk;

    /**
     * @param name
     * @param ordinal
     */
    private EnumTipoUbicabilidad(int pk) {
        this.pk = pk;
    }

    /**
     * @return the pk
     */
    public int getPk() {
        return this.pk;
    }

}
