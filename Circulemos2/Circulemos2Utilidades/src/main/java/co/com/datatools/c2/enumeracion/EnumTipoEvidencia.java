package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion de los registros de la tabla tipo_evidencia
 * 
 * @author felipe.martinez
 */
public enum EnumTipoEvidencia implements SearchableEnumeration<Integer> {
    IMAGEN_COMPARENDO(1), PRUEBA_ALCOHOLEMIA(2), FOTO_COMPARENDO(3);

    private int codigo;

    EnumTipoEvidencia(int codigo) {
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
