package co.com.datatools.c2.dto.ws;

import javax.xml.datatype.XMLGregorianCalendar;

import co.com.datatools.c2.dto.AbstractDTO;

public class GestionCobroWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private Integer numeroRegistro;
    private XMLGregorianCalendar fechaGestion;
    private String tipoGestion;
    private String tipoNotificacion;
    private String evidencia;
    private Integer identificadorUbicabilidad;
    private Integer obligacion;
    private String resultado;
    private String observaciones;
    
    public Integer getObligacion() {
        return obligacion;
    }

    public void setObligacion(Integer obligacion) {
        this.obligacion = obligacion;
    }

    public String getTipoGestion() {
        return tipoGestion;
    }

    public void setTipoGestion(String tipoGestion) {
        this.tipoGestion = tipoGestion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public XMLGregorianCalendar getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(XMLGregorianCalendar fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

	public Integer getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(Integer numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}

	public Integer getIdentificadorUbicabilidad() {
		return identificadorUbicabilidad;
	}

	public void setIdentificadorUbicabilidad(Integer identificadorUbicabilidad) {
		this.identificadorUbicabilidad = identificadorUbicabilidad;
	}
}
