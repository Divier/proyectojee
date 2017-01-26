package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 27 10:31:46 COT 2016
 */
public class ErrorGestionPagoSacDTO implements EntidadDtoC2 {

	private static final long serialVersionUID = 1L;
	// --- Atributos
	private Integer id;
	private String codigo;
	private String descripcion;
	
	 // --- Constructor
    public ErrorGestionPagoSacDTO() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
