package co.com.datatools.c2.dto.formularios;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 02 10:45:13 COT 2015
 */
public class RangoFormularioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String numeroInicial;
    private String numeroFinal;
    private Date fechaAutorizacion;
    private Integer cantidadTotal;
    private Boolean alertaStock;
    private Integer cantidadDisponible;
    private OrganismoTransitoDTO codigoOrganismo;
    private DocumentoFormularioDTO documentoFormulario;
    private TipoFormularioDTO tipoFormulario;
    private NumeracionFormularioDTO numeracion;
    private List<FormularioDTO> formularioList;
    private List<DetalleCambioEstadoDTO> detalleCambioEstadoList;
    private ArchivoTransportableDTO archivoAutorizacion;
    private boolean tieneDetalles;

    // --- Constructor
    public RangoFormularioDTO() {
    }

    public RangoFormularioDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroInicial() {
        return this.numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
        return this.numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public Date getFechaAutorizacion() {
        return this.fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Integer getCantidadTotal() {
        return this.cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Boolean getAlertaStock() {
        return this.alertaStock;
    }

    public void setAlertaStock(Boolean alertaStock) {
        this.alertaStock = alertaStock;
    }

    public Integer getCantidadDisponible() {
        return this.cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public OrganismoTransitoDTO getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransitoDTO codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public DocumentoFormularioDTO getDocumentoFormulario() {
        return this.documentoFormulario;
    }

    public void setDocumentoFormulario(DocumentoFormularioDTO documentoFormulario) {
        this.documentoFormulario = documentoFormulario;
    }

    public TipoFormularioDTO getTipoFormulario() {
        return this.tipoFormulario;
    }

    public void setTipoFormulario(TipoFormularioDTO tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public NumeracionFormularioDTO getNumeracion() {
        return this.numeracion;
    }

    public void setNumeracion(NumeracionFormularioDTO numeracion) {
        this.numeracion = numeracion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<FormularioDTO> getFormularioList() {
        if (this.formularioList == null) {
            this.formularioList = new java.util.ArrayList<>(1);
        }
        return this.formularioList;
    }

    public void setFormularioList(List<FormularioDTO> formularioList) {
        this.formularioList = formularioList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleCambioEstadoDTO> getDetalleCambioEstadoList() {
        if (this.detalleCambioEstadoList == null) {
            this.detalleCambioEstadoList = new java.util.ArrayList<>(1);
        }
        return this.detalleCambioEstadoList;
    }

    public void setDetalleCambioEstadoList(List<DetalleCambioEstadoDTO> detalleCambioEstadoList) {
        this.detalleCambioEstadoList = detalleCambioEstadoList;
    }

    public ArchivoTransportableDTO getArchivoAutorizacion() {
        return archivoAutorizacion;
    }

    public void setArchivoAutorizacion(ArchivoTransportableDTO archivoAutorizacion) {
        this.archivoAutorizacion = archivoAutorizacion;
    }

    public boolean isTieneDetalles() {
        return tieneDetalles;
    }

    public void setTieneDetalles(boolean tieneDetalles) {
        this.tieneDetalles = tieneDetalles;
    }

}
