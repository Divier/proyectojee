package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 10 14:01:59 COT 2016
 */
public class NovedadSacDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idNovedadSac;
    private Date fechaNovedad;
    private Date fechaRegistro;
    private Integer idEstadoTransaccionSac;
    private Integer idTipoNovedadSac;
    private String soporteNovedad;
    private BigDecimal valorNovedad;
    private ObligacionSacDTO obligacionSac;

    // --- Constructor
    public NovedadSacDTO() {
    }

    public NovedadSacDTO(Long idNovedadSac) {
        this.idNovedadSac = idNovedadSac;

    }

    // --- Getters-Setters
    public Long getIdNovedadSac() {
        return this.idNovedadSac;
    }

    public void setIdNovedadSac(Long idNovedadSac) {
        this.idNovedadSac = idNovedadSac;
    }

    public Date getFechaNovedad() {
        return this.fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdEstadoTransaccionSac() {
        return this.idEstadoTransaccionSac;
    }

    public void setIdEstadoTransaccionSac(Integer idEstadoTransaccionSac) {
        this.idEstadoTransaccionSac = idEstadoTransaccionSac;
    }

    public Integer getIdTipoNovedadSac() {
        return this.idTipoNovedadSac;
    }

    public void setIdTipoNovedadSac(Integer idTipoNovedadSac) {
        this.idTipoNovedadSac = idTipoNovedadSac;
    }

    public String getSoporteNovedad() {
        return this.soporteNovedad;
    }

    public void setSoporteNovedad(String soporteNovedad) {
        this.soporteNovedad = soporteNovedad;
    }

    public BigDecimal getValorNovedad() {
        return this.valorNovedad;
    }

    public void setValorNovedad(BigDecimal valorNovedad) {
        this.valorNovedad = valorNovedad;
    }

    public ObligacionSacDTO getObligacionSac() {
        return this.obligacionSac;
    }

    public void setObligacionSac(ObligacionSacDTO obligacionSac) {
        this.obligacionSac = obligacionSac;
    }

}
