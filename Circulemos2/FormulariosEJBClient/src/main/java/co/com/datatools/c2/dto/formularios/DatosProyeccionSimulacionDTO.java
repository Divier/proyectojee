/**
 * 
 */
package co.com.datatools.c2.dto.formularios;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author javier.fajardo
 *
 */
public class DatosProyeccionSimulacionDTO implements EntidadDtoC2 {

	private static final long serialVersionUID = 1L;

	// --- Atributos
	private Integer numeroCuota;
	private Date fechaPagoOportuna;
	private BigDecimal valorCapital;
	private BigDecimal valorInteres;
	private BigDecimal valorTotalCuota;
	private BigDecimal saldoObligacion;	

	// --- Constructor
	public DatosProyeccionSimulacionDTO() {}

	public Integer getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public Date getFechaPagoOportuna() {
		return fechaPagoOportuna;
	}

	public void setFechaPagoOportuna(Date fechaPagoOportuna) {
		this.fechaPagoOportuna = fechaPagoOportuna;
	}

	public BigDecimal getValorCapital() {
		return valorCapital;
	}

	public void setValorCapital(BigDecimal valorCapital) {
		this.valorCapital = valorCapital;
	}

	public BigDecimal getValorInteres() {
		return valorInteres;
	}

	public void setValorInteres(BigDecimal valorInteres) {
		this.valorInteres = valorInteres;
	}

	public BigDecimal getValorTotalCuota() {
		return valorTotalCuota;
	}

	public void setValorTotalCuota(BigDecimal valorTotalCuota) {
		this.valorTotalCuota = valorTotalCuota;
	}

	public BigDecimal getSaldoObligacion() {
		return saldoObligacion;
	}

	public void setSaldoObligacion(BigDecimal saldoObligacion) {
		this.saldoObligacion = saldoObligacion;
	}
}
