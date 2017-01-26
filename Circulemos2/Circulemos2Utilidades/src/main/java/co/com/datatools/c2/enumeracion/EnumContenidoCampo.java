/**
 * 
 */
package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que representa el contenido de los tipo de campos.  
 * 
 * @author Javier.Fajardo 06/abr/2016
 *   
 */
public enum EnumContenidoCampo implements SearchableEnumeration<Integer> {
	
	NUMERICO(1), 
	ALFANUMERICO(2),
	NUMERICO_COMO_ALFANUMERICO(3),
	FECHA(4);
	
	private Integer codigo;

	private EnumContenidoCampo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public Integer getValue() {
		return codigo;
	}	
}
