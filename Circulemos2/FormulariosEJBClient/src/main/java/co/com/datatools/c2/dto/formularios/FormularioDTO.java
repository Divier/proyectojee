package co.com.datatools.c2.dto.formularios;

import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Fri Sep 04 16:15:44 COT 2015
 */
public class FormularioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String numeroFormulario;
    private EstadoFormularioDTO estadoFormulario;
    private TipoFormularioDTO tipoFormulario;
    private ResponsableFormularioDTO responsableFormulario;
    private RangoFormularioDTO rangoFormulario;
    private OrganismoTransitoDTO codigoOrganismo;
    private DetalleCambioEstadoDTO detalleCambioEstado;

    // ATRIBUTO UTILIZADO PARA EXTRACCION DE SEGUIMIENTO
    private List<DetalleCambioEstadoDTO> lstDetalleCambiosEstados;

    // --- Constructor
    public FormularioDTO() {
    }

    public FormularioDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFormulario() {
        return this.numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public EstadoFormularioDTO getEstadoFormulario() {
        return this.estadoFormulario;
    }

    public void setEstadoFormulario(EstadoFormularioDTO estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public TipoFormularioDTO getTipoFormulario() {
        return this.tipoFormulario;
    }

    public void setTipoFormulario(TipoFormularioDTO tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public ResponsableFormularioDTO getResponsableFormulario() {
        return this.responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormularioDTO responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

    public RangoFormularioDTO getRangoFormulario() {
        return this.rangoFormulario;
    }

    public void setRangoFormulario(RangoFormularioDTO rangoFormulario) {
        this.rangoFormulario = rangoFormulario;
    }

    public OrganismoTransitoDTO getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransitoDTO codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public DetalleCambioEstadoDTO getDetalleCambioEstado() {
        return this.detalleCambioEstado;
    }

    public void setDetalleCambioEstado(DetalleCambioEstadoDTO detalleCambioEstado) {
        this.detalleCambioEstado = detalleCambioEstado;
    }

    public List<DetalleCambioEstadoDTO> getLstDetalleCambiosEstados() {
        return lstDetalleCambiosEstados;
    }

    public void setLstDetalleCambiosEstados(List<DetalleCambioEstadoDTO> lstDetalleCambiosEstados) {
        this.lstDetalleCambiosEstados = lstDetalleCambiosEstados;
    }

}
