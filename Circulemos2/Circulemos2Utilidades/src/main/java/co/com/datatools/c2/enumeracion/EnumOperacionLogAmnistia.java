package co.com.datatools.c2.enumeracion;

/**
 * Enumeracion que identifica los posibles estados que puede tomar un log de amnistia al momento de una determinada operacion efectuada sobre una
 * Amnistia
 * 
 * @author luis.forero
 * 
 */
public enum EnumOperacionLogAmnistia {

    CREACION("C"), //
    MODIFICACION("U"), //
    ELIMINACION("D"), //
    ;
    private String valor;

    private EnumOperacionLogAmnistia(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
