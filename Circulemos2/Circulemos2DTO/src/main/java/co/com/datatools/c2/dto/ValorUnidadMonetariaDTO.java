package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class ValorUnidadMonetariaDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer idValorUnidadMonetaria;
    private Date fechaFinalVigencia;
    private Date fechaInicioVigencia;
    private BigDecimal valorEquivalenciaUnidaMonet;
    private TipoMonedaDTO tipoMonedaDTO;
    private UnidadMonetariaDTO unidadMonetariaDTO;

    // Constructors Declaration

    public ValorUnidadMonetariaDTO() {

    }

    // Start Methods Declaration

    public Integer getIdValorUnidadMonetaria() {
        return idValorUnidadMonetaria;
    }

    public void setIdValorUnidadMonetaria(Integer idValorUnidadMonetaria) {
        this.idValorUnidadMonetaria = idValorUnidadMonetaria;
    }

    public Date getFechaFinalVigencia() {
        return fechaFinalVigencia;
    }

    public void setFechaFinalVigencia(Date fechaFinalVigencia) {
        this.fechaFinalVigencia = fechaFinalVigencia;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public BigDecimal getValorEquivalenciaUnidaMonet() {
        return valorEquivalenciaUnidaMonet;
    }

    public void setValorEquivalenciaUnidaMonet(BigDecimal valorEquivalenciaUnidaMonet) {
        this.valorEquivalenciaUnidaMonet = valorEquivalenciaUnidaMonet;
    }

    public TipoMonedaDTO getTipoMonedaDTO() {
        return tipoMonedaDTO;
    }

    public void setTipoMonedaDTO(TipoMonedaDTO tipoMonedaDTO) {
        this.tipoMonedaDTO = tipoMonedaDTO;
    }

    public UnidadMonetariaDTO getUnidadMonetariaDTO() {
        return unidadMonetariaDTO;
    }

    public void setUnidadMonetariaDTO(UnidadMonetariaDTO unidadMonetariaDTO) {
        this.unidadMonetariaDTO = unidadMonetariaDTO;
    }

    // Finish the class
}