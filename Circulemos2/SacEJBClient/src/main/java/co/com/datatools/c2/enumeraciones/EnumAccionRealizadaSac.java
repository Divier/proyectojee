/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author javier.fajardo
 *
 */
public enum EnumAccionRealizadaSac implements SearchableEnumeration<Integer> {
	CORREO_ELECTRONICO(1),
	CORREO_CERTIFICADO(2),
	PUBLICACION_PRENSA(3),
	LLAMADA(4),
	SMS(5),
    ;

	private Integer id;

	private EnumAccionRealizadaSac(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getValue() {
		return id;
	}
}
