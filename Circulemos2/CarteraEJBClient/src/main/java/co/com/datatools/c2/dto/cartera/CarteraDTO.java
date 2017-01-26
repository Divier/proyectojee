package co.com.datatools.c2.dto.cartera;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:22:38 COT 2015
 */
public class CarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaActualizacion;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaRegistro;
    private Long idDeudor;
    private String nombre;
    private Integer origen;
    private BigDecimal saldoCapital;
    private BigDecimal saldoInteres;
    private BigDecimal saldoCostasProcesales;
    private EstadoObligacionDTO estadoObligacion;
    private TipoObligacionDTO tipoObligacion;
    private List<LogAfectarCarteraDTO> logAfectarCarteras;
    private List<MovimientosCarteraDTO> movimientosCarteras;
    private List<SaldoCarteraDTO> saldoCarteras;
    private List<TrazabilidadCarteraDTO> trazabilidadCarteras;

    // --- Constructor
    public CarteraDTO() {
    }

    public CarteraDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
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

    public EstadoObligacionDTO getEstadoObligacion() {
        return this.estadoObligacion;
    }

    public void setEstadoObligacion(EstadoObligacionDTO estadoObligacion) {
        this.estadoObligacion = estadoObligacion;
    }

    public TipoObligacionDTO getTipoObligacion() {
        return this.tipoObligacion;
    }

    public void setTipoObligacion(TipoObligacionDTO tipoObligacion) {
        this.tipoObligacion = tipoObligacion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<LogAfectarCarteraDTO> getLogAfectarCarteras() {
        if (this.logAfectarCarteras == null) {
            this.logAfectarCarteras = new java.util.ArrayList<>(1);
        }
        return this.logAfectarCarteras;
    }

    public void setLogAfectarCarteras(List<LogAfectarCarteraDTO> logAfectarCarteras) {
        this.logAfectarCarteras = logAfectarCarteras;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<MovimientosCarteraDTO> getMovimientosCarteras() {
        if (this.movimientosCarteras == null) {
            this.movimientosCarteras = new java.util.ArrayList<>(1);
        }
        return this.movimientosCarteras;
    }

    public void setMovimientosCarteras(List<MovimientosCarteraDTO> movimientosCarteras) {
        this.movimientosCarteras = movimientosCarteras;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<SaldoCarteraDTO> getSaldoCarteras() {
        if (this.saldoCarteras == null) {
            this.saldoCarteras = new java.util.ArrayList<>(1);
        }
        return this.saldoCarteras;
    }

    public void setSaldoCarteras(List<SaldoCarteraDTO> saldoCarteras) {
        this.saldoCarteras = saldoCarteras;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<TrazabilidadCarteraDTO> getTrazabilidadCarteras() {
        if (this.trazabilidadCarteras == null) {
            this.trazabilidadCarteras = new java.util.ArrayList<>(1);
        }
        return this.trazabilidadCarteras;
    }

    public void setTrazabilidadCarteras(List<TrazabilidadCarteraDTO> trazabilidadCarteras) {
        this.trazabilidadCarteras = trazabilidadCarteras;
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
