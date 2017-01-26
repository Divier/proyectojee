package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.TipoResponsableSolidarioDTO;
import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:33:46 COT 2015
 */
public class ConfiguracionInfraccionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String descripcion;
    private String articulo;
    private Date fechaArticulo;
    private Boolean requierePlaca;
    private Boolean infractorObligatorio;
    private Short diasGeneraCartera;
    private Boolean generaCartera;
    private Boolean diaHabilGeneraCartera;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean aplicaDescuento;
    private Boolean aplicaEmbriaguez;
    private InfraccionDTO infraccion;
    private NormatividadDTO normatividad;
    private TipoEntidadAgenteTransitoDTO entidadAgenteTransito;
    private CausalInfraccionDTO causalInfraccion;
    private OrdenamientoPaisDTO ordenamientoPais;
    private List<TipoResponsableSolidarioDTO> tipoResponsableSolidarioList;
    private List<TipoSancionDTO> tipoSancionList;
    private List<TipoInfractorDTO> tipoInfractorList;
    // Atributos manipulacion desde interfaz.
    private String valDiaHabilGeneraCartera;
    private boolean cierreVigencia;

    // --- Constructor
    public ConfiguracionInfraccionDTO() {
    }

    public ConfiguracionInfraccionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArticulo() {
        return this.articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Date getFechaArticulo() {
        return this.fechaArticulo;
    }

    public void setFechaArticulo(Date fechaArticulo) {
        this.fechaArticulo = fechaArticulo;
    }

    public Boolean getRequierePlaca() {
        return this.requierePlaca;
    }

    public void setRequierePlaca(Boolean requierePlaca) {
        this.requierePlaca = requierePlaca;
    }

    public Boolean getInfractorObligatorio() {
        return this.infractorObligatorio;
    }

    public void setInfractorObligatorio(Boolean infractorObligatorio) {
        this.infractorObligatorio = infractorObligatorio;
    }

    public Short getDiasGeneraCartera() {
        return this.diasGeneraCartera;
    }

    public void setDiasGeneraCartera(Short diasGeneraCartera) {
        this.diasGeneraCartera = diasGeneraCartera;
    }

    public Boolean getGeneraCartera() {
        return this.generaCartera;
    }

    public void setGeneraCartera(Boolean generaCartera) {
        this.generaCartera = generaCartera;
    }

    public Boolean getDiaHabilGeneraCartera() {
        return this.diaHabilGeneraCartera;
    }

    public void setDiaHabilGeneraCartera(Boolean diaHabilGeneraCartera) {
        this.diaHabilGeneraCartera = diaHabilGeneraCartera;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getAplicaDescuento() {
        return this.aplicaDescuento;
    }

    public void setAplicaDescuento(Boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public Boolean getAplicaEmbriaguez() {
        return this.aplicaEmbriaguez;
    }

    public void setAplicaEmbriaguez(Boolean aplicaEmbriaguez) {
        this.aplicaEmbriaguez = aplicaEmbriaguez;
    }

    public InfraccionDTO getInfraccion() {
        return this.infraccion;
    }

    public void setInfraccion(InfraccionDTO infraccion) {
        this.infraccion = infraccion;
    }

    public NormatividadDTO getNormatividad() {
        return this.normatividad;
    }

    public void setNormatividad(NormatividadDTO normatividad) {
        this.normatividad = normatividad;
    }

    public TipoEntidadAgenteTransitoDTO getEntidadAgenteTransito() {
        return this.entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(TipoEntidadAgenteTransitoDTO entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }

    public CausalInfraccionDTO getCausalInfraccion() {
        return this.causalInfraccion;
    }

    public void setCausalInfraccion(CausalInfraccionDTO causalInfraccion) {
        this.causalInfraccion = causalInfraccion;
    }

    public OrdenamientoPaisDTO getOrdenamientoPais() {
        return this.ordenamientoPais;
    }

    public void setOrdenamientoPais(OrdenamientoPaisDTO ordenamientoPais) {
        this.ordenamientoPais = ordenamientoPais;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<TipoResponsableSolidarioDTO> getTipoResponsableSolidarioList() {
        if (this.tipoResponsableSolidarioList == null) {
            this.tipoResponsableSolidarioList = new java.util.ArrayList<>(1);
        }
        return this.tipoResponsableSolidarioList;
    }

    public void setTipoResponsableSolidarioList(List<TipoResponsableSolidarioDTO> tipoResponsableSolidarioList) {
        this.tipoResponsableSolidarioList = tipoResponsableSolidarioList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<TipoSancionDTO> getTipoSancionList() {
        if (this.tipoSancionList == null) {
            this.tipoSancionList = new java.util.ArrayList<>(1);
        }
        return this.tipoSancionList;
    }

    public void setTipoSancionList(List<TipoSancionDTO> tipoSancionList) {
        this.tipoSancionList = tipoSancionList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<TipoInfractorDTO> getTipoInfractorList() {
        if (this.tipoInfractorList == null) {
            this.tipoInfractorList = new java.util.ArrayList<>(1);
        }
        return this.tipoInfractorList;
    }

    public void setTipoInfractorList(List<TipoInfractorDTO> tipoInfractorList) {
        this.tipoInfractorList = tipoInfractorList;
    }

    public String getValDiaHabilGeneraCartera() {
        return valDiaHabilGeneraCartera;
    }

    public void setValDiaHabilGeneraCartera(String valDiaHabilGeneraCartera) {
        this.valDiaHabilGeneraCartera = valDiaHabilGeneraCartera;
    }

    public boolean isCierreVigencia() {
        return cierreVigencia;
    }

    public void setCierreVigencia(boolean cierreVigencia) {
        this.cierreVigencia = cierreVigencia;
    }

}
