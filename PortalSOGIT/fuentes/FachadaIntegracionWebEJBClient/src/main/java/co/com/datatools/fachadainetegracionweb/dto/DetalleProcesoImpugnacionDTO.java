package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DetalleProcesoImpugnacionDTO")
public class DetalleProcesoImpugnacionDTO implements Serializable {
	
	private static final long serialVersionUID = -5097149233100080724L;
	
	private String numeroProceso;
	private Date fechaInicio;
	private Date fechaFin;
	private String numeroCitacion;
	private Date fechaInfraccion;
	private String codigoInfraccion;
	private String descripcionInfraccion;
	private String estadoProceso;
	private List<ParticipanteProcesoImpugnacionDTO> participantes;
	
	public String getNumeroProceso() {
		return numeroProceso;
	}
	public void setNumeroProceso(String numeroProceso) {
		this.numeroProceso = numeroProceso;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getNumeroCitacion() {
		return numeroCitacion;
	}
	public void setNumeroCitacion(String numeroCitacion) {
		this.numeroCitacion = numeroCitacion;
	}
	public Date getFechaInfraccion() {
		return fechaInfraccion;
	}
	public void setFechaInfraccion(Date fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	public String getCodigoInfraccion() {
		return codigoInfraccion;
	}
	public void setCodigoInfraccion(String codigoInfraccion) {
		this.codigoInfraccion = codigoInfraccion;
	}
	public String getDescripcionInfraccion() {
		return descripcionInfraccion;
	}
	public void setDescripcionInfraccion(String descripcionInfraccion) {
		this.descripcionInfraccion = descripcionInfraccion;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public List<ParticipanteProcesoImpugnacionDTO> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<ParticipanteProcesoImpugnacionDTO> participantes) {
		this.participantes = participantes;
	}
}
