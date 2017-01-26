package co.com.datatools.c2.managed_bean.comparendo.simit.archivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.StreamedContent;

/**
 * 
 */
public class ArchivoSimitHolderFL implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArchivoSimitFL eventoArchivoSimitSel;
    private List<ArchivoSimitFL> resultados;
    private StreamedContent streamedContent;

    public ArchivoSimitFL getEventoArchivoSimitSel() {
        return eventoArchivoSimitSel;
    }

    public void setEventoArchivoSimitSel(ArchivoSimitFL eventoArchivoSimitSel) {
        this.eventoArchivoSimitSel = eventoArchivoSimitSel;
    }

    public List<ArchivoSimitFL> getResultados() {
        if (resultados == null)
            resultados = new ArrayList<ArchivoSimitFL>();
        return resultados;
    }

    public void setResultados(List<ArchivoSimitFL> resultados) {
        this.resultados = resultados;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

}