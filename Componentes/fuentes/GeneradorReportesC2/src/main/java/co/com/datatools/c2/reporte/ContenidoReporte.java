package co.com.datatools.c2.reporte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;

/**
 * DTO para almacenar la informacion de generacion de un reporte
 * 
 * @author felipe.martinez
 */
@SuppressWarnings("rawtypes")
public class ContenidoReporte implements Serializable {

    private static final long serialVersionUID = -4119119875531175858L;

    /**
     * Codigo que identifica el reporte a generar
     */
    private String codigo;

    /**
     * Valores para las variables especiales dentro del encabezado del reporte
     */
    private Map<EnumEncabezadoEspecial, Object> variablesEncabezado;

    /**
     * Mapa para configurar los parametros de cada posicion del encabezado:<br>
     * key:numero de linea del encabezado, value:parametros para esa linea
     */
    private Map<Integer, Object> parametrosEncabezado;

    /**
     * Contenido del reporte
     */
    private List contenido;

    public ContenidoReporte() {
        contenido = new ArrayList<>();
        parametrosEncabezado = new HashMap<Integer, Object>();
        variablesEncabezado = new HashMap<EnumEncabezadoEspecial, Object>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List getContenido() {
        return contenido;
    }

    public void setContenido(List contenido) {
        if (contenido == null) {
            contenido = new ArrayList<>(0);
        }
        this.contenido = contenido;
    }

    public Map<EnumEncabezadoEspecial, Object> getVariablesEncabezado() {
        return variablesEncabezado;
    }

    public void setVariablesEncabezado(Map<EnumEncabezadoEspecial, Object> variablesEncabezado) {
        this.variablesEncabezado = variablesEncabezado;
    }

    public Map<Integer, Object> getParametrosEncabezado() {
        return parametrosEncabezado;
    }

    public void setParametrosEncabezado(Map<Integer, Object> parametrosEncabezado) {
        this.parametrosEncabezado = parametrosEncabezado;
    }

}
