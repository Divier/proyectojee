/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author javier.fajardo
 *
 */
public enum EnumTipoNotificacionSac implements SearchableEnumeration<Integer> {
	CORREO_ELETRONICO(1),
	CORREO_CERTIFICADO(2),
	PUBLICACION_PRENSA(3),
	CONDUCTA_CONCLUYENTE(4),
	PROCESO_CORREO_CERTIFICADO(5),
	PROCESO_NOTIFICACION_AVISO(6)
    ;

	private Integer id;

	private EnumTipoNotificacionSac(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getValue() {
		return id;
	}
}
