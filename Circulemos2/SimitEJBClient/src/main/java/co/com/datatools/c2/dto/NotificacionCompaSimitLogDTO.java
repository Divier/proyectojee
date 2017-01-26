package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

/**
 * CU_CIR20_DAT_SIM_008 Generar por demanda archivo para envio a SIMIT - DTO Log
 * 
 * @author rodrigo.cruz
 */
public class NotificacionCompaSimitLogDTO implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Date fechaHoraGeneracion;
    private Integer codigoOrganismo;
    private Integer numeroRegistros;
    private String resultadoEnvio;
    private String tipoDocumentoEnvio;

    public Date getFechaHoraGeneracion() {
        return fechaHoraGeneracion;
    }

    public void setFechaHoraGeneracion(Date fechaHoraGeneracion) {
        this.fechaHoraGeneracion = fechaHoraGeneracion;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(Integer numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public String getResultadoEnvio() {
        return resultadoEnvio;
    }

    public void setResultadoEnvio(String resultadoEnvio) {
        this.resultadoEnvio = resultadoEnvio;
    }

    public String getTipoDocumentoEnvio() {
        return tipoDocumentoEnvio;
    }

    public void setTipoDocumentoEnvio(String tipoDocumentoEnvio) {
        this.tipoDocumentoEnvio = tipoDocumentoEnvio;
    }

}
