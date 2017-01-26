package co.com.datatools.c2.enumeraciones;

public enum EnumCampoCorreoRechazoRecaudo {
    NUMERO_RECAUDO("NÚMERO DEL RECAUDO"), //
    OBLIGACION_PAGADA("OBLIGACIÓN PAGADA"), //
    FECHA_RECEPCION("FECHA Y HORA DE RECEPCIÓN"), //
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
