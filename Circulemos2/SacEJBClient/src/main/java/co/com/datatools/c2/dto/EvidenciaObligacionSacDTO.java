package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;
/**
 * @author Generated
 * @version 3.0 - Tue May 17 14:52:16 COT 2016
 */
public class EvidenciaObligacionSacDTO implements EntidadDtoC2 { 
	private static final long serialVersionUID = 1L;
	// --- Atributos
	private String idEvidenciaObligacionSac;
	private String nombre;
	private String url;
    private String numeroCitacion;
    private String numeroFactura;
	private ObligacionSacDTO obligacionSac;

	// --- Constructor
	public EvidenciaObligacionSacDTO () {
	}

	public EvidenciaObligacionSacDTO (String idEvidenciaObligacionSac ) {
		this.idEvidenciaObligacionSac=idEvidenciaObligacionSac;

	}

	// --- Getters-Setters
	public String getIdEvidenciaObligacionSac () {
		return this.idEvidenciaObligacionSac;
	}

	public void setIdEvidenciaObligacionSac (String idEvidenciaObligacionSac) {
		this.idEvidenciaObligacionSac=idEvidenciaObligacionSac;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ObligacionSacDTO getObligacionSac () {
		return this.obligacionSac;
	}

	public void setObligacionSac (ObligacionSacDTO obligacionSac) {
		this.obligacionSac=obligacionSac;
	}

	public String getNumeroCitacion() {
		return numeroCitacion;
	}

	public void setNumeroCitacion(String numeroCitacion) {
		this.numeroCitacion = numeroCitacion;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
}
