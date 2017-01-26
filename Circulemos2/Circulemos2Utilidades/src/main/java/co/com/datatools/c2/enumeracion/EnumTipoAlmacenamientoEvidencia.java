package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Lista los tipos de almacenamiento q pueden tener las evidencias de un comparendo, los valores son campos parametrizados en las tablas parametro ó
 * parametro_organismo
 * 
 * @author felipe.martinez
 */
public enum EnumTipoAlmacenamientoEvidencia implements SearchableEnumeration<Integer> {
    BASE_DATOS(1), ARCHIVO(2);

    private int codigo;

    private EnumTipoAlmacenamientoEvidencia(int codigo) {
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
