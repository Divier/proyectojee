package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@NamedQuery(name = "Financiacion.findAll", query = "SELECT f FROM Financiacion f")
public class Financiacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financiacion")
    private Long id;

    @Column(name = "numero_financiacion")
    private String numeroFinanciacion;

    @Column(name = "numero_referencia_terceros")
    private String numeroReferenciaTerceros;

    @Column(name = "numero_cuotas")
    private Integer numeroCuotas;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "valor_total_costas_procesales")
    private BigDecimal valorTotalCostasProcesales;

    @Column(name = "justificacion")
    private String justificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_deudor")
    private Persona deudor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_codeudor")
    private Persona codeudor;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_financiacion")
    private ConfiguracionFinanciacion configuracionFinanciacion;

    @OneToMany(mappedBy = "financiacion")
    private List<DetalleFinanciacion> detallesFinanciacion;

    @OneToMany(mappedBy = "financiacion")
    private List<ObligacionFinanciacion> obligacionesFinanciacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financiacion", fetch = FetchType.LAZY)
    private List<FinanciacionCartera> financiacionCarteraList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proceso")
    private Proceso proceso;

    @Column(name = "cantidad_obligaciones")
    private Integer cantidadObligaciones;

    @Column(name = "tasa_interes")
    private Double tasaInteres;

    @Column(name = "valor_mora")
    private BigDecimal valorMora;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_fuente_informacion")
    private TipoFuenteInformacion fuenteInformacion;

    @Column(name = "valor_total_saldo_capital_obliga")
    private BigDecimal valorTotalSaldoCapitalObligaciones;

    @Column(name = "valor_total_intereses_moratorios")
    private BigDecimal valorTotalInteresesMoratorios;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "valor_total_financiar_interes")
    private BigDecimal valorTotalFinanciarInteres;

    @Column(name = "id_tramite")
    private Long idTramite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(String numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getValorTotalCostasProcesales() {
        return valorTotalCostasProcesales;
    }

    public void setValorTotalCostasProcesales(BigDecimal valorTotalCostasProcesales) {
        this.valorTotalCostasProcesales = valorTotalCostasProcesales;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Persona getDeudor() {
        return deudor;
    }

    public void setDeudor(Persona deudor) {
        this.deudor = deudor;
    }

    public Persona getCodeudor() {
        return codeudor;
    }

    public void setCodeudor(Persona codeudor) {
        this.codeudor = codeudor;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public ConfiguracionFinanciacion getConfiguracionFinanciacion() {
        return configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacion configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

    public List<DetalleFinanciacion> getDetallesFinanciacion() {
        return detallesFinanciacion;
    }

    public void setDetallesFinanciacion(List<DetalleFinanciacion> detallesFinanciacion) {
        this.detallesFinanciacion = detallesFinanciacion;
    }

    public List<ObligacionFinanciacion> getObligacionesFinanciacion() {
        return obligacionesFinanciacion;
    }

    public void setObligacionesFinanciacion(List<ObligacionFinanciacion> obligacionesFinanciacion) {
        this.obligacionesFinanciacion = obligacionesFinanciacion;
    }

    public List<FinanciacionCartera> getFinanciacionCarteraList() {
        return financiacionCarteraList;
    }

    public void setFinanciacionCarteraList(List<FinanciacionCartera> financiacionCarteraList) {
        this.financiacionCarteraList = financiacionCarteraList;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
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

    public TipoFuenteInformacion getFuenteInformacion() {
        return fuenteInformacion;
    }

    public void setFuenteInformacion(TipoFuenteInformacion fuenteInformacion) {
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

}