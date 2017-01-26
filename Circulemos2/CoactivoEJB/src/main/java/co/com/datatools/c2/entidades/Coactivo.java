package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Funcionario;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the coactivo database table.
 * 
 */
@Entity
@Table(name = "coactivo")
@NamedQuery(name = "Coactivo.findAll", query = "SELECT c FROM Coactivo c")
public class Coactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coactivo")
    private Long id;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "cantidad_obligaciones")
    private Integer cantidadObligaciones;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;

    @Column(name = "id_tramite")
    private Long idTramite;

    @Column(name = "numero_coactivo")
    private String numeroCoactivo;

    @Column(name = "valor_total_costas_procesales")
    private BigDecimal valorTotalCostasProcesales;

    @Column(name = "valor_total_obligaciones")
    private BigDecimal valorTotalObligaciones;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proceso")
    private Proceso proceso;

    // bi-directional many-to-one association to CargueCoactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargue_coactivo")
    private CargueCoactivo cargueCoactivo;

    // bi-directional many-to-one association to ConfiguracionCoactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_coactivo")
    private ConfiguracionCoactivo configuracionCoactivo;

    // bi-directional many-to-one association to Direccion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    // bi-directional many-to-one association to Persona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_deudor")
    private Persona persona;

    // bi-directional many-to-one association to DetalleCargueCourier
    @OneToMany(mappedBy = "coactivo")
    private List<DetalleCargueCourier> detalleCargueCouriers;

    // bi-directional many-to-one association to ObligacionCoactivo
    @OneToMany(mappedBy = "coactivo")
    private List<ObligacionCoactivo> obligacionCoactivos;

    @OneToMany(mappedBy = "coactivo")
    private List<RadicarExcepcion> radicarExcepcions;

    // bi-directional many-to-one association to Funcionario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public Coactivo() {
    }

    public Integer getAnio() {
        return this.anio;
    }

    public Integer getCantidadObligaciones() {
        return this.cantidadObligaciones;
    }

    public void setCantidadObligaciones(Integer cantidadObligaciones) {
        this.cantidadObligaciones = cantidadObligaciones;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Long getIdTramite() {
        return this.idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public String getNumeroCoactivo() {
        return this.numeroCoactivo;
    }

    public void setNumeroCoactivo(String numeroCoactivo) {
        this.numeroCoactivo = numeroCoactivo;
    }

    public BigDecimal getValorTotalCostasProcesales() {
        return this.valorTotalCostasProcesales;
    }

    public void setValorTotalCostasProcesales(BigDecimal valorTotalCostasProcesales) {
        this.valorTotalCostasProcesales = valorTotalCostasProcesales;
    }

    public BigDecimal getValorTotalObligaciones() {
        return this.valorTotalObligaciones;
    }

    public void setValorTotalObligaciones(BigDecimal valorTotalObligaciones) {
        this.valorTotalObligaciones = valorTotalObligaciones;
    }

    public CargueCoactivo getCargueCoactivo() {
        return this.cargueCoactivo;
    }

    public void setCargueCoactivo(CargueCoactivo cargueCoactivo) {
        this.cargueCoactivo = cargueCoactivo;
    }

    public ConfiguracionCoactivo getConfiguracionCoactivo() {
        return this.configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivo configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<DetalleCargueCourier> getDetalleCargueCouriers() {
        return this.detalleCargueCouriers;
    }

    public void setDetalleCargueCouriers(List<DetalleCargueCourier> detalleCargueCouriers) {
        this.detalleCargueCouriers = detalleCargueCouriers;
    }

    public DetalleCargueCourier addDetalleCargueCourier(DetalleCargueCourier detalleCargueCourier) {
        getDetalleCargueCouriers().add(detalleCargueCourier);
        detalleCargueCourier.setCoactivo(this);

        return detalleCargueCourier;
    }

    public DetalleCargueCourier removeDetalleCargueCourier(DetalleCargueCourier detalleCargueCourier) {
        getDetalleCargueCouriers().remove(detalleCargueCourier);
        detalleCargueCourier.setCoactivo(null);

        return detalleCargueCourier;
    }

    public List<ObligacionCoactivo> getObligacionCoactivos() {
        return this.obligacionCoactivos;
    }

    public void setObligacionCoactivos(List<ObligacionCoactivo> obligacionCoactivos) {
        this.obligacionCoactivos = obligacionCoactivos;
    }

    public ObligacionCoactivo addObligacionCoactivo(ObligacionCoactivo obligacionCoactivo) {
        getObligacionCoactivos().add(obligacionCoactivo);
        obligacionCoactivo.setCoactivo(this);

        return obligacionCoactivo;
    }

    public ObligacionCoactivo removeObligacionCoactivo(ObligacionCoactivo obligacionCoactivo) {
        getObligacionCoactivos().remove(obligacionCoactivo);
        obligacionCoactivo.setCoactivo(null);

        return obligacionCoactivo;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public List<RadicarExcepcion> getRadicarExcepcions() {
        return radicarExcepcions;
    }

    public void setRadicarExcepcions(List<RadicarExcepcion> radicarExcepcions) {
        this.radicarExcepcions = radicarExcepcions;
    }

}