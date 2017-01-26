package co.com.datatools.c2.dto.formularios;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 06 17:39:17 COT 2015
 */
public class NumeracionFormularioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Date fechaInicial;
    private Date fechaFinal;
    private int digitos;
    private Boolean activo;
    private String trama;
    private List<DetalleNumeracionDTO> detalleNumeracionList;
    private TipoFormularioDTO tipoFormulario;
    private List<RangoFormularioDTO> rangoFormularioList;

    // --- Constructor
    public NumeracionFormularioDTO() {
    }

    public NumeracionFormularioDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getDigitos() {
        return this.digitos;
    }

    public void setDigitos(int digitos) {
        this.digitos = digitos;
    }

    public Boolean getActivo() {
        return this.activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getTrama() {
        return this.trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleNumeracionDTO> getDetalleNumeracionList() {
        if (this.detalleNumeracionList == null) {
            this.detalleNumeracionList = new java.util.ArrayList<>(1);
        }
        return this.detalleNumeracionList;
    }

    public void setDetalleNumeracionList(List<DetalleNumeracionDTO> detalleNumeracionList) {
        this.detalleNumeracionList = detalleNumeracionList;
    }

    public TipoFormularioDTO getTipoFormulario() {
        return this.tipoFormulario;
    }

    public void setTipoFormulario(TipoFormularioDTO tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<RangoFormularioDTO> getRangoFormularioList() {
        if (this.rangoFormularioList == null) {
            this.rangoFormularioList = new java.util.ArrayList<>(1);
        }
        return this.rangoFormularioList;
    }

    public void setRangoFormularioList(List<RangoFormularioDTO> rangoFormularioList) {
        this.rangoFormularioList = rangoFormularioList;
    }

}
