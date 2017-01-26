package co.com.datatools.c2.dto.cartera;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:22:51 COT 2015
 */
public class ConceptoCarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer codigo;
    private String nombre;
    private TipoSaldoDTO tipoSaldo;
    private TipoConceptoCarteraDTO tipoConceptoCartera;
    private Boolean estado;

    // --- Constructor
    public ConceptoCarteraDTO() {
    }

    public ConceptoCarteraDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoSaldoDTO getTipoSaldo() {
        return this.tipoSaldo;
    }

    public void setTipoSaldo(TipoSaldoDTO tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    public TipoConceptoCarteraDTO getTipoConceptoCartera() {
        return this.tipoConceptoCartera;
    }

    public void setTipoConceptoCartera(TipoConceptoCarteraDTO tipoConceptoCartera) {
        this.tipoConceptoCartera = tipoConceptoCartera;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
