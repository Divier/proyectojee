package co.com.datatools.c2.enumeracion;

/**
 * Esta enumeracion no debe existir, solo hay una deficion para formato de fechas y esta en un bundle web
 * 
 */
@Deprecated
public enum EnumFormatValues {

    /**
     * Formato de la fecha que se maneja en la aplicacion.
     */
    FORMATO_FECHA("dd/MM/yyyy"), //
    ;

    private String valor;

    private EnumFormatValues(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
