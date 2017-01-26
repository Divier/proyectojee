package co.com.datatools.c2.managed_bean.coactivo.administracion.seguimiento;

import java.util.List;

import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.ObligacionCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaTrazabilidadDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de consulta de la traza de seguimiento de coactivos
 * 
 * @author divier.casas
 * 
 */
public class ConsultaTrazaSeguimientoHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "consultaTrazaSeguimientoHolderFL";

    /**
     * listado de documentos
     */
    private List<DocumentoProcesoDTO> lstDocumentos;

    /**
     * Documento seleccionado
     */
    private DocumentoProcesoDTO docSeleccionado;

    /**
     * listado de seguimientos consultados
     */
    private List<RespuestaTrazabilidadDTO> lstTrazabilidades;

    /**
     * Seguimiento seleccionado
     */
    private RespuestaTrazabilidadDTO trazaSeleccionada;

    private boolean verDocumento;

    /**
     * listado de obligaciones consultados
     */
    private List<ObligacionCoactivoDTO> lstObligaciones;

    public ConsultaTrazaSeguimientoHolderFL() {
    }

    public List<RespuestaTrazabilidadDTO> getLstTrazabilidades() {
        return lstTrazabilidades;
    }

    public void setLstTrazabilidades(List<RespuestaTrazabilidadDTO> lstTrazabilidades) {
        this.lstTrazabilidades = lstTrazabilidades;
    }

    public RespuestaTrazabilidadDTO getTrazaSeleccionada() {
        return trazaSeleccionada;
    }

    public void setTrazaSeleccionada(RespuestaTrazabilidadDTO trazaSeleccionada) {
        this.trazaSeleccionada = trazaSeleccionada;
    }

    public boolean isVerDocumento() {
        return verDocumento;
    }

    public void setVerDocumento(boolean verDocumento) {
        this.verDocumento = verDocumento;
    }

    public List<DocumentoProcesoDTO> getLstDocumentos() {
        return lstDocumentos;
    }

    public void setLstDocumentos(List<DocumentoProcesoDTO> lstDocumentos) {
        this.lstDocumentos = lstDocumentos;
    }

    public DocumentoProcesoDTO getDocSeleccionado() {
        return docSeleccionado;
    }

    public void setDocSeleccionado(DocumentoProcesoDTO docSeleccionado) {
        this.docSeleccionado = docSeleccionado;
    }

    public List<ObligacionCoactivoDTO> getLstObligaciones() {
        return lstObligaciones;
    }

    public void setLstObligaciones(List<ObligacionCoactivoDTO> lstObligaciones) {
        this.lstObligaciones = lstObligaciones;
    }

}