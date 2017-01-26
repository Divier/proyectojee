/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author Javier.Fajardo
 *
 */
public enum EnumOrigenUsabilidadSac implements SearchableEnumeration<Integer> {
	ATM(1),
	ANT(1),
	SOGIT(1),
	TERCEROS(1),
    ;
	
	private Integer id;

	private EnumOrigenUsabilidadSac(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getValue() {
		return id;
	}
}
