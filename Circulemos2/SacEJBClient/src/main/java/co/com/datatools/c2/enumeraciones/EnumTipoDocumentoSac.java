/**
 * 
 */
package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author Javier.Fajardo
 *
 */
public enum EnumTipoDocumentoSac implements SearchableEnumeration<Integer> {
	CEDULA_DE_CIUDADANIA(1),
    NUMERO_TRIBUTARIO(2),
    CEDULA_EXTRANJERIA(3),
    TARJETA_IDENTIDAD(4),
    PASAPORTE(5),
    REGISTRO_CIVIL(6),
    NUIP(7),
    CARNET_DIPLOMATICO(8),
    TARJETA_EXTRANJERIA(9)
    ;

    private Integer id;

    private EnumTipoDocumentoSac(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }
}
