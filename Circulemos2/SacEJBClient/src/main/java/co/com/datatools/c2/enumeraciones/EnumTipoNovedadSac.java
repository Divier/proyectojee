/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author Javier.Fajardo
 *
 */
public enum EnumTipoNovedadSac implements SearchableEnumeration<Integer> {
	PAGO(1),
	EXONERACION(2),
	FINANCIACION(3),
	IMPUGNACION(4),
	ANULACION(5),
	FALLECIDO(6);
	
	private Integer id;

	private EnumTipoNovedadSac(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getValue() {
		return id;
	}
}
