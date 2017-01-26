package co.com.datatools.c2.dto;

import java.util.List;
import java.util.Date;
import java.math.*;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 14 15:30:34 COT 2016
 */
public class EstadoEventoDTO implements EntidadDtoC2 {
	private static final long serialVersionUID = 1L;
	// --- Atributos
	private Integer id;
	private String codigo;
	private String descripcion;
	private Boolean estado;
	private String nombre;
	private String sigla;

	// --- Constructor
	public EstadoEventoDTO() {
	}

	public EstadoEventoDTO(Integer id) {
		this.id = id;

	}

	// --- Getters-Setters
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
