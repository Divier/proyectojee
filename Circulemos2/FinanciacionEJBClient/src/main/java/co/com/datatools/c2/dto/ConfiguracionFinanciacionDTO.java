package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 09:39:33 COT 2016
 */
public class ConfiguracionFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private Date fechaInicial;
    private Date fechaFinal;
    private List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaInici;
    private OrganismoTransitoDTO organismoTransito;
    private List<ValorCondicionFinanciacionDTO> lstValorCondicionFinanciacion;
    private List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuota;
    private List<EstadoCondicionFinanciacionDTO> lstEstadoCondicionFinanciacion;

    // --- Constructor
    public ConfiguracionFinanciacionDTO() {
    }

    public ConfiguracionFinanciacionDTO(Integer id) {
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

    public Date getFechaInicial() {
        return this.fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return this.fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
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

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<EstadoCondicionFinanciacionDTO> getLstEstadoCondicionFinanciacion() {
        if (this.lstEstadoCondicionFinanciacion == null) {
            this.lstEstadoCondicionFinanciacion = new java.util.ArrayList<>(1);
        }
        return this.lstEstadoCondicionFinanciacion;
    }

    public void setLstEstadoCondicionFinanciacion(List<EstadoCondicionFinanciacionDTO> lstEstadoCondicionFinanciacion) {
        this.lstEstadoCondicionFinanciacion = lstEstadoCondicionFinanciacion;
    }

}
