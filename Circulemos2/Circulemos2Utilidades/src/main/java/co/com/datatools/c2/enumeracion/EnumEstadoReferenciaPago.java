package co.com.datatools.c2.enumeracion;

public enum EnumEstadoReferenciaPago {

    VIGENTE(1);

    private Integer pk;

    private EnumEstadoReferenciaPago(Integer pk) {
        this.pk = pk;
    }

    public Integer getPk() {
        return pk;
    }

}
