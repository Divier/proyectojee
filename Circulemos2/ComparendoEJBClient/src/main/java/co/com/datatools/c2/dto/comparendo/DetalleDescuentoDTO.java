package co.com.datatools.c2.dto.comparendo;

import java.math.BigDecimal;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 26 11:48:47 COT 2016
 */
public class DetalleDescuentoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private short diasFin;
    private short diasInicio;
    private int idConcepto;
    private BigDecimal porcentajeDescuento;
    private ConfiguracionDescuentoDTO configuracionDescuento;

    // --- Constructor
    public DetalleDescuentoDTO() {
    }

    public DetalleDescuentoDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getDiasFin() {
        return this.diasFin;
    }

    public void setDiasFin(short diasFin) {
        this.diasFin = diasFin;
    }

    public short getDiasInicio() {
        return this.diasInicio;
    }

    public void setDiasInicio(short diasInicio) {
        this.diasInicio = diasInicio;
    }

    public int getIdConcepto() {
        return this.idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public BigDecimal getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public ConfiguracionDescuentoDTO getConfiguracionDescuento() {
        return this.configuracionDescuento;
    }

    public void setConfiguracionDescuento(ConfiguracionDescuentoDTO configuracionDescuento) {
        this.configuracionDescuento = configuracionDescuento;
    }

}
