package co.com.datatools.c2.enumeracion;

public enum EnumTipoOperacionENotifica {

    FIRMA_DOCUMENTO("SIG"), //
    DESCARGA_DOCUMENTO("DWN"), //
    DESCARGA_DOCUMENTO_OTP("OTP"), //
    APORTAR_DOCUMENTACION("SDO"), //
    REVISION_DOCUMENTACION("RVD"), //
    NOTIFICACION_ELECTRONICA("NOTE"), //
    VALIDACION("VAL"), //
    REVISION_OCUMENTACION_OTP("RVDOTP"),//
    ;

    private String codigo;

    private EnumTipoOperacionENotifica(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}