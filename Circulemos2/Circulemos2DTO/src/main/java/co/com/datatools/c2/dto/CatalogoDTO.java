package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * Objeto que representa los valores posibles que puede tomar un determinado catalogo
 * 
 * @author luis.forero
 * 
 */
// TODO LF Cambiar el nombre de esta clase
public class CatalogoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String valor;

    public CatalogoDTO() {

    }

    public CatalogoDTO(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.valor = codigo.toString();
    }

    public CatalogoDTO(String nombre, Long codigo) {
        this.nombre = nombre;
        this.valor = codigo.toString();
    }

    public CatalogoDTO(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
