package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author rodrigo.cruz
 * 
 */
public enum EnumUnidadMonetaria implements SearchableEnumeration<Integer> {

    SMLV(1, "Salario mínimo legal vigente"), //
    SMLDV(2, "Salario mínimo legal diario vigente"), //
    UVT(3, "Unidad de valor tributario"), //
    COP(4, "Pesos colombianos"), //
    ;

    private int codigo;
    private String nombre;

    private EnumUnidadMonetaria(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
