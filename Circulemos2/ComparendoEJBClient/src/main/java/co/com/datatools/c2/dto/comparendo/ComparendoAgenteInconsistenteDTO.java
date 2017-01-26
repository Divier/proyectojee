package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO resultado consulta comparendos inconsistentes por agente.
 * @author ricardo.chavarro
 *
 */
public class ComparendoAgenteInconsistenteDTO implements Serializable {
	
	private static final long serialVersionUID = 4363794909443351589L;	
	private Long cicomparendo;
	private Long idFacturaAxis;
	private Date fechaInfraccion;
	private Date horaInfraccion;
	private String placaAgenteTransito;
	private String inconsistencia;
	
	public Long getCicomparendo() {
		return cicomparendo;
	}
	public void setCicomparendo(Long cicomparendo) {
		this.cicomparendo = cicomparendo;
	}
	public Long getIdFacturaAxis() {
		return idFacturaAxis;
	}
	public void setIdFacturaAxis(Long idFacturaAxis) {
		this.idFacturaAxis = idFacturaAxis;
	}
	public Date getFechaInfraccion() {
		return fechaInfraccion;
	}
	public void setFechaInfraccion(Date fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	public Date getHoraInfraccion() {
		return horaInfraccion;
	}
	public void setHoraInfraccion(Date horaInfraccion) {
		this.horaInfraccion = horaInfraccion;
	}
	public String getPlacaAgenteTransito() {
		return placaAgenteTransito;
	}
	public void setPlacaAgenteTransito(String placaAgenteTransito) {
		this.placaAgenteTransito = placaAgenteTransito;
	}
	public String getInconsistencia() {
		return inconsistencia;
	}
	public void setInconsistencia(String inconsistencia) {
		this.inconsistencia = inconsistencia;
	}
}
