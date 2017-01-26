package co.com.datatools.c2.reporte.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contenido de las cabeceras del reporte
 * 
 * @author dixon.alvarez
 * 
 */
public class CabeceraReporte implements Serializable {

    private static final long serialVersionUID = 1456438057432847963L;

    /**
     * Codigo del reporte
     */
    private String codigo;

    /**
     * Nombre del reporte
     */
    private String nombre;

    /**
     * Codigo del encabezado con el cual se genera el reporte
     */
    private String codigo_encabezado;

    /**
     * Cabeceras del contenido del reporte
     */
    private List<AtributoEncabezado> cabeceras;

    public CabeceraReporte() {
        cabeceras = new ArrayList<AtributoEncabezado>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AtributoEncabezado> getCabeceras() {
        return cabeceras;
    }

    public void add(List<AtributoEncabezado> cabeceras) {
        this.cabeceras.addAll(cabeceras);
    }

    public String getCodigo_encabezado() {
        return codigo_encabezado;
    }

    public void setCodigo_encabezado(String codigo_encabezado) {
        this.codigo_encabezado = codigo_encabezado;
    }

}
