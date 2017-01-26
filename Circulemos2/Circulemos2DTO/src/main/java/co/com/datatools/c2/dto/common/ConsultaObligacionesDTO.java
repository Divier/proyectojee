package co.com.datatools.c2.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.dto.personas.PersonaDTO;

/**
 * @author javier.fajardo
 * 
 */
public class ConsultaObligacionesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer idTipoObligacion;
    private String tipoObligacion;
    private String numeroObligacion;
    private Date fechaObligacion;
    private Date fechaNotificacion;
    private String codigoInfraccion;
    private Boolean estaEnCoactivo;
    private String placa;
    private BigDecimal saldoObligacion;
    private BigDecimal valorRecargo;
    private BigDecimal valorCostaProcesal;
    private Long idCartera;
    private String medioImposicion;
    // Consulta
    private PersonaDTO persona;

    // --- Constructor
    public ConsultaObligacionesDTO() {
    }

    public String getTipoObligacion() {
        return tipoObligacion;
    }

    public void setTipoObligacion(String tipoObligacion) {
        this.tipoObligacion = tipoObligacion;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Boolean getEstaEnCoactivo() {
        return estaEnCoactivo;
    }

    public void setEstaEnCoactivo(Boolean estaEnCoactivo) {
        this.estaEnCoactivo = estaEnCoactivo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigDecimal getValorRecargo() {
        return valorRecargo;
    }

    public void setValorRecargo(BigDecimal valorRecargo) {
        this.valorRecargo = valorRecargo;
    }

    public BigDecimal getValorCostaProcesal() {
        return valorCostaProcesal;
    }

    public void setValorCostaProcesal(BigDecimal valorCostaProcesal) {
        this.valorCostaProcesal = valorCostaProcesal;
    }

    public Integer getIdTipoObligacion() {
        return idTipoObligacion;
    }

    public void setIdTipoObligacion(Integer idTipoObligacion) {
        this.idTipoObligacion = idTipoObligacion;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public BigDecimal getSaldoObligacion() {
        return saldoObligacion;
    }

    public void setSaldoObligacion(BigDecimal saldoObligacion) {
        this.saldoObligacion = saldoObligacion;
    }

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public String getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(String medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

}
