/**
 * 
 */
package co.com.datatools.c2.axis.ws.cliente.vo.financiacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto que se enviará como parametro en registro de financiacion
 * 
 * @author julio.pinzon 2016-08-16
 *
 */
public class FinanciacionVO implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    @SerializedName("convenio_referencia")
    private Long convenioReferencia;

    @SerializedName("tipo_identificacion")
    private String tipoIdentificacion;
    private String identificacion;
    private String empresa;
    private String usuario;

    @SerializedName("fecha_convenio")
    private Date fechaConvenio;

    @SerializedName("deuda_a_financiar")
    private BigDecimal deudaAFinanciar;
    private Integer plazo;

    @SerializedName("valor_cuota_incial")
    private BigDecimal valorCuotaIncial;

    @SerializedName("intereses_total")
    private BigDecimal interesesTotal;

    @SerializedName("porcentaje_interes")
    private BigDecimal porcentajeInteres;
    private List<CuotaVO> cuotas;
    private List<InfraccionVO> infracciones;

    public Long getConvenioReferencia() {
        return convenioReferencia;
    }

    public void setConvenioReferencia(Long convenioReferencia) {
        this.convenioReferencia = convenioReferencia;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaConvenio() {
        return fechaConvenio;
    }

    public void setFechaConvenio(Date fechaConvenio) {
        this.fechaConvenio = fechaConvenio;
    }

    public BigDecimal getDeudaAFinanciar() {
        return deudaAFinanciar;
    }

    public void setDeudaAFinanciar(BigDecimal deudaAFinanciar) {
        this.deudaAFinanciar = deudaAFinanciar;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public BigDecimal getValorCuotaIncial() {
        return valorCuotaIncial;
    }

    public void setValorCuotaIncial(BigDecimal valorCuotaIncial) {
        this.valorCuotaIncial = valorCuotaIncial;
    }

    public BigDecimal getInteresesTotal() {
        return interesesTotal;
    }

    public void setInteresesTotal(BigDecimal interesesTotal) {
        this.interesesTotal = interesesTotal;
    }

    public BigDecimal getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(BigDecimal porcentajeInteres) {
        this.porcentajeInteres = porcentajeInteres;
    }

    public List<CuotaVO> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<CuotaVO> cuotas) {
        this.cuotas = cuotas;
    }

    public List<InfraccionVO> getInfracciones() {
        return infracciones;
    }

    public void setInfracciones(List<InfraccionVO> infracciones) {
        this.infracciones = infracciones;
    }

}
