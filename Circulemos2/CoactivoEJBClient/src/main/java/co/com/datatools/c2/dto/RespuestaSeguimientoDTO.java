package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores para mostrar en la tabla de resultados de la consulta de seguimiento de cobro coactivo
 * 
 * @author divier.casas
 * 
 */
public class RespuestaSeguimientoDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long idProceso;

    private Date fechaGeneraCoactivo;
    private String numeroCoactivo;
    private String nombreDeudor;

    private String numeroProceso;
    private Date fechaProceso;
    private String tipoDocDeudor;
    private String numeroDocDeudor;

    private Long idTramite;
    private Long idCoactivo;

    private BigDecimal valorCoactivo;
    private BigDecimal valorCostas;

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public Date getFechaGeneraCoactivo() {
        return fechaGeneraCoactivo;
    }

    public void setFechaGeneraCoactivo(Date fechaGeneraCoactivo) {
        this.fechaGeneraCoactivo = fechaGeneraCoactivo;
    }

    public String getNumeroCoactivo() {
        return numeroCoactivo;
    }

    public void setNumeroCoactivo(String numeroCoactivo) {
        this.numeroCoactivo = numeroCoactivo;
    }

    public String getNombreDeudor() {
        return nombreDeudor;
    }

    public void setNombreDeudor(String nombreDeudor) {
        this.nombreDeudor = nombreDeudor;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getTipoDocDeudor() {
        return tipoDocDeudor;
    }

    public void setTipoDocDeudor(String tipoDocDeudor) {
        this.tipoDocDeudor = tipoDocDeudor;
    }

    public String getNumeroDocDeudor() {
        return numeroDocDeudor;
    }

    public void setNumeroDocDeudor(String numeroDocDeudor) {
        this.numeroDocDeudor = numeroDocDeudor;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public Long getIdCoactivo() {
        return idCoactivo;
    }

    public void setIdCoactivo(Long idCoactivo) {
        this.idCoactivo = idCoactivo;
    }

    public BigDecimal getValorCoactivo() {
        return valorCoactivo;
    }

    public void setValorCoactivo(BigDecimal valorCoactivo) {
        this.valorCoactivo = valorCoactivo;
    }

    public BigDecimal getValorCostas() {
        return valorCostas;
    }

    public void setValorCostas(BigDecimal valorCostas) {
        this.valorCostas = valorCostas;
    }

}