package co.com.datatools.c2.mail.util;

/**
 * Catalogo de errores parametros de cuenta de correo
 * 
 * @author luis.martinez
 * 
 */
public enum EnumErrorParametrosCorreo {

    CUENTA_CORREO_VACIO(1, "La cuenta de correo es requerida."), //
    PASSWORD_CORREO_VACIO(2, "El password la cuenta de correo es requerida."), //
    SMTP_HOST_NAME_VACIO(3, "El SMTP Host Name es requerido."), //
    SMTP_PORT_VACIO(4, "El SMTP puerto es requerido."), //
    CUENTA_CORREO_NO_VALIDA(5, "La cuenta de correo electronico no es valida."), //
    SMTP_PORT_NO_VALIDO(6, "El puerto SMTP debe ser númerico."), //
    ;

    private int codigo;
    private String descripcion;

    private EnumErrorParametrosCorreo(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
