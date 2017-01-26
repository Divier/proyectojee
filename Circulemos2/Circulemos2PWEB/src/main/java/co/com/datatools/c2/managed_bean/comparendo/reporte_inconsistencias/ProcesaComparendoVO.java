package co.com.datatools.c2.managed_bean.comparendo.reporte_inconsistencias;

import java.io.Serializable;

import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.UnificacionInconsistenciasComparendoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;

public class ProcesaComparendoVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProcesaComparendoDTO procesaComparendoDTO;
    private ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO;
    private TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO;
    private String nombreOrigenComparendo;
    private UnificacionInconsistenciasComparendoDTO unificacionInconsistenciasComparendoDTO;

    public ProcesaComparendoDTO getProcesaComparendoDTO() {
        return procesaComparendoDTO;
    }

    public void setProcesaComparendoDTO(ProcesaComparendoDTO procesaComparendoDTO) {
        this.procesaComparendoDTO = procesaComparendoDTO;
    }

    public ProcesaComparendoPersonaDTO getProcesaComparendoPersonaDTO() {
        return procesaComparendoPersonaDTO;
    }

    public void setProcesaComparendoPersonaDTO(ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO) {
        this.procesaComparendoPersonaDTO = procesaComparendoPersonaDTO;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacionPersonaDTO() {
        return tipoIdentificacionPersonaDTO;
    }

    public void setTipoIdentificacionPersonaDTO(TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO) {
        this.tipoIdentificacionPersonaDTO = tipoIdentificacionPersonaDTO;
    }

    public String getNombreOrigenComparendo() {
        return nombreOrigenComparendo;
    }

    public void setNombreOrigenComparendo(String nombreOrigenComparendo) {
        this.nombreOrigenComparendo = nombreOrigenComparendo;
    }

    public UnificacionInconsistenciasComparendoDTO getUnificacionInconsistenciasComparendoDTO() {
        return unificacionInconsistenciasComparendoDTO;
    }

    public void setUnificacionInconsistenciasComparendoDTO(
            UnificacionInconsistenciasComparendoDTO unificacionInconsistenciasComparendoDTO) {
        this.unificacionInconsistenciasComparendoDTO = unificacionInconsistenciasComparendoDTO;
    }

}
