package co.com.datatools.fachadainetegracionweb.excepciones;

import javax.ejb.ApplicationException;

/**
 * Excepcion para manejar errores de parametros ingresados a un metodo de negocio
 * 
 * @author felipe.martinez
 * 
 */
@ApplicationException(rollback = true)
public class CirculemosIllegalArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public CirculemosIllegalArgumentException(String message) {
        super(message);
    }
}
