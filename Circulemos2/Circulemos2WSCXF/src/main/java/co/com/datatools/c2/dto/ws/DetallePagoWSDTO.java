package co.com.datatools.c2.dto.ws;

import java.math.BigDecimal;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Contiene la respuesta detalla de la invocacion de un servicio para clientes externos de la aplicacion.
 * 
 * @author julio.pinzon (2016-04-21)
 * 
 */
public class DetallePagoWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    /**
     * Indica el Número de comparendo, acuerdo de pago o coactivo que se recaudó.
     */
    private String obligacionPagada;

    /**
     * Valor obligacion
     */
    private BigDecimal valorObligacion;

    /**
     * Catálogo del sistema.
     * 
     * Se debe enviar los siguientes códigos:
     * 
     * 1. Recaudo de Comparendo 2. Recaudo de Acuerdos de Pago - Financiaciones 3. Recaudo de Coactivo.
     */
    private String tipoRecaudo;

    /**
     * Indica el número de la cuota al cual pertenece el recaudo que se está reportando.
     */
    private Integer numeroCuota;

    public String getObligacionPagada() {
        return obligacionPagada;
    }

    public void setObligacionPagada(String obligacionPagada) {
        this.obligacionPagada = obligacionPagada;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public String getTipoRecaudo() {
        return tipoRecaudo;
    }

    public void setTipoRecaudo(String tipoRecaudo) {
        this.tipoRecaudo = tipoRecaudo;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

}
