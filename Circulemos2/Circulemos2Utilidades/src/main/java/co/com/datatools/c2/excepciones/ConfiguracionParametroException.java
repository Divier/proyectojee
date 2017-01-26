package co.com.datatools.c2.excepciones;

import co.com.datatools.c2.enumeracion.EnumParametro;

public class ConfiguracionParametroException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public enum ErrorParametro {
        PARAMETRO_NO_ENCONTRADO("No se encuentra parametro solicitado"), //
        PARAMETRO_NULL("No se encuentra valor valido para el parametro, se requiere valor diferente a null"), //
        PARAMETRO_NO_BOOLEAN("El valor del parametro no es S o N"), //
        PARAMETRO_NO_ENTERO("El valor del parametro no es numerico");

        String descripcion;

        ErrorParametro(String descripcion) {
            this.descripcion = descripcion;
        }
    }

    private int codigoParametro;

    public ConfiguracionParametroException(ErrorParametro error, EnumParametro parametro) {
        super(error.descripcion + " [CodigoParametro: " + parametro.getCodigo() + "]");
        codigoParametro = parametro.getCodigo();
    }

    public int getCodigoParametro() {
        return codigoParametro;
    }

}
