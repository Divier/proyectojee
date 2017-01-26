package co.com.datatools.c2.enumeracion;

public enum EnumTipoDireccion {

    SIMPLE(1, "Simple"), VALIDADO(2, "Validado");

    private Integer id;
    private String label;

    EnumTipoDireccion(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
