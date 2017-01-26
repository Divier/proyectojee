package co.com.datatools.c2.dto;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * No autogenerar
 * 
 * @author Generated
 * @version 3.0 - Mon Aug 01 11:57:12 COT 2016
 */
public class VariableCondicionCoacDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private Integer orden;
    private String fuenteData;
    private CondicionCoactivoDTO condicionCoactivo;
    private TipoVariableCoactivoDTO tipoVariableCoactivo;
    private List<ValorCondicionCoactivoDTO> lstValorCondicionCoactivo;

    private List<CatalogoDTO> listCatalogoDTO;

    // --- Constructor
    public VariableCondicionCoacDTO() {
    }

    public VariableCondicionCoacDTO(Integer id) {
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

    public CondicionCoactivoDTO getCondicionCoactivo() {
        return this.condicionCoactivo;
    }

    public void setCondicionCoactivo(CondicionCoactivoDTO condicionCoactivo) {
        this.condicionCoactivo = condicionCoactivo;
    }

    public TipoVariableCoactivoDTO getTipoVariableCoactivo() {
        return this.tipoVariableCoactivo;
    }

    public void setTipoVariableCoactivo(TipoVariableCoactivoDTO tipoVariableCoactivo) {
        this.tipoVariableCoactivo = tipoVariableCoactivo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ValorCondicionCoactivoDTO> getLstValorCondicionCoactivo() {
        if (this.lstValorCondicionCoactivo == null) {
            this.lstValorCondicionCoactivo = new java.util.ArrayList<>(1);
        }
        return this.lstValorCondicionCoactivo;
    }

    public void setLstValorCondicionCoactivo(List<ValorCondicionCoactivoDTO> lstValorCondicionCoactivo) {
        this.lstValorCondicionCoactivo = lstValorCondicionCoactivo;
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
