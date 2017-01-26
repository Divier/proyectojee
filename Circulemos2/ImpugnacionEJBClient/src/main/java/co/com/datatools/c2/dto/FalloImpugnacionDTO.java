package co.com.datatools.c2.dto;

import java.math.BigDecimal;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * No regenerar, se metio atributo util para funcionalidad de negocio
 * 
 * @author Generated
 * @version 3.0 - Mon Jun 13 15:02:57 COT 2016
 */
public class FalloImpugnacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private TipoFalloDTO tipoFallo;
    private String motivo;
    private Double porcentaje;
    private String codigoPlantilla;
    private BigDecimal valorDescuento;
    private BigDecimal valorObligacion;
    private Double puntos;
    // Atributo puesto para realizar otro funcion en el fallo
    private Long cicomparendo;

    // --- Constructor
    public FalloImpugnacionDTO() {
    }

    public FalloImpugnacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProceso() {
        return this.trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProcesoDTO trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public TipoFalloDTO getTipoFallo() {
        return this.tipoFallo;
    }

    public void setTipoFallo(TipoFalloDTO tipoFallo) {
        this.tipoFallo = tipoFallo;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getPorcentaje() {
        return this.porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    public BigDecimal getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public Double getPuntos() {
        return puntos;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

}
