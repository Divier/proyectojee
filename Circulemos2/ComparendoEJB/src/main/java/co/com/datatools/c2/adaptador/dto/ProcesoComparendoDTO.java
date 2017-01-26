package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;

/**
 * Contiene los filtros básicos para consultar procesos asociados a comparendos desde el módulo de comparendos, mediante la interfaz
 * IProcesoComparendo.
 * 
 * @author julio.pinzon
 * 
 */
public class ProcesoComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int codigoOrganismo;
    private EnumEstadoProceso estadoProceso;
    private String numeroComparendo;
    private EnumTipoProceso tipoProceso;

    // Contiene la informacion de la trazabilidad para ese proceso de un comparendo en especifico
    private List<TrazabilidadProcesoDTO> trazabilidadProcesoDTOs;

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public EnumEstadoProceso getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(EnumEstadoProceso estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public EnumTipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(EnumTipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public List<TrazabilidadProcesoDTO> getTrazabilidadProcesoDTOs() {
        return trazabilidadProcesoDTOs;
    }

    public void setTrazabilidadProcesoDTOs(List<TrazabilidadProcesoDTO> trazabilidadProcesoDTOs) {
        this.trazabilidadProcesoDTOs = trazabilidadProcesoDTOs;
    }

}
