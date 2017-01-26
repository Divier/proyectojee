package co.com.datatools.c2.dto.formularios;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Sep 17 14:44:34 COT 2015
 */
public class DetalleCambioEstadoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String numeroInicial;
    private String numeroFinal;
    private Date fechaMovimiento;
    private Integer cantidadFormularios;
    private String folio;
    private String trama;
    private Date fechaAplicacionEstado;
    private String observaciones;
    private RangoFormularioDTO rangoFormulario;
    private ResponsableFormularioDTO responsableFormulario;
    private DocumentoFormularioDTO documentoFormulario;
    private CausalCambioEstadoDTO causalCambioEstado;
    private EstadoFormularioDTO estadoFormulario;
    private List<SeguimientoFormularioDTO> seguimientoFormularioList;
    private List<FormularioDTO> formularioList;
    private boolean empresa;
    private boolean organismo;
    private boolean esEvento;

    // --- Constructor
    public DetalleCambioEstadoDTO() {
    }

    public DetalleCambioEstadoDTO(Long id) {
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

    public Date getFechaMovimiento() {
        return this.fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Integer getCantidadFormularios() {
        return this.cantidadFormularios;
    }

    public void setCantidadFormularios(Integer cantidadFormularios) {
        this.cantidadFormularios = cantidadFormularios;
    }

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getTrama() {
        return this.trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public Date getFechaAplicacionEstado() {
        return this.fechaAplicacionEstado;
    }

    public void setFechaAplicacionEstado(Date fechaAplicacionEstado) {
        this.fechaAplicacionEstado = fechaAplicacionEstado;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public RangoFormularioDTO getRangoFormulario() {
        return this.rangoFormulario;
    }

    public void setRangoFormulario(RangoFormularioDTO rangoFormulario) {
        this.rangoFormulario = rangoFormulario;
    }

    public ResponsableFormularioDTO getResponsableFormulario() {
        return this.responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormularioDTO responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

    public DocumentoFormularioDTO getDocumentoFormulario() {
        return this.documentoFormulario;
    }

    public void setDocumentoFormulario(DocumentoFormularioDTO documentoFormulario) {
        this.documentoFormulario = documentoFormulario;
    }

    public CausalCambioEstadoDTO getCausalCambioEstado() {
        return this.causalCambioEstado;
    }

    public void setCausalCambioEstado(CausalCambioEstadoDTO causalCambioEstado) {
        this.causalCambioEstado = causalCambioEstado;
    }

    public EstadoFormularioDTO getEstadoFormulario() {
        return this.estadoFormulario;
    }

    public void setEstadoFormulario(EstadoFormularioDTO estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<SeguimientoFormularioDTO> getSeguimientoFormularioList() {
        if (this.seguimientoFormularioList == null) {
            this.seguimientoFormularioList = new java.util.ArrayList<>(1);
        }
        return this.seguimientoFormularioList;
    }

    public void setSeguimientoFormularioList(List<SeguimientoFormularioDTO> seguimientoFormularioList) {
        this.seguimientoFormularioList = seguimientoFormularioList;
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

    public boolean isEmpresa() {
        return empresa;
    }

    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    }

    public boolean isOrganismo() {
        return organismo;
    }

    public void setOrganismo(boolean organismo) {
        this.organismo = organismo;
    }

    public boolean isEsEvento() {
        return esEvento;
    }

    public void setEsEvento(boolean esEvento) {
        this.esEvento = esEvento;
    }

}
