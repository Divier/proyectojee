package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoEvidenciaENotifica implements SearchableEnumeration<Integer> {

    GENERICO(1), //
    EMAIL(2), //
    LINK(3), //
    ;

    private int codigo;

    private EnumTipoEvidenciaENotifica(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    /**
     * Busca la enumeracion por nombre
     * 
     * @param nombre
     * @return EnumTipoEvidenciaENotifica
     * @author divier.casas 2016-10-25
     */
    public static EnumTipoEvidenciaENotifica encontrarPorNombre(String nombre) {
        EnumTipoEvidenciaENotifica eTipoEvENotifica = null;
        for (EnumTipoEvidenciaENotifica respuesta : EnumTipoEvidenciaENotifica.values()) {
            if (respuesta.name().equalsIgnoreCase(nombre)) {
                eTipoEvENotifica = respuesta;
                break;
            }
        }
        return eTipoEvENotifica;
    }
}