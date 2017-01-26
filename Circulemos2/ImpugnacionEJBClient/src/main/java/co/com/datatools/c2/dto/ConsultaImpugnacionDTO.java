package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores para consultar los procesos de impugnacion.
 * 
 * @author divier.casas
 * 
 */
public class ConsultaImpugnacionDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private Integer idEstadoComparendo;
    private String numeroComparendo;
    private Integer tipoDocumentoInfractor;
    private String numeroDocumentoInfractor;
    private Integer idEstadoProceso;
    private String numeroProceso;
    private Date fechaInicioAperturaProceso;
    private Date fechaFinAperturaProceso;
    private String numeroCitacion;
    private Integer anioProceso;

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getIdEstadoComparendo() {
        return idEstadoComparendo;
    }

    public void setIdEstadoComparendo(Integer idEstadoComparendo) {
        this.idEstadoComparendo = idEstadoComparendo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Integer getTipoDocumentoInfractor() {
        return tipoDocumentoInfractor;
    }

    public void setTipoDocumentoInfractor(Integer tipoDocumentoInfractor) {
        this.tipoDocumentoInfractor = tipoDocumentoInfractor;
    }

    public String getNumeroDocumentoInfractor() {
        return numeroDocumentoInfractor;
    }

    public void setNumeroDocumentoInfractor(String numeroDocumentoInfractor) {
        this.numeroDocumentoInfractor = numeroDocumentoInfractor;
    }

    public Integer getIdEstadoProceso() {
        return idEstadoProceso;
    }

    public void setIdEstadoProceso(Integer idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Date getFechaInicioAperturaProceso() {
        return fechaInicioAperturaProceso;
    }

    public void setFechaInicioAperturaProceso(Date fechaInicioAperturaProceso) {
        this.fechaInicioAperturaProceso = fechaInicioAperturaProceso;
    }

    public Date getFechaFinAperturaProceso() {
        return fechaFinAperturaProceso;
    }

    public void setFechaFinAperturaProceso(Date fechaFinAperturaProceso) {
        this.fechaFinAperturaProceso = fechaFinAperturaProceso;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public Integer getAnioProceso() {
        return anioProceso;
    }

    public void setAnioProceso(Integer anioProceso) {
        this.anioProceso = anioProceso;
    }

}