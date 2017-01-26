package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:40:30 COT 2015
 */
public class NotificacionSimitDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer cantidadRegistros;
    private Date fechaGeneracion;
    private String idDocumento;
    private String rutaDocumento;
    private OrganismoTransitoDTO codigoOrganismo;
    private TipoDocumentoEnvioSimitDTO tipoDocumentoEnvioSimit;
    private TipoResultadoEnvioSimitDTO tipoResultadoEnvioSimit;
    private List<ArchivoNotificacionSimitDTO> archivoNotificacionSimits;

    // --- Constructor
    public NotificacionSimitDTO() {
    }

    public NotificacionSimitDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadRegistros() {
        return this.cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getRutaDocumento() {
        return this.rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public TipoDocumentoEnvioSimitDTO getTipoDocumentoEnvioSimit() {
        return this.tipoDocumentoEnvioSimit;
    }

    public void setTipoDocumentoEnvioSimit(TipoDocumentoEnvioSimitDTO tipoDocumentoEnvioSimit) {
        this.tipoDocumentoEnvioSimit = tipoDocumentoEnvioSimit;
    }

    public TipoResultadoEnvioSimitDTO getTipoResultadoEnvioSimit() {
        return this.tipoResultadoEnvioSimit;
    }

    public void setTipoResultadoEnvioSimit(TipoResultadoEnvioSimitDTO tipoResultadoEnvioSimit) {
        this.tipoResultadoEnvioSimit = tipoResultadoEnvioSimit;
    }

    public OrganismoTransitoDTO getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransitoDTO codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public List<ArchivoNotificacionSimitDTO> getArchivoNotificacionSimits() {
        return archivoNotificacionSimits;
    }

    public void setArchivoNotificacionSimits(List<ArchivoNotificacionSimitDTO> archivoNotificacionSimits) {
        this.archivoNotificacionSimits = archivoNotificacionSimits;
    }

}
