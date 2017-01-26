package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 5
 * 
 */
@Entity
@NamedQueries(
        value = { @NamedQuery(name = "Cartera.findAll", query = "SELECT c FROM Cartera c"),
                @NamedQuery(
                        name = "Cartera.findCarteraVigenteByRefTipoObl",
                        query = "SELECT c FROM Cartera c"
                                + " WHERE c.tipoObligacion.id = :idTipoObligacion AND c.nombre = :referenciaObligacion"
                                + " AND c.estadoObligacion.id IN (:idEstadoObligacionL)") })
public class Cartera implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta cartera vigente por tipo de obligacion, referencia de obligacion (nombre) y estados de obligacion no finales
     * 
     * <pre>
     * SELECT c FROM Cartera c<br> WHERE c.tipoObligacion.id = :idTipoObligacion<br> AND c.nombre = :referenciaObligacion<br> AND c.estadoObligacion.id IN (:idEstadoObligacionL)
     * </pre>
     */
    public static final String SQ_CARTERA_VIGENTE_REF_TIPO_OBL = "Cartera.findCarteraVigenteByRefTipoObl";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartera")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "id_deudor")
    private Long idDeudor;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "origen")
    private Integer origen;

    @Column(name = "saldo_capital")
    private BigDecimal saldoCapital;

    @Column(name = "saldo_interes")
    private BigDecimal saldoInteres;

    @Column(name = "saldo_costas_procesales")
    private BigDecimal saldoCostasProcesales;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_estado_obligacion")
    private EstadoObligacion estadoObligacion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_obligacion")
    private TipoObligacion tipoObligacion;

    @OneToMany(mappedBy = "cartera", fetch = FetchType.LAZY)
    private List<LogAfectarCartera> logAfectarCarteras;

    @OneToMany(mappedBy = "cartera", fetch = FetchType.LAZY)
    private List<MovimientosCartera> movimientosCarteras;

    @OneToMany(mappedBy = "cartera", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<SaldoCartera> saldoCarteras;

    @OneToMany(mappedBy = "cartera", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<TrazabilidadCartera> trazabilidadCarteras;

    public Cartera() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdDeudor() {
        return this.idDeudor;
    }

    public void setIdDeudor(Long idDeudor) {
        this.idDeudor = idDeudor;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrigen() {
        return this.origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public EstadoObligacion getEstadoObligacion() {
        return this.estadoObligacion;
    }

    public void setEstadoObligacion(EstadoObligacion estadoObligacion) {
        this.estadoObligacion = estadoObligacion;
    }

    public TipoObligacion getTipoObligacion() {
        return this.tipoObligacion;
    }

    public void setTipoObligacion(TipoObligacion tipoObligacion) {
        this.tipoObligacion = tipoObligacion;
    }

    public List<LogAfectarCartera> getLogAfectarCarteras() {
        return this.logAfectarCarteras;
    }

    public void setLogAfectarCarteras(List<LogAfectarCartera> logAfectarCarteras) {
        this.logAfectarCarteras = logAfectarCarteras;
    }

    public LogAfectarCartera addLogAfectarCartera(LogAfectarCartera logAfectarCartera) {
        this.getLogAfectarCarteras().add(logAfectarCartera);
        logAfectarCartera.setCartera(this);

        return logAfectarCartera;
    }

    public List<MovimientosCartera> getMovimientosCarteras() {
        return this.movimientosCarteras;
    }

    public void setMovimientosCarteras(List<MovimientosCartera> movimientosCarteras) {
        this.movimientosCarteras = movimientosCarteras;
    }

    public MovimientosCartera addMovimientosCartera(MovimientosCartera movimientosCartera) {
        this.getMovimientosCarteras().add(movimientosCartera);
        movimientosCartera.setCartera(this);

        return movimientosCartera;
    }

    public List<SaldoCartera> getSaldoCarteras() {
        return this.saldoCarteras;
    }

    public void setSaldoCarteras(List<SaldoCartera> saldoCarteras) {
        this.saldoCarteras = saldoCarteras;
    }

    public SaldoCartera addSaldoCartera(SaldoCartera saldoCartera) {
        this.getSaldoCarteras().add(saldoCartera);
        saldoCartera.setCartera(this);
        return saldoCartera;
    }

    public List<TrazabilidadCartera> getTrazabilidadCarteras() {
        return this.trazabilidadCarteras;
    }

    public void setTrazabilidadCarteras(List<TrazabilidadCartera> trazabilidadCarteras) {
        this.trazabilidadCarteras = trazabilidadCarteras;
    }

    public TrazabilidadCartera addTrazabilidadCartera(TrazabilidadCartera trazabilidadCartera) {
        getTrazabilidadCarteras().add(trazabilidadCartera);
        trazabilidadCartera.setCartera(this);

        return trazabilidadCartera;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public BigDecimal getSaldoInteres() {
        return saldoInteres;
    }

    public void setSaldoInteres(BigDecimal saldoInteres) {
        this.saldoInteres = saldoInteres;
    }

    public BigDecimal getSaldoCostasProcesales() {
        return saldoCostasProcesales;
    }

    public void setSaldoCostasProcesales(BigDecimal saldoCostasProcesales) {
        this.saldoCostasProcesales = saldoCostasProcesales;
    }

}