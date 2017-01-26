package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enum con la informacion de los roles para los procesos de impuegnacion
 * 
 * @author giovanni.velandia
 * 
 */
public enum EnumRolImpugnacion implements SearchableEnumeration<Integer> {

    AUXILIAR(-13), //
    ABOGADO_PRIMERA_INSTANCIA(-14), //
    AUTORIZADOR_EXONERACION(-15), //
    ABOGADO_SEGUNDA_INSTANCIA(-16), //
    ABOGADO_ESPECIALISTA(-17), //
    ;

    private Integer id;

    private EnumRolImpugnacion(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }
}