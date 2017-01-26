package co.com.datatools.c2.enumeracion;

public enum EnumTercero {

    SIMIT(1), //
    CIRCULEMOS_1(2);

    private int id;

    private EnumTercero(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
