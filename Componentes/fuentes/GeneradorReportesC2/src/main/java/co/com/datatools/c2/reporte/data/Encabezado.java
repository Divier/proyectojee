package co.com.datatools.c2.reporte.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene la estructura del encabezado de un reporte
 * 
 * @author dixon.alvarez
 * 
 */
public class Encabezado implements Serializable {

    private static final long serialVersionUID = -3965049356641039635L;

    /**
     * Codigo asignado a un encabezado
     */
    private String codigo;

    /**
     * Contenido del encabezado
     */
    private List<AtributoEncabezado> atributos;

    public Encabezado() {
        atributos = new ArrayList<AtributoEncabezado>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<AtributoEncabezado> getAtributos() {
        return atributos;
    }

    public void add(List<AtributoEncabezado> atributos) {
        this.atributos = atributos;
    }
}
