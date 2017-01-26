package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Feb 08 15:14:27 COT 2016
 */
public class RectificaComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fecha;
    private List<CampoRectificaComparendoDTO> campoRectificaComparendos;
    private ComparendoDTO comparendo;
    private List<RectificaEvidenciaDTO> rectificaEvidencias;

    // --- Constructor
    public RectificaComparendoDTO() {
    }

    public RectificaComparendoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<CampoRectificaComparendoDTO> getCampoRectificaComparendos() {
        if (this.campoRectificaComparendos == null) {
            this.campoRectificaComparendos = new java.util.ArrayList<>(1);
        }
        return this.campoRectificaComparendos;
    }

    public void setCampoRectificaComparendos(List<CampoRectificaComparendoDTO> campoRectificaComparendos) {
        this.campoRectificaComparendos = campoRectificaComparendos;
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<RectificaEvidenciaDTO> getRectificaEvidencias() {
        if (this.rectificaEvidencias == null) {
            this.rectificaEvidencias = new java.util.ArrayList<>(1);
        }
        return this.rectificaEvidencias;
    }

    public void setRectificaEvidencias(List<RectificaEvidenciaDTO> rectificaEvidencias) {
        this.rectificaEvidencias = rectificaEvidencias;
    }

}
