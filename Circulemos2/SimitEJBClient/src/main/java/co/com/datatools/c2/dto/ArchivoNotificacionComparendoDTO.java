package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * Contiene el archivo generado a SIMIT con los comparendos que deben notificarse. Adicionalmente contiene el nombre y cantidad de registros del
 * mismo.
 * 
 * @author julio.pinzon
 */
public class ArchivoNotificacionComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    /**
     * Cantidad de registros contenidos en el archivo.
     */
    private int cantidadRegistros;
    /**
     * Nombre del archivo.
     */
    private String nombre;
    /**
     * Contenido del archivo.
     */
    private byte[] contenido;
    /**
     * Número de oficio asociado al archivo generado.
     */
    private String numeroOficio;

    // --- Constructor
    public ArchivoNotificacionComparendoDTO() {
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

}
