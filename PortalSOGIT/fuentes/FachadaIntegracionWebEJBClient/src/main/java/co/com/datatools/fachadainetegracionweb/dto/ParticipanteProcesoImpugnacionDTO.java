package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ParticipanteProcesoImpugnacionDTO")
public class ParticipanteProcesoImpugnacionDTO implements Serializable {
	
	private static final long serialVersionUID = 943658942210388790L;
	
	private String tipoParticipante;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String nombreParticipante;
	private String direccionParticipante;
	private String correoParticipante;
	
	public String getTipoParticipante() {
		return tipoParticipante;
	}
	public void setTipoParticipante(String tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getNombreParticipante() {
		return nombreParticipante;
	}
	public void setNombreParticipante(String nombreParticipante) {
		this.nombreParticipante = nombreParticipante;
	}
	public String getDireccionParticipante() {
		return direccionParticipante;
	}
	public void setDireccionParticipante(String direccionParticipante) {
		this.direccionParticipante = direccionParticipante;
	}
	public String getCorreoParticipante() {
		return correoParticipante;
	}
	public void setCorreoParticipante(String correoParticipante) {
		this.correoParticipante = correoParticipante;
	}
}
