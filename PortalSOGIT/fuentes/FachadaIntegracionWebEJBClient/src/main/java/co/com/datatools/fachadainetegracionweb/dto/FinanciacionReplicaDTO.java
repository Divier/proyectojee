package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FinanciacionReplicaDTO")
public class FinanciacionReplicaDTO implements Serializable{

	private static final long serialVersionUID = 8292559544021271935L;
	
	private BigDecimal numeroFinanciacion;
	private short estadoFinanciacion;
	private Date fechaFinanciacion;
	private BigDecimal valorTotalFinanciado; 
	private BigDecimal saldoFinanciacion;
	
	public BigDecimal getNumeroFinanciacion() {
		return numeroFinanciacion;
	}
	public void setNumeroFinanciacion(BigDecimal numeroFinanciacion) {
		this.numeroFinanciacion = numeroFinanciacion;
	}
	public short getEstadoFinanciacion() {
		return estadoFinanciacion;
	}
	public void setEstadoFinanciacion(short estadoFinanciacion) {
		this.estadoFinanciacion = estadoFinanciacion;
	}
	public Date getFechaFinanciacion() {
		return fechaFinanciacion;
	}
	public void setFechaFinanciacion(Date fechaFinanciacion) {
		this.fechaFinanciacion = fechaFinanciacion;
	}
	public BigDecimal getValorTotalFinanciado() {
		return valorTotalFinanciado;
	}
	public void setValorTotalFinanciado(BigDecimal valorTotalFinanciado) {
		this.valorTotalFinanciado = valorTotalFinanciado;
	}
	public BigDecimal getSaldoFinanciacion() {
		return saldoFinanciacion;
	}
	public void setSaldoFinanciacion(BigDecimal saldoFinanciacion) {
		this.saldoFinanciacion = saldoFinanciacion;
	}
	
	 
}
