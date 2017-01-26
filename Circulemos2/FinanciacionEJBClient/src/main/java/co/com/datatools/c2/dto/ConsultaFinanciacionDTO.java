package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO que se encarga de devolver los datos especificos para financiacion
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaFinanciacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idProceso;
    private Long idFinanciacion;
    private String numeroFinanciacion;
    private String estadoFinaciacion;
    private Integer idEstadoFinanciacion;
    private Date fechaSolicitud;
    private int cantidadObligaciones;
    private BigDecimal totalSaldoCapitalObligaciones;
    private BigDecimal valorTotalRecargo;
    private BigDecimal valorTotalCostasProcesales;
    private BigDecimal valorTotal;
    private BigDecimal valorTotalFinanciarInteres;
    private BigDecimal valorMora;
    private BigDecimal saldoPagar;
    private Long idTramite;
    private String origenFinanciacion;

    // Datos detalle financiacion
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombresApellidos;
    private Double tasaInteresFinanciacion;
    private int numeroCuotas;
    private List<ConsultaDetalleFinanciacionDTO> consultaDetalleFinanciacionDTOs;
    private List<ConsultaObligacionesFinanciacionDTO> consultaObligacionesFinanciacionDTOs;
    private List<TrazabilidadProcesoDTO> trazabilidadProcesoDTOs;

    public String getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(String numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public String getEstadoFinaciacion() {
        return estadoFinaciacion;
    }

    public void setEstadoFinaciacion(String estadoFinaciacion) {
        this.estadoFinaciacion = estadoFinaciacion;
    }

    public Integer getIdEstadoFinanciacion() {
        return idEstadoFinanciacion;
    }

    public void setIdEstadoFinanciacion(Integer idEstadoFinanciacion) {
        this.idEstadoFinanciacion = idEstadoFinanciacion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public BigDecimal getTotalSaldoCapitalObligaciones() {
        return totalSaldoCapitalObligaciones;
    }

    public void setTotalSaldoCapitalObligaciones(BigDecimal totalSaldoCapitalObligaciones) {
        this.totalSaldoCapitalObligaciones = totalSaldoCapitalObligaciones;
    }

    public BigDecimal getValorTotalRecargo() {
        return valorTotalRecargo;
    }

    public void setValorTotalRecargo(BigDecimal valorTotalRecargo) {
        this.valorTotalRecargo = valorTotalRecargo;
    }

    public BigDecimal getValorTotalCostasProcesales() {
        return valorTotalCostasProcesales;
    }

    public void setValorTotalCostasProcesales(BigDecimal valorTotalCostasProcesales) {
        this.valorTotalCostasProcesales = valorTotalCostasProcesales;
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

    public BigDecimal getValorMora() {
        return valorMora;
    }

    public void setValorMora(BigDecimal valorMora) {
        this.valorMora = valorMora;
    }

    public BigDecimal getSaldoPagar() {
        return saldoPagar;
    }

    public void setSaldoPagar(BigDecimal saldoPagar) {
        this.saldoPagar = saldoPagar;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdFinanciacion() {
        return idFinanciacion;
    }

    public void setIdFinanciacion(Long idFinanciacion) {
        this.idFinanciacion = idFinanciacion;
    }

    public List<ConsultaDetalleFinanciacionDTO> getConsultaDetalleFinanciacionDTOs() {
        return consultaDetalleFinanciacionDTOs;
    }

    public void setConsultaDetalleFinanciacionDTOs(List<ConsultaDetalleFinanciacionDTO> consultaDetalleFinanciacionDTOs) {
        this.consultaDetalleFinanciacionDTOs = consultaDetalleFinanciacionDTOs;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public Double getTasaInteresFinanciacion() {
        return tasaInteresFinanciacion;
    }

    public void setTasaInteresFinanciacion(Double tasaInteresFinanciacion) {
        this.tasaInteresFinanciacion = tasaInteresFinanciacion;
    }

    public List<ConsultaObligacionesFinanciacionDTO> getConsultaObligacionesFinanciacionDTOs() {
        return consultaObligacionesFinanciacionDTOs;
    }

    public void setConsultaObligacionesFinanciacionDTOs(
            List<ConsultaObligacionesFinanciacionDTO> consultaObligacionesFinanciacionDTOs) {
        this.consultaObligacionesFinanciacionDTOs = consultaObligacionesFinanciacionDTOs;
    }

    public List<TrazabilidadProcesoDTO> getTrazabilidadProcesoDTOs() {
        return trazabilidadProcesoDTOs;
    }

    public void setTrazabilidadProcesoDTOs(List<TrazabilidadProcesoDTO> trazabilidadProcesoDTOs) {
        this.trazabilidadProcesoDTOs = trazabilidadProcesoDTOs;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public int getCantidadObligaciones() {
        return cantidadObligaciones;
    }

    public void setCantidadObligaciones(int cantidadObligaciones) {
        this.cantidadObligaciones = cantidadObligaciones;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public String getOrigenFinanciacion() {
        return origenFinanciacion;
    }

    public void setOrigenFinanciacion(String origenFinanciacion) {
        this.origenFinanciacion = origenFinanciacion;
    }

}
