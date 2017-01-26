package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.Utilidades;

/**
 * @deprecated usar {@link Utilidades#toBooleanParametro(String)}
 */
@Deprecated
public enum EnumValorBoolean {

    SI("1"), //
    NO("0"), //
    ;

    private String codigo;

    private EnumValorBoolean(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    /**
     * Indica si el valor indicado corresponde a alguno de los utilizado como verdadero
     * 
     * @param valor
     *            el valor a evaluar
     * 
     * @return true si el valor contiene algún valor de los tratados como 'verdadero'
     * @deprecated usar {@link Utilidades#toBooleanParametro(String)}
     */
    @Deprecated
    public static boolean esValorVerdadero(String valor) {
        boolean resultado = false;

        if (valor != null) {
            resultado = ((valor.equalsIgnoreCase("SI")) || (valor.equals("1")) || (valor.equalsIgnoreCase("S"))
                    || (valor.equalsIgnoreCase("T")) || (valor.equalsIgnoreCase("VERDADERO")));

        }

        return resultado;
    }

    /**
     * Indica si el valor indicado corresponde a alguno de los utilizado como falso
     * 
     * @param valor
     *            el valor a evaluar
     * 
     * @return true si el valor contiene algún valor de los tratados como 'falso'
     * @deprecated usar {@link Utilidades#toBooleanParametro(String)}
     */
    @Deprecated
    public static boolean esValorFalso(String valor) {
        boolean resultado = false;

        if (valor != null) {
            resultado = ((valor.equalsIgnoreCase("NO")) || (valor.equals("0")) || (valor.equalsIgnoreCase("N"))
                    || (valor.equalsIgnoreCase("F")) || (valor.equalsIgnoreCase("FALSO")));

        }

        return resultado;
    }

    /**
     * Indica si el valor indicado corresponde a alguno de los utilizado como valor booleano
     * 
     * @param valor
     *            el valor a evaluar
     * 
     * @return true si el valor contiene algún valor de los tratados como boolean en la enumeración
     * @deprecated usar {@link Utilidades#toBooleanParametro(String)}
     */
    @Deprecated
    public static boolean esValorBooleano(String valor) {
        boolean resultado = false;

        if (valor != null) {
            resultado = ((EnumValorBoolean.esValorVerdadero(valor)) || (EnumValorBoolean.esValorFalso(valor)));

        }

        return resultado;
    }
}
