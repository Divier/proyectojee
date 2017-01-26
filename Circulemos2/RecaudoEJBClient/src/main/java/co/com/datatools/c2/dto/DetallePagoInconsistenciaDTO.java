package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 27 10:31:23 COT 2016
 */
public class DetallePagoInconsistenciaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private String id;
    private Integer idConceptoCartera;
    private Integer idTipoRecaudo;
    private Integer numeroCuota;
    private String numeroObligacion;
    private BigDecimal valorObligacion;
    private PagoInconsistenciaDTO pagoInconsistencia;
    private List<ErrorInconsistenciaPagoDTO> errorInconsistenciaPagos;

    // --- Constructor
    public DetallePagoInconsistenciaDTO() {
    }

    public DetallePagoInconsistenciaDTO(String id) {
        this.id = id;

    }

    // --- Getters-Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdConceptoCartera() {
        return this.idConceptoCartera;
    }

    public void setIdConceptoCartera(Integer idConceptoCartera) {
        this.idConceptoCartera = idConceptoCartera;
    }

    public Integer getIdTipoRecaudo() {
        return this.idTipoRecaudo;
    }

    public void setIdTipoRecaudo(Integer idTipoRecaudo) {
        this.idTipoRecaudo = idTipoRecaudo;
    }

    public Integer getNumeroCuota() {
        return this.numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNumeroObligacion() {
        return this.numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorObligacion() {
        return this.valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public PagoInconsistenciaDTO getPagoInconsistencia() {
        return this.pagoInconsistencia;
    }

    public void setPagoInconsistencia(PagoInconsistenciaDTO pagoInconsistencia) {
        this.pagoInconsistencia = pagoInconsistencia;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ErrorInconsistenciaPagoDTO> getErrorInconsistenciaPagos() {
        if (this.errorInconsistenciaPagos == null) {
            this.errorInconsistenciaPagos = new java.util.ArrayList<>(1);
        }
        return this.errorInconsistenciaPagos;
    }

    public void setErrorInconsistenciaPagos(List<ErrorInconsistenciaPagoDTO> errorInconsistenciaPagos) {
        this.errorInconsistenciaPagos = errorInconsistenciaPagos;
    }

}
