package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.util.EntidadDtoC2;

public class GeneracionNumeroFormularioDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Base que actua como un prefijo que no varia para generar numeros
     */
    private String base;
    /**
     * Posicion del numero que va variando desde un valor minimo hasta un valor maximo
     */
    private int posicion;
    /**
     * Valor de caracter minimo desde el cual varia la posicion
     */
    private Character min;
    /**
     * Valor de caracter maximo hasta el cual varia la posicion
     */
    private Character max;
    /**
     * Valor del numero final hasta el cual se deben generar los numeros
     */
    private String numeroFinal;

    /**
     * Cantidad de formularios que se desean generar
     */
    private Integer catidadFormularios;

    public GeneracionNumeroFormularioDTO(String base, int posicion, Character min, Character max, String numeroFinal,
            Integer cantidadFormularios) {
        this.base = base;
        this.posicion = posicion;
        this.min = min;
        this.max = max;
        this.numeroFinal = numeroFinal;
        this.catidadFormularios = cantidadFormularios;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Character getMin() {
        return min;
    }

    public void setMin(Character min) {
        this.min = min;
    }

    public Character getMax() {
        return max;
    }

    public void setMax(Character max) {
        this.max = max;
    }

    public String getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public Integer getCatidadFormularios() {
        return catidadFormularios;
    }

    public void setCatidadFormularios(Integer catidadFormularios) {
        this.catidadFormularios = catidadFormularios;
    }

}
