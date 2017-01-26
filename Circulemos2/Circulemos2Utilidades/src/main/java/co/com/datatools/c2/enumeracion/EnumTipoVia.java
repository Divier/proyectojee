package co.com.datatools.c2.enumeracion;

import java.util.ArrayList;
import java.util.List;

/**
 * Catalogo de tipos de via para Colombia. Si se requiere otro pais se puede agregar a la enumeracion el atributo {@code idPais}.
 * 
 * @author rodrigo.cruz
 * 
 */
public enum EnumTipoVia {

    SIN_IDENTIFICAR(0, true, null), //
    CARRERA(1, false, EnumTipoTipoVia.NUMERICO), //
    CALLE(2, false, EnumTipoTipoVia.NUMERICO), //
    AVENIDA(3, true, EnumTipoTipoVia.CATALOGO), //
    AUTOPISTA(4, true, EnumTipoTipoVia.CATALOGO), //
    KILOMETRO(5, true, null), //
    TRANSVERSAL(6, false, EnumTipoTipoVia.NUMERICO), //
    DIAGONAL(7, false, EnumTipoTipoVia.NUMERICO), //
    TRAMO(8, true, null), //
    PASO(9, true, null), //
    RAMAL(10, true, null), //
    SUBRAMAL(11, true, null), //
    CIRCULAR(12, true, null), //
    AVENIDA_CARRERA(13, false, EnumTipoTipoVia.CATALOGO), //
    AVENIDA_CALLE(14, false, EnumTipoTipoVia.CATALOGO), //
    ;

    /**
     * El tipo de tipo de via clasifica entre numerico o en catalogo
     * 
     */
    private enum EnumTipoTipoVia {
        NUMERICO, CATALOGO;
    }

    private int codigo;
    private boolean igualTipoVia; // TVP y TVS pueden ser iguales en direccion
    private EnumTipoTipoVia tipoViaNumerico;

    EnumTipoVia(int codigo, boolean igualTipoVia, EnumTipoTipoVia tipoViaNumerico) {
        this.codigo = codigo;
        this.igualTipoVia = igualTipoVia;
        this.tipoViaNumerico = tipoViaNumerico;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean isIgualTipoVia() {
        return igualTipoVia;
    }

    public EnumTipoTipoVia getTipoViaNumerico() {
        return tipoViaNumerico;
    }

    /**
     * Tipos de via numericos: aquellos que requieren numero de via, diferente a nombre de via. Ej: Calle <b>84</b> es numerico, Avenida <b>El
     * Dorado</b> esta en catalogo.
     * 
     * @return Lista de {@link EnumTipoVia#codigo}
     */
    public static List<Integer> obtenerTiposViaNumericos() {
        List<Integer> idTipoViaNumericoL = new ArrayList<>();
        for (EnumTipoVia tipoVia : EnumTipoVia.values())
            if (tipoVia.getTipoViaNumerico() != null
                    && tipoVia.getTipoViaNumerico().equals(EnumTipoVia.EnumTipoTipoVia.NUMERICO))
                idTipoViaNumericoL.add(tipoVia.getCodigo());
        return idTipoViaNumericoL;
    }

    /**
     * Tipos de via catalogo: aquellos que requieren nombre de via, diferente a numero de via. Ej: Avenida <b>El Dorado</b> esta en catalogo, Calle
     * <b>84</b> es numerico.
     * 
     * @return Lista de {@link EnumTipoVia#codigo}
     */
    public static List<Integer> obtenerTiposViaCatalogo() {
        List<Integer> idTipoViaNumericoL = new ArrayList<>();
        for (EnumTipoVia tipoVia : EnumTipoVia.values())
            if (tipoVia.getTipoViaNumerico() != null
                    && tipoVia.getTipoViaNumerico().equals(EnumTipoVia.EnumTipoTipoVia.CATALOGO))
                idTipoViaNumericoL.add(tipoVia.getCodigo());
        return idTipoViaNumericoL;
    }

    /**
     * Tipos de via iguales: aquellos que pueden ser iguales en una direccion valida. Ej: <b>Avenida</b> Suba con <b>Avenida</b> Ciudad de Cali.
     * 
     * @return Lista de {@link EnumTipoVia#codigo}
     */
    public static List<Integer> obtenerTiposViaIguales() {
        List<Integer> idTipoViaIgualL = new ArrayList<>();
        for (EnumTipoVia tipoVia : EnumTipoVia.values())
            if (tipoVia.isIgualTipoVia())
                idTipoViaIgualL.add(tipoVia.getCodigo());
        return idTipoViaIgualL;
    }

}
