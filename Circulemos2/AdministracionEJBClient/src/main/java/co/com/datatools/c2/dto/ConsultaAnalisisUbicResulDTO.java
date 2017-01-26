package co.com.datatools.c2.dto;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

public class ConsultaAnalisisUbicResulDTO implements EntidadDtoC2{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PersonaDTO> direccionPersonas;

	private List<PersonaDTO> telefonoPersonas;
	
	private List<PersonaDTO> correoPersonas;
	
	private List<InconsistenciaAnalisisUbicDTO> inconsistencias;
	
	private DocumentoResultadoCargueDTO docDireccionesPersonas;

	private DocumentoResultadoCargueDTO docTelefonoPersonas;
	
	private DocumentoResultadoCargueDTO docCorreoPersonas;
	
	private DocumentoResultadoCargueDTO docInconsistencias;
	
	private CargueArchivoDTO cargueArchivo;
	
	public ConsultaAnalisisUbicResulDTO(){
		this.direccionPersonas = new ArrayList<PersonaDTO>();
		this.telefonoPersonas = new ArrayList<PersonaDTO>();
		this.correoPersonas = new ArrayList<PersonaDTO>();
		this.inconsistencias = new ArrayList<InconsistenciaAnalisisUbicDTO>();
	}


	public List<InconsistenciaAnalisisUbicDTO> getInconsistencias() {
		return inconsistencias;
	}

	public void setInconsistencias(List<InconsistenciaAnalisisUbicDTO> inconsistencias) {
		this.inconsistencias = inconsistencias;
	}

	public List<PersonaDTO> getDireccionPersonas() {
		return direccionPersonas;
	}


	public void setDireccionPersonas(List<PersonaDTO> direccionPersonas) {
		this.direccionPersonas = direccionPersonas;
	}


	public List<PersonaDTO> getTelefonoPersonas() {
		return telefonoPersonas;
	}


	public void setTelefonoPersonas(List<PersonaDTO> telefonoPersonas) {
		this.telefonoPersonas = telefonoPersonas;
	}


	public List<PersonaDTO> getCorreoPersonas() {
		return correoPersonas;
	}


	public void setCorreoPersonas(List<PersonaDTO> correoPersonas) {
		this.correoPersonas = correoPersonas;
	}
	
	public DocumentoResultadoCargueDTO getDocDireccionesPersonas() {
		return docDireccionesPersonas;
	}

	public void setDocDireccionesPersonas(DocumentoResultadoCargueDTO docDireccionesPersonas) {
		this.docDireccionesPersonas = docDireccionesPersonas;
	}

	public DocumentoResultadoCargueDTO getDocTelefonoPersonas() {
		return docTelefonoPersonas;
	}

	public void setDocTelefonoPersonas(DocumentoResultadoCargueDTO docTelefonoPersonas) {
		this.docTelefonoPersonas = docTelefonoPersonas;
	}

	public DocumentoResultadoCargueDTO getDocCorreoPersonas() {
		return docCorreoPersonas;
	}

	public void setDocCorreoPersonas(DocumentoResultadoCargueDTO docCorreoPersonas) {
		this.docCorreoPersonas = docCorreoPersonas;
	}

	public DocumentoResultadoCargueDTO getDocInconsistencias() {
		return docInconsistencias;
	}

	public void setDocInconsistencias(DocumentoResultadoCargueDTO docInconsistencias) {
		this.docInconsistencias = docInconsistencias;
	}

	public CargueArchivoDTO getCargueArchivo() {
		return cargueArchivo;
	}

	public void setCargueArchivo(CargueArchivoDTO cargueArchivo) {
		this.cargueArchivo = cargueArchivo;
	}
}
