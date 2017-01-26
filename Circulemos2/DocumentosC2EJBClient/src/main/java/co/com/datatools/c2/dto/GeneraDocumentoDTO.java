package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import co.com.datatools.c2.negocio.interfaces.IPlantillaGenerable;

/**
 * Contiene la información para generar un documento desde el sistema con el módulo encargado de documentos.
 * 
 * @author julio.pinzon
 */
public class GeneraDocumentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Código del organismo al cual pertenecen los comparendos a consultar.
     */
    private int codigoOrganismo;

    /**
     * Contiene el cicomparendo a consultar
     */
    private Long cicomparendo;
    /**
     * Identifica el tipo de documento que se va a generar.
     */
    private IPlantillaGenerable tipoDocumentoGenerado;

    /**
     * Fecha de generación del documento
     */
    private Date fechaGeneracion;

    /**
     * Parametros que se deben cambiar en la ubicacion del archivo
     */
    private Object[] valoresParametros;

    /**
     * Indica si es una vista preliminar
     */
    private boolean preliminar;

    /**
     * Indica la ubicacion del archivo
     */
    private String ubicacion;

    /**
     * Registros de la vista preliminar
     */
    private Map<String, Object> valoresVistaPreliminar;

    public IPlantillaGenerable getTipoDocumentoGenerado() {
        return tipoDocumentoGenerado;
    }

    public void setIdTipoDocumentoGenerado(IPlantillaGenerable tipoDocumentoGenerado) {
        this.tipoDocumentoGenerado = tipoDocumentoGenerado;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isPreliminar() {
        return preliminar;
    }

    public void setPreliminar(boolean preliminar) {
        this.preliminar = preliminar;
    }

    public Object[] getValoresParametros() {
        return valoresParametros;
    }

    public void setValoresParametros(Object[] valoresParametros) {
        this.valoresParametros = valoresParametros;
    }

    public Map<String, Object> getValoresVistaPreliminar() {
        return valoresVistaPreliminar;
    }

    public void setValoresVistaPreliminar(Map<String, Object> valoresVistaPreliminar) {
        this.valoresVistaPreliminar = valoresVistaPreliminar;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

}