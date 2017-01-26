package co.com.datatools.seguridad.excepciones;

import java.io.Serializable;

/**
 * Objeto para almacenar el catalogo extensible de errores de las funcionalidades de Seguridad
 * 
 * @author felipe.martinez
 * 
 */
public class ErrorWrapper implements Serializable {

    private static final long serialVersionUID = 5033731497698294669L;

    /**
     * codigo alfanumerico del error
     */
    private final String codigoError;
    /**
     * descripcion catalogada del error
     */
    private final String descError;

    /**
     * Constructor con codigo del error y descripcion
     * 
     * @param codigoError
     * @param descError
     */
    public ErrorWrapper(String codigoError, String descError) {
        super();
        this.codigoError = codigoError == null ? "" : codigoError;
        this.descError = descError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public String getDescError() {
        return descError;
    }

    @Override
    public String toString() {
        return codigoError + " - " + descError;
    }
}
