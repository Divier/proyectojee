package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author divier.casas
 */
public class ConsultaComparendoDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    private Boolean esPolca;
    private Date fechaFinImposicion;
    private Date fechaInicioImposicion;
    private Integer idTipoComparendo;
    private String numeroComparendo;
    private String numeroDocumentoAgente;
    private String numeroDocumentoInfractor;
    private String numeroDocumentoPropietario;
    private String placaVehiculo;
    private Integer tipoDocumentoAgente;
    private Integer tipoDocumentoInfractor;
    private Integer tipoDocumentoPropietario;
    private String numeroCitacion;
    private Integer anioCitacion;

    public ConsultaComparendoDTO() {
        super();
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Boolean isEsPolca() {
        return esPolca;
    }

    public void setEsPolca(Boolean esPolca) {
        this.esPolca = esPolca;
    }

    public Date getFechaFinImposicion() {
        return fechaFinImposicion;
    }

    public void setFechaFinImposicion(Date fechaFinImposicion) {
        this.fechaFinImposicion = fechaFinImposicion;
    }

    public Date getFechaInicioImposicion() {
        return fechaInicioImposicion;
    }

    public void setFechaInicioImposicion(Date fechaInicioImposicion) {
        this.fechaInicioImposicion = fechaInicioImposicion;
    }

    public Integer getIdTipoComparendo() {
        return idTipoComparendo;
    }

    public void setIdTipoComparendo(Integer idTipoComparendo) {
        this.idTipoComparendo = idTipoComparendo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getNumeroDocumentoAgente() {
        return numeroDocumentoAgente;
    }

    public void setNumeroDocumentoAgente(String numeroDocumentoAgente) {
        this.numeroDocumentoAgente = numeroDocumentoAgente;
    }

    public String getNumeroDocumentoInfractor() {
        return numeroDocumentoInfractor;
    }

    public void setNumeroDocumentoInfractor(String numeroDocumentoInfractor) {
        this.numeroDocumentoInfractor = numeroDocumentoInfractor;
    }

    public String getNumeroDocumentoPropietario() {
        return numeroDocumentoPropietario;
    }

    public void setNumeroDocumentoPropietario(String numeroDocumentoPropietario) {
        this.numeroDocumentoPropietario = numeroDocumentoPropietario;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Integer getTipoDocumentoAgente() {
        return tipoDocumentoAgente;
    }

    public void setTipoDocumentoAgente(Integer tipoDocumentoAgente) {
        this.tipoDocumentoAgente = tipoDocumentoAgente;
    }

    public Integer getTipoDocumentoInfractor() {
        return tipoDocumentoInfractor;
    }

    public void setTipoDocumentoInfractor(Integer tipoDocumentoInfractor) {
        this.tipoDocumentoInfractor = tipoDocumentoInfractor;
    }

    public Integer getTipoDocumentoPropietario() {
        return tipoDocumentoPropietario;
    }

    public void setTipoDocumentoPropietario(Integer tipoDocumentoPropietario) {
        this.tipoDocumentoPropietario = tipoDocumentoPropietario;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public Integer getAnioCitacion() {
        return anioCitacion;
    }

    public void setAnioCitacion(Integer anioCitacion) {
        this.anioCitacion = anioCitacion;
    }
}