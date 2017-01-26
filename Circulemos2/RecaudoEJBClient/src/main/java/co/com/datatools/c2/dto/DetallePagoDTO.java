package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * No autogenerar, toString y equals sobreescrito
 * 
 * @author Generated
 * @version 3.0 - Thu Apr 28 15:47:36 COT 2016
 */
public class DetallePagoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer numeroCuota;
    private String numeroObligacion;
    private BigDecimal valorObligacion;
    private Integer idConceptoCartera;
    private Integer idTipoRecaudo;
    private Integer numeroIntentos;
    private PagoDTO pago;
    private EstadoPagoDTO estadoPago;
    private List<DetallePagoConciliacionErrorDTO> detallePagoConciliacionErrores;
    private Date fechaGeneracionReporte;
    private Date fechaRegistro;
    private Date fechaAplicacionPago;

    // --- Constructor
    public DetallePagoDTO() {
    }

    public DetallePagoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(Integer numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public PagoDTO getPago() {
        return this.pago;
    }

    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }

    public EstadoPagoDTO getEstadoPago() {
        return this.estadoPago;
    }

    public void setEstadoPago(EstadoPagoDTO estadoPago) {
        this.estadoPago = estadoPago;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetallePagoConciliacionErrorDTO> getDetallePagoConciliacionErrores() {
        if (this.detallePagoConciliacionErrores == null) {
            this.detallePagoConciliacionErrores = new java.util.ArrayList<>(1);
        }
        return this.detallePagoConciliacionErrores;
    }

    public void setDetallePagoConciliacionErrores(
            List<DetallePagoConciliacionErrorDTO> detallePagoConciliacionErrores) {
        this.detallePagoConciliacionErrores = detallePagoConciliacionErrores;
    }

    public Date getFechaGeneracionReporte() {
        return this.fechaGeneracionReporte;
    }

    public void setFechaGeneracionReporte(Date fechaGeneracionReporte) {
        this.fechaGeneracionReporte = fechaGeneracionReporte;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaAplicacionPago() {
        return fechaAplicacionPago;
    }

    public void setFechaAplicacionPago(Date fechaAplicacionPago) {
        this.fechaAplicacionPago = fechaAplicacionPago;
    }

    @Override
    public String toString() {
        return "DetallePagoDTO [id=" + id + ", numeroCuota=" + numeroCuota + ", numeroObligacion=" + numeroObligacion
                + ", valorObligacion=" + valorObligacion + ", idConceptoCartera=" + idConceptoCartera
                + ", idTipoRecaudo=" + idTipoRecaudo + ", numeroIntentos=" + numeroIntentos + ", estadoPago="
                + estadoPago + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (obj instanceof DetallePagoDTO) {
            DetallePagoDTO unDetallePagoDTO = (DetallePagoDTO) obj;

            if (!(this.getIdTipoRecaudo().equals(unDetallePagoDTO.getIdTipoRecaudo())
                    && this.getNumeroObligacion().equals(unDetallePagoDTO.getNumeroObligacion())
                    && this.getNumeroCuota().equals(unDetallePagoDTO.getNumeroCuota())))
                return false;

            if (!(this.getPago() != null && this.getPago().getId() != null))
                return false;

            if (!(unDetallePagoDTO.getPago() != null && unDetallePagoDTO.getPago().getId() != null))
                return false;

            if (!this.getPago().getId().equals(unDetallePagoDTO.getPago().getId()))
                return false;
        } else
            return false;

        return true;
    }

}
