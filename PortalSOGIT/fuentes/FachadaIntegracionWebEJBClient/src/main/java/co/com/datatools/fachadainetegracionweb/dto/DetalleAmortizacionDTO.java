package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "DetalleAmortizacionDTO")
public class DetalleAmortizacionDTO implements Serializable {
	
	private static final long serialVersionUID = -2689974052774617223L;
	
	private int numeroCuota;
	private Date fechaPagoOportuno;
	private BigDecimal valorCuota;
	private BigDecimal recargoCuota;
	private BigDecimal valorTotal;
	private BigDecimal saldoFinanciacion;
	private Date fechaTransaccion;
	public int getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public Date getFechaPagoOportuno() {
		return fechaPagoOportuno;
	}
	public void setFechaPagoOportuno(Date fechaPagoOportuno) {
		this.fechaPagoOportuno = fechaPagoOportuno;
	}
	public BigDecimal getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}
	public BigDecimal getRecargoCuota() {
		return recargoCuota;
	}
	public void setRecargoCuota(BigDecimal recargoCuota) {
		this.recargoCuota = recargoCuota;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getSaldoFinanciacion() {
		return saldoFinanciacion;
	}
	public void setSaldoFinanciacion(BigDecimal saldoFinanciacion) {
		this.saldoFinanciacion = saldoFinanciacion;
	}
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
}
