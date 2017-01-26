package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * Clase que encapsula los datos necesarios para cambiar a un estado indicado un conjunto de formularios de ciertos rangos
 * 
 * @author claudia.rodriguez
 * 
 */
public class CambioEstadoFormularioDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private Integer idEstadoFinal;
    private List<RangoDTO> listRangoDTO;

    private Long idDetalleCambioEstado;
    private String folio;
    private Integer idCausalCambioEstado;
    private String numeroDocumentoSoporte;
    private ArchivoTransportableDTO archivoTransportableDTO;
    private Date fechaAplicacionEstado;
    private String observaciones;

    public CambioEstadoFormularioDTO() {

    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getIdEstadoFinal() {
        return idEstadoFinal;
    }

    public void setIdEstadoFinal(Integer idEstadoFinal) {
        this.idEstadoFinal = idEstadoFinal;
    }

    public List<RangoDTO> getListRangoDTO() {
        return listRangoDTO;
    }

    public void setListRangoDTO(List<RangoDTO> listRangoDTO) {
        this.listRangoDTO = listRangoDTO;
    }

    public Long getIdDetalleCambioEstado() {
        return idDetalleCambioEstado;
    }

    public void setIdDetalleCambioEstado(Long idDetalleCambioEstado) {
        this.idDetalleCambioEstado = idDetalleCambioEstado;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getIdCausalCambioEstado() {
        return idCausalCambioEstado;
    }

    public void setIdCausalCambioEstado(Integer idCausalCambioEstado) {
        this.idCausalCambioEstado = idCausalCambioEstado;
    }

    public String getNumeroDocumentoSoporte() {
        return numeroDocumentoSoporte;
    }

    public void setNumeroDocumentoSoporte(String numeroDocumentoSoporte) {
        this.numeroDocumentoSoporte = numeroDocumentoSoporte;
    }

    public ArchivoTransportableDTO getArchivoTransportableDTO() {
        return archivoTransportableDTO;
    }

    public void setArchivoTransportableDTO(ArchivoTransportableDTO archivoTransportableDTO) {
        this.archivoTransportableDTO = archivoTransportableDTO;
    }

    public Date getFechaAplicacionEstado() {
        return fechaAplicacionEstado;
    }

    public void setFechaAplicacionEstado(Date fechaAplicacionEstado) {
        this.fechaAplicacionEstado = fechaAplicacionEstado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
