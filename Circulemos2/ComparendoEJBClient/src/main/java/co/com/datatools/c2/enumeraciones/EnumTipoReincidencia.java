package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Contiene los valores de los tipos de reincidencia a consultar
 * 
 * @author rodrigo.cruz
 * 
 */
public enum EnumTipoReincidencia implements SearchableEnumeration<Integer> {

    GENERAL(1), EMBRIAGUEZ(2);

    private int id;

    EnumTipoReincidencia(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
