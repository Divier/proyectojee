package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * No regenerar se agregaron campos
 * 
 * @author Generated
 * @version 3.0 - Fri Jun 17 11:31:57 COT 2016
 */
public class FinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String numeroFinanciacion;
    private String numeroReferenciaTerceros;
    private Integer numeroCuotas;
    private Integer anio;
    private BigDecimal valorTotalCostasProcesales;
    private String justificacion;
    private PersonaDTO deudor;
    private PersonaDTO codeudor;
    private OrganismoTransitoDTO organismoTransito;
    private ConfiguracionFinanciacionDTO configuracionFinanciacion;
    private List<DetalleFinanciacionDTO> detallesFinanciacion;
    private List<ObligacionFinanciacionDTO> obligacionesFinanciacion;
    private List<FinanciacionCarteraDTO> financiacionCarteraList;
    private ProcesoDTO proceso;
    private Integer cantidadObligaciones;
    private Double tasaInteres;
    private BigDecimal valorMora;
    private TipoFuenteInformacionDTO fuenteInformacion;
    private BigDecimal valorTotalSaldoCapitalObligaciones;
    private BigDecimal valorTotalInteresesMoratorios;
    private BigDecimal valorTotal;
    private BigDecimal valorTotalFinanciarInteres;
    private Long idTramite;

    // Atributos colocados manualmente, no pertenecen a la entidad
    private Date fechaFinanciacion;
    private String firma;

    // --- Constructor
    public FinanciacionDTO() {
    }

    public FinanciacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFinanciacion() {
        return this.numeroFinanciacion;
    }

    public void setNumeroFinanciacion(String numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public Integer getNumeroCuotas() {
        return this.numeroCuotas;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getValorTotalCostasProcesales() {
        return this.valorTotalCostasProcesales;
    }

    public void setValorTotalCostasProcesales(BigDecimal valorTotalCostasProcesales) {
        this.valorTotalCostasProcesales = valorTotalCostasProcesales;
    }

    public PersonaDTO getDeudor() {
        return this.deudor;
    }

    public void setDeudor(PersonaDTO deudor) {
        this.deudor = deudor;
    }

    public PersonaDTO getCodeudor() {
        return this.codeudor;
    }

    public void setCodeudor(PersonaDTO codeudor) {
        this.codeudor = codeudor;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public ConfiguracionFinanciacionDTO getConfiguracionFinanciacion() {
        return this.configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacionDTO configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleFinanciacionDTO> getDetallesFinanciacion() {
        if (this.detallesFinanciacion == null) {
            this.detallesFinanciacion = new java.util.ArrayList<>(1);
        }
        return this.detallesFinanciacion;
    }

    public void setDetallesFinanciacion(List<DetalleFinanciacionDTO> detallesFinanciacion) {
        this.detallesFinanciacion = detallesFinanciacion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ObligacionFinanciacionDTO> getObligacionesFinanciacion() {
        if (this.obligacionesFinanciacion == null) {
            this.obligacionesFinanciacion = new java.util.ArrayList<>(1);
        }
        return this.obligacionesFinanciacion;
    }

    public void setObligacionesFinanciacion(List<ObligacionFinanciacionDTO> obligacionesFinanciacion) {
        this.obligacionesFinanciacion = obligacionesFinanciacion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<FinanciacionCarteraDTO> getFinanciacionCarteraList() {
        if (this.financiacionCarteraList == null) {
            this.financiacionCarteraList = new java.util.ArrayList<>(1);
        }
        return this.financiacionCarteraList;
    }

    public void setFinanciacionCarteraList(List<FinanciacionCarteraDTO> financiacionCarteraList) {
        this.financiacionCarteraList = financiacionCarteraList;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public ProcesoDTO getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoDTO proceso) {
        this.proceso = proceso;
    }

    public Integer getCantidadObligaciones() {
        return cantidadObligaciones;
    }

    public void setCantidadObligaciones(Integer cantidadObligaciones) {
        this.cantidadObligaciones = cantidadObligaciones;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public BigDecimal getValorMora() {
        return valorMora;
    }

    public void setValorMora(BigDecimal valorMora) {
        this.valorMora = valorMora;
    }

    public TipoFuenteInformacionDTO getFuenteInformacion() {
        return fuenteInformacion;
    }

    public void setFuenteInformacion(TipoFuenteInformacionDTO fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    public BigDecimal getValorTotalSaldoCapitalObligaciones() {
        return valorTotalSaldoCapitalObligaciones;
    }

    public void setValorTotalSaldoCapitalObligaciones(BigDecimal valorTotalSaldoCapitalObligaciones) {
        this.valorTotalSaldoCapitalObligaciones = valorTotalSaldoCapitalObligaciones;
    }

    public BigDecimal getValorTotalInteresesMoratorios() {
        return valorTotalInteresesMoratorios;
    }

    public void setValorTotalInteresesMoratorios(BigDecimal valorTotalInteresesMoratorios) {
        this.valorTotalInteresesMoratorios = valorTotalInteresesMoratorios;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorTotalFinanciarInteres() {
        return valorTotalFinanciarInteres;
    }

    public void setValorTotalFinanciarInteres(BigDecimal valorTotalFinanciarInteres) {
        this.valorTotalFinanciarInteres = valorTotalFinanciarInteres;
    }

    public String getNumeroReferenciaTerceros() {
        return numeroReferenciaTerceros;
    }

    public void setNumeroReferenciaTerceros(String numeroReferenciaTerceros) {
        this.numeroReferenciaTerceros = numeroReferenciaTerceros;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public Date getFechaFinanciacion() {
        return fechaFinanciacion;
    }

    public void setFechaFinanciacion(Date fechaFinanciacion) {
        this.fechaFinanciacion = fechaFinanciacion;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

}
