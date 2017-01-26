package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.common.ConsultaObligacionesDTO;

/**
 * @author luis.forero(2016-06-23)
 */
public class SimulacionFinanciacionDTO extends FinanciacionDTO {

    private static final long serialVersionUID = 1L;

    private List<ConsultaObligacionesDTO> obligacionesInfractor;

    private DetalleCantidadCuotaDTO cantidadCuotasAplica;

    private DetallePorcentajeCuotaIniciDTO porcentajeCuotaInicialAplica;

    /**
     * Valor del porcentaje de interes aplicado
     */
    private BigDecimal interesFinanciacion;

    /**
     * Valor de primera cuota
     */
    private BigDecimal valorPrimeraCuota;

    public SimulacionFinanciacionDTO() {
    }

    /**
     * Obligaciones correspondientes del infractor consultado
     * 
     * NOTA: Tiene safe list null, es decir siempre retorna una instancia de las obligaciones del infractor.
     * 
     * @return Retorna las obligaciones correspondientes del infractor consultado
     */
    public List<ConsultaObligacionesDTO> getObligacionesInfractor() {
        if (obligacionesInfractor == null) {
            obligacionesInfractor = new ArrayList<ConsultaObligacionesDTO>(1);
        }
        return obligacionesInfractor;
    }

    public void setObligacionesInfractor(List<ConsultaObligacionesDTO> obligacionesInfractor) {
        this.obligacionesInfractor = obligacionesInfractor;
    }

    public BigDecimal getInteresFinanciacion() {
        return interesFinanciacion;
    }

    public void setInteresFinanciacion(BigDecimal interesFinanciacion) {
        this.interesFinanciacion = interesFinanciacion;
    }

    public DetalleCantidadCuotaDTO getCantidadCuotasAplica() {
        return cantidadCuotasAplica;
    }

    public void setCantidadCuotasAplica(DetalleCantidadCuotaDTO cantidadCuotasAplica) {
        this.cantidadCuotasAplica = cantidadCuotasAplica;
    }

    public DetallePorcentajeCuotaIniciDTO getPorcentajeCuotaInicialAplica() {
        return porcentajeCuotaInicialAplica;
    }

    public void setPorcentajeCuotaInicialAplica(DetallePorcentajeCuotaIniciDTO porcentajeCuotaInicialAplica) {
        this.porcentajeCuotaInicialAplica = porcentajeCuotaInicialAplica;
    }

    public BigDecimal getValorPrimeraCuota() {
        return valorPrimeraCuota;
    }

    public void setValorPrimeraCuota(BigDecimal valorPrimeraCuota) {
        this.valorPrimeraCuota = valorPrimeraCuota;
    }

}
