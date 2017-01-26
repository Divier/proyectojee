package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.ResolucionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Feb 02 12:19:01 COT 2016
 */
public class ComparendoResolucionDTO extends ResolucionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ComparendoDTO comparendo;

    // --- Constructor
    public ComparendoResolucionDTO() {
    }

    public ComparendoResolucionDTO(Long id) {
        this.id = id;
    }

    public ComparendoResolucionDTO(Long id, Long idResolucion) {
        super(idResolucion);
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdResolucion() {
        return super.getId();
    }

    public void setIdResolucion(Long idResolucion) {
        super.setId(idResolucion);
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

}
