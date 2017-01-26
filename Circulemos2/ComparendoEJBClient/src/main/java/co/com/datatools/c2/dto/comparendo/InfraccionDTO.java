package co.com.datatools.c2.dto.comparendo;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 28 08:24:51 COT 2016
 */
public class InfraccionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String numeral;
    private String codigo;
    private TipoInfraccionDTO tipoInfraccion;
    private TipoComparendoDTO tipoComparendo;
    private List<ConfiguracionInfraccionDTO> configuracionInfraccionList;
    private List<TarifaInfraccionDTO> tarifaInfraccionList;

    // --- Constructor
    public InfraccionDTO() {
    }

    public InfraccionDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeral() {
        return this.numeral;
    }

    public void setNumeral(String numeral) {
        this.numeral = numeral;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoInfraccionDTO getTipoInfraccion() {
        return this.tipoInfraccion;
    }

    public void setTipoInfraccion(TipoInfraccionDTO tipoInfraccion) {
        this.tipoInfraccion = tipoInfraccion;
    }

    public TipoComparendoDTO getTipoComparendo() {
        return this.tipoComparendo;
    }

    public void setTipoComparendo(TipoComparendoDTO tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ConfiguracionInfraccionDTO> getConfiguracionInfraccionList() {
        if (this.configuracionInfraccionList == null) {
            this.configuracionInfraccionList = new java.util.ArrayList<>(1);
        }
        return this.configuracionInfraccionList;
    }

    public void setConfiguracionInfraccionList(List<ConfiguracionInfraccionDTO> configuracionInfraccionList) {
        this.configuracionInfraccionList = configuracionInfraccionList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<TarifaInfraccionDTO> getTarifaInfraccionList() {
        if (this.tarifaInfraccionList == null) {
            this.tarifaInfraccionList = new java.util.ArrayList<>(1);
        }
        return this.tarifaInfraccionList;
    }

    public void setTarifaInfraccionList(List<TarifaInfraccionDTO> tarifaInfraccionList) {
        this.tarifaInfraccionList = tarifaInfraccionList;
    }
}