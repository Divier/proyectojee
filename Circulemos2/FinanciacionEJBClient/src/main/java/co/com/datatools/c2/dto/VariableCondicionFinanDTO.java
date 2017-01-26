package co.com.datatools.c2.dto;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 09:42:37 COT 2016
 */
public class VariableCondicionFinanDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private Integer orden;
    private String fuenteData;
    private List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaInici;
    private CondicionFinanciacionDTO condicionFinanciacion;
    private TipoVariableFinanciacionDTO tipoVariableFinanciacion;
    private List<ValorCondicionFinanciacionDTO> lstValorCondicionFinanciacion;
    private List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuota;

    private List<CatalogoDTO> listCatalogoDTO;

    // --- Constructor
    public VariableCondicionFinanDTO() {
    }

    public VariableCondicionFinanDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return this.orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getFuenteData() {
        return this.fuenteData;
    }

    public void setFuenteData(String fuenteData) {
        this.fuenteData = fuenteData;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetallePorcentajeCuotaIniciDTO> getLstDetallePorcentajeCuotaInici() {
        if (this.lstDetallePorcentajeCuotaInici == null) {
            this.lstDetallePorcentajeCuotaInici = new java.util.ArrayList<>(1);
        }
        return this.lstDetallePorcentajeCuotaInici;
    }

    public void setLstDetallePorcentajeCuotaInici(List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaInici) {
        this.lstDetallePorcentajeCuotaInici = lstDetallePorcentajeCuotaInici;
    }

    public CondicionFinanciacionDTO getCondicionFinanciacion() {
        return this.condicionFinanciacion;
    }

    public void setCondicionFinanciacion(CondicionFinanciacionDTO condicionFinanciacion) {
        this.condicionFinanciacion = condicionFinanciacion;
    }

    public TipoVariableFinanciacionDTO getTipoVariableFinanciacion() {
        return this.tipoVariableFinanciacion;
    }

    public void setTipoVariableFinanciacion(TipoVariableFinanciacionDTO tipoVariableFinanciacion) {
        this.tipoVariableFinanciacion = tipoVariableFinanciacion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ValorCondicionFinanciacionDTO> getLstValorCondicionFinanciacion() {
        if (this.lstValorCondicionFinanciacion == null) {
            this.lstValorCondicionFinanciacion = new java.util.ArrayList<>(1);
        }
        return this.lstValorCondicionFinanciacion;
    }

    public void setLstValorCondicionFinanciacion(List<ValorCondicionFinanciacionDTO> lstValorCondicionFinanciacion) {
        this.lstValorCondicionFinanciacion = lstValorCondicionFinanciacion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleCantidadCuotaDTO> getLstDetalleCantidadCuota() {
        if (this.lstDetalleCantidadCuota == null) {
            this.lstDetalleCantidadCuota = new java.util.ArrayList<>(1);
        }
        return this.lstDetalleCantidadCuota;
    }

    public void setLstDetalleCantidadCuota(List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuota) {
        this.lstDetalleCantidadCuota = lstDetalleCantidadCuota;
    }

    public List<CatalogoDTO> getListCatalogoDTO() {
        if (this.listCatalogoDTO == null) {
            this.listCatalogoDTO = new ArrayList<CatalogoDTO>(1);
        }
        return this.listCatalogoDTO;
    }

    public void setListCatalogoDTO(List<CatalogoDTO> listCatalogoDTO) {
        this.listCatalogoDTO = listCatalogoDTO;
    }

}
