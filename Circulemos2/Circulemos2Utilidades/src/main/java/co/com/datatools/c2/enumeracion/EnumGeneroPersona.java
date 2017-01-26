package co.com.datatools.c2.enumeracion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author robert.bautista
 * @since 2013-10-03
 */
public enum EnumGeneroPersona {

    MASCULINO(1, "M", "Masculino"), //
    FEMENINO(2, "F", "Femenino");

    /**
     * Contiene el id del género
     */
    private int id;

    /**
     * Contiene el diminutivo del género
     */
    private String diminutivo;

    /**
     * Contiene el nombre del género
     */
    private String nombre;

    /**
     * Constructor de la Enumeración con id, diminutivo y nombre.
     * 
     * @param id
     *            Id del género
     * 
     * @param diminutivo
     *            diminutivo del género
     * 
     * @param nombre
     *            Nombre del género
     */
    private EnumGeneroPersona(int id, String diminutivo, String nombre) {
        this.id = id;
        this.diminutivo = diminutivo;
        this.nombre = nombre;
    }

    /**
     * Retorna el id de la enumeración
     * 
     * @return campo id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna el diminutivo de la enumeración
     * 
     * @return campo diminutivo
     */
    public String getDiminutivo() {
        return this.diminutivo;
    }

    /**
     * Retorna el nombre de la enumeración
     * 
     * @return campo enumeración
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna la lista de enumeraciones ordenadas de manera específica.
     * 
     * @return List<EnumGeneroPersona> ordenadas de manera específica
     */
    public static List<EnumGeneroPersona> getGenerosOrdenados() {
        List<EnumGeneroPersona> list = new ArrayList<EnumGeneroPersona>();

        list.add(MASCULINO);
        list.add(FEMENINO);
        return list;
    }

}
