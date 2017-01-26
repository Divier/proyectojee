package co.com.datatools.c2.enumeraciones;

public enum EnumCampoCorreoInconsistenciaRecaudo {
    ORGANISMO_TRANSITO("ORGANISMO DE TRANSITO"), //
    FECHA_TRANSACCION("FECHA DE LA TRANSACCI�N"), //
    HORA_TRANSACCION("HORA DE LA TRANSACCI�N"), //
    NUMERO_RECAUDO("N�MERO DEL RECAUDO"), //
    CUENTA("CUENTA"), //
    TIPO_CUENTA("TIPO DE CUENTA"), //
    BANCO("BANCO"), //
    TOTAL_EFECTIVO("TOTAL EFECTIVO"), //
    TOTAL_CHEQUE("TOTAL CHEQUE"), //
    TOTAL_RECAUDO("TOTAL DEL RECAUDO"), //
    TIPO_DOCUMENTO_DEUDOR("TIPO DE DOCUMENTO DEL DEUDOR"), //
    NUMERO_IDENTIFICACION_DEUDOR("N�MERO DE IDENTIFICACI�N DEL DEUDOR"), //
    OBLIGACION_PAGADA("OBLIGACI�N PAGADA"), //
    VALOR_OBLIGACION("VALOR DE LA OBLIGACI�N"), //
    TIPO_RECUADO("TIPO DE RECAUDO"), //
    NRO_CUOTAS("NRO. DE CUOTAS"), //
    FECHA_GENERACION("FECHA DE GENERACI�N"), //
    ;

    private String titulo;

    private EnumCampoCorreoInconsistenciaRecaudo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

}
