package co.com.datatools.ingreso.excepcion;

import javax.security.auth.login.LoginException;

public class AutenticacionExcepcion extends LoginException {

    private static final long serialVersionUID = 1L;
    private ErrorAutenticacion errorAutenticacion;

    public AutenticacionExcepcion(ErrorAutenticacion errorAutenticacion) {
        super(errorAutenticacion.getMensaje());
        this.errorAutenticacion = errorAutenticacion;
    }

    public ErrorAutenticacion getErrorAutenticacion() {
        return errorAutenticacion;
    }

    public void setErrorAutenticacion(ErrorAutenticacion errorAutenticacion) {
        this.errorAutenticacion = errorAutenticacion;
    }

}
