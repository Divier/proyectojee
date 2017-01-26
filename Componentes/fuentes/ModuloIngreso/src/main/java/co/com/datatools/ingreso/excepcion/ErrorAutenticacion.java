package co.com.datatools.ingreso.excepcion;

public enum ErrorAutenticacion {
    AUTENTICACION_001(-1, "Error general al intentar realizar la autenticaci�n", "Error al realizar cifrado"), //
    AUTENTICACION_002(-2, "Error datasource no encontrado", "Error datasource no encontrado"), //
    AUTENTICACION_003(-3, "Error al obtener el archivo de configuraci�n",
            "Error al obtener el archivo de configuraci�n"), //
    AUTENTICACION_004(-4, "Error de acceso a la base de datos", "Error de acceso a la base de datos"), //

    AUTENTICACION_101(1,
            "Autenticaci�n fallida para los datos ingresados. Verifique el usuario y la contrase�a ingresados",
            "El usuario no existe en el sistema o el password es err�neo"), //
    AUTENTICACION_102(2,
            "Autenticaci�n fallida para los datos ingresados. Verifique el usuario y la contrase�a ingresados",
            "El usuario no se encuentra activo en el sistema"), //
    AUTENTICACION_103(3, "Error al consultar par�metro en el sistema", "El par�metro no existe en el sistema"), //
    AUTENTICACION_104(4, "El usuario alcanz� la cantidad m�xima de intentos de ingreso fallidos",
            "El usuario alcanz� la cantidad m�xima de intentos de ingreso fallidos"), //
    AUTENTICACION_105(5,
            "Autenticaci�n fallida para los datos ingresados. Verifique el usuario y la contrase�a ingresados",
            "El password esta bloqueado");
    ;

    private String mensaje;
    private int codigo;
    private String descripcion;

    private ErrorAutenticacion(int codigo, String mensaje, String descripcion) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
