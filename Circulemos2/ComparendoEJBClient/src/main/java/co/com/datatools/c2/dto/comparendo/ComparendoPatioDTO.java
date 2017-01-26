package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Tiene la logica para el consulta de comparendos no regenerar
 * 
 * @author Generated
 * @author julio.pinzon (2015-10-21)
 * @version 3.0 - Tue Oct 20 17:35:46 COT 2015
 */
public class ComparendoPatioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ComparendoDTO comparendo;
    private String consecutivoAsignadoGrua;
    private String nombre;
    private Integer numeroGrua;
    private String numeroInforme;
    private Integer numeroPatio;
    private String placaGrua;
    private Integer idPatio;
    private DireccionDTO direccion;

    // Atributo por logica
    private PatioComparendoDTO patio;

    // --- Constructor
    public ComparendoPatioDTO() {
    }

    public ComparendoPatioDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public String getConsecutivoAsignadoGrua() {
        return this.consecutivoAsignadoGrua;
    }

    public void setConsecutivoAsignadoGrua(String consecutivoAsignadoGrua) {
        this.consecutivoAsignadoGrua = consecutivoAsignadoGrua;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroGrua() {
        return this.numeroGrua;
    }

    public void setNumeroGrua(Integer numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getNumeroInforme() {
        return this.numeroInforme;
    }

    public void setNumeroInforme(String numeroInforme) {
        this.numeroInforme = numeroInforme;
    }

    public Integer getNumeroPatio() {
        return this.numeroPatio;
    }

    public void setNumeroPatio(Integer numeroPatio) {
        this.numeroPatio = numeroPatio;
    }

    public String getPlacaGrua() {
        return this.placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public Integer getIdPatio() {
        return this.idPatio;
    }

    public void setIdPatio(Integer idPatio) {
        this.idPatio = idPatio;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public PatioComparendoDTO getPatio() {
        return patio;
    }

    public void setPatio(PatioComparendoDTO patio) {
        this.patio = patio;
    }

    public ComparendoPatioDTO clone() {
        ComparendoPatioDTO clone = new ComparendoPatioDTO(this.id != null ? new Long(this.id) : null);

        clone.setConsecutivoAsignadoGrua(this.consecutivoAsignadoGrua != null ? new String(this.consecutivoAsignadoGrua)
                : null);
        clone.setNombre(this.nombre != null ? new String(this.nombre) : null);
        clone.setNumeroGrua(this.numeroGrua != null ? new Integer(this.numeroGrua) : null);
        clone.setNumeroInforme(this.numeroInforme != null ? new String(this.numeroInforme) : null);
        clone.setNumeroPatio(this.numeroPatio != null ? new Integer(this.numeroPatio) : null);
        clone.setPlacaGrua(this.placaGrua != null ? new String(this.placaGrua) : null);
        clone.setIdPatio(this.idPatio != null ? new Integer(this.idPatio) : null);
        clone.setDireccion(this.direccion != null ? this.direccion.clone() : null);
        return clone;
    }
}
