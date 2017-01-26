package co.com.datatools.c2.managed_bean.financiacion.administracion.proceso_financiacion;

import java.io.Serializable;

import co.com.datatools.c2.dto.ConsultaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.ConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.DejarFirmeDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;

/**
 * Se encarga de manejar los objetos para el detalle de una financiacion
 * 
 * @author giovanni.velandia
 * 
 */
public class DetalleFinanciacionFL implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "detalleFinanciacionFL";

    private ConsultaFinanciacionDTO consultaFinanciacionDTO;

    private boolean obligaciones;
    private boolean seguimiento;
    private boolean imprimirReciboPago;
    private boolean imprimirCuadroAmortizacion;
    private boolean dejarFirme;

    private boolean btnDejarFirme;

    private ConsultaDetalleFinanciacionDTO consultaDetalleFinanciacionSelDTO;
    private TrazabilidadProcesoDTO trazabilidadProcesoSelDTO;

    // Dejar en firme
    private DejarFirmeDTO dejarFirmeDTO;

    public DetalleFinanciacionFL() {
        dejarFirmeDTO = new DejarFirmeDTO();
    }

    public boolean isImprimirReciboPago() {
        return imprimirReciboPago;
    }

    public void setImprimirReciboPago(boolean imprimirReciboPago) {
        this.imprimirReciboPago = imprimirReciboPago;
    }

    public boolean isImprimirCuadroAmortizacion() {
        return imprimirCuadroAmortizacion;
    }

    public void setImprimirCuadroAmortizacion(boolean imprimirCuadroAmortizacion) {
        this.imprimirCuadroAmortizacion = imprimirCuadroAmortizacion;
    }

    public boolean isObligaciones() {
        return obligaciones;
    }

    public void setObligaciones(boolean obligaciones) {
        this.obligaciones = obligaciones;
    }

    public boolean isSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(boolean seguimiento) {
        this.seguimiento = seguimiento;
    }

    public ConsultaFinanciacionDTO getConsultaFinanciacionDTO() {
        return consultaFinanciacionDTO;
    }

    public void setConsultaFinanciacionDTO(ConsultaFinanciacionDTO consultaFinanciacionDTO) {
        this.consultaFinanciacionDTO = consultaFinanciacionDTO;
    }

    public boolean isDejarFirme() {
        return dejarFirme;
    }

    public void setDejarFirme(boolean dejarFirme) {
        this.dejarFirme = dejarFirme;
    }

    public boolean isBtnDejarFirme() {
        return btnDejarFirme;
    }

    public void setBtnDejarFirme(boolean btnDejarFirme) {
        this.btnDejarFirme = btnDejarFirme;
    }

    public ConsultaDetalleFinanciacionDTO getConsultaDetalleFinanciacionSelDTO() {
        return consultaDetalleFinanciacionSelDTO;
    }

    public void setConsultaDetalleFinanciacionSelDTO(ConsultaDetalleFinanciacionDTO consultaDetalleFinanciacionSelDTO) {
        this.consultaDetalleFinanciacionSelDTO = consultaDetalleFinanciacionSelDTO;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProcesoSelDTO() {
        return trazabilidadProcesoSelDTO;
    }

    public void setTrazabilidadProcesoSelDTO(TrazabilidadProcesoDTO trazabilidadProcesoSelDTO) {
        this.trazabilidadProcesoSelDTO = trazabilidadProcesoSelDTO;
    }

    public DejarFirmeDTO getDejarFirmeDTO() {
        return dejarFirmeDTO;
    }

    public void setDejarFirmeDTO(DejarFirmeDTO dejarFirmeDTO) {
        this.dejarFirmeDTO = dejarFirmeDTO;
    }
}
