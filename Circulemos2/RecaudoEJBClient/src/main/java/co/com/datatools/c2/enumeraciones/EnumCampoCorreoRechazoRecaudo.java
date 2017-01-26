package co.com.datatools.c2.enumeraciones;

public enum EnumCampoCorreoRechazoRecaudo {
    NUMERO_RECAUDO("N�MERO DEL RECAUDO"), //
    OBLIGACION_PAGADA("OBLIGACI�N PAGADA"), //
    FECHA_RECEPCION("FECHA Y HORA DE RECEPCI�N"), //
    NOMBRE_USUARIO("NOMBRE DE USUARIO"), //
    ERROR("ERROR"), //
    ;

    private String titulo;

    private EnumCampoCorreoRechazoRecaudo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

}
