/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author javier.fajardo
 *
 */
public enum EnumTipoEvidenciaSac implements SearchableEnumeration<Integer> {
	FOTOGRAFIA(1), //
	MULTIMEDIA(2), //
	;

	private Integer id;

	private EnumTipoEvidenciaSac(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getValue() {
		return id;
	}
}
