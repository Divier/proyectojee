package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.log.ILogueable;

public class ResolucionSancionAutomaticaLogDTO implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Date fechaInicioProceso;
    private Date fechaFinalProceso;
    private List<String> numerosComparendo;
    private String codigoRespuesta;
    // Fecha y hora del sistema
    private Date fechaProcesamiento;
    private String correoEnviado;

    public Date getFechaInicioProceso() {
        return fechaInicioProceso;
    }

    public void setFechaInicioProceso(Date fechaInicioProceso) {
        this.fechaInicioProceso = fechaInicioProceso;
    }

    public Date getFechaFinalProceso() {
        return fechaFinalProceso;
    }

    public void setFechaFinalProceso(Date fechaFinalProceso) {
        this.fechaFinalProceso = fechaFinalProceso;
    }

    public List<String> getNumerosComparendo() {
        return numerosComparendo;
    }

    public void setNumerosComparendo(List<String> numerosComparendo) {
        this.numerosComparendo = numerosComparendo;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public Date getFechaProcesamiento() {
        return fechaProcesamiento;
    }

    public void setFechaProcesamiento(Date fechaProcesamiento) {
        this.fechaProcesamiento = fechaProcesamiento;
    }

    public String getCorreoEnviado() {
        return correoEnviado;
    }

    public void setCorreoEnviado(String correoEnviado) {
        this.correoEnviado = correoEnviado;
    }

}
