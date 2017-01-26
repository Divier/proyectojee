package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class BienDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long id;
    private PersonaDTO personaDTO;
    private TipoEntidadDTO tipoEntidadDTO;
    private TipoBienDTO tipoBienDTO;
    private EntidadBienDTO entidadBienDTO;
    private String numeroReferencia;
    private BigDecimal montoRetenido;
    private BigDecimal valorEmbargado;
    private Date fechaModifiacion;
    private Date fechaRegistro;

    public BienDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }

    public TipoEntidadDTO getTipoEntidadDTO() {
        return tipoEntidadDTO;
    }

    public void setTipoEntidadDTO(TipoEntidadDTO tipoEntidadDTO) {
        this.tipoEntidadDTO = tipoEntidadDTO;
    }

    public TipoBienDTO getTipoBienDTO() {
        return tipoBienDTO;
    }

    public void setTipoBienDTO(TipoBienDTO tipoBienDTO) {
        this.tipoBienDTO = tipoBienDTO;
    }

    public EntidadBienDTO getEntidadBienDTO() {
        return entidadBienDTO;
    }

    public void setEntidadBienDTO(EntidadBienDTO entidadBienDTO) {
        this.entidadBienDTO = entidadBienDTO;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public BigDecimal getMontoRetenido() {
        return montoRetenido;
    }

    public void setMontoRetenido(BigDecimal montoRetenido) {
        this.montoRetenido = montoRetenido;
    }

    public BigDecimal getValorEmbargado() {
        return valorEmbargado;
    }

    public void setValorEmbargado(BigDecimal valorEmbargado) {
        this.valorEmbargado = valorEmbargado;
    }

    public Date getFechaModifiacion() {
        return fechaModifiacion;
    }

    public void setFechaModifiacion(Date fechaModifiacion) {
        this.fechaModifiacion = fechaModifiacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}