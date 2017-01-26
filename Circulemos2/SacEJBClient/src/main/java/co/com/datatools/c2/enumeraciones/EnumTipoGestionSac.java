/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author Javier.Fajardo
 * 
 */
public enum EnumTipoGestionSac implements SearchableEnumeration<Integer> {
    NOTIFICAR_COBRANZA(1, "Notificación y cobranza"), //
    COBRANZA(2, "Cobranza"), //
    ;

    private Integer id;
    private String nombre;

    private EnumTipoGestionSac(Integer id) {
        this.id = id;
    }

    private EnumTipoGestionSac(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
