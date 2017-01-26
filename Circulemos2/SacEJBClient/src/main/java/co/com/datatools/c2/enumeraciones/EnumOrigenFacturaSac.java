/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author javier.fajardo
 *
 */
public enum EnumOrigenFacturaSac implements SearchableEnumeration<Integer> {
	PEDESTRE(1),
	ELECTRONICA(2),
	ADMINISTRATIVA(3),
	FINANCIACION(4),
    ;

	private Integer id;

	private EnumOrigenFacturaSac(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getValue() {
		return id;
	}
}
