package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Listado de los campos asociados a la entidad direccion subconjunto de los registros q se encuentran en campo_entidad
 * 
 * @author felipe.martinez
 */
public enum EnumCampoDireccion implements SearchableEnumeration<Integer> {

    BARRIO(163), //
    BIS_VIA_PRINCIPAL(164), //
    BIS_VIA_SECUNDARIA(165), //
    COMPLEMENTO(166), //
    LATITUD(167), //
    LETRA_BIS_VIA_PRINCIPAL(168), //
    LETRA_BIS_VIA_SECUNDARIA(169), //
    LETRA_VIA_PRINCIPAL(170), //
    LETRA_VIA_SECUNDARIA(171), //
    LONGITUD(172), //
    NUMERO_PLACA_DOMICILIARIA(173), //
    NUMERO_VIA_PRINCIPAL(174), //
    NUMERO_VIA_SECUNDARIA(175), //
    LOCALIDAD(176), //
    MUNICIPIO(177), //
    NOMBRE_VIA_PRINCIPAL(178), //
    NOMBRE_VIA_SECUNDARIA(179), //
    PAIS(0), //
    DEPARTAMENTO(0), //
    TIPO_COORDENADA(180), //
    TIPO_UBICABILIDAD(181), //
    TIPO_VIA_SECUNDARIA(182), //
    TIPO_VIA_PRINCIPAL(183), //
    CARDINALIDAD_VIA_PRINCIPAL(184), //
    CARDINALIDAD_VIA_SECUNDARIA(185), //
    ;
    /**
     * columna codigo_campo de la tabla campo_entidad
     */
    int codigo;

    private EnumCampoDireccion(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }
}
