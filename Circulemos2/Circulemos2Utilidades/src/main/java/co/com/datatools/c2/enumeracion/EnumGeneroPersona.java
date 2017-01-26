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
     * Contiene el id del g�nero
     */
    private int id;

    /**
     * Contiene el diminutivo del g�nero
     */
    private String diminutivo;

    /**
     * Contiene el nombre del g�nero
     */
    private String nombre;

    /**
     * Constructor de la Enumeraci�n con id, diminutivo y nombre.
     * 
     * @param id
     *            Id del g�nero
     * 
     * @param diminutivo
     *            diminutivo del g�nero
     * 
     * @param nombre
     *            Nombre del g�nero
     */
    private EnumGeneroPersona(int id, String diminutivo, String nombre) {
        this.id = id;
        this.diminutivo = diminutivo;
        this.nombre = nombre;
    }

    /**
     * Retorna el id de la enumeraci�n
     * 
     * @return campo id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna el diminutivo de la enumeraci�n
     * 
     * @return campo diminutivo
     */
    public String getDiminutivo() {
        return this.diminutivo;
    }

    /**
     * Retorna el nombre de la enumeraci�n
     * 
     * @return campo enumeraci�n
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna la lista de enumeraciones ordenadas de manera espec�fica.
     * 
     * @return List<EnumGeneroPersona> ordenadas de manera espec�fica
     */
    public static List<EnumGeneroPersona> getGenerosOrdenados() {
        List<EnumGeneroPersona> list = new ArrayList<EnumGeneroPersona>();

        list.add(MASCULINO);
        list.add(FEMENINO);
        return list;
    }

}
