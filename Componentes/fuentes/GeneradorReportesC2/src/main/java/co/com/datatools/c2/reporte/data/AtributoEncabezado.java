package co.com.datatools.c2.reporte.data;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;

/**
 * DTO que contiene información de encabezados y cabeceras de un reporte
 * 
 * @author dixon.alvarez
 * 
 */
public class AtributoEncabezado implements Serializable {

    private static final long serialVersionUID = -5204242116701795481L;

    /**
     * Informacion que sera procesada en el reporte
     */
    private String contenido;
    private String formato;

    /**
     * Contenido de encabezados especiales
     */
    private List multilinea;

    /**
     * Tipo de contenido de encabezado especial
     */
    private EnumEncabezadoEspecial variable;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public List getMultilinea() {
        return multilinea;
    }

    public void setMultilinea(List multilinea) {
        this.multilinea = multilinea;
    }

    public EnumEncabezadoEspecial getVariable() {
        return variable;
    }

    public void setVariable(EnumEncabezadoEspecial variable) {
        this.variable = variable;
    }

}
