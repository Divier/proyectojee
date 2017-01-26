package co.com.datatools.c2.managed_bean.comparendo.administracion.colombia;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;

/**
 * Objeto de flujo que permite manejar las resoluciones de un comparendo
 * 
 * @author luis.forero(2016-03-16)
 * 
 */
public class ResolucionesComparendoFL implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_FL = "resolucionesComparendoFL";

    private List<ComparendoResolucionDTO> lstComparendoResolucion;

    private ComparendoResolucionDTO resolucionSeleccionada;

    private StreamedContent streamedContent;

    private boolean mostrarDocResolucion;

    public ResolucionesComparendoFL() {
    }

    public List<ComparendoResolucionDTO> getLstComparendoResolucion() {
        return lstComparendoResolucion;
    }

    public void setLstComparendoResolucion(List<ComparendoResolucionDTO> lstComparendoResolucion) {
        this.lstComparendoResolucion = lstComparendoResolucion;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public ComparendoResolucionDTO getResolucionSeleccionada() {
        return resolucionSeleccionada;
    }

    public void setResolucionSeleccionada(ComparendoResolucionDTO resolucionSeleccionada) {
        this.resolucionSeleccionada = resolucionSeleccionada;
    }

    public boolean isMostrarDocResolucion() {
        return mostrarDocResolucion;
    }

    public void setMostrarDocResolucion(boolean mostrarDocResolucion) {
        this.mostrarDocResolucion = mostrarDocResolucion;
    }

}
