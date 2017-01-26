package co.com.datatools.ingreso.utilidades;

public enum EnumAlgoritmo {

    SHA512("SHA-512"), //
    MD5("MD5"), //
    SHA256("SHA-256"), //
    ;

    private String codigo;

    private EnumAlgoritmo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
