/**
 * 
 */
package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.util.EntidadDtoC2;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author javier.fajardo
 *
 */
public class ConsultaSimulacionDTO implements EntidadDtoC2 {

	private static final long serialVersionUID = 1L;
    // --- Atributos
	private String tipoObligacion;
	private String numeroObligacion;
	private Date fechaImposicion;
	private Date fechaNotificacion;
	private String codigoInfraccion;
	private Boolean estaEnCoactivo;
	private String placa;
	private BigDecimal valorMulta;
	private BigDecimal valorRecargo;
	private BigDecimal valorCostaProcesal;
	
	// --- Constructor
    public ConsultaSimulacionDTO() {
    }

	public String getTipoObligacion() {
		return tipoObligacion;
	}

	public void setTipoObligacion(String tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}

	public String getNumeroObligacion() {
		return numeroObligacion;
	}

	public void setNumeroObligacion(String numeroObligacion) {
		this.numeroObligacion = numeroObligacion;
	}

	public Date getFechaImposicion() {
		return fechaImposicion;
	}

	public void setFechaImposicion(Date fechaImposicion) {
		this.fechaImposicion = fechaImposicion;
	}

	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public String getCodigoInfraccion() {
		return codigoInfraccion;
	}

	public void setCodigoInfraccion(String codigoInfraccion) {
		this.codigoInfraccion = codigoInfraccion;
	}

	public Boolean getEstaEnCoactivo() {
		return estaEnCoactivo;
	}

	public void setEstaEnCoactivo(Boolean estaEnCoactivo) {
		this.estaEnCoactivo = estaEnCoactivo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorRecargo() {
		return valorRecargo;
	}

	public void setValorRecargo(BigDecimal valorRecargo) {
		this.valorRecargo = valorRecargo;
	}

	public BigDecimal getValorCostaProcesal() {
		return valorCostaProcesal;
	}

	public void setValorCostaProcesal(BigDecimal valorCostaProcesal) {
		this.valorCostaProcesal = valorCostaProcesal;
	}
}
